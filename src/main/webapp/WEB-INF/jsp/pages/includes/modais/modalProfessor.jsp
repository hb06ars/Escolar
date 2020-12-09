<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalProfessor(professor, nomeProfessor, horarioId, telefone, novaSala, novaTurma, horarioDaAula){
	$("#modalProfessor").modal().show();
	document.getElementById("professorAtualConfirma").value = professor;
	
	telefone = telefone.replace('-','');
	telefone = telefone.replace('(','');
	telefone = telefone.replace(')','');
	telefone = "55"+telefone;
	if(telefone != null && telefone != ''){
		var div = document.getElementById("telefone");
		var texto = "Olá tudo bem?\nO professor "+nomeProfessor+" não poderá comparecer na aula.\nFavor substituí-lo:\nSala: " + novaSala + "\nTurma: " + novaTurma + "\nHorário: "+horarioDaAula+"hs\n\nMuito obrigado!";
		var frase = window.encodeURIComponent(texto);
		var valor = "<a href='https://api.whatsapp.com/send?phone="+telefone+"&text="+frase+"'> <img src='https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Fwhatsapp.png?alt=media&token=dfee5cfa-b5bc-4a86-95bb-c13d3b2f1bf8' width='35' height='35'></img>&nbsp Mandar mensagem </a>";
		document.getElementById("telefone").innerHTML = valor;
	}
	
	document.getElementById("horarioId").value = horarioId;
	document.getElementById("professorAtual").value = professor;
	document.getElementById("nomeProfessorAtual").value = nomeProfessor;
	document.getElementById("pergunta").innerHTML = "Deseja substituir o Professor(a) "+nomeProfessor+"?";
}

function janelaSubstituto(valor){
	var professorAtual = document.getElementById("nomeProfessorAtual").value;
	if(valor == 'sim'){
		document.getElementById("telaSubstituto").style.display = "block";
		document.getElementById("btSubstituir").style.display = "block";
		document.getElementById("pergunta").innerHTML = "Deseja substituir o Professor(a) "+professorAtual+"?";
	} else{
		document.getElementById("btSubstituir").style.display = "none";
		document.getElementById("telaSubstituto").style.display = "none";
		document.getElementById("pergunta").innerHTML = "Deseja substituir o Professor(a) "+professorAtual+"?";
	}	
}

function substituir(){
	var professorAtual = document.getElementById("professorAtual").value;
	var substituto = document.getElementById("substituto").value;
	var todasAulas = document.getElementById("somenteSim").checked;
	if(document.getElementById("somenteSim").checked){
		todasAulas = 'nao';
	} else{
		todasAulas = 'todas';
	}
	document.getElementById("professorAtual").value = professorAtual;
	document.getElementById("professorSubstituto").value = substituto;
	document.getElementById("todasAulas").value = todasAulas;
	
	document.getElementById("substituirForm").submit();
}

</script>



<form action="<c:url value='/professor/confirmarPresenca'/>" id="form" method="post" >
<div class="modal fade" id="modalProfessor" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Selecione a opção:</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
		<div class="input-group mb-3">
  			<div id="pergunta">
  			Deseja Substituir o professor?
  			</div>
  			<div class="input-group mb-3">
	  			<div>
	  				<input type="radio" id="sim" name="substituir" value="sim" onclick="janelaSubstituto('sim')">
					<label for="sim">Sim</label>
				</div>
				<div>&nbsp&nbsp&nbsp</div>
				<div>
					<input type="radio" id="nao" name="substituir" value="nao" onclick="janelaSubstituto('nao')" checked="checked">
					<label for="nao">Não</label>
				</div>
			</div>
			<div class="input-group mb-3" id="telaSubstituto" style="display:none">
  			<div>
  			Escolha o substituto:
  			</div>
  			<div class="input-group mb-3">
  				<select style="height=:100%; width=:100%" class="form-control" id="substituto" name="substituto" required aria-describedby="inputGroup-sizing-default">
						<c:forEach items="${professores }" var="p">
							<option value="${p.id }" >${p.nome }</option>
						</c:forEach>
				</select>
			</div>
			<div>
  				Somente essa aula?:
  			</div>
			<div class="input-group mb-3">
	  			<div>
	  				<input type="radio" id="somenteSim" name="somente" value="sim" checked="checked">
					<label for="sim">Somente essa aula</label>
				</div>
				<div>&nbsp&nbsp&nbsp</div>
				<div>
					<input type="radio" id="somenteNao" name="somente" value="nao" >
					<label for="nao">Todas as outras aulas</label>
				</div>
			</div>
		</div>
		<div class="input-group mb-3">
			<div id="telefone">  
				
			</div>
				
		</div>
		
		</div>
		
      </div>
      <div class="modal-footer">
      	<input type="hidden" id="professorAtualConfirma" name="professorAtualConfirma">
      	<input type="hidden" id="nomeProfessorAtual" name="nomeProfessorAtual">
      	<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <botao type="button" id="btSubstituir" class="btn btn-secondary" onclick="substituir()" data-dismiss="modal" style="display:none">Substituir</botao>
        <input type="submit" name="submit" class="btn btn-primary" value="Confirmar presença">
      </div>
    </div>
  </div>
</div>
</form>


<form action="<c:url value='/professor/substituir'/>" id="substituirForm" method="post" >
	<input type="hidden" id="professorAtual" name="professorAtual">
	<input type="hidden" id="professorSubstituto" name="professorSubstituto">
	<input type="hidden" id="todasAulas" name="todasAulas">
	<input type="hidden" id="horarioId" name="horarioId">
</form>
