package org.randomlima.ringcraftplus.Listeners.AmogusJet;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
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

public class AmogusRocketJet implements Listener {
    private final RingCraftPlus main;
    public AmogusRocketJet(RingCraftPlus main) {
        this.main = main;
    }
    @EventHandler
    public void onLeftClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction().isLeftClick() && event.getPlayer().getInventory().getItemInOffHand().getItemMeta() != null && event.getPlayer().getInventory().getItemInOffHand().getItemMeta().equals(CustomItems.AmogusRocketJet.getItemMeta())){
            Vector forwardDirection = player.getLocation().getDirection();
            player.setVelocity(forwardDirection.multiply(2));
            player.playSound(player, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH,1,1);
        }
    }
}
