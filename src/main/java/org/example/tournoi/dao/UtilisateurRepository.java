package org.example.tournoi.dao;

import org.example.tournoi.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Utilisateur findByPseudo(String pseudo);
}
