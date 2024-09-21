package org.example.tournoi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tournoi.validation.MyValid;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titre obligatoire")
    private String titre;

    @NotBlank(message = "Contenu du message obligatoire")
    @MyValid(message = "Gros mots interdits") // Validation personnalisée
    @Column(columnDefinition = "text")
    private String contenu;

    private LocalDateTime dateEnvoi; // Date et heure d'envoi du message


    // ---------- Relations ----------

//    @ManyToOne
//    @JoinColumn(name = "expediteur_id", nullable = false)
//    private Utilisateur expediteur; // L'utilisateur qui envoie le message
//
//    @ManyToOne
//    @JoinColumn(name = "destinataire_id", nullable = false)
//    private Utilisateur destinataire; // L'utilisateur qui reçoit le message


    // ---------- @Transient : Ne pas persister dans BDD ----------

    @Transient
    private String dateEnvoiFormatted;

    /**
     * Formater les dates
     */
    public void formatDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy à HH:mm");
        this.dateEnvoiFormatted = this.dateEnvoi != null ? this.dateEnvoi.format(formatter) : "";
    }

}
