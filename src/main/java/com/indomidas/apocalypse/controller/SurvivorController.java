package com.indomidas.apocalypse.controller;


import com.indomidas.apocalypse.model.Location;
import com.indomidas.apocalypse.model.Survivor;
import com.indomidas.apocalypse.payload.GeneralResponse;
import com.indomidas.apocalypse.payload.LocationUpdateRequest;
import com.indomidas.apocalypse.payload.SurvivorRequest;
import com.indomidas.apocalypse.payload.SurvivorResponse;
import com.indomidas.apocalypse.service.SurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/survivor")
public class SurvivorController {
    @Autowired
    SurvivorService survivorService;

    @GetMapping("/list")
    public List<SurvivorResponse> listAllSurvivors(){

        return survivorService.listAllSurvivors();
    }
    @PostMapping("/save")
    public GeneralResponse save(@RequestBody SurvivorRequest survivorRequest){

        return survivorService.saveOrUpdate(survivorRequest);
    }
    @PostMapping("/location/update")
    public GeneralResponse updateLocation(@RequestBody LocationUpdateRequest locationUpdateRequest){

        return survivorService.updateLocation(locationUpdateRequest);
    }
}
