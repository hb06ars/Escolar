package brandaoti.sistema.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;

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
import brandaoti.sistema.dao.PresencaDao;
import brandaoti.sistema.dao.TreinoDao;
import brandaoti.sistema.dao.UsuarioDao;
import brandaoti.sistema.excel.ProcessaExcel;
import brandaoti.sistema.excel.Tabela;
import brandaoti.sistema.model.Aula;
import brandaoti.sistema.model.Contrato;
import brandaoti.sistema.model.Horario;
import brandaoti.sistema.model.Objeto;
import brandaoti.sistema.model.Parcela;
import brandaoti.sistema.model.Perfil;
import brandaoti.sistema.model.Plano;
import brandaoti.sistema.model.Presenca;
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
		@Autowired
		private PresencaDao presencaDao;
		
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
			if(p == null || p.size() == 0) {
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
				perfil.setNome("Aluno");
				perfil.setAdmin(false);
				perfil.setFuncionario(false);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("3");
				perfil.setNome("Funcionário");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
				
				perfil = new Perfil();
				perfil.setAtivo(true);
				perfil.setCodigo("4");
				perfil.setNome("Professor");
				perfil.setAdmin(false);
				perfil.setFuncionario(true);
				perfil.setProfessor(true);
				perfil.setAluno(true);
				perfilDao.save(perfil);
			}
			if(pl == null || pl.size() == 0) {
				Plano plano = new Plano();
				plano.setCodigo("1");
				plano.setNome("A");
				plano.setAtivo(true);
				plano.setValor(1200.0);
				plano.setDescricao("Plano Básico");
				planoDao.save(plano);
				plano = new Plano();
				plano.setCodigo("2");
				plano.setNome("B");
				plano.setValor(2000.0);
				plano.setAtivo(true);
				plano.setDescricao("Plano Intermediário");
				planoDao.save(plano);
				plano = new Plano();
				plano.setCodigo("3");
				plano.setNome("C");
				plano.setValor(2500.0);
				plano.setAtivo(true);
				plano.setDescricao("Plano Avançado");
				planoDao.save(plano);
			}
			
			
			
			if(u == null || u.size() == 0) {
				Usuario usu = new Usuario();
				usu.setAtivo(true);
				usu.setMatricula("adm");
				usu.setSenha("adm");
				usu.setNome("Admnistrador");
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
					modelAndView.addObject("atualizarPagina", atualizarPagina);
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
					modelAndView.addObject("atualizarPagina", atualizarPagina);
				}
				if(tabela.equals("parcela")) {
					modelAndView = new ModelAndView(link);
					paginaAtual = "Pendências";
					Parcela objeto = parcelaDao.findById(id).get();
					Contrato c = contratoDao.findById(objeto.getContrato().getId()).get();
					objeto.setAtivo(false);
					parcelaDao.save(objeto);
					
					Integer parcelasDoContrato = parcelaDao.buscarPorContrato(c.getId()).size();
					if(parcelasDoContrato <= 0) {
						c.setAtivo(false);
						contratoDao.save(c);
					}
					List<Parcela> pendencias = parcelaDao.buscarPendencias();
					modelAndView.addObject("pendencias", pendencias);
					atualizarPagina = "/pendencias";
					modelAndView.addObject("atualizarPagina", atualizarPagina);
				}
				if(tabela.equals("treino")) {
					link = verificaLink("pages/cadastrarTreinos");
					modelAndView = new ModelAndView(link);
					paginaAtual = "Cadastrar Treinos";
					Treino objeto = treinoDao.findById(id).get();
					Usuario us = usuarioDao.buscarMatricula(objeto.getAluno().getMatricula());
					objeto.setAtivo(false);
					treinoDao.save(objeto);
					try {
						List<Treino> treinos = treinoDao.buscarMatricula(us.getMatricula());
						modelAndView.addObject("treinos", treinos);
					} catch(Exception e) {
						System.out.println("Erro: "+e);
					}
					modelAndView.addObject("usuario", us);
				}
				if(tabela.equals("planos")) {
					link = verificaLink("pages/planos");
					modelAndView = new ModelAndView(link);
					paginaAtual = "Cadastrar novo Plano";
					Plano objeto = planoDao.findById(id).get();
					objeto.setAtivo(false);
					planoDao.save(objeto);
					List<Plano> pl = planoDao.buscarTudo();
					modelAndView.addObject("planos", pl);
				}
			}
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
					 		aulaDao.deleteAll();
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
					 	List<Aula> h = aulaDao.buscarhorarios();
					 	List<Horario> horarios = new ArrayList<Horario>();
					 	String ultimoHorarioInicio = "";
					 	String ultimoHorarioFim = "";
					 	for(Aula aul : h) {
					 		if(!((ultimoHorarioInicio.equals(aul.getInicio())) && (ultimoHorarioFim.equals(aul.getFim())))) {
					 			ultimoHorarioInicio = aul.getInicio();
					 			ultimoHorarioFim = aul.getFim();
					 			Horario hr = new Horario();
					 			hr.setInicio(aul.getInicio());
					 			hr.setFim(aul.getFim());
					 			horarios.add(hr);
					 		}
					 	}
					 	modelAndView.addObject("horarios", horarios);
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
				if(usuarioSessao.getPerfil().getAdmin()) {
					Integer presentesOntem = presencaDao.presentesOntem().size();
					Integer todosAlunos = usuarioDao.buscarAlunos().size();
					List<Parcela> alunosPendentesLista = parcelaDao.buscarPendencias();
					Integer alunosPendentes = 0;
					Usuario a = new Usuario();
					for(Parcela p: alunosPendentesLista) {
						if(a == null || p.getContrato().getCliente() != a) {
							a = p.getContrato().getCliente();
							alunosPendentes++;
						}
					}
					Integer alunosAniversariantes = usuarioDao.buscarAniversariantes().size();
					Integer novosDoMes = usuarioDao.novosDoMes().size();
					List<Objeto> mesesTodos = new ArrayList<Objeto>();
					List<Objeto> mesesManha= new ArrayList<Objeto>();
					List<Objeto> mesesTarde = new ArrayList<Objeto>();
					List<Objeto> mesesNoite = new ArrayList<Objeto>();
					String mesStr = "";
					String valStr = "";
					for(int i = 1; i <= 12; i++) {
						switch(i) {
						  case 1: mesStr ="Jan"; break;
						  case 2: mesStr ="Fev"; break;
						  case 3: mesStr ="Mar"; break;
						  case 4: mesStr ="Abr"; break;
						  case 5: mesStr ="Mai"; break;
						  case 6: mesStr ="Jun"; break;
						  case 7: mesStr ="Jul"; break;
						  case 8: mesStr ="Ago"; break;
						  case 9: mesStr ="Set"; break;
						  case 10: mesStr ="Out"; break;
						  case 11: mesStr ="Nov"; break;
						  case 12: mesStr ="Dez"; break;
						  default:
						}
						Objeto mTodos = new Objeto();
						mTodos.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMes(i).size();
						valStr = valStr.replace(".0", "");
						mTodos.setValor1(valStr);
						mesesTodos.add(mTodos);
						
						Objeto mManha = new Objeto();
						mManha.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesManha(i).size();
						valStr = valStr.replace(".0", "");
						mManha.setValor1(valStr);
						mesesManha.add(mManha);
						
						Objeto mTarde = new Objeto();
						mTarde.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesTarde(i).size();
						valStr = valStr.replace(".0", "");
						mTarde.setValor1(valStr);
						mesesTarde.add(mTarde);
						
						Objeto mNoite = new Objeto();
						mNoite.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesNoite(i).size();
						valStr = valStr.replace(".0", "");
						mNoite.setValor1(valStr);
						mesesNoite.add(mNoite);
					}
					modelAndView.addObject("presentesOntem", presentesOntem);
					modelAndView.addObject("todosAlunos", todosAlunos);
					modelAndView.addObject("alunosPendentes", alunosPendentes);
					modelAndView.addObject("alunosAniversariantes", alunosAniversariantes);
					modelAndView.addObject("novosDoMes", novosDoMes);
					modelAndView.addObject("mesesTodos", mesesTodos);
					modelAndView.addObject("mesesManha", mesesManha);
					modelAndView.addObject("mesesTarde", mesesTarde);
					modelAndView.addObject("mesesNoite", mesesNoite);
				}
				if(!usuarioSessao.getPerfil().getAdmin()) {
					List<Treino> treinos = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					modelAndView.addObject("treinos", treinos);
				}
				
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
				if(usuarioSessao.getPerfil().getAdmin()) {
					Integer presentesOntem = presencaDao.presentesOntem().size();
					Integer todosAlunos = usuarioDao.buscarAlunos().size();
					
					List<Parcela> alunosPendentesLista = parcelaDao.buscarPendencias();
					Integer alunosPendentes = 0;
					Usuario a = new Usuario();
					for(Parcela p: alunosPendentesLista) {
						if(a == null || p.getContrato().getCliente() != a) {
							a = p.getContrato().getCliente();
							alunosPendentes++;
						}
					}
					
					Integer alunosAniversariantes = usuarioDao.buscarAniversariantes().size();
					Integer novosDoMes = usuarioDao.novosDoMes().size();
					
					List<Objeto> mesesTodos = new ArrayList<Objeto>();
					List<Objeto> mesesManha= new ArrayList<Objeto>();
					List<Objeto> mesesTarde = new ArrayList<Objeto>();
					List<Objeto> mesesNoite = new ArrayList<Objeto>();
					String mesStr = "";
					String valStr = "";
					for(int i = 1; i <= 12; i++) {
						switch(i) {
						  case 1: mesStr ="Jan"; break;
						  case 2: mesStr ="Fev"; break;
						  case 3: mesStr ="Mar"; break;
						  case 4: mesStr ="Abr"; break;
						  case 5: mesStr ="Mai"; break;
						  case 6: mesStr ="Jun"; break;
						  case 7: mesStr ="Jul"; break;
						  case 8: mesStr ="Ago"; break;
						  case 9: mesStr ="Set"; break;
						  case 10: mesStr ="Out"; break;
						  case 11: mesStr ="Nov"; break;
						  case 12: mesStr ="Dez"; break;
						  default:
						}
						Objeto mTodos = new Objeto();
						mTodos.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMes(i).size();
						valStr = valStr.replace(".0", "");
						mTodos.setValor1(valStr);
						mesesTodos.add(mTodos);
						
						Objeto mManha = new Objeto();
						mManha.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesManha(i).size();
						valStr = valStr.replace(".0", "");
						mManha.setValor1(valStr);
						mesesManha.add(mManha);
						
						Objeto mTarde = new Objeto();
						mTarde.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesTarde(i).size();
						valStr = valStr.replace(".0", "");
						mTarde.setValor1(valStr);
						mesesTarde.add(mTarde);
						
						Objeto mNoite = new Objeto();
						mNoite.setNome1(mesStr);
						valStr = ""+presencaDao.presentesMesNoite(i).size();
						valStr = valStr.replace(".0", "");
						mNoite.setValor1(valStr);
						mesesNoite.add(mNoite);
					}
					modelAndView.addObject("presentesOntem", presentesOntem);
					modelAndView.addObject("todosAlunos", todosAlunos);
					modelAndView.addObject("alunosPendentes", alunosPendentes);
					modelAndView.addObject("alunosAniversariantes", alunosAniversariantes);
					modelAndView.addObject("novosDoMes", novosDoMes);
					modelAndView.addObject("mesesTodos", mesesTodos);
					modelAndView.addObject("mesesManha", mesesManha);
					modelAndView.addObject("mesesTarde", mesesTarde);
					modelAndView.addObject("mesesNoite", mesesNoite);
				}
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		
		@RequestMapping(value = "/treino", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView treino(Integer proximo, Integer anterior) throws SQLException {
			paginaAtual = "Treino";
			iconePaginaAtual = "fa fa-edit"; //Titulo do menuzinho.
			String link = verificaLink("pages/treino");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				System.out.println("proximo: "+proximo+" / anterior: "+anterior);
				if(proximo != null && proximo > 0) {
					Integer maiorTreino = 0;
					try { maiorTreino =  treinoDao.maiorTreino(usuarioSessao.getMatricula()); } catch(Exception e) {}
					List<Treino> t = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					for(Treino tr : t) {
						if(tr.getultimoTreinoExecutado() >= maiorTreino) {
							tr.setultimoTreinoExecutado(0);
						} else {
							tr.setultimoTreinoExecutado(tr.getultimoTreinoExecutado()+1);
						}
						treinoDao.save(tr);
					}
				}
				if(anterior != null && anterior > 0) {
					List<Treino> t = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
					for(Treino tr : t) {
						if(tr.getultimoTreinoExecutado() <= 0) {
							tr.setultimoTreinoExecutado(0);
						} else {
							tr.setultimoTreinoExecutado(tr.getultimoTreinoExecutado()-1);
						}
						treinoDao.save(tr);
					}
				}
				System.out.println("usuarioSessao.getMatricula(): "+usuarioSessao.getMatricula());
				List<Treino> treinos = treinoDao.buscarMatricula(usuarioSessao.getMatricula());
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
						a.setSenha(aluno.getCpf().replace(".", "").replace("-", ""));
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
		public ModelAndView funcionarios(Usuario funcionario, String acao, String perfil_codigo) throws SQLException {
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
						a.setSenha(funcionario.getCpf().replace(".", "").replace("-", ""));
						a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
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
					a.setPerfil(perfilDao.buscarCodigo(perfil_codigo));
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
				List<Parcela> pendencias = parcelaDao.buscarPendencias();
				modelAndView.addObject("pendencias", pendencias);
				
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
				List<Usuario> usuarios = usuarioDao.buscarAniversariantes();
				modelAndView.addObject("usuarios", usuarios);
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
				GregorianCalendar calendar = new GregorianCalendar();
				int ano = calendar.get(GregorianCalendar.YEAR);
				int mes = calendar.get(GregorianCalendar.MONTH);
				int limiteDias = calendar.getActualMaximum (Calendar.DAY_OF_MONTH);  
				String mesStr ="Janeiro";
				mes++;
				
				switch(mes) {
				  case 1: mesStr ="Janeiro"; break;
				  case 2: mesStr ="Fevereiro"; break;
				  case 3: mesStr ="Março"; break;
				  case 4: mesStr ="Abril"; break;
				  case 5: mesStr ="Maio"; break;
				  case 6: mesStr ="Junho"; break;
				  case 7: mesStr ="Julho"; break;
				  case 8: mesStr ="Agosto"; break;
				  case 9: mesStr ="Setembro"; break;
				  case 10: mesStr ="Outubro"; break;
				  case 11: mesStr ="Novembro"; break;
				  case 12: mesStr ="Dezembro"; break;
				  default:
				}
				
				List<String> dias = new ArrayList<String>();
				String d = "";
				for(int i = 1; i <= limiteDias; i++) {
					if(i < 10) {
						d = "0"+i;
					} else {
						d = ""+i;
					}
					dias.add(d);
				}
				
				//Caso esteja logado.
				List<Usuario> usuarios = usuarioDao.buscarAlunos();
				List<Presenca> presenca = presencaDao.buscarMesAlunos();
				modelAndView.addObject("usuarios", usuarios);
				modelAndView.addObject("presenca", presenca);
				modelAndView.addObject("ano", ano);
				modelAndView.addObject("mes", mesStr);
				modelAndView.addObject("dias", dias);
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
				GregorianCalendar calendar = new GregorianCalendar();
				int ano = calendar.get(GregorianCalendar.YEAR);
				int mes = calendar.get(GregorianCalendar.MONTH);
				int limiteDias = calendar.getActualMaximum (Calendar.DAY_OF_MONTH);  
				String mesStr ="Janeiro";
				mes++;
				
				switch(mes) {
				  case 1: mesStr ="Janeiro"; break;
				  case 2: mesStr ="Fevereiro"; break;
				  case 3: mesStr ="Março"; break;
				  case 4: mesStr ="Abril"; break;
				  case 5: mesStr ="Maio"; break;
				  case 6: mesStr ="Junho"; break;
				  case 7: mesStr ="Julho"; break;
				  case 8: mesStr ="Agosto"; break;
				  case 9: mesStr ="Setembro"; break;
				  case 10: mesStr ="Outubro"; break;
				  case 11: mesStr ="Novembro"; break;
				  case 12: mesStr ="Dezembro"; break;
				  default:
				}
				
				List<String> dias = new ArrayList<String>();
				String d = "";
				for(int i = 1; i <= limiteDias; i++) {
					if(i < 10) {
						d = "0"+i;
					} else {
						d = ""+i;
					}
					dias.add(d);
				}
				
				List<Usuario> usuarios = usuarioDao.buscarFuncionarios();
				List<Presenca> presenca = presencaDao.buscarMesFuncionarios();
				modelAndView.addObject("usuarios", usuarios);
				modelAndView.addObject("presenca", presenca);
				modelAndView.addObject("ano", ano);
				modelAndView.addObject("mes", mesStr);
				modelAndView.addObject("dias", dias);
				
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/cadastrarTreinos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView cadastrarTreinos(Integer salvar,String matricula, String vlrTreinoA,String vlrTreinoB,String vlrTreinoC,String vlrTreinoD,String vlrTreinoE,String vlrTreinoF,String vlrTreinoG) throws SQLException {
			paginaAtual = "Cadastrar Treinos";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/cadastrarTreinos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//... Salvando dados.
				if(salvar != null) {
					if(salvar > 0) {
						String valor="";
						for(int tr = 0 ; tr < 7; tr++) {
							if(tr == 0) valor = vlrTreinoA;
							if(tr == 1) valor = vlrTreinoB;
							if(tr == 2) valor = vlrTreinoC;
							if(tr == 3) valor = vlrTreinoD;
							if(tr == 4) valor = vlrTreinoE;
							if(tr == 5) valor = vlrTreinoF;
							if(tr == 6) valor = vlrTreinoG;
							
							valor = valor.replace("<br>","#");
						    String[] splitado = valor.split("#");
						    String descanso="";
						    String descricao="";
						    String series="";
						    String repeticoes="";
					        for (int i = 1; i < splitado.length; i++) {
					            String s = "#"+splitado[i].replace("[","#").replace("]","#");
					            String[] splitadoB = s.split("#");
					            Integer cont = 0;
					            for (int j = 0; j < splitadoB.length; j++) {
					                if(cont == 1) {
					                	descricao = splitadoB[j];
					                }
					                if(cont == 2) {
					                	repeticoes = splitadoB[j];
					                	String vl = repeticoes;
					                	vl = vl.replace("X","#");
					            	    String[] splitadoV = vl.split("#");
					                    for (int iv = 1; iv < splitadoV.length; iv++) {
					                    	series = splitadoV[0];
					                    	repeticoes = splitadoV[1];
					                    }
					                }
					                if(cont == 3) {
					                	descanso = splitadoB[j].replace(" - ", "");
					                }
					                cont++;
					                if(cont > 3) cont = 0;
					            }
					            Treino t = new Treino();
					            t.setAtivo(true);
					            t.setTipoOrdem(tr); //Treino A,B,C,D...
					            t.setOrdemDoDia(i);
					            t.setDescricao(descricao);
					            t.setSeries(Integer.parseInt(series));
					            t.setRepeticoes(Integer.parseInt(repeticoes));
					            t.setDescanso(descanso);
					            t.setAluno(usuarioDao.buscarMatricula(matricula));
					            treinoDao.save(t);   
					        }
						}
						
						
					}
				}

				// Carregando a tela...
				if(matricula != null && !matricula.equals("")) {
					Usuario u = new Usuario();
					try {
						u = usuarioDao.buscarMatricula(matricula);
						modelAndView.addObject("usuario", u);
						modelAndView.addObject("matricula", matricula);
					} catch(Exception e) {}
					try {
						List<Treino> treinos = treinoDao.buscarMatricula(u.getMatricula());
						modelAndView.addObject("treinos", treinos);
					} catch(Exception e) {}
				}
			}
			return modelAndView; //retorna a variavel
		}
		
		
		@RequestMapping(value = "/planos", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView planos(String acao, Plano plano) throws SQLException {
			paginaAtual = "Planos";
			iconePaginaAtual = "fa fa-user"; //Titulo do menuzinho.
			String link = verificaLink("pages/planos");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				//... Salvando dados.
				if(acao != null) {
					Plano p = new Plano();
					if(acao.equals("atualizar")) {
						p = planoDao.buscarCodigo(plano.getCodigo()).get(0);
					}
					p.setAtivo(true);
					p.setCodigo(plano.getCodigo());
					p.setDescricao(plano.getDescricao());
					p.setNome(plano.getNome());
					p.setValor(plano.getValor());
					planoDao.save(p);
				}
				atualizarPagina = "/planos";
				List<Plano> planos = planoDao.buscarTudo();
				modelAndView.addObject("planos", planos);
			}
			return modelAndView; //retorna a variavel
		}
		
		@RequestMapping(value = "/alterarSenha", produces = "text/plain;charset=UTF-8", method = {RequestMethod.GET,RequestMethod.POST}) // Pagina de Vendas
		public ModelAndView alterarSenha(Integer acao, String matricula,String senha,String novaSenha,String confirmaSenha) throws SQLException {
			paginaAtual = "Alterar Senha";
			iconePaginaAtual = "fa fa-key"; //Titulo do menuzinho.
			String link = verificaLink("pages/alterarSenha");
			itemMenu = link;
			ModelAndView modelAndView = new ModelAndView(link); //JSP que irá acessar.
			modelAndView.addObject("usuario", usuarioSessao);
			modelAndView.addObject("paginaAtual", paginaAtual); 
			modelAndView.addObject("iconePaginaAtual", iconePaginaAtual);
			if(logado) {
				String msg = "";
				//... Salvando dados.
				if(acao != null) {
					if(acao > 0) {
						Usuario u = usuarioDao.fazerLogin(matricula, senha);
						if(u != null && (novaSenha.equals(confirmaSenha)) ) {
							u.setSenha(novaSenha);
							usuarioDao.save(u);
							msg = "Alterado com sucesso.";
							modelAndView.addObject("msgOk", msg);
						} else {
							msg = "Usuário ou senha inválidos.";
							modelAndView.addObject("msg", msg);
						}
					}
				}
			}
			return modelAndView; //retorna a variavel
		}
		
}
	
	
	




