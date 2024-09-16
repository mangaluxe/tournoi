package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StatistiquesJoueur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int matchsJoues;

    private int matchsGagnes;

    private int tournoisJoues;

    private int tournoisGagnes;

    private double tauxVictoire; // Calculé automatiquement (matchs gagnés / matchs joués)

    // ---------- Relations ----------

    @OneToOne
    @JoinColumn(name = "utilisateur_id", nullable = false) // Crée une colonne utilisateur_id sur la table 'statistiques_joueur'
    private Utilisateur utilisateur;

}
