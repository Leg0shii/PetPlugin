package de.legoshi.petplugin.util;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

public class CustomHeads {

    public static ItemStack camelHead = create("8d4c32449ad23060c3f41ba9d27cd51ec2eece63bcff304ae44075043a2ec440");
    public static ItemStack moleHead = create("fea3f447455cd229dbc7eb3974e3cb5766aacfc46b5a3bf4c596d19594179d");
    public static ItemStack echidnaHead = create("13d30cf7ef2cd8440242f2c5a77c54cd3f9157e191607d207c487562f8385484");
    public static ItemStack rabbitHead = create("ffac8e0b1f93aa8918cbfbeaa0fe246b3e6ecc8bd0058b3c41aa05948c4b7a86");
    public static ItemStack dragonHead = create("11dfd56233e99d6302d58b57efe34dae5710ca2347706db8bc8d25b7e3daf462");
    public static ItemStack bearHead = create("f94f21fe83398135c9d04deb84a6004ed01f981203cb6443ae570c15994b947e");
    public static ItemStack lizardHead = create("43d57652c4ecde6dc028d6a079266fb7c5757f59be74ba4f91e677954ea91169");

    public static ItemStack create(String texture) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"http://textures.minecraft.net/texture/" + texture + "\"}}}").getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(skullMeta, profile);
        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
            e1.printStackTrace();
        }

        itemStack.setItemMeta(skullMeta);
        return itemStack;
    }

}
