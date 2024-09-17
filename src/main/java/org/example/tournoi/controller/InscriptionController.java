package org.example.tournoi.controller;

import jakarta.servlet.http.HttpSession;
import org.example.tournoi.entity.Inscription;
import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.InscriptionService;
import org.example.tournoi.service.TournoiService;
import org.example.tournoi.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class InscriptionController {

    // ========== Propriétés ==========

    private final InscriptionService inscriptionService;
    private final TournoiService tournoiService;
    private final UtilisateurService utilisateurService;


    // ========== Constructeur ==========

    public InscriptionController(InscriptionService inscriptionService, TournoiService tournoiService, UtilisateurService utilisateurService) {
        this.inscriptionService = inscriptionService;
        this.tournoiService = tournoiService;
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    // ----- Create -----

    /**
     * Formulaire pour inscrire un utilisateur à un tournoi
     */
    @GetMapping("/inscription-tournoi/nouveau")
    public String formulaireInscriptionTournoi(Model model) {

        List<Tournoi> tournois = tournoiService.getAllTournois();

        model.addAttribute("title", "Inscription aux tournois"); // Pour le title de la page
        model.addAttribute("tournois", tournois);
        model.addAttribute("inscription", new Inscription());

        return "creer-inscription-tournoi";
    }

    /**
     * Inscrire un utilisateur à un tournoi
     */
    @PostMapping("/inscription-tournoi/nouveau")
    public String creerInscriptionTournoi(@ModelAttribute("inscription") Inscription inscription, HttpSession session) {

        Integer utilisateurId = (Integer) session.getAttribute("pseudo_id"); // Récupérer l'ID de l'utilisateur connecté depuis la session

        if (utilisateurId != null) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(utilisateurId);
            inscription.setUtilisateur(utilisateur);

            if (inscription.getEstEligible() == null) {
                inscription.setEstEligible(false);
            }

            if (inscription.getEstValide() == null) {
                inscription.setEstValide(false);
            }

            inscriptionService.creerInscription(inscription);

            return "redirect:/inscriptions-tournois";
        }
        else {
            return "redirect:/login";
        }
    }


    // ----- Read -----

    /**
     * Afficher la liste des inscriptions
     */
    @GetMapping("/inscriptions-tournois")
    public String listeInscriptionsTournois(Model model) {

        model.addAttribute("title", "Toutes les inscription aux tournois"); // Pour le title de la page

        List<Inscription> inscriptions = inscriptionService.getAllInscriptions();

        model.addAttribute("inscriptions", inscriptions);

        return "inscriptions-tournois";
    }


    /**
     * Afficher mes inscriptions
     */
    @GetMapping("/mes-inscriptions")
    public String mesInscriptionsTournois(HttpSession session, Model model) {

        Integer utilisateurId = (Integer) session.getAttribute("pseudo_id"); // Récupérer l'ID de l'utilisateur connecté depuis la session

        // Vérifier que l'utilisateur est bien connecté
        if (utilisateurId != null) {
            List<Inscription> inscriptions = inscriptionService.getInscriptionsByUtilisateurId(utilisateurId); // Récupérer les inscriptions de cet utilisateur
            model.addAttribute("inscriptions", inscriptions);
        }
        else {
            return "redirect:/login";
        }

        model.addAttribute("title", "Mes inscriptions aux tournois");

        return "mes-inscriptions";
    }


    /**
     * Afficher une inscription
     */
    @GetMapping("/inscription/{id}")
    public String monInscriptionTournoi(@PathVariable("id") int id, HttpSession session, Model model) {

        Inscription inscription = inscriptionService.getInscriptionById(id);
        Integer utilisateurId = (Integer) session.getAttribute("pseudo_id");

        // Vérifier que l'inscription existe et que l'utilisateur est bien le propriétaire
        if (inscription != null && inscription.getUtilisateur().getId() == utilisateurId) {
            model.addAttribute("inscription", inscription);
            model.addAttribute("title", "Mon inscription");
            return "mon-inscription";
        }
        else {
            return "error/404"; // Si l'inscription n'existe pas ou l'utilisateur n'est pas propriétaire, renvoyer une erreur 404
        }
    }


    // ----- Delete -----

    /**
     * Supprimer une inscription
     */
    @PostMapping("/inscription-tournoi/{id}/supprimer")
    public String supprimerInscriptionTournois(@PathVariable("id") int id) {
        inscriptionService.supprimerInscription(id);
        return "redirect:/inscriptions-tournois";
    }


}
