package org.randomlima.ringcraftplus.Listeners.GaladrielPhial;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Entity;
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
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class GaladrielPhialRightClick implements Listener {
    private int taskID;

    private final RingCraftPlus main;
    public GaladrielPhialRightClick(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 60 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.galadrielPhial.getLore())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            setCooldown(player);
            player.playSound(player, Sound.BLOCK_BEACON_ACTIVATE,1, 1);
            for (Entity entity : player.getNearbyEntities(10, 10, 10)){
                if (entity instanceof Player) {
                    Player nearbyPlayer = (Player) entity;
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 1));
                }
                if (entity instanceof LivingEntity && !(entity instanceof Player)){
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

    private boolean isOnCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            long currentTime = System.currentTimeMillis();
            long lastUseTime = cooldowns.get(player.getUniqueId());
            return (currentTime - lastUseTime) < cooldownDuration;
        }
        return false;
    }
    private void setCooldown(Player player) {
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    private void displayCooldownTime(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = cooldowns.get(player.getUniqueId());
        long remainingTimeMillis = cooldownDuration - (currentTime - lastUseTime);

        int remainingSeconds = (int) (remainingTimeMillis / 1000);
        player.sendMessage(Colorize.format("&7Radiant Elixir is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
