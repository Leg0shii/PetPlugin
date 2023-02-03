package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;

public class EchidnaPet extends Pet {

    public EchidnaPet() {
        super();

        this.petName = "Echidna";
        this.petDescription.add("generic echidna fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.echidnaHead;
        buildPet(petName, petDescription);
    }

    @Override
    public void equipPet() {
        super.equipPet();
    }

}
