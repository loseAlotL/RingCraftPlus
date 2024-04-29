package org.randomlima.ringcraftplus.Listeners.GaladrielPhial;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class GaladrielPhialRightClick implements Listener {
    private int taskID;
    private int cooldown = 60;
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    public GaladrielPhialRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.galadrielPhial.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE,1, 1);
            for (Entity entity : player.getNearbyEntities(10, 10, 10)){
                if (entity instanceof Player) {
                    Player nearbyPlayer = (Player) entity;
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
                }
                if (entity instanceof LivingEntity && !(entity instanceof Player) && isHostile(entity)){
                    LivingEntity lentity = (LivingEntity) entity;
                    damageTask(lentity, player);
                }
            }
        }
    }
    private void damageTask(LivingEntity entity, Player player) {
        entity.damage(3);
        final int[] ticks = {0};
        final int durationTicks = 3;
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

            @Override
            public void run() {
                if (ticks[0] >= durationTicks) {
                    Bukkit.getScheduler().cancelTask(taskID); // Stop the task after 3 seconds
                    return;
                }
                // Deal damage every second
                entity.damage(3);
                entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(10, 1));
                for (double i = 0; i <360; i +=5){
                    Location loc = player.getLocation();
                    double angle = i * Math.PI / 180;
                    double x = 5 * Math.cos(angle);
                    double z = 5 * Math.sin(angle);
                    loc.getWorld().spawnParticle(Particle.END_ROD, loc.getX() + x, loc.getY() + 0.5, loc.getZ() + z, 0);
                }
                ticks[0]++;
            }
        }, 0L, 20L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }

    public boolean isHostile(Entity entity) {
        EntityType type = entity.getType();
        return type == EntityType.ZOMBIE ||
                type == EntityType.SKELETON ||
                type == EntityType.SPIDER ||
                type == EntityType.CREEPER ||
                type == EntityType.WITHER ||
                type == EntityType.WITHER_SKELETON ||
                type == EntityType.ENDER_DRAGON ||
                type == EntityType.ENDERMAN ||
                type == EntityType.ENDERMITE ||
                type == EntityType.PHANTOM ||
                type == EntityType.RAVAGER ||
                type == EntityType.SHULKER ||
                type == EntityType.SHULKER_BULLET ||
                type == EntityType.SILVERFISH ||
                type == EntityType.SKELETON_HORSE ||
                type == EntityType.SLIME ||
                type == EntityType.STRAY ||
                type == EntityType.VEX ||
                type == EntityType.CAVE_SPIDER ||
                type == EntityType.VINDICATOR ||
                type == EntityType.MAGMA_CUBE ||
                type == EntityType.GUARDIAN ||
                type == EntityType.ELDER_GUARDIAN ||
                type == EntityType.GHAST ||
                type == EntityType.EVOKER ||
                type == EntityType.ZOGLIN ||
                type == EntityType.ZOMBIE_VILLAGER ||
                type == EntityType.BLAZE ||
                type == EntityType.PIGLIN ||
                type == EntityType.PIGLIN_BRUTE ||
                type == EntityType.ZOMBIFIED_PIGLIN ||
                type == EntityType.HOGLIN ||
                type == EntityType.PILLAGER ||
                type == EntityType.WARDEN;
    }
}
