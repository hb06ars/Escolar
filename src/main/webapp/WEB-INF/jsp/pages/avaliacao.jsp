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
											<th>Excluir</th>
											<th>Matrícula</th>
											<th>Nome</th>
											<th>Treino</th>
											<th>Ordem</th>
											<th>Séries</th>
											<th>Repetições</th>
											<th>Descanso</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${treinos }" var="t">
											<tr class="gradeX">
												<td>
													<i class="fa fa-trash" onclick="modalDeletar('treino', ${t.id}) "></i> &nbsp
												</td>
												<td>${t.aluno.matricula }</td>
												<td>${t.aluno.nome }</td>
												<td>${t.descricao }</td>
												<td>
													<c:if test="${t.tipoOrdem == 0}">A</c:if>
													<c:if test="${t.tipoOrdem == 1}">B</c:if>
													<c:if test="${t.tipoOrdem == 2}">C</c:if>
													<c:if test="${t.tipoOrdem == 3}">D</c:if>
													<c:if test="${t.tipoOrdem == 4}">E</c:if>
													<c:if test="${t.tipoOrdem == 5}">F</c:if>
													<c:if test="${t.tipoOrdem == 6}">G</c:if>
												</td>
												<td>${t.series }</td>
												<td>${t.repeticoes }</td>
												<td>${t.descanso }</td>
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