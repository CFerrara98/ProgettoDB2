package com.example.progettodb22.Services;

import com.example.progettodb22.entity.Città;
import com.example.progettodb22.repository.CittàRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CittàService {

    @Autowired
    CittàRepository cittàRepository;

    public List<Città> getAllComuni(){

        return cittàRepository.findAll();
    }

    public Città getByComune(String comune){

        return cittàRepository.findByComune(comune);
    }
}
