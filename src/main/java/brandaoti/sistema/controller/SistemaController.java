package brandaoti.sistema.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import brandaoti.sistema.dao.AulaDao;
import brandaoti.sistema.dao.ContratoDao;
import brandaoti.sistema.dao.ParcelaDao;
import brandaoti.sistema.dao.PerfilDao;
import brandaoti.sistema.dao.PlanoDao;
import brandaoti.sistema.dao.TreinoDao;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.excel.ProcessaExcel;
import brandaoti.sistema.excel.Tabela;
import brandaoti.sistema.model.Aula;
import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Horario;
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
		@Autowired
		private AulaDao aulaDao;
		
		public static Usuario usuarioSessao;
		public static String atualizarPagina = null;
		public static Boolean logado = false;
		public static String itemMenu = "home";
		public static String paginaAtual = "Dashboard";
		public static String iconePaginaAtual = "fa fa-home";
		
		
		public String gerarMatricula() {
			Random gerador = new Random();
	    	Calendar data = Calendar.getInstance();
	    	int ano = data.get(Calendar.YEAR);
	    	int mes = data.get(Calendar.MONTH);
	    	mes++;
	    	int m = mes;
	    	int dia = data.get(Calendar.DAY_OF_MONTH);
	        int hora = data.get(Calendar.HOUR_OF_DAY); 
	        int min = data.get(Calendar.MINUTE);
	        int seg = data.get(Calendar.SECOND);
	        int numero = gerador.nextInt(100);
	        String matricula = ""+ano+m+dia+hora+min+seg+numero;
	        return matricula;
		}
		
		@RequestMapping(value = {"/","/login"}, produces = "text/plain;charset=UTF-8", method = RequestMethod.GET) // Pagina de Vendas
		public ModelAndView login(@RequestParam(value = "nome", required = false, defaultValue = "Henrique Brandão") String nome) throws SQLException { //Funcao e alguns valores que recebe...
			//Caso não haja registros
			List<Usuario> u = usuarioDao.buscarTudo();
			List<Perfil> p = perfilDao.buscarTudo();
			List<Plano> pl = planoDao.buscarTudo();
			usuarioSessao = null;
			if(p.size() == 0 || p == null) {
				Perfil perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("1");
				perfil.setNome("adm");
				perfil.setAdmin(true);
				perfil.setFuncionario(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("2");
				perfil.setNome("aluno");
				perfil.setAdmin(false);
				perfil.setFuncionario(false);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("3");
				perfil.setNome("funcionario");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
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
			paginaAtual = "Alunos";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/alunos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			
			if(logado) {
				//Caso esteja logado.
				if(tabela.equals("usuario")) {
					modelAndView = new ModelAndView(link);
					paginaAtual = "Alunos";
					try {
						List<Parcela> parcelas = parcelaDao.buscarCliente(""+id);
						for(Parcela p : parcelas) {
							p.setAtivo(false);
							parcelaDao.save(p);
						}
						List<Contrato> contratosCli = contratoDao.buscarIdCliente(""+id);
						for(Contrato co : contratosCli) {
							co.setAtivo(false);
							contratoDao.save(co);
						}
					}catch(Exception e) {}
					Usuario objeto = usuarioDao.findById(id).get();
					objeto.setAtivo(false);
					usuarioDao.save(objeto);
					List<Usuario> usuarios = usuarioDao.buscarTudo();
					modelAndView.addObject("usuarios", usuarios);
					List<Plano> planos = planoDao.buscarTudo();
					modelAndView.addObject("planos", planos);
					atualizarPagina = "/alunos";
				}
				if(tabela.equals("funcionario")) {
					modelAndView = new ModelAndView(link);
					paginaAtual = "Funcionários";
					
					try {
						List<Contrato> contratosFunc = contratoDao.buscarIdCliente(""+id);
						System.out.println("erros: "+contratosFunc.size());
						List<Parcela> parcelas = parcelaDao.buscarCliente(""+id);
						for(Parcela p : parcelas) {
							p.setAtivo(false);
							parcelaDao.save(p);
						}
						for(Contrato co : contratosFunc) {
							co.setAtivo(false);
							contratoDao.save(co);
						}
					}catch(Exception e) {}
					Usuario objeto = usuarioDao.findById(id).get();
					objeto.setAtivo(false);
					usuarioDao.save(objeto);
					List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
					modelAndView.addObject("usuarios", usuarios);
					atualizarPagina = "/funcionarios";
				}
			}
			modelAndView.addObject("atualizarPagina", atualizarPagina);
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			return modelAndView; 
		}
		
		
		
		/* SALVAR EXCEL */
		@RequestMapping(value = "/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
		public ModelAndView uploadExcel(Model model, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe...
			System.out.println("file: "+file);
			paginaAtual = "Financeiro";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/home");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Caso esteja logado.
				ProcessaExcel processaExcel = new ProcessaExcel();
				List<Tabela> tabelas = processaExcel.uploadExcel(file);
		    	String conteudo="";
		   		Integer finalLinha = 0;
		   		Aula a = new Aula();
		   		int coluna = 0;
				switch (tabelaUsada) {  
				 case "aulas" : // CASO SEJA AULAS ---------------------
					 	link = verificaLink("pages/aulas");
					 	modelAndView = new ModelAndView(link);
					 	paginaAtual = "Aulas";
						iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
					 	try {
					 		String inicio="";
			   				String fim="";
				   			for(int i=0; i < tabelas.size(); i++) {
				   				coluna = tabelas.get(i).getColuna();
				   				conteudo = tabelas.get(i).getConteudo();
				   				
				   				if(coluna == 0) inicio = conteudo;
				   				if(coluna == 1) fim = conteudo;
				   				
				   				if(coluna == 2) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Segunda");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 3) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Terça");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 4) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Quarta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 5) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Quinta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 6) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Sexta");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 7) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Sábado");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				if(coluna == 8) {
				   					a = new Aula();
				   					a.setInicio(inicio);
				   					a.setFim(fim);
				   					a.setDiaSemana("Domingo");
				   					a.setNomeAula(conteudo);
				   					aulaDao.save(a);
				   				}
				   				
				   				
				   				
				   				if(finalLinha >= 8) {
				   					finalLinha = -1;
				   				}
				   				finalLinha++;
				   			}
				   		} catch(Exception e) {
				   			System.out.println("Erro: "+ e);
				   		}
					 	List<Aula> aulas = aulaDao.buscarTudo();
					 	modelAndView.addObject("aulas", aulas);
					}
				
			}
			System.out.println("Validado");
			return modelAndView; //retorna a variavel
			
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
			
			List<Plano> planos = planoDao.buscarTudo();
			modelAndView.addObject("planos", planos);			
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			
			
			if(logado) {
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula();
				modelAndView.addObject("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarAlunosRepetidos(aluno.getMatricula(), aluno.getCpf()).size() > 0) {
					repetido = true;
				}
				if(aluno.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						atualizarPagina = "/alunos";
				    	List<Contrato> contratos = new ArrayList<>();
				    	try{
				    		contratos = contratoDao.buscarCliente(""+aluno.getMatricula());
				    	} catch(Exception e) {}
				    	
						Usuario a = new Usuario();
						a = aluno;
						a.setSenha(aluno.getCpf());
						a.setPerfil(perfilDao.buscarSomenteAluno().get(0));
						usuarioDao.save(a);
						
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicioContrato = formato.parse(contrato_inicio);
						Date fimContrato = formato.parse(contrato_fim);
						Contrato c = new Contrato();
						c.setAtivo(true);
						c.setFim(fimContrato);
						c.setInicio(inicioContrato);
						c.setNome(aluno.getMatricula());
						c.setObservacoes(contrato_obs);
						c.setValor(contrato_total);
						c.setValorBruto(contrato_totalContrato);
						c.setSinal(contrato_sinal);
						c.setDesconto(contrato_desconto);
						c.setValor(contrato_total);
						c.setParcelas(contrato_parcelas);
						c.setValorDaParcela(contrato_valorDaParcela);
						c.setVencimento(contrato_vencimento);
						c.setCliente(a);
						contratoDao.save(c);
						contratos.add(c);
						
						a.setContrato(contratos);
						usuarioDao.save(a);
						
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
						}

						
					} catch(Exception e) {
						modelAndView.addObject("erro", e);
					}
				} else if (aluno.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(aluno.getMatricula());
					a.setNome(aluno.getNome());
					a.setDataNascimento(aluno.getDataNascimento());
					a.setTelefone(aluno.getTelefone());
					a.setCelular(aluno.getCelular());
					a.setEndereco(aluno.getEndereco());
					a.setEmail(aluno.getEmail());
					a.setPathImagem(aluno.getPathImagem());
					a.setCep(aluno.getCep());
					a.setBairro(aluno.getBairro());
					a.setCidade(aluno.getCidade());
					a.setEstado(aluno.getEstado());
					a.setPlano(aluno.getPlano());
					usuarioDao.save(a);
					
					if(contratoDao.buscarCliente(aluno.getMatricula()).size() <= 0) {
						SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd"); 
						Date inicioContrato = formato.parse(contrato_inicio);
						Date fimContrato = formato.parse(contrato_fim);
						Contrato c = new Contrato();
						c.setAtivo(true);
						c.setFim(fimContrato);
						c.setInicio(inicioContrato);
						c.setNome(aluno.getMatricula());
						c.setObservacoes(contrato_obs);
						c.setValor(contrato_total);
						c.setValorBruto(contrato_totalContrato);
						c.setSinal(contrato_sinal);
						c.setDesconto(contrato_desconto);
						c.setValor(contrato_total);
						c.setParcelas(contrato_parcelas);
						c.setValorDaParcela(contrato_valorDaParcela);
						c.setVencimento(contrato_vencimento);
						c.setCliente(a);
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
							a.setContrato(contratos);
							usuarioDao.save(a);
						}
					}
				} else if(aluno.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					modelAndView.addObject("erro", "Já existe este CPF / Matrícula.");
				}
				modelAndView.addObject("atualizarPagina", atualizarPagina);
				List<Usuario> usuarios = usuarioDao.buscarAlunos();
				modelAndView.addObject("usuarios", usuarios);
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/funcionarios", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView funcionarios(Usuario funcionario, String acao) throws SQLException {
			paginaAtual = "Funcionários";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/funcionarios");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//Gerando matrícula aleatória
				String matriculaPadrao = gerarMatricula();
				modelAndView.addObject("matriculaPadrao", matriculaPadrao);
				
				Boolean repetido = false;
				if(usuarioDao.buscarFuncionariosRepetidos(funcionario.getMatricula(), funcionario.getCpf()).size() > 0) {
					repetido = true;
				}
				if(funcionario.getMatricula() != null && (acao.equals("salvar")) && !repetido) {
					try {
						atualizarPagina = "/funcionarios";
						Usuario a = new Usuario();
						a = funcionario;
						a.setSenha(funcionario.getCpf());
						a.setPerfil(perfilDao.buscarFuncionario().get(0));
						usuarioDao.save(a);
						modelAndView.addObject("atualizarPagina", atualizarPagina);
					} catch(Exception e) {
						modelAndView.addObject("erro", e);
						System.out.println("Erro: "+e);
					}
				} else if (funcionario.getMatricula() != null && (acao.equals("atualizar")) && repetido){
					Usuario a = usuarioDao.buscarMatricula(funcionario.getMatricula());
					a.setNome(funcionario.getNome());
					a.setDataNascimento(funcionario.getDataNascimento());
					a.setTelefone(funcionario.getTelefone());
					a.setCelular(funcionario.getCelular());
					a.setEndereco(funcionario.getEndereco());
					a.setEmail(funcionario.getEmail());
					a.setPathImagem(funcionario.getPathImagem());
					a.setCep(funcionario.getCep());
					a.setBairro(funcionario.getBairro());
					a.setCidade(funcionario.getCidade());
					a.setEstado(funcionario.getEstado());
					a.setPlano(funcionario.getPlano());
					a.setPerfil(perfilDao.buscarFuncionario().get(0));
					usuarioDao.save(a);
				} else if(funcionario.getMatricula() != null && (acao.equals("salvar")) && repetido) {
					modelAndView.addObject("erro", "Já existe este CPF / Matrícula.");
				}
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				modelAndView.addObject("usuarios", usuarios);
				
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
				List<Aula> aulas = aulaDao.buscarTudo();
			 	modelAndView.addObject("aulas", aulas);
			 	List<Aula> h = aulaDao.buscarhorarios();
			 	List<Horario> horarios = new ArrayList<Horario>();
			 	String ultimoHorarioInicio = "";
			 	String ultimoHorarioFim = "";
			 	for(Aula a : h) {
			 		if(!((ultimoHorarioInicio.equals(a.getInicio())) && (ultimoHorarioFim.equals(a.getFim())))) {
			 			ultimoHorarioInicio = a.getInicio();
			 			ultimoHorarioFim = a.getFim();
			 			Horario hr = new Horario();
			 			hr.setInicio(a.getInicio());
			 			hr.setFim(a.getFim());
			 			System.out.println("Hora: " + hr.getInicio() + " - " + hr.getFim());
			 			horarios.add(hr);
			 		}
			 	}
			 	modelAndView.addObject("horarios", horarios);
				
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
	
	
	




