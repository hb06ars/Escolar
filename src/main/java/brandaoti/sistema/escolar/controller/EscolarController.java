package brandaoti.sistema.escolar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import brandaoti.sistema.escolar.dao.AlunosDao;
import brandaoti.sistema.escolar.dao.HorarioDao;
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.PeriodoDao;
import brandaoti.sistema.escolar.dao.RecadoDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.excel.ProcessaExcel;
import brandaoti.sistema.escolar.excel.Tabela;
import brandaoti.sistema.escolar.model.Alunos;
import brandaoti.sistema.escolar.model.Horarios;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Periodos;
import brandaoti.sistema.escolar.model.Recado;
import brandaoti.sistema.escolar.model.Usuario;


@Controller
public class EscolarController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private PerfilDao perfilDao;
	@Autowired
	private PeriodoDao periodoDao;
	@Autowired
	private HorarioDao horarioDao;
	@Autowired
	private AlunosDao alunosDao;
	@Autowired
	private RecadoDao recadoDao;
	
	//public static Usuario usuarioSessao;
	//public static String atualizarPagina = null;
	public static String mensagem = "";
	public static String tituloMensagem = "";
	public static String tipoMensagem = "";
	public static String periodoAtual = "";
	public static String hoje = "";
	//public static String itemMenuSelecionado = "home";
	
	public void registraMsg(String titulo, String msg, String tipo) {
		tituloMensagem = titulo;
		mensagem = msg;
		tipoMensagem = tipo;
	}
	
	public HttpServletRequest enviaMsg(HttpServletRequest request) {
		request.setAttribute("mensagem", mensagem);
		request.setAttribute("tituloMensagem", tituloMensagem);
		request.setAttribute("tipoMensagem", tipoMensagem);
		mensagem = null;
		tituloMensagem = null;
		tipoMensagem = null;
		return request;
	}
	
	
	public void buscarPeriodoAtual() {
		LocalDateTime agora = LocalDateTime.now();
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
		String horaFormatada = formatterHora.format(agora);
		Integer horaAtual = Integer.parseInt(horaFormatada.substring(0, 2));
		Integer minutoAtual = Integer.parseInt(horaFormatada.substring(3, 5));
		List<Periodos> periodos = periodoDao.ordenado();
		Integer inicioHoraPeriodo = 0;
		Integer inicioMinutoPeriodo = 0;
		Integer fimHoraPeriodo = 0;
		Integer fimMinutoPeriodo = 0;
		Integer repeticoes = 0;
		Boolean encontrado = false;
		while(repeticoes < 24) {
			for(Periodos p : periodos) {
				inicioHoraPeriodo = Integer.parseInt(p.getInicio().substring(0, 2));
				inicioMinutoPeriodo = Integer.parseInt(p.getInicio().substring(3,5));
				fimHoraPeriodo = Integer.parseInt(p.getFim().substring(0, 2));
				fimMinutoPeriodo = Integer.parseInt(p.getFim().substring(3,5));
				if(horaAtual >= inicioHoraPeriodo && horaAtual <= fimHoraPeriodo) {
					if(horaAtual == fimHoraPeriodo ) {
						if(minutoAtual <= fimMinutoPeriodo) {
							periodoAtual = p.getNome();
							encontrado = true;
						}
					} else {
						periodoAtual = p.getNome();
						encontrado = true;
					}
				}
			}
			if(!encontrado) {
				horaAtual++;
				minutoAtual++;
				if(minutoAtual >=60 ) minutoAtual = 0;
				if(horaAtual >=24 ) horaAtual = 0;
			} else {
				repeticoes = 24;
			}
			repeticoes++;
		}
		System.out.println("OK: " + periodoAtual);
	}
	
	
	public String diaDaSemana() {
		Date d = new Date();
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		String nome = "";
		int dia = c.get(c.DAY_OF_WEEK);
		switch(dia){
		case Calendar.SUNDAY: nome = "Domingo";
			break;
		case Calendar.MONDAY: nome = "Segunda";
			break;
		case Calendar.TUESDAY: nome = "Terça";
			break;
		case Calendar.WEDNESDAY: nome = "Quarta";
			break;
		case Calendar.THURSDAY: nome = "Quinta";
			break;
		case Calendar.FRIDAY: nome = "Sexta";
			break;
		case Calendar.SATURDAY: nome = "Sábado";
			break;
		}
		return nome;
	}
	
	public void hoje() {
		Calendar c = Calendar.getInstance();
		int ano = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		m++;
		String mes = ""+m;
		if(m < 10){
		    mes = "0"+m;
		}
		int d = c.get(Calendar.DAY_OF_MONTH);
        String dia=""+d;
		if(d < 10){
		    dia = "0"+d;
		}
		hoje = ano+"-"+mes+"-"+dia;
	}
	
	@RequestMapping(value = {"/","/index"} , produces = "text/plain;charset=UTF-8", method = RequestMethod.GET) 
		public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Usuario> usu = usuarioDao.findAll();
		List<Perfil> perfis = perfilDao.findAll();
		List<Periodos> periodos = periodoDao.findAll();
		hoje();
		if(perfis.size() == 0) {
			Perfil p = new Perfil();
			p.setAtivo(true);
			p.setNome("Admnistrador");
			p.setCodigo("1");
			p.setAdmin(true);
			perfilDao.save(p);
			
			p = new Perfil();
			p.setAtivo(true);
			p.setNome("Funcionário");
			p.setCodigo("2");
			p.setFuncionario(true);
			perfilDao.save(p);
			
			p = new Perfil();
			p.setAtivo(true);
			p.setNome("Professor");
			p.setCodigo("3");
			p.setProfessor(true);
			perfilDao.save(p);
			
			p = new Perfil();
			p.setAtivo(true);
			p.setNome("Aluno");
			p.setCodigo("4");
			p.setAluno(true);
			perfilDao.save(p);
		}
		if(usu == null || usu.size() == 0) {
			Usuario u = new Usuario();
			u.setAtivo(true);
			u.setCargo("Admnistrador");
			u.setTelefone("(11)88888-8888");
			u.setEmail("teste@hotmail.com");
			u.setPerfil(perfilDao.buscarAdm().get(0));
			u.setLogin("adm");
			u.setNome("Admnistrador");
			u.setSenha("adm");
			usuarioDao.save(u);
		}
		if(periodos.size() == 0) {
			Periodos p = new Periodos();
			p.setCodigo("1");
			p.setNome("Manhã");
			p.setInicio("08:00");
			p.setFim("12:20");
			periodoDao.saveAndFlush(p);
			
			p = new Periodos();
			p.setCodigo("2");
			p.setNome("Tarde");
			p.setInicio("13:00");
			p.setFim("18:20");
			periodoDao.saveAndFlush(p);
			
			p = new Periodos();
			p.setCodigo("3");
			p.setNome("Noite");
			p.setInicio("19:00");
			p.setFim("22:45");
			periodoDao.saveAndFlush(p);
		}
		buscarPeriodoAtual();
		
		
		List<Usuario> usuarios = usuarioDao.zeraComparecimento(hoje);
		for(Usuario u : usuarios) {
			u.setUltimoComparecimento(null);
			u.setCompareceu(false);
			usuarioDao.saveAndFlush(u);
		}
		List<Horarios> horarios = horarioDao.zeraComparecimento(hoje);
		for(Horarios h : horarios) {
			h.setSubstituto(null);
			h.setUltimaAtualizacao(null);
			horarioDao.saveAndFlush(h);
		}
		
		if(session.getAttribute("usuarioSessao") != null) {
			response.sendRedirect("/home");
		} else {
			request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
		}
		request.setAttribute("itemMenuSelecionado", "home");
	}
	
	@RequestMapping(value = "/deslogar")
	public void deslogar(HttpServletRequest request, HttpServletResponse response) throws IOException {  
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/");
	}
	
	
	@RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void logar(HttpServletRequest request, HttpServletResponse response, String usuarioVal, String senhaVal) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		String link = "deslogar";
		if(session.getAttribute("usuarioSessao") == null) {
			Usuario usu = usuarioDao.fazerLogin(usuarioVal, senhaVal);
			if(usu != null && usu.getId() != null) {
				session.setAttribute("usuarioSessao",usu);
				link = "pages/home";
				itemMenuSelecionado = "pages/home";
				session.setAttribute("usuarioSessao", usu);
			}
		} else {
			link = "pages/home";
		}
		request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response);
	}
	
	
	
	
	
	@RequestMapping(value = "/adm/deletando/{tabela}/{id}", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public void  deletando(HttpServletRequest request, HttpServletResponse response, String tabela, @PathVariable("id") Integer id) throws ServletException, IOException { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			request.setAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/"+tabela;
			if(tabela.equals("alunos")) {
				atualizarPagina = "/alunos";
				Alunos objeto = alunosDao.findById(id).get();
				alunosDao.delete(objeto);
				List<Alunos> alunos = alunosDao.findAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("alunos", alunos);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("funcionarios")) {
				atualizarPagina = "/funcionarios";
				Usuario objeto = usuarioDao.findById(id).get();
				usuarioDao.delete(objeto);
				List<Usuario> usuarios = usuarioDao.findAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("usuarios", usuarios);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("recados")) {
				atualizarPagina = "/recados";
				Recado objeto = recadoDao.findById(id).get();
				recadoDao.delete(objeto);
				List<Recado> recados = recadoDao.findAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("recados", recados);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Recado", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("periodos")) {
				atualizarPagina = "/periodos";
				Periodos objeto = periodoDao.findById(id).get();
				periodoDao.delete(objeto);
				List<Periodos> periodos = periodoDao.findAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("periodos", periodos);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Período", "Deletado com sucesso.", "erro");
			}
		}
		 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/adm/presencaConfirmada/{campo}/{id}", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public void  presencaConfirmada(HttpServletRequest request, HttpServletResponse response, String campo, @PathVariable("id") Integer id) throws ServletException, IOException { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			request.setAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/presenca";
			Boolean compareceu = false;
			if(campo.equals("faltou")) {
				compareceu = false;
			} else {
				compareceu = true;
			}
			String diaDaSemanaAtual = diaDaSemana();
			atualizarPagina = "/presenca";
			Usuario objeto = usuarioDao.findById(id).get();
			objeto.setCompareceu(compareceu);
			objeto.setUltimoComparecimento(hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(periodoAtual, diaDaSemanaAtual);
			request.setAttribute("atualizarPagina", atualizarPagina);
			request.setAttribute("horarios", horarios);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			registraMsg("Confirmado", "Presença confirmada com sucesso.", "info");
		}
		 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/professor/confirmarPresenca", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public void  confirmarPresencaProfessor(HttpServletRequest request, HttpServletResponse response, Integer professorAtualConfirma) throws ServletException, IOException { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			request.setAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = diaDaSemana();
			atualizarPagina = "/horarios";
			Usuario objeto = usuarioDao.findById(professorAtualConfirma).get();
			objeto.setCompareceu(true);
			objeto.setUltimoComparecimento(hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(periodoAtual, diaDaSemanaAtual);
			request.setAttribute("atualizarPagina", atualizarPagina);
			request.setAttribute("horarios", horarios);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			registraMsg("Confirmado", "Presença confirmada com sucesso.", "info");
		}
		 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/professor/substituir", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public void  substituirProfessor(HttpServletRequest request, HttpServletResponse response, Integer professorAtual, Integer professorSubstituto, String todasAulas, Integer horarioId) throws ServletException, IOException { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
			request.setAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = diaDaSemana();
			atualizarPagina = "/horarios";
			System.out.println("horarioId: "+horarioId);
			System.out.println("professorAtual: "+professorAtual);
			System.out.println("professorSubstituto: "+professorSubstituto);
			System.out.println("todasAulas: "+todasAulas);
			Horarios apenasUmaAula = horarioDao.findById(horarioId).get();
			List<Horarios> todas = horarioDao.todasAulasProfessor(professorAtual);
			Usuario s = usuarioDao.findById(professorSubstituto).get();
			s.setCompareceu(true);
			s.setUltimoComparecimento(hoje);
			
			if(todasAulas.equals("todas")) {
				for(Horarios hor : todas) {
					hor.setSubstituto(s);
					horarioDao.saveAndFlush(hor);
				}
			} else {
				apenasUmaAula.setSubstituto(s);
				horarioDao.saveAndFlush(apenasUmaAula);
			}
			usuarioDao.saveAndFlush(s);
			
			List<Horarios> horarios = horarioDao.buscarPeriodo(periodoAtual, diaDaSemanaAtual);
			request.setAttribute("atualizarPagina", atualizarPagina);
			request.setAttribute("horarios", horarios);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			registraMsg("Confirmado", "Substituição confirmada com sucesso.", "info");
		}
		 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	/* SALVAR EXCEL */
	@RequestMapping(value = "/adm/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
	public void  uploadExcel(HttpServletRequest request, HttpServletResponse response, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe..
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		String link = "deslogar";
		if(usuarioSessao != null && usuarioSessao.getId() != null) {
			link = "/pages/alunos"; // Alunos ---------------------
			ProcessaExcel processaExcel = new ProcessaExcel();
			List<Tabela> tabelas = processaExcel.uploadAlunos(file);
	    	String conteudo="";
	   		Integer finalLinha = 0;
	   		Alunos a = new Alunos();
	   		Usuario u = new Usuario();
	   		Horarios h = new Horarios();
	   		int coluna = 0;
	   		
			switch (tabelaUsada) {  
		       case "alunos" : // CASO SEJA ALUNO ---------------------
		   		try {
		   			for(int i=0; i < tabelas.size(); i++) {
		   				coluna = tabelas.get(i).getColuna();
		   				conteudo = tabelas.get(i).getConteudo();
		   				if(coluna == 0) a.setLogin(conteudo);
		   				if(coluna == 1) a.setSenha(conteudo);
		   				if(coluna == 2) {
		   					if(conteudo.toLowerCase().contains("sim") || conteudo.toLowerCase().contains("1")) {
		   						if(coluna == 2) a.setAtivo(true);
		   					} else {
		   						if(coluna == 2) a.setAtivo(false);
		   					}
		   				}
		   				if(coluna == 3) a.setNome(conteudo);
		   				if(coluna == 4) a.setEmail(conteudo);
		   				if(coluna == 5) a.setRa(conteudo);
		   				if(coluna == 6) a.setRg(conteudo);
		   				if(coluna == 7) a.setCpf(conteudo);
		   				try {
		   					if(coluna == 8) {
		   						a.setDataNascimento(LocalDate.parse(conteudo));
		   					}
		   				} catch(Exception e) {}
		   				if(coluna == 9) a.setSerie(conteudo);
		   				if(coluna == 10) a.setTurma(conteudo);
		   				if(coluna == 11) a.setTelefone(conteudo);
		   				if(coluna == 12) a.setEndereco(conteudo);
		   				if(coluna == 13) a.setCep(conteudo);
		   				if(coluna == 14) a.setBairro(conteudo);
		   				if(coluna == 15) a.setCidade(conteudo);
		   				if(coluna == 16) a.setEstado(conteudo);
		   				if(coluna == 17) a.setResponsavel(conteudo);
		   				if(coluna == 18) a.setCpfResponsavel(conteudo);
		   				if(coluna == 19) {
		   					if(conteudo.toLowerCase().contains("sim") || conteudo.toLowerCase().contains("1")) {
		   						a.setSuspensao(true);
		   					} else {
		   						a.setSuspensao(false);
		   					}
		   				}
		   				try {
		   					if(coluna == 20) a.setPerfil(perfilDao.buscarNome(""+conteudo).get(0));
		   				} catch(Exception e) {}
		   				if(finalLinha >= 20) {
		   					finalLinha = -1;
		   					alunosDao.save(a);
		   					a = new Alunos();
		   				}
		   				finalLinha++;
		   			}
		   		} catch(Exception e) {}
		    	link = "/pages/alunos";
		    	atualizarPagina = "/alunos";
		    	break;
		    	
		       case "funcionarios" : // CASO SEJA ALUNO ---------------------
			   		try {
			   			for(int i=0; i < tabelas.size(); i++) {
			   				coluna = tabelas.get(i).getColuna();
			   				conteudo = tabelas.get(i).getConteudo();
			   				System.out.println(conteudo);
			   				if(coluna == 0) u.setNome(conteudo);
			   				if(coluna == 1) u.setCargo(conteudo);
			   				try {
			   					if(coluna == 2) u.setPerfil(perfilDao.buscarNome(""+conteudo).get(0));
			   				} catch(Exception e) {}
			   				if(coluna == 3) u.setLogin(conteudo);
			   				if(coluna == 4) u.setSenha(conteudo);
			   				if(coluna == 5) u.setTelefone(conteudo);
			   				if(coluna == 6) {
			   					if(conteudo.toLowerCase().contains("sim") || conteudo.toLowerCase().contains("1")) {
			   						u.setAtivo(true);
			   					} else {
			   						u.setAtivo(false);
			   					}
			   				}
			   				if(coluna == 7) u.setEmail(conteudo);
			   				
			   				if(finalLinha >= 7) {
			   					finalLinha = -1;
			   					usuarioDao.save(u);
			   					u = new Usuario();
			   				}
			   				finalLinha++;
			   			}
			   		} catch(Exception e) {}
			    	link = "/pages/funcionarios";
			    	atualizarPagina = "/funcionarios";
			    	break;
			    	
			    	
		       case "cadHorarios" : // CASO SEJA ALUNO ---------------------
			   		try {
			   			for(int i=0; i < tabelas.size(); i++) {
			   				coluna = tabelas.get(i).getColuna();
			   				conteudo = tabelas.get(i).getConteudo();
			   				System.out.println("Conteudo: "+conteudo);
			   				if(coluna == 0) h.setDiaDaSemana(conteudo);
			   				try {
			   					if(coluna == 1) h.setPeriodo(periodoDao.porNome(""+conteudo).get(0));
			   				} catch(Exception e) {System.out.println("ERRO: "+e);}
			   				if(coluna == 2) h.setHorarioDaAula(conteudo);
			   				if(coluna == 3) h.setSala(Integer.parseInt(""+conteudo));
			   				if(coluna == 4) h.setTurma(conteudo);
			   				if(coluna == 5) h.setSerie(conteudo);
			   				if(coluna == 6) h.setDisciplina(conteudo);
			   				try {
			   					if(coluna == 7) h.setUsuario(usuarioDao.buscaLogin(""+conteudo).get(0));
			   				} catch(Exception e) {System.out.println("ERRO: "+e);}
			   				
			   				if(finalLinha >= 7) {
			   					finalLinha = -1;
			   					horarioDao.save(h);
			   					h = new Horarios();
			   				}
			   				finalLinha++;
			   			}
			   		} catch(Exception e) {}
			    	link = "/pages/cadastroHorarios";
			    	atualizarPagina = "/cadHorarios";
			    	break;
		    	
		    	
		    	
			}
			registraMsg("Upload", "Upload realizado com sucesso.", "info");
			request.setAttribute("atualizarPagina", atualizarPagina); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response);
	}
	
	@RequestMapping(value = "/alunos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  alunos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			link = "pages/alunos";
			itemMenuSelecionado = "pages/alunos";
			List<Alunos> alunos = alunosDao.findAll();
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("alunos", alunos); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/funcionarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  funcionarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			link = "pages/funcionarios";
			List<Usuario> funcionarios = usuarioDao.findAll();
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("funcionarios", funcionarios); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/recados", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  recados(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			itemMenuSelecionado = "pages/recados";
			link = "pages/recados";
			List<Recado> recados = recadoDao.ordenado();
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("recados", recados); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/periodos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  periodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			link = "pages/periodos";
			List<Periodos> periodos = periodoDao.findAll();
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("periodos", periodos); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/cadHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  cadHorarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			link = "pages/cadastroHorarios";
			List<Horarios> horarios = horarioDao.findAll();
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("horarios", horarios); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/presenca", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  presenca(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		
		if(usuarioSessao.getId() != null && usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			itemMenuSelecionado = "pages/presenca";
			String diaDaSemanaAtual = diaDaSemana();
			List<Usuario> usuarios = horarioDao.presenca(periodoAtual, diaDaSemanaAtual);
			for(int i = 0 ; i < usuarios.size(); i++) {
				System.out.println("usuarios: " + usuarios.get(i).getNome());
			}
			link = "pages/presenca";
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("usuarios", usuarios); 
			request.setAttribute("periodoAtual", periodoAtual);
			request.setAttribute("diaDaSemanaAtual", diaDaSemanaAtual);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	@RequestMapping(value = "/horarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  horarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		System.out.println("Horarios OK");
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
			System.out.println("Horarios: "+usuarioSessao.getNome());
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			itemMenuSelecionado = "pages/horarios";
			link = "pages/horarios";
			String diaDaSemanaAtual = diaDaSemana();
			List<Integer> quantidadeDeSalas = horarioDao.qtdSalas(periodoAtual, diaDaSemanaAtual);
			List<String> quantidadeDeSeries = horarioDao.qtdSeries(periodoAtual, diaDaSemanaAtual);
			List<String> quantidadeDeHorarios = horarioDao.qtdHorarios(periodoAtual, diaDaSemanaAtual);
			List<Periodos> periodos = periodoDao.periodos();
			List<Horarios> horarios = horarioDao.buscarPeriodo(periodoAtual, diaDaSemanaAtual);
			request.setAttribute("professores", usuarioDao.professores());
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("horarios", horarios); 
			request.setAttribute("periodoAtual", periodoAtual); 
			request.setAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
			request.setAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			request.setAttribute("quantidadeDeSeries", quantidadeDeSeries);
			request.setAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			request.setAttribute("periodos", periodos);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/meusHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  meusHorarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			if(usuarioSessao.getPerfil().getProfessor()) {
				String diaDaSemanaAtual = diaDaSemana();
				List<Periodos> periodos = periodoDao.periodos();
				List<Horarios> horarios = horarioDao.filtroTodasAulasProfessor(usuarioSessao.getId(), periodoAtual, diaDaSemanaAtual);
				link = "pages/meusHorarios";
				request.setAttribute("usuarioSessao", usuarioSessao);
				request.setAttribute("horarios", horarios); 
				request.setAttribute("periodoAtual", periodoAtual); 
				request.setAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
				request.setAttribute("periodos", periodos);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/filtrarMeusHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  filtrarMeusHorarios(HttpServletRequest request, HttpServletResponse response, String semana, Integer periodo) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		
		switch (semana) {
		case "seg":
			semana = "Segunda";
			break;
		case "ter":
			semana = "Terça";
			break;
		case "qua":
			semana = "Quarta";
			break;
		case "qui":
			semana = "Quinta";
			break;
		case "sex":
			semana = "Sexta";
			break;
		case "sab":
			semana = "Sábado";
			break;
		case "dom":
			semana = "Domingo";
			break;
		default:
			break;
	}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			if(usuarioSessao.getPerfil().getProfessor()) {
				String semanaEscolhida = semana;
				String periodoEscolhido = periodoDao.findById(periodo).get().getNome();
				String diaDaSemanaAtual = semanaEscolhida;
				List<Periodos> periodos = periodoDao.periodos();
				List<Horarios> horarios = horarioDao.filtroTodasAulasProfessor(usuarioSessao.getId(), periodoEscolhido, semanaEscolhida);
			
				link = "pages/meusHorarios";
				request.setAttribute("usuarioSessao", usuarioSessao);
				request.setAttribute("horarios", horarios); 
				request.setAttribute("periodoAtual", periodoEscolhido); 
				request.setAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
				request.setAttribute("periodos", periodos);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/filtrarHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  filtrarHorarios(HttpServletRequest request, HttpServletResponse response, String semana, Integer periodo) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		
		switch (semana) {
			case "seg":
				semana = "Segunda";
				break;
			case "ter":
				semana = "Terça";
				break;
			case "qua":
				semana = "Quarta";
				break;
			case "qui":
				semana = "Quinta";
				break;
			case "sex":
				semana = "Sexta";
				break;
			case "sab":
				semana = "Sábado";
				break;
			case "dom":
				semana = "Domingo";
				break;
			default:
				break;
		}
		
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			link = "pages/horarios";
			String semanaEscolhida = semana;
			String periodoEscolhido = periodoDao.findById(periodo).get().getNome();
			System.out.println("Filtro: "+semana + periodo);
			List<Integer> quantidadeDeSalas = horarioDao.qtdSalas(periodoEscolhido, semanaEscolhida);
			List<String> quantidadeDeSeries = horarioDao.qtdSeries(periodoEscolhido, semanaEscolhida);
			List<String> quantidadeDeHorarios = horarioDao.qtdHorarios(periodoEscolhido, semanaEscolhida);
			List<Horarios> horarios = horarioDao.buscarPeriodo(periodoEscolhido, semanaEscolhida);
			List<Periodos> periodos = periodoDao.periodos();
			
			request.setAttribute("professores", usuarioDao.professores());
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("horarios", horarios); 
			request.setAttribute("periodoAtual", periodoEscolhido); 
			request.setAttribute("diaDaSemanaAtual", semanaEscolhida); 
			request.setAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			request.setAttribute("quantidadeDeSeries", quantidadeDeSeries);
			request.setAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			request.setAttribute("periodos", periodos);
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		
		enviaMsg(request);
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}	
	
	
	@RequestMapping(value = "/alunos/salvarAluno", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  salvarAlunos(HttpServletRequest request, HttpServletResponse response, Alunos aluno, String nasc, Integer ID, String permissaoFunc) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && (usuarioSessao.getPerfil().getAdmin() != null || usuarioSessao.getPerfil().getFuncionario() != null)) {
		link = "pages/alunos";
			LocalDate data = LocalDate.parse(nasc);
		Perfil p = new Perfil();
		if (permissaoFunc.toLowerCase().contains("aluno")) {
			p = perfilDao.buscarAluno().get(0);
		}		
		if(ID != null) {
			Alunos a = alunosDao.findById(ID).get();
			a.setAtivo(aluno.getAtivo());
			a.setDataNascimento(data);
			a.setBairro(aluno.getBairro());
			a.setCep(aluno.getCep());
			a.setCidade(aluno.getCidade());
			a.setCpf(aluno.getCpf());
			a.setCpfResponsavel(aluno.getCpfResponsavel());
			a.setEmail(aluno.getEmail());
			a.setEndereco(aluno.getEndereco());
			a.setEstado(aluno.getEstado());
			a.setNome(aluno.getNome());
			a.setPerfil(p);
			a.setRa(aluno.getRa());
			a.setResponsavel(aluno.getResponsavel());
			a.setRg(aluno.getRg());
			a.setSenha(aluno.getSenha());
			a.setSuspensao(aluno.getSuspensao());
			a.setSerie(aluno.getSerie());
			a.setTelefone(aluno.getTelefone());
			a.setTurma(aluno.getTurma());
			alunosDao.saveAndFlush(a);
		} else {
			aluno.setPerfil(p);
			aluno.setDataNascimento(data);
			alunosDao.save(aluno);
		}
		List<Alunos> alunos = alunosDao.findAll();
			atualizarPagina = "/alunos";
			request.setAttribute("atualizarPagina", atualizarPagina);
			registraMsg("Criação", "Salvo com sucesso.", "info");
			request.setAttribute("usuarioSessao", usuarioSessao);
			request.setAttribute("alunos", alunos); 
			request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		 
		enviaMsg(request); 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	
	@RequestMapping(value = "/funcionarios/salvarFuncionario", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  salvarFuncionarios(HttpServletRequest request, HttpServletResponse response, Usuario usuario, Integer ID, String permissaoFunc) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		String link = "deslogar";
		
		if(usuarioSessao!= null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin() != null) {
			link = "pages/funcionarios";
			
			Perfil p = new Perfil();

			if (permissaoFunc.toLowerCase().contains("admin")) {
				p = perfilDao.buscarAdm().get(0);
			} else if (permissaoFunc.toLowerCase().contains("professor")) {
				p = perfilDao.buscarProfessor().get(0);
			} else if (permissaoFunc.toLowerCase().contains("funcionario")) {
				p = perfilDao.buscarFuncionario().get(0);
			}
			if(ID != null) {
				Usuario u = usuarioDao.findById(ID).get();
				u.setAtivo(usuario.getAtivo());
				u.setLogin(usuario.getLogin());
				u.setSenha(usuario.getSenha());
				u.setEmail(usuario.getEmail());
				u.setNome(usuario.getNome());
				u.setTelefone(usuario.getTelefone());
				u.setCargo(p.getNome());
				u.setPerfil(p);
				usuarioDao.saveAndFlush(u);
			} else {
				usuario.setPerfil(p);
				usuario.setCargo(p.getNome());
				usuarioDao.save(usuario);
			}
			List<Usuario> usuarios = usuarioDao.findAll();
			if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
				registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/funcionarios";
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("usuarioSessao", usuarioSessao);
				request.setAttribute("usuarios", usuarios); 
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		} 
		enviaMsg(request); 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarRecado", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  salvarRecados(HttpServletRequest request, HttpServletResponse response, Recado recado, String dataEnvio) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		String link = "deslogar";
		Usuario usuarioSessao = new Usuario();
		
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao!= null && usuarioSessao.getPerfil() != null) {
			link = "pages/recados"; 
			LocalDate data = LocalDate.parse(dataEnvio);
			
			Recado r = new Recado();
			r.setAssunto(recado.getAssunto());
			r.setConteudo(recado.getConteudo());
			r.setData(data);
			r.setPara(recado.getPara());
			r.setRemetente(recado.getRemetente());
			recadoDao.saveAndFlush(r);
			
			
			List<Recado> recados = recadoDao.findAll();
			if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
				registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/recados";
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("usuarioSessao", usuarioSessao);
				request.setAttribute("recados", recados); 
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
			 
			enviaMsg(request); 
		}
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarPeriodo", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public void  salvarPeriodo(HttpServletRequest request, HttpServletResponse response, Periodos periodo, String dataEnvio) throws ServletException, IOException { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		String link = "deslogar";
		Usuario usuarioSessao = new Usuario();
		
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao!= null && usuarioSessao.getPerfil() != null) {
			link = "pages/periodos"; 
			Periodos p = new Periodos();
			p.setCodigo(periodo.getCodigo());
			p.setFim(periodo.getFim());
			p.setInicio(periodo.getInicio());
			p.setNome(periodo.getNome());
			periodoDao.saveAndFlush(p);
			
			List<Periodos> periodos = periodoDao.findAll();
			if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
				registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/periodos";
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("usuarioSessao", usuarioSessao);
				request.setAttribute("periodos", periodos); 
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
			 
			enviaMsg(request);
		} 
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}
	
	
	@RequestMapping(value = "/adm/limparTudo", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public void  limparTudo(HttpServletRequest request, HttpServletResponse response, String tabelaUsadaDeletar) throws ServletException, IOException { //Função e alguns valores que recebe..
		String tabela = tabelaUsadaDeletar;
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		String link = "deslogar";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao!= null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
			link = "/pages/home";
			
			request.setAttribute("usuarioSessao", usuarioSessao);
			if(tabela.equals("alunos")) {
				atualizarPagina = "/home";
				alunosDao.deleteAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Registros", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("funcionarios")) {
				atualizarPagina = "/home";
				List<Horarios> listaDeHorarios = horarioDao.findAll();
				if(listaDeHorarios != null) {
					if(listaDeHorarios.size() > 0) {
						request.setAttribute("atualizarPagina", atualizarPagina);
						request.setAttribute("itemMenuSelecionado", "home");
						registraMsg("Registros", "Favor deletar todos os horários antes de deletar todos os funcionários.", "erro");
					} else {
						usuarioDao.deleteAll();
						request.setAttribute("atualizarPagina", atualizarPagina);
						request.setAttribute("itemMenuSelecionado", "home");
						registraMsg("Registros", "Deletado com sucesso.", "erro");						
					}
				} else {
					usuarioDao.deleteAll();
					request.setAttribute("atualizarPagina", atualizarPagina);
					request.setAttribute("itemMenuSelecionado", "home");
					registraMsg("Registros", "Deletado com sucesso.", "erro");	
				}
					
				
			}
			if(tabela.equals("horarios")) {
				atualizarPagina = "/home";
				horarioDao.deleteAll();
				request.setAttribute("atualizarPagina", atualizarPagina);
				request.setAttribute("itemMenuSelecionado", itemMenuSelecionado);
				registraMsg("Horários", "Deletado com sucesso.", "erro");
			}
			request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response);
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/"+link+".jsp").forward(request, response); 
	}

}
