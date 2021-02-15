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


<script>

function habilitar(){
	document.getElementById("pesqForm").submit();
}

function acao(valor){
	if(valor == 'salvar'){
		document.getElementById("acao").value="1";
		document.getElementById("pesqForm").submit();
	}
	if(valor == 'atualizar'){
		document.getElementById("acao").value="2";
		document.getElementById("pesqForm").submit();
	}
	
}

function desfazer(){
	document.getElementById("atualizar").style.display="none";
	document.getElementById("gorduraCorporal").value="";
	document.getElementById("gorduraTrans").value="";
	document.getElementById("peso").value="";
	document.getElementById("altura").value="";
	document.getElementById("abdomen").value="";
	document.getElementById("biceps").value="";
	document.getElementById("triceps").value="";
	document.getElementById("costas").value="";
	document.getElementById("perna").value="";
	document.getElementById("inicio").value="";
	document.getElementById("fim").value="";
}


function editar(id){
	document.getElementById("atualizar").style.display="block";
	<c:forEach items="${avaliacao }" var="a" varStatus="as">
		if(${a.id} == id){
			document.getElementById("matricula").value = '${a.codigo}';
			document.getElementById("nome").value = '${a.aluno.nome}';
			document.getElementById("celular").value = '${a.aluno.celular}';
			document.getElementById("observacoes").value = '${a.observacoes}';
			document.getElementById("avaliador").value = '${a.avaliador.nome}';
			document.getElementById("inicio").value = '${a.inicio}';
			document.getElementById("fim").value = '${a.fim}';
		}
	</c:forEach>

}

</script>



<!-- start: page -->
<div class="row">
<form action="/cadastrarAvaliacao" id="pesqForm" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Cadastrar Avaliações</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon" onclick="habilitar()">
								<i class="fa fa-search"></i>
							</span>
							<input type="number" placeholder="Matrícula" name="matricula" id="matricula" class="form-control" value="${usuarioAlterar.matricula }" required>
						</div>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" value="${usuarioAlterar.nome }" disabled>
					</div>
					
					
					<div class="col-md-3 form-group">
						Início:
						<input type="date" name="inicio_avaliacao" id="inicio" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						Fim:
						<input type="date" name="fim_avaliacao" id="fim" class="form-control" required>
					</div>
					<div class="col-md-6 form-group">
					</div>
					<div class="col-md-12 form-group">
					</div>
					<div class="col-md-2 form-group">
						Gordura Corporal:
						<input type="number" step="0.010" name="gorduraCorporal" id="gorduraCorporal" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Gordura Trans:
						<input type="number" step="0.010" name="gorduraTrans" id="gorduraTrans" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Peso (kg):
						<input type="number" step="0.010" name="peso" id="peso" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Altura (cm):
						<input type="number" step="0.010" name="altura" id="altura" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Abdomen:
						<input type="number" step="0.010" name="abdominal" id="abdominal" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Bíceps:
						<input type="number" step="0.010" name="biceps" id="biceps" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Tríceps:
						<input type="number" step="0.010" name="triceps" id="triceps" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Costas:
						<input type="number" step="0.010" name="costas" id="costas" class="form-control" min="0">
					</div>
					<div class="col-md-2 form-group">
						Perna:
						<input type="number" step="0.010" name="perna" id="perna" class="form-control" min="0">
					</div>
					<div class="col-md-12 form-group">
						Observações:
						<textArea name="observacoes" id="observacoes" class="form-control"></textArea>
					</div>
					
					<div class="col-md-12 form-group">
					</div>
					

					<c:if test="${matricula != null }">
						<div class="col-md-2 form-group">
							<a class="btn btn-primary" onclick="acao('salvar')" id="btAdd" style="display:block" >Salvar</a>
						</div>
						<div class="col-md-2 form-group" id="atualizar" style="display:none">
							<input type="submit" class="btn btn-warning" onclick="acao('atualizar')" value="Atualizar">
						</div>
						<div class="col-md-2 form-group">
							<a class="btn btn-danger" onclick="desfazer()" id="btDesfazer" style="display:block" >Cancelar</a>
						</div>
						<div class="col-md-12 form-group">
						</div>
					</c:if>
					
					
					<input type="hidden" id="acao" name="acao" value="0">
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
						
								<h2 class="panel-title">Avaliação</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Edição</th>
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
												<td>
													<i class="fa fa-trash" onclick="modalDeletar('avaliacao', ${a.id}) "></i> &nbsp
													<i class="fa fa-pencil" onclick="editar(${a.id }) "></i>
												</td>
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