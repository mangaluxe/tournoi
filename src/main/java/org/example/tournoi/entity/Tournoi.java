package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private LocalDate dateDebut; // Date de début du tournoi

    private LocalDate dateFin; // Date de fin du tournoi

    // ---------- Relations ----------

    @OneToMany(mappedBy = "tournoi")
    private List<Inscription> inscriptions; // Liste des inscriptions liées au tournoi

}

