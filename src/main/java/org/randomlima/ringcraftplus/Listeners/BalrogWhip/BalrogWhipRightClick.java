package org.randomlima.ringcraftplus.Listeners.BalrogWhip;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.Random;

public class BalrogWhipRightClick implements Listener {
    private int cooldown = 60;
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    private final Random random = new Random();
    public BalrogWhipRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.BalrogWhip.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            if(player.getTargetEntity(10) != null && player.getTargetEntity(10) instanceof LivingEntity){
                LivingEntity entity = (LivingEntity) player.getTargetEntity(10);
                Location eLoc = entity.getLocation();
                Location pLoc = player.getEyeLocation();
                double height = random.nextDouble() * (3 - 1) + 1;
                spawnCurvedLineParticles(pLoc, eLoc, 50, height);
            }else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
            }
        }
    }
    private void spawnCurvedLineParticles(Location start, Location end, int count, double curveHeight) {
        World world = start.getWorld();
        double spacing = 1.0 / count;

        for (double t = 0; t <= 1; t += spacing) {
            double x = (1 - t) * start.getX() + t * end.getX();
            double y = (1 - t) * start.getY() + t * end.getY() + curveHeight * Math.sin(Math.PI * t); // Adjust curve with sin function
            double z = (1 - t) * start.getZ() + t * end.getZ();

            Location particleLoc = new Location(world, x, y, z);
            world.spawnParticle(Particle.FLAME, particleLoc, 0);
        }
    }
}
