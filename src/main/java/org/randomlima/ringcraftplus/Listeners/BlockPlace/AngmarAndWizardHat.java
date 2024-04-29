package org.randomlima.ringcraftplus.Listeners.BlockPlace;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;

public class AngmarAndWizardHat implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(event.getPlayer().getInventory().getItemInMainHand().equals(CustomItems.AngmarHelmet) || event.getPlayer().getInventory().getItemInOffHand().equals(CustomItems.AngmarHelmet) || event.getPlayer().getInventory().getItemInMainHand().equals(CustomItems.WizardHat) || event.getPlayer().getInventory().getItemInOffHand().equals(CustomItems.WizardHat)){
            event.setCancelled(true);
        }
    }
}
