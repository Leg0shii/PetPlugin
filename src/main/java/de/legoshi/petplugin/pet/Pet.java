package de.legoshi.petplugin.pet;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class Pet {

    public Player player;

    public String petName;
    public ArrayList<String> petDescription = new ArrayList<>();

    public void buildPet(String petName, ArrayList<String> petDescription) {
        this.petName = petName;
        this.petDescription = petDescription;

        spawnPet();
    }

    public void spawnPet() {
        player.sendMessage("Spawned pet " + petName);
        // spawn armor stand
        // make armor stand invisible
        // register movement logic for pet to follow
        // set the head of the pet on the armor stand
    }

    public abstract void equipPet();
    public abstract void unEquipPet();

}
