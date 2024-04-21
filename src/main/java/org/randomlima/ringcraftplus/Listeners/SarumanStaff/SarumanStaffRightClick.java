package org.randomlima.ringcraftplus.Listeners.SarumanStaff;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class SarumanStaffRightClick implements Listener {
    private final RingCraftPlus main;
    public SarumanStaffRightClick(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 60 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.sarumanStaff.getItemMeta())) {
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            setCooldown(player);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof LivingEntity)) {
                LivingEntity entity = (LivingEntity) player.getTargetEntity(100);
                Vector velocity = entity.getVelocity();
                velocity.setY(2);
                entity.setVelocity(velocity);
                setCooldown(player);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    entity.setGravity(false);
                }, 20 * 1);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    entity.setGravity(true);
                }, 20 * 4);
            } else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
            }
        }
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
        player.sendMessage(Colorize.format("&7Telekinesis is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
