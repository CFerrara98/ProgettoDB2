package com.example.progettodb22.web;

import com.example.progettodb22.Services.ServerLocationServicesImpl;
import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.entity.ServerLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MapController {

    @Autowired
    ServerLocationServicesImpl serverLocationService;

    @GetMapping(value = "/map")
    public String init(HttpServletRequest request) {
        return "map";
    }

    //return back json string
    @GetMapping(value = "/getLocationByIpAddress")
    public @ResponseBody String getDomainInJsonFormat(@RequestParam String ipAddress) {

        if(ipAddress == null || ipAddress.equals("")) return "";

        ObjectMapper mapper = new ObjectMapper();

        ServerLocation location = serverLocationService.getLocation(ipAddress);

        List<Biblioteca> bibliotecheNearIp = serverLocationService.getBibliotecheNearIp(Double.parseDouble(location.getLongitude()), Double.parseDouble(location.getLatitude()));

        /*bibliotecheNearIp.forEach(biblioteca -> {
            System.out.println(biblioteca);
        });*/

        String result = "";

        try {
            result = "[";
            result +=  mapper.writeValueAsString(location);
            result += ",";
            result += mapper.writeValueAsString(bibliotecheNearIp);
            result += "]";

            System.out.println(result);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}