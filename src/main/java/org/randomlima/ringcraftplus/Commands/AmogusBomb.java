package org.randomlima.ringcraftplus.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;

public class AmogusBomb implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;
        player.getInventory().addItem(CustomItems.AmogusJetBomb);
        player.getInventory().addItem(CustomItems.AmogusJetGuns);
        player.getInventory().addItem(CustomItems.AmogusJetWings);
        player.getInventory().addItem(CustomItems.AmogusJet);
        player.getInventory().setItemInOffHand(CustomItems.AmogusRocketJet);
        return true;
    }
}
