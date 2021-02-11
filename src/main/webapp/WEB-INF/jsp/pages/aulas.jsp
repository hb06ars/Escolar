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
						Antes de cadastrar os horários será necessário cadastrar todos os professores primeiro.<br>
						Para importar as aulas crie um arquivo no excel no formato (xlsx).<br>
						O sistema irá limpar todas as aulas e se basear no Excel que você enviar.<br>
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
											<th>Horário</th>
											<th>Segunda</th>
											<th>Terça</th>
											<th>Quarta</th>
											<th>Quinta</th>
											<th>Sexta</th>
											<th>Sábado</th>
											<th>Domingo</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${horarios }" var="h">
											<tr class="gradeX">
											<td>${h.inicio } / ${h.fim }</td>
											<c:forEach items="${aulas }" var="a" varStatus="loop">
												<c:if test="${a.inicio == h.inicio && a.fim == h.fim }">
													<c:if test="${a.diaSemana == 'Segunda' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Terça' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Quarta' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Quinta' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Sexta' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Sábado' }" ><td>${a.nomeAula}</td></c:if>
													<c:if test="${a.diaSemana == 'Domingo' }" ><td>${a.nomeAula}</td></c:if>
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