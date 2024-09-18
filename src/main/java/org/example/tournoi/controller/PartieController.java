//package org.example.tournoi.controller;
//
//import org.example.tournoi.entity.Partie;
//import org.example.tournoi.entity.Utilisateur; // Pour la sélection des joueurs
//import org.example.tournoi.service.PartieService;
//import org.example.tournoi.service.UtilisateurService; // Pour récupérer la liste des joueurs
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class PartieController {
//
//    // ========== Propriétés ==========
//
//    private final PartieService partieService;
//    private final UtilisateurService utilisateurService;
//
//    // ========== Constructeur ==========
//
//    @Autowired
//    public PartieController(PartieService partieService, UtilisateurService utilisateurService) {
//        this.partieService = partieService;
//        this.utilisateurService = utilisateurService;
//    }
//
//    // ========== Méthodes ==========
//
//    /**
//     * Afficher la liste des parties
//     */
//    @GetMapping("/parties")
//    public String listerParties(Model model) {
//        List<Partie> parties = partieService.getAllParties();
//        model.addAttribute("parties", parties);
//        return "match/parties";
//    }
//
//    /**
//     * Afficher les détails d'une partie
//     */
//    @GetMapping("/partie/{id}")
//    public String detailsPartie(@PathVariable("id") int id, Model model) {
//        Partie partie = partieService.getPartieById(id);
//        if (partie != null) {
//            model.addAttribute("partie", partie);
//            return "match/partie";
//        }
//        return "error/404";
//    }
//
//    /**
//     * Formulaire de création d'une partie
//     */
//    @GetMapping("/partie/nouveau")
//    public String formulaireCreationPartie(Model model) {
//
////        Partie partie = new Partie(); // Crée un objet vide pour le formulaire
////        model.addAttribute("partie", partie);
////
////        // Récupérer la liste des joueurs pour les dropdowns
////        List<Utilisateur> joueurs = utilisateurService.getAllUtilisateurs();
////        model.addAttribute("joueurs", joueurs);
//
//        return "match/creer-partie";
//    }
//
//
//    /**
//     * Créer une nouvelle partie
//     */
//    @PostMapping("/partie/nouveau")
//    public String creerPartie(@ModelAttribute("partie") Partie partie) {
//        partieService.creerPartie(partie);
//        return "redirect:/parties";
//    }
//
//
//    /**
//     * Formulaire de modification d'une partie
//     */
//    @GetMapping("/partie/{id}/modifier")
//    public String formulaireModifierPartie(@PathVariable("id") int id, Model model) {
//        Partie partie = partieService.getPartieById(id);
//        if (partie != null) {
////            model.addAttribute("partie", partie);
////
////            // Récupérer la liste des joueurs pour les dropdowns
////            List<Utilisateur> joueurs = utilisateurService.getAllUtilisateurs();
////            model.addAttribute("joueurs", joueurs);
//
//            return "match/creer-partie"; // Réutilise le même formulaire que la création
//        }
//        return "error/404";
//    }
//
//
//    /**
//     * Mettre à jour une partie
//     */
//    @PostMapping("/partie/{id}/modifier")
//    public String modifierPartie(@PathVariable("id") int id, @ModelAttribute("partie") Partie partie) {
//        partieService.modifierPartie(id, partie);
//        return "redirect:/parties";
//    }
//
//    /**
//     * Supprimer une partie
//     */
//    @PostMapping("/partie/{id}/supprimer")
//    public String supprimerPartie(@PathVariable("id") int id) {
//        partieService.supprimerPartie(id);
//        return "redirect:/parties";
//    }
//}
