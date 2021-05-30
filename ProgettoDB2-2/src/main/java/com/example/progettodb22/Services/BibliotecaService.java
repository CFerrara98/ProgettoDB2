package com.example.progettodb22.Services;

import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
