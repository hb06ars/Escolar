<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<!DOCTYPE html>
<html>

<!-- MENSAGENS -->
<jsp:include page="mensagens.jsp" />
<!-- MENSAGENS -->

<body onload="iniciando()">

<!--  ATUALIZAR PÁGINA  -->
<c:if test="${atualizarPagina != null }">
	<script>window.location.href='${atualizarPagina}';</script>
</c:if>
<!--  FIM ATUALIZAR PÁGINA  -->

<jsp:include page="css/cssVendas.jsp" />

<script>
function expandir(campo, quantidade){
	var ativado = document.getElementsByClassName(campo)[0].style.display;
	if( ativado == 'none' ){
		document.getElementById("pastaFechada").style.display="none";
		document.getElementById("pastaAberta").style.display="block";
		var i = 0;
		var x = document.getElementsByClassName(campo);
		for(i; i < quantidade; i++){
			x[i].style.display = "block";
		}
	} else{
		document.getElementById("pastaFechada").style.display="block";
		document.getElementById("pastaAberta").style.display="none";
		var i = 0;
		var x = document.getElementsByClassName(campo);
		for(i; i < quantidade; i++){
			x[i].style.display = "none";
		}
	}
	
	
}
</script>

<head>
	<meta charset="utf-8">
    <meta http-equiv="cleartype" content="on">
    <meta name="MobileOptimized" content="320">
    <meta name="HandheldFriendly" content="True">
    <meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="mobile-web-app-capable" content="yes">
	<link rel="shortcut icon" href="https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Flivro.png?alt=media&token=50641a98-ee5c-461b-9151-8ce97810b749" type="image/ico">
    <title>Sistema Escolar</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <style>@import "https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700"; body { font-family: 'Poppins', sans-serif; background: #fafafa; }  p { font-family: 'Poppins', sans-serif; font-size: 1.1em; font-weight: 300; line-height: 1.7em; color: #999; }  a, a:hover, a:focus { color: inherit; text-decoration: none; transition: all 0.3s; }  .navbar { padding: 15px 10px; background: #fff; border: none; border-radius: 0; margin-bottom: 40px; box-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1); }  .navbar-btn { box-shadow: none; outline: none !important; border: none; }  .line { width: 100%; height: 1px; border-bottom: 1px dashed #ddd; margin: 40px 0; }  /* --------------------------------------------------- SIDEBAR STYLE ----------------------------------------------------- */  .wrapper { display: flex; width: 100%; align-items: stretch; }  #sidebar { min-width: 250px; max-width: 250px; background: #6A90AB; color: #fff; transition: all 0.3s; margin-left: -250px;}  #sidebar.active { margin-left: 0px; }  #sidebar .sidebar-header { padding: 20px; background: #7FA4BF; }  #sidebar ul.components { padding: 20px 0; border-bottom: 1px solid #47748b; }  #sidebar ul p { color: #fff; padding: 10px; }  #sidebar ul li a { padding: 10px; font-size: 1.1em; display: block; }  #sidebar ul li a:hover { color: #6A90AB; background: #fff; }  #sidebar ul li.active>a, a[aria-expanded="true"] { color: #fff; background: #7FA4BF; }  a[data-toggle="collapse"] { position: relative; }  .dropdown-toggle::after { display: block; position: absolute; top: 50%; right: 20px; transform: translateY(-50%); }  ul ul a { font-size: 0.9em !important; padding-left: 30px !important; background: #7FA4BF; }  ul.CTAs { padding: 20px; }  ul.CTAs a { text-align: center; font-size: 0.9em !important; display: block; border-radius: 5px; margin-bottom: 5px; }  a.download { background: #fff; color: #6A90AB; }  a.article, a.article:hover { background: #7FA4BF !important; color: #fff !important; }  /* --------------------------------------------------- CONTENT STYLE ----------------------------------------------------- */  #content { width: 100%; padding: 20px; min-height: 100vh; transition: all 0.3s; }  /* --------------------------------------------------- MEDIAQUERIES ----------------------------------------------------- */  @media (max-width: 768px) { #sidebar {     margin-left: -250px; } #sidebar.active {     margin-left: 0; } #sidebarCollapse span {     display: none; } }</style>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
    <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
    
    		
</head>

<!-- SCRIPTS -->
<jsp:include page="javaScript.jsp" />
<!-- SCRIPTS -->

<!-- MENSAGENS -->
<jsp:include page="mensagens.jsp" />
<!-- MENSAGENS -->

<body onload="iniciando()">

<!-- MODAIS -->
<jsp:include page="modal.jsp" />
<!-- MODAIS -->

<!--  ATUALIZAR PÁGINA  -->
<c:if test="${atualizarPagina != null }">
	<script>window.location.href='/${atualizarPagina}';</script>
</c:if>
<!--  FIM ATUALIZAR PÁGINA  -->

    <div class="wrapper" style="overflow: auto; background-size:cover; background-image: url('https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Ffundo.jpg?alt=media&token=e3e625e5-6148-45d1-b3ef-197c4303f06c'); background-repeat:repeat; ">
        <nav id="sidebar">
            <div class="sidebar-header">
                <a href="/home"> <i style="cursor: pointer;" ><img width="40" height="40" src="https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Flivro.png?alt=media&token=50641a98-ee5c-461b-9151-8ce97810b749"></img> &nbsp Sistema Escolar</i></a>
            </div>

            <ul class="list-unstyled components">
                <li>
                	
                    <a style="align:center" href="#menuVendas" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"> <i style="align:center" class="fas fa-clipboard" style="font-size:24px"></i>&nbsp Horário</a>
                    <ul class="collapse list-unstyled" id="menuVendas">
                        <li><a href="/horarios"> <i style="align:center" class="fas fa-clock" style="font-size:24px"></i>&nbsp Horário Atual</a></li>
                        <li><a href="/presenca"> <i style="align:center" class="fas fa-edit" style="font-size:24px"></i>&nbsp Presença</a></li>
                        <c:if test="${usuarioSessao.perfil.professor}">
							<li><a href="/meusHorarios"> <i style="align:center" class="fas fa-user-alt" style="font-size:24px"></i>&nbsp Meus Horários</a></li>
                        </c:if>
                    </ul>
                </li>
                
                <li>
                	<a style="align:center" href="#menuCadastro" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle"> <i style="align:center" class="fas fa-cog" style="font-size:24px"></i>&nbsp Registro</a>
                    <ul class="collapse list-unstyled" id="menuCadastro">
                        <li><a href="/funcionarios"> <i style="align:center" class="fas fa-user-friends" style="font-size:24px"></i>&nbsp Funcionários</a></li>
                        <li><a href="/alunos"> <i style="align:center" class="fas fa-user-graduate" style="font-size:24px"></i>&nbsp Alunos</a></li>
                        <li><a href="/recados"> <i style="align:center" class="fas fa-bullhorn" style="font-size:24px"></i>&nbsp Recados</a></li>
                        <li><a href="/periodos"> <i style="align:center" class="fas fa-stopwatch" style="font-size:24px"></i>&nbsp Períodos</a></li>
                        <li><a href="/cadHorarios"> <i style="align:center" class="fas fa-calendar-check" style="font-size:24px"></i>&nbsp Cadastro Horário</a></li>
                    </ul>
                </li>
                <li>
                    <a onclick="modalContato()" style="cursor: pointer;"> <i href="#" style="align:center" class="fas fa-phone" style="font-size:24px"></i> &nbsp Contato</a>
                </li>
                <li>
                    <a onclick="modalAutor()" style="cursor: pointer;"> <i style="align:center" class="fas fa-id-card" style="font-size:24px"></i> &nbsp Autor</a>
                </li>
                <li>
                    <a href="/deslogar" style="cursor: pointer;"> <i style="align:center" class="fas fa-power-off" style="font-size:24px"></i> &nbsp Deslogar</a>
                </li>
            </ul>
			
            <ul class="list-unstyled">
                <li>
	                <a href="https://www.facebook.com"> <img src="https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Fface.png?alt=media&token=f244229d-cb1b-44ca-8bee-dcdfc96840f5" width="35" height="35"></img>&nbsp Facebook</a>
                
	                <a href="https://www.instagram.com"> <img src="https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Finstagram.png?alt=media&token=9ac6b88f-4a79-4ea4-a06c-b05e168834df" width="35" height="35"></img>&nbsp Instagram</a>
	                
	                <a href="https://wa.me/5511989376271"> <img src="https://firebasestorage.googleapis.com/v0/b/sistema-202a2.appspot.com/o/Imagens%2Fwhatsapp.png?alt=media&token=dfee5cfa-b5bc-4a86-95bb-c13d3b2f1bf8" width="35" height="35"></img>&nbsp WhatsApp</a>
                </li>
            </ul>
       
        </nav>

        <div id="content">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <button type="button" id="sidebarCollapse" class="btn btn-info">
                        <i class="fas fa-align-left"></i>
                        <span>Menu</span>
                    </button>
                </div>
            </nav>
            