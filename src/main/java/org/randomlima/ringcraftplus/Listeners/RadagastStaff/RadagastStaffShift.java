package org.randomlima.ringcraftplus.Listeners.RadagastStaff;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class RadagastStaffShift implements Listener {
    private final RingCraftPlus main;
    public RadagastStaffShift(RingCraftPlus main) {
        this.main = main;
    }


    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 60 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.radagastStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof Player)){
                rootPlayer((Player) player.getTargetEntity(100));
                spawnBlock1(player.getTargetEntity(100));
                spawnParticle(player.getTargetEntity(100));
                setCooldown(player);
            } else if(player.getTargetEntity(100) instanceof LivingEntity){
                rootEntity((LivingEntity) player.getTargetEntity(100));
                spawnBlock1(player.getTargetEntity(100));
                spawnParticle(player.getTargetEntity(100));
                setCooldown(player);
            } else{
                player.sendMessage(Colorize.format("&7There are no players in your direction or they are too far away!"));
            }
        }
    }

    private void rootPlayer(Player entity){
        entity.addPotionEffect(PotionEffectType.SLOW.createEffect(200, 255));
        entity.addPotionEffect(PotionEffectType.JUMP.createEffect(200, 128));
        entity.setCollidable(false);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            entity.setCollidable(true);
        }, 20 * 10);
    }
    private void rootEntity(LivingEntity entity){
        entity.setAI(false);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            entity.setAI(true);
        }, 20 * 10);
    }

    private void spawnBlock1(Entity player){
        Location loc = player.getLocation();
        loc.setY(loc.getY() -0.4);
        FallingBlock block = player.getWorld().spawnFallingBlock(loc, Material.OAK_LEAVES, (byte) 0);
        block.setGravity(false);
        loc.setY(loc.getY() +0.6);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            block.remove();
        }, 20 * 10);
    }

    private void spawnParticle(Entity player){
        Location loc = player.getLocation();
        loc.setY(loc.getY() -0.4);
        for (double i = 0; i <360; i +=5){
            double angle = i * Math.PI / 180;
            double x = 1 * Math.cos(angle);
            double z = 1 * Math.sin(angle);
            loc.getWorld().spawnParticle(Particle.SLIME, loc.getX() + x, loc.getY() + 0.5, loc.getZ() + z, 0);
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
        player.sendMessage(Colorize.format("&7Entangling Vines is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}