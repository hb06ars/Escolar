<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  EDITAR FUNCIONARIO -->
<script>

function modalEditarAluno(id, nome, perfil, endereco, bairro, cep, cidade, estado, telefone, login, senha, ativo, email, ra, rg, cpf, dataNascimento, serie, turma, responsavel, cpfResponsavel, suspensao){
	document.getElementById("form").action = "/aluno/atualizarUsuario";
	var funcAluno = '';
	var index = 0;
	if(perfil == 'Admnistrador'){
		index = 0;
		funcAluno = 'admin';
	}else if(perfil == 'Aluno'){
		index = 1;
		funcAluno = 'aluno';
	}else if(perfil == 'funcionario'){
		index = 2;
		funcAluno = 'Funcion�rio';
	}else if(perfil == 'professor'){
		index = 3;
		funcAluno = 'professor';
	}

	var suspAluno = '';
	var indexSusp = 0;
	if(suspensao == 'true'){
		indexSusp = 0;
		suspAluno = '1';
	}else{
		indexSusp = 1;
		suspAluno = '0';
	}

	var ativoAluno = '';
	var indexAtivo = 0;
	if(suspensao == 'true'){
		indexAtivo = 0;
		ativoAluno = '1';
	}else{
		indexAtivo = 1;
		ativoAluno = '0';
	}

	document.getElementById("ID").value = id;
	document.getElementById("nome").value = nome;
	document.getElementById("permissaoFunc").selectedIndex = index;
	document.getElementById("endereco").value = endereco;
	document.getElementById("endereco").required = false;
	document.getElementById("bairro").value = bairro;
	document.getElementById("bairro").required = false;
	document.getElementById("cep").value = cep;
	document.getElementById("cep").required = false;
	document.getElementById("cidade").value = cidade;
	document.getElementById("cidade").required = false;
	document.getElementById("estado").value = estado;
	document.getElementById("estado").required = false;
	document.getElementById("telefone").value = telefone;
	document.getElementById("telefone").required = false;
	document.getElementById("login").value = login;
	document.getElementById("login").required = true;
	document.getElementById("senha").value = senha;
	document.getElementById("senha").required = true;
	document.getElementById("ativo").value = indexAtivo;
	document.getElementById("ativo").required = true;
	document.getElementById("email").value = email;
	document.getElementById("email").required = true;
	document.getElementById("ra").value = ra;
	document.getElementById("ra").required = true;
	document.getElementById("rg").value = rg;
	document.getElementById("rg").required = true;
	document.getElementById("cpf").value = cpf;
	document.getElementById("cpf").required = true;
	document.getElementById("dataNascimento").value = dataNascimento;
	document.getElementById("dataNascimento").required = true;
	document.getElementById("serie").value = serie;
	document.getElementById("serie").required = true;
	document.getElementById("turma").value = turma;
	document.getElementById("turma").required = true;
	document.getElementById("responsavel").value = responsavel;
	document.getElementById("responsavel").required = true;
	document.getElementById("cpfResponsavel").value = cpfResponsavel;
	document.getElementById("cpfResponsavel").required = true;
	document.getElementById("suspensao").value = indexSusp;
	document.getElementById("suspensao").required = true;

	
	$("#modalEditarAluno").modal().show();
}

function modalNovoAluno(){
	document.getElementById("form").action = "/aluno/atualizarAluno";
	document.getElementById("nome").value = '';
	document.getElementById("nome").required = true;
	document.getElementById("alterarSalvar").value = 'novo';
	document.getElementById("permissaoFunc").required = true;
	document.getElementById("funcAluno").value = funcAluno;
	document.getElementById("telefone").value = '';
	document.getElementById("telefone").required = false;
	document.getElementById("endereco").value = '';
	document.getElementById("endereco").required = false;
	document.getElementById("bairro").value = '';
	document.getElementById("bairro").required = false;
	document.getElementById("cep").value = '';
	document.getElementById("cep").required = false;
	document.getElementById("observacoes").value = '';
	document.getElementById("observacoes").required = false;
	document.getElementById("referencia").value = '';
	document.getElementById("referencia").required = false;
	document.getElementById("cidade").value = 'S�o Paulo';
	document.getElementById("cidade").required = false;
	document.getElementById("estado").value = 'SP';
	document.getElementById("estado").required = false;
	
	$("#modalEditarAluno").modal().show();
}


