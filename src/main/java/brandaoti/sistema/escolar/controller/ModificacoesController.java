package brandaoti.sistema.escolar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class ModificacoesController {
	
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
							escolarController.periodoAtual = p.getNome();
							encontrado = true;
						}
					} else {
						escolarController.periodoAtual = p.getNome();
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
	public ModelAndView deletando(Model model,@PathVariable("tabela") String tabela, @PathVariable("id") Integer id) { //Função e alguns valores que recebe...
		String link = escolarController.verificaLink("/deslogar");
		if(escolarController.usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			link = "/pages/"+tabela;
			if(tabela.equals("alunos")) {
				escolarController.atualizarPagina = "/alunos";
				Alunos objeto = alunosDao.findById(id).get();
				alunosDao.delete(objeto);
				List<Alunos> alunos = alunosDao.findAll();
				model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
				model.addAttribute("alunos", alunos);
				escolarController.registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("funcionarios")) {
				escolarController.atualizarPagina = "/funcionarios";
				Usuario objeto = usuarioDao.findById(id).get();
				usuarioDao.delete(objeto);
				List<Usuario> usuarios = usuarioDao.findAll();
				model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
				model.addAttribute("usuarios", usuarios);
				escolarController.registraMsg("Usuário", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("recados")) {
				escolarController.atualizarPagina = "/recados";
				Recado objeto = recadoDao.findById(id).get();
				recadoDao.delete(objeto);
				List<Recado> recados = recadoDao.findAll();
				model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
				model.addAttribute("recados", recados);
				escolarController.registraMsg("Recado", "Deletado com sucesso.", "erro");
			}
			if(tabela.equals("periodos")) {
				escolarController.atualizarPagina = "/periodos";
				Periodos objeto = periodoDao.findById(id).get();
				periodoDao.delete(objeto);
				List<Periodos> periodos = periodoDao.findAll();
				model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
				model.addAttribute("periodos", periodos);
				escolarController.registraMsg("Período", "Deletado com sucesso.", "erro");
			}
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/adm/presencaConfirmada/{campo}/{id}", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView presencaConfirmada(Model model,@PathVariable("campo") String campo, @PathVariable("id") Integer id) { //Função e alguns valores que recebe...
		String link = escolarController.verificaLink("/deslogar");
		if(escolarController.usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			link = "/pages/presenca";
			Boolean compareceu = false;
			if(campo.equals("faltou")) {
				compareceu = false;
			} else {
				compareceu = true;
			}
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			escolarController.atualizarPagina = "/presenca";
			Usuario objeto = usuarioDao.findById(id).get();
			objeto.setCompareceu(compareceu);
			objeto.setUltimoComparecimento(escolarController.hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(escolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("horarios", horarios);
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/professor/confirmarPresenca", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView confirmarPresencaProfessor(Model model, Integer professorAtualConfirma) { //Função e alguns valores que recebe...
		String link = escolarController.verificaLink("/deslogar");
		if(escolarController.usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			escolarController.atualizarPagina = "/horarios";
			Usuario objeto = usuarioDao.findById(professorAtualConfirma).get();
			objeto.setCompareceu(true);
			objeto.setUltimoComparecimento(escolarController.hoje);
			usuarioDao.saveAndFlush(objeto);
			List<Horarios> horarios = horarioDao.buscarPeriodo(escolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("horarios", horarios);
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/professor/substituir", method = {RequestMethod.GET, RequestMethod.POST}) // Pagina de Alteração de Perfil
	public ModelAndView substituirProfessor(Model model, Integer professorAtual, Integer professorSubstituto, String todasAulas, Integer horarioId) { //Função e alguns valores que recebe...
		String link = escolarController.verificaLink("/deslogar");
		if(escolarController.usuarioSessao.getPerfil().getAdmin()) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			link = "/pages/horarios";
			String diaDaSemanaAtual = escolarController.diaDaSemana();
			escolarController.atualizarPagina = "/horarios";
			System.out.println("horarioId: "+horarioId);
			System.out.println("professorAtual: "+professorAtual);
			System.out.println("professorSubstituto: "+professorSubstituto);
			System.out.println("todasAulas: "+todasAulas);
			Horarios apenasUmaAula = horarioDao.findById(horarioId).get();
			List<Horarios> todas = horarioDao.todasAulasProfessor(professorAtual);
			Usuario s = usuarioDao.findById(professorSubstituto).get();
			s.setCompareceu(true);
			s.setUltimoComparecimento(escolarController.hoje);
			
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
			
			List<Horarios> horarios = horarioDao.buscarPeriodo(escolarController.periodoAtual, diaDaSemanaAtual);
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("horarios", horarios);
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	/* SALVAR EXCEL */
	@RequestMapping(value = "/adm/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
	public ModelAndView uploadExcel(Model model, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe...
		Perfil perfildaSessao = perfilDao.findById(escolarController.usuarioSessao.getPerfil().getId()).get();
		String link = escolarController.verificaLink("/pages/alunos"); // Alunos ---------------------
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
	    	link = escolarController.verificaLink("/pages/alunos");
	    	escolarController.atualizarPagina = "/alunos";
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
		    	link = escolarController.verificaLink("/pages/funcionarios");
		    	escolarController.atualizarPagina = "/funcionarios";
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
		    	link = escolarController.verificaLink("/pages/cadastroHorarios");
		    	escolarController.atualizarPagina = "/cadHorarios";
		    	break;
	    	
	    	
	    	
		}
		
		model.addAttribute("atualizarPagina", escolarController.atualizarPagina); 
		ModelAndView modelAndView = new ModelAndView(link);
		return modelAndView;
	}
	
	@RequestMapping(value = "/alunos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView alunos(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/alunos");
		List<Alunos> alunos = alunosDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("alunos", alunos); 
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/funcionarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView funcionarios(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/funcionarios");
		List<Usuario> funcionarios = usuarioDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("funcionarios", funcionarios); 
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/recados", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView recados(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/recados");
		List<Recado> recados = recadoDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("recados", recados); 
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/periodos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView periodos(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/periodos");
		List<Periodos> periodos = periodoDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("periodos", periodos); 
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/cadHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView cadHorarios(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/cadastroHorarios");
		List<Horarios> horarios = horarioDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("horarios", horarios); 
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/presenca", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView presenca(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/presenca");
		String diaDaSemanaAtual = escolarController.diaDaSemana();
		List<Usuario> usuarios = horarioDao.presenca(escolarController.periodoAtual, diaDaSemanaAtual);
		for(int i = 0 ; i < usuarios.size(); i++) {
			System.out.println("usuarios: " + usuarios.get(i).getNome());
		}
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("usuarios", usuarios); 
			model.addAttribute("periodoAtual", escolarController.periodoAtual);
			model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}
	
	@RequestMapping(value = "/horarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView horarios(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/horarios");
		
		
		String diaDaSemanaAtual = escolarController.diaDaSemana();
		List<Integer> quantidadeDeSalas = horarioDao.qtdSalas(escolarController.periodoAtual, diaDaSemanaAtual);
		List<String> quantidadeDeSeries = horarioDao.qtdSeries(escolarController.periodoAtual, diaDaSemanaAtual);
		List<String> quantidadeDeHorarios = horarioDao.qtdHorarios(escolarController.periodoAtual, diaDaSemanaAtual);
		List<Periodos> periodos = periodoDao.periodos();
		
		List<Horarios> horarios = horarioDao.buscarPeriodo(escolarController.periodoAtual, diaDaSemanaAtual);
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("professores", usuarioDao.professores());
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("horarios", horarios); 
			model.addAttribute("periodoAtual", escolarController.periodoAtual); 
			model.addAttribute("diaDaSemanaAtual", diaDaSemanaAtual); 
			model.addAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			model.addAttribute("quantidadeDeSeries", quantidadeDeSeries);
			model.addAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			model.addAttribute("periodos", periodos);
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}	
	
	
	@RequestMapping(value = "/filtrarHorarios", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView filtrarHorarios(Model model, String semana, Integer periodo) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/horarios");
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
		String semanaEscolhida = semana;
		String periodoEscolhido = periodoDao.findById(periodo).get().getNome();
		System.out.println("Filtro: "+semana + periodo);
		List<Integer> quantidadeDeSalas = horarioDao.qtdSalas(periodoEscolhido, semanaEscolhida);
		List<String> quantidadeDeSeries = horarioDao.qtdSeries(periodoEscolhido, semanaEscolhida);
		List<String> quantidadeDeHorarios = horarioDao.qtdHorarios(periodoEscolhido, semanaEscolhida);
		List<Horarios> horarios = horarioDao.buscarPeriodo(periodoEscolhido, semanaEscolhida);
		List<Periodos> periodos = periodoDao.periodos();
		
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("professores", usuarioDao.professores());
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("horarios", horarios); 
			model.addAttribute("periodoAtual", periodoEscolhido); 
			model.addAttribute("diaDaSemanaAtual", semanaEscolhida); 
			model.addAttribute("quantidadeDeSalas", quantidadeDeSalas); 
			model.addAttribute("quantidadeDeSeries", quantidadeDeSeries);
			model.addAttribute("quantidadeDeHorarios", quantidadeDeHorarios);
			model.addAttribute("periodos", periodos);
			
		}
		ModelAndView modelAndView = new ModelAndView(link);
		escolarController.enviaMsg(modelAndView);
		return modelAndView; 
	}	
	
	
	@RequestMapping(value = "/alunos/salvarAluno", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarAlunos(Model model, Alunos aluno, String nasc, Integer ID, String permissaoFunc) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/alunos"); 
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
		if(escolarController.usuarioSessao != null) {
			escolarController.atualizarPagina = "/alunos";
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("alunos", alunos); 
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/funcionarios/salvarFuncionario", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarFuncionarios(Model model, Usuario usuario, Integer ID, String permissaoFunc) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/funcionarios"); 
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
		if(escolarController.usuarioSessao != null) {
			escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
			escolarController.atualizarPagina = "/funcionarios";
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("usuarios", usuarios); 
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarRecado", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarRecados(Model model, Recado recado, String dataEnvio) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/funcionarios"); 
		Perfil p = new Perfil();
		LocalDate data = LocalDate.parse(dataEnvio);
		
		Recado r = new Recado();
		r.setAssunto(recado.getAssunto());
		r.setConteudo(recado.getConteudo());
		r.setData(data);
		r.setPara(recado.getPara());
		r.setRemetente(recado.getRemetente());
		recadoDao.saveAndFlush(r);
		
		
		List<Recado> recados = recadoDao.findAll();
		if(escolarController.usuarioSessao != null) {
			escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
			escolarController.atualizarPagina = "/recados";
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("recados", recados); 
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	
	
	
	@RequestMapping(value = "/recados/salvarPeriodo", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView salvarPeriodo(Model model, Periodos periodo, String dataEnvio) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/funcionarios"); 
		Periodos p = new Periodos();
		p.setCodigo(periodo.getCodigo());
		p.setFim(periodo.getFim());
		p.setInicio(periodo.getInicio());
		p.setNome(periodo.getNome());
		periodoDao.saveAndFlush(p);
		
		List<Periodos> periodos = periodoDao.findAll();
		if(escolarController.usuarioSessao != null) {
			escolarController.registraMsg("Criação", "Salvo com sucesso.", "info");
			escolarController.atualizarPagina = "/periodos";
			model.addAttribute("atualizarPagina", escolarController.atualizarPagina);
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("periodos", periodos); 
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		escolarController.enviaMsg(modelAndView); 
		return modelAndView; 
	}
	

}
