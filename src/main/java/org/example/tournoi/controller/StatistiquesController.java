package org.example.tournoi.controller;

import org.example.tournoi.entity.Statistique;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.StatistiquesService;
import org.example.tournoi.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class StatistiquesController {

    private final StatistiquesService statistiquesService;
    private final UtilisateurService utilisateurService;

    public StatistiquesController(StatistiquesService statistiquesService, UtilisateurService utilisateurService) {
        this.statistiquesService = statistiquesService;
        this.utilisateurService = utilisateurService;
    }

    // Afficher le formulaire pour ajouter des statistiques
    @GetMapping("/stats/ajouter")
    public String formulaireAjoutStatistiques(Model model) {
        List<Utilisateur> utilisateurs = utilisateurService.getAllUsers();
        model.addAttribute("utilisateurs", utilisateurs);
        model.addAttribute("statistiques", new Statistique());
        return "stats/ajouter-statistiques";
    }

    // Gérer la soumission du formulaire
    @PostMapping("/stats/ajouter")
    public String ajouterStatistiques(@ModelAttribute("statistiques") Statistique statistiques, BindingResult bindingResult) {

        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "/stats/ajouter"; // Si erreurs de validation, retour au formulaire
        }
        // --- ---

        statistiquesService.creerStatistiques(statistiques);

        return "redirect:/stats/liste";
    }

    // Afficher la liste des statistiques
    @GetMapping("/stats/liste")
    public String listerStatistiques(Model model) {
        List<Statistique> statistiques = statistiquesService.getAllStatistiques();
        model.addAttribute("statistiques", statistiques);
        return "stats/liste-statistiques";
    }

}
