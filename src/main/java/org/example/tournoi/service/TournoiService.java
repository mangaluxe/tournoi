package org.example.tournoi.service;

import org.example.tournoi.dao.TournoiRepository;
import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.util.Levenshtein;
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


    // ----- Recherche -----

    /**
     * Recherche de tournois par leur titre
     */
    public List<Tournoi> search(String searchTerm) {
        return tournoiRepository.findByNomContainingIgnoreCase(searchTerm);
    }


    /**
     * Recherche de sujets par leur titre approximatif avec la distance de Levenshtein
     */
    public List<Tournoi> rechercheApproximativeByNom(String recherche, int tolerance) {
        List<Tournoi> allSujets = tournoiRepository.findAll(); // Récupérer tous les tournois

        return allSujets.stream()
                .filter(s -> {
                    String titre = s.getNom().toLowerCase();
                    String rechercheLower = recherche.toLowerCase();

                    if (titre.length() < rechercheLower.length()) {
                        return false;  // Si la longueur du titre est inférieure à celle de la recherche
                    }

                    // Parcourir toutes les sous-chaînes du titre de la longueur de la recherche
                    for (int i = 0; i <= titre.length() - rechercheLower.length(); i++) {
                        String sousChaine = titre.substring(i, i + rechercheLower.length());
                        int distance = Levenshtein.calculLevenshteinDistance(rechercheLower, sousChaine);

                        if (distance <= tolerance) {
                            return true;  // Correspondance trouvée
                        }
                    }

                    return false;  // Pas de correspondance trouvée
                })
                .toList();
    }

}
