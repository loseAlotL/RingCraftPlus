package org.randomlima.ringcraftplus.Listeners.Palantir;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
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
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class PalantirRightClick implements Listener {
    private final RingCraftPlus main;
    private int cooldown = 60;
    public PalantirRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }
    private CooldownManager cooldownManager;
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.Palantir.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            player.playSound(player, Sound.BLOCK_BEACON_AMBIENT,1, 1);
            for (Entity entity : player.getNearbyEntities(10, 10, 10)){
                if(entity instanceof LivingEntity){
                    ((LivingEntity) entity).addPotionEffect(PotionEffectType.GLOWING.createEffect(300, 1));
                }
            }
            for (double i = 0; i <360; i +=1){
                Location loc = player.getLocation();
                double angle = i * Math.PI / 180;
                double x = 10 * Math.cos(angle);
                double z = 10 * Math.sin(angle);
                loc.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, loc.getX() + x, loc.getY() + 0.5, loc.getZ() + z, 0);
            }
        }
    }
}
