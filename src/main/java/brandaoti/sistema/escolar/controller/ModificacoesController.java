package brandaoti.sistema.escolar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
public class ModificacoesController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private AlunosDao alunosDao;
	@Autowired
	private PerfilDao perfilDao;
	@Autowired
	private RecadoDao recadoDao;
	@Autowired
	private HorarioDao horarioDao;
	@Autowired
	private PeriodoDao periodoDao;
	
	private EscolarController escolarController = new EscolarController();
	
	public void periodoAtual(String periodoAtual) {
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
							EscolarController.periodoAtual = p.getNome();
							encontrado = true;
						}
					} else {
						EscolarController.periodoAtual = p.getNome();
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
	}
	
	@RequestMapping(value = "/adm/deletando/{tabela}/{id}", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView deletando(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable("tabela") String tabela, @PathVariable("id") Integer id) { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/"+tabela;
			if(tabela.equals("alunos")) {
				atualizarPagina = "/alunos";
				Alunos objeto = alunosDao.findById(id).get();
				alunosDao.delete(objeto);
				List<Alunos> alunos = alunosDao.findAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("alunos", alunos);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("funcionarios")) {
				atualizarPagina = "/funcionarios";
				Usuario objeto = usuarioDao.findById(id).get();
				usuarioDao.delete(objeto);
				List<Usuario> usuarios = usuarioDao.findAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("usuarios", usuarios);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("recados")) {
				atualizarPagina = "/recados";
				Recado objeto = recadoDao.findById(id).get();
				recadoDao.delete(objeto);
				List<Recado> recados = recadoDao.findAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("recados", recados);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Recado", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("periodos")) {
				atualizarPagina = "/periodos";
				Periodos objeto = periodoDao.findById(id).get();
				periodoDao.delete(objeto);
				List<Periodos> periodos = periodoDao.findAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("periodos", periodos);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Período", "Deletado com sucesso.", "erro");
			}
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/adm/presencaConfirmada/{campo}/{id}", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView presencaConfirmada(HttpServletRequest request, HttpServletResponse response, Model model,@PathVariable("campo") String campo, @PathVariable("id") Integer id) { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/presenca";
			Boolean compareceu = false;
			if(campo.equals("faltou")) {
				compareceu = false;
			} else {
				compareceu = true;
			}
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			atualizarPagina = "/presenca";
			Usuario objeto = usuarioDao.findById(id).get();
			objeto.setCompareceu(compareceu);
			objeto.setUltimoComparecimento(EscolarController.hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(EscolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", atualizarPagina);
			model.addAttribute("horarios", horarios);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			escolarController.registraMsg("Confirmado", "Presença confirmada com sucesso.", "info");
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/professor/confirmarPresenca", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView confirmarPresencaProfessor(HttpServletRequest request, HttpServletResponse response, Model model, Integer professorAtualConfirma) { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			atualizarPagina = "/horarios";
			Usuario objeto = usuarioDao.findById(professorAtualConfirma).get();
			objeto.setCompareceu(true);
			objeto.setUltimoComparecimento(EscolarController.hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(EscolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", atualizarPagina);
			model.addAttribute("horarios", horarios);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			escolarController.registraMsg("Confirmado", "Presença confirmada com sucesso.", "info");
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/professor/substituir", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView substituirProfessor(HttpServletRequest request, HttpServletResponse response, Model model, Integer professorAtual, Integer professorSubstituto, String todasAulas, Integer horarioId) { //Função e alguns valores que recebe..
		String link = "/deslogar";
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			atualizarPagina = "/horarios";
			System.out.println("horarioId: "+horarioId);
			System.out.println("professorAtual: "+professorAtual);
			System.out.println("professorSubstituto: "+professorSubstituto);
			System.out.println("todasAulas: "+todasAulas);
			Horarios apenasUmaAula = horarioDao.findById(horarioId).get();
			List<Horarios> todas = horarioDao.todasAulasProfessor(professorAtual);
			Usuario s = usuarioDao.findById(professorSubstituto).get();
			s.setCompareceu(true);
			s.setUltimoComparecimento(EscolarController.hoje);
			
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
			
			List<Horarios> horarios = horarioDao.buscarPeriodo(EscolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", atualizarPagina);
			model.addAttribute("horarios", horarios);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			escolarController.registraMsg("Confirmado", "Substituição confirmada com sucesso.", "info");
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	/* SALVAR EXCEL */
	@RequestMapping(value = "/adm/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
	public ModelAndView uploadExcel(HttpServletRequest request, HttpServletResponse response, Model model, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe..
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
			escolarController.registraMsg("Upload", "Upload realizado com sucesso.", "info");
			model.addAttribute("atualizarPagina", atualizarPagina); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		return modelAndView;
	}
	
	@RequestMapping(value = "/alunos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView alunos(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("alunos", alunos); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/funcionarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView funcionarios(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("funcionarios", funcionarios); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/recados", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView recados(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("recados", recados); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/periodos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView periodos(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("periodos", periodos); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/cadHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView cadHorarios(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("horarios", horarios); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/presenca", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView presenca(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		
		if(usuarioSessao.getId() != null && usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			itemMenuSelecionado = "pages/presenca";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			List<Usuario> usuarios = horarioDao.presenca(EscolarController.periodoAtual, diaDaSemanaAtual);
			for(int i = 0 ; i < usuarios.size(); i++) {
				System.out.println("usuarios: " + usuarios.get(i).getNome());
			}
			link = "pages/presenca";
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("usuarios", usuarios); 
			model.addAttribute("periodoAtual", EscolarController.periodoAtual);
			model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/horarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView horarios(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "pages/delsogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			itemMenuSelecionado = "pages/horarios";
			link = "pages/horarios";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			List<Integer> quantidadeDeSalas = horarioDao.qtdSalas(EscolarController.periodoAtual, diaDaSemanaAtual);
			List<String> quantidadeDeSeries = horarioDao.qtdSeries(EscolarController.periodoAtual, diaDaSemanaAtual);
			List<String> quantidadeDeHorarios = horarioDao.qtdHorarios(EscolarController.periodoAtual, diaDaSemanaAtual);
			List<Periodos> periodos = periodoDao.periodos();
			List<Horarios> horarios = horarioDao.buscarPeriodo(EscolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("professores", usuarioDao.professores());
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("horarios", horarios); 
			model.addAttribute("periodoAtual", EscolarController.periodoAtual); 
			model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
			model.addAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			model.addAttribute("quantidadeDeSeries", quantidadeDeSeries);
			model.addAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			model.addAttribute("periodos", periodos);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/meusHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView meusHorarios(HttpServletRequest request, HttpServletResponse response, Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "deslogar";
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if(usuarioSessao != null && usuarioSessao.getPerfil() != null) {
			if(usuarioSessao.getPerfil().getProfessor()) {
				String diaDaSemanaAtual = escolarController.diaDaSemana();
				List<Periodos> periodos = periodoDao.periodos();
				List<Horarios> horarios = horarioDao.filtroTodasAulasProfessor(usuarioSessao.getId(), EscolarController.periodoAtual, diaDaSemanaAtual);
				link = "pages/meusHorarios";
				model.addAttribute("usuarioSessao", usuarioSessao);
				model.addAttribute("horarios", horarios); 
				model.addAttribute("periodoAtual", EscolarController.periodoAtual); 
				model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
				model.addAttribute("periodos", periodos);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/filtrarMeusHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView filtrarMeusHorarios(HttpServletRequest request, HttpServletResponse response, Model model, String semana, Integer periodo) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
				model.addAttribute("usuarioSessao", usuarioSessao);
				model.addAttribute("horarios", horarios); 
				model.addAttribute("periodoAtual", periodoEscolhido); 
				model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
				model.addAttribute("periodos", periodos);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/filtrarHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView filtrarHorarios(HttpServletRequest request, HttpServletResponse response, Model model, String semana, Integer periodo) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			
			model.addAttribute("professores", usuarioDao.professores());
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("horarios", horarios); 
			model.addAttribute("periodoAtual", periodoEscolhido); 
			model.addAttribute("diaDaSemanaAtual", semanaEscolhida); 
			model.addAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			model.addAttribute("quantidadeDeSeries", quantidadeDeSeries);
			model.addAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			model.addAttribute("periodos", periodos);
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}	
	
	
	@RequestMapping(value = "/alunos/salvarAluno", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarAlunos(HttpServletRequest request, HttpServletResponse response, Model model, Alunos aluno, String nasc, Integer ID, String permissaoFunc) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
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
			model.addAttribute("atualizarPagina", atualizarPagina);
			escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
			model.addAttribute("usuarioSessao", usuarioSessao);
			model.addAttribute("alunos", alunos); 
			model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/funcionarios/salvarFuncionario", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarFuncionarios(HttpServletRequest request, HttpServletResponse response, Model model, Usuario usuario, Integer ID, String permissaoFunc) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		Usuario usuarioSessao = new Usuario();
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		String link = "deslogar";
		ModelAndView modelAndView = new ModelAndView(link);
		if(usuarioSessao!= null && usuarioSessao.getPerfil() != null && usuarioSessao.getPerfil().getAdmin() != null) {
			link = "pages/funcionarios";
			modelAndView = new ModelAndView(link);
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
				escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/funcionarios";
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("usuarioSessao", usuarioSessao);
				model.addAttribute("usuarios", usuarios); 
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
		} 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarRecado", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarRecados(HttpServletRequest request, HttpServletResponse response, Model model, Recado recado, String dataEnvio) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		String link = "deslogar";
		Usuario usuarioSessao = new Usuario();
		ModelAndView modelAndView = new ModelAndView(link);
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
				escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/recados";
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("usuarioSessao", usuarioSessao);
				model.addAttribute("recados", recados); 
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
			modelAndView = new ModelAndView(link); 
			escolarController.enviaMsg(modelAndView); 
		}
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarPeriodo", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarPeriodo(HttpServletRequest request, HttpServletResponse response, Model model, Periodos periodo, String dataEnvio) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String atualizarPagina = "";
		String itemMenuSelecionado = "";
		String link = "deslogar";
		Usuario usuarioSessao = new Usuario();
		ModelAndView modelAndView = new ModelAndView(link);
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
				escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
				atualizarPagina = "/periodos";
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("usuarioSessao", usuarioSessao);
				model.addAttribute("periodos", periodos); 
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
			}
			modelAndView = new ModelAndView(link); 
			escolarController.enviaMsg(modelAndView);
		} 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/adm/limparTudo", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView limparTudo(HttpServletRequest request, HttpServletResponse response, Model model, String tabelaUsadaDeletar) { //Função e alguns valores que recebe..
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
			ModelAndView modelAndView = new ModelAndView(link);
			model.addAttribute("usuarioSessao", usuarioSessao);
			if(tabela.equals("alunos")) {
				atualizarPagina = "/home";
				alunosDao.deleteAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Registros", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("funcionarios")) {
				atualizarPagina = "/home";
				List<Horarios> listaDeHorarios = horarioDao.findAll();
				if(listaDeHorarios != null) {
					if(listaDeHorarios.size() > 0) {
						model.addAttribute("atualizarPagina", atualizarPagina);
						model.addAttribute("itemMenuSelecionado", "home");
						escolarController.registraMsg("Registros", "Favor deletar todos os horários antes de deletar todos os funcionários.", "erro");
					} else {
						usuarioDao.deleteAll();
						model.addAttribute("atualizarPagina", atualizarPagina);
						model.addAttribute("itemMenuSelecionado", "home");
						escolarController.registraMsg("Registros", "Deletado com sucesso.", "erro");						
					}
				} else {
					usuarioDao.deleteAll();
					model.addAttribute("atualizarPagina", atualizarPagina);
					model.addAttribute("itemMenuSelecionado", "home");
					escolarController.registraMsg("Registros", "Deletado com sucesso.", "erro");	
				}
					
				
			}
			if(tabela.equals("horarios")) {
				atualizarPagina = "/home";
				horarioDao.deleteAll();
				model.addAttribute("atualizarPagina", atualizarPagina);
				model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
				escolarController.registraMsg("Horários", "Deletado com sucesso.", "erro");
			}
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView(link);
		return modelAndView; 
	}

}
