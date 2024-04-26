package org.randomlima.ringcraftplus.Listeners.SarumanStaff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SarumanStaffLeftClick implements Listener {
    private final RingCraftPlus main;
    public SarumanStaffLeftClick(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 45 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)
    public void isHalf(Player player){
        WizardHatArmor wizardclass = new WizardHatArmor(main);
        List<Player> wizardList = wizardclass.getWizardList();
        if (wizardList.contains(player)){
            cooldownDuration = cooldownDuration/2;
        } else {
            cooldownDuration = 60 * 1000;
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && event.getItem().getItemMeta().equals(CustomItems.sarumanStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            if (player.getTargetEntity(100) != null && (player.getTargetEntity(100) instanceof LivingEntity)) {
                LivingEntity entity = (LivingEntity) player.getTargetEntity(100);
                int numberOfFireballs = 5;
                for (int i = 0; i < numberOfFireballs; i++) {
                    final int delay = i * 20;
                    Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                        Location loc = entity.getLocation(); // Get the current location of the entity
                        Location spawnLoc = loc.clone().add(0, 50, 0);
                        LargeFireball fireball = entity.getWorld().spawn(spawnLoc, LargeFireball.class);
                        fireball.setVelocity(new Vector(0, -0.1, 0));
                        fireball.setYield(2);
                        fireball.setShooter(player);
                        if (entity.isDead()){
                            return;
                        }
                    }, delay);
                }
                setCooldown(player);
                player.playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK,1  ,1);
            } else {
                player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
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
        player.sendMessage(Colorize.format("&7Fiery Curse is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
