package org.randomlima.ringcraftplus;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Commands.*;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.AmogusJet.AmogusJetGunsRightClick;
import org.randomlima.ringcraftplus.Listeners.AmogusJet.AmogusJetRightClick;
import org.randomlima.ringcraftplus.Listeners.AmogusJet.AmogusRocketJet;
import org.randomlima.ringcraftplus.Listeners.AmogusRing.AmogusRingRightClick;
import org.randomlima.ringcraftplus.Listeners.AngmarHelmet.AngmarHelmetArmor;
import org.randomlima.ringcraftplus.Listeners.BalrogWhip.BalrogWhipRightClick;
import org.randomlima.ringcraftplus.Listeners.BlockPlace.AngmarAndWizardHat;
import org.randomlima.ringcraftplus.Listeners.GaladrielPhial.GaladrielPhialRightClick;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffDamage;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffLeftClick;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffRightClick;
//import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffShift;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffShift;
import org.randomlima.ringcraftplus.Listeners.GondorHorn.GondorHornRightClick;
import org.randomlima.ringcraftplus.Listeners.MorgulBlade.MorgulBladeRightClick;
import org.randomlima.ringcraftplus.Listeners.Palantir.PalantirRightClick;
import org.randomlima.ringcraftplus.Listeners.RadagastStaff.RadagastStaffLeftClick;
import org.randomlima.ringcraftplus.Listeners.RadagastStaff.RadagastStaffRightClick;
import org.randomlima.ringcraftplus.Listeners.RadagastStaff.RadagastStaffShift;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffLeftClick;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffRightClick;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffShift;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;

import java.util.List;

public final class RingCraftPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.err.println("Server has started.");
        CustomItems.init();
        this.getCommand("rcpangmarhelmet").setExecutor(new AngmarHelmet());
        this.getCommand("rcppalantir").setExecutor(new Palantir());
        this.getCommand("rcpgondorhorn").setExecutor(new GondorHorn());
        this.getCommand("rcpgandalfstaff").setExecutor(new GandalfStaff());
        this.getCommand("rcpsarumanstaff").setExecutor(new SarumanStaff());
        this.getCommand("rcpgaladrielphial").setExecutor(new GaladrielPhial());
        this.getCommand("rcpradagaststaff").setExecutor(new RadagastStaff());
        this.getCommand("rcpwizardhat").setExecutor(new WizardHat());
        this.getCommand("rcpamogusring").setExecutor(new AmogusRing());
        this.getCommand("rcpurukhaihelmet").setExecutor(new UrukHaiHelmet());
        this.getCommand("rcpurukhaishield").setExecutor(new UrukHaiShield());
        this.getCommand("rcpurukhaisword").setExecutor(new UrukHaiSword());
        this.getCommand("rcpbalrogwhip").setExecutor(new BalrogWhip());
        this.getCommand("rcpmorgulblade").setExecutor(new MorgulBlade());
        this.getCommand("rcpamogusjet").setExecutor(new AmogusBomb());

        getServer().getPluginManager().registerEvents(new GondorHornRightClick(this), this);

        getServer().getPluginManager().registerEvents(new PalantirRightClick(this), this);

        getServer().getPluginManager().registerEvents(new GandalfStaffRightClick(this), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffDamage(), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffShift(this), this);

        getServer().getPluginManager().registerEvents(new SarumanStaffRightClick(this), this);
        getServer().getPluginManager().registerEvents(new SarumanStaffLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new SarumanStaffShift(this), this);

        getServer().getPluginManager().registerEvents(new RadagastStaffRightClick(this), this);
        getServer().getPluginManager().registerEvents(new RadagastStaffLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new RadagastStaffShift(this), this);

        getServer().getPluginManager().registerEvents(new GaladrielPhialRightClick(this), this);

        getServer().getPluginManager().registerEvents(new AngmarHelmetArmor(this), this);

        getServer().getPluginManager().registerEvents(new WizardHatArmor(this), this);

        getServer().getPluginManager().registerEvents(new AmogusRingRightClick(this), this);

        getServer().getPluginManager().registerEvents(new BalrogWhipRightClick(this), this);

        getServer().getPluginManager().registerEvents(new AngmarAndWizardHat(), this);

        getServer().getPluginManager().registerEvents(new MorgulBladeRightClick(this), this);

        getServer().getPluginManager().registerEvents(new AmogusJetRightClick(this), this);

        getServer().getPluginManager().registerEvents(new AmogusJetGunsRightClick(this), this);

        getServer().getPluginManager().registerEvents(new AmogusRocketJet(this),this);

        getServer().getScheduler().runTaskTimer(this, () -> {
            // Iterate through online players and check if they are in water
            for (Player player : getServer().getOnlinePlayers()) {
                Material blockType = player.getLocation().getBlock().getType();
                if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getItemMeta().getLore() != null && player.getInventory().getHelmet().getLore().equals(CustomItems.AngmarHelmet)){
                    if (blockType == Material.WATER) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 20, 5));
                    }
                }
            }
        }, 0L, 10L); // 10 ticks = 0.5 seconds

        getServer().getScheduler().runTaskTimer(this, () -> {
            AngmarHelmetArmor pumpkinClass = new AngmarHelmetArmor(this);
            List<Player> pumpkinList = pumpkinClass.getPumpkinList();
            for(Player player : pumpkinList){
                System.out.println(player.getName());
                for (Entity nearplayer : player.getNearbyEntities(10, 10, 10)){
                    if (nearplayer instanceof LivingEntity){
                        ((LivingEntity) nearplayer).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 10, 1));
                    }
                }
            }
        }, 0L, 10L);
    }

    @Override
    public void onDisable() {
        System.err.println("Server has stopped.");
        // Plugin shutdown logic
    }
}
