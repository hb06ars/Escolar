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

<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Avaliação</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Matrícula</th>
											<th>Nome</th>
											<th>Celular</th>
											<th>Observações</th>
											<th>Avaliador</th>
											<th>Início</th>
											<th>Fim</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${avaliacao }" var="a">
											<tr class="gradeX">
												<td>${a.codigo }</td>
												<td>${a.aluno.nome }</td>
												<td>${a.aluno.celular }</td>
												<td>${a.observacoes }</td>
												<td>${a.avaliador.nome }</td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.inicio }" /></td>
												<td><fmt:formatDate pattern="dd/MM/yyyy" value="${a.fim }" /></td>
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