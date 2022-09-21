package com.indomidas.apocalypse.controller;


import com.indomidas.apocalypse.model.Location;
import com.indomidas.apocalypse.model.Survivor;
import com.indomidas.apocalypse.payload.GeneralResponse;
import com.indomidas.apocalypse.payload.LocationUpdateRequest;
import com.indomidas.apocalypse.payload.SurvivorRequest;
import com.indomidas.apocalypse.payload.SurvivorResponse;
import com.indomidas.apocalypse.service.SurvivorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/uninfected/percentage")
    public ResponseEntity<?> UninfectedSurvivorsPercentage(){

        return survivorService.uninfectedSurvivorsPercentage();
    }
    @GetMapping("/infected/percentage")
    public ResponseEntity<?> infectedSurvivorsPercentage(){

        return survivorService.infectedSurvivorsPercentage();
    }
    @GetMapping("/uninfected/list")
    public List<SurvivorResponse> listAllUninfectedSurvivors(){

        return survivorService.listAllUninfectedSurvivors();
    }
    @GetMapping("/infected/list")
    public List<SurvivorResponse> listAllInfectedSurvivors(){

        return survivorService.listAllInfectedSurvivors();
    }
    @GetMapping("/flag/infected/{id}")
    public ResponseEntity<?> flagSurvivorAsInfected(@PathVariable Long id){

        return survivorService.flagSurvivorAsInfected(id);
    }
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SurvivorRequest survivorRequest){

        return survivorService.saveOrUpdate(survivorRequest);
    }
    @PostMapping("/location/update")
    public ResponseEntity<?>updateLocation(@RequestBody LocationUpdateRequest locationUpdateRequest){

        return survivorService.updateLocation(locationUpdateRequest);
    }
    @GetMapping("/find/by/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        return survivorService.findById(id);
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){

        return survivorService.deleteById(id);
    }
}
