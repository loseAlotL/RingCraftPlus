package org.randomlima.ringcraftplus.Listeners.SarumanStaff;

import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.WizardHat.WizardHatArmor;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SarumanStaffShift implements Listener {
    private final RingCraftPlus main;
    public SarumanStaffShift(RingCraftPlus main) {
        this.main = main;
    }

    private HashMap<UUID, Long> cooldowns = new HashMap<>();
    private long cooldownDuration = 60 * 1000; // Cooldown duration in milliseconds (e.g., 60 seconds)
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
        if (event.getItem() != null && event.getAction().isRightClick() && event.getPlayer().isSneaking() && event.getItem().getItemMeta().equals(CustomItems.sarumanStaff.getItemMeta())){
            if (isOnCooldown(player)) {
                event.setCancelled(true);
                displayCooldownTime(player);
                return;
            }
            event.setCancelled(true);
            setCooldown(player);
            for(Entity entity : player.getNearbyEntities(10,10,10)){
                if(entity instanceof LivingEntity){
                    ((LivingEntity) entity).addPotionEffect(PotionEffectType.GLOWING.createEffect(300, 1));
                    ((LivingEntity) entity).addPotionEffect(PotionEffectType.BLINDNESS.createEffect(300, 2));
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
        player.sendMessage(Colorize.format("&7Illumination is on cooldown! Use again in: " + remainingSeconds + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
}
