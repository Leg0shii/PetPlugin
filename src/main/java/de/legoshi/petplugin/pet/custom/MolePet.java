package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class MolePet extends Pet {

    public MolePet() {
        super();

        this.petName = "Mole";
        this.petDescription.add("generic Mole fact...");
        this.petDescription.add("+ No required food");

        this.itemStack = CustomHeads.moleHead;
        buildPet(petName, petDescription);
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 10000000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
    }

    @Override
    public void unEquipPet() {
        super.equipPet();
        player.removePotionEffect(PotionEffectType.FAST_DIGGING);
        player.removePotionEffect(PotionEffectType.SPEED);
    }

}
