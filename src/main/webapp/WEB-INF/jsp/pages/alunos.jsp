<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<jsp:include page="includes/modais/modalUsuario.jsp" />
<!-- TABELAS COM FILTRO -->
<jsp:include page="includes/jquery/filtro.jsp" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="pages/includes/jquery/script.js"></script>
<!-- TABELAS COM FILTRO -->
<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->
<!-- UPLOAD EXCEL -->
<jsp:include page="includes/modais/modalUploadExcel.jsp" />
<!-- UPLOAD EXCEL -->

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h4">Alunos</h1>
	<div>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalPesquisarUsuario()">Pesquisar</button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoUsuario()">Novo</button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><i class="fas fa-download"></i></button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalUploadExcel('funcionarios')"><i class="fas fa-upload"></i></button>
	</div>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<c:if test="${perfilSessao.admin}">
			<th>Editar </th>
		</c:if> 
		<th>Login</th><th>Nome</th><th>Perfil</th><th>Código</th><th>Email</th>
		<c:if test="${perfilSessao.admin}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><i class="fas fa-search"></i></th>
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
		
		
		
		<c:forEach items="${usuarios}" var="u">
			<c:if test="${perfilSessao.admin}">
				<c:if test="${u.perfil.admin == true}"><c:set var = "tipoPerfilFunc" value = "admin"/></c:if>
				<c:if test="${u.perfil.funcionario == true}"><c:set var = "tipoPerfilFunc" value = "funcionario"/></c:if>
				<td><i class="fas fa-edit" onclick="modalEditarUsuario(${u.ID}, '${u.login}', '${u.nome}','${u.codigo}','${u.email}','${tipoPerfilFunc}')"></i></td>
			</c:if>
			<td>${u.login}  
			<td>${u.nome}   
			<td>${u.perfil.descricao} 
			<td>${u.codigo} 
			<td>${u.email}  
			<c:if test="${perfilSessao.admin}">
				<td><i class="fas fa-trash" onclick="modalDeletar('funcionario', ${u.ID})" ></i></td>
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

