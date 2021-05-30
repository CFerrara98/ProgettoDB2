package com.example.progettodb22.web;

import com.example.progettodb22.Services.BibliotecaService;
import com.example.progettodb22.entity.Biblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProgectController {

    @Autowired
    BibliotecaService bibliotecaService;


    @GetMapping("/search")
    public String formReqest() {
        return "formDiRicerca";
    }

    @PostMapping(value = "/findFiltered")
    public String findFiltered(@RequestParam String Denominazione, @RequestParam String Comune,
                                        @RequestParam String Provincia, @RequestParam String Indirizzo,
                                        @RequestParam String Cap, @RequestParam String minVolumi,
                                        @RequestParam String maxVolumi, @RequestParam String minRangeVolumi,
                                        @RequestParam String maxRangeVolumi, @RequestParam String limit,
                                        Model model) {

        System.out.println(Comune);
        List<Biblioteca> biblioteche = bibliotecaService.findFiltered(Denominazione, Comune, Provincia, Indirizzo, Cap,
                                                                        minVolumi, maxVolumi, minRangeVolumi, maxRangeVolumi, limit);
        model.addAttribute("Biblioteche", biblioteche);

        System.out.println(biblioteche);
        return "QueryResult";

    }

    @GetMapping("/prova")
    public String printBiblioteche() {

        bibliotecaService.provaFindAll();
        return "sample";
    }

}
