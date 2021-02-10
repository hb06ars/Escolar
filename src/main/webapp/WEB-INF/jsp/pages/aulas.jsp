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
					<h2 class="panel-title" id="">Cadastrar Aula</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-2 form-group">
						Início:
						<input type="time" class="form-control" >
					</div>
					<div class="col-md-2 form-group">
						Fim:
						<input type="time" class="form-control" >
					</div>
					<div class="col-md-2 form-group">
						Semana:
						<select id="semana" name="semana" class="form-control">
							<option value="Segunda">Segunda</option>
							<option value="Terça">Terça</option>
							<option value="Quarta">Quarta</option>
							<option value="Quinta">Quinta</option>
							<option value="Sexta">Sexta</option>
							<option value="Sábado">Sábado</option>
							<option value="Domingo">Domingo</option>
						</select>
					</div>
					<div class="col-md-3 form-group">
						Aula:
						<select id="plano" name="plano.id" class="form-control">
							<option value="">Selecione a Aula</option>
							<c:forEach items="${planos }" var="p">
								<option value="${p.id }">Plano ${p.nome } (${p.descricao})</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-3 form-group">
						Professor:
						<select id="plano" name="plano.id" class="form-control">
							<option value="">Selecione o Professor</option>
							<c:forEach items="${planos }" var="p">
								<option value="${p.id }">Plano ${p.nome } (${p.descricao})</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-2 form-group" id="salvar">
						<input type="submit" class="btn btn-primary" onclick="acao('salvar')" value="Criar">
					</div>
					<div class="col-md-2 form-group" id="atualizar" style="display:none">
						<input type="submit" class="btn btn-primary" onclick="acao('atualizar')" value="Atualizar">
					</div>
					<div class="col-md-2 form-group" id="cancelar" style="display:none">
						<input type="button" class="btn btn-danger" onclick="cancelar()" value="Voltar">
					</div>
					<input type="hidden" id="acao" name="acao" value="salvar">
				</div>
			</div>
		</div>
	</div>
</form>
</div>



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
										<tr class="gradeX">
											<td>07:00 / 09:00</td>
											<td>Boxe (José)</td>
											<td>Balé (Ana)</td>
											<td>Jiu-Jitsu (Maria)</td>
											<td>Pilates (Jorge)</td>
											<td>Sertanejo (Carla)</td>
											<td>Boxe (Juca)</td>
											<td>Fechado</td>
										</tr>
										<tr class="gradeX">
											<td>09:00 / 10:00</td>
											<td>Balé (Ana)</td>
											<td>Sertanejo (Carla)</td>
											<td>Jiu-Jitsu (Maria)</td>
											<td>Pilates (Jorge)</td>
											<td>Boxe (José)</td>
											<td>Kung-Fu (Arnaldo)</td>
											<td>Fechado</td>
										</tr>
										<tr class="gradeX">
											<td>10:00 / 11:00</td>
											<td>Boxe (José)</td>
											<td>Balé (Ana)</td>
											<td>Jiu-Jitsu (Maria)</td>
											<td>Pilates (Jorge)</td>
											<td>Sertanejo (Carla)</td>
											<td>Boxe (Juca)</td>
											<td>Fechado</td>
										</tr>
										<tr class="gradeX">
											<td>11:00 / 12:00</td>
											<td>Balé (Ana)</td>
											<td>Sertanejo (Carla)</td>
											<td>Jiu-Jitsu (Maria)</td>
											<td>Pilates (Jorge)</td>
											<td>Boxe (José)</td>
											<td>Kung-Fu (Arnaldo)</td>
											<td>Fechado</td>
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