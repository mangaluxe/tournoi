package org.example.tournoi.service;

import org.example.tournoi.dao.InscriptionRepository;
import org.example.tournoi.entity.Inscription;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;

    public InscriptionService(InscriptionRepository inscriptionRepository) {
        this.inscriptionRepository = inscriptionRepository;
    }

    // Créer une nouvelle inscription
    public void creerInscription(Inscription inscription) {
        inscriptionRepository.save(inscription);
    }

    // Obtenir toutes les inscriptions
    public List<Inscription> getAllInscriptions() {
        return inscriptionRepository.findAll();
    }

    // Supprimer une inscription
    public void supprimerInscription(int id) {
        inscriptionRepository.deleteById(id);
    }
}
