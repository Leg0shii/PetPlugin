package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RabbitPet extends Pet {

    public RabbitPet() {
        super();

        this.petName = "Rabbit";
        this.petDescription.add("generic rabbit fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.rabbitHead;
        buildPet(petName, petDescription);
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 10000000, 0));
    }

    @Override
    public void unEquipPet() {
        super.equipPet();
        player.removePotionEffect(PotionEffectType.JUMP);
        player.removePotionEffect(PotionEffectType.SPEED);
    }

}
