package org.randomlima.ringcraftplus.Listeners.SarumanStaff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SarumanStaffLeftClick implements Listener {
    private final RingCraftPlus main;
    private CooldownManager cooldownManager; //cooldown manager class
    public SarumanStaffLeftClick(RingCraftPlus main) {
        this.main = main;
        //new cooldown manager class with 45 seconds of cooldown. We need to pass in the main class for the BukkitRunnable.
        this.cooldownManager = new CooldownManager(main, 45);
        cooldownManager.setCooldownMessage("&7Fiery Curse is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }
    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        if (wizardList.contains(player)){
            cooldownManager.changeCooldownTimer(22.5); // changes the cooldown timer to half of 45 seconds.
        } else {
            cooldownManager.changeCooldownTimer(60); // sets the cooldown timer back to one minute.
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.sarumanStaff.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof LivingEntity)) {
                LivingEntity entity = (LivingEntity) player.getTargetEntity(100);
                int numberOfFireballs = 5;
                for (int i = 0; i < numberOfFireballs; i++) {
                    final int delay = i * 20;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                        Location loc = entity.getLocation(); // Get the current location of the entity
                        Location spawnLoc = loc.clone().add(0, 50, 0);
                        LargeFireball fireball = entity.getWorld().spawn(spawnLoc, LargeFireball.class);
                        fireball.setVelocity(new Vector(0, -0.1, 0));
                        fireball.setYield(2);
                        fireball.setShooter(player);
                        if (entity.isDead()){
                            return;
                        }
                    }, delay);
                }
                cooldownManager.setCooldown(player); //sets the cooldown of a player.
                player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK,1  ,1);
            } else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
            }
        }
    }
}
