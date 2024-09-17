//package org.example.tournoi.service;
//
//import org.example.tournoi.entity.Partie;
//import org.example.tournoi.dao.PartieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PartieService {
//
//    @Autowired
//    private PartieRepository partieRepository;
//
//    // ----- Read -----
//
//    /**
//     * Obtenir toutes les parties
//     */
//    public List<Partie> getAllParties() {
//        return partieRepository.findAll();
//    }
//
//    /**
//     * Trouver une partie par son id
//     */
//    public Partie getPartieById(int id) {
//        return partieRepository.findById(id).orElse(null);
//    }
//
//    // ----- Create / Update -----
//
//    /**
//     * Ajouter une nouvelle partie
//     */
//    public void creerPartie(Partie partie) {
//        partieRepository.save(partie); // Crée une nouvelle partie
//    }
//
//    /**
//     * Mettre à jour une partie existante
//     */
//    public void modifierPartie(int id, Partie partieModifiee) {
////        Partie partieExistante = partieRepository.findById(id).orElse(null);
////        if (partieExistante != null) {
////            partieExistante.setNom(partieModifiee.getNom());
////            partieExistante.setTournoi(partieModifiee.getTournoi());
////            partieExistante.setJoueur1(partieModifiee.getJoueur1());
////            partieExistante.setJoueur2(partieModifiee.getJoueur2());
////            partieExistante.setResultat(partieModifiee.getResultat());
////            // Ajoute toutes les autres propriétés à mettre à jour...
////            partieRepository.save(partieExistante); // Sauvegarde les modifications
////        }
//    }
//
//    // ----- Delete -----
//
//    /**
//     * Supprimer une partie par son id
//     */
//    public void supprimerPartie(int id) {
//        partieRepository.deleteById(id);
//    }
//
//}
