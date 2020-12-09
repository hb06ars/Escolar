<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<!-- TABELAS COM FILTRO -->
<jsp:include page="includes/jquery/filtro.jsp" />
<jsp:include page="includes/modais/modalPresenca.jsp" />
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
	<h1 class="h4">${periodoAtual} - ${diaDaSemanaAtual}</h1>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<c:if test="${ usuarioSessao.perfil.admin}">
			<th >Alterar </th>
			<th >Compareceu? </th>
		</c:if>
		<c:if test="${ !usuarioSessao.perfil.admin}">
			<th ></th>
			<th >Compareceu? </th>
		</c:if> 
		<th>Nome</th>
		<c:if test="${usuarioSessao.perfil.admin}">
			<th>Telefone</th> <th>Ativo</th> <th>Email</th>
		</c:if>
		
		
		<tr>
		<th></th>
		<th><input type="text" id="filtro1"/></th>
		<th><input type="text" id="filtro2"/></th>
		
		<c:if test="${usuarioSessao.perfil.admin}">
			<th><input type="text" id="filtro3"/></th>
			<th><input type="text" id="filtro4"/></th>
			<th><input type="text" id="filtro5"/></th>
		</c:if>
		
		<th></th>
		</tr>
		
		</thead>
		<tbody>
		<tr>
		
		
		<c:forEach items="${usuarios}" var="h">
			<c:if test="${!usuarioSessao.perfil.admin}">
				<td>
					<c:if test="${h.compareceu}" ><i class="fas fa-window-close" ></i> </c:if>
					<c:if test="${!h.compareceu}" ><i class="fas fa-check" ></i> </c:if>
				</td>
			</c:if>
			<c:if test="${usuarioSessao.perfil.admin}">
					<td>
						<i class="fas fa-window-close" onClick="validarPresenca(${h.id},'faltou')"></i> &nbsp
						<i class="fas fa-check" onClick="validarPresenca(${h.id},'compareceu')"></i>
					</td>
			</c:if>
			
			<td
				<c:if test="${h.compareceu}" >style="background-color:#9fc4d6;color:black" </c:if>
				<c:if test="${!h.compareceu}" >style="background-color:#d17875;color:white" </c:if>
			> 	<c:if test="${h.compareceu}" >Sim </c:if> <c:if test="${!h.compareceu}" >Não </c:if>
				
				<td>${h.nome}
				<c:if test="${usuarioSessao.perfil.admin}">
					<td>${h.telefone}
					<td><c:if test="${h.ativo}">Sim</c:if> <c:if test="${!h.ativo}">Não</c:if>
					<td>${h.email}
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

