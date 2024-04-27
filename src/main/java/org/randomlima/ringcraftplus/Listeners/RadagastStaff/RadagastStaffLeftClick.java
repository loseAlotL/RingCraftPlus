package org.randomlima.ringcraftplus.Listeners.RadagastStaff;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.AngmarHelmet.AngmarHelmetArmor;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RadagastStaffLeftClick implements Listener {
    private final RingCraftPlus main;
    private int cooldown = 60;
    public RadagastStaffLeftClick(RingCraftPlus main) {
        this.main = main;

        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }
    private CooldownManager cooldownManager;

    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        if (wizardList.contains(player)){
            cooldownManager.changeCooldownTimer(30); // changes the cooldown timer to half of 45 seconds.
        } else {
            cooldownManager.changeCooldownTimer(60); // sets the cooldown timer back to one minute.
        }
    }
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.radagastStaff.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            player.playSound(player.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 1, 1);
            for(Entity entity : player.getNearbyEntities(10,10,10)){
                if(entity instanceof LivingEntity){
                    ((LivingEntity) entity).addPotionEffect(PotionEffectType.SATURATION.createEffect(200, 1));
                    ((LivingEntity) entity).addPotionEffect(PotionEffectType.REGENERATION.createEffect(200, 2));
                }
            }
        }
    }
}
