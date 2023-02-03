package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import de.legoshi.petplugin.util.NBTUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CamelPet extends Pet {

    public CamelPet() {
        super();

        this.petName = "Camel";
        this.petDescription.add("generic camel fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.camelHead;
        buildPet(petName, petDescription);
    }

    @Override
    public CamelPet clone(Pet pet, Player player) {
        return (CamelPet) super.clone(pet, player);
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 10000000, 200));
    }

    @Override
    public void unEquipPet() {
        super.equipPet();
        player.removePotionEffect(PotionEffectType.SATURATION);
    }

}
