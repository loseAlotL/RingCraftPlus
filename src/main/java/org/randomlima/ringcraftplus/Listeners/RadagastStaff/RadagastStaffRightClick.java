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
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 120 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)
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
        if (event.getItem() != null && event.getAction().isRightClick() && !event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.radagastStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
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
            setCooldown(player);
        } else {
            player.sendMessage(Colorize.format("&7There are no enemies in your direction or they are too far away!"));
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
        player.sendMessage(Colorize.format("&7Nature's Call is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
