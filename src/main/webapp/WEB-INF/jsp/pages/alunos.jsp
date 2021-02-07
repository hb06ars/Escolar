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
						<input type="number" placeholder="Matrícula" name="matricula" class="form-control" required>
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
						<input type="text" placeholder="Endereço" name="endereco" class="form-control">
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
						<input type="text" placeholder="Observações no Contrato" name="contrato_obs" class="form-control">
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Início Contrato
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
					
					<div class="col-md-6 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Parcelas
							</span>
							<input type="number" name="contrato_parcelas" id="contrato_parcelas" onkeyup="calcular()" placeholder="1" min="1" value="1" class="form-control" />
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
											<th>Matrícula</th>
											<th>Situação</th>
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
										<tr class="gradeX">
											<td>12345</td>
											<td style="color:green">Regular</td>
											<td>28/01/2020</td>
											<td>Henrique Brandão</td>
											<td class="center">(11)99999-8888</td>
											<td class="center">(11)89999-8888</td>
											<td>teste@teste.com</td>
											<td>Rua da Alegria</td>
											<td>Bairro da Felicidade</td>
											<td>São Paulo</td>
											<td>SP</td>
											<td>222.333.444-88</td>
										</tr>
										<tr class="gradeX">
											<td>32345</td>
											<td style="color:red">Pendente</td>
											<td>28/01/2020</td>
											<td>Juca da SIlva</td>
											<td class="center">(11)39999-8888</td>
											<td class="center">(11)49999-8888</td>
											<td>email@teste.com</td>
											<td>Av da Paz</td>
											<td>Bairro da Amizade</td>
											<td>São Paulo</td>
											<td>SP</td>
											<td>322.333.444-88</td>
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