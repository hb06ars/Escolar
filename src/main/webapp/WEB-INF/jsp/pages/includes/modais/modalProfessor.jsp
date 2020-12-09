<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalProfessor(professor, nomeProfessor){
	$("#modalProfessor").modal().show();
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
	var professor = document.getElementById("professorAtual").value;
}

</script>



<form action="<c:url value='/professor/atualizar'/>" id="form" method="post" >
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
  				<select style="height=:100%; width=:100%" class="form-control" id="ativo" name="ativo" required aria-describedby="inputGroup-sizing-default">
						<option value="0" selected="selected">Aula vaga</option>
						<c:forEach items="${professores }" var="p">
							<option value="${p.id }" >${p.nome }</option>
						</c:forEach>
				</select>
			</div>
		</div>
		</div>
		
      </div>
      <div class="modal-footer">
      	<input type="hidden" id="professorAtual">
      	<input type="hidden" id="nomeProfessorAtual">
      	<button type="button" class="btn btn-danger" data-dismiss="modal">Cancelar</button>
        <botao type="button" id="btSubstituir" class="btn btn-secondary" onclick="substituir()" data-dismiss="modal" style="display:none">Substituir</botao>
        <input type="submit" name="submit" class="btn btn-primary" value="Confirmar presença">
      </div>
    </div>
  </div>
</div>
</form>