package com.example.progettodb22.web;

import com.example.progettodb22.Services.CittàService;
import com.example.progettodb22.Services.ServerLocationServicesImpl;
import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.entity.Città;
import com.example.progettodb22.entity.ServerLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MapController {

    @Autowired
    ServerLocationServicesImpl serverLocationService;
    @Autowired
    CittàService cittàService;

    @GetMapping(value = "/map")
    public String init(HttpServletRequest request, Model model) {
        model.addAttribute("comuni", cittàService.getAllComuni());
        return "map";
    }

    //return back json string
    @GetMapping(value = "/getLocationByComune")
    public @ResponseBody String getDomainInJsonFormatByCity(@RequestParam String comune, @RequestParam String distance) {

        if(comune == null || comune.equals("")) return "";

        ObjectMapper mapper = new ObjectMapper();
        Città città = cittàService.getByComune(comune);

        System.out.println(città);

        List<Biblioteca> bibliotecheNearIp = serverLocationService.getBibliotecheNearCoordinates(città.getLongitudine(), città.getLatitudine(), Double.parseDouble(distance));

        /*bibliotecheNearIp.forEach(biblioteca -> {
            System.out.println(biblioteca);
        });*/

        String result = "";

        try {
            result = "[";
            result +=  mapper.writeValueAsString(città);
            result += ",";
            result += mapper.writeValueAsString(bibliotecheNearIp);
            result += "]";
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;

    }

    //return back json string
    @GetMapping(value = "/getLocationByIpAddress")
    public @ResponseBody String getDomainInJsonFormat(@RequestParam String ipAddress, @RequestParam String distance) {

        if(ipAddress == null || ipAddress.equals("")) return "";

        ObjectMapper mapper = new ObjectMapper();

        ServerLocation location = serverLocationService.getLocation(ipAddress);

        List<Biblioteca> bibliotecheNearIp = serverLocationService.getBibliotecheNearCoordinates(Double.parseDouble(location.getLongitude()), Double.parseDouble(location.getLatitude()), Double.parseDouble(distance));

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