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
                    <div>
                        <div class="input-group mb-3" style="max-width: 30%">
                            <input style="width:fit-content" class="form-control" type="text" placeholder="0.0.0.0" id="ipInput" value="">
                            <div class="input-group-append">
                                <button id="reg" type="button" class="btn btn-info  btn-block" style="max-width: fit-content">Search</button>
                            </div>
                        </div>
                        <br/>

                    </div>
                        <br/>
                        <div id="result"></div>
                        <br/>
                        <div style="width:60%;background-color:#ffffff;height:60%" id="map"></div>

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

        $("#reg").click(function() {
            $.getJSON("${pageContext.request.contextPath}/getLocationByIpAddress",
                {
                    ipAddress : $('#ipInput').val()
                },
                function(data) {

                    var data = JSON.stringify(data);
                    var json = JSON.parse(data);
                    var myPosition = json[0];
                    var biblioteche = json[1];


                    showMap(myPosition["latitude"],myPosition["longitude"], biblioteche)

                    //$("#result").html(data)

                })
                .done(function() {
                })
                .fail(function() {
                })
                .complete(function() {
                });

        });

        var map;

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

    });
</script>
<br/>


</body>
</html>