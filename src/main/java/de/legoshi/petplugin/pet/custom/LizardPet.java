package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LizardPet extends Pet {

    public LizardPet() {
        super();

        this.petName = "Lizard";
        this.petDescription.add("generic lizard fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.lizardHead;
        buildPet(petName, petDescription);
    }

    @Override
    public LizardPet clone(Pet pet, Player player) {
        return (LizardPet) super.clone(pet, player);
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 10000000, 0));
    }

    @Override
    public void unEquipPet() {
        super.equipPet();
        player.removePotionEffect(PotionEffectType.REGENERATION);
    }

}
