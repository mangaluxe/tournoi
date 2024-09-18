package org.example.tournoi.service;

import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Utilisateur;
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

    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

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

    public Utilisateur updateUser(int id, Utilisateur updatedUtilisateur) {
        Utilisateur utilisateurExist = getUtilisateurById(id);
        if (utilisateurExist != null) {
            utilisateurExist.setPseudo(updatedUtilisateur.getPseudo());
            utilisateurExist.setMotdepasse(updatedUtilisateur.getMotdepasse());
            utilisateurExist.setNom(updatedUtilisateur.getNom());
            utilisateurExist.setPrenom(updatedUtilisateur.getPrenom());
            utilisateurExist.setEmail(updatedUtilisateur.getEmail());
            utilisateurExist.setDateNaissance(updatedUtilisateur.getDateNaissance());
            utilisateurRepository.save(utilisateurExist);
        }
        return utilisateurExist;
    }

    public Utilisateur findByEmailAndPseudo(String email, String pseudo) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmailAndPseudo(email, pseudo);
        return utilisateurOpt.orElse(null); // Retourne null si aucun utilisateur n'est trouvé
    }

}