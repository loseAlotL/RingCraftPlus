package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GandalfStaffShift implements Listener {
    private int taskID;
    private final RingCraftPlus main;
    public GandalfStaffShift(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 75 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)
    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        if (wizardList.contains(player)){
            cooldownDuration = cooldownDuration/2;
        } else {
            cooldownDuration = 75 * 1000;
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isRightClick() && event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.gandalfStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            setCooldown(player);
            player.playSound(player, Sound.ENTITY_GHAST_SHOOT, 1, 1);
            plotSphere(player.getLocation(), 4, Particle.FIREWORKS_SPARK, 50);
            Bukkit.getScheduler().runTaskLater(main, () -> {
                //plotSphere(player.getLocation(), 4, Particle.END_ROD, 40);
                gandalfAbility(player);
            }, 15);
        }
    }
    public void gandalfAbility(Player player){
        List<Player> gandalfAllies = GandalfStaffDamage.getGandalfList();
        plotSphere(player.getLocation(), 4, Particle.SNOWBALL, 25);
        for (Entity entity : player.getNearbyEntities(8,8,8)){
            if (entity instanceof LivingEntity){
                if (gandalfAllies.contains(entity)){
                    break;
                }
                Location entityLoc = entity.getLocation();
                Vector direction = entityLoc.toVector().subtract(player.getLocation().toVector()).normalize();
                direction.setY(1);
                entity.setVelocity(direction.multiply(2));
            }
        }
    }

    public void plotSphere(Location center, double radius, Particle particle, int numPoints) {
        double theta, phi;

        for (int i = 0; i < numPoints; i++) {
            for (int j = 0; j < numPoints; j++) {
                theta = Math.PI * i / (numPoints - 1);
                phi = 2 * Math.PI * j / (numPoints - 1);

                double x = radius * Math.sin(theta) * Math.cos(phi);
                double z = radius * Math.sin(theta) * Math.sin(phi);
                double y = radius * Math.cos(theta);

                Location particleLoc = center.clone().add(x, y, z);
                Bukkit.getScheduler().runTaskLater(main, () -> {
                    center.getWorld().spawnParticle(particle, particleLoc, 0);
                }, i);

            }
        }
    }
    private boolean isOnCooldown(Player player) {
        if (cooldowns.containsKey(player.getUniqueId())) {
            long currentTime = System.currentTimeMillis();
            long lastUseTime = cooldowns.get(player.getUniqueId());
            return (currentTime - lastUseTime) < cooldownDuration;
        }
        return false;
    }
    private void setCooldown(Player player) {
        isHalf(player);
        cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
    }

    private void displayCooldownTime(Player player) {
        long currentTime = System.currentTimeMillis();
        long lastUseTime = cooldowns.get(player.getUniqueId());
        long remainingTimeMillis = cooldownDuration - (currentTime - lastUseTime);

        int remainingSeconds = (int) (remainingTimeMillis / 1000);
        player.sendMessage(Colorize.format("&7Smoke Rings of Gandalf is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
