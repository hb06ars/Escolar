<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalCancelarVenda(){
	if(document.getElementById("botaoInicia").innerText == "FINALIZAR VENDA"){
	$("#modalCancelarVenda").modal().show();
	}else{
		mensagemErro('Venda','Você não pode cancelar uma venda que não foi iniciada.');
	}
}

function confirmaCancela(){
	cancelarVenda();
}

</script>
<div class="modal fade" id="modalCancelarVenda" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Cancelar Venda</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	Tem certeza que deseja cancelar?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="deletar" onclick="confirmaCancela()" data-dismiss="modal">Sim</button>
        <button type="button" class="btn btn-primary" id="deletar" data-dismiss="modal">Não</button>
      </div>
    </div>
  </div>
</div>
<!--  DELETAR PERFIL -->
