package de.legoshi.petplugin.listener;

import de.legoshi.petplugin.manager.ItemManager;
import de.legoshi.petplugin.manager.PlayerManager;
import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.pet.custom.DragonPet;
import de.legoshi.petplugin.util.NBTUtil;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

@RequiredArgsConstructor
public class PlayerListener implements Listener {

    private final PlayerManager playerManager;
    private final ItemManager itemManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ItemStack petSelector = itemManager.getPetSelectionItem();
        if (petSelector == null) return;

        Player player = event.getPlayer();
        player.getInventory().setItem(8, petSelector);

        playerManager.playerJoin(player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerManager.playerLeave(player);
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();
        if (itemStack == null) return;

        Player player = event.getPlayer();
        if (NBTUtil.hasValue(itemStack, "pet")) {
            itemManager.handlePetSelectorClick(player);
        } else if (itemStack.getType().equals(Material.MAGMA_CREAM) && NBTUtil.hasValue(itemStack, "dragon")) {
            itemManager.handleFireBallClick(player, event);
        } else if(itemStack.getType().equals(Material.FIREWORK_ROCKET) && NBTUtil.hasValue(itemStack, "dragon")) {
            Pet pet = playerManager.getPlayerPetHashMap().get(player);
            if (pet == null || !pet.petName.toLowerCase().equals("dragon")) return;
            if (event.getItem().getAmount() <= 2) {
                if (!(pet instanceof DragonPet dragonPet)) return;
                ItemStack fireWork = dragonPet.fireWork;
                player.getInventory().setItemInMainHand(fireWork);
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        Pet playerPet = playerManager.getPlayerPetHashMap().get(player);
        if (playerPet == null) return;
        if (!playerPet.petName.equals("Echidna")) return;

        Entity entity;
        if (event.getDamager() instanceof Arrow arrow) {
            if (!(arrow.getShooter() instanceof Entity tempEntity)) return;
            entity = tempEntity;
        } else if (event.getDamager() instanceof Fireball fireball) {
            if (!(fireball.getShooter() instanceof Entity tempEntity)) return;
            entity = tempEntity;
        } else {
            entity = event.getDamager();
        }

        double deflect = Math.random();
        double damage = Math.random() * 5;

        if (!(entity instanceof Damageable damageable)) return;
        if (deflect < 0.75) {
            damageable.damage(damage);
            System.out.println("Damaged Entity: " + damage);
        }
    }

    @EventHandler
    public void onDropEvent(PlayerDropItemEvent event) {
        if (NBTUtil.hasValue(event.getItemDrop().getItemStack(), "dragon")) {
            event.setCancelled(true);
        }
    }

}
