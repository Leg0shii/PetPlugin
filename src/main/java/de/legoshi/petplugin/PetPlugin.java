package de.legoshi.petplugin;

import de.legoshi.petplugin.listener.PlayerListener;
import de.legoshi.petplugin.manager.ItemManager;
import de.legoshi.petplugin.manager.PlayerManager;
import de.legoshi.petplugin.pet.Pet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PetPlugin extends JavaPlugin {

    private static PetPlugin instance;

    private PlayerManager playerManager;
    private ItemManager itemManager;

    @Override
    public void onEnable() {
        instance = this;

        this.playerManager = new PlayerManager();
        this.itemManager = new ItemManager(playerManager);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerListener(playerManager, itemManager), this);
    }

    @Override
    public void onDisable() {
        for (Player all : Bukkit.getOnlinePlayers()) {
            Pet pet = playerManager.getPlayerPetHashMap().get(all);
            if (pet == null) return;
            pet.unEquipPet();
        }
    }

    public static PetPlugin getInstance() {
        return instance;
    }
}
