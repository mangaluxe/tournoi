package org.example.tournoi.service;

import org.example.tournoi.repository.InscriptionRepository;
import org.example.tournoi.entity.Inscription;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InscriptionService {

    // ========== Propriétés ==========

    private final InscriptionRepository inscriptionRepository;


    // ========== Constructeur ==========

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Obtenir toutes les inscriptions
     */
    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }


    /**
     * Récupérer les inscriptions par utilisateur_id
     */
    public List<Inscription> getInscriptionsByUtilisateurId(int utilisateurId) {
        return inscriptionRepository.findByUtilisateurId(utilisateurId);
    }


    /**
     * Obtenir une inscription par id
     */
    public Inscription getInscriptionById(int id) {
        return inscriptionRepository.findById(id).orElse(null);
    }


    public List<Inscription> getInscriptionsParTournoi(int tournoiId) {
        return inscriptionRepository.findByTournoiId(tournoiId);
    }


    // ----- Create -----

    /**
     * Créer une nouvelle inscription
     */
    public void creerInscription(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

//    public void creerInscription(Inscription inscription) {
//        Utilisateur utilisateur = utilisateurService.getUtilisateurById(inscription.getUtilisateur().getId());
//        Tournoi tournoi = tournoiService.getTournoiById(inscription.getTournoi().getId());
//
//        if (utilisateur != null && tournoi != null) {
//            inscription.setUtilisateur(utilisateur);
//            inscription.setTournoi(tournoi);
//            inscriptionRepository.save(inscription);
//        }
//    }


    // ----- Update -----


    // ----- Delete -----

    /**
     * Supprimer une inscription
     */
    public void supprimerInscription(int id) {
        inscriptionRepository.deleteById(id);
    }

}

