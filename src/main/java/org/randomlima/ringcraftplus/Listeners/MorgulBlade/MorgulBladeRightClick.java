package org.randomlima.ringcraftplus.Listeners.MorgulBlade;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

public class MorgulBladeRightClick implements Listener {
    private CooldownManager cooldownManager;
    private final RingCraftPlus main;
    public MorgulBladeRightClick(RingCraftPlus main) {
        this.main = main;
        //new cooldown manager class with 45 seconds of cooldown. We need to pass in the main class for the BukkitRunnable.
        this.cooldownManager = new CooldownManager(main, 60);
        cooldownManager.setCooldownMessage("&7Fiery Curse is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getLore() != null && event.getItem().getLore().equals(CustomItems.MorgulBlade.getLore())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            event.setCancelled(true);
            if(player.getTargetEntity(50)!=null && (player.getTargetEntity(50) instanceof LivingEntity)){
                cooldownManager.setCooldown(player);
                LivingEntity entity = (LivingEntity) player.getTargetEntity(50);
                entity.addPotionEffect(PotionEffectType.WITHER.createEffect(600, 1));
            } else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
            }
        }
    }
}
