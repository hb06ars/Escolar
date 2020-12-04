<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function modalEditarPerfil(id, descricaoPerfil, codigo, tipo){
	document.getElementById("form").action = "/Sistema/adm/atualizarPerfil";
	var index = 1;
	if(tipo == 'admin'){
		index = 1;
	}else if(tipo == 'cliente'){
		index = 2;
	}else if(tipo == 'funcionario'){
		index = 3;
	}else if(tipo == 'visitante'){
		index = 4;
	}
	document.getElementById("permissao").selectedIndex = index;
	document.getElementById("perfilID").value = id;
	document.getElementById("descricao").value = descricaoPerfil;
	document.getElementById("codigo").value = codigo;
	document.getElementById("codigo").readOnly = true;
	document.getElementById("descricao").required = true;
	document.getElementById("permissao").required = true;
	$("#modalEditarPerfil").modal().show();
}


function modalNovoPerfil(){
	document.getElementById("form").action = "/Sistema/adm/atualizarPerfil";
	document.getElementById("permissao").selectedIndex = 1;
	document.getElementById("perfilID").value = '';
	document.getElementById("descricao").value = '';
	document.getElementById("codigo").value = '';
	document.getElementById("codigo").required = true;
	document.getElementById("codigo").readOnly = false;
	document.getElementById("permissao").required = true;
	document.getElementById("descricao").required = true;
	document.getElementById("descricao").readOnly = false;
	$("#modalEditarPerfil").modal().show();
}


function modalPesquisarPerfil(){
	document.getElementById("form").action = "/Sistema/adm/pesquisarPerfil";
	document.getElementById("permissao").selectedIndex = 1;
	document.getElementById("perfilID").value = '';
	document.getElementById("descricao").value = '';
	document.getElementById("codigo").value = '';
	document.getElementById("codigo").required = false;
	document.getElementById("codigo").readOnly = false;
	document.getElementById("descricao").required = false;
	document.getElementById("descricao").readOnly = false;
	document.getElementById("permissao").required = false;
	$("#modalEditarPerfil").modal().show();
}

</script>


<form action="<c:url value='/adm/atualizarPerfil'/>" id="form" method="post" >
<div class="modal fade" id="modalEditarPerfil" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Perfil</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div>
  			Perfil:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="descricao" name="descricao" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Permissão:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="permissao" name="permissao" required aria-describedby="inputGroup-sizing-default">
						<option value="" >-- Selecione --</option>
						<option value="admin" >Admin</option>
						<option value="cliente" >Cliente</option>
						<option value="funcionario" >Funcionário</option>
						<option value="visitante" >Visitante</option>				  	
				</select>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Código:
  			</div>
  			<div class="input-group mb-3">
  				<input type="number" class="form-control" id="codigo" name="codigo" min="0" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	 <input type="hidden" name="perfilID" id="perfilID">
      	 <input type="submit" name="submit" class="btn btn-primary" value="Ok">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR PERFIL -->