function modalPesquisarAluno(){
	document.getElementById("form").action = "/aluno/pesquisarAluno";
	document.getElementById("nome").value = '';
	document.getElementById("nome").required = false;
	document.getElementById("permissaoFunc").required = false;
	document.getElementById("alterarSalvar").value = 'novo';
	document.getElementById("funcAluno").value = funcAluno;
	document.getElementById("telefone").value = '';
	document.getElementById("telefone").required = false;
	document.getElementById("endereco").value = '';
	document.getElementById("endereco").required = false;
	document.getElementById("bairro").value = '';
	document.getElementById("bairro").required = false;
	document.getElementById("cep").value = '';
	document.getElementById("cep").required = false;
	document.getElementById("observacoes").value = '';
	document.getElementById("observacoes").required = false;
	document.getElementById("referencia").value = '';
	document.getElementById("referencia").required = false;
	document.getElementById("cidade").value = 'S�o Paulo';
	document.getElementById("cidade").required = false;
	document.getElementById("estado").value = 'SP';
	document.getElementById("estado").required = false;
	
	
	$("#modalEditarAluno").modal().show();
}



</script>


<form action="<c:url value='/adm/atualizarAluno'/>" id="form" method="post" >
<div class="modal fade" id="modalEditarAluno" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Registro</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div>
  			Nome:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="nome" name="nome" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Ativo:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="ativo" name="ativo" required aria-describedby="inputGroup-sizing-default">
						<option value="1" >Sim</option>
						<option value="0" selected="selected">N�o</option>
				</select>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Perfil:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="permissaoFunc" name="permissaoFunc" required aria-describedby="inputGroup-sizing-default">
						<option value="admin" >Administrador</option>
						<option value="aluno" selected="selected">Aluno</option>
						<option value="funcionario" >Funcion�rio</option>
						<option value="professor" >Professor</option>				  	
				</select>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Endere�o:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="endereco" name="endereco" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Bairro:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="bairro" name="bairro" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			CEP:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="cep" id="cep" class="form-control" onkeypress="mascaraCep(this)" onkeyup="somenteNumeros(this);" placeholder="00000-000" autocomplete="off" maxlength="9" minlength="9" >
  			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Cidade:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="cidade" name="cidade" value="S�o Paulo" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Estado:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="estado" name="estado" value="SP" type="text" required aria-describedby="inputGroup-sizing-default" maxlength="2" minlength="2">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Telefone:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="telefone" id="telefone" class="form-control" onkeypress="mascaraTel(this)" onkeyup="somenteNumeros(this);" placeholder="(00)00000-0000" autocomplete="off" maxlength="14" minlength="14" >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Login:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="login" id="login" class="form-control" autocomplete="off" readonly="readonly">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Senha:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="senha" id="senha" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Email:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="email" id="email" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			RA:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="ra" id="ra" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			RG:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="rg" id="rg" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			CPF:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="cpf" id="cpf" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Data de Nascimento:
  			</div>
  			<div class="input-group mb-3">
  				<input type="date" name="dataNascimento" id="dataNascimento" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			S�rie:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="serie" id="serie" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Turma:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="turma" id="turma" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Nome do Respons�vel:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="responsavel" id="responsavel" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			CPF do Respons�vel:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="cpfResponsavel" id="cpfResponsavel" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Suspens�o?:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="suspensao" name="suspensao" required aria-describedby="inputGroup-sizing-default">
						<option value="1" >Sim</option>
						<option value="0" selected="selected">N�o</option>
				</select>
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	<input type="hidden" name="funcAluno" id="funcAluno" value="">
		<input type="hidden" name="alterarSalvar" id="alterarSalvar">
      	<input type="hidden" name="ID" id="ID">
      	<input type="submit" name="submit" class="btn btn-primary" value="Ok">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR FUNCIONARIO -->

