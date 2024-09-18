package org.example.tournoi.dao;

import org.example.tournoi.entity.Statistique;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface StatistiquesRepository extends JpaRepository<Statistique, Integer> {

    // Les méthodes de base : save(), delete(), findAll() sont natives

    boolean existsByUtilisateur(Utilisateur utilisateur);

    Optional<Statistique> findByUtilisateur(Utilisateur utilisateur);

}
