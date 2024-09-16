package org.example.tournoi.service;

import org.example.tournoi.dao.TournoiRepository;
import org.example.tournoi.entity.Tournoi;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TournoiService {

    // ========== Propriétés ==========

    private final TournoiRepository tournoiRepository;


    // ========== Constructeur ==========

    public TournoiService(TournoiRepository tournoiRepository) {
        this.tournoiRepository = tournoiRepository;
    }


    // ========== Méthodes ==========

    public List<Tournoi> getAllTournois() {
        return tournoiRepository.findAll();
    }


    /**
     * Compter nb de tournois
     * @return long
     */
    public long getNbTotalTournois() {
        return tournoiRepository.count();
    }




}
