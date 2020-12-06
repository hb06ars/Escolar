package brandaoti.sistema.escolar.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import brandaoti.sistema.escolar.dao.AlunosDao;
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.model.Alunos;

@Controller
public class ModificacoesController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private AlunosDao alunosDao;
	@Autowired
	private PerfilDao perfilDao;
	
	private EscolarController escolarController = new EscolarController();
	
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
			}
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
	@RequestMapping(value = "/alunos", method = {RequestMethod.POST,RequestMethod.GET}) // Link do submit do form e o method POST que botou la
	public ModelAndView alunos(Model model, @RequestParam(value = "usuarioVal", defaultValue = "", required=false ) String variavelUsuario, @RequestParam(value = "senhaVal", defaultValue = "", required=false ) String variavelSenha) { // model é usado para mandar , e variavelNome está recebendo o name="nome" do submit feito na pagina principal 
		String link = escolarController.verificaLink("pages/alunos");
		List<Alunos> alunos = alunosDao.findAll();
		if(escolarController.usuarioSessao != null) {
			model.addAttribute("usuarioSessao", escolarController.usuarioSessao);
			model.addAttribute("alunos", alunos); 
		}
		ModelAndView modelAndView = new ModelAndView(link); 
		return modelAndView; 
	}
	
		

}
