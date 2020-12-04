<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function modalEditarCategoria(id, descricao, codigo){
	document.getElementById("form").action = "/Sistema/adm/atualizarCategoria";
	document.getElementById("categoriaID").value = id;
	document.getElementById("descricao").value = descricao;
	document.getElementById("codigo").value = codigo;
	document.getElementById("codigo").readOnly = true;
	document.getElementById("descricao").required = true;
	document.getElementById("alterarSalvar").value = 'edita';
	$("#modalEditarCategoria").modal().show();
}


function modalNovoCategoria(){
	document.getElementById("form").action = "/Sistema/adm/atualizarCategoria";
	document.getElementById("categoriaID").value = '';
	document.getElementById("descricao").value = '';
	document.getElementById("codigo").value = '';
	document.getElementById("codigo").required = true;
	document.getElementById("codigo").readOnly = false;
	document.getElementById("descricao").required = true;
	document.getElementById("descricao").readOnly = false;
	document.getElementById("alterarSalvar").value = 'novo';
	$("#modalEditarCategoria").modal().show();
}


function modalPesquisarCategoria(){
	document.getElementById("form").action = "/Sistema/adm/pesquisarCategoria";
	document.getElementById("categoriaID").value = '';
	document.getElementById("descricao").value = '';
	document.getElementById("codigo").value = '';
	document.getElementById("codigo").required = false;
	document.getElementById("codigo").readOnly = false;
	document.getElementById("descricao").required = false;
	document.getElementById("descricao").readOnly = false;
	$("#modalEditarCategoria").modal().show();
}

</script>


<form action="<c:url value='/adm/atualizarCategoria'/>" id="form" method="post" >
<div class="modal fade" id="modalEditarCategoria" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Categoria</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div>
  			Descrição:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="descricao" name="descricao" type="text" required aria-describedby="inputGroup-sizing-default">
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
      	 <input type="hidden" name="alterarSalvar" id="alterarSalvar">
      	 <input type="hidden" name="categoriaID" id="categoriaID">
      	 <input type="submit" name="submit" class="btn btn-primary" value="Ok">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR PERFIL -->



