package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tournoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;

    private String jeu;

    private String format;

    private String regles;

    private int maxJoueurs;

    private LocalDateTime dateDebut; // Datetime de début du tournoi

    private LocalDateTime dateFin; // Datetime de fin du tournoi

    // ---------- Relations ----------

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.REMOVE) // Cascade pour supprimer automatiquement les inscriptions si un tournoi est effacé
    private List<Inscription> inscriptions; // Liste des inscriptions liées au tournoi

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.REMOVE) // Cascade pour supprimer automatiquement les parties si un tournoi est effacé
    private List<Partie> parties; // Liste des parties liées au tournoi

}

