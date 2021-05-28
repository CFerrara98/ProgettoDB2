<html>
<head>
    <script src="http://maps.google.com/maps/api/js?sensor=true"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<h2>Spring MVC + jQuery + Google Map</h2>

<div>
    <input type="text" placeholder="0.0.0.0" id="ipInput" value="">
    <span>
        <button id="w-button-search" type="button">Search</button>
    </span>
</div>

<script>
    $(document).ready(function() {

        $("#w-button-search").click(function() {
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
                zoom: 50,
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
<div id="result"></div>
<br/>
<div style="width:600px;height:400px" id="map"></div>

</body>
</html>