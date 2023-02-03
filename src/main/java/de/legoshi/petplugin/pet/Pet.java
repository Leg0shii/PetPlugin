package de.legoshi.petplugin.pet;

import de.legoshi.petplugin.manager.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class Pet {

    public Player player;

    public ItemStack itemStack;
    public String petName;
    public ArrayList<String> petDescription = new ArrayList<>();

    public Pet() {

    }

    public void buildPet(String petName, ArrayList<String> petDescription) {
        this.petName = petName;
        this.petDescription = petDescription;

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(petName);
        itemMeta.setLore(petDescription);
        itemStack.setItemMeta(itemMeta);
    }

    public Pet clone(Pet pet, Player player) {
        pet.player = player;
        pet.itemStack = this.itemStack;
        pet.petName = this.petName;
        pet.petDescription = this.petDescription;
        return pet;
    }

    public void spawnPet() {
        player.sendMessage("Spawned " + petName + " pet.");
        // spawn armor stand
        // make armor stand invisible
        // register movement logic for pet to follow
        // set the head of the pet on the armor stand
    }

    public void despawnPet() {
        player.sendMessage("Despawned " + petName + " pet.");
        // destroy armor stand
    }

    public void equipPet() {
        player.sendMessage("Applied effects from " + petName + " pet.");
    }

    public void unEquipPet() {
        player.sendMessage("Unequipped " + petName + " pet.");
    }

}
