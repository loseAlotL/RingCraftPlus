package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GandalfStaffRightClick implements Listener {
    private final RingCraftPlus main;
    public GandalfStaffRightClick(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 60 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.gandalfStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            setCooldown(player);

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
        player.sendMessage(Colorize.format("&7This item is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
