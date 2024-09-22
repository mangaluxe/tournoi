package org.example.tournoi.service;

import org.example.tournoi.repository.UtilisateurRepository;
import org.example.tournoi.entity.Utilisateur;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;


@Service
public class UtilisateurService {

    // ========== Propriétés ==========

    private final UtilisateurRepository utilisateurRepository;


    // ========== Constructeur ==========

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur searchUser(String search) {
        return utilisateurRepository.findByPseudo(search);
    }

    public Utilisateur getUtilisateurById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public Utilisateur findByPseudo(String pseudo) {
        return utilisateurRepository.findByPseudo(pseudo);
    }

    public Utilisateur findByEmailAndPseudo(String email, String pseudo) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmailAndPseudo(email, pseudo);
        return utilisateurOpt.orElse(null); // Retourne null si aucun utilisateur n'est trouvé
    }


    // ----- Create -----

    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }


    // ----- Update -----

    public Utilisateur updateUser(int id, Utilisateur utilisateur) {
        Utilisateur utilisateurExist = getUtilisateurById(id);
        if (utilisateurExist != null) {
            utilisateurExist.setPseudo(utilisateur.getPseudo());
            utilisateurExist.setNom(utilisateur.getNom());
            utilisateurExist.setPrenom(utilisateur.getPrenom());
            utilisateurExist.setEmail(utilisateur.getEmail());
            utilisateurExist.setDateNaissance(utilisateur.getDateNaissance());

            // Mise à jour du mot de passe uniquement si un nouveau mot de passe est entré :
            if (utilisateur.getMotdepasse() != null && !utilisateur.getMotdepasse().isEmpty()) {
                // utilisateurExist.setMotdepasse(updatedUtilisateur.getMotdepasse());

                String hashedPassword = BCrypt.hashpw(utilisateur.getMotdepasse(), BCrypt.gensalt()); // Hacher le nouveau mot de passe
                utilisateurExist.setMotdepasse(hashedPassword); // Remplacer le mot de passe par sa version hashée
            }

            utilisateurRepository.save(utilisateurExist);
        }
        return utilisateurExist;
    }


    // Utile pour sauvegarder le nouveau mot de passe généré dans mot de passe oublié :
    public void save(Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
    }


    // ----- Delete -----

    public void deleteUser(int id) {
        utilisateurRepository.deleteById(id);
    }

//    public Utilisateur updateUser(int id, Utilisateur updatedUtilisateur) {
//        Utilisateur utilisateurExist = getUtilisateurById(id);
//        if(utilisateurExist != null){
//            utilisateurRepository.save(updatedUtilisateur);
//        }
//        return utilisateurExist;
//    }


}