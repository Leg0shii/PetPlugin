package de.legoshi.petplugin;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import com.github.stefvanschie.inventoryframework.pane.Pane;
import de.legoshi.petplugin.manager.PlayerManager;
import de.legoshi.petplugin.pet.Pet;
import de.legoshi.petplugin.pet.custom.*;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PetGUI extends ChestGui {

    private final PlayerManager playerManager;
    private final Player player;
    private final ArrayList<Pet> pets = new ArrayList<>();

    public PetGUI(PlayerManager playerManager, Player player) {
        super(3, "§7§lPet-Selection");
        this.playerManager = playerManager;
        this.player = player;

        pets.add(new CamelPet());
        pets.add(new MolePet());
        pets.add(new EchidnaPet());
        pets.add(new RabbitPet());
        pets.add(new DragonPet());
        pets.add(new BearPet());
        pets.add(new LizardPet());

        loadGUI();
    }

    private void loadGUI() {
        this.setOnGlobalClick(event -> event.setCancelled(true));

        OutlinePane background = new OutlinePane(0, 0, 9, 3, Pane.Priority.LOWEST);
        background.addItem(new GuiItem(new ItemStack(Material.BLACK_STAINED_GLASS_PANE)));
        background.setRepeat(true);

        this.addPane(background);
        String selectionValue = "SELECTED ";

        OutlinePane petPane = new OutlinePane(1, 1, 7, 1);
        pets.forEach(pet -> {
            Pet petCopy = pet.clone(pet, player);
            Pet tempPet = playerManager.getPlayerPetHashMap().get(player);
            if (tempPet != null && petCopy.petName.equals(tempPet.petName))
                updateItemStackName(petCopy.itemStack, selectionValue + petCopy.petName);

            petPane.addItem(new GuiItem(petCopy.itemStack, event -> {
                Pet currPet = playerManager.getPlayerPetHashMap().get(player);
                if (currPet != null) {
                    currPet.unEquipPet();
                    currPet.despawnPet();
                }

                petCopy.spawnPet();
                petCopy.equipPet();

                for (ItemStack itemStack : this.getInventory().getContents()) {
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    if (itemMeta != null) updateItemStackName(itemStack, itemMeta.getDisplayName().replace(selectionValue, ""));
                }

                if (event.getCurrentItem() != null) updateItemStackName(event.getCurrentItem(), selectionValue + petCopy.petName);
                playerManager.getPlayerPetHashMap().put(player, petCopy);
            }));
        });

        this.addPane(petPane);
        this.show(player);
    }

    private void updateItemStackName(ItemStack itemStack, String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
    }

}
