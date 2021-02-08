package brandaoti.sistema.controller;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import brandaoti.sistema.dao.ContratoDao;
import brandaoti.sistema.dao.ParcelaDao;
import brandaoti.sistema.dao.PerfilDao;
import brandaoti.sistema.dao.PlanoDao;
import brandaoti.sistema.dao.TreinoDao;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Parcela;
import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Treino;
import brandaoti.sistema.model.Usuario;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class SistemaController {
		
		@Autowired
		private UsuarioDao usuarioDao;
		@Autowired
		private PerfilDao perfilDao;
		@Autowired
		private TreinoDao treinoDao;
		@Autowired
		private PlanoDao planoDao;
		@Autowired
		private ContratoDao contratoDao;
		@Autowired
		private ParcelaDao parcelaDao;
		
		public static Usuario usuarioSessao;
		public static String atualizarPagina = null;
		public static Boolean logado = false;
		public static String itemMenu = "home";
		public static String paginaAtual = "Dashboard";
		public static String iconePaginaAtual = "fa fa-home";
		
		
		
		@RequestMapping(value = {"/","/login"}, produces = "text/plain;charset=UTF-8", method = RequestMethod.GET) // Pagina de Vendas
		public ModelAndView login(@RequestParam(value = "nome", required = false, defaultValue = "Henrique Brandão") String nome) throws SQLException { //Funcao e alguns valores que recebe...
			//Caso não haja registros
			List<Usuario> u = usuarioDao.findAll();
			List<Perfil> p = perfilDao.findAll();
			List<Plano> pl = planoDao.findAll();
			usuarioSessao = null;
			if(p.size() == 0 || p == null) {
				Perfil perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("1");
				perfil.setNome("adm");
				perfil.setAdmin(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("2");
				perfil.setNome("aluno");
				perfil.setAluno(true);
				perfilDao.save(perfil);
			}
			if(pl.size() == 0 || pl == null) {
				Plano plano = new Plano();
				plano.setCodigo("1");
				plano.setNome("A");
				plano.setAtivo(true);
				plano.setDescricao("Plano Básico");
				planoDao.save(plano);
				plano = new Plano();
				plano.setCodigo("2");
				plano.setNome("B");
				plano.setAtivo(true);
				plano.setDescricao("Plano Intermediário");
				planoDao.save(plano);
				
			}
			
			
			
			if(u.size() == 0 || u == null) {
				Usuario usu = new Usuario();
				usu.setAtivo(true);
				usu.setMatricula("adm");
				usu.setSenha("adm");
				usu.setNome("Admnistrador");
				
				
				// -- Excluir
				usu.setPathImagem("https://www.freeiconspng.com/thumbs/computer-user-icon/computer-user-icon-28.png");
				List<Treino> treinos = new ArrayList<Treino>();
				Treino t = new Treino();
				t.setMatricula(usu.getMatricula());
				t.setTipoOrdem(0);
				t.setRepeticoes(3);
				t.setOrdemDoDia(0);
				t.setDescanso("1min");
				t.setDescricao("Supino Reto");
				treinoDao.save(t);
				treinos.add(t);
				usu.setTreino(treinos);
				// -- Excluir
				
				
				
				
				
				
				
				
				usu.setPerfil(perfilDao.buscarAdm().get(0));
				usuarioDao.save(usu);
			}
			logado = false;
			String link = "index";
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irÃ¡ acessar
			return modelAndView; //retorna a variavel
		}
		
		public String verificaLink(String link) {
			atualizarPagina = null;
			String direcao = "/pages/deslogar";
			if(logado) {
				direcao = link;
			} else {
				direcao = "/pages/deslogar";
			}
			return direcao;
		}
		
		@RequestMapping(value = "/deslogar", method = {RequestMethod.POST, RequestMethod.GET}) // Link que irÃ¡ acessar...
		public ModelAndView deslogar() { //Funcao e alguns valores que recebe...
			ModelAndView modelAndView = new ModelAndView("/pages/deslogar"); //JSP que irao acessar
			usuarioSessao = null;
			logado = false;
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/deletando", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
		public ModelAndView deletando(String tabela,Integer id) { //Função e alguns valores que recebe...
			paginaAtual = "aluno";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/alunos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				if(tabela.equals("usuario")) {
					Usuario objeto = usuarioDao.findById(id).get();
					usuarioDao.delete(objeto);
					List<Usuario> usuarios = usuarioDao.buscarAlunos();
					modelAndView.addObject("usuarios", usuarios);
					List<Plano> planos = planoDao.findAll();
					modelAndView.addObject("planos", planos);
				}
			}
			return modelAndView; 
		}
		
		
		@RequestMapping(value = "/home", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView home(@RequestParam(value = "usuarioVal", defaultValue = "") String usuarioVal, @RequestParam(value = "senhaVal", defaultValue = "") String senhaVal) throws SQLException {
			String link = verificaLink("home");
			itemMenu = link;
			if(usuarioSessao == null) {
				Usuario u = usuarioDao.fazerLogin(usuarioVal, senhaVal);
				usuarioSessao = u;
			}
			if((usuarioSessao != null) || logado) {
				logado=true;
				if(usuarioSessao.getPerfil().getAdmin()) {
					paginaAtual = "Financeiro";
					iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
					link = verificaLink("pages/financeiro"); //Colocar regra se for ADM ou Aluno.
				} else {
					paginaAtual = "Meu Treino";
					iconePaginaAtual = "fa fa-edit"; //Titulo do menuzinho.
					link = verificaLink("pages/treino"); //Colocar regra se for ADM ou Aluno.
				}
			} else {
				logado=false;
				link = verificaLink("pages/deslogar"); 
			}
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}

		
		@RequestMapping(value = "/financeiro", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView financeiro() throws SQLException {
			paginaAtual = "Financeiro";
			iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
			String link = verificaLink("pages/financeiro");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/treino", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView treino() throws SQLException {
			paginaAtual = "Treino";
			iconePaginaAtual = "fa fa-edit"; //Titulo do menuzinho.
			String link = verificaLink("pages/treino");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				List<Treino> treinos = usuarioDao.treinosUsario(usuarioSessao);
				System.out.println("getMatricula: "+usuarioSessao.getMatricula());
				System.out.println("treinos: "+treinos.size());
				modelAndView.addObject("treinos", treinos);
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/alunos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView alunos(Usuario aluno, String acao, String contrato_obs, Integer contrato_vencimento, String contrato_inicio, String contrato_fim, Double contrato_totalContrato, Double contrato_sinal, Double contrato_desconto, Double contrato_total, Integer contrato_parcelas, Double contrato_valorDaParcela) throws SQLException, ParseException {
			System.out.println("acao: "+acao);
			paginaAtual = "Alunos";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/alunos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			
			List<Plano> planos = planoDao.findAll();
			modelAndView.addObject("planos", planos);
			
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				if(aluno.getMatricula() != null && (acao.equals("salvar") || acao.equals("atualizar"))) {
					try {
						atualizarPagina = "/alunos";
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicioContrato = formato.parse(contrato_inicio);
						Date fimContrato = formato.parse(contrato_fim);
						Contrato c = new Contrato();
						if(acao.equals("atualizar")) {
							c = contratoDao.buscarCliente(aluno.getMatricula()).get(0);
						}
						c.setAtivo(true);
						c.setCliente(usuarioSessao);
						c.setFim(fimContrato);
						c.setInicio(inicioContrato);
						c.setNome(usuarioSessao.getMatricula());
						c.setObservacoes(contrato_obs);
						c.setValor(contrato_total);
						c.setValorBruto(contrato_totalContrato);
						c.setSinal(contrato_sinal);
						c.setDesconto(contrato_desconto);
						c.setValor(contrato_total);
						c.setParcelas(contrato_parcelas);
						c.setValorDaParcela(contrato_valorDaParcela);
						c.setVencimento(contrato_vencimento);
						contratoDao.save(c);
						
						Date hoje = new Date();
					    Calendar cal = Calendar.getInstance();
				        cal.setTime(hoje);
				        cal.add(Calendar.MONTH, 1);
				        int dia = cal.get(Calendar.DAY_OF_MONTH);
				        int ano = cal.get(Calendar.YEAR);
				        int mes = cal.get(Calendar.MONTH);
				        mes++;
				        String strDia = contrato_vencimento+"";
				        String strMes = mes+"";
				        String strAno = ano+"";
				        if(dia < 10) strDia = "0"+strDia;
				        if(mes < 10) strMes = "0"+strMes;
				        if(ano < 10) strAno = "0"+strAno;
				        
						for(int i = 0 ; i < contrato_parcelas; i++) {
							Parcela p = new Parcela();
							p.setContrato(c);
							p.setIndice((i+1)+"");
							p.setPago(false);
							p.setValor(contrato_valorDaParcela);
							//Vencimento
							cal.add(Calendar.MONTH, 1);
							mes = cal.get(Calendar.MONTH);
							ano = cal.get(Calendar.YEAR);
							dia = cal.get(Calendar.DAY_OF_MONTH);
							mes++;
							strDia = dia+"";
					        strMes = mes+"";
					        strAno = ano+"";
					        if(contrato_vencimento < 10) strDia = "0"+contrato_vencimento;
					        if(mes < 10) strMes = "0"+strMes;
					        if(ano < 10) strAno = "0"+strAno;
					        p.setVencimento(LocalDate.parse(strAno+"-"+strMes+"-"+strDia));
							
					    	parcelaDao.save(p);
						
					    	List<Contrato> contratos = new ArrayList<>();
							contratos.add(c);
							Usuario a = new Usuario();
							a = aluno;
							a.setSenha(aluno.getCpf());
							a.setPerfil(perfilDao.buscarAluno().get(0));
							a.setContrato(contratos);
							usuarioDao.save(a);
						}
						modelAndView.addObject("atualizarPagina", atualizarPagina);
					} catch(Exception e) {
						modelAndView.addObject("erro", e);
					}
				}
				List<Usuario> usuarios = usuarioDao.buscarAlunos();
				modelAndView.addObject("usuarios", usuarios);
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/funcionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView funcionarios() throws SQLException {
			paginaAtual = "Funcionários";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/funcionarios");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/aulas", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView aulas() throws SQLException {
			paginaAtual = "Aulas";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/aulas");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/pendencias", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView pendencias() throws SQLException {
			paginaAtual = "Pendências";
			iconePaginaAtual = "fa fa-money"; //Titulo do menuzinho.
			String link = verificaLink("pages/pendencias");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/aniversariantes", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView aniversariantes() throws SQLException {
			paginaAtual = "Aniversariantes";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/aniversariantes");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/presencaAlunos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView presencaAlunos() throws SQLException {
			paginaAtual = "Presença dos Alunos";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/presencaAlunos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/presencaFuncionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView presencaFuncionarios() throws SQLException {
			paginaAtual = "Presença dos Funcionários";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/presencaFuncionarios");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
}
	
	
	




