package com.br.rafael_tech.CinePhrase.services;

import com.br.rafael_tech.CinePhrase.dto.FraseDTO;
import com.br.rafael_tech.CinePhrase.models.Frase;
import com.br.rafael_tech.CinePhrase.repository.FraseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraseServices {

    @Autowired
    private FraseRepository fraseRepository;

    public FraseDTO getFrase(){
        Frase frase = fraseRepository.getRandomFrase();
        return new FraseDTO(frase.getTitulo(), frase.getFrase(), frase.getPersonagem(), frase.getPoster());
    }
}
