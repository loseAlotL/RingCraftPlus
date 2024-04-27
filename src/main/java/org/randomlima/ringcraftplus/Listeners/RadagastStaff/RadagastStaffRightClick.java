package org.randomlima.ringcraftplus.Listeners.RadagastStaff;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.BlockIterator;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RadagastStaffRightClick implements Listener {
    private final RingCraftPlus main;
    public RadagastStaffRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }
    private int cooldown = 60;
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
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.radagastStaff.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            event.setCancelled(true);
            summonWolves(player, 7);
        }
    }
    public void summonWolves(Player player, int numWolves){
        if(player.getTargetEntity(100)!=null && (player.getTargetEntity(100) instanceof LivingEntity)){
            player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 1, 1);
            for (int i = 0; i < numWolves; i++) {
                Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
                wolf.setCustomName(player.getName() + "'s wolf");
                // Customize other properties of the wolf if needed
                wolf.setCollarColor(DyeColor.BLACK);
                wolf.addPotionEffect(PotionEffectType.SPEED.createEffect(600, 4));
                wolf.setAngry(true);
                wolf.setOwner(player);
                wolf.setTarget((LivingEntity) player.getTargetEntity(100));
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_WHINE, 1.0f, 1.0f);
                }, 20 * 30);
                Bukkit.getScheduler().runTaskLater(main, wolf::remove, 20 * 30);
            }
            cooldownManager.setCooldown(player);
        } else {
            player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
        }
    }
}
