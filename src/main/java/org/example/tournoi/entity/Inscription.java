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

    private boolean estEligible; // Vérifie si l'utilisateur est éligible ou non

    private boolean estValide; // Valide l'inscription

    // ---------- Relations ----------

    @ManyToOne
    @JoinColumn(name = "utilisateur_id", nullable = false) // Crée une colonne utilisateur_id sur la table 'inscription'
    private Utilisateur utilisateur; // L'utilisateur inscrit

    @ManyToOne
    @JoinColumn(name = "tournoi_id", nullable = false) // Crée une colonne tournoi_id sur la table 'inscription'
    private Tournoi tournoi; // Le tournoi auquel l'utilisateur s'inscrit

}
