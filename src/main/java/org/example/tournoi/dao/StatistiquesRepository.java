package org.example.tournoi.dao;

import org.example.tournoi.entity.Statistique;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StatistiquesRepository extends JpaRepository<Statistique, Integer> {

    // Les méthodes de base : save(), delete(), findAll() sont natives

}
