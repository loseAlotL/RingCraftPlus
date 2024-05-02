package org.randomlima.ringcraftplus.Listeners.AmogusJet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Bukkit.getServer;

public class AmogusMissles implements Listener {

    List<ArrowInfo> arrowList = new ArrayList<>();
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    public AmogusMissles(RingCraftPlus main) {
        this.main = main;
        //new cooldown manager class with 45 seconds of cooldown. We need to pass in the main class for the BukkitRunnable.
        this.cooldownManager = new CooldownManager(main, 10);
        cooldownManager.setCooldownMessage("&7AMOGUS MISSILES is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
        arrowLoop();
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.AmogusMissles.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            for (int i = 0; i < 5; i++){
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    player.playSound(player, Sound.ENTITY_WARDEN_SONIC_BOOM, 1, 1);
                    Location eyeLocation = player.getEyeLocation();
                    Vector direction = eyeLocation.getDirection().normalize();
                    // Spawn an arrow at the player's eye location with the calculated direction
                    Location targetLocation = player.getLocation().add(player.getLocation().getDirection().multiply(1));
                    Arrow arrow = player.getWorld().spawnArrow(targetLocation, player.getLocation().getDirection(), 1.0f, 0.0f);
                    //FallingBlock fallingBlock = player.getWorld().spawnFallingBlock(arrow.getLocation(), Material.BEDROCK, (byte) 0);
                    arrow.setShooter(player);
                    //arrow.setPassenger(fallingBlock);
                    //fallingBlock.setGlowing(true);
                    arrow.setVelocity(direction.multiply(4));
                    player.playSound(player, Sound.ENTITY_ARROW_SHOOT, 1, 1);
                    arrow.setGlowing(true);
                    arrowList.add(new ArrowInfo(arrow, player));
                    Bukkit.getScheduler().runTaskLater(main, () -> {
                        arrow.remove();
                        //fallingBlock.remove();
                        arrowList.remove(new ArrowInfo(arrow, player));
                    }, 100);
                }, i*10);
            }
        }
    }
    public void arrowLoop() {
        Bukkit.getScheduler().runTaskTimer(main, () -> {
            for (ArrowInfo arrowInfo : arrowList) {
                Arrow arrow = arrowInfo.getArrow();
                Player owner = arrowInfo.getOwner();

                for (Entity entity : arrow.getNearbyEntities(5, 5, 5)) {
                    if (entity instanceof LivingEntity && !entity.equals(owner)) {
                        entity.getWorld().createExplosion(entity.getLocation(), 2);
                    }
                }
            }
        }, 0L, 5L);
    }
    public static class ArrowInfo {
        private Arrow arrow;
        private Player owner;

        public ArrowInfo(Arrow arrow, Player owner) {
            this.arrow = arrow;
            this.owner = owner;
        }

        public Arrow getArrow() {
            return arrow;
        }

        public Player getOwner() {
            return owner;
        }
    }

}
