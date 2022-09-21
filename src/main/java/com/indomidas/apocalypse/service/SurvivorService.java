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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        List<SurvivorResponse> survivorsResponseList = new ArrayList<>();

        survivorRepository.findAll().stream().forEach(survivor -> {
                    survivorsResponseList.add(setSurvivorResponse(survivor));
                }
        );
        return survivorsResponseList;

    }
    public List<SurvivorResponse> listAllInfectedSurvivors() {
        List<SurvivorResponse> survivorsResponseList = new ArrayList<>();

        survivorRepository.findByInfectionStatus("infected").stream().forEach(survivor -> {
                    survivorsResponseList.add(setSurvivorResponse(survivor));
                }
        );
        return survivorsResponseList;

    }
    public List<SurvivorResponse> listAllUninfectedSurvivors() {
        List<SurvivorResponse> survivorsResponseList = new ArrayList<>();

        survivorRepository.findByInfectionStatus("uninfected").stream().forEach(survivor -> {
                    survivorsResponseList.add(setSurvivorResponse(survivor));
                }
        );
        return survivorsResponseList;

    }
    public ResponseEntity<?> infectedSurvivorsPercentage() {
        double infectedSurvivers=survivorRepository.findByInfectionStatus("infected").size();
        double allSurvivers=survivorRepository.findAll().size();
        double percentage =(infectedSurvivers/allSurvivers)*100;
        generalResponse= new GeneralResponse();
        generalResponse.setStatus(HttpStatus.ACCEPTED);
        generalResponse.setDescription("Infected Percentage is : "+percentage);

        return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);

    }
    public ResponseEntity<?> uninfectedSurvivorsPercentage() {
        double infectedSurvivers=survivorRepository.findByInfectionStatus("uninfected").size();
        double allSurvivers=survivorRepository.findAll().size();
        double percentage =(infectedSurvivers/allSurvivers)*100;
         generalResponse= new GeneralResponse();
        generalResponse.setStatus(HttpStatus.ACCEPTED);
        generalResponse.setDescription("Uninfected Percentage is : "+percentage);

        return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);

    }


    public ResponseEntity<?> flagSurvivorAsInfected(Long id) {
        generalResponse= new GeneralResponse();
        try{
           Survivor survivor= survivorRepository.findById(id).get();
           survivor.setContaminationCount(survivor.getContaminationCount()+1);
           if(survivor.getContaminationCount()>=3){
               survivor.setInfectionStatus("infected");
           }
           survivorRepository.save(survivor);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Survivor Flagged Successfully");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }catch (Exception e){
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Survivor Not Found, please check Id and try again");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }




    }







    public ResponseEntity<?> saveOrUpdate(SurvivorRequest survivorRequest) {
        generalResponse = new GeneralResponse();
        try {
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

            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Failed to save survivor to DB, please check your request and try again");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);

        }


    }

    public ResponseEntity<?> updateLocation(LocationUpdateRequest locationUpdateRequest) {
        generalResponse = new GeneralResponse();
        try {
            Survivor survivor = survivorRepository.findById(locationUpdateRequest.getId()).get();
            Location location = survivor.getLocation();
            location.setLongitude(locationUpdateRequest.getLongitude());
            location.setLatitude(locationUpdateRequest.getLatitude());
            locationRepository.save(location);
            survivor.setLocation(location);
            survivorRepository.save(survivor);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Location updated Successfully");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Failed to update location please check your request and try again");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }


    }

    public SurvivorResponse setSurvivorResponse(Survivor survivor) {
        SurvivorResponse survivorResponse = new SurvivorResponse();
        survivorResponse.setId(survivor.getId());
        survivorResponse.setAge(survivor.getAge());
        survivorResponse.setGender(survivor.getGender());
        survivorResponse.setInfectionStatus(survivor.getInfectionStatus());
        survivorResponse.setName(survivor.getName());
        survivorResponse.setNationalIdNo(survivor.getNationalIdNo());
        survivorResponse.setFlagedAsInfectedCount(survivor.getContaminationCount());
        //setting up location
        Location location = new Location();
        location.setLongitude(survivor.getLocation().getLongitude());
        location.setLatitude(survivor.getLocation().getLatitude());
        //setting up inventory
        survivorResponse.setLocation(location);
        Inventory inventory = new Inventory();
        inventory.setWater(survivor.getInventory().getWater());
        inventory.setMedication(survivor.getInventory().getMedication());
        inventory.setFood(survivor.getInventory().getFood());
        inventory.setAmmunition(survivor.getInventory().getAmmunition());
        survivorResponse.setInventory(inventory);

        return survivorResponse;

    }

    public ResponseEntity<?> findById(Long id){
        try{
            return new ResponseEntity(setSurvivorResponse(survivorRepository.findById(id).get()), HttpStatus.ACCEPTED);

        }catch (Exception e){
            generalResponse =new GeneralResponse();
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Survivor not found, please check Id and try again");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }

    }

    public ResponseEntity<?> deleteById(Long id){

        generalResponse= new GeneralResponse();

        try{
            survivorRepository.deleteById(id);
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Survivor Deleted Successfully");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            generalResponse.setStatus(HttpStatus.ACCEPTED);
            generalResponse.setDescription("Failed to delete please check your request and try again");
            return new ResponseEntity(generalResponse, HttpStatus.ACCEPTED);
        }
    }

}
