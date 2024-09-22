package org.example.tournoi.controller;

import jakarta.validation.Valid;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.UtilisateurService;
import org.example.tournoi.util.Utils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.mindrot.jbcrypt.BCrypt; // Utiliser le hachage de mot de passe BCrypt


@Controller
public class RecuperationController {

    // ========== Propriétés ==========

    private final UtilisateurService utilisateurService;


    // ========== Controller ==========

    public RecuperationController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    /**
     * Formulaire de demande d'infos afin de récupérer le mot de passe
     */
    @GetMapping("/motdepasse-oublie")
    public String recuperationMotDePasse(Model model) {

        model.addAttribute("title", "Récupération de mot de passe");

        return "motdepasse-oublie";
    }


    /**
     * Récupération de mot de passe
     */
    @PostMapping("/motdepasse-oublie")
    public String traiterRecuperation(@Valid @RequestParam String email, @RequestParam String pseudo, Model model) {
        Utilisateur utilisateur = utilisateurService.findByEmailAndPseudo(email, pseudo);

        String nouveauMotdepasse = Utils.generatePassword(); // Utilisation d'une méthode statique que j'ai créé
        String hashedMotdepasse = BCrypt.hashpw(nouveauMotdepasse, BCrypt.gensalt()); // BCrypt.hashpw() pour hasher

        if (utilisateur != null) {
            utilisateur.setMotdepasse(hashedMotdepasse); // setMotdepasse() pour mettre à jour le mot de passe
            utilisateurService.save(utilisateur);

            model.addAttribute("message", "Votre nouveau mot de passe temporaire : " + nouveauMotdepasse);
        }
        else {
            model.addAttribute("error", "Erreur ou utilisateur inexistant.");
        }

        return "motdepasse-oublie";
    }

}