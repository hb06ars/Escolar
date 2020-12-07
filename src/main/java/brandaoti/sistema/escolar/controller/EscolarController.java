package brandaoti.sistema.escolar.controller;

import java.time.LocalDate;
import java.util.Date;
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
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.model.Alunos;
import brandaoti.sistema.escolar.model.Perfil;
import brandaoti.sistema.escolar.model.Usuario;


@Controller
public class EscolarController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private AlunosDao alunosDao;
	@Autowired
	private PerfilDao perfilDao;
	
	public static Usuario usuarioSessao;
	public static String atualizarPagina = null;
	public static String mensagem = "";
	public static String tituloMensagem = "";
	public static String tipoMensagem = "";
	
	
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
	
	@GetMapping({"/","/index"}) 
		public ModelAndView index(Model model) { 
		ModelAndView modelAndView = new ModelAndView("index"); 
		Usuario usu = usuarioDao.fazerLogin("adm", "adm");
		List<Perfil> perfis = perfilDao.findAll();
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
			u.setEmail("admin@admin.com");
			u.setPerfil(perfilDao.buscarAdm().get(0));
			u.setLogin("adm");
			u.setNome("Admin");
			u.setSenha("adm");
			usuarioDao.save(u);
			
			
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
