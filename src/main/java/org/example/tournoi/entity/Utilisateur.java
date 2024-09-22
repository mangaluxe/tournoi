package org.example.tournoi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tournoi.validation.OnLogin; // Interface que j'ai créé pour obliger durant la connexion
import org.example.tournoi.validation.OnRegistration; // Interface que j'ai créé pour obliger durant l'inscription

import java.time.LocalDate;


@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Entity
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indiquent que le champ id est la clé primaire et qu'il sera auto-généré par la base de données (génération automatique de l'ID)
    private int id;

    @NotBlank(message = "Pseudo obligatoire", groups = {OnLogin.class, OnRegistration.class}) // OnLogin et OnRegistration : Obligatoire à l'inscription et connexion
    @Size(min = 3, max = 100, message = "Entre 3 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
    @Column(unique = true)
    private String pseudo;

    @NotBlank(message = "Mot de passe obligatoire")
    @Size(min = 3, max = 100, message = "Entre 3 et 100 caractères", groups = {OnLogin.class, OnRegistration.class})
    private String motdepasse;

    private String nom;

    private String prenom;

    @NotBlank(message = "Email obligatoire", groups = OnRegistration.class) // OnRegistration uniquement : Obligatoire uniquement lors de l'inscription
    @Email(message = "Email invalide", groups = OnRegistration.class)
    private String email;

    private LocalDate dateNaissance;

    private String avatar;

    private String profil;

    private String preferences;


    // ---------- Relations ----------

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) // Crée une colonne role_id sur la table 'utilisateur'
    private Role role;

}
