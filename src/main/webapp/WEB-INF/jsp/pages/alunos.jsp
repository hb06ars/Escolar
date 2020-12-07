<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<jsp:include page="includes/modais/modalAluno.jsp" />
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
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalNovoAluno()">Novo</button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><i class="fas fa-download"></i></button>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="modalUploadExcel('alunos')"><i class="fas fa-upload"></i></button>
	</div>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<c:if test="${ usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario }">
			<th>Editar </th>
		</c:if> 
		<th>ID</th> <th>Nome</th> <th>Perfil</th> <th>Série</th> <th>Turma</th>
		<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
			<th>Endereco</th> <th>Bairro</th> <th>Cep</th> <th>Cidade</th> <th>Estado</th> <th>Telefone</th> <th>Ativo</th> <th>Email</th> <th>RA</th> <th>RG</th> <th>Cpf</th> <th>Data de Nascimento</th> <th>Responsavel</th> <th>CPF Responsável</th> <th>Suspensao</th>
		</c:if>
		
		<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
			<th>Excluir</th> 
		</c:if>
		
		<tr>
		<th><i class="fas fa-search"></i></th>
		<th><input type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		<th><input type="text" id="filtro3"/></th>
		<th><input type="text" id="filtro4"/></th>
		<th><input type="text" id="filtro5"/></th>
		
		<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
			<th><input type="text" id="filtro6"/></th>
			<th><input type="text" id="filtro7"/></th>
			<th><input type="text" id="filtro8"/></th>
			<th><input type="text" id="filtro9"/></th>
			<th><input type="text" id="filtro10"/></th>
			<th><input type="text" id="filtro11"/></th>
			<th><input type="text" id="filtro12"/></th>
			<th><input type="text" id="filtro13"/></th>
			<th><input type="text" id="filtro14"/></th>
			<th><input type="text" id="filtro15"/></th>
			<th><input type="text" id="filtro16"/></th>
			<th><input type="text" id="filtro17"/></th>
			<th><input type="text" id="filtro18"/></th>
			<th><input type="text" id="filtro19"/></th>
			<th><input type="text" id="filtro20"/></th>
		</c:if>
		
		<th></th>
		</tr>
		
		
		</thead>
		<tbody>
		<tr>
		
		
		
		<c:forEach items="${alunos}" var="a">
			<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
				<td><i class="fas fa-edit" onclick="modalEditarAluno(${a.id}, '${a.nome}', '${a.perfil.nome}', '${a.endereco}', '${a.bairro}', '${a.cep}', '${a.cidade}', '${a.estado}', '${a.telefone}',  ${a.ativo}, '${a.email}', '${a.ra}', '${a.rg}', '${a.cpf}', '${a.dataNascimento}', '${a.serie}', '${a.turma}', '${a.responsavel}', '${a.cpfResponsavel}', ${a.suspensao})"></i></td>
			</c:if>
			<td>${a.id}  
			<td>${a.nome}
			<td>${a.perfil.nome}
			<td>${a.serie}
			<td>${a.turma}
			
			<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
				<td>${a.endereco}
				<td>${a.bairro}
				<td>${a.cep}
				<td>${a.cidade}
				<td>${a.estado}
				<td>${a.telefone}
				<td><c:if test="${a.ativo}">Sim</c:if> <c:if test="${!a.ativo}">Não</c:if>
				<td>${a.email}
				<td>${a.ra}
				<td>${a.rg}
				<td>${a.cpf}
				<td>${a.dataNascimento}
				<td>${a.responsavel}
				<td>${a.cpfResponsavel}
				<td><c:if test="${a.suspensao}">Sim</c:if> <c:if test="${!a.suspensao}">Não</c:if>
			</c:if>
						  
			<c:if test="${usuarioSessao.perfil.admin || usuarioSessao.perfil.funcionario}">
				<td><i class="fas fa-trash" onclick="modalDeletar('alunos', ${a.id})" ></i></td>
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

