package brandaoti.sistema.escolar.controller;

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
	
	@GetMapping({"/","/index"}) 
		public ModelAndView index(Model model) { 
		ModelAndView modelAndView = new ModelAndView("index"); 
		Usuario usu = usuarioDao.fazerLogin("adm", "adm");
		List<Perfil> perfis = perfilDao.findAll();
		System.out.println("perfis: " + perfis.size());
		if(perfis.size() == 0) {
			Perfil p = new Perfil();
			p.setAtivo(true);
			p.setNome("Admnistrador");
			p.setCodigo("1");
			perfilDao.save(p);
		}
		if(usu == null) {
			Usuario u = new Usuario();
			u.setAtivo(true);
			u.setCargo("Admnistrador");
			u.setEmail("admin@admin.com");
			u.setPerfil(perfilDao.buscarCodigo("1"));
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
		String link = "deslogar";
		Usuario usu = usuarioDao.fazerLogin(variavelUsuario, variavelSenha);
		if(usu != null)
			usuarioSessao = usu;
		if(usu != null || usuarioSessao != null) {
			model.addAttribute("cli", usuarioSessao); 
		}
		if(usuarioSessao != null) {
			link = "pages/home";
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	
	@RequestMapping(value = "/alunos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView alunos(Model model, @RequestParam(value = "usuarioVal", defaultValue = "", required=false ) String variavelUsuario, @RequestParam(value = "senhaVal", defaultValue = "", required=false ) String variavelSenha) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "/";
		List<Alunos> alunos = alunosDao.findAll();
		if(usuarioSessao != null) {
			model.addAttribute("logado", usuarioSessao);
			model.addAttribute("alunos", alunos); 
		} 
		if(usuarioSessao != null) {
			link = "pages/alunos";
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	

}
