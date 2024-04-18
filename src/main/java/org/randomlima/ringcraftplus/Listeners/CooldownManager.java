package org.randomlima.ringcraftplus.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.randomlima.ringcraftplus.Colorize;
import org.randomlima.ringcraftplus.RingCraftPlus;

import java.util.HashMap;
import java.util.UUID;

public class CooldownManager extends BukkitRunnable {
    private RingCraftPlus plugin;
    private BukkitRunnable runnable;
    private double coolDownSeconds;
    private HashMap<UUID, Double> playerList = new HashMap<>();
    public CooldownManager(RingCraftPlus plugin, double seconds){
        this.plugin = plugin;
        this.coolDownSeconds = seconds;
        runTaskTimer(plugin, 0L, 5L); //the run function will execute every 1/4 second.
    }

    public void setCooldown(UUID uuid){
        playerList.put(uuid, coolDownSeconds);
    }
    public double getCooldownSeconds(UUID uuid){
        return playerList.get(uuid);
    }
    public boolean isOnCooldown(UUID uuid){
        if(!playerList.containsKey(uuid))return false; //if the player isn't in the list, then they are not on cooldown.
        return playerList.get(uuid) >= 0;
    }
    public void removePlayer(UUID uuid){
        playerList.remove(uuid);
    }
    public void displayCooldownTime(UUID uuid) {
        Player player = Bukkit.getPlayer(uuid);
        player.sendMessage(Colorize.format("&7This item is on cooldown! Use again in: " + getCooldownSeconds(uuid) + " seconds"));
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT,1, 1);
    }
    public void killTask(){
        cancel(); //stops the timer from running.
    }
    @Override
    public void run() {
        for(UUID uuid : playerList.keySet()){
            //Only 1/4 of a second should be subtracted because the task runs 4 times a second.
            //The task runs 4 times a second to reduce the timer error to 1/4 a second.
            if(playerList.get(uuid) > 0) playerList.replace(uuid, playerList.get(uuid)-0.25);
        }
    }
}
