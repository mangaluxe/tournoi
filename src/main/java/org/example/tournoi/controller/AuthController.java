package org.example.tournoi.controller;

import jakarta.validation.Valid;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @RequestMapping("/registration")
    public String formAddUser(Model model) {
        if (!authService.isLogged()) {
            model.addAttribute("utilisateur", new Utilisateur());
            return "registration-form";
        }
        return "index";
    }

//    @PostMapping("/registration")
//    public String inscriptionForm(@ModelAttribute("utilisateur") Utilisateur utilisateur, Model model, RedirectAttributes redirectAttributes) {
//        try {
//            authService.register(utilisateur);
//            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter."); // Message flash
//            return "redirect:/";
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("error", e.getMessage());
//            return "registration-form";
//        }
//    }

    @PostMapping("/registration")
    public String inscriptionForm(@ModelAttribute("utilisateur") @Valid Utilisateur utilisateur, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }
        try {
            authService.register(utilisateur);
            redirectAttributes.addFlashAttribute("successMessage", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/registration";
        }
    }

    @RequestMapping("/login")
    public String connexion(Model model) {
        return "connexion-form";
    }

//    @PostMapping("/login")
//    public String connexionForm(@ModelAttribute("pseudo") String pseudo, @ModelAttribute("motdepasse") String motdepasse, RedirectAttributes redirectAttributes){
//        boolean connected = authService.login(pseudo, motdepasse);
//        if(connected){
//            redirectAttributes.addFlashAttribute("successMessage", "Connexion réussie !"); // Message flash
//
//            return "redirect:/";
//        }
//        redirectAttributes.addFlashAttribute("errorMessage", "Identifiants incorrects !"); // Message flash
//        return "redirect:/login";
//    }


    //PostMapping en prenant en compte la gestion des rôles
    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("pseudo") String pseudo, @ModelAttribute("motdepasse") String motdepasse, RedirectAttributes redirectAttributes) {
        boolean connected = authService.login(pseudo, motdepasse);
        if (connected) {
            // Récupération du rôle de l'utilisateur connecté
            String role = authService.getCurrentUserRole();

            // Ajout d'un message de succès à la session
            redirectAttributes.addFlashAttribute("successMessage", "Connexion réussie !"); // Message flash
            return "redirect:/";  // Redirection normale après connexion réussie
        }

        // En cas d'identifiants incorrects
        redirectAttributes.addFlashAttribute("errorMessage", "Identifiants incorrects !"); // Message flash
        return "redirect:/login"; // Retour à la page de connexion
    }


    @RequestMapping("/logout")
    public String deconnexion(Model model) {
        authService.logout();
        return "index";
    }
}