package de.legoshi.petplugin.listener;

import de.legoshi.petplugin.manager.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        ItemStack petSelector = ItemManager.getPetSelectionItem();
        if (petSelector == null) return;

        Player player = event.getPlayer();
        player.getInventory().setItem(8, petSelector);
    }

    @EventHandler
    public void onItemClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemManager.handlePetSelectorClick(player);
    }

}
