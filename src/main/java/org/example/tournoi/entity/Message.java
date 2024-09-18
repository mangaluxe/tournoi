package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String titre;

    @Column(nullable = false, length = 65535) // 65535 = taille max pour TEXT
    private String contenu; // Contenu du message

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
