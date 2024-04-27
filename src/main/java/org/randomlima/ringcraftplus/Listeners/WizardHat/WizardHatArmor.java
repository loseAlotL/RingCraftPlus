package org.randomlima.ringcraftplus.Listeners.WizardHat;

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

public class WizardHatArmor implements Listener {
    List<Player> wizards = new ArrayList<>();


    private final RingCraftPlus main;
    public WizardHatArmor(RingCraftPlus main) {
        this.main = main;
    }
    @EventHandler
    public void onHelmetEquip(PlayerArmorChangeEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getLore() != null && player.getInventory().getHelmet().getLore().equals(CustomItems.WizardHat.getLore())) {
            wizards.add(player);
        } else if (wizards.contains(player)) {
            wizards.remove(player);
        }
    }

    public List<Player> getWizardList() {
        return wizards;
    }
}
