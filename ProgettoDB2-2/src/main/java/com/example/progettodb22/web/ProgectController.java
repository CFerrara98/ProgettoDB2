package com.example.progettodb22.web;

import com.example.progettodb22.Services.BibliotecaService;
import com.example.progettodb22.entity.Biblioteca;
import com.mongodb.client.model.Aggregates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
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

    @GetMapping(value = "/Aggregation")
    public String formAggregationReqest() {
        return "FormAggregazioni";
    }

    @PostMapping(value = "/findAggregate")
    public String findFiltered(@RequestParam String GroupSelect, @RequestParam String GroupOperation,
                               @RequestParam String Order, @RequestParam String MatchOperation, @RequestParam String Indirizzo, @RequestParam String Volumi, Model model) {

        Integer VolumiDis = null;
        if (Volumi != null && !Volumi.equals("")) VolumiDis = Integer.parseInt(Volumi);
        AggregationResults<org.bson.Document> documentiAggregati = bibliotecaService.findAggregate(GroupSelect, GroupOperation, Order, MatchOperation, Indirizzo, VolumiDis);
        model.addAttribute("Documenti", documentiAggregati.getMappedResults());
        model.addAttribute("GruppoOperation", GroupOperation);
        model.addAttribute("GruppoSelect", GroupSelect);

        System.out.println(documentiAggregati.getMappedResults());
        return "QueryAggregationResult";


    }


}
