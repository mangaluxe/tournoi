package org.example.tournoi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    private String pseudo;

    private String motdepasse;

    private String nom;

    private String prenom;

    private String email;

    private String avatar;

    private String profil;

    private String preferences;

    // ---------- Relations ----------

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) // Crée une colonne role_id sur la table 'utilisateur'
    private Role role;

}
