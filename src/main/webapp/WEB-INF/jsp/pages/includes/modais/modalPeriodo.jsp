<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<jsp:include page="../mascaras/mascaras.jsp" />
<jsp:include page="../jquery/excel/downloadExcel.jsp" />

<!--  EDITAR Recado -->
<script>


function modalNovoPeriodo(){
	document.getElementById("form").action = "/recados/salvarPeriodo";
	$("#modalEditarPeriodo").modal().show();
}





</script>


<form action="<c:url value='/recados/salvarPeriodo'/>" id="form" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
<div class="modal fade" id="modalEditarPeriodo" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
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
  			Código:
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="codigo" name="codigo" type="text" required aria-describedby="inputGroup-sizing-default">
			</div>
		</div>
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
  			Início:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="inicio" id="inicio" class="form-control" onkeypress="mascaraPeriodo(this)" onkeyup="somenteNumeros(this);" placeholder="18:30" autocomplete="off" maxlength="5" minlength="5" >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Fim:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="fim" id="fim" class="form-control" onkeypress="mascaraPeriodo(this)" onkeyup="somenteNumeros(this);" placeholder="18:30" autocomplete="off" maxlength="5" minlength="5" >
			</div>
		</div>
		
      </div>
      <div class="modal-footer">
      	<input type="hidden" name="funcPeriodo" id="funcPeriodo" value="">
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


