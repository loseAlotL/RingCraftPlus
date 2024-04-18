package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;

import java.util.ArrayList;
import java.util.List;

public class GandalfStaffDamage implements Listener {
    List<Player> listOfPlayers = new ArrayList<>();
    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player &&  event.getDamager().isSneaking() && ((Player) event.getDamager()).getItemInHand().getItemMeta().equals(CustomItems.gandalfStaff)){
            Entity damagerEntity = event.getDamager();
            Entity damagedEntity = event.getEntity();
            if (event.getEntity() instanceof Player){
                Player damager = (Player) damagerEntity;
                Player damagedPlayer = (Player) damagedEntity;
                if (!listOfPlayers.contains(damagedPlayer)){
                    listOfPlayers.add(damagedPlayer);
                    event.setCancelled(true);
                    damager.sendMessage("You have added: "+ damagedPlayer.getName() + "to your list of allies.");
                    damager.playSound(damager, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1, 1);
                } else {
                    listOfPlayers.remove(damagedPlayer);
                    event.setCancelled(true);
                    damager.sendMessage("You have removed: "+ damagedPlayer.getName() + "from your list of allies.");
                    damager.playSound(damager, Sound.ENTITY_ARMOR_STAND_BREAK,1, 1);
                }
            } else {return;}
        } else {return;}
    }
}
