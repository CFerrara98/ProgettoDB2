<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Form Query Dinamica</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
<link href="<c:url value="/resources/css/formStylePage.css" />" rel="stylesheet">
<!-- Custom styles for this template -->

<link rel="stylesheet" href="./resources/css/sidebar.css">
<link href="./resources/css/dashboard.css" rel="stylesheet">

</head>

<body>
	<div class="container-fluid">

		<div class="row">
			<div class="wrapper d-flex align-items-stretch">
				<nav id="sidebar">
					<div class="custom-menu">
						<button type="button" id="sidebarCollapse" class="btn btn-primary">
							<i class="fa fa-bars"></i> <span class="sr-only">Toggle Menu</span>
						</button>
					</div>
					<div class="p-4 pt-5">
						<!--  <h1><a href="index.html" class="logo">IFY</a></h1>-->
						
						<jsp:include page="menu.jsp"/>
					</div>
				</nav>

				<!-- Page Content  -->
				<div id="content" class="p-4 p-md-5 pt-5">
					<div style="margin-top: 35px; class="container">
						<form class="form-horizontal" role="form" name="ricercaForm" method="Post" action="./findFiltered" modelAttribute="InserimentoProgettoFormativoForm">
			            <div style="margin-top: 15px;">
						<h3>Form di Ricerca</h3>
						</div>
						<div style="justify-content:center;"  class="row" >
							<div style="margin-top: 15px;" class="col-11">
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<input type="text" name="Denominazione" id="Denominazione" placeholder="Denominazione" class="form-control is-invalid">
										</div>
									</div>
									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<input type="text" name="Comune" id="Comune" placeholder="Comune" class="form-control is-invalid">
										</div>
									</div>
								<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<input type="text" name="Provincia" id="Provincia" placeholder="Provincia" class="form-control is-invalid">
										</div>
									</div>
								<div class="form-group">
									<div  style="margin: 0 auto;" class="col-sm-11">
										<input type="text" name="Indirizzo" id="Indirizzo" placeholder="Indirizzo" class="form-control is-invalid">
									</div>
								</div>

								<div class="form-group">
									<div  style="margin: 0 auto;" class="col-sm-11">
										<input type="text" name="Cap" id="Cap" placeholder="CAP" class="form-control is-invalid">
									</div>
								</div>

								<div class="form-group">
									<span style = "margin-left: 70px"><b>Volumi disponibili</b></span>

									<div>
										<div class="row">

											<div class="form-check form-check-inline" style = "margin-left: 80px">
												<input name="gruppoVolumi" type="radio" id="min" value = "min" checked>
												<label for="min" style = "padding: 15px">Min di...</label>
											</div>
											<div class="form-check form-check-inline">
												<input name="gruppoVolumi" type="radio" id="max" value = "max">
												<label for="max" style = "padding: 15px">Max di...</label>
											</div>

											<div class="form-check form-check-inline">
												<input name="gruppoVolumi" type="radio" id="range" value="range">
												<label for="range" style = "padding: 15px">Range</label>
											</div>
										</div>
									</div>

									<div  style="margin: 0 auto;" class="col-sm-11">
										<input type="number" id = "VolumiMin" name="minVolumi" placeholder="Inserisci volumi minimi" min = "1" class="form-control is-invalid">

										<input type="number" id = "VolumiMax" name="maxVolumi" placeholder="Inserisci volumi massimi" min = "1" class="form-control is-invalid .d-none">

										<input type="number" id = "VolumiMinRange" name="minRangeVolumi" placeholder="Inserisci limite inferiore" min = "1" class="form-control is-invalid .d-none">
										<br>
										<input type="number" id = "VolumiMaxRange" name="maxRangeVolumi" placeholder="Inserisci limite superiore" min = "1" class="form-control is-invalid .d-none">
										<br>


									</div>

									<div class="form-group">
										<div  style="margin: 0 auto;" class="col-sm-11">
											<input type="number" id = "limit" name="limit" placeholder="Inserisci un limite di tuple" min = "1" class="form-control is-invalid">
										</div>
									</div>



								</div>

									<button type="submit" id="reg" class="btn btn-info btn-block" style="margin: 25px 0 15px 45px">Cerca</button>
								</div>


							</div>
						</form>
					</div>
				</div>
				</div>

			</div>
		</div>
		

</body>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="./resources/js/sidebar.js"></script>
<script>
	$(document).ready(function() {

		$("#VolumiMin").prop("checked", true);
		$("#VolumiMin").show();
		$("#VolumiMax").hide();
		$("#VolumiMinRange").hide();
		$("#VolumiMaxRange").hide();

		$('input[type=radio][name=gruppoVolumi]').change(function () {
			if (this.value == 'min') {
				$("#VolumiMin").show();
				$("#VolumiMax").hide();
				$("#VolumiMinRange").hide();
				$("#VolumiMaxRange").hide();
			} else if (this.value == 'max') {
				$("#VolumiMin").hide();
				$("#VolumiMax").show();
				$("#VolumiMinRange").hide();
				$("#VolumiMaxRange").hide();
			} else if (this.value == 'range') {
				$("#VolumiMin").hide();
				$("#VolumiMax").hide();
				$("#VolumiMinRange").show();
				$("#VolumiMaxRange").show();
			}
		});
	});





</script>
</html>