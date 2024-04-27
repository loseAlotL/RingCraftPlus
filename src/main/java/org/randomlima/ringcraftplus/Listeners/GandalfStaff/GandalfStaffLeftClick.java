package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GandalfStaffLeftClick implements Listener {

    private final RingCraftPlus main;
    private CooldownManager cooldownManager;
    private int cooldown = 60;
    public GandalfStaffLeftClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }

    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        System.out.println(wizardList);
        if (wizardList.contains(player)){
            cooldownManager.changeCooldownTimer(30); // changes the cooldown timer to half of 45 seconds.
        } else {
            cooldownManager.changeCooldownTimer(60); // sets the cooldown timer back to one minute.
        }
    }


    @EventHandler
    public void onLeftClick(PlayerInteractEvent event){
        //GandalfStaffDamage gandalfStaffDamage = new GandalfStaffDamage();
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && !event.getPlayer().isSneaking() && event.getItem().equals(CustomItems.gandalfStaff)){
            List<Player> gandalfAllies = GandalfStaffDamage.getGandalfList();
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            if (gandalfAllies == null || gandalfAllies.isEmpty()) {
                cooldownManager.setCooldown(player);
                player.playSound(player.getLocation(), Sound.ENTITY_ARMOR_STAND_HIT, 1, 1);
                player.sendMessage(Colorize.format("&7You have not set any allies!"));
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            if(gandalfAllies.size() == 8){
                for(Player gandalfAlly : gandalfAllies){
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2));
                    gandalfAlly.playSound(gandalfAlly, Sound.ENTITY_GHAST_WARN, 1, 1);
                    for (double i = 0; i <360; i +=5){
                        Location loc = gandalfAlly.getLocation();
                        double angle = i * Math.PI / 180;
                        double x = 1.3 * Math.cos(angle);
                        double z = 1.3 * Math.sin(angle);
                        loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.getX() + x, loc.getY() + 2, loc.getZ() + z, 0);
                    }
                }
                return;
            }
            for(Player gandalfAlly : gandalfAllies){
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2));
                gandalfAlly.playSound(gandalfAlly, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                for (double i = 0; i <360; i +=5){
                    Location loc = gandalfAlly.getLocation();
                    double angle = i * Math.PI / 180;
                    double x = 1.3 * Math.cos(angle);
                    double z = 1.3 * Math.sin(angle);
                    loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.getX() + x, loc.getY() + 2, loc.getZ() + z, 0);
                }
            }
        }
    }
}
