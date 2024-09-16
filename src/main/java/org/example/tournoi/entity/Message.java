package org.example.tournoi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titre;

    @Column(nullable = false, length = 65535) // 65535 = taille max pour TEXT
    private String contenu; // Contenu du message

    private LocalDateTime dateEnvoi; // Date et heure d'envoi du message

    private boolean estLu; // Indique si le message a été lu ou non

    // ---------- Relations ----------

    @ManyToOne
    @JoinColumn(name = "expediteur_id", nullable = false)
    private Utilisateur expediteur; // L'utilisateur qui envoie le message

    @ManyToOne
    @JoinColumn(name = "destinataire_id", nullable = false)
    private Utilisateur destinataire; // L'utilisateur qui reçoit le message
}
