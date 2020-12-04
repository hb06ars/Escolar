<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script> 

<script>
function mascaraCep(mascaraCep){ 
    if(mascaraCep.value.length == 5)
        mascaraCep.value = mascaraCep.value + '-'; 
}


function mascaraTel(mascaraTel){ 
	if(mascaraTel.value.length == 1)
		mascaraTel.value = '(' + mascaraTel.value; 
    if(mascaraTel.value.length == 3)
    	mascaraTel.value = mascaraTel.value + ')'; 
	if(mascaraTel.value.length == 9)
		mascaraTel.value = mascaraTel.value + '-'; 
}


function somenteNumeros(num) {
        var er = /[^0-9)-(//]/;
        er.lastIndex = 0;
        var campo = num;
        if (er.test(campo.value)) {
          campo.value = "";
        }
}	





</script>