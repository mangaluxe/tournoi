package org.example.tournoi.service;

import jakarta.annotation.PostConstruct;
import org.example.tournoi.dao.RoleRepository;
import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Role;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UtilisateurRepository utilisateurRepository) {
        this.roleRepository = roleRepository;
        this.utilisateurRepository = utilisateurRepository;
        initializeRolesAndAdmin();
    }

    @PostConstruct
    private void initializeRolesAndAdmin() {
        // Création des rôles si la table des rôles est vide
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(1, "USER"));
            roleRepository.save(new Role(2, "ADMIN"));
        }

        // Vérifie  si un admin existe et en crée un dans le cas contraire
        if (utilisateurRepository.count() == 0) {
            Role adminRole = roleRepository.findByNomRole("ADMIN");  // Récupérer le rôle admin
            if (adminRole != null) {
                Utilisateur admin = new Utilisateur();
                admin.setPseudo("Admin");
                admin.setMotdepasse("123"); // MDP à modifier pour des raisons de sécurité
                admin.setEmail("admin@gmail.com");
                admin.setRole(adminRole);
                utilisateurRepository.save(admin);
                System.out.println("Admin créé avec succès");
            } else {
                System.out.println("Le rôle ADMIN n'existe pas, impossible de créer l'administrateur.");
            }
        } else {
            System.out.println("Un administrateur existe déjà.");
        }
    }
}