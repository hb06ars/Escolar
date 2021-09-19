package brandaoti.sistema.escolar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import brandaoti.sistema.escolar.dao.HorarioDao;
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.PeriodoDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.model.Horarios;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Periodos;
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
	
	public ModelAndView enviaMsg(ModelAndView modelAndView) {
		modelAndView.addObject("mensagem", mensagem);
		modelAndView.addObject("tituloMensagem", tituloMensagem);
		modelAndView.addObject("tipoMensagem", tipoMensagem);
		mensagem = null;
		tituloMensagem = null;
		tipoMensagem = null;
		return modelAndView;
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
	
	@GetMapping({"/","/index"}) 
		public ModelAndView index(HttpServletRequest request, HttpServletResponse response, Model model) { 
		ModelAndView modelAndView = new ModelAndView("index"); 
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
		
		model.addAttribute("itemMenuSelecionado", "home");
		return modelAndView; 
	}
	
	@GetMapping(value = "/deslogar")
	public void deslogar(HttpServletRequest request, HttpServletResponse response) throws IOException {  
		String link = "/deslogar";
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/");
	}
	
	
	@RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView logar(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam(value = "usuarioVal", defaultValue = "", required=false ) String variavelUsuario, @RequestParam(value = "senhaVal", defaultValue = "", required=false ) String variavelSenha) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		HttpSession session = request.getSession();
		String itemMenuSelecionado = "";
		Usuario usu = usuarioDao.fazerLogin(variavelUsuario, variavelSenha);
		Usuario usuarioSessao = new Usuario();
		String link = "deslogar";
		if(usu != null && usu.getId() != null) {
			usuarioSessao = usu;
			session.setAttribute("usuarioSessao",usuarioSessao);
			link = "pages/home";
			itemMenuSelecionado = "pages/home";
		}
		if(session.getAttribute("usuarioSessao") != null) {
			usuarioSessao = (Usuario) session.getAttribute("usuarioSessao");
		}
		if((usu != null && usu.getId() != null) || (usuarioSessao != null && usuarioSessao.getId() != null)) {
			model.addAttribute("usuarioSessao", usuarioSessao);
		}
		
		model.addAttribute("itemMenuSelecionado", itemMenuSelecionado);
		ModelAndView modelAndView = new ModelAndView(link);
		return modelAndView; 
	}
	
	
	

}
