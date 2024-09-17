package org.example.tournoi.dao;

import org.example.tournoi.entity.Role;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNomRole(String nomRole);
}
