package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BearPet extends Pet {

    public BearPet() {
        super();

        this.petName = "Bear";
        this.petDescription.add("generic bear fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.bearHead;
        buildPet(petName, petDescription);
    }

    @Override
    public BearPet clone(Pet pet, Player player) {
        return (BearPet) super.clone(pet, player);
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 10000000, 0));
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10000000, 1));
    }

    @Override
    public void unEquipPet() {
        super.equipPet();
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
    }

}
