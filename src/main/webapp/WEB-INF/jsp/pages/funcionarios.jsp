<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<jsp:include page="includes/modais/modalFuncionario.jsp" />
<!-- TABELAS COM FILTRO -->
<jsp:include page="includes/jquery/filtro.jsp" />
<script type="text/javascript" src="https://code.jquery.com/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="includes/jquery/script.js"></script>
<link href="script.js" rel="stylesheet" id="style">
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
	<h1 class="h4">&nbsp&nbsp Funcionários</h1>
	<div>
		<c:if test="${usuarioSessao.perfil.admin}">
			<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoFuncionario()"><span class="material-icons icon">person_add</span></button>
		</c:if>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><span class="material-icons icon">save</span></button>
		<c:if test="${usuarioSessao.perfil.admin}">
			<button class="shadow btn btn-sm btn-outline-dark" onclick="modalUploadExcel('funcionarios')"><span class="material-icons icon">publish</span></button>
		</c:if>
		&nbsp&nbsp
	</div>
</div>


<div class="card" >
<div class="card-body p-0 border-0" style="overflow: auto; width: 100%">
	<table id="tabela" class="table-responsive table-striped table-sm">
		<thead>
		<tr>
		<c:if test="${ usuarioSessao.perfil.admin}">
			<th>Editar </th>
		</c:if>
		<c:if test="${ !usuarioSessao.perfil.admin}">
			<th></th>
		</c:if> 
		
		<th>ID</th><th>Nome</th> <th>Cargo</th> <th>Perfil</th>
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Login</th><th>Telefone</th> <th>Ativo</th> <th>Email</th>
		</c:if>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><span class="material-icons icon">edit</span></th>
		<th><input style="min-width:40px;max-width:40px" type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		<th><input type="text" id="filtro3"/></th>
		<th><input type="text" id="filtro4"/></th>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th><input type="text" id="filtro5"/></th>
			<th><input type="text" id="filtro6"/></th>
			<th><input style="min-width:40px;max-width:40px" type="text" id="filtro7"/></th>
			<th><input type="text" id="filtro8"/></th>
			<th><input style="min-width:40px;max-width:40px" type="text" id="filtro9"/></th>
		</c:if>
		
		<th></th>
		</tr>
		
		
		</thead>
		<tbody>
		<tr>
		
		
		
		<c:forEach items="${funcionarios}" var="f">
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><span class="material-icons icon" style="cursor:pointer" onclick="modalEditarFuncionario( ${f.id}, '${f.nome}', '${f.cargo}', '${f.perfil.nome}', '${f.login}', '${f.senha}', '${f.telefone}',  '${f.ativo}', '${f.email}' )">edit</span></td>
			</c:if>
			<c:if test="${!usuarioSessao.perfil.admin}">
				<td></td>
			</c:if>
			<td>${f.id}
			<td>${f.nome}
			<td>${f.cargo}
			<td>${f.perfil.nome}
			
			<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
				<td>${f.login}
				<td>${f.telefone}
				<td><c:if test="${f.ativo}">Sim</c:if> <c:if test="${!f.ativo}">Não</c:if>
				<td>${f.email}
			</c:if>
						  
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><span class="material-icons icon" style="cursor:pointer" onclick="modalDeletar('funcionarios', ${f.id})" >delete</span></td>
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

