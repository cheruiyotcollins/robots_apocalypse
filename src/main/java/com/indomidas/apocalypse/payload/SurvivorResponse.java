package com.indomidas.apocalypse.payload;

import com.indomidas.apocalypse.model.Inventory;
import com.indomidas.apocalypse.model.Location;

public class SurvivorResponse {
    private Long id;
    private String nationalIdNo;
    private String name;
    private String gender;
    private int age;
    private String infectionStatus;
    private Location location;
    private Inventory inventory;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInfectionStatus() {
        return infectionStatus;
    }

    public void setInfectionStatus(String infectionStatus) {
        this.infectionStatus = infectionStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
