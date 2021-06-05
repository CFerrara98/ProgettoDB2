<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Dashboard</title>


<link rel="stylesheet"href="webjars/bootstrap/4.4.1/css/bootstrap.min.css"/>
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet"/>
<link href="/resources/css/bootstrap-table.css" rel="stylesheet"/>
<!-- Custom styles for this template -->

<link rel="stylesheet" href="/resources/css/sidebar.css"/>
<link href="/resources/css/dashboard.css" rel="stylesheet"/>

</head>

<body>
	<div class="container-fluid">

		<div class="row">
			<div class="wrapper d-flex align-items-stretch">
				<nav id="sidebar">
					<div class="custom-menu">
						<button type="button" id="sidebarCollapse" class="btn btn-primary">
							<i class="fa fa-bars"></i> <span class="sr-only">Toggle
								Menu</span>
						</button>
					</div>
					<div class="p-4 pt-5">
						<jsp:include page="menu.jsp" />
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div class="container">

						<h4 class="h4-spazio">
							<span class="my-4 header">Library-Info-Finder</span>
						</h4>
						<img src="http://avante.biz/wp-content/uploads/Library-Backgrounds/Library-Backgrounds-008.jpg" class="img-fluid rounded" alt="Responsive image">
					</div>
				</div>

			</div>
		</div>
		</div>
</body>

<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="./resources/js/bootstrap-table.min.js"></script>
<script src="./resources/js/sidebar.js"></script>

</html>