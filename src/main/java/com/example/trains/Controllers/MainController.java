package com.example.trains.Controllers;

import com.example.trains.Repo.StationRepo;
import com.example.trains.domain.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
public class MainController {
    @Autowired
    final private StationRepo stationRepo;

    public MainController(StationRepo stationRepo) {
        this.stationRepo = stationRepo;
    }

    @GetMapping("/")
    public String index(Model model){

        return "index"; // name of page
    }

}
