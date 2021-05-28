package com.example.progettodb22.Services;

import com.example.progettodb22.repository.BibliotecaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BibliotecaService {

    @Autowired
    private BibliotecaRepository repository;

    public void provaFindAll(){
        repository.findAll().forEach((biblioteca) -> {
            System.out.println(biblioteca);
        });
    }
}
