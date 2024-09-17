package org.example.tournoi.service;

import org.example.tournoi.dao.StatistiquesRepository;
import org.example.tournoi.entity.Statistique;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatistiquesService {

    // ========== Propriétés ==========

    private final StatistiquesRepository statistiquesRepository;


    // ========== Constructeur ==========

    public StatistiquesService(StatistiquesRepository statistiquesRepository) {
        this.statistiquesRepository = statistiquesRepository;
    }


    // ========== Méthodes ==========

    /**
     * Ajouter ou mettre à jour des statistiques
     */
    public void creerStatistiques(Statistique statistiques) {
        statistiquesRepository.save(statistiques);
    }

    /**
     * Récupérer toutes les statistiques
     */
    public List<Statistique> getAllStatistiques() {
        return statistiquesRepository.findAll();
    }

}
