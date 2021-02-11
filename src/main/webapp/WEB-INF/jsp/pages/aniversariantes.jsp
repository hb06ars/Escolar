<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<!-- HEADER -->
<!-- TAGS -->
<%@ page pageEncoding="UTF-8"%>
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
						
								<h2 class="panel-title">Aniversariantes de hoje</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Mensagem</th>
											<th>Matrícula</th>
											<th>Data de Nascimento</th>
											<th>Contrato</th>
											<th>Nome</th>
											<th>Telefone</th>
											<th>Celular</th>
											<th>Email</th>
											<th>Endereço</th>
											<th>Bairro</th>
											<th>Cidade</th>
											<th>Estado</th>
											<th>CPF</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${usuarios }" var="u">
											<tr class="gradeX">
												
												<c:set var = "primeiroNome"  value = "" />
												<c:set var = "fim"  value = "0" />
												<c:set var = "cont"  value = "0" />
												<c:forEach var="i" begin="0" end="${fn:length(u.nome) - 1}" step="1">
													<c:if test="${fim == 0}">
														<c:if test="${u.nome.charAt(i) != ' '}">
															<c:if test="${cont == 0}">
																<c:set var = "primeiroNome"  value = "${primeiroNome } ${u.nome.charAt(i)}" />
															</c:if>
															<c:if test="${cont > 0}">
																<c:set var = "primeiroNome" value = "${primeiroNome } ${fn:toLowerCase(u.nome.charAt(i))}" />
																<c:set var = "primeiroNome" value = "${fn:replace(primeiroNome, ' ', '')}" />
															</c:if>
														</c:if>
														<c:if test="${u.nome.charAt(i) == ' '}">
															<c:set var = "fim"  value = "1" />
														</c:if>
													</c:if>
													<c:set var = "cont"  value = "1" />
												</c:forEach>
											
												<td>
													<c:set var = "cel"  value = "${fn:replace(u.celular, '(', '')}" />
													<c:set var = "cel"  value = "${fn:replace(cel, ')', '')}" />
													<c:set var = "cel"  value = "${fn:replace(cel, ' ', '')}" />
													<c:set var = "cel"  value = "${fn:replace(cel, '-', '')}" />
													<c:set var = "msg"  value = "Olá ${primeiroNome}! Queremos desejar um feliz aniversario, muitos anos de vida e que tudo de bom aconteça contigo hoje e sempre!" />
													<a class="fa fa-whatsapp" href="https://api.whatsapp.com/send?phone=55${cel}&text=${msg}" style="color:green;background-color:white"></a>
												</td>
												
												<td>${u.matricula }</td>
												<td>
												<c:set var="nascimento" value="${fn:substring(u.dataNascimento, 8, 10)}/${fn:substring(u.dataNascimento, 5, 7)}/${fn:substring(u.dataNascimento, 0, 4)}" />
												${nascimento }
												</td>
												
												<td>
												<c:forEach items="${u.contrato}" var="c" varStatus="loop">
													<c:if test="${c.ativo == true }">
														<c:set var = "fimContrato" scope = "session" value = "${c.fim }"/>
													</c:if>
												</c:forEach>
												<fmt:formatDate pattern="dd/MM/yyyy" value="${fimContrato }" />
												</td>
												<td>${u.nome }</td>
												<td>${u.telefone }</td>
												<td>${u.celular }</td>
												<td>${u.email }</td>
												<td>${u.endereco }</td>
												<td>${u.bairro }</td>
												<td>${u.cidade }</td>
												<td>${u.estado }</td>
												<td>${u.cpf }</td>
											</tr>
										</c:forEach>
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