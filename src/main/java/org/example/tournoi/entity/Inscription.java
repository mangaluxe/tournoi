package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Inscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true) // Champ optionnel, peut être null
    private Boolean estEligible;

    @Column(nullable = true) // Champ optionnel
    private Boolean estValide;


    // ---------- Relations ----------

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false) // Crée une colonne utilisateur_id sur la table 'inscription'
    private Utilisateur utilisateur; // L'utilisateur inscrit

    @ManyToOne
    @JoinColumn(name = "tournoi_id", nullable = false) // Crée une colonne tournoi_id sur la table 'inscription'
    private Tournoi tournoi; // Le tournoi auquel l'utilisateur s'inscrit

}