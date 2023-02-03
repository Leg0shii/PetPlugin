package de.legoshi.petplugin.util;

import de.legoshi.petplugin.PetPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class NBTUtil {

    public static void setValue(ItemStack item, String key, String value) {
        NamespacedKey namespacedKey = new NamespacedKey(PetPlugin.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(namespacedKey, PersistentDataType.STRING, value);
        item.setItemMeta(meta);
    }

    public static String getValue(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(PetPlugin.getInstance(), key);
        ItemMeta meta = item.getItemMeta();
        return meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
    }

    public static boolean hasValue(ItemStack item, String key) {
        NamespacedKey namespacedKey = new NamespacedKey(PetPlugin.getInstance(), key);
        if (item == null) return false;

        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return false;

        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(namespacedKey , PersistentDataType.STRING);
    }

}