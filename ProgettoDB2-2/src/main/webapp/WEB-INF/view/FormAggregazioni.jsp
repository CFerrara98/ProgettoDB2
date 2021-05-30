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

    <title>Form Query Aggregate</title>

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
                <div style="margin-top: 35px;">
                <form class="form-horizontal" role="form" name="ricercaForm" method="Post" action="./findAggregate" modelAttribute="InserimentoProgettoFormativoForm">
                    <div style="margin-top: 15px;">
                        <h3>Query aggregate sulle province e sui comuni della Campania</h3>
                    </div>
                    <div style="justify-content:center;"  class="row" >
                        <div style="margin-top: 15px;" class="col-11">
                            <div class="form-group">
                                <div  style="margin: 0 auto;" class="col-sm-11">
                                    <label>Condizione di grouping</label>

                                    <select class="form-control" aria-label=".form-select-lg example" name="GroupSelect">
                                            <option selected value="Provincia">Provincia</option>
                                            <option value="Comune">Comune</option>
                                            <option value="Cap">Cap</option>
                                    </select>

                                    <label>Operazione di aggregazione</label>
                                    <select class="form-control" aria-label=".form-select-lg example" name="GroupOperation">
                                        <option selected value="somma">Somma</option>
                                        <option value="media">Media</option>
                                        <option value="minimo">Minimo</option>
                                        <option value="massimo">Massimo</option>
                                        <option value="primo">Primo</option>
                                        <option value="ultimo">Ultimo</option>
                                    </select>
                                    <br/>
                                    <label>Ordine di aggregazione</label>
                                    <br/>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="Order" id="inlineRadio1" value="NessunOrdinamento">
                                        <label class="form-check-label" for="inlineRadio1">Nessun ordinamento</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="Order" id="inlineRadio2" value="OrdinamentoCrescente">
                                        <label class="form-check-label" for="inlineRadio2">Ordinamento crescente</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="Order" id="inlineRadio3" value="OrdinamentoDecrescente">
                                        <label class="form-check-label" for="inlineRadio3">Ordinamento decrescente</label>
                                    </div>

                                    <br/>
                                    <label>Attributi di matching</label>
                                    <br/>
                                    <select class="form-control" aria-label=".form-select-lg example" name="MatchOperation" id="MatchingSelect">
                                        <option selected value="NessunMatching">Nessun matching</option>
                                        <option value="GratherThan"> Taglia valori minori di...</option>
                                        <option value="LessThan"> Taglia varoli maggiori di...</option>
                                        <option value="ValoriRange"> Taglia valori esterni ad un range</option>
                                    </select>
                                    <br/>

                                    <input type="number" id = "SogliaMin" name="SogliaMin" placeholder="Inserisci il bound inferiore" min = "1" class="form-control is-invalid disabled">
                                    <br/>

                                    <input type="number" id = "SogliaMax" name="SogliaMax" placeholder="Inserisci il bound superiore" min = "1" class="form-control is-invalid disabled">

                                </div>
                            </div>

                            </div>

                            <button type="submit" id="reg" class="btn btn-primary btn-block" style="margin: 25px 0 15px 45px">Cerca</button>
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

        $("#SogliaMax").hide();
        $("#SogliaMin").hide();


        $('#MatchingSelect').change(function () {
            if (this.value == 'GratherThan') {
                $("#SogliaMin").show();
                $("#SogliaMax").hide();
            } else if (this.value == 'LessThan') {
                $("#SogliaMin").hide();
                $("#SogliaMax").show();
            } else if (this.value == 'ValoriRange') {
                $("#SogliaMin").show();
                $("#SogliaMax").show();
            } else if (this.value == 'NessunMatching') {
                $("#SogliaMin").hide();
                $("#SogliaMax").hide();
            }
        });
    });





</script>


</html>