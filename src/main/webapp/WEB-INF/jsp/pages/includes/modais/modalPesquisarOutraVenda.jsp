<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function modalPesquisarOutraVenda(){
	$("#modalPesquisarOutraVenda").modal().show();
}

</script>


<form action="<c:url value='/adm/todasVendas'/>" id="form" method="post" >
<div class="modal fade" id="modalPesquisarOutraVenda" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Pesquisar Venda</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div>
  			Nota:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="nota" name="nota" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	       	 <input type="submit" name="submit" class="btn btn-primary" value="Pesquisar">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR PERFIL -->



