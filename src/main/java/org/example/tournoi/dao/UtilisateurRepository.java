package org.example.tournoi.dao;

import org.example.tournoi.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    Utilisateur findByPseudo(String pseudo);

    // Trouver un utilisateur par email et pseudo
    Optional<Utilisateur> findByEmailAndPseudo(String email, String pseudo);

    Utilisateur findByPseudoIgnoreCase(String pseudo);

    // Vérifier si un utilisateur existe déjà (lors de l'inscription)
    boolean existsByPseudo(String pseudo);
}
