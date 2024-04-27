package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GandalfStaffLeftClick implements Listener {

    private final RingCraftPlus main;
    public GandalfStaffLeftClick(RingCraftPlus main) {
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
    public void onLeftClick(PlayerInteractEvent event){
        //GandalfStaffDamage gandalfStaffDamage = new GandalfStaffDamage();
        Player player = event.getPlayer();
        if (event.getItem() != null && event.getAction().isLeftClick() && !event.getPlayer().isSneaking() && event.getItem().equals(CustomItems.gandalfStaff)){
            List<Player> gandalfAllies = GandalfStaffDamage.getGandalfList();
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            if (gandalfAllies == null || gandalfAllies.isEmpty()) {
                setCooldown(player);
                player.playSound(player.getLocation(), Sound.ENTITY_ARMOR_STAND_HIT, 1, 1);
                player.sendMessage(Colorize.format("&7You have not set any allies!"));
                return;
            }
            event.setCancelled(true);
            setCooldown(player);
            if(gandalfAllies.size() == 8){
                for(Player gandalfAlly : gandalfAllies){
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                    gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2));
                    gandalfAlly.playSound(gandalfAlly, Sound.ENTITY_GHAST_WARN, 1, 1);
                    for (double i = 0; i <360; i +=5){
                        Location loc = gandalfAlly.getLocation();
                        double angle = i * Math.PI / 180;
                        double x = 1.3 * Math.cos(angle);
                        double z = 1.3 * Math.sin(angle);
                        loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.getX() + x, loc.getY() + 2, loc.getZ() + z, 0);
                    }
                }
                return;
            }
            for(Player gandalfAlly : gandalfAllies){
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
                gandalfAlly.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 1, 2));
                gandalfAlly.playSound(gandalfAlly, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
                for (double i = 0; i <360; i +=5){
                    Location loc = gandalfAlly.getLocation();
                    double angle = i * Math.PI / 180;
                    double x = 1.3 * Math.cos(angle);
                    double z = 1.3 * Math.sin(angle);
                    loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc.getX() + x, loc.getY() + 2, loc.getZ() + z, 0);
                }
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
        player.sendMessage(Colorize.format("&7Strategic Wisdom is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
