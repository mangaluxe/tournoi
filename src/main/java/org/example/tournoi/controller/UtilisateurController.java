package org.example.tournoi.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UtilisateurController {

    // ========== Propriétés ==========

    private final UtilisateurService utilisateurService;
    private final AuthService authService;


    // ========== Controller ==========

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, AuthService authService) {
        this.utilisateurService = utilisateurService;
        this.authService = authService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @RequestMapping("/utilisateurs")
    public String showUsers(@RequestParam(name = "search", required = false) String search, Model model) {
        if (authService.isLogged()) {
            if (search == null) {
                model.addAttribute("utilisateurs", utilisateurService.getAllUsers());
            } else {
                model.addAttribute("utilisateurs", utilisateurService.searchUser(search));
            }
            return "list-utilisateurs";
        }
        return "index";
    }

    @RequestMapping("/utilisateur/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        if (authService.isLogged()) {
            model.addAttribute("utilisateur", utilisateurService.getUtilisateurById(id));
            return "detail";
        }
        return "index";
    }


    @GetMapping("/espace-membre")
    public String espaceMembre(HttpSession session, Model model) {
        if (authService.isLogged()) { // Vérifie si l'utilisateur est connecté

            String pseudo = (String) session.getAttribute("pseudo");// Récupère le pseudo de l'utilisateur depuis la session
            if (pseudo != null) {
                Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
                model.addAttribute("utilisateur", utilisateur);


                model.addAttribute("title", "Espace membre"); // Pour le titre de la page

                return "espace-membre";
            } else {
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }


    // ----- Update -----

    @RequestMapping("/update")
    public String formUpdate(@RequestParam("utilisateurId") int id, Model model) {
        if (authService.isLogged()) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            model.addAttribute("utilisateur", utilisateur);

            model.addAttribute("title", "Espace membre - Modifier"); // Pour le titre de la page

            return "update-form";
        }
        return "index";
    }


    @PostMapping("/update")
    public String updateUtilisateur(@RequestParam("id") int id,
                                    @Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-form";
        }
        utilisateurService.updateUser(id, utilisateur);
        redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès");

        return "redirect:/espace-membre";
    }


}