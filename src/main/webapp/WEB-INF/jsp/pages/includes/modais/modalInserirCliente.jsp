<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalInserirCliente(){
	$("#modalInserirCliente").modal().show();
}

function confirmaCliente(){
	clienteTel = document.getElementById("telefone").value;
	observ = document.getElementById("observ").value;
	validarCliente(clienteTel,observ);
}

</script>
<div class="modal fade" id="modalInserirCliente" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Inserir o endereço do Cliente na nota</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      	<div class="input-group mb-3">
  			<div>
  			Telefone:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="telefone" id="telefone" class="form-control" onkeypress="mascaraTel(this)" onkeyup="somenteNumeros(this);" placeholder="(00)00000-0000" autocomplete="off" maxlength="14" minlength="14" >
			</div>
		</div>
		<div class="input-group mb-3">
  			<div>
  			Insira uma observação:
  			</div>
  			<div class="input-group mb-3">
  				<input type="text" name="observ" id="observ" class="form-control" placeholder="Insira uma observação" autocomplete="off" >
			</div>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="deletar" onclick="confirmaCliente()" data-dismiss="modal">Sim</button>
        <button type="button" class="btn btn-primary" id="deletar" data-dismiss="modal">Não</button>
      </div>
    </div>
  </div>
</div>
<!--  DELETAR PERFIL -->
