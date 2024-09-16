package org.example.tournoi.dao;

import org.example.tournoi.entity.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TournoiRepository extends JpaRepository<Tournoi, Integer>  {

    long count(); // Compter le nombre de tournois

    // Les méthodes de base : save(), delete(), findAll() sont natives


}
