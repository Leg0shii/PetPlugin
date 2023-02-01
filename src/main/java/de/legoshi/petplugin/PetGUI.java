package de.legoshi.petplugin;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import de.legoshi.petplugin.pet.custom.CamelPet;
import de.legoshi.petplugin.util.CustomHeads;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PetGUI extends ChestGui {

    private Player player;

    public PetGUI(Player player) {
        super(3, "§7§lPet-Selection");
        this.player = player;

        loadGUI();
    }

    private void loadGUI() {
        this.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        this.addPane(background);

        OutlinePane petPane = new OutlinePane(1, 1, 7, 1);
        petPane.addItem(new GuiItem(CustomHeads.camelHead, event -> {
            CamelPet camelPet = new CamelPet();
        }));

        petPane.addItem(new GuiItem(CustomHeads.moleHead, event -> {

        }));

        petPane.addItem(new GuiItem(CustomHeads.echidnaHead, event -> {

        }));

        petPane.addItem(new GuiItem(CustomHeads.rabbitHead, event -> {

        }));

        petPane.addItem(new GuiItem(CustomHeads.dragonHead, event -> {

        }));

        petPane.addItem(new GuiItem(CustomHeads.bearHead, event -> {

        }));

        petPane.addItem(new GuiItem(CustomHeads.lizardHead, event -> {
            
        }));

        this.addPane(petPane);
        this.show(player);
    }

}
