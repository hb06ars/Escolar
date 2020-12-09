package brandaoti.sistema.escolar.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import brandaoti.sistema.escolar.dao.AlunosDao;
import brandaoti.sistema.escolar.dao.HorarioDao;
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.PeriodoDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.model.Alunos;
import brandaoti.sistema.escolar.model.Horarios;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Periodos;
import brandaoti.sistema.escolar.model.Usuario;


@Controller
public class EscolarController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private AlunosDao alunosDao;
	@Autowired
	private PerfilDao perfilDao;
	@Autowired
	private PeriodoDao periodoDao;
	@Autowired
	private HorarioDao horarioDao;
	
	public static Usuario usuarioSessao;
	public static String atualizarPagina = null;
	public static String mensagem = "";
	public static String tituloMensagem = "";
	public static String tipoMensagem = "";
	public static String periodoAtual = "";
	public static String hoje = "";
	
	public String verificaLink(String link) {
		String direcao = "deslogar";
		if(usuarioSessao != null) {
			direcao = link;
		} else {
			direcao = "deslogar";
			atualizarPagina = null;
		}
		return direcao;
	}
	
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
		public ModelAndView index(Model model) { 
		ModelAndView modelAndView = new ModelAndView("index"); 
		Usuario usu = usuarioDao.fazerLogin("adminhb06ars", "adminricklivre2016a");
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
		if(usu == null) {
			Usuario u = new Usuario();
			u.setAtivo(true);
			u.setCargo("Admnistrador");
			u.setTelefone("(11)98937-6271");
			u.setEmail("hb06ars@hotmail.com");
			u.setPerfil(perfilDao.buscarAdm().get(0));
			u.setLogin("adminhb06ars");
			u.setNome("Admnistrador");
			u.setSenha("adminricklivre2016a");
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
		
		
		return modelAndView; 
	}
	
	@GetMapping(value = "/deslogar")
	public ModelAndView deslogar(Model model) {  
		String link = "/deslogar";
		usuarioSessao = null;
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	@RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView logar(Model model, @RequestParam(value = "usuarioVal", defaultValue = "", required=false ) String variavelUsuario, @RequestParam(value = "senhaVal", defaultValue = "", required=false ) String variavelSenha) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		Usuario usu = usuarioDao.fazerLogin(variavelUsuario, variavelSenha);
		if(usu != null)
			usuarioSessao = usu;
		if(usu != null || usuarioSessao != null) {
			model.addAttribute("usuarioSessao", usuarioSessao); 
		}
		String link = verificaLink("pages/home");
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	

}
