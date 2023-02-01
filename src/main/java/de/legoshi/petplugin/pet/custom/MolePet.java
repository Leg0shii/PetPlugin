package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import io.netty.handler.ssl.util.SimpleTrustManagerFactory;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class MolePet extends Pet {

    public MolePet(Player player) {
        String petName = "Mole";
        ArrayList<String> petDescription = new ArrayList<>();
        petDescription.add("generic camel fact...");
        petDescription.add("+ No required food");

        this.player = player;

        buildPet(petName, petDescription);
    }

    @Override
    public void equipPet() {

    }

    @Override
    public void unEquipPet() {

    }

}
