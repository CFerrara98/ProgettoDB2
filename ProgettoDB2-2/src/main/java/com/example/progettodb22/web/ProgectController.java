package com.example.progettodb22.web;

import com.example.progettodb22.Services.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgectController {

    @Autowired
    BibliotecaService bibliotecaService;

    @GetMapping("/prova")
    public String printBiblioteche() {

        bibliotecaService.provaFindAll();
        return "sample";
    }

}
