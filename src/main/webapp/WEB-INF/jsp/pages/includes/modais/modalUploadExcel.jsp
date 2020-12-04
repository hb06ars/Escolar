<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function modalUploadExcel(tabela){
	if(tabela == 'funcionarios' || tabela == 'clientes'){
		document.getElementById("modelo").innerHTML = "<table class='table table-striped table-bordered table-sm'>"+"<tr>"
		+"<td>Login</td>"
		+"<td>Nome</td>"
		+"<td>Perfil</td>"
		+"<td>Código</td>"
		+"<td>Email</td>"
		+"</tr>"
		+"<tr>"
		+"<td>jhsb123</td>"
		+"<td>José</td>"
		+"<td>admin</td>"
		+"<td>555</td>"
		+"<td>jose@email.com</td>"
		+"</table>";	
	}
	if(tabela == 'perfis'){
		document.getElementById("modelo").innerHTML = "<table class='table table-striped table-bordered table-sm'>"+"<tr>"
		+"<td>Código</td>"
		+"<td>Descrição</td>"
		+"<td>Permissão</td>"
		+"</tr>"
		+"<tr>"
		+"<td>123</td>"
		+"<td>Admin</td>"
		+"<td>admin</td>"
		+"</table>";
	}
	if(tabela == 'produtos'){
		document.getElementById("modelo").innerHTML = "<table class='table table-striped table-bordered table-sm'>"+"<tr>"
		+"<td>Codigo</td>"
		+"<td>Ean</td>"
		+"<td>Descricao</td>"
		+"<td>Preco</td>"
		+"<td>Categoria</td>"
		+"<td>PrecoPromocional</td>"
		+"<td>Estoque</td>"
		+"<td>Fornecedor</td>"
		+"<td>Marca</td>"
		+"<td>Peso</td>"
		+"<td>TipoPeso</td>"
		+"<td>Unidades</td>"
		+"<td>ImgPath</td>"
		+"</tr>"
		+"<tr>"
		+"<td>123</td>"
		+"<td>789515155156</td>"
		+"<td>Achocolatado XYZ</td>"
		+"<td>10.25</td>"
		+"<td>Salgados</td>"
		+"<td>1.25</td>"
		+"<td>10</td>"
		+"<td>Empresa XYZ</td>"
		+"<td>XYZ</td>"
		+"<td>100</td>"
		+"<td>g</td>"
		+"<td>1</td>"
		+"<td>http:\\asasdasdasd.png</td>"
		+"</table>";
	}
	if(tabela == 'categorias'){
		document.getElementById("modelo").innerHTML = "<table class='table table-striped table-bordered table-sm'>"+"<tr>"
		+"<td>Código</td>"
		+"<td>Descrição</td>"
		+"</tr>"
		+"<tr>"
		+"<td>123</td>"
		+"<td>Salgados</td>"
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



