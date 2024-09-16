package org.example.tournoi.controller;

import org.example.tournoi.entity.Tournoi;
import org.example.tournoi.service.TournoiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class TournoiController {

    // ========== Propriétés ==========

    private TournoiService tournoiService;


    // ========== Controller ==========

    public TournoiController(TournoiService tournoiService) {
        this.tournoiService = tournoiService;
    }


    // ========== Méthodes ==========

    @GetMapping("/tournois")
    public String tournois(Model model) {

        List<Tournoi> tournois = tournoiService.getAllTournois();

        model.addAttribute("title", "Liste des tournois"); // Pour le title de la page

        long nb = tournoiService.getNbTotalTournois();
        model.addAttribute("nbTotalTournois", nb);

        return "tournois";

    }





}
