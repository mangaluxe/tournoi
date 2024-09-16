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

    // ----- Read -----

    /**
     * Obtenir tous les tournois
     */
    public List<Tournoi> getAllTournois() {
        return tournoiRepository.findAll();
    }


    /**
     * Obtenir un tournoi par id
     */
    public Tournoi getTournoiById(int id) {
        return tournoiRepository.findById(id).orElse(null);
    }


    /**
     * Compter nb de tournois
     * @return long
     */
    public long getNbTotalTournois() {
        return tournoiRepository.count();
    }

    // ----- Create -----

    /**
     * Créer un tournoi
     */
    public void creerTournoi(Tournoi tournoi) {
        tournoiRepository.save(tournoi);
    }

    // ----- Update -----


    // ----- Delete -----

    /**
     * Supprimer un tournoi par son id
     */
    public void supprimerTournoi(int id) {
        tournoiRepository.deleteById(id); // deleteById() auto-geré par JPA
    }


}
