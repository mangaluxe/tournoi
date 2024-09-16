package org.example.tournoi.controller;

import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


// Non nécessaire car fait doublon avec la méthode dans UtilisateurController
//    @PostMapping("/inscription")
//    public String inscriptionForm(@ModelAttribute("utilisateur") Utilisateur utilisateur){
//        authService.register(utilisateur);
//        return "redirect:/";
//    }

    @RequestMapping("/login")
    public String connexion(Model model){
        return "connexion-form";
    }

    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("pseudo") String pseudo, @ModelAttribute("motdepasse") String motdepasse){
        boolean connected = authService.login(pseudo, motdepasse);
        if(connected){
            return "redirect:/";
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String deconnexion(Model model){
        authService.logout();
        return "index";
    }
}