package com.example.progettodb22.Services;

import com.example.progettodb22.entity.Biblioteca;
import com.example.progettodb22.entity.ServerLocation;

import java.util.List;

public interface ServerLocationServices {
    ServerLocation getLocation(String ipAddress);
    List<Biblioteca> getBibliotecheNearIp(Double Longitudine, Double Latitudine);
}
