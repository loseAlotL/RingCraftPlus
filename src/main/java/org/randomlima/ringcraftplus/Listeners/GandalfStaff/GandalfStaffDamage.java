package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;

import java.util.ArrayList;
import java.util.List;

public class GandalfStaffDamage implements Listener {
    static List<Player> gandalfAllies = new ArrayList<>();

    @EventHandler
    public void onDamageEvent(EntityDamageByEntityEvent event){
        if (event.getDamager() instanceof Player &&  event.getDamager().isSneaking() && ((Player) event.getDamager()).getInventory().getItemInMainHand().getItemMeta().equals(CustomItems.gandalfStaff.getItemMeta()) && (event.getEntity() instanceof Player)){
            Entity damagerEntity = event.getDamager();
            Entity damagedEntity = event.getEntity();
            Player damager = (Player) damagerEntity;
            Player damagedPlayer = (Player) damagedEntity;
            if (!gandalfAllies.contains(damagedPlayer)){
                gandalfAllies.add(damagedPlayer);
                event.setCancelled(true);
                damager.sendMessage(Colorize.format("&2You have added: "+ damagedPlayer.getName() + " to your list of allies."));
                damagedPlayer.sendMessage(Colorize.format("&2You have been added to "+damager.getName()+"'s ally list."));
                damager.playSound(damager, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1, 1);
            } else {
                gandalfAllies.remove(damagedPlayer);
                event.setCancelled(true);
                damager.sendMessage(Colorize.format("&cYou have removed: "+ damagedPlayer.getName() + " from your list of allies."));
                damagedPlayer.sendMessage(Colorize.format("&cYou have been removed to "+damager.getName()+"'s ally list."));
                damager.playSound(damager, Sound.ENTITY_ARMOR_STAND_BREAK,1, 1);
            }
        } else {return;}
    }

    public static List<Player> getGandalfList() {
        return gandalfAllies;
    }
}
