package com.indomidas.apocalypse.service;

import com.indomidas.apocalypse.model.Inventory;
import com.indomidas.apocalypse.model.Location;
import com.indomidas.apocalypse.model.Survivor;
import com.indomidas.apocalypse.payload.GeneralResponse;
import com.indomidas.apocalypse.payload.LocationUpdateRequest;
import com.indomidas.apocalypse.payload.SurvivorRequest;
import com.indomidas.apocalypse.payload.SurvivorResponse;
import com.indomidas.apocalypse.repository.InventoryRepository;
import com.indomidas.apocalypse.repository.LocationRepository;
import com.indomidas.apocalypse.repository.SurvivorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
import java.util.List;

@Service
public class SurvivorService {
    @Autowired
    SurvivorRepository survivorRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    InventoryRepository inventoryRepository;
    GeneralResponse generalResponse;

    public List<SurvivorResponse> listAllSurvivors() {
        List<Survivor> survivors = survivorRepository.findAll();
        List<SurvivorResponse> survivorsResponseList = new ArrayList<>();

        survivors.stream().forEach(survivor -> {
                    SurvivorResponse survivorResponse = new SurvivorResponse();
                    survivorResponse.setId(survivor.getId());
                    survivorResponse.setAge(survivor.getAge());
                    survivorResponse.setGender(survivor.getGender());
                    survivorResponse.setInfectionStatus(survivor.getInfectionStatus());
                    survivorResponse.setName(survivor.getName());
                    survivorResponse.setNationalIdNo(survivor.getNationalIdNo());
                    //setting up location
                    Location location= new Location();
                    location.setLongitude(survivor.getLocation().getLongitude());
                    location.setLatitude(survivor.getLocation().getLatitude());
                    //setting up inventory
                    survivorResponse.setLocation(location);
                    Inventory inventory= new Inventory();
                    inventory.setWater(survivor.getInventory().getWater());
                    inventory.setMedication(survivor.getInventory().getMedication());
                    inventory.setFood(survivor.getInventory().getFood());
                    inventory.setAmmunition(survivor.getInventory().getAmmunition());
                    survivorResponse.setInventory(inventory);
                   survivorsResponseList.add(survivorResponse);

                }
        );

       return survivorsResponseList;

    }

    public GeneralResponse saveOrUpdate(SurvivorRequest survivorRequest) {
        generalResponse=new GeneralResponse();
        try{
            Survivor survivor = new Survivor();
            //Setting up survivor details
            survivor.setAge(survivorRequest.getAge());
            survivor.setGender(survivorRequest.getGender());
            survivor.setInfectionStatus(survivorRequest.getInfectionStatus());
            survivor.setName(survivorRequest.getName());
            survivor.setNationalIdNo(survivorRequest.getNationalIdNo());
            survivor.setContaminationCount(0);
            //Setting up location
            Location location = new Location();
            location.setLatitude(survivorRequest.getLatitude());
            location.setLongitude(survivorRequest.getLongitude());
            survivor.setLocation(locationRepository.save(location));

            //Setting up inventory
            Inventory inventory = new Inventory();
            inventory.setAmmunition(survivorRequest.getAmmunition());
            inventory.setFood(survivorRequest.getFood());
            inventory.setMedication(survivorRequest.getMedication());
            inventory.setWater(survivorRequest.getWater());
            survivor.setInventory(inventoryRepository.save(inventory));
            survivorRepository.save(survivor);

            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Survivor saved successfully");

            return generalResponse;
        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Failed to save survivor to DB, please check your request and try again");
            return generalResponse;

        }



    }

    public GeneralResponse updateLocation(LocationUpdateRequest locationUpdateRequest){
        generalResponse= new GeneralResponse();
        try{
           Survivor survivor= survivorRepository.findById(locationUpdateRequest.getId()).get();
            Location location= survivor.getLocation();
            location.setLongitude(locationUpdateRequest.getLongitude());
            location.setLatitude(locationUpdateRequest.getLatitude());
            locationRepository.save(location);
            survivor.setLocation(location);
            survivorRepository.save(survivor);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Location updated Successfully");
            return generalResponse;

        }catch (Exception e){
             generalResponse.setStatus(HttpStatus.ACCEPTED);
             generalResponse.setDescription("Failed to update location please check your request and try again");
            return generalResponse;
        }


    }

}
