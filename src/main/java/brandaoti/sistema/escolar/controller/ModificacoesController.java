package brandaoti.sistema.escolar.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.UploadContext;
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
import brandaoti.sistema.escolar.dao.PerfilDao;
import brandaoti.sistema.escolar.dao.UsuarioDao;
import brandaoti.sistema.escolar.excel.ProcessaExcel;
import brandaoti.sistema.escolar.excel.Tabela;
import brandaoti.sistema.escolar.model.Alunos;
import brandaoti.sistema.escolar.model.Perfil;

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
	
	/* SALVAR EXCEL */
	@RequestMapping(value = "/adm/upload/excel", method = {RequestMethod.POST, RequestMethod.GET}) // Pagina de Alteração de Perfil
	public ModelAndView uploadExcel(Model model, String tabelaUsada, @ModelAttribute MultipartFile file) throws Exception, IOException { //Função e alguns valores que recebe...
		Perfil perfildaSessao = perfilDao.findById(escolarController.usuarioSessao.getPerfil().getId()).get();
		String link = escolarController.verificaLink("/pages/alunos"); //Session
		ProcessaExcel processaExcel = new ProcessaExcel();
		List<Tabela> tabelas = processaExcel.uploadAlunos(file);
    	String conteudo="";
   		Integer finalLinha = 0;
   		Alunos a = new Alunos();
   		int coluna = 0;
   		
		switch (tabelaUsada) {  
	       case "alunos" :
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
	   					if(coluna == 20) a.setPerfil(perfilDao.buscarCodigo(""+conteudo));
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
		}
		
		model.addAttribute("atualizarPagina", escolarController.atualizarPagina); 
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
