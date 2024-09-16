package org.example.tournoi.service;


import jakarta.servlet.http.HttpSession;
import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    private HttpSession httpSession;

    public AuthService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur register(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public boolean login(String pseudo, String motdepasse) {
        Utilisateur utilisateur = utilisateurRepository.findByUsername(pseudo);
        if(utilisateur.getMotdepasse().equals(motdepasse)) {
            httpSession.setAttribute("pseudo", utilisateur.getPseudo());
            httpSession.setAttribute("login", "OK");
            return true;
        }
        return false;
    }

    public boolean isLogged() {
        try {
            String isLogged = httpSession.getAttribute("login").toString();
            return isLogged.equals("OK");
        } catch (Exception ex) {
            return false;
        }
    }

    public void logout() {
        httpSession.invalidate();
    }
}
