package org.example.tournoi.controller;

import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.service.TournoiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        return "creer-tournoi";
    }

    /**
     * Créer tournoi
     */
    @PostMapping("/tournoi/nouveau")
    public String creerTournoi(@ModelAttribute("tournoi") Tournoi tournoi) {
        tournoiService.creerTournoi(tournoi); // Sauvegarder le nouveau tournoi
        return "redirect:/tournois";
    }


    // ----- Update -----

    /**
     * Formulaire pour modifier un tournoi
     */
    @GetMapping("/tournoi/{id}/modifier")
    public String formulaireModifierTournoi(@PathVariable("id") int id, Model model) {
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
        tournoiService.supprimerTournoi(id); // Appel au service pour supprimer le tournoi
        return "redirect:/tournois"; // Redirige vers la liste des tournois après suppression
    }


}
