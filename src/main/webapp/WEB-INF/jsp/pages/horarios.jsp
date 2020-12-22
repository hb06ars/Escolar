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
<script type="text/javascript" src="includes/jquery/script.js"></script>
<!-- TABELAS COM FILTRO -->
<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->
<!-- UPLOAD EXCEL -->
<jsp:include page="includes/modais/modalUploadExcel.jsp" />
<!-- UPLOAD EXCEL -->

<script>
function passarMouse(linha){
	linha.style.color = "white";
}
function foraMouse(linha){
	linha.style.color = "";
}
</script>

<div class="card mb-4" >

<div
	class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<h1 class="h4">&nbsp ${periodoAtual} - ${diaDaSemanaAtual }</h1>
	<div>
		<button class="shadow btn btn-sm btn-outline-dark" onclick="tableToExcel('tabela', 'Documento')"><span class="material-icons icon">save</span></button>
		&nbsp
	</div>
</div>


<form action="<c:url value='/filtrarHorarios'/>" id="form" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
	<div class="d-flex pt-3 pb-2 mb-3">
		<div>&nbsp&nbsp</div>
	  	<div> 
	  		<select class="form-control" id="periodo" name="periodo" required aria-describedby="inputGroup-sizing-default">
	  			<c:forEach items="${periodos}" var="p">
	  				<option value="${p.id }" <c:if test="${p.nome eq periodoAtual}">selected="selected"</c:if> >${p.nome }</option>
	  			</c:forEach>
			</select>
		</div>
		<div>&nbsp&nbsp</div>
		<div>
	  		<select class="form-control" id="semana" name="semana" required aria-describedby="inputGroup-sizing-default">
				<option value="seg" <c:if test="${diaDaSemanaAtual eq 'Segunda'} ">selected="selected"</c:if> >Segunda</option>
				<option value="ter" <c:if test="${diaDaSemanaAtual eq 'Terça'}">selected="selected"</c:if> >Terça</option>
				<option value="qua" <c:if test="${diaDaSemanaAtual eq 'Quarta'}">selected="selected"</c:if> >Quarta</option>
				<option value="qui" <c:if test="${diaDaSemanaAtual eq 'Quinta'}">selected="selected"</c:if> >Quinta</option>
				<option value="sex" <c:if test="${diaDaSemanaAtual eq 'Sexta'}">selected="selected"</c:if> >Sexta</option>
				<option value="sab" <c:if test="${diaDaSemanaAtual eq 'Sábado'}">selected="selected"</c:if> >Sábado</option>
				<option value="dom" <c:if test="${diaDaSemanaAtual eq 'Domingo'}">selected="selected"</c:if> >Domingo</option>
			</select>
		</div>
		<div>&nbsp&nbsp</div>
		<div>
	  		<input type="submit" id="pesquisar" class="form-control btn btn-secondary" value="Pesquisar" >
		</div>
	</div>
</form>



<div class="card" >
<div class="card-body p-0 border-0" style="overflow: auto; width: 100%">
	<table id="tabela" class="table table-striped table-bordered table-sm">
		<thead>
		<tr>
		<th rowspan="2" style="background-color:#8EAAB9; vertical-align: middle;">Horário</th>
		<c:forEach items="${quantidadeDeSalas }" var="s">
			<th style="background-color:#8EAAB9">Sala ${s}</th>
		</c:forEach>
		<tr>
		<c:forEach items="${quantidadeDeSeries }" var="se">
			<th style="background-color:#9fc4d6" >${se}</th>
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
								<td  onmouseover="passarMouse(this)" onmouseout="foraMouse(this)"
									<c:if test="${usuarioSessao.perfil.admin}">
										onclick="modalProfessor(${h.usuario.id}, '${h.usuario.nome}', ${h.id }, '${h.usuario.telefone }', '${h.sala }', '${h.turma }','${h.horarioDaAula }')"
									</c:if>
									<c:if test="${h.usuario.compareceu && h.substituto == null}">style="cursor:pointer;background-color:#B6DCB6"</c:if>
									<c:if test="${!h.usuario.compareceu && h.substituto == null}">style="cursor:pointer;background-color:#FD8F8F"</c:if>
									<c:if test="${!h.usuario.compareceu && h.substituto == null}">style="cursor:pointer;background-color:#FD8F8F"</c:if>
									<c:if test="${h.substituto != null}">style="cursor:pointer;background-color:#F8D49B"</c:if>
								 >
								 <c:if test="${h.substituto != null }"> ${h.substituto.nome } </c:if>
								 <c:if test="${h.substituto == null }"> ${h.usuario.nome } </c:if>
								 
								 </td>
								<c:set var = "encontrou" value = "1"/>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
		</c:forEach>
		
		</tbody>
	</table>
</div>
</div>
<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
	<div>&nbsp&nbsp<b>Legenda:</b>
		<div>&nbsp&nbsp<span class="material-icons icon" style="color:#B6DCB6">stop_circle</span> Compareceu</div>
		<div>&nbsp&nbsp<span class="material-icons icon" style="color:#FD8F8F">stop_circle</span> Não veio  / Aula Vaga</div>
		<div>&nbsp&nbsp<span class="material-icons icon" style="color:#F8D49B">stop_circle</span> Substituto</div>
	</div>
</div>
</div>




<!-- FOOTER -->
<jsp:include page="includes/barraFooter.jsp" />
<!-- FOOTER -->
<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->

