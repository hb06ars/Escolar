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
			
			//Teste
			Alunos a = new Alunos();
			a.setNome("Henrique");
			a.setPerfil(perfilDao.buscarCodigo("4"));
			a.setEndereco("Rua da Paz");
			a.setBairro("Jd das Rosas");
			a.setCep("33333-444");
			a.setCidade("São Paulo");
			a.setEstado("SP");
			a.setTelefone("22222-3333");
			a.setLogin("hb06ars");
			a.setSenha("123");
			a.setAtivo(true);
			a.setEmail("hb@gmail.com");
			a.setRa("333");
			a.setRg("123456789");
			a.setCpf("12345678901");
			a.setDataNascimento(LocalDate.of(1988, 5, 2));
			a.setSerie("1");
			a.setTurma("C");
			a.setResponsavel("Donizeti");
			a.setCpfResponsavel("2211142433");
			a.setSuspensao(false);
			alunosDao.save(a);
			
			a = new Alunos();
			a.setNome("Margarida");
			a.setPerfil(perfilDao.buscarCodigo("4"));
			a.setEndereco("Rua da Alegria");
			a.setBairro("Jd Esperança");
			a.setCep("99999-234");
			a.setCidade("São Paulo");
			a.setEstado("SP");
			a.setTelefone("89999-3333");
			a.setLogin("mah");
			a.setSenha("123");
			a.setAtivo(true);
			a.setEmail("mah@mah.com");
			a.setRa("222");
			a.setRg("334445556");
			a.setCpf("22233344455");
			a.setDataNascimento(LocalDate.of(1986, 8, 26));
			a.setSerie("3");
			a.setTurma("A");
			a.setResponsavel("Almir");
			a.setCpfResponsavel("22233344455");
			a.setSuspensao(false);
			alunosDao.save(a);
			
			
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
