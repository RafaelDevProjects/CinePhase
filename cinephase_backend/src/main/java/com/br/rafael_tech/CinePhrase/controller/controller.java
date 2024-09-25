package com.br.rafael_tech.CinePhrase.controller;

import com.br.rafael_tech.CinePhrase.dto.FraseDTO;
import com.br.rafael_tech.CinePhrase.models.Frase;
import com.br.rafael_tech.CinePhrase.services.FraseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class controller {
    // http://localhost:8080/series/frases

    @Autowired
    private FraseServices services;

    @GetMapping("/series/frases")
    public FraseDTO getFrases(){
        return services.getFrase();
    }
}
