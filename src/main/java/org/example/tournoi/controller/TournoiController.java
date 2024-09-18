package org.example.tournoi.controller;

import jakarta.validation.Valid;
import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.service.TournoiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class TournoiController {

    // ========== Propriétés ==========

    private TournoiService tournoiService;


    // ========== Controller ==========

    public TournoiController(TournoiService tournoiService) {
        this.tournoiService = tournoiService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Afficher liste de tournois
     */
    @GetMapping("/tournois")
    public String tournois(Model model) {

        List<Tournoi> tournois = tournoiService.getAllTournois();

        // Formater les dates pour chaque tournoi
        tournois.forEach(t -> t.formatDates());

        model.addAttribute("title", "Liste des tournois"); // Pour le title de la page
        model.addAttribute("tournois", tournois);

        long nb = tournoiService.getNbTotalTournois();
        model.addAttribute("nbTotalTournois", nb);

        return "tournois";
    }


    /**
     * Afficher un tournoi
     */
    @GetMapping("/tournoi/{id}")
    public String getTournoiById(@PathVariable("id") int id, Model model) {

        Tournoi tournoi = tournoiService.getTournoiById(id);

        if (tournoi != null) {

            // Formater date
            tournoi.formatDates();

            model.addAttribute("tournoi", tournoi);
            model.addAttribute("title", "Tournoi : " + tournoi.getNom()); // Pour le title de la page

            return "tournoi";
        }
        else {
            return "error/404";
        }
    }


    // ----- Create -----

    /**
     * Formulaire pour créer tournoi
     */
    @GetMapping("/tournoi/nouveau")
    public String formulaireCreationTournoi(Model model) {
        Tournoi tournoi = new Tournoi(); // Un tournoi vide pour le formulaire
        model.addAttribute("tournoi", tournoi);
        return "creer-tournoi"; // Attention : indiquer nom du fichier html uniquement, ne marche pas avec nom de route
    }

    /**
     * Créer tournoi
     */
    @PostMapping("/tournoi/nouveau")
    public String creerTournoi(@Valid @ModelAttribute("tournoi") Tournoi tournoi, BindingResult bindingResult, Model model) {

        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) { // BindingResult capture les erreurs de validation
            model.addAttribute("tournoi", tournoi); // Si erreurs de validation, on retourne le formulaire avec les messages d'erreur
            return "creer-tournoi"; // Si erreurs de validation, retour au formulaire. 💡 Attention : indiquer nom du fichier html uniquement, ne marche pas avec nom de route
        }
        // --- ---

        tournoiService.creerTournoi(tournoi);

        return "redirect:/tournois"; // 💡 Attention : avec redirect, c'est nom du chemin qu'il faut indiquer, et non le html
    }


    // ----- Update -----

    /**
     * Formulaire pour modifier un tournoi
     */
    @GetMapping("/tournoi/{id}/modifier")
    public String formulaireModifierTournoi(@Valid @PathVariable("id") int id, Model model) {
        Tournoi tournoi = tournoiService.getTournoiById(id);

        if (tournoi != null) {
            model.addAttribute("tournoi", tournoi);
            model.addAttribute("title", "Modifier le tournoi : " + tournoi.getNom());
            return "modifier-tournoi";
        }
        else {
            return "error/404";
        }
    }

    /**
     * Modifier un tournoi
     */
    @PostMapping("/tournoi/{id}/modifier")
    public String modifierTournoi(@PathVariable("id") int id, @ModelAttribute("tournoi") Tournoi tournoiModifie) {
        Tournoi tournoiExistant = tournoiService.getTournoiById(id);

        if (tournoiExistant != null) {
            tournoiExistant.setNom(tournoiModifie.getNom()); // Mise à jour des champs
            tournoiExistant.setJeu(tournoiModifie.getJeu());
            tournoiExistant.setFormat(tournoiModifie.getFormat());
            tournoiExistant.setRegles(tournoiModifie.getRegles());
            tournoiExistant.setMaxJoueurs(tournoiModifie.getMaxJoueurs());
            tournoiExistant.setDateDebut(tournoiModifie.getDateDebut());
            tournoiExistant.setDateFin(tournoiModifie.getDateFin());

            tournoiService.creerTournoi(tournoiExistant); // Sauvegarde les modifications

            return "redirect:/tournoi/" + id;
        }
        else {
            return "error/404";
        }
    }


    // ----- Delete -----

    // Supprimer un tournoi (annuler)
    @PostMapping("/tournoi/{id}/supprimer")
    public String supprimerTournoi(@PathVariable("id") int id) {
        tournoiService.supprimerTournoi(id);
        return "redirect:/tournois";
    }


    // ---- Recherche -----

    /**
     * Rechercher de sujets par leur titre approximatif
     */
    @GetMapping("/recherche")
    public String recherche(@RequestParam("recherche") String recherche, @RequestParam(value = "tolerance", defaultValue = "2") int tolerance, Model model) {

        // Utilisation de la recherche approximative avec une tolérance donnée
        List<Tournoi> tournois = tournoiService.rechercheApproximativeByNom(recherche, tolerance);

        model.addAttribute("recherche", recherche);
        model.addAttribute("tournois", tournois);
        model.addAttribute("title", "Recherche de tournois"); // Pour le titre de la page

        return "recherche";
    }


}
