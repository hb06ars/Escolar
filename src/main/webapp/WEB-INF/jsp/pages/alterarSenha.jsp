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

function acao(){
	document.getElementById("acao").value = 1;
	var novaSenha = document.getElementById("novaSenha").value;
	var confirmaSenha = document.getElementById("confirmaSenha").value;
	if(novaSenha == '' || confirmaSenha == ''){
		document.getElementById("textoValida").innerHTML = "Preencha todos os campos.";
	} else{
		if(novaSenha == confirmaSenha){
			document.getElementById("formAlterarSenha").submit();
		} else{
			document.getElementById("textoValida").innerHTML = "Confirmação da nova senha não confere.";
		}
	}
	
	

}

</script>


<!-- start: page -->
<div class="row">
<form action="/alterarSenha" id="formAlterarSenha" method="post" accept-charset="utf-8">
	<div class="col-md-12">
		<div data-collapsed="0" class="panel">
			<div class="panel-heading">
				<div class="panel-title">
					<div class="panel-actions">
						<a href="#" class="panel-action panel-action-toggle" data-panel-toggle></a>
						<a href="#" class="panel-action panel-action-dismiss" data-panel-dismiss></a>
					</div>
					<h2 class="panel-title" id="">Alterar a Senha</h2>
				</div>
			</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-md-3 form-group">
						<input type="text" placeholder="Matrícula" name="matricula" id="matricula" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<input type="password" placeholder="Senha Atual" name="senha" id="senha" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<input type="password" placeholder="Nova Senha" name="novaSenha" id="novaSenha" class="form-control" required>
					</div>
					<div class="col-md-3 form-group">
						<input type="password" placeholder="Repetir nova Senha" name="confirmaSenha" id="confirmaSenha" class="form-control" required>
					</div>
					<div class="col-md-2 form-group" id="salvar">
						<a class="btn btn-primary" onclick="acao()" >Alterar</a>
					</div>
					<div class="col-md-12 form-group">
						<i style="color:red" id="textoValida">${msg }</i>
					</div>
					<div class="col-md-12 form-group">
						<i style="color:green" id="textoValida">${msgOk }</i>
					</div>
					<input type="hidden" id="acao" name="acao" value="0">
				</div>
			</div>
		</div>
	</div>
</form>
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