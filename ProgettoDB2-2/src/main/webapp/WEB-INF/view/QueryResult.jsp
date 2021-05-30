<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>Query Result</title>
		<!-- Bootstrap core CSS -->
		<link rel="stylesheet" href="webjars/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href="webjars/font-awesome/5.12.0/css/all.css" rel="stylesheet" />
		<link href="./resources/css/bootstrap-table.css" rel="stylesheet" />
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
							<jsp:include page="menu.jsp"/>
						</div>
					</nav>
	
					<!-- Page Content  -->
					<div id="content" class="p-4 p-md-5 pt-5">
						<div class="container">	
							<h4>
								<span class="my-4 header">Risultato Interrogazione</span>
							</h4>
							<input class="form-control" id="filter" type="text"
								placeholder="Filtra Richieste">
							<table id="parentTable" data-toggle="table" data-sortable="true"
								data-detail-view="true" data-pagination="true" data-page-size="5">
								<thead>
									<tr>
										<th class="d-none">Hidden nested details table</th>
										<th data-sortable="true" class="titolo">Denominazione</th>
										<th data-sortable="true" class="titolo">Comune</th>
										<th data-sortable="true" class="titolo">Email</th>
									</tr>	
								</thead>
								<tbody>

									<c:forEach items="${Biblioteche}" var="biblioteca" varStatus="loop">
										<tr>
											<td>
												<dl>
													<dt>Denominazione Completa: ${biblioteca.getDenominazione()}</dt>
													<br><dt>Indirizzo: ${biblioteca.getIndirizzo()}</dt>
													<br><dt>Regione: ${biblioteca.getRegione()}</dt>
													<br><dt>Provincia: ${biblioteca.getProvincia()}</dt>
													<br><dt>Comune: ${biblioteca.getComune()}</dt>
													<br><dt>CAP: ${biblioteca.getCap()}</dt>
													<br><dt>Codice Istat Comune: ${biblioteca.getCodiceIstatComune()}</dt>
													<br><dt>Codice Istat Provincia: ${biblioteca.getCodiceIstatProvincia()}</dt>
													<br><dt>Telefono: ${biblioteca.getTelefono()}</dt>
													<br><dt>Sito: ${biblioteca.getUrl()}</dt>
													<br><dt>Volumi Disponibili: ${biblioteca.getVolumiDisponibili()}</dt>

													<br>

												</dl>
											</td>

											<td class="testo-tabella testo-denominazione" >${biblioteca.getDenominazione()}</td>
											<td class="testo-tabella">${biblioteca.getComune()}</td>
											<td class="testo-tabella">${biblioteca.getEmail()}</td>
										</tr>	
									</c:forEach>	
								</tbody>
							</table>
						</div>
					</div>	
				</div>
			</div>
		</div>

	<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
	<script src="webjars/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="./resources/js/bootstrap-table.min.js"></script>
	<script src="./resources/js/sidebar.js"></script>

		<script>
			// Load detail view
			$('#parentTable').on('expand-row.bs.table',
					function(e, index, row, $detail) {

						// Get subtable from first cell
						var $rowDetails = $(row[0]);

						// Give new id to avoid conflict with first cell
						var id = $rowDetails.attr("id");
						$rowDetails.attr("id", id + "-Show");

						// Write rowDetail to detail
						$detail.html($rowDetails);

						return;

					})

			/*filtraggio campi*/
			$(document)
					.ready(
							function() {
								$("#filter")
										.on(
												"keyup",
												function() {
													var value = $(this).val()
															.toLowerCase();
													$("#parentTable tbody tr")
															.filter(
																	function() {
																		$(this)
																				.toggle(
																						$(
																								this)
																								.text()
																								.toLowerCase()
																								.indexOf(
																										value) > -1)
																	});
												});
							});

			//show modal
		</script>

</body>

</html>