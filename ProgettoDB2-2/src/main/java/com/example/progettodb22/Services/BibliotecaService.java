package com.example.progettodb22.Services;

import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;



    public List<Biblioteca> findFiltered(String Denominazione, String Comune, String Provincia,
                                         String Indirizzo, String Cap,  String minVolumi, String maxVolumi, String minRangeVolumi,
                                         String maxRangeVolumi, String limit){
        Query q = new Query();

        if(Denominazione != null && !Denominazione.equals("")) {
            Criteria nameCriteria = Criteria.where("Denominazione").regex(Denominazione, "i");
            q.addCriteria(nameCriteria);
        }

        if(Comune != null && !Comune.equals("")) {
            Criteria comuneCriteria = Criteria.where("Comune").regex(Comune, "i");
            q.addCriteria(comuneCriteria);
        }

        if(Provincia != null && !Provincia.equals("")) {
            Criteria provinciaCriteria = Criteria.where("Provincia").regex(Provincia, "i");
            q.addCriteria(provinciaCriteria);
        }

        if(Indirizzo != null && !Indirizzo.equals("")) {
            Criteria indirizzoCriteria = Criteria.where("Indirizzo").regex(Indirizzo, "i");
            q.addCriteria(indirizzoCriteria);
        }

        if(Cap != null && !Cap.equals("")) {
            Criteria capCriteria = Criteria.where("Cap").regex(Cap, "i");
            q.addCriteria(capCriteria);
        }


        if(minVolumi != null && !minVolumi.equals("")) {
            try {
                int minVolumiInt = Integer.parseInt(minVolumi);

                Criteria minVolumiCriteria = Criteria.where("VolumiDisponibili").gte(minVolumiInt);
                q.addCriteria(minVolumiCriteria);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(maxVolumi != null && !maxVolumi.equals("")) {
            try {
                int maxVolumiInt = Integer.parseInt(maxVolumi);

                Criteria minVolumiCriteria = Criteria.where("VolumiDisponibili").lte(maxVolumiInt);
                q.addCriteria(minVolumiCriteria);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if((minRangeVolumi != null && !minRangeVolumi.equals("")) && (maxRangeVolumi != null && !maxRangeVolumi.equals(""))) {
            try {
                int minVolumiInt = Integer.parseInt(minRangeVolumi);
                int maxVolumiInt = Integer.parseInt(maxRangeVolumi);

                Criteria VolumiCriteria = Criteria.where("VolumiDisponibili").lte(maxVolumiInt).gte(minVolumiInt);
                q.addCriteria(VolumiCriteria);

            } catch (Exception e){
                e.printStackTrace();
            }
        }

        if(limit != null && !limit.equals("")){
            q.limit(Integer.parseInt(limit));
        }


        List<Biblioteca> biblioteche = mongoTemplate.find(q, Biblioteca.class, "biblioteche2");

        return biblioteche;
    }

    public void provaFindAll(){
        repository.findAll().forEach((biblioteca) -> {
            System.out.println(biblioteca);
        });
    }

    public AggregationResults<org.bson.Document> findAggregate(String groupselect, String groupoperation, String order, String matchoperation, Integer max, Integer min) {


        //System.out.println("service" + volumi + " "+ indirizzo + ""+ matchoperation);
        GroupOperation group = group(groupselect).sum("VolumiDisponibili").as(groupoperation);
        SortOperation sortByCount = null;
        Aggregation aggr;
        MatchOperation match = null;

        switch (groupoperation){
            case "somma": {
                group = group(groupselect).sum("VolumiDisponibili").as(groupoperation);
                break;

            }

            case "media": {
                group = group(groupselect).avg("VolumiDisponibili").as(groupoperation);
                break;
            }

            case "minimo": {
                group = group(groupselect).min("VolumiDisponibili").as(groupoperation);
                break;
            }

            case "massimo": {
                group = group(groupselect).max("VolumiDisponibili").as(groupoperation);
                break;
            }

            case "primo": {
                group = group(groupselect).first("VolumiDisponibili").as(groupoperation);
                break;
            }

            case "ultimo": {
                group = group(groupselect).last("VolumiDisponibili").as(groupoperation);
                break;
            }

        }



        switch (order){
            case "NessunOrdinamento": {
                break;
            }

            case "OrdinamentoCrescente": {
                sortByCount = sort(Sort.Direction.ASC, groupoperation);
                break;
            }

            case "OrdinamentoDecrescente": {
                sortByCount = sort(Sort.Direction.DESC, groupoperation);
                break;
            }

        }

        switch (matchoperation){
            case "NessunMatching": {
                break;
            }

            case "GratherThan": {
                match = Aggregation.match(new Criteria(groupoperation).gte(min));
                break;
            }

            case "LessThan": {
                match = Aggregation.match(new Criteria(groupoperation).lte(max));
                break;
            }

            case "ValoriRange": {
                match = Aggregation.match(new Criteria(groupoperation).gte(min).lte(max));
                break;
            }

        }

        //.orOperator(new Criteria("Email").regex("Non disponibile", "i")))
        if (sortByCount != null) {

            if (match != null) aggr = Aggregation.newAggregation(group, match, sortByCount);
                else aggr = Aggregation.newAggregation(group, sortByCount);

        } else {

            if (match != null) {

                aggr = Aggregation.newAggregation(group, match);

            } else aggr = Aggregation.newAggregation(group);
        }


        AggregationResults<org.bson.Document> documenti = mongoTemplate.aggregate(aggr, "biblioteche2", org.bson.Document.class);

        System.out.println(aggr.getPipeline());
        System.out.println("documenti"+ documenti);
        return documenti;

    }
}
