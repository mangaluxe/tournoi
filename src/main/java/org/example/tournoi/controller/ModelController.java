package org.example.tournoi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.SecureRandom;
import java.util.Base64;


@ControllerAdvice // Annotation globale qui permet d'appliquer des méthodes à tous les contrôleurs
public class ModelController {

    // ========== Propriétés ==========

    // ========== Controller ==========

    // ========== Méthodes ==========

    @ModelAttribute("csrfToken")
    public String addCsrfTokenToModel(HttpSession session) {

        String csrfToken = (String) session.getAttribute("csrfToken");

        System.out.println("Session ID: " + session.getId()); // Debug
        System.out.println("Token CSRF actuel: " + csrfToken); // Debug

        if (csrfToken == null) { // Génère un token CSRF si ce n'est pas déjà présent dans la session
            SecureRandom secureRandom = new SecureRandom();
            byte[] token = new byte[32];
            secureRandom.nextBytes(token);
            csrfToken = Base64.getEncoder().encodeToString(token);

            System.out.println("Génération d'un nouveau token CSRF: " + csrfToken);

            session.setAttribute("csrfToken", csrfToken); // Pour qu'on puisse utiliser dans le html
        }

        return csrfToken;
    }


}
