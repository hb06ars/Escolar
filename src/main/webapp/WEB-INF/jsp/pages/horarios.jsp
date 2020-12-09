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
<jsp:include page="includes/modais/modalProfessor.jsp" />
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
	<h1 class="h4">Horários (${periodoAtual} - ${diaDaSemanaAtual })</h1>
</div>



<div style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<th rowspan="2" style="background-color:#ADC4D1; vertical-align: middle;">Horário</th>
		<c:forEach items="${quantidadeDeSalas }" var="s">
			<th style="background-color:#8EAAB9">Sala ${s}</th>
		</c:forEach>
		<tr>
		<c:forEach items="${quantidadeDeSeries }" var="se">
			<th style="background-color:#B6D5E6" >${se}</th>
		</c:forEach>

		</thead>
		<tbody>
		<tr>
		
		<c:set var = "encontrou" value = "0"/>
		<c:forEach items="${quantidadeDeHorarios }" var="ho">
				<tr><th style="background-color:#8CB1C0" >${ho}</th>
				<c:forEach items="${quantidadeDeSalas }" var="s" varStatus="loop">
					<c:set var = "encontrou" value = "0"/>
					<c:forEach items="${horarios}" var="h" >
						<c:if test="${ho eq h.horarioDaAula }">
							<c:if test="${h.sala eq s && encontrou == 0 }">
								<td  onclick="modalProfessor(${h.usuario.id}, '${h.usuario.nome}')"
									<c:if test="${h.usuario.compareceu }">style="background-color:#A8F0B0"</c:if>
									<c:if test="${!h.usuario.compareceu }">style="background-color:#FD8F8F"</c:if>
								 >${h.usuario.nome }</td>
								<c:set var = "encontrou" value = "1"/>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
		</c:forEach>
				
		
				
		
		</tbody>
	</table>
	</div>
<br>


<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->

