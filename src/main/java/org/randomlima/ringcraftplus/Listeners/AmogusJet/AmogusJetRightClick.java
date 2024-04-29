package org.randomlima.ringcraftplus.Listeners.AmogusJet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

public class AmogusJetRightClick implements Listener {
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    public AmogusJetRightClick(RingCraftPlus main) {
        this.main = main;
        //new cooldown manager class with 45 seconds of cooldown. We need to pass in the main class for the BukkitRunnable.
        this.cooldownManager = new CooldownManager(main, 10);
        cooldownManager.setCooldownMessage("&7AMOGUS BOMB is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.AmogusJetBomb.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            for (int i = 0; i < 20; i++){
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    Location tntLocation = player.getLocation();
                    TNTPrimed tnt = (TNTPrimed) player.getWorld().spawnEntity(tntLocation, EntityType.PRIMED_TNT);
                    player.playSound(player, Sound.ENTITY_TNT_PRIMED, 1,1);
                    tnt.setFuseTicks(60);
                }, i*5);
            }
        }
    }
}
