<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>


<!--  CONTATO -->
<script>
function modalContato(){
	$("#modalContato").modal().show();
}
</script>

<div class="modal fade" id="modalContato" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Contato</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Rua da Academia, 123 <br>
        CEP: 44444-240 <br>
        São Paulo - SP <br>
        Contato: (11)33333-3332 <br>
        Email: academia@teste.com <br><br>
        Funcionamento: <br>
        seg à sex - 06hs às 23hs <br>
        sáb - 09hs às 14hs <br>
        dom e feriados - fechado
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
<!--  FINAL CONTATO -->

