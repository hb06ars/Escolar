<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>



<!--  DELETAR PERFIL -->
<script>
function modalPesquisarVenda(vendaID){
	document.getElementById("pesquisarVendaID").value = vendaID;
	document.getElementById("formPesqVenda").submit();
}

function imprimir(){
	var divToPrint=document.getElementById("tabelaProdutos");
    newWin= window.open("");
    newWin.document.write(divToPrint.outerHTML);
    newWin.print();
    newWin.close();
}

function modalVendaPesquisada(){
	var inicio = '${vendaSelecionada}';
	if(inicio != ''){
		$("#modalPesquisarVenda").modal().show();	
	}
	var valores = new Array();
	<c:forEach var="v" items="${vendaSelecionada}">
		if("${vendaSelecionada.size()}" > 0){
			document.getElementById("notaPesq").innerHTML = "${v.pedido}";	
			var data = "${v.data}";
			data = data.substring(8, 10) + '/' + data.substring(5, 7) + '/' + data.substring(0, 4);
			document.getElementById("dataPesq").innerHTML = data;
			document.getElementById("funcPesq").innerHTML = "${v.funcionario.nome}";
			document.getElementById("totalPesq").innerHTML = "R\$${v.total}";			
			
			var table = document.getElementById("tabelaProdutos");
			var linhas = table.getElementsByTagName('tr').length;
			var row = table.insertRow(linhas-1);

			var cell0 = row.insertCell(0);
			var cell1 = row.insertCell(1);
			var cell2 = row.insertCell(2);
			var cell3 = row.insertCell(3);
			var cell4 = row.insertCell(4);
			
			
			cell0.innerHTML = "<td>${v.produtos.codigo}";
			cell1.innerHTML = "<td>${v.produtos.descricao}";
			cell2.innerHTML = "<td>R\$${v.precoUnit}";
			cell3.innerHTML = "<td>${v.quantidade}";
			cell4.innerHTML = "<td>R\$${v.subtotal}";
			
			
			
			}
		</c:forEach>
	}



</script>
<div class="modal fade" id="modalPesquisarVenda" tabindex="-1" role="dialog" aria-labelledby="TituloModalCentralizado" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="TituloModalCentralizado">Consultar Nota</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body input-group mb-3">
	     	<div class="input-group mb-3">
	     	
	  			<div class="input-group mb-3" style="overflow:auto">
	  				<table id="tabelaProdutos" class="table table-striped table-bordered table-sm">
	  				<tr>
	  					<td colspan="1">Nota:</td>
	  					<td colspan="4" id="notaPesq"> XXXX </td>
	  				<tr>
	  					<td>Data:</td>
	  					<td colspan="2">Funcionário:</td>
	  					<td colspan="2">Total:</td>
	  				</tr>
	  				<tr>
	  					<td id="dataPesq"> XXXX </td>
	  					<td colspan="2" id="funcPesq"> XXXX </td>
	  					<td colspan="2" id="totalPesq"> R$00.00 </td>
	  				</tr>
	  				<tr>
	  					<td colspan="5" style="background-color:#A4A4A4" align="center">Produtos</td>
	  				<tr>
	  				<tr>
	  					<td style="background-color:#F2F2F2">Código</td>
	  					<td style="background-color:#F2F2F2">Descrição</td>
	  					<td style="background-color:#F2F2F2">Preço Unit.</td>
	  					<td style="background-color:#F2F2F2">Qtd</td>
	  					<td style="background-color:#F2F2F2">SubTotal</td>
	  				<tr>
	  				
	  				
	  				</table>
				</div>
				
			</div>
		</div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="imprimir()" >Imprimir</button>
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>
<!--  DELETAR PERFIL -->
