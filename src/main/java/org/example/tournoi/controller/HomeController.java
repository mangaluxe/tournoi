package org.example.tournoi.controller;

import org.mindrot.jbcrypt.BCrypt; // Utiliser le hachage de mot de passe BCrypt
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    // ========== Propriétés ==========

    // ========== Constructeur ==========

    public HomeController() {
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Page d'Accueil
     */
    @GetMapping("/") // URL : http://localhost:8080/
    public String home(Model model) {

        model.addAttribute("title", "Tournoi Battle Zone"); // Pour le title de la page

        return "index"; // Renvoie le nom de la vue "index" pour la page d'accueil
    }


    /**
     * Mentions Légales
     */
    @GetMapping("/mentions-legales")
    public String mentionsLegales(Model model) {

        model.addAttribute("title", "Mentions légales - Battle Zone"); // Pour le title de la page

        return "info/mentions-legales";
    }


    /**
     * Règlement
     */
    @GetMapping("/reglement")
    public String reglement(Model model) {

        model.addAttribute("title", "Règlement - Battle Zone"); // Pour le title de la page

        return "info/reglement";
    }


    /**
     * Test de Cryptage BCrypt
     */
    @GetMapping("/test")
    public String testCryptage(Model model) {
        String password = "1234";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); // BCrypt.hashpw() pour hasher
        System.out.println("Mot de passe hashé : " + hashed); // Exemple: $2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze

        model.addAttribute("title", "Test Cryptage de mot de passe"); // Pour le title de la page
        model.addAttribute("info", hashed);
        return "info/test";
    }

    /**
     * Test de Décryptage BCrypt
     */
    @GetMapping("/test2")
    public String testDecryptage(Model model) {
        String pseudo = "haiou";
        String motdepasse = "1234";
        String hashedPassword = "$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze"; // Hash de "1234"

        if (pseudo.equals("haiou") && BCrypt.checkpw(motdepasse, hashedPassword)) { // BCrypt.checkpw() pour vérifier hashage
            System.out.println("Pseudo et mot de passe correctes");
            model.addAttribute("info", "Pseudo et mot de passe correctes");
        }
        else {
            System.out.println("Erreur pseudo ou mot de passe");
            model.addAttribute("info", "Erreur pseudo ou mot de passe");
        }

        model.addAttribute("title", "Test Décryptage de mot de passe"); // Pour le title de la page
        return "info/test";
    }


}
