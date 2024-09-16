package org.example.tournoi.controller;

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

        model.addAttribute("title", "Tournoi - Bienvenue au tournoi !"); // Pour le title de la page

        return "index"; // Renvoie le nom de la vue "index" pour la page d'accueil
    }



    @GetMapping("/about")
    public String aboutPage(Model model) {

        model.addAttribute("title", "À propos du tournoi");

        return "about";
    }


    // ----- Create -----

    // ----- Update -----

    // ----- Delete -----

}
