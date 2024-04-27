package org.randomlima.ringcraftplus.Listeners.RadagastStaff;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RadagastStaffShift implements Listener {
    private final RingCraftPlus main;
    public RadagastStaffShift(RingCraftPlus main) {
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
        if (event.getItem() != null && event.getAction().isRightClick() && event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.radagastStaff.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            event.setCancelled(true);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof Player)){
                Player entity = (Player) player.getTargetEntity(100);
                rootPlayer(entity);
                entity.playSound(entity.getLocation(), Sound.ENTITY_WARDEN_EMERGE,1,1);
                spawnBlock1(entity);
                spawnParticle(entity);
                cooldownManager.setCooldown(player);
            } else if(player.getTargetEntity(100) instanceof LivingEntity){
                LivingEntity entity = (LivingEntity) player.getTargetEntity(100);
                rootEntity(entity);
                player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_EMERGE,1,1);
                spawnBlock1(entity);
                spawnParticle(entity);
                cooldownManager.setCooldown(player);
            } else{
                player.sendMessage(Colorize.format("&7There are no players in your direction or they are too far away!"));
            }
        }
    }

    private void rootPlayer(Player entity){
        entity.addPotionEffect(PotionEffectType.SLOW.createEffect(200, 255));
        entity.addPotionEffect(PotionEffectType.JUMP.createEffect(200, 128));
        entity.addPotionEffect(PotionEffectType.WITHER.createEffect(200, 2));
        entity.setCollidable(false);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            entity.setCollidable(true);
        }, 20 * 10);
    }
    private void rootEntity(LivingEntity entity){
        entity.setAI(false);
        entity.addPotionEffect(PotionEffectType.SLOW.createEffect(200, 255));
        entity.addPotionEffect(PotionEffectType.JUMP.createEffect(200, 128));
        entity.addPotionEffect(PotionEffectType.WITHER.createEffect(200, 2));
        Bukkit.getScheduler().runTaskLater(main, () -> {
            entity.setAI(true);
        }, 20 * 10);
    }

    private void spawnBlock1(Entity player){
        Location loc = player.getLocation();
        loc.setY(loc.getY() -0.4);
        FallingBlock block = player.getWorld().spawnFallingBlock(loc, Material.OAK_LEAVES, (byte) 0);
        block.setGravity(false);
        loc.setY(loc.getY() +0.6);
        Bukkit.getScheduler().runTaskLater(main, () -> {
            block.remove();
        }, 20 * 10);
    }

    private void spawnParticle(Entity player){
        Location loc = player.getLocation();
        loc.setY(loc.getY() -0.4);
        for (double i = 0; i <360; i +=5){
            double angle = i * Math.PI / 180;
            double x = 1 * Math.cos(angle);
            double z = 1 * Math.sin(angle);
            loc.getWorld().spawnParticle(Particle.SLIME, loc.getX() + x, loc.getY() + 0.5, loc.getZ() + z, 0);
        }
    }
}