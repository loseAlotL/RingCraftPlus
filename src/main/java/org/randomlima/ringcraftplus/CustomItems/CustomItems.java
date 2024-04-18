package org.randomlima.ringcraftplus.CustomItems;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {
    public static ItemStack AngmarHelmet;
    public static ItemStack Palantir;
    public static ItemStack gondorHorn;
    public static ItemStack gandalfStaff;
    public static ItemStack sarumanStaff;
    public static ItemStack galadrielPhial;

    public static void init(){
        createAngmarHelmet();
        createPalantir();
        createGondorHorn();
        createGandalfStaff();
        createSarumanStaff();
        createGaladrielPhial();
    }

    private static void createAngmarHelmet() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
        meta.addEnchant(Enchantment.THORNS, 4, true);
        meta.addEnchant(Enchantment.MENDING, 1, true);
        meta.addEnchant(Enchantment.DURABILITY, 3, true);
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("GENERIC_MAX_HEALTH", 20, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName("§4[Witch King of Angmar's Helmet]");
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add("§7The one and only helmet of the §4Witch King§7.");
        lore.add("");
        lore.add("§8Gives the wearer §dregeneration 1§8, §dnight vision ");
        lore.add("§8but also gives them §dwithering §8while in water. Gives all");
        lore.add("§8surrounding players in a 10 block radius §dslowness 2§8.");
        lore.add("");
        meta.setLore(lore);
        item.setItemMeta(meta);
        AngmarHelmet = item;
    }

    private static void createPalantir() {
        ItemStack item = new ItemStack(Material.ENDER_EYE,1);
        ItemMeta meta = item.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("GENERIC_MAX_HEALTH", 10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setDisplayName("§5[Palantir]");
        List<String> lore = new ArrayList<>();
        lore.add("§6Ability: Ethereal Gaze §e§lRIGHT CLICK");
        lore.add("§7Reveals surrounding mobs, giving all entities within 15 blocks");
        lore.add("§dglowing§7 for 15 seconds.");
        lore.add("§8Cooldown: 60s");
        meta.setLore(lore);
        item.setItemMeta(meta);
        Palantir = item;
    }

    private static void createGondorHorn() {
        ItemStack item = new ItemStack(Material.STONE_HOE,1);
        ItemMeta meta = item.getItemMeta();
        meta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, new AttributeModifier("GENERIC_MAX_HEALTH", 10, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setUnbreakable(true);
        meta.setDisplayName("§6[The Horn of Gondor]");
        List<String> lore = new ArrayList<>();
        lore.add("§6Ability: Call of Valor §e§lRIGHT CLICK");
        lore.add("§7When blown, gives all players within 10 blocks §dregeneration 2");
        lore.add("§7 for 15 seconds.");
        lore.add("§8Cooldown: 50s");
        meta.setLore(lore);
        item.setItemMeta(meta);
        gondorHorn = item;
    }

    private static void createGandalfStaff() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName("§7[Gandalf's Staff]");
        List<String> lore = new ArrayList<>();
        lore.add("§6Ability: The Smoke Rings of Gandalf §e§lRIGHT CLICK");
        lore.add("§7Create a cloud of smoke around you.");
        lore.add("§8Cooldown: 60s");
        lore.add("§6Ability: Strategic Wisdom §e§lLEFT CLICK");
        lore.add("§7Gives §3allies §dinstant health 2, regeneration 1 §7 and");
        lore.add("§dspeed 1§7 for 10 seconds.");
        lore.add("§8Cooldown: 75s");
        lore.add("§6Ability: Marker §e§lSHIFT-RIGHT CLICK");
        lore.add("§7Use while facing a player to mark tham as an §3ally§7.");
        meta.setLore(lore);
        item.setItemMeta(meta);
        gandalfStaff = item;
    }
    private static void createSarumanStaff() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName("§7[Saruman's Staff]");
        List<String> lore = new ArrayList<>();
        lore.add("§6Ability: Telekinesis §e§lRIGHT CLICK");
        lore.add("§7All enemies in front of you will be slowed down to a snails");
        lore.add("§7pace for 15s.");
        lore.add("§8Cooldown: 60s");
        lore.add("§6Ability: Fiery Curse §e§lLEFT CLICK");
        lore.add("§7Launches a cluster of fireballs to deal massive damage!");
        lore.add("§8Cooldown: 45s");
        lore.add("§6Ability: Illumination §e§lSHIFT-RIGHT CLICK");
        lore.add("§7Emits a bright light. All players within 7 blocks §dglowing");
        lore.add("§7and §dblindness §7for 15 seconds.");
        lore.add("§8Cooldown: 60s");
        meta.setLore(lore);
        item.setItemMeta(meta);
        sarumanStaff = item;
    }

    public static void createGaladrielPhial() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName("§f[Galadriel's Phial]");
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier("GENERIC_MOVEMENT_SPEED", 0.1, AttributeModifier.Operation.ADD_NUMBER));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add("§6Ability: Radiant Elixir §e§lRIGHT CLICK");
        lore.add("§7Blasts the surrounding area with §fGaladriel's Light§7. §fGaladriel's");
        lore.add("§fLight §7gives all players within 10 blocks §dregeneration 2 §7for 30");
        lore.add("§7seconds. It also slowly damages all hostile mobs within 10 blocks.");
        lore.add("§8Cooldown: 60s");
        meta.setLore(lore);
        item.setItemMeta(meta);
        galadrielPhial = item;
    }
}
