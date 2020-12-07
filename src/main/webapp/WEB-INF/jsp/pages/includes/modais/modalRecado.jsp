<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<jsp:include page="../mascaras/mascaras.jsp" />
<jsp:include page="../jquery/excel/downloadExcel.jsp" />

<!--  EDITAR Recado -->
<script>


function modalNovoRecado(){
	document.getElementById("form").action = "/recados/salvarRecado";
	$("#modalEditarRecado").modal().show();
}





</script>


<form action="<c:url value='/recados/salvarRecado'/>" id="form" method="post" >
<div class="modal fade" id="modalEditarRecado" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
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
  			Remetente:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="remetente" name="remetente" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Para:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="para" name="para" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Assunto:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="assunto" name="assunto" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Conteúdo:
  			</div>
  			<div class="input-group mb-3">
  				<textArea class="form-control" id="conteudo" name="conteudo" type="text" required aria-describedby="inputGroup-sizing-default"></textArea>
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Data:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="dataEnvio" name="dataEnvio" type="date" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	<input type="hidden" name="funcRecado" id="funcRecado" value="">
		<input type="hidden" name="alterarSalvar" id="alterarSalvar">
      	<input type="hidden" name="ID" id="ID">
      	<input type="submit" name="submit" class="btn btn-primary" value="Ok">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR Recado -->


