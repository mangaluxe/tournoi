package org.example.tournoi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tournoi.validation.MyValid;
import java.time.format.DateTimeFormatter;

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

    @NotBlank(message = "Nom du tournoi obligatoire")
    private String nom;

    @NotBlank(message = "Nom du jeu obligatoire")
    private String jeu;

    private String format;

    @NotBlank(message = "Règle obligatoire")
    @MyValid(message = "Gros mots interdits") // Validation personnalisée
    @Column(columnDefinition = "text")
    private String regles;

    @NotNull(message = "Nb max joueurs obligatoire")
    private Integer maxJoueurs;

    @NotNull(message = "Datetime de début du tournoi obligatoire") // 💡 @NotBlank ne marche pas avec Date, il faut utiliser @NotNull
    private LocalDateTime dateDebut; // Datetime de début du tournoi

    @NotNull(message = "Datetime de fin du tournoi obligatoire")
    private LocalDateTime dateFin; // Datetime de fin du tournoi


    // ---------- Relations ----------

    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.REMOVE) // Cascade pour supprimer automatiquement les inscriptions si un tournoi est effacé
    private List<Inscription> inscriptions; // Liste des inscriptions liées au tournoi

//    @OneToMany(mappedBy = "tournoi", cascade = CascadeType.REMOVE) // Cascade pour supprimer automatiquement les parties si un tournoi est effacé
//    private List<Partie> parties; // Liste des parties liées au tournoi


    // ---------- @Transient : Ne pas persister dans BDD ----------

    @Transient
    private String dateDebutFormatted;

    @Transient
    private String dateFinFormatted;

    /**
     * Formater les dates
     */
    public void formatDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        this.dateDebutFormatted = this.dateDebut != null ? this.dateDebut.format(formatter) : "";
        this.dateFinFormatted = this.dateFin != null ? this.dateFin.format(formatter) : "";
    }

}

