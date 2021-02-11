<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<!-- HEADER -->
<!-- TAGS -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- TAGS -->
<!-- INICIO BODY -->

<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->

<form action="<c:url value='/upload/excel'/>" id="form" accept=".xlsx" method="post" enctype='multipart/form-data' accept-charset="utf-8" >
<div class="row">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Importar Aulas</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-12 form-group">
						Antes de cadastrar os hor�rios ser� necess�rio cadastrar todos os professores primeiro.<br>
						Para importar as aulas crie um arquivo no excel no formato (xlsx).<br>
						O sistema ir� limpar todas as aulas e se basear no Excel que voc� enviar.<br>
						Clique <a href="/modelos/aulas.xlsx">aqui</a> para baixar o modelo de exemplo de como importar.
					</div>
					<div class="col-md-10 form-group"	>
						<input type="file" id="file" name="file">
					</div>
					<div class="col-md-2 form-group">
						<input type="hidden" name="tabelaUsada" value="aulas">
						<input type="submit" class="btn btn-secondary" value="Enviar">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>


<!-- start: page -->
<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Aulas</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Hor�rio</th>
											<th>Segunda</th>
											<th>Ter�a</th>
											<th>Quarta</th>
											<th>Quinta</th>
											<th>Sexta</th>
											<th>S�bado</th>
											<th>Domingo</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="cont" value="0"/>
										<c:forEach items="${horarios }" var="h">
											<tr class="gradeX">
											<td>${h.inicio } / ${h.fim }</td>
											<c:forEach items="${aulas }" var="a" varStatus="loop">
												<c:if test="${a.inicio == h.inicio && a.fim == h.fim }">
													<c:if test="${a.diaSemana == 'Segunda' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="segundaOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'Ter�a' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="tercaOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'Quarta' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="quartaOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'Quinta' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="quintaOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'Sexta' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="sextaOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'S�bado' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="sabadoOk" value="1"/> </c:if>
													<c:if test="${a.diaSemana == 'Domingo' }" ><td>${a.nomeAula} (${a.professor})</td> <c:set var="domingoOk" value="1"/> </c:if>
													<c:set var="cont" value="${cont = cont + 1 }"/>
												</c:if>
												<c:if test="${cont > 6 }">
													<c:if test="${tercaOk < 1 }">
														<td></td>
													</c:if>
												</c:if>
											</c:forEach>
											
											</tr>
										</c:forEach>
									
									</tbody>
								</table>
							</div>
							<div class="panel-footer">
								<button type="button" class="btn btn-primary" onclick="tableToExcel('datatable-default', 'Documento')">Download</button>
							</div>
						</section>




<!-- end: page -->
	</section>
</div>







<!-- FIM BODY -->
</div>
<!-- FIM BODY -->
<!-- MAIN NO HEADER -->
</main>
<!-- HEADER -->
<jsp:include page="includes/footer.jsp" />
<!-- HEADER -->