package org.randomlima.ringcraftplus.CustomItems;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.randomlima.ringcraftplus.Colorize;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomItems {
    public static ItemStack AngmarHelmet;
    public static ItemStack Palantir;
    public static ItemStack gondorHorn;
    public static ItemStack gandalfStaff;
    public static ItemStack sarumanStaff;
    public static ItemStack radagastStaff;
    public static ItemStack galadrielPhial;
    public static ItemStack WizardHat;
    public static ItemStack AmogusRing;
    public static ItemStack UrukHaiHelmet;
    public static ItemStack UrukHaiShield;
    public static ItemStack UrukHaiSword;
    public static ItemStack BalrogWhip;
    public static ItemStack MorgulBlade;
    public static ItemStack AmogusJetBomb;
    public static ItemStack AmogusJetGuns;
    public static ItemStack AmogusJetWings;
    public static ItemStack AmogusJet;
    public static ItemStack AmogusRocketJet;
    public static ItemStack AmogusDroneMines;
    public static ItemStack AmogusMissles;
    public static ItemStack AmogusBarrelRoll;
    public static ItemStack AmogusBarrelRollLeft;

    public static ItemStack AmogusNuke;

    public static void init(){
        createAngmarHelmet();
        createWizardHat();
        createPalantir();
        createGondorHorn();
        createGandalfStaff();
        createSarumanStaff();
        createRadagastStaff();
        createGaladrielPhial();
        createAmogusRing();
        createBalrogWhip();
        createUrukHaiHelmet();
        createUrukHaiShield();
        createUrukHaiSword();
        createMorgulBlade();
        createAmogusJetBomb();
        createAmogusJetGuns();
        createAmogusJetWings();
        createAmogusJet();
        createAmogusRocketJets();
        createAmogusDroneMines();
        createAmogusMissles();
        createAmogusBarrelRoll();
        createAmogusBarrelRollLeft();
        createAmogusNuke();
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
        meta.setDisplayName(Colorize.format("&4[Witch King of Angmar's Helmet]"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Colorize.format("&8The one and only helmet of the &7Witch King&8."));
        lore.add("");
        lore.add(Colorize.format("&7Gives the wearer &dregeneration 1&7, &dnight vision "));
        lore.add(Colorize.format("&7but also gives them &dwithering &7while in water. Gives all"));
        lore.add(Colorize.format("&7surrounding players in a 10 block radius &dslowness 2&7."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        AngmarHelmet = item;
    }
    private static void createUrukHaiHelmet() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5);
        meta.setDisplayName(Colorize.format("&8[UrukHai Helmet]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&8Worn by the warriors of Saruman."));
        lore.add("");
        lore.add(Colorize.format("&6Passive Ability: Feral Prowl &e&lWHILE WORN"));
        lore.add(Colorize.format("&7UrukHai yes yes"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        UrukHaiHelmet = item;
    }
    private static void createUrukHaiShield() {
        ItemStack item = new ItemStack(Material.SHIELD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(5);
        meta.setDisplayName(Colorize.format("&8[UrukHai Shield]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&8Worn by the warriors of Saruman."));
        lore.add("");
        lore.add(Colorize.format("&6Passive Ability: Isengard's Mark &e&lWHILE HELD"));
        lore.add(Colorize.format("&7UrukHai yes yes"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        UrukHaiShield = item;
    }
    private static void createUrukHaiSword() {
        ItemStack item = new ItemStack(Material.NETHERITE_SWORD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&8[UrukHai Sword]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&8Worn by the warriors of Saruman."));
        lore.add("");
        lore.add(Colorize.format("&6Passive Ability: Vile Steel &e&lWHILE HELD"));
        lore.add(Colorize.format("&7UrukHai yes yes"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        UrukHaiSword = item;
    }

    private static void createWizardHat() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName(Colorize.format("&3[Wizard Hat]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&8Worn by the greatest wizards."));
        lore.add("");
        lore.add(Colorize.format("&6Passive Ability: Mystical Aura &e&lWHILE WORN"));
        lore.add(Colorize.format("&7Cuts all cooldowns on wizard staffs in half."));
        meta.setLore(lore);
        item.setItemMeta(meta);
        WizardHat = item;
    }

    private static void createPalantir() {
        ItemStack item = new ItemStack(Material.ENDER_EYE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&5[Palantir]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Ethereal Gaze &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Reveals surrounding mobs, giving all entities within 15 blocks"));
        lore.add(Colorize.format("&dglowing&7 for 15 seconds."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
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
        meta.setDisplayName(Colorize.format("&6[The Horn of Gondor]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Call of Valor &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7When blown, gives all players within 10 blocks &dregeneration 2"));
        lore.add(Colorize.format("&7 for 15 seconds."));
        lore.add(Colorize.format("&8Cooldown: 50s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        gondorHorn = item;
    }

    private static void createGandalfStaff() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(1);
        meta.setDisplayName(Colorize.format("&7[Gandalf's Staff]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format(Colorize.format("&6Ability: The Smoke Rings of Gandalf &e&lRIGHT CLICK")));
        lore.add(Colorize.format("&7Create a cloud of smoke around you."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        lore.add(Colorize.format("&6Ability: Strategic Wisdom &e&lLEFT CLICK"));
        lore.add(Colorize.format("&7Gives &3allies &dinstant health 2, regeneration 1 &7 and"));
        lore.add(Colorize.format("&dspeed 1&7 for 10 seconds."));
        lore.add(Colorize.format("&8Cooldown: 75s"));
        lore.add(Colorize.format("&6Ability: Repelling Flame &e&lSHIFT-RIGHT CLICK"));
        lore.add(Colorize.format("&7Launches all entities in a 8 block radius away from you. The ability"));
        lore.add(Colorize.format("&7will ignore &3allies&7."));
        lore.add(Colorize.format("&8Cooldown: 75s"));
        lore.add(Colorize.format("&6Ability: Marker &e&lSHIFT-LEFT CLICK"));
        lore.add(Colorize.format("&7Use while facing a player to mark tham as an &3ally&7."));

        meta.setLore(lore);
        item.setItemMeta(meta);
        gandalfStaff = item;
    }
    private static void createSarumanStaff() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName(Colorize.format("&7[Saruman's Staff]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Telekinesis &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Launch your enemy into the sky, and hold them their for 2 seconds"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        lore.add(Colorize.format("&6Ability: Fiery Curse &e&lLEFT CLICK"));
        lore.add(Colorize.format("&7Launches a cluster of fireballs to deal massive damage!"));
        lore.add(Colorize.format("&8Cooldown: 45s"));
        lore.add(Colorize.format("&6Ability: Illumination &e&lSHIFT-RIGHT CLICK"));
        lore.add(Colorize.format("&7Emits a bright light. All players within 7 blocks &dglowing"));
        lore.add(Colorize.format("&7and &dblindness &7for 15 seconds."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        sarumanStaff = item;
    }

    private static void createRadagastStaff() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(3);
        meta.setDisplayName(Colorize.format("&2[Radagast's Staff]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Nature's call &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Summons wolves to aid you in battle. Wolves will disappear after"));
        lore.add(Colorize.format("&730 seconds."));
        lore.add(Colorize.format("&8Cooldown: 120s"));
        lore.add(Colorize.format("&6Ability: Forest Renewal &e&lLEFT CLICK"));
        lore.add(Colorize.format("&7Rejuvenate all players within 10 blocks and give them &dregeneration 2"));
        lore.add(Colorize.format("&7and &dsaturation 1 &7for 10 seconds."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        lore.add(Colorize.format("&6Ability: Entangling Vines &e&lSHIFT-RIGHT CLICK"));
        lore.add(Colorize.format("&7Releases entangling vines towards your enemy. Vines will root your foe"));
        lore.add(Colorize.format("&7to the ground for 5 seconds and give them &dpoison 2 &7for 7 seconds."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        radagastStaff = item;
    }

    public static void createGaladrielPhial() {
        ItemStack item = new ItemStack(Material.EXPERIENCE_BOTTLE,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(2);
        meta.setDisplayName(Colorize.format("&f[Galadriel's Phial]"));
        AttributeModifier modifier = new AttributeModifier("GaladrielMovementSpeed", 0.1, AttributeModifier.Operation.ADD_NUMBER);
        meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, modifier);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Radiant Elixir &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Blasts the surrounding area with &fGaladriel's Light&7. &fGaladriel's"));
        lore.add(Colorize.format("&fLight &7gives all players within 10 blocks &dregeneration 2 &7for 30"));
        lore.add(Colorize.format("&7seconds. It also slowly damages all hostile mobs within 10 blocks."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        galadrielPhial = item;
    }

    public static void createAmogusRing() {
        ItemStack item = new ItemStack(Material.GOLD_NUGGET,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(69);
        meta.setDisplayName(Colorize.format("&4[AMOGUS &bRING]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: AMOGUS &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7AMOGUS YES SUS IMPOSTER RED"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusRing = item;
    }

    private static void createBalrogWhip() {
        ItemStack item = new ItemStack(Material.FISHING_ROD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[Balrog's Whip]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Infernal Sweep &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Cast a firey whip to entangle your enemies."));
        lore.add(Colorize.format("&8Cooldown: 2s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        BalrogWhip = item;
    }

    private static void createMorgulBlade() {
        ItemStack item = new ItemStack(Material.STONE_SWORD,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&2[Morgul Blade]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: Venom's Cast &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Give your enemy &dwithering 1 &7for 30 seconds."));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        MorgulBlade = item;
    }

    private static void createAmogusJetBomb() {
        ItemStack item = new ItemStack(Material.TNT,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS BOMB]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: BOMBS AWAY &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7Drop a bunch of bombs where you are. (Try not to explode yourself)"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusJetBomb = item;
    }
    private static void createAmogusJetGuns() {
        ItemStack item = new ItemStack(Material.SPECTRAL_ARROW,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS GUNS]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: GUNS &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7PEW PEW PEW PEW"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusJetGuns = item;
    }
    private static void createAmogusJetWings() {
        ItemStack item = new ItemStack(Material.ELYTRA,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS WINGS]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusJetWings = item;
    }

    private static void createAmogusJet() {
        ItemStack item = new ItemStack(Material.CARVED_PUMPKIN,1);
        ItemMeta meta = item.getItemMeta();
        meta.setCustomModelData(6);
        meta.setDisplayName(Colorize.format("&4[AMOGUS JET]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusJet = item;
    }
    private static void createAmogusRocketJets() {
        ItemStack item = new ItemStack(Material.STICK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS ROCKET JET]"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = new ArrayList<>();
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusRocketJet = item;
    }

    private static void createAmogusDroneMines() {
        ItemStack item = new ItemStack(Material.TNT_MINECART,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS DRONE MINES]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: FLOATING BOMBS &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7KAABOOMMM"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusDroneMines = item;
    }

    private static void createAmogusMissles() {
        ItemStack item = new ItemStack(Material.ARROW,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS MISSILES]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: MISSILES!!! &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7KAABOOMMM"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusMissles = item;
    }

    private static void createAmogusBarrelRoll() {
        ItemStack item = new ItemStack(Material.BARREL,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS BARREL ROLL RIGHT]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: ROLL RIGHT!!! &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7WHEEEEEEEEEE RIGHT"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusBarrelRoll = item;
    }
    private static void createAmogusBarrelRollLeft() {
        ItemStack item = new ItemStack(Material.BARREL,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS BARREL ROLL LEFT]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: ROLL LEFT!!! &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7WHEEEEEEEEEE LEFT"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusBarrelRollLeft = item;
    }
    private static void createAmogusNuke() {
        ItemStack item = new ItemStack(Material.COMMAND_BLOCK,1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Colorize.format("&4[AMOGUS BARREL NUKE]"));
        List<String> lore = new ArrayList<>();
        lore.add(Colorize.format("&6Ability: NUKEEE!!! &e&lRIGHT CLICK"));
        lore.add(Colorize.format("&7OMEGA NUKE"));
        lore.add(Colorize.format("&8Cooldown: 60s"));
        meta.setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
        item.setItemMeta(meta);
        AmogusNuke = item;
    }
}
