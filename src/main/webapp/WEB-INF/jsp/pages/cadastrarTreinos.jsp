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
var treinoA = 'Treino A:',
	treinoB = 'Treino B:',
	treinoC = 'Treino C:',
	treinoD = 'Treino D:',
	treinoE = 'Treino E:',
	treinoF = 'Treino F:',
	treinoG = 'Treino G:';

function habilitar(){
	document.getElementById("pesqForm").submit();
}

function adicionar(){
	var letra = document.getElementById("tipoOrdem").value;
	document.getElementById("treino"+letra).style.display = "block";
	var conteudo = "";
	if(letra == 0){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoA = treinoA+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoA;
		document.getElementById("vlrTreinoA").value = treinoA;
	}
	if(letra == 1){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoB = treinoB+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoB;
		document.getElementById("vlrTreinoB").value = treinoB;
	}
	if(letra == 2){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoC = treinoC+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoC;
		document.getElementById("vlrTreinoC").value = treinoC;
	}
	if(letra == 3){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoD = treinoD+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoD;
		document.getElementById("vlrTreinoD").value = treinoD;
	}
	if(letra == 4){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoE = treinoE+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoE;
		document.getElementById("vlrTreinoE").value = treinoE;
	}
	if(letra == 5){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoF = treinoF+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoF;
		document.getElementById("vlrTreinoF").value = treinoF;
	}
	if(letra == 6){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoG = treinoG+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoG;
		document.getElementById("vlrTreinoG").value = treinoG;
	}
	if(letra == 7){
		conteudo = document.getElementById("descricao").value;
		conteudo = conteudo + " [" + document.getElementById("repeticoes").value + "X";
		conteudo = conteudo + document.getElementById("series").value + "]";
		conteudo = conteudo + " - "+document.getElementById("descanso").value;
		treinoH = treinoH+"<br>"+conteudo;
		document.getElementById("treino"+letra).innerHTML = treinoH;
		document.getElementById("vlrTreinoH").value = treinoH;
	}
	
}


</script>



<!-- start: page -->
<div class="row">
<form action="/cadastrarTreinos" id="pesqForm" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Cadastrar Treinos</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-4 form-group">
						<div class="input-group">
							<span class="input-group-addon" onclick="habilitar()">
								<i class="fa fa-search"></i>
							</span>
							<input type="number" placeholder="Matr�cula" name="matricula" id="matricula" class="form-control" required>
						</div>
					</div>
					<div class="col-md-8 form-group">
						<input type="text" placeholder="Nome" name="nome" id="nome" class="form-control" required>
					</div>
					
					
					<div class="col-md-2 form-group">
						Treino:
						<select id="tipoOrdem" name="tipoOrdem" class="form-control">
							<option value="0">A</option>
							<option value="1">B</option>
							<option value="2">C</option>
							<option value="3">D</option>
							<option value="4">E</option>
							<option value="5">F</option>
							<option value="6">G</option>
						</select>
					</div>
					<div class="col-md-4 form-group">
						Exerc�cio:
						<input type="text" placeholder="Exerc�cio" name="descricao" id="descricao" class="form-control" required>
					</div>
					<div class="col-md-2 form-group">
						S�ries:
						<input type="number" placeholder="1" min="1" name="series" id="series" value="10" class="form-control" required>
					</div>
					<div class="col-md-2 form-group">
						Repeti��es:
						<input type="number" placeholder="1" min="1" name="repeticoes" id="repeticoes" value="4" class="form-control" required>
					</div>
					
					<div class="col-md-2 form-group">
						Descanso:
						<input type="text" placeholder="00s" maxlength="3" name="descanso" id="descanso" value="30s" class="form-control" required>
					</div>
					

					<c:if test="${usuario != null }">
						<div class="col-md-2 form-group">
							<a class="btn btn-primary" onclick="adicionar()" id="btAdd" style="display:none" >Adicionar</a>
						</div>
						<div class="col-md-2 form-group">
							<a class="btn btn-danger" onclick="finalizar()" id="btFim" style="display:none" >Finalizar</a>
						</div>
						<div class="col-md-12 form-group">
						</div>
					</c:if>
					
					<div class="col-md-4 form-group" id="treino0" style="display:none">
						Treino A
					</div>
					<div class="col-md-4 form-group" id="treino1" style="display:none">
						Treino B
					</div>
					<div class="col-md-4 form-group" id="treino2" style="display:none">
						Treino C
					</div>
					<div class="col-md-4 form-group" id="treino3" style="display:none">
						Treino D
					</div>
					<div class="col-md-4 form-group" id="treino4" style="display:none">
						Treino E
					</div>
					<div class="col-md-4 form-group" id="treino5" style="display:none">
						Treino F
					</div>
					<div class="col-md-4 form-group" id="treino6" style="display:none">
						Treino G
					</div>
					
					
					<input type="hidden" id="vlrTreinoA" name="vlrTreinoA">
					<input type="hidden" id="vlrTreinoB" name="vlrTreinoB">
					<input type="hidden" id="vlrTreinoC" name="vlrTreinoC">
					<input type="hidden" id="vlrTreinoD" name="vlrTreinoD">
					<input type="hidden" id="vlrTreinoE" name="vlrTreinoE">
					<input type="hidden" id="vlrTreinoF" name="vlrTreinoF">
					<input type="hidden" id="vlrTreinoG" name="vlrTreinoG">
					
					
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
						
								<h2 class="panel-title">Registro dos Treinos</h2>
							</header>
							<div class="panel-body">
								<table class="table table-bordered table-striped mb-none" id="datatable-default" style="overflow:auto">
									<thead>
										<tr>
											<th>Editar</th>
											<th>Matr�cula</th>
											<th>Nome</th>
											<th>Treino</th>
											<th>Ordem</th>
											<th>S�ries</th>
											<th>Repeti��es</th>
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
												<td>${t.tipoOrdem }</td>
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