package org.randomlima.ringcraftplus.Listeners.GandalfStaff;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CooldownManager;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class GandalfStaffRightClick implements Listener {
    private int taskID;
    private final RingCraftPlus main;
    private CooldownManager cooldownManager;
    private int cooldown = 60;
    public GandalfStaffRightClick(RingCraftPlus main) {
        this.main = main;
        this.cooldownManager = new CooldownManager(main, cooldown);
        cooldownManager.setCooldownMessage("&7Forest Renewal is on cooldown! Use again in: %seconds% seconds.");
    }

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
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.gandalfStaff.getItemMeta())){
            if (cooldownManager.isOnCooldown(player)) {
                event.setCancelled(true);
                cooldownManager.displayTimeLeftInteger(player);
                return;
            }
            isHalf(player);
            event.setCancelled(true);
            cooldownManager.setCooldown(player);
            particleCircle(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 150, 1));
            player.playSound(player.getLocation(), Sound.BLOCK_END_PORTAL_SPAWN,1,1);
        }
    }
    private void particleCircle(Player player) {
        final int[] ticks = {0};
        final int durationTicks = 5;
        Location loc = player.getLocation();
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {

            @Override
            public void run() {
                if (ticks[0] >= durationTicks) {
                    Bukkit.getScheduler().cancelTask(taskID); // Stop the task after 3 seconds
                    return;
                }
                for (double i = 0; i <360; i +=90){

                    double angle = i * Math.PI / 180;
                    double x = 2 * Math.cos(angle);
                    double z = 2 * Math.sin(angle);
                    loc.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, loc.getX() + x, loc.getY() + 1, loc.getZ() + z, 0,0,0,0,0.01);
                }
                ticks[0]++;
            }
        }, 0L, 10L); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }
}
