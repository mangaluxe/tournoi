package org.example.tournoi.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.validation.OnLogin;
import org.example.tournoi.validation.OnRegistration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @RequestMapping("/registration")
    public String formAddUser(Model model) {
        if (!authService.isLogged()) {
            model.addAttribute("title", "Inscription sur Battle Zone"); // Pour le title de la page

            model.addAttribute("utilisateur", new Utilisateur());

            return "registration-form";
        }
        return "index";
    }

    @PostMapping("/registration")
    public String inscriptionForm(@Validated(OnRegistration.class) @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("csrfToken") String csrfToken, HttpSession session, Model model) {
        // --- Vérification du token CSRF ---
        String sessionCsrfToken = (String) session.getAttribute("csrfToken");

        if (sessionCsrfToken == null || !sessionCsrfToken.equals(csrfToken)) {
            model.addAttribute("error", "Erreur token CSRF !");
            return "registration-form";
        }
        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "registration-form"; // Si erreurs de validation, retour au formulaire
        }
        // --- ---

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
        model.addAttribute("utilisateur", new Utilisateur()); // Pour maintenir les données soumises en cas d'erreur de validation. Mettre th:object="${utilisateur}" dans <form th:action="@{/login}" th:object="${utilisateur}" method="post">

        model.addAttribute("title", "Connexion sur Battle Zone"); // Pour le title de la page

        return "connexion-form";
    }


    //PostMapping en prenant en compte la gestion des rôles
    @PostMapping("/login")
    public String connexionForm(@Validated(OnLogin.class) @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult bindingResult, RedirectAttributes redirectAttributes, @RequestParam("csrfToken") String csrfToken, HttpSession session, Model model) {
        // --- Vérification du token CSRF ---
        String sessionCsrfToken = (String) session.getAttribute("csrfToken");

        if (sessionCsrfToken == null || !sessionCsrfToken.equals(csrfToken)) {
            model.addAttribute("error", "Erreur token CSRF !");
            return "connexion-form";
        }
        // --- Validation de formulaire ---
        if (bindingResult.hasErrors()) {
            return "connexion-form"; // Si erreurs de validation, retour au formulaire
        }
        // --- ---

        boolean connected = authService.login(utilisateur.getPseudo(), utilisateur.getMotdepasse());
        System.out.println("Connexion réussie ? " + connected); // Debug

        if (connected) {
            // Récupération du rôle de l'utilisateur connecté
            String role = authService.getCurrentUserRole();

            System.out.println("Rôle utilisateur : " + role); // Debug

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