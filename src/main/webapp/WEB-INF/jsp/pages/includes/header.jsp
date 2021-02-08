<!doctype html>
<html class="fixed">
	<head>

		<!-- Basic -->
		<meta charset="UTF-8">
		<title>Academia</title>
		<meta name="keywords" content="HTML5 Admin Template" />
		<meta name="description" content="Porto Admin - Responsive HTML5 Template">
		<meta name="author" content="okler.net">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="shortcut icon" href="assets/images/logo.png" type="image/ico">
		<!-- Mobile Metas -->
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<!-- Web Fonts  -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700,800|Shadows+Into+Light" rel="stylesheet" type="text/css">
		<!-- Vendor CSS -->
		<link rel="stylesheet" href="/assets/vendor/bootstrap/css/bootstrap.css" />
		<link rel="stylesheet" href="/assets/vendor/font-awesome/css/font-awesome.css" />
		<link rel="stylesheet" href="/assets/vendor/magnific-popup/magnific-popup.css" />
		<link rel="stylesheet" href="/assets/vendor/bootstrap-datepicker/css/datepicker3.css" />
		<!-- Specific Page Vendor CSS -->
		<link rel="stylesheet" href="/assets/vendor/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.css" />
		<link rel="stylesheet" href="/assets/vendor/bootstrap-multiselect/bootstrap-multiselect.css" />
		<link rel="stylesheet" href="/assets/vendor/morris/morris.css" />
		<!-- Theme CSS -->
		<link rel="stylesheet" href="/assets/stylesheets/theme.css" />
		<!-- Skin CSS -->
		<link rel="stylesheet" href="/assets/stylesheets/skins/default.css" />
		<!-- Theme Custom CSS -->
		<link rel="stylesheet" href="/assets/stylesheets/theme-custom.css">
		<!-- Loading -->
		<link rel="stylesheet" href="/assets/vendor/loading/loading.css" />
		<!-- Head Libs -->
		<script src="/assets/vendor/modernizr/modernizr.js"></script>
		
		<!-- Tables -->
		<link rel="stylesheet" href="/assets/vendor/select2/select2.css" />
		<link rel="stylesheet" href="/assets/vendor/jquery-datatables-bs3/assets/css/datatables.css" />
		
		
		
		
	</head>
	<body>
		<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<!-- screen loader -->
	    <div class="load_container-fluid h-100 loader-display">
	        <div class="load_row h-100">
	            <div class="align-self-center load_col">
	                <div class="logo-loading">
	                    <div class="icon icon-100 load_mb-4">
	                        <img src="/assets/images/logo.png" alt="" class="load_w-100">
	                    </div>
	                    <h4 class="text-default"></h4>
	                    <div class="loader-ellipsis">
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                        <div></div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>

		<section class="body">
			<!-- start: header -->
			<header class="header">
				<div class="logo-container">
					<a href="/home" class="logo">
						<img src="/assets/images/logo.png" height="35" alt="Porto Admin" />
					</a>
					<div class="visible-xs toggle-sidebar-left" data-toggle-class="sidebar-left-opened" data-target="html" data-fire-event="sidebar-left-opened">
						<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
					</div>
				</div>
			</header>
			<!-- end: header -->

			<div class="inner-wrapper">
				<!-- start: sidebar -->
				<aside id="sidebar-left" class="sidebar-left">
				
					<div class="sidebar-header">
						<div class="sidebar-title">
							Navegação
						</div>
						<div class="sidebar-toggle hidden-xs" data-toggle-class="sidebar-left-collapsed" data-target="html" data-fire-event="sidebar-left-toggle">
							<i class="fa fa-bars" aria-label="Toggle sidebar"></i>
						</div>
					</div>
				
					<div class="nano">
						<div class="nano-content">
							<nav id="menu" class="nav-main" role="navigation">
								<ul class="nav nav-main">
									<li class="nav-active">
										<c:if test="${!usuario.perfil.admin }">
										<a href="/home">
											<i class="fa fa-home" aria-hidden="true"></i>
											<span>Home</span>
										</a>
										</c:if>
										<c:if test="${usuario.perfil.admin }">
										<a href="/home">
											<i class="fa fa-home" aria-hidden="true"></i>
											<span>Home</span>
										</a>
										</c:if>
									</li>
									<li>
										<a href="/treino">
											<i class="fa fa-edit" aria-hidden="true"></i>
											<span>Meu Treino</span>
										</a>
									</li>
									<c:if test="${usuario.perfil.admin }">
										<li>
											<a href="/financeiro">
												<i class="fa fa-money" aria-hidden="true"></i>
												<span>Financeiro</span>
											</a>
										</li>
									</c:if>
									<li class="nav-parent">
										<a>
											<i class="fa fa-align-left" aria-hidden="true"></i>
											<span>Admnistração</span>
										</a>
										<ul class="nav nav-children">
											<li>
												<a href="/alunos">Alunos</a>
											</li>
											<li>
												<a href="/funcionarios">Funcionários</a>
											</li>
											<li>
												<a href="/aulas">Aulas</a>
											</li>
											<li>
												<a href="/pendencias">Pendências</a>
											</li>
											<li>
												<a href="/aniversariantes">Aniversariantes</a>
											</li>
											<li class="nav-parent">
												<a>Presença</a>
												<ul class="nav nav-children">
													<li>
														<a href="/presencaAlunos">Alunos</a>
													</li>
													<li>
														<a href="/presencaFuncionarios">Funcionários</a>
													</li>
												</ul>
											</li>
										</ul>
									</li>
									<li>
										<a href="/deslogar" >
											<i class="fa fa-power-off" aria-hidden="true"></i>
											<span>Sair</span>
										</a>
									</li>
								</ul>
							</nav>
				
							<hr class="separator" />
				
							<div class="sidebar-widget widget-tasks">
								<div class="widget-header">
									<h6>Plano</h6>
									<div class="widget-toggle">+</div>
								</div>
								<div class="widget-content">
									<ul class="list-unstyled m-none">
										<li><a href="#">Inicial</a></li>
									</ul>
								</div>
							</div>
				
							<hr class="separator" />
				
							<div class="sidebar-widget widget-stats">
								<div class="widget-header">
									<h6>Alunos Presentes Ontem</h6>
									<div class="widget-toggle">+</div>
								</div>
								<div class="widget-content">
									<ul>
										<li>
											<span class="stats-title">Total</span>
											<span class="stats-complete">85%</span>
											<div class="progress">
												<div class="progress-bar progress-bar-primary progress-without-number" role="progressbar" aria-valuenow="85" aria-valuemin="0" aria-valuemax="100" style="width: 85%;">
													<span class="sr-only">85% Complete</span>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
						</div>
				
					</div>
				
				</aside>
				<!-- end: sidebar -->

				<section role="main" class="content-body">
					<header class="page-header">
						<h2>${paginaAtual }</h2>
					
						<div class="right-wrapper pull-right">
							<ol class="breadcrumbs">
								<li>
									<a href="index.html">
										<i class="${iconePaginaAtual }"></i>
									</a>
								</li>
								<li><span>${paginaAtual }</span></li>
							</ol>
					
							<a class="sidebar-right-toggle" data-open="sidebar-right"><i class="fa fa-chevron-left"></i></a>
						</div>
					</header>

					
					<!-- MODAIS -->
					<jsp:include page="modal.jsp" />
					
					
					<!--  ATUALIZAR PÁGINA  -->
					<c:if test="${atualizarPagina != null }">
						<script>window.location.href='${atualizarPagina}';</script>
					</c:if>
					<!--  FIM ATUALIZAR PÁGINA  -->
					
					<!--  ERRO  -->
					<c:if test="${erro != null}">
						<div class="alert alert-danger">
							<button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
							<strong>Erro!</strong><br>Ocorreu um erro ao salvar o usuário. <br> Favor comunicar a equipe de desenvolvimento.<br>
						</div>
					</c:if>
					<!--  FIM ERRO  -->
					
					