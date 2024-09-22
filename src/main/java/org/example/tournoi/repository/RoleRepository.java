package org.example.tournoi.repository;

import org.example.tournoi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByNomRole(String nomRole);
}
