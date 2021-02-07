<!doctype html>
<html class="fixed">
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<!-- Theme CSS -->
		<link rel="stylesheet" href="/assets/stylesheets/theme.css" />

		<!-- Skin CSS -->
		<link rel="stylesheet" href="/assets/stylesheets/skins/default.css" />
		
		<!-- Loading -->
		<link rel="stylesheet" href="/assets/vendor/loading/loading.css" />
		
		
		<!-- Head Libs -->
		<script src="/assets/vendor/modernizr/modernizr.js"></script>
	</head>
	<body>
	
	    <!-- screen loader -->
	    <div class="load_container-fluid h-100 loader-display">
	        <div class="load_row h-100">
	            <div class="align-self-center load_col">
	                <div class="logo-loading">
	                    <div class="icon icon-100 load_mb-4">
	                        <img src="assets/images/logo.png" alt="" class="load_w-100">
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
    
		<!-- start: page -->
		<section class="body-sign">
			<div class="center-sign">
				<a class="logo pull-left">
					<img src="/assets/images/logo.png" height="54px" alt="Porto Admin" />
				</a>
				<div class="panel panel-sign">
					<div class="panel-title-sign mt-xl text-right">
						<h2 class="title text-uppercase text-weight-bold m-none"><i class="fa fa-user mr-xs"></i> Login</h2>
					</div>
					<div class="panel-body">
						<form action="/home" method="post" accept-charset="utf-8">
							<div class="form-group mb-lg">
								<label>Usuário</label>
								<div class="input-group input-group-icon">
									<input name="usuarioVal" type="text" class="form-control input-lg" required="required" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-user"></i>
										</span>
									</span>
								</div>
							</div>

							<div class="form-group mb-lg">
								<div class="clearfix">
									<label class="pull-left">Senha</label>
								</div>
								<div class="input-group input-group-icon">
									<input name="senhaVal" type="password" class="form-control input-lg" required="required" />
									<span class="input-group-addon">
										<span class="icon icon-lg">
											<i class="fa fa-lock"></i>
										</span>
									</span>
								</div>
							</div>
							
							<div class="row">
								<div class="col-sm-12 text-right">
									<button type="submit" class="btn btn-primary hidden-xs">Entrar</button>
									<button type="submit" class="btn btn-primary btn-block btn-lg visible-xs mt-lg">Entrar</button>
								</div>
							</div>

						</form>
					</div>
				</div>

				<p class="text-center text-muted mt-md mb-md">&copy; Copyright 2021. All Rights Reserved.</p>
			</div>
		</section>
		<!-- end: page -->

		<!-- Vendor -->
		<script src="/assets/vendor/jquery/jquery.js"></script>
		<script src="/assets/vendor/jquery-browser-mobile/jquery.browser.mobile.js"></script>
		<script src="/assets/vendor/bootstrap/js/bootstrap.js"></script>
		<script src="/assets/vendor/nanoscroller/nanoscroller.js"></script>
		<script src="/assets/vendor/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
		<script src="/assets/vendor/magnific-popup/magnific-popup.js"></script>
		<script src="/assets/vendor/jquery-placeholder/jquery.placeholder.js"></script>
		
		<!-- Theme Base, Components and Settings -->
		<script src="/assets/javascripts/theme.js"></script>
		
		<!-- Theme Custom -->
		<script src="/assets/javascripts/theme.custom.js"></script>
		
		<!-- Theme Initialization Files -->
		<script src="/assets/javascripts/theme.init.js"></script>
		
		<!-- Tela loading -->
		<script src="/assets/vendor/loading/main.js"></script>

	</body>
</html>