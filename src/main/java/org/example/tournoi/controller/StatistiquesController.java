package org.example.tournoi.controller;

import jakarta.validation.Valid;
import org.example.tournoi.entity.Statistique;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.StatistiquesService;
import org.example.tournoi.service.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;


@Controller
public class StatistiquesController {

    // ========== Propriétés ==========

    private final StatistiquesService statistiquesService;
    private final UtilisateurService utilisateurService;


    // ========== Constructeur ==========

    public StatistiquesController(StatistiquesService statistiquesService, UtilisateurService utilisateurService) {
        this.statistiquesService = statistiquesService;
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Afficher stats
     */
    @GetMapping("/stats")
    public String listerStatistiques(Model model) {

        List<Statistique> statistiques = statistiquesService.getAllStatistiques();

        statistiques.forEach(stat -> { // Calcul le ratio pour chaque stat
            if (stat.getDefaites() != 0) {
                double ratio = (double) stat.getVictoires() / stat.getDefaites();
                stat.setRatioVictoiresDefaites(ratio);
            }
            else {
                stat.setRatioVictoiresDefaites(Double.MAX_VALUE); // Evite division par 0
            }
        });

        model.addAttribute("statistiques", statistiques);

        // Trier la liste par score :
        statistiques.sort(Comparator.comparingInt(Statistique::getScore).reversed());

        model.addAttribute("statistiques", statistiques); // Ajouter les statistiques triées au modèle

        model.addAttribute("title", "Résultats & Statistiques"); // Pour le title de la page

        return "stats/statistiques";
    }


    // ----- Create -----

    /**
     * Formulaire pour ajouter des stats
     */
    @GetMapping("/stats/ajouter")
    public String formulaireAjoutStatistiques(Model model) {

        List<Utilisateur> utilisateurs = utilisateurService.getAllUsers();

        model.addAttribute("utilisateurs", utilisateurs);

        model.addAttribute("statistiques", new Statistique());

        model.addAttribute("title", "Ajouter un résultat"); // Pour le title de la page

        return "stats/ajouter-stat";
    }


    /**
     * Ajouter stats
     */
//    @PostMapping("/stats/ajouter")
//    public String ajouterStatistiques(@ModelAttribute("statistiques") Statistique statistiques, BindingResult bindingResult) {
//
//        // --- Validation de formulaire ---
//        if (bindingResult.hasErrors()) {
//            return "stats/ajouter-stat";
//        }
//        // --- ---
//
//        statistiquesService.creerStatistiques(statistiques);
//
//        return "redirect:/stats";
//    }
    @PostMapping("/stats/ajouter")
    public String ajouterStatistiques(@Valid @ModelAttribute("statistiques") Statistique statistiques, BindingResult bindingResult, Model model) {

        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            List<Utilisateur> utilisateurs = utilisateurService.getAllUsers();
            model.addAttribute("utilisateurs", utilisateurs);
            return "stats/ajouter-stat"; // Retourner au formulaire en cas d'erreur
        }

        // Vérifier si l'utilisateur a déjà des statistiques
        Utilisateur utilisateur = statistiques.getUtilisateur();
        if (statistiquesService.existsByUtilisateur(utilisateur)) {
            return "redirect:/stats";
        }
        else {
            // Sinon, on crée une nouvelle stat
            statistiquesService.creerStatistiques(statistiques);
        }

        return "redirect:/stats";
    }

}
