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

    private final UtilisateurService utilisateurService;
    private final AuthService authService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, AuthService authService) {
        this.utilisateurService = utilisateurService;
        this.authService = authService;
    }


//    @PostMapping("/add")
//    public String addUser(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result) {
//        if (result.hasErrors()) {
//            return "inscription";
//        } else {
//            if (utilisateur.getId() != 0) { //ne fonctionne pas avec null. Solution: remplacer int par Interger ou null par zéro
//                utilisateurService.updateUser(utilisateur.getId(), utilisateur);
//            } else {
//                utilisateurService.createUser(utilisateur);
//            }
//            return "redirect:/utilisateur/" + utilisateur.getId();
//        }
//    }

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

//    @GetMapping("/espace-membre/{pseudo}")
//    public String espaceMembre(@PathVariable("pseudo") String pseudo, HttpSession session, Model model) {
//        // Vérifie si l'utilisateur est connecté
//        if (authService.isLogged()) {
//            Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
//            model.addAttribute("utilisateur", utilisateur);
//            return "espace-membre";
//        }
//        return "redirect:/login";
//    }

    @GetMapping("/espace-membre")
    public String espaceMembre(HttpSession session, Model model) {
        // Vérifie si l'utilisateur est connecté
        if (authService.isLogged()) {
            // Récupère le pseudo de l'utilisateur depuis la session
            String pseudo = (String) session.getAttribute("pseudo");
            if (pseudo != null) {
                Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);
                model.addAttribute("utilisateur", utilisateur);
                return "espace-membre";
            } else {
                // Si le pseudo n'est pas trouvé dans la session, redirige vers la page de connexion
                return "redirect:/login";
            }
        }
        return "redirect:/login";
    }


//    @RequestMapping("/mon-espace/{id}")
//    public String showStudent(@PathVariable("id") int id, Model model) {
//        if (authService.isLogged()) {
//            model.addAttribute("utilisateur", utilisateurService.getUtilisateurById(id));
//            return "espace-membre";
//        }
//        return "redirect:/login";



//    @RequestMapping("/delete")
//    public String deleteUser(@RequestParam("id") int id) {
//        if (authService.isLogged()) {
//            utilisateurService.deleteUser(id);
//            return "redirect:/utilisateurs";
//        }
//        return "index";
//    }

    @RequestMapping("/update")
    public String formUpdate(@RequestParam("utilisateurId") int id, Model model) {
        if (authService.isLogged()) {
            Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
            model.addAttribute("utilisateur", utilisateur);
            return "update-form";
        }
        return "index";
    }

//    @PostMapping("/update")
//    public String updateUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
//                                    BindingResult bindingResult,
//                                    RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            return "update-form";
//        }
//        utilisateurService.updateUser(utilisateur);
//        // Message si modification réussie
//        redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès");
//        // Redirige vers une page de confirmation ou de détails
//        return "redirect:/espace-membre"; // Changez ceci selon votre route de redirection
//    }

    @PostMapping("/update")
    public String updateUtilisateur(@RequestParam("id") int id,
                                    @Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
                                    BindingResult bindingResult,
                                    RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "update-form";
        }
        utilisateurService.updateUser(id, utilisateur);
        // Message si modification réussie
        redirectAttributes.addFlashAttribute("message", "Utilisateur mis à jour avec succès");

        return "redirect:/espace-membre";
    }



}