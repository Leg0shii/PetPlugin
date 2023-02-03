package de.legoshi.petplugin.manager;

import de.legoshi.petplugin.PetGUI;
import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.pet.custom.DragonPet;
import de.legoshi.petplugin.util.NBTUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.logging.Level;

@RequiredArgsConstructor
public class ItemManager {

    private final PlayerManager playerManager;

    public ItemStack getPetSelectionItem() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR, 1);
        NBTUtil.setValue(itemStack, "pet", "");

        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            Bukkit.getLogger().log(Level.SEVERE, "Couldn't get item meta of pet-selection-item.");
            return null;
        }

        itemMeta.setDisplayName("§6§lPets");

        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add("§7This magical item lets you summon pets.");
        itemMeta.setLore(itemLore);

        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void handlePetSelectorClick(Player player) {
        new PetGUI(playerManager, player);
    }

    public void handleFireBallClick(Player player, PlayerInteractEvent event) {
        Action action = event.getAction();
        DragonPet dragonPet = (DragonPet) playerManager.getPlayerPetHashMap().get(player);
        if (dragonPet == null) return;

        if (!dragonPet.isReady()) {
            player.sendMessage("Your fire-breath isn't ready.");
            return;
        }

        if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            Fireball fireball = player.launchProjectile(Fireball.class);
            fireball.setIsIncendiary(false);
            fireball.setVelocity(player.getLocation().getDirection().multiply(2));
        }

        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;

        dragonPet.throwFireBall(itemStack);
    }

}
