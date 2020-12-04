
<div id="alerta" style="display: none; position: fixed; top: 0; left: 20%; right: 20%; width: 60%; padding-top: 10px; z-index: 9999">
<div class="alert alert-info alert-dismissable" style="margin: 0 auto; box-shadow: 1px 1px 5px black;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	<strong><i id="tituloMsg">Teste</i> </strong> <div id="corpoMsg">Teste</div>
</div>
</div>
<div id="alertaErro" style="display: none; position: fixed; top: 0; left: 20%; right: 20%; width: 60%; padding-top: 10px; z-index: 9999">
<div class="alert alert-danger alert-dismissable" style="margin: 0 auto; box-shadow: 1px 1px 5px black;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	<strong><i id="tituloMsgErro">Teste</i> </strong> <div id="corpoMsgErro">Teste</div>
</div>
</div>


<script type="text/javascript">
$("#alerta").hide();
$("#alertaErro").hide();
function mensagem(titulo, corpo){
	document.getElementById("alerta").style.display = "block";
	document.getElementById("tituloMsg").innerHTML = titulo;
	document.getElementById("corpoMsg").innerHTML = corpo;
	$("#alerta").fadeIn(0);
	setTimeout(function() {
	$('#alerta').fadeOut(500, function(){});
	$(this).remove();
	}, 2000);
}
function mensagemErro(titulo, corpo){
	document.getElementById("alertaErro").style.display = "block";
	document.getElementById("tituloMsgErro").innerHTML = titulo;
	document.getElementById("corpoMsgErro").innerHTML = corpo;
	$("#alertaErro").fadeIn(0);
	setTimeout(function() {
	$('#alertaErro').fadeOut(500, function(){});
	$(this).remove();
	}, 2000);
}
</script>
