package org.randomlima.ringcraftplus.Listeners.AngmarHelmet;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.ArrayList;
import java.util.List;

public class AngmarHelmetArmor implements Listener {
    List<Player> listOfPlayers = new ArrayList<>();


    private final RingCraftPlus main;
    public AngmarHelmetArmor(RingCraftPlus main) {
        this.main = main;
    }
    @EventHandler
    public void onHelmetEquip(PlayerArmorChangeEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getLore().equals(CustomItems.AngmarHelmet)) {
            PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0);
            PotionEffect nv = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0);
            player.addPotionEffect(regen);
            player.addPotionEffect(nv);
            listOfPlayers.add(player);
        } else if (listOfPlayers.contains(player)) {
            player.removePotionEffect(PotionEffectType.REGENERATION);
            player.removePotionEffect(PotionEffectType.NIGHT_VISION);
            listOfPlayers.remove(player);
        }
    }

    public List<Player> getPumpkinList() {
        return listOfPlayers;
    }
}
