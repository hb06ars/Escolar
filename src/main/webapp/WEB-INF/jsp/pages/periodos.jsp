<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<jsp:include page="includes/modais/modalPeriodo.jsp" />
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

<div class="card mb-4">

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h4">&nbsp&nbsp Períodos</h1>
	<div>
		<c:if test="${usuarioSessao.perfil.admin}">
			<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoPeriodo()"><span class="material-icons icon">add_circle</span></button>
		</c:if>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><span class="material-icons icon">save</span></button>
		&nbsp&nbsp
	</div>
</div>



<div class="card" >
<div class="card-body p-0 border-0" style="overflow: auto; width: 100%">


	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		
		<th>Código </th><th>Período</th><th>Início</th> <th>Fim</th>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><input type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		<th><input type="text" id="filtro3"/></th>
		<th><input type="text" id="filtro4"/></th>
		<th></th>
		</tr>
		
		</thead>
		<tbody>
		<tr>
		
		
		<c:forEach items="${periodos}" var="p">
			<td>${p.codigo}  
			<td>${p.nome}
			<td>${p.inicio}
			<td>${p.fim}
					  
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><span class="material-icons icon" style="cursor:pointer" onclick="modalDeletar('periodos', ${p.id})" >delete</span></td>
			</c:if>
			<tr>	
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
<br>
</div>

<!-- FOOTER -->
<jsp:include page="includes/barraFooter.jsp" />
<!-- FOOTER -->

<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->

