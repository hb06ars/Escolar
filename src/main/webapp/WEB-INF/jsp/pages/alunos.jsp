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

<script>
function calcular(){
	var totalContrato = document.getElementById("contrato_totalContrato").value.replace(',','.');
	document.getElementById("contrato_totalContrato").value = totalContrato; 
	var sinal = document.getElementById("contrato_sinal").value.replace(',','.');
	var desconto = document.getElementById("contrato_desconto").value.replace(',','.');
	var parcelas = document.getElementById("contrato_parcelas").value.replace(',','.');
	var valorFinal = totalContrato - sinal - desconto;
	document.getElementById("contrato_valorDaParcela").value = (valorFinal / parcelas).toFixed([2]);
	document.getElementById("contrato_total").value = valorFinal.toFixed([2]);
}
</script>

<c:if test="${erro != null}">
	<div class="alert alert-danger">
		<button type="button" class="close" data-dismiss="alert" aria-hidden="true">�</button>
		<strong>Erro!</strong><br>Ocorreu um erro ao salvar o usu�rio. <br> Enviar o seguinte c�digo de erro ao desenvolvedor:<br>
		${erro }
	</div>
</c:if>

<!-- start: page -->
<div class="row">
<form action="/alunos" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title">Cadastrar novo aluno</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2 form-group">
						<input type="number" placeholder="Matr�cula" name="matricula" class="form-control" required>
					</div>
					<div class="col-md-5 form-group">
						<input type="text" placeholder="Nome" name="nome" class="form-control" required>
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="CPF" name="cpf" maxlength="14" minlength="14" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-birthday-cake"></i>
							</span>
							<input type="date" name="dataNascimento" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="number" name="telefone" name="telefone" class="form-control" placeholder="Telefone" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="number" name="celular" name="celular" class="form-control" placeholder="Celular" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-envelope"></i>
							</span>
							<input type="email" name="email" class="form-control" placeholder="eg.: email@email.com" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<input type="text" placeholder="Endere�o" name="endereco" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Bairro" name="bairro" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Cidade" name="cidade" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="Estado" maxlength="2" minlength="2" name="estado" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="CEP" maxlength="9" minlength="9" name="cep" class="form-control">
					</div>
					<div class="col-md-10 form-group">
						<input type="text" placeholder="Link da Foto" name="pathImagem" class="form-control">
					</div>
					
					
					
					<div class="col-md-4 form-group">
						<select id="plano" name="plano.id" class="form-control">
							<c:forEach items="${planos }" var="p">
								<option value="${p.id }">Plano ${p.nome } (${p.descricao})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Observa��es no Contrato" name="contrato_obs" class="form-control">
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								In�cio Contrato
							</span>
							<input type="date" name="contrato_inicio" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Fim Contrato
							</span>
							<input type="date" name="contrato_fim" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Total do Contrato
							</span>
							<input type="text" name="contrato_totalContrato" id="contrato_totalContrato" onkeyup="calcular()" value="0" class="form-control" />
						</div>
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Sinal
							</span>
							<input type="text" name="contrato_sinal" id="contrato_sinal" onkeyup="calcular()" min="0" value="0" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Desconto
							</span>
							<input type="text" name="contrato_desconto" id="contrato_desconto" onkeyup="calcular()" min="0" value="0" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Total a pagar
							</span>
							<input type="text" name="contrato_total" id="contrato_total" onkeyup="calcular()" min="0" value="0" class="form-control" />
						</div>
					</div>
					
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Parcelas
							</span>
							<input type="number" name="contrato_parcelas" id="contrato_parcelas" onkeyup="calcular()" placeholder="1" min="1" value="1" class="form-control" />
						</div>
					</div>
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Vencimento
							</span>
							<input type="number" name="contrato_vencimento" id="contrato_vencimento" placeholder="1" min="1" max="31" value="1" class="form-control" />
						</div>
					</div>
					<div class="col-md-6 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Valor da Parcela
							</span>
							<input type="text" name="contrato_valorDaParcela" id="contrato_valorDaParcela" onkeyup="calcular()" value="0" class="form-control" />
						</div>
					</div>
					<div class="col-md-2 form-group">
						<input type="submit" class="btn btn-primary" value="Salvar">
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
</div>






<section class="panel">
							<header class="panel-heading">
								<div class="panel-actions">
									<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
									<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
								</div>
						
								<h2 class="panel-title">Registro dos Alunos</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Editar</th>
											<th>Matr�cula</th>
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
										<c:forEach items="${usuarios }" var="u">
											<tr class="gradeX">
												<td>
													<i class="fa fa-trash" onclick="modalDeletar('usuario', ${u.id}) "></i> &nbsp
													<i class="fa fa-pencil" onclick="editar(${u.id }) "></i>
												</td>
												<td>${u.matricula }</td>
												<c:forEach items="${u.contrato }" var="c">
													<c:if test="${c.ativo }">
														<c:if test="${c.situacao == 'Regular'}">
															<td style="color:green">${c.situacao }</td>
														</c:if>
														<c:if test="${c.situacao == 'Pendente'}">
															<td style="color:red">${c.situacao }</td>
														</c:if>
														<td>
															<fmt:formatDate pattern="dd/MM/yyyy" value="${c.fim }" />
														</td>
													</c:if>
												</c:forEach>
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