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
</script>


<!-- start: page -->

<div class="row">
	<div class="col-xl-1 col-lg-12">
		<section class="panel panel-transparent">
			<div class="panel-body">
				<section class="panel panel-group">
					<header class="panel-heading bg-primary">

						<div class="widget-profile-info">
							<div class="profile-picture">
								<img src="https://scontent.fcgh11-1.fna.fbcdn.net/v/t1.0-9/56340109_2808450262713759_4215951610129416192_o.jpg?_nc_cat=109&ccb=2&_nc_sid=174925&_nc_eui2=AeELqnWcJisSfH0r9cd2L0J-Gw9Ge0-rDWUbD0Z7T6sNZRKf-BT3BkWoo5nxELS2i3wl_jrKSD87GxF83BKLQ1Oq&_nc_ohc=AY5GfwWvaBoAX_8IV7b&_nc_ht=scontent.fcgh11-1.fna&oh=ef51b6af81f364d04d9b4d06921dba66&oe=602E171F">
							</div>
							<div class="profile-info">
								<h4 class="name text-weight-semibold">${usuario.nome }</h4>
								<c:set var = "ultimoDiaExecutado" value = "0"/>
								<h5 class="role">Treino A ${treinos.size()}</h5>
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
										<li class="list-group-item" id="todoListItem1" onclick="confirmar('todoListItem1')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem1"><span>SUPINO RETO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem2" onclick="confirmar('todoListItem2')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem2"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem3" onclick="confirmar('todoListItem3')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem3"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem4" onclick="confirmar('todoListItem4')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem4"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem5" onclick="confirmar('todoListItem5')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem5"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem6" onclick="confirmar('todoListItem6')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem6"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem7" onclick="confirmar('todoListItem7')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem7"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
										<li class="list-group-item" id="todoListItem8" onclick="confirmar('todoListItem8')" style="background-color:#F78181; color:#610B0B;">
											<label class="todo-label" for="todoListItem8"><span>SUPINO INCLINADO (3X 10)</span></label>
										</li>
									</ul>
									<hr class="solid mt-sm mb-lg">
									<form method="get" class="form-horizontal form-bordered">
										<div class="form-group">
											<div class="col-sm-12">
												<div class="input-group mb-md">
													<div class="input-group-btn">
														<button type="button" class="btn btn-primary" tabindex="-1">Finalizar tudo</button>
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