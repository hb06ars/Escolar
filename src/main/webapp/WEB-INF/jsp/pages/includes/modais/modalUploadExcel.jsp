<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function modalUploadExcel(tabela){
	if(tabela == 'alunos'){
		document.getElementById("modelo").innerHTML = "<table class='table table-striped table-bordered table-sm'>"+"<tr>"
		+"<td>login</td>"
		+"<td>senha</td>"
		+"<td>ativo</td>"
		+"<td>nome</td>"
		+"<td>email</td>"
		+"<td>ra</td>"
		+"<td>rg</td>"
		+"<td>cpf</td>"
		+"<td>dataNascimento</td>"
		+"<td>serie</td>"
		+"<td>turma</td>"
		+"<td>telefone</td>"
		+"<td>endereco</td>"
		+"<td>cep</td>"
		+"<td>bairro</td>"
		+"<td>cidade</td>"
		+"<td>estado</td>"
		+"<td>responsavel</td>"
		+"<td>cpfResponsavel</td>"
		+"<td>suspensao</td>"
		+"<td>perfil</td>"
		+"</table>";	
	}
	document.getElementById("tabelaUsada").value = tabela;
	$("#modalUploadExcel").modal().show();
}
</script>


<form action="<c:url value='/adm/upload/excel'/>" id="form" accept=".xlsx" method="post" enctype='multipart/form-data' accept-charset="utf-8" >
<div class="modal fade" id="modalUploadExcel" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Upload Excel</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div>
  			Arquivo (.xlsx):
  			</div>
  			<div class="input-group mb-3">
  				<input class="form-control" id="file" name="file" type="file" required>
			</div>
			<div class="input-group mb-3">
				Envie uma tabela assim:
			</div>
			<div class="input-group mb-3"> 
  				<div id="modelo" style="overflow:auto"></div>
			</div>
			<div class="input-group mb-3">
				A aba da planilha deve conter o seguinte nome: Planilha1
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	 <input type="hidden" name="tabelaUsada" id="tabelaUsada">
      	 <input type="submit" name="submit" class="btn btn-primary" value="Enviar">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR PERFIL -->



