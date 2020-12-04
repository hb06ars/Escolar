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

import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.model.Usuario;


@Controller
public class EscolarController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public static Boolean logado = false;
	
	@GetMapping({"/","/index"}) //Link acessado pela URL...
		public ModelAndView index(Model model) { // model é usado para mandar 
		ModelAndView modelAndView = new ModelAndView("index"); //modelAndView é usado para direcionar para determinado JSP
		Usuario usu = usuarioDao.fazerLogin("adm", "adm");
		if(usu == null) {
			Usuario u = new Usuario();
			u.setAtivo(true);
			u.setCargo("Admnistrador");
			u.setEmail("admin@admin");
			u.setLogin("adm");
			u.setNome("Admin");
			u.setSenha("adm");
			usuarioDao.save(u);
		}
		
		
		return modelAndView; //retorna para a pagina index.jsp
	}
	
	@GetMapping(value = "/deslogar") // Link do submit do form e o method POST que botou la
	public ModelAndView deslogar(Model model) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "/index";
		logado = false;
		ModelAndView modelAndView = new ModelAndView(link); //modelAndView é usado para direcionar para determinado JSP
		return modelAndView; //retorna para a pagina outra.jsp
	}
	
	@RequestMapping(value = "/home", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView logar(Model model, @RequestParam(value = "usuarioVal", defaultValue = "", required=false ) String variavelUsuario, @RequestParam(value = "senhaVal", defaultValue = "", required=false ) String variavelSenha) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = "/index";
		Usuario usu = usuarioDao.fazerLogin(variavelUsuario, variavelSenha);
		if(usu != null || logado) {
			logado = true;
			model.addAttribute("logado", logado);
			model.addAttribute("cli", usu); //Objeto que tô mandando pro JSP
		} else {
			logado = false;
		}
		if(logado) {
			link = "pages/home";
		}
		ModelAndView modelAndView = new ModelAndView(link); //modelAndView é usado para direcionar para determinado JSP
		return modelAndView; //retorna para a pagina outra.jsp
	}
	

}
