<!-- HEADER -->
<jsp:include page="includes/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!-- HEADER -->
<!-- MODAL -->
<!-- TABELAS COM FILTRO -->
<jsp:include page="includes/jquery/filtro.jsp" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> 
<script type="text/javascript" src="includes/jquery/script.js"></script>
<!-- TABELAS COM FILTRO -->
<!-- DOWNLOAD EXCEL -->
<jsp:include page="includes/jquery/excel/downloadExcel.jsp" />
<!-- DOWNLOAD EXCEL -->
<!-- UPLOAD EXCEL -->
<jsp:include page="includes/modais/modalUploadExcel.jsp" />
<!-- UPLOAD EXCEL -->

<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">

<script>
function redirecionar(pagina){
	window.location.href = "/"+pagina;
}
</script>


<div class="row aumentar" align="center" style="margin: auto;cursor:pointer" onclick="redirecionar('horarios')">
	<div class="card text-white ml-3 mt-3" style="min-height: 18rem; min-width: 18rem; background-color:#6196AC;opacity: 0.8">
	  <div class="card-header"><h5>Horário</h5></div>
	  <div class="card-body">
	  	<h5 class="card-title" style="width:100%; height:100%; background: no-repeat center; background-size: cover;background-image: url('https://image.freepik.com/vetores-gratis/despertador_16734-153.jpg"></h5>
	  </div>
	</div>
</div>

<c:if test="${!usuarioSessao.perfil.professor}">
	<div class="row" align="center" style="margin: auto;cursor:pointer" onclick="redirecionar('presenca')">
		<div class="card text-white ml-3 mt-3" style="min-height: 18rem; min-width: 18rem; background-color:#6196AC;opacity: 0.8">
		  <div class="card-header"><h5>Presença</h5></div>
		  <div class="card-body">
		  	<h5 class="card-title" style="width:100%; height:100%; background: no-repeat center; background-size: cover;background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTacVAJcO6vzn-9SDB7Ps7xVjYgLA4eYHgWsQ&usqp=CAU')"></h5>
		  </div>
		</div>
	</div>
</c:if>
<c:if test="${usuarioSessao.perfil.professor}">
	<div class="row" align="center" style="margin: auto;cursor:pointer" onclick="redirecionar('meusHorarios')">
		<div class="card text-white ml-3 mt-3" style="min-height: 18rem; min-width: 18rem; background-color:#6196AC;opacity: 0.8">
		  <div class="card-header"><h5>Minhas Aulas</h5></div>
		  <div class="card-body">
		  	<h5 class="card-title" style="width:100%; height:100%; background: no-repeat center; background-size: cover;background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTacVAJcO6vzn-9SDB7Ps7xVjYgLA4eYHgWsQ&usqp=CAU')"></h5>
		  </div>
		</div>
	</div>
</c:if>

<div class="row" align="center" style="margin: auto;cursor:pointer" onclick="redirecionar('alunos')">
	<div class="card text-white ml-3 mt-3" style="min-height: 18rem; min-width: 18rem; background-color:#6196AC;opacity: 0.8">
	  <div class="card-header"><h5>Alunos</h5></div>
	  <div class="card-body">
	  	<h5 class="card-title" style="width:100%; height:100%; background: no-repeat center; background-size: cover;background-image: url('https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQR-228iAHIh_-Ul8J-C8hLjP5f_2WrUWVZA&usqp=CAU')"></h5>
	  </div>
	</div>
</div>

<div class="row" align="center" style="margin: auto;cursor:pointer" onclick="redirecionar('recados')">
	<div class="card text-white ml-3 mt-3" style="min-height: 18rem; min-width: 18rem; background-color:#6196AC;opacity: 0.8">
	  <div class="card-header"><h5>Recados</h5></div>
	  <div class="card-body">
	  	<h5 class="card-title" style="width:100%; height:100%; background: no-repeat center; background-size: cover;background-image: url('https://superawesomevectors.com/wp-content/uploads/2015/01/open-letter-flat-icon.jpg')"></h5>
	  </div>
	</div>
</div>

</div>
<br>


<!-- FOOTER -->
<jsp:include page="includes/footer.jsp" />
<!-- FOOTER -->

