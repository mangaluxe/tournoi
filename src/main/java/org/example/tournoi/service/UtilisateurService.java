package org.example.tournoi.service;


import org.example.tournoi.dao.UtilisateurRepository;
import org.example.tournoi.entity.Utilisateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Utilisateur createUser(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public List<Utilisateur> getAllUsers() {
        return utilisateurRepository.findAll();
    }

    public Utilisateur searchUser(String search) {
        return utilisateurRepository.findByPseudo(search);
    }

    public Utilisateur getUserById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur updateUser(int id, Utilisateur updatedUtilisateur) {
        Utilisateur utilisateurExist = getUserById(id);
        if(utilisateurExist != null){
            utilisateurRepository.save(updatedUtilisateur);
        }
        return utilisateurExist;
    }
}