package com.br.rafael_tech.CinePhrase.repository;

import com.br.rafael_tech.CinePhrase.models.Frase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FraseRepository extends JpaRepository<Frase, Long> {
    @Query("SELECT f FROM Frase f ORDER BY FUNCTION('RANDOM') LIMIT 1")
    Frase getRandomFrase();
}
