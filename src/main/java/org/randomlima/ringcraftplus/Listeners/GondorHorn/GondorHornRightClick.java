package org.randomlima.ringcraftplus.Listeners.GondorHorn;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.CooldownManager;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class GondorHornRightClick implements Listener {
    private RingCraftPlus plugin;
    private CooldownManager cooldownManager;

    public GondorHornRightClick(RingCraftPlus plugin){
        this.plugin = plugin;
        cooldownManager = new CooldownManager(plugin, 50);
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.gondorHorn.getItemMeta())){
            if (cooldownManager.isOnCooldown(player.getUniqueId())) {
                event.setCancelled(true);
                cooldownManager.displayCooldownTime(player.getUniqueId());
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player.getUniqueId());
            player.playSound(player, Sound.ITEM_GOAT_HORN_SOUND_1,1, 1);
            for (Entity entity : player.getNearbyEntities(10, 10, 10)){
                if (entity instanceof Player) {
                    Player nearbyPlayer = (Player) entity;
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
                }
            }
        }
    }

}
