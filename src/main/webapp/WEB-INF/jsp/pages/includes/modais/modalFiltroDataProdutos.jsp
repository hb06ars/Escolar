<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


<!--  AUTOR -->
<script>
function modalFiltroDataProdutos(){
	$("#modalFiltroDataProdutos").modal().show();
}

function verificaData(){
	var de = document.getElementById("dataDe").value;
	var ate = document.getElementById("dataAte").value;
	
	if(de != '' && ate != ''){
		var dataDe = new Date(de);
		var dataAte = new Date(ate);
		if(dataDe <= dataAte){
			document.getElementById("msgData").innerHTML = '';
			document.getElementById("btSubmitDate").style.display = 'block';
		} else{
			document.getElementById("msgData").innerHTML = 'Data inválida';
			document.getElementById("btSubmitDate").style.display = 'none';
		}
	}
	
}


</script>

<div class="modal fade" id="modalFiltroDataProdutos" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Filtrar data</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form action="<c:url value='/adm/relatoriosMaisVendidos'/>" id="form" method="post" >
      	<div class="modal-body">
	        <div class="form-group">
				<label >De:</label>
				<input type="date" name="dataDe" id="dataDe" onchange="verificaData()" max="3000-12-31" min="1000-01-01" class="form-control">
			</div>
			<div class="form-group">
				 <label >Até:</label>
				 <input type="date" name="dataAte" id="dataAte" min="1000-01-01" onchange="verificaData()" max="3000-12-31" class="form-control">
			</div>
			<div class="form-group">
				 <div id="msgData" style="color:red"></div>
			</div>
	    </div>
	    <div class="modal-footer">
	    	<div id="btSubmitDate" style="display:none">
	    		<input type="submit" name="submit" class="btn btn-primary" value="Selecionar">
	    	</div>
	    </div>
      </form>
    </div>
  </div>
</div>
<!--  FINAL AUTOR -->



