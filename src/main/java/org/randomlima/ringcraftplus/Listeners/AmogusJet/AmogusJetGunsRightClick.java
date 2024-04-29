package org.randomlima.ringcraftplus.Listeners.AmogusJet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

public class AmogusJetGunsRightClick implements Listener {
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    public AmogusJetGunsRightClick(RingCraftPlus main) {
        this.main = main;
        //new cooldown manager class with 45 seconds of cooldown. We need to pass in the main class for the BukkitRunnable.
        this.cooldownManager = new CooldownManager(main, 1);
        cooldownManager.setCooldownMessage("&7AMOGUS BOMB is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.AmogusJetGuns.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            for (int i = 0; i < 20; i++){
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    Location eyeLocation = player.getEyeLocation();
                    Vector direction = eyeLocation.getDirection().normalize();

                    // Spawn an arrow at the player's eye location with the calculated direction
                    Arrow arrow = player.launchProjectile(Arrow.class, direction);
                    arrow.setVelocity(direction.multiply(3));
                    player.playSound(player, Sound.ENTITY_ARROW_SHOOT, 1, 1);
                    arrow.setGlowing(true);
                    Bukkit.getScheduler().runTaskLater(main, arrow::remove, 100);
                }, i*2);
            }
        }
    }
}
