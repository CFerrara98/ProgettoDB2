package com.example.progettodb22.Services;


import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.entity.ServerLocation;
import com.example.progettodb22.repository.BibliotecaRepository;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;


@Service
public class ServerLocationServicesImpl implements ServerLocationServices {

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Override
    public ServerLocation getLocation(String ipAddress) {

        String dataFile = "./location/GeoLiteCity.dat";
        return getLocation(ipAddress, dataFile);

    }


    private ServerLocation getLocation(String ipAddress, String locationDataFile) {

        ServerLocation serverLocation = null;

        URL url = getClass().getClassLoader().getResource(locationDataFile);

        if (url == null) {
            System.err.println("location database is not found - "
                    + locationDataFile);
        } else {

            try {

                serverLocation = new ServerLocation();

                LookupService lookup = new LookupService(url.getPath(),
                        LookupService.GEOIP_MEMORY_CACHE);
                Location locationServices = lookup.getLocation(ipAddress);

                System.out.println(locationServices);

                serverLocation.setCountryCode(locationServices.countryCode);
                serverLocation.setCountryName(locationServices.countryName);
                serverLocation.setRegion(locationServices.region);
                serverLocation.setRegionName(regionName.regionNameByCode(locationServices.countryCode, locationServices.region));
                serverLocation.setCity(locationServices.city);
                serverLocation.setPostalCode(locationServices.postalCode);
                serverLocation.setLatitude(
                        String.valueOf(locationServices.latitude));
                serverLocation.setLongitude(
                        String.valueOf(locationServices.longitude));

            } catch (IOException e) {

                System.err.println(e.getMessage());

            }

        }

        return serverLocation;

    }

    @Override
    public List<Biblioteca> getBibliotecheNearIp(Double Longitudine, Double Latitudine) {
        System.out.println(Longitudine + "," +Latitudine);


        Point pos = new Point(Longitudine, Latitudine);
        Distance dis = new Distance(1, Metrics.KILOMETERS);

        List<Biblioteca> biblioteche = bibliotecaRepository.findByLocationNear(pos, dis);
        return biblioteche;
    }

}