package de.legoshi.petplugin.manager;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import de.legoshi.petplugin.PetGUI;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.logging.Level;

public class ItemManager {

    public static ItemStack getPetSelectionItem() {
        ItemStack itemStack = new ItemStack(Material.NETHER_STAR, 1);
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

    public static void handlePetSelectorClick(Player player) {
        new PetGUI(player);
    }

}
