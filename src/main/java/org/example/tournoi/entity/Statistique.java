package org.example.tournoi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Statistique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Score obligatoire") // Pour int, utiliser @NotNull au lieu de @NotBlank
//    private int score; // int pose problème avec la validation de formulaire, il faut utiliser Integer
    private Integer score; // Score joueur

    @NotNull(message = "Nb victoires obligatoire")
    private Integer victoires; // Nb victoires

    @NotNull(message = "Nb défaites obligatoire")
    private Integer defaites; // Nb défaites


    // ---------- Relations ----------

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false) // Crée une colonne utilisateur_id sur la table 'statistiques_joueur'
    private Utilisateur utilisateur;


    // ---------- @Transient : Ce qu'on ne persiste pas en BDD ----------

    @Transient
    private double ratioVictoiresDefaites;

}
//