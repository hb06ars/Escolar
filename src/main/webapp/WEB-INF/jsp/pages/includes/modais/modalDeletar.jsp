<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalDeletar(campo, id){
	document.getElementById("tabelaExclusao").value = campo;
	document.getElementById("idExclusao").value = id;
	$("#modalDeletar").modal().show();
}

function confirmaDelete(){
	var tabela = document.getElementById("tabelaExclusao").value; 
	var id = document.getElementById("idExclusao").value;
	document.getElementById("formDeletar").submit();
}

</script>
<form action="/deletando" method="post" id="formDeletar" accept-charset="utf-8">
	<div class="modal fade" id="modalDeletar" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="TituloModalCentralizado">Exclusão</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	Tem certeza que deseja excluir?
	      </div>
	      <div class="modal-footer">
	      	 <input type="hidden" name="tabela" id="tabelaExclusao">
	      	 <input type="hidden" name="id" id="idExclusao">
	        <button type="button" class="btn btn-danger" id="deletar" onclick="confirmaDelete()" data-dismiss="modal">Sim</button>
	        <button type="button" class="btn btn-primary" id="deletar" data-dismiss="modal">Não</button>
	      </div>
	    </div>
	  </div>
	</div>
</form>
<!--  DELETAR PERFIL -->
