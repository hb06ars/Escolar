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
	<h1 class="h4">Funcionários</h1>
	<div>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoFuncionario()">Novo</button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><i class="fas fa-download"></i></button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalUploadExcel('funcionarios')"><i class="fas fa-upload"></i></button>
	</div>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<c:if test="${ usuarioSessao.perfil.admin}">
			<th>Editar </th>
		</c:if> 
		<th>ID</th> <th>Nome</th> <th>Cargo</th> <th>Perfil</th>
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Login</th><th>Senha</th><th>Telefone</th> <th>Ativo</th> <th>Email</th>
		</c:if>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><i class="fas fa-search"></i></th>
		<th><input type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		<th><input type="text" id="filtro3"/></th>
		<th><input type="text" id="filtro4"/></th>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th><input type="text" id="filtro5"/></th>
			<th><input type="text" id="filtro6"/></th>
			<th><input type="text" id="filtro7"/></th>
			<th><input type="text" id="filtro8"/></th>
			<th><input type="text" id="filtro9"/></th>
		</c:if>
		
		<th></th>
		</tr>
		
		
		</thead>
		<tbody>
		<tr>
		
		
		
		<c:forEach items="${funcionarios}" var="f">
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><i class="fas fa-edit" onclick="modalEditarFuncionario( ${f.id}, '${f.nome}', '${f.cargo}', '${f.perfil.nome}', '${f.login}', '${f.senha}', '${f.telefone}',  '${f.ativo}', '${f.email}' )"></i></td>
			</c:if>
			<td>${f.id}  
			<td>${f.nome}
			<td>${f.cargo}
			<td>${f.perfil.nome}
			
			<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
				<td>${f.login}
				<td>${f.senha}
				<td>${f.telefone}
				<td><c:if test="${f.ativo}">Sim</c:if> <c:if test="${!f.ativo}">Não</c:if>
				<td>${f.email}
			</c:if>
						  
			<c:if test="${usuarioSessao.perfil.admin}">
				<td><i class="fas fa-trash" onclick="modalDeletar('funcionarios', ${f.id})" ></i></td>
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

