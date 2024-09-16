package org.example.tournoi.dao;

import org.example.tournoi.entity.Tournoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TournoiRepository extends JpaRepository<Tournoi, Integer>  {

    long count(); // Compter le nombre de tournois

}
