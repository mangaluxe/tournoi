package org.example.tournoi.service;

import jakarta.servlet.http.HttpSession;
import org.example.tournoi.dao.RoleRepository;
import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Role;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt; // Utiliser le hachage de mot de passe BCrypt


@Service
public class AuthService {

    // ========== Propriétés ==========

    private final UtilisateurRepository utilisateurRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private HttpSession httpSession;


    // ========== Constructeur ==========

    @Autowired
    public AuthService(UtilisateurRepository utilisateurRepository, RoleRepository roleRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.roleRepository = roleRepository;
    }


    // ========== Méthodes ==========

    public Utilisateur register(Utilisateur utilisateur) {
        if (utilisateurRepository.existsByPseudo(utilisateur.getPseudo())) {
            throw new IllegalArgumentException("Ce pseudo est déjà utilisé, veuillez en choisir un autre.");
        }
        Role role = roleRepository.findByNomRole("USER"); // Attribution du rôle USER par défaut
        utilisateur.setRole(role);
        // Pour Eviter que l'ID soit manipulé . La BDD générera automatiquement un ID unique pour le nouvel utilisateur
        utilisateur.setId(0);

        String hashedPassword = BCrypt.hashpw(utilisateur.getMotdepasse(), BCrypt.gensalt()); // Hasher le mot de passe
        utilisateur.setMotdepasse(hashedPassword); // Remplacer le mot de passe par sa version hashée

        return utilisateurRepository.save(utilisateur);


    }


//    public boolean login(String pseudo, String motdepasse) {
//        Utilisateur utilisateur = utilisateurRepository.findByPseudo(pseudo);
//        if(utilisateur.getMotdepasse().equals(motdepasse)) {
//            httpSession.setAttribute("pseudo", utilisateur.getPseudo());
//            httpSession.setAttribute("login", "OK");
//            return true;
//        }
//        return false;
//    }

    public boolean login(String pseudo, String motdepasse) {
        Utilisateur utilisateur = utilisateurRepository.findByPseudoIgnoreCase(pseudo);

        // Vérifie si l'utilisateur est trouvé
        if (utilisateur == null) {
            System.out.println("Utilisateur non trouvé pour le pseudo : " + pseudo);
            return false;
        }

//        if (utilisateur.getMotdepasse().equals(motdepasse)) { // Compare les mots de passe
        if (utilisateur != null && BCrypt.checkpw(motdepasse, utilisateur.getMotdepasse())) { // Compare le mot de passe avec la version hashée de la BDD
            httpSession.setAttribute("pseudo", utilisateur.getPseudo());
            httpSession.setAttribute("pseudo_id", utilisateur.getId());
            httpSession.setAttribute("login", "OK");
            httpSession.setAttribute("role", utilisateur.getRole().getNomRole());
            return true;
        }

        // Si les mots de passe ne correspondent pas
        System.out.println("Mot de passe incorrect pour le pseudo : " + pseudo);
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

    public String getCurrentUserRole() {
        return httpSession.getAttribute("role").toString();
    }

}
