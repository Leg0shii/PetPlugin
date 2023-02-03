package de.legoshi.petplugin.pet.custom;

import de.legoshi.petplugin.PetPlugin;
import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.util.CustomHeads;
import de.legoshi.petplugin.util.NBTUtil;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DragonPet extends Pet {

    private ItemStack fireBall;
    public ItemStack fireWork;
    @Getter private boolean ready = true;

    public DragonPet() {
        super();

        this.petName = "Dragon";
        this.petDescription.add("generic dragon fact...");
        this.petDescription.add("+ ...");

        this.itemStack = CustomHeads.dragonHead;

        buildPet(petName, petDescription);
    }

    @Override
    public DragonPet clone(Pet pet, Player player) {
        DragonPet dragonPet = (DragonPet) super.clone(pet, player);
        dragonPet.fireBall = new ItemStack(Material.MAGMA_CREAM);
        dragonPet.fireBall.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        NBTUtil.setValue(dragonPet.fireBall, "dragon", "");
        ItemMeta itemMeta = dragonPet.fireBall.getItemMeta();
        itemMeta.setDisplayName("Dragon Breath");
        dragonPet.fireBall.setItemMeta(itemMeta);

        dragonPet.fireWork = new ItemStack(Material.FIREWORK_ROCKET, 2);
        dragonPet.fireWork.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        NBTUtil.setValue(dragonPet.fireWork, "dragon", "");
        ItemMeta fireWorkMeta = dragonPet.fireWork.getItemMeta();
        fireWorkMeta.setDisplayName("Dragon Wings");
        dragonPet.fireWork.setItemMeta(fireWorkMeta);
        return dragonPet;
    }

    @Override
    public void equipPet() {
        super.equipPet();
        player.getInventory().addItem(fireBall);
        player.getInventory().addItem(fireWork);
    }

    @Override
    public void unEquipPet() {
        super.unEquipPet();
        for (ItemStack item : player.getInventory().getContents()) {
            if (NBTUtil.hasValue(item, "dragon")) player.getInventory().remove(item);
        }
    }

    public void throwFireBall(ItemStack itemStack) {
        ready = false;
        itemStack.removeEnchantment(Enchantment.DURABILITY);
        Bukkit.getScheduler().runTaskLater(PetPlugin.getInstance(), () -> {
            ready = true;
            itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        }, 60);
    }

}
