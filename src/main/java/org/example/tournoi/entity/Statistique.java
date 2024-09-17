package org.example.tournoi.entity;

import jakarta.persistence.*;
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

    private int score;  // Score ou nombre de points obtenus par le joueur

    private int victoires;  // Nombre de victoires

    private int defaites;   // Nombre de défaites

    // ---------- Relations ----------

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false) // Crée une colonne utilisateur_id sur la table 'statistiques_joueur'
    private Utilisateur utilisateur;

}
