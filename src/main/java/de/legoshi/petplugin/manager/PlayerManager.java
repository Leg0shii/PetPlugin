package de.legoshi.petplugin.manager;

import de.legoshi.petplugin.pet.Pet;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerManager {

    @Getter
    private final HashMap<Player, Pet> playerPetHashMap = new HashMap<>();

    public void playerJoin(Player player) {
        playerPetHashMap.put(player, null);
    }

    public void playerLeave(Player player) {
        Pet pet = playerPetHashMap.get(player);
        if (pet == null) return;
        pet.unEquipPet();

        playerPetHashMap.remove(player);
    }

}
