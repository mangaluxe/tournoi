package org.example.tournoi.controller;

import jakarta.validation.Valid;
import org.example.tournoi.entity.Utilisateur;
import org.example.tournoi.service.AuthService;
import org.example.tournoi.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthService authService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService, AuthService authService) {
        this.utilisateurService = utilisateurService;
        this.authService = authService;
    }


    @RequestMapping("/inscription")
    public String formAddUser(Model model) {
        if (!authService.isLogged()) {
            model.addAttribute("utilisateur", new Utilisateur());
            return "registration-form";
        }
        return "index";
    }

//    @PostMapping("/add")
//    public String addUser(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur, BindingResult result) {
//        if (result.hasErrors()) {
//            return "inscription";
//        } else {
//            if (utilisateur.getId() != 0) { //ne fonctionne pas avec null. Solution, remplacer int par Interger ou null par zéro
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
            return "list";
        }
        return "index";
    }

    @RequestMapping("/utilisateur/{id}")
    public String showUser(@PathVariable("id") int id, Model model) {
        if (authService.isLogged()) {
            model.addAttribute("utilisateur", utilisateurService.getUserById(id));
            return "detail";
        }
        return "index";
    }

//    @RequestMapping("/delete")
//    public String deleteUser(@RequestParam("id") int id) {
//        if (authService.isLogged()) {
//            utilisateurService.deleteUser(id);
//            return "redirect:/utilisateurs";
//        }
//        return "index";
//    }

//    @RequestMapping("/update")
//    public String formUpdate(@RequestParam("utilisateurId") int id, Model model) {
//        if (authService.isLogged()) {
//            Utilisateur utilisateur = utilisateurService.getUserById(id);
//            model.addAttribute("utilisateur", utilisateur);
//            return "inscription";
//        }
//        return "index";
//    }
}