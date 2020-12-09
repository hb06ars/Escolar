<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<jsp:include page="includes/modais/modalRecado.jsp" />
<!-- TABELAS COM FILTRO -->
<jsp:include page="includes/jquery/filtro.jsp" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="includes/jquery/script.js"></script>
<!-- TABELAS COM FILTRO -->
<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->
<!-- UPLOAD EXCEL -->
<jsp:include page="includes/modais/modalUploadExcel.jsp" />
<!-- UPLOAD EXCEL -->



<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h4">Recados</h1>
	<div>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoRecado()">Novo</button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><i class="fas fa-download"></i></button>
	</div>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		
		<th>Remetente </th><th>Para</th><th>Assunto</th> <th>Conteúdo</th> <th>Data</th>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><input type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		<th><input type="text" id="filtro3"/></th>
		<th><input type="text" id="filtro4"/></th>
		<th><input type="text" id="filtro5"/></th>
		<th></th>
		</tr>
		
		</thead>
		<tbody>
		<tr>
		
		
		<c:forEach items="${recados}" var="r">
			<td>${r.remetente}  
			<td>${r.para}
			<td>${r.assunto}
			<td>${r.conteudo}
			<td>${r.data}
					  
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><i class="fas fa-trash" onclick="modalDeletar('recados', ${r.id})" ></i></td>
			</c:if>
			<tr>	
		</c:forEach>
		</tbody>
	</table>
	</div>
<br>


<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->

