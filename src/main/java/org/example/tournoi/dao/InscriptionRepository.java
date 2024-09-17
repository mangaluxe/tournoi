package org.example.tournoi.dao;

import org.example.tournoi.entity.Inscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    // Les méthodes de base : save(), delete(), findAll() sont natives

    List<Inscription> findByUtilisateurId(int utilisateurId);

    List<Inscription> findByTournoiId(int tournoiId);

}
