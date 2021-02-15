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


<style>
.dot {
  height: 25px;
  width: 25px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
}
</style>


<!-- start: page -->
<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Presen�a dos Alunos</h2>
							</header>
							
							<div class="panel-body" style="overflow:auto">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" >
									<thead>
										<tr>
											<th>Matr�cula</th>
											<th>Nome</th>
											<th>M�s/Ano</th>
											<c:forEach items="${dias }" var="d">
												<th>${d }</th>
											</c:forEach>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios }" var="u">
										
										<tr class="gradeX">
											<td>${u.matricula}</td>
											<td>${u.nome}</td>
											<td>${mes}/${ano}</td>
											
											
											<c:forEach items="${dias }" var="d">
												<c:set var="cont" value="0"/>
												<c:forEach items="${presenca }" var="p" varStatus="loop">
													<c:if test="${p.usuario.id == u.id}">
														<c:set var="dia" value="${fn:substring(p.presenca, 8, 10)}" />
														<c:if test="${dia == d  }">
															<c:set var="cont" value="1"/>
															<td style="background-color: #E3F6CE; border-color: #BDBDBD" ><span class="dot" onclick="modalHorario('${p.presenca}')" style="background-color: green;"></span></td>
														</c:if>
													</c:if>
												</c:forEach>
												<c:if test="${cont == 0 }">
													<td style="background-color: #F6CECE; border-color: #BDBDBD " ><span class="dot" style="background-color: red;"></span></td>
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