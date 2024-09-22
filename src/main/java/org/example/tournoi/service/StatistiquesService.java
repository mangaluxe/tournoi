package org.example.tournoi.service;

import org.example.tournoi.repository.StatistiquesRepository;
import org.example.tournoi.entity.Statistique;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StatistiquesService {

    // ========== Propriétés ==========

    private final StatistiquesRepository statistiquesRepository;


    // ========== Constructeur ==========

    public StatistiquesService(StatistiquesRepository statistiquesRepository) {
        this.statistiquesRepository = statistiquesRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Récupérer toutes les statistiques
     */
    public List<Statistique> getAllStatistiques() {
        return statistiquesRepository.findAll();
    }


    /**
     * Vérifier si un utilisateur a déjà des stats
     */
    public boolean existsByUtilisateur(Utilisateur utilisateur) {
        return statistiquesRepository.existsByUtilisateur(utilisateur);
    }


    public Optional<Statistique> getByUtilisateur(Utilisateur utilisateur) {
        return statistiquesRepository.findByUtilisateur(utilisateur);
    }


    // ----- Create -----

    /**
     * Ajouter ou mettre à jour des statistiques
     */
//    public void creerStatistiques(Statistique statistiques) {
//        statistiquesRepository.save(statistiques);
//    }
    public void creerStatistiques(Statistique nouvellesStatistiques) {
        Utilisateur utilisateur = nouvellesStatistiques.getUtilisateur();

        // Vérifier si l'utilisateur a déjà des stats
        Optional<Statistique> statistiquesExistantes = statistiquesRepository.findByUtilisateur(utilisateur);

        if (statistiquesExistantes.isPresent()) {
            Statistique existantes = statistiquesExistantes.get(); // Mettre à jour les statistiques existantes
            existantes.setScore(nouvellesStatistiques.getScore());
            existantes.setVictoires(nouvellesStatistiques.getVictoires());
            existantes.setDefaites(nouvellesStatistiques.getDefaites());
            statistiquesRepository.save(existantes);
        }
        else {
            statistiquesRepository.save(nouvellesStatistiques);
        }
    }

}

