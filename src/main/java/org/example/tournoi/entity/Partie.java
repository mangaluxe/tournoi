//package org.example.tournoi.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDateTime;
//
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//public class Partie {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    private LocalDateTime dateHeure; // Date et heure du match
//
//    private int resultat; // Peut stocker le résultat du match (ex: 1 pour joueur1 gagne, 2 pour joueur2 gagne)
//
//    private boolean estValideParAdmin; // Validation du résultat par l'admin
//
//    // ---------- Relations ----------
//
//    @ManyToOne
//    @JoinColumn(name = "tournoi_id", nullable = false) // Crée une colonne tournoi_id sur la table 'match'
//    private Tournoi tournoi; // Tournoi auquel le match est associé
//
//    @ManyToOne
//    @JoinColumn(name = "joueur1_id", nullable = false) // Crée une colonne joueur1_id sur la table 'match'
//    private Utilisateur joueur1;
//
//    @ManyToOne
//    @JoinColumn(name = "joueur2_id", nullable = false) // Crée une colonne joueur2_id sur la table 'match'
//    private Utilisateur joueur2;
//
//}
