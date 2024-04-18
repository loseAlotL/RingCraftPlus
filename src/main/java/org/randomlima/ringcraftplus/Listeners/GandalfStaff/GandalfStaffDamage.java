package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

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
        if (event.getEntity() instanceof Player){
            Entity damagerEntity = event.getDamager();
            Entity damagedEntity = event.getEntity();
            if (event.getDamager() instanceof Player &&  event.getDamager().isSneaking() && ((Player) event.getDamager()).getItemInHand().getItemMeta().equals(CustomItems.gandalfStaff)){
                Player damager = (Player) damagerEntity;
                Player damagedPlayer = (Player) damagedEntity;
                if (!listOfPlayers.contains(damagedPlayer)){
                    listOfPlayers.add(damagedPlayer);
                    event.setCancelled(true);
                    damager.sendMessage("You have added: "+ damagedPlayer.getName() + "to your list of allies.");
                } else {
                    listOfPlayers.remove(damagedPlayer);
                    event.setCancelled(true);
                    damager.sendMessage("You have removed: "+ damagedPlayer.getName() + "from your list of allies.");
                }
            } else {return;}
        } else {return;}
    }
}
