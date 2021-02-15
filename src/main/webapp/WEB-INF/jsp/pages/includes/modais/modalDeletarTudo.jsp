<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>





<!--  EDITAR PERFIL -->
<script>
function limparTudo(tabela){
	document.getElementById("msgExc").innerHTML = 'Deseja realmente excluir todos os registros da tabela '+tabela+'?';
	if(tabela == 'ALUNOS'){
		document.getElementById("tabelaUsadaDeletar").value = 'alunos';
	}
	if(tabela == 'FUNCIONÁRIOS'){
		document.getElementById("tabelaUsadaDeletar").value = 'funcionarios';
	}
	if(tabela == 'HORÁRIOS'){
		document.getElementById("tabelaUsadaDeletar").value = 'horarios';
	}
	$("#modalLimparTudo").modal().show();
}
</script>


<form action="<c:url value='/adm/limparTudo'/>" id="formExc" method="post" enctype='multipart/form-data' accept-charset="utf-8" >
<div class="modal fade" id="modalLimparTudo" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Limpando todos os Registros</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
      	<div class="input-group mb-3">
  			<div style="color:red">
  			<h2>ATENÇÃO!</h2>
  			</div>
			<div class="input-group mb-3" id="msgExc">
				
			</div>
		</div>
      </div>
      <div class="modal-footer">
      	 <input type="hidden" name="tabelaUsadaDeletar" id="tabelaUsadaDeletar">
      	 <input type="submit" name="submit" class="btn btn-danger" value="Sim">
        <button type="button" class="btn btn-primary" data-dismiss="modal">Não</button>
      </div>
    </div>
  </div>
</div>
</form>
<!--  EDITAR PERFIL -->



