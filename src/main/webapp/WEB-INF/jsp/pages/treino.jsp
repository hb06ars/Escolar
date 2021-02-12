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
function confirmar(id){
	var cor = document.getElementById(id).style.backgroundColor;
	if(cor == 'rgb(247, 129, 129)'){
		document.getElementById(id).style.backgroundColor = '#58ACFA';
	} else {
		document.getElementById(id).style.backgroundColor = '#F78181';
	}
}

function pular(){
	document.getElementById("proximo").value="1";
	document.getElementById("anterior").value="0";
	document.getElementById("formTreino").submit();
}
function voltar(){
	document.getElementById("proximo").value="0";
	document.getElementById("anterior").value="1";
	document.getElementById("formTreino").submit();
}
</script>


<!-- start: page -->

<div class="row" id="treinoA" style="display:block">
	<div class="col-xl-1 col-lg-12">
		<section class="panel panel-transparent">
			<div class="panel-body">
				<section class="panel panel-group">
					<header class="panel-heading bg-primary">

						<div class="widget-profile-info">
							<div class="profile-picture">
								<img src="${usuario.pathImagem }">
							</div>
							<div class="profile-info">
								<h4 class="name text-weight-semibold">${usuario.nome }</h4>
								<c:set var = "ultimoDiaExecutado" value = "0"/>
								<h5 class="role">Treino A</h5>
								<div class="profile-footer">
									<a href="#">${usuario.perfil.nome }</a>
								</div>
							</div>
						</div>

					</header>
					<div id="accordion">
						<div class="panel panel-accordion panel-accordion-first">
							<div id="collapse1One" class="accordion-body collapse in">
								<div class="panel-body">
									<ul class="list-group">
										<c:forEach items="${treinos}" var="t" varStatus="loop">
											<c:if test="${t.tipoOrdem == t.ultimoTreinoExecutado }">
												<li class="list-group-item" id="todoListItem${loop.index+1 }" onclick="confirmar('todoListItem${loop.index+1 }')" style="background-color:#F78181; color:#610B0B;">
													<label class="todo-label" for="todoListItem${loop.index+1 }"><span>${t.descricao } ( ${t.series }X${t.repeticoes } &nbsp&nbsp<i class="fa fa-refresh"></i>&nbsp&nbsp ${t.descanso } )</span></label>
												</li>
											</c:if>
										</c:forEach>
									</ul>
									<hr class="solid mt-sm mb-lg">
									<form action="/treino" id="formTreino" method="post" accept-charset="utf-8">
											<div class="form-group">
												<div class="col-sm-12">
													<div class="input-group mb-md">
														<div class="input-group-btn">
															<input type="hidden" name="proximo" id="proximo" value="0"/>
															<button class="btn btn-primary" onclick="voltar()" tabindex="-1">Anterior</button>
															<a style="cursor:default" class="btn" tabindex="-1">&nbsp</a>
															<input type="hidden" name="anterior" id="anterior" value="0"/>
															<button class="btn btn-primary" onclick="pular()" tabindex="-1">Pr�ximo</button>
														</div>
													</div>
												</div>
											</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</section>

			</div>
		</section>
	</div>
</div>

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