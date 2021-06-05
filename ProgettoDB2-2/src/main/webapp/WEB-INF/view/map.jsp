<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
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

            <div id="content" class=" pt-5">
                <div class="container">

                    <h4 class="h4-spazio">
                        <span class="my-4 header">Progetto database 2 Ferrara Spinelli</span>
                    </h4>
                    <div class="row row-cols-2">
                        <div class = "col-9">
                            <div>
                                <div class="input-group mb-3" style="max-width: 40%">
                                    <input style="width:fit-content" class="form-control" type="text" placeholder="0.0.0.0" id="ipInput" value="">
                                    <div class="input-group-append">
                                        <button id="reg" type="button" class="btn btn-info  btn-block" style="max-width: fit-content">Search</button>
                                    </div>
                                </div>
                                <br/>

                                <div class="input-group mb-3" style="max-width: 40%">

                                    <select class="form-select" aria-label="Default select example" id = "comuneSelect" >
                                        <c:forEach items="${comuni}" var="comune" varStatus="loop">
                                            <option value="${comune.getComune()}">${comune.getComune()}</option>
                                        </c:forEach>
                                    </select>




                                    <div class="input-group-append">
                                        <button id="reg1" type="button" class="btn btn-info  btn-block" style="max-width: fit-content">Search</button>
                                    </div>
                                </div>
                                <br/>
                                <h5 for="myDistance" class="form-label">Itervallo di distanza 1-20 km - step 1</h5>
                                <h5 id = "myDistanceSelected">Distanza attuale: 1 km</h5>

                                <input type="range" class="form-range" value="1" min="1" max="20" step="1" id="myDistance">


                            </div>
                            <div id="result"></div>
                            <br/>
                            <div style="width:80%;background-color:#ffffff;height:150%" id="map">

                            </div>
                        </div>

                        <div class = "col-3" style="color: #1d2124">
                           <p id = "intestazioneBiblioteche"></p>
                            <div class="overflow-auto" style="height: 200px; width: 100%;">
                                <ul id = "listaBiblioteche"></ul>
                            </div>

                        </div>
                    </div>


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
    $(document).ready(function() {

        var ipSelected = false, comuneSelected = false;

        $('#myDistance').change(function(){
            $('#myDistanceSelected').text("Distanza attuale: " + $('#myDistance').val() + " km");

            if(comuneSelected && !ipSelected) comuneRequest();
            else if (ipSelected && ! comuneSelected) ipRequest();
        })

        $("#reg1").click(function() {
            comuneSelected = true;
            ipSelected = false;
            comuneRequest();

        });

        $("#reg").click(function() {
            if($('#ipInput').val() != ""){
                comuneSelected = false;
                ipSelected = true;
                ipRequest();
            }
        });

        var map;

        function comuneRequest (){
            $.getJSON("${pageContext.request.contextPath}/getLocationByComune",
                {
                    comune : $('#comuneSelect').val(),
                    distance : $('#myDistance').val()
                },
                function(data) {

                    var data = JSON.stringify(data);
                    var json = JSON.parse(data);
                    var myCity = json[0];
                    var biblioteche = json[1];

                    showMap(myCity["latitudine"],myCity["longitudine"], biblioteche);

                    $("#intestazioneBiblioteche").text("Biblioteche vicino al comune: " + $('#comuneSelect').val());
                    $("#listaBiblioteche").text("");
                    biblioteche.forEach(addBiblioteca);


                });
        }

        function ipRequest(){
            $.getJSON("${pageContext.request.contextPath}/getLocationByIpAddress",
                {
                    ipAddress : $('#ipInput').val(),
                    distance : $('#myDistance').val()
                },
                function(data) {

                    var data = JSON.stringify(data);
                    var json = JSON.parse(data);
                    var myPosition = json[0];
                    var biblioteche = json[1];

                    $("#intestazioneBiblioteche").text("Biblioteche vicino ad IP: " + $('#ipInput').val());
                    $("#listaBiblioteche").text("");
                    biblioteche.forEach(addBiblioteca);

                    showMap(myPosition["latitude"],myPosition["longitude"], biblioteche)

                    //$("#result").html(data)

                });
        }


        function showMap(latitude,longitude, biblioteche) {

            var googleLatandLong = new google.maps.LatLng(latitude,longitude);

            var mapOptions = {
                zoom: 15,
                center: googleLatandLong,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };


            var mapDiv = document.getElementById("map");
            map = new google.maps.Map(mapDiv, mapOptions);

            //add my ip marker
            var title = "Server Location";
            addMarker(map, googleLatandLong, title, "", "http://maps.google.com/mapfiles/ms/icons/green-dot.png");

            //add marker biblioteche

            biblioteche.forEach(MarkBiblioteca);
        }

        function MarkBiblioteca(value, index, array) {
            var title = value['denominazione'];
            var latitude = value['location']['coordinates'][1];
            var longitude = value['location']['coordinates'][0];
            console.log(title);

            var xPosition = new google.maps.LatLng(latitude,longitude);
            addMarker(map, xPosition, title, "", "http://maps.google.com/mapfiles/ms/icons/blue-dot.png");
        }


        function addMarker(map, latlong, title, content, icon) {

            var markerOptions = {
                position: latlong,
                map: map,
                title: title,
                clickable: true,
                icon: {
                    url: icon
                }
            };
            var marker = new google.maps.Marker(markerOptions);
        }

        function addBiblioteca(item, index) {
            $('#listaBiblioteche').append("<li>" + item['denominazione'] + " - " + item['indirizzo'] +  " - " + item['comune'] + "</li>") ;
        }
    });
</script>
<br/>


</body>
</html>