package org.randomlima.ringcraftplus.Listeners.Palantir;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.CooldownManager;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class PalantirRightClick implements Listener {
    private final RingCraftPlus main;
    private CooldownManager cooldownManager;
    public PalantirRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, 60);
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getItem().getItemMeta().equals(CustomItems.Palantir.getItemMeta())){
            if (cooldownManager.isOnCooldown(player.getUniqueId())) {
                event.setCancelled(true);
                cooldownManager.displayCooldownTime(player.getUniqueId());
                return;
            }
            event.setCancelled(true);
            cooldownManager.setCooldown(player.getUniqueId());
            player.playSound(player, Sound.BLOCK_BEACON_AMBIENT,1, 1);
            for (Entity entity : player.getNearbyEntities(10, 10, 10)){
                if (entity instanceof Player) {
                    Player nearbyPlayer = (Player) entity;
                    nearbyPlayer.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 300, 1));
                }
            }
        }
    }

}
