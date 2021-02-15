<script type="text/javascript" src="https://code.jquery.com/jquery-1.7.2.min.js"></script> 

<script>
$(function(){
	$("#tabela input").keyup(function(){		

		var index = $(this).parent().index();
		var nth = "#tabela td:nth-child("+(index+1).toString()+")";
		var valor = $(this).val().toUpperCase();
		$("#tabela tbody tr").show();
		$(nth).each(function(){
			if($(this).text().toUpperCase().indexOf(valor) < 0){
				$(this).parent().hide();
			}
		});
	});

	$("#tabela input").blur(function(){
		$(this).val("");
	});	
});
</script>