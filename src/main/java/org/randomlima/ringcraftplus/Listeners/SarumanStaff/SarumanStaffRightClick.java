package org.randomlima.ringcraftplus.Listeners.SarumanStaff;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SarumanStaffRightClick implements Listener {
    private final RingCraftPlus main;
    private CooldownManager cooldownManager;
    public SarumanStaffRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, 60); //new CooldownManager with 60 seconds.
        this.cooldownManager.setCooldownMessage("&7Telekinesis is on cooldown! Use again in: %seconds% seconds."); //the %seconds% will be replaced with the remaining seconds.
    }
    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        if (wizardList.contains(player)){
            cooldownManager.changeCooldownTimer(30); //sets the new cooldown to half a minute.
        } else {
            cooldownManager.changeCooldownTimer(60); //sets the cooldown back to one minute.
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.sarumanStaff.getItemMeta())) {
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            event.setCancelled(true);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof LivingEntity)) {
                LivingEntity entity = (LivingEntity) player.getTargetEntity(100);
                Vector velocity = entity.getVelocity();
                velocity.setY(2.2);
                entity.setVelocity(velocity);
                cooldownManager.setCooldown(player); //sets the cooldown of the player
                player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1, 1);
                entity.addPotionEffect(PotionEffectType.GLOWING.createEffect(20 * 4, 1));
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_CHARGE, 1, 1);
                    entity.setGravity(false);
                    entity.setVelocity(velocity.setY(0));
                    entity.setAI(false);
                    spawnParticleCircle(entity, -1, 1);
                    spawnParticleCircle(entity, 1, 1);
                }, 15);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    entity.setGravity(true);
                    entity.setAI(true);
                    entity.setVelocity(velocity.setY(-1));
                    player.playSound(player.getLocation(), Sound.ENTITY_WARDEN_SONIC_BOOM, 1, 1);
                }, 20 * 4);
            } else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
            }
        }
    }
    private void spawnParticleCircle(Entity player, double yOfset, double radius){
        Location loc = player.getLocation();
        loc.setY(loc.getY() + yOfset);
        for (double i = 0; i <360; i +=5){
            double angle = i * Math.PI / 180;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            loc.getWorld().spawnParticle(Particle.FLAME, loc.getX() + x, loc.getY() + 0.5, loc.getZ() + z, 0);
        }
    }
}
