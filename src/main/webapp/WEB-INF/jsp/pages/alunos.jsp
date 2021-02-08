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

function acao(valor){
	document.getElementById("acao").value = valor;
}

function editar(id){
	document.getElementById("acao").value = 'atualizar';
	document.getElementById("atualizar").style.display = "block";
	document.getElementById("salvar").style.display = "none";
	var inicio = 'x';
	var fim = 'x';
	<c:forEach items="${usuarios }" var="u" varStatus="s">
		if(${u.id} == id){
			document.getElementById("matricula").value = '${u.matricula}';
			document.getElementById("nome").value = '${u.nome}';
			document.getElementById("cpf").value = '${u.cpf}';
			document.getElementById("dataNascimento").value = '${u.dataNascimento}';
			document.getElementById("telefone").value = '${u.telefone}';
			document.getElementById("celular").value = '${u.celular}';
			document.getElementById("email").value = '${u.email}';
			document.getElementById("endereco").value = '${u.endereco}';
			document.getElementById("bairro").value = '${u.bairro}';
			document.getElementById("cidade").value = '${u.cidade}';
			document.getElementById("estado").value = '${u.estado}';
			document.getElementById("cep").value = '${u.cep}';
			document.getElementById("pathImagem").value = '${u.pathImagem}';
			document.getElementById("plano").value = '${u.plano.id}';
			<c:forEach items="${u.contrato }" var="c">
				<c:if test="${c.ativo == true}">
					document.getElementById("contrato_totalContrato").value = '${c.valorBruto}';
					document.getElementById("contrato_obs").value = '${c.observacoes}';
					document.getElementById("contrato_total").value = '${c.valor}';
					document.getElementById("contrato_sinal").value = '${c.sinal}';
					document.getElementById("contrato_desconto").value = '${c.desconto}';
					document.getElementById("contrato_valorDaParcela").value = '${c.valorDaParcela}';
					document.getElementById("contrato_vencimento").value = '${c.vencimento}';
					document.getElementById("contrato_parcelas").value = '${c.parcelas}';
					inicio = '${c.inicio}'.replace('00:00:00','').replace('.0','').replace(' ','')
					fim = '${c.fim}'.replace('00:00:00','').replace('.0','').replace(' ','')
					document.getElementById("contrato_inicio").value = inicio;
					document.getElementById("contrato_fim").value = fim;
				</c:if>
			</c:forEach>
			
		}
	</c:forEach>

		
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
					<h2 class="panel-title" id="">Cadastrar novo aluno </h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2 form-group">
						<input type="number" placeholder="Matrícula" name="matricula" id="matricula" class="form-control" required>
					</div>
					<div class="col-md-5 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" required>
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="CPF" name="cpf" id="cpf" maxlength="14" minlength="14" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-birthday-cake"></i>
							</span>
							<input type="date" name="dataNascimento" id="dataNascimento" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="number" name="telefone" id="telefone" class="form-control" placeholder="Telefone" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-phone"></i>
							</span>
							<input type="number" name="celular" id="celular" class="form-control" placeholder="Celular" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="fa fa-envelope"></i>
							</span>
							<input type="email" name="email" id="email" class="form-control" placeholder="eg.: email@email.com" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<input type="text" placeholder="Endereço" id="endereco" name="endereco" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Bairro" id="bairro" name="bairro" class="form-control">
					</div>
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Cidade" id="cidade" name="cidade" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="Estado" id="estado" maxlength="2" minlength="2" name="estado" class="form-control">
					</div>
					<div class="col-md-2 form-group">
						<input type="text" placeholder="CEP" id="cep" maxlength="9" minlength="9" name="cep" class="form-control">
					</div>
					<div class="col-md-10 form-group">
						<input type="text" placeholder="Link da Foto" id="pathImagem" name="pathImagem" class="form-control">
					</div>
					
					
					
					<div class="col-md-4 form-group">
						<select id="plano" name="plano.id" class="form-control">
							<c:forEach items="${planos }" var="p">
								<option value="${p.id }">Plano ${p.nome } (${p.descricao})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Observações no Contrato"  id="contrato_obs" name="contrato_obs" class="form-control">
					</div>
					
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Início Contrato
							</span>
							<input type="date" name="contrato_inicio"   id="contrato_inicio" class="form-control" />
						</div>
					</div>
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon">
								Fim Contrato
							</span>
							<input type="date" name="contrato_fim" id="contrato_fim" class="form-control" />
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
					<div class="col-md-2 form-group" id="salvar">
						<input type="submit" class="btn btn-primary" onclick="acao('salvar')" value="Salvar">
					</div>
					<div class="col-md-1 form-group" id="atualizar" style="display:none">
						<input type="submit" class="btn btn-primary" onclick="acao('atualizar')" value="Atualizar">
					</div>
					<input type="hidden" id="acao" name="acao" value="salvar">
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
										<c:forEach items="${usuarios }" var="u">
											<tr class="gradeX">
												<td>
													<i class="fa fa-trash" onclick="modalDeletar('usuario', ${u.id}) "></i> &nbsp
													<i class="fa fa-pencil" onclick="editar(${u.id }) "></i>
												</td>
												<td>${u.matricula }</td>
												<c:set var = "index" value = "0"/>
												<c:forEach items="${u.contrato }" var="c">
													<c:if test="${c.ativo }">
														<c:set var = "index" value = "1"/>
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
												<c:if test="${index < 1}">
													<td style="color:blue">Renovar</td>
												</c:if>
												<c:if test="${index < 1}">
													<td style="color:blue">Renovar</td>
												</c:if>
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