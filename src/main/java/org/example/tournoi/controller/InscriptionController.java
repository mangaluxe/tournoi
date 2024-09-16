package org.example.tournoi.controller;

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

//        List<Tournoi> tournois = tournoiService.getAllTournois();
//
//        List<Utilisateur> utilisateurs = utilisateurService.getAllUtilisateurs(); // Attends que Rémi termine l'utilisateur
//
//        model.addAttribute("tournois", tournois);
//        model.addAttribute("utilisateurs", utilisateurs);
//        model.addAttribute("inscription", new Inscription());

        return "creer-inscription-tournoi";
    }

    /**
     * Inscrire un utilisateur à un tournoi
     */
    @PostMapping("/inscription-tournoi/nouveau")
    public String creerInscriptionTournoi(@ModelAttribute("inscription") Inscription inscription) {
        inscriptionService.creerInscription(inscription);
        return "redirect:/inscriptions-tournois";
    }


    // ----- Read -----

    /**
     * Afficher la liste des inscriptions
     */
    @GetMapping("/inscriptions-tournois")
    public String listeInscriptionsTournois(Model model) {

        List<Inscription> inscriptions = inscriptionService.getAllInscriptions();

        model.addAttribute("inscriptions", inscriptions);

        return "inscriptions-tournois";
    }


    // ----- Delete -----

    /**
     * Supprimer une inscription
     */
    @PostMapping("/inscription-tournoi/{id}/supprimer")
    public String supprimerInscriptionTournois(@PathVariable("id") int id) {
        inscriptionService.supprimerInscription(id);
        return "redirect:/inscriptions-tournois"; // Redirige vers la liste des inscriptions après suppression
    }


}
