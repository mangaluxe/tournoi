package org.example.tournoi.service;

import org.example.tournoi.dao.InscriptionRepository;
import org.example.tournoi.entity.Inscription;
import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }


//    public List<Inscription> getInscriptionsParUtilisateur(int utilisateurId) {
//        return inscriptionRepository.findByUtilisateurId(utilisateurId);
//    }

    public List<Inscription> getInscriptionsParTournoi(int tournoiId) {
        return inscriptionRepository.findByTournoiId(tournoiId);
    }


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

    /**
     * Obtenir toutes les inscriptions
     */
    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }

    /**
     * Supprimer une inscription
     */
    public void supprimerInscription(int id) {
        inscriptionRepository.deleteById(id);
    }
}
