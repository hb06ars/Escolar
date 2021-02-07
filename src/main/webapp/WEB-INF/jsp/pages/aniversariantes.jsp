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



<!-- start: page -->
<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Aniversariantes</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Matr�cula</th>
											<th>Data de Nascimento</th>
											<th>Situa��o</th>
											<th>Contrato</th>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Celular</th>
											<th>Email</th>
											<th>Endere�o</th>
											<th>Bairro</th>
											<th>Cidade</th>
											<th>Estado</th>
											<th>CPF</th>
										</tr>
									</thead>
									<tbody>
										<tr class="gradeX">
											<td>12345</td>
											<td>1990-05-12</td>
											<td style="color:green">Regular</td>
											<td> 22/01/2001 </td>
											<td>Henrique Brand�o</td>
											<td class="center">(11)99999-8888</td>
											<td class="center">(11)89999-8888</td>
											<td>teste@teste.com</td>
											<td>Rua da Alegria</td>
											<td>Bairro da Felicidade</td>
											<td>S�o Paulo</td>
											<td>SP</td>
											<td>222.333.444-88</td>
										</tr>
									</tbody>
								</table>
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