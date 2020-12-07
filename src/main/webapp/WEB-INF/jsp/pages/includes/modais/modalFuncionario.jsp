<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<jsp:include page="../mascaras/mascaras.jsp" />
<jsp:include page="../jquery/excel/downloadExcel.jsp" />

<!--  EDITAR FUNCIONARIO -->
<script>

function modalEditarFuncionario(id, nome, cargo, perfil, login, senha, telefone,  ativo, email){
	document.getElementById("form").action = "/funcionarios/salvarFuncionario";
	var funcFuncionario = '';
	var index = 0;
	if(perfil == 'Admnistrador'){
		index = 0;
		funcFuncionario = 'admin';
	}else if(perfil == 'funcionario'){
		index = 1;
		funcFuncionario = 'Funcionário';
	}else if(perfil == 'professor'){
		index = 2;
		funcFuncionario = 'professor';
	}


	var ativoAluno = '';
	var indexAtivo = 0;
	if(ativo){
		indexAtivo = 1;
		ativoAluno = '1';
	}else{
		indexAtivo = 0;
		ativoAluno = '0';
	}

	document.getElementById("ID").value = id;
	document.getElementById("nome").value = nome;
	document.getElementById("permissaoFunc").selectedIndex = index;
	document.getElementById("telefone").value = telefone;
	document.getElementById("telefone").required = false;
	document.getElementById("ativo").value = indexAtivo;
	document.getElementById("ativo").required = true;
	document.getElementById("email").value = email;
	document.getElementById("email").required = false;
	document.getElementById("login").required = true;
	document.getElementById("senha").required = true;
	document.getElementById("login").value = login;
	document.getElementById("senha").value = senha;
	$("#modalEditarFuncionario").modal().show();
}

function modalNovoFuncionario(){
	document.getElementById("form").action = "/funcionarios/salvarFuncionario";
	document.getElementById("nome").value = '';
	document.getElementById("permissaoFunc").selectedIndex = 0;
	document.getElementById("telefone").value = '';
	document.getElementById("telefone").required = false;
	document.getElementById("ativo").value = 1;
	document.getElementById("ativo").required = true;
	document.getElementById("email").value = '';
	document.getElementById("email").required = false;
	document.getElementById("login").value ='';
	document.getElementById("senha").value='';
	document.getElementById("login").required = true;
	document.getElementById("senha").required = true;
	
	$("#modalEditarFuncionario").modal().show();
}





</script>


<form action="<c:url value='/funcionarios/salvarFuncionario'/>" id="form" method="post" >
<div class="modal fade" id="modalEditarFuncionario" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
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
						<option value="0" selected="selected">Não</option>
						<option value="1" >Sim</option>
				</select>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Perfil / Cargo:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="permissaoFunc" name="permissaoFunc" required aria-describedby="inputGroup-sizing-default">
						<option value="admin" >Administrador</option>
						<option value="funcionario" >Funcionário</option>
						<option value="professor" >Professor</option>
				</select>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Ativo:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="ativo" name="ativo" required aria-describedby="inputGroup-sizing-default">
						<option value="0" selected="selected">Não</option>
						<option value="1" >Sim</option>
				</select>
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
  			Email:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="email" id="email" class="form-control" autocomplete="off"  >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Login:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="login" name="login" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Senha:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="senha" name="senha" type="password" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	<input type="hidden" name="funcFuncionario" id="funcFuncionario" value="">
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


