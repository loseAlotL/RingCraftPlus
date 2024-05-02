package org.randomlima.ringcraftplus.Commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;

public class AmogusBomb implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) sender;
        player.getInventory().clear();
        player.getInventory().setChestplate(CustomItems.AmogusJetWings);
        player.getInventory().setHelmet(CustomItems.AmogusJet);
        player.getInventory().setItemInOffHand(CustomItems.AmogusRocketJet);
        player.getInventory().addItem(new ItemStack(Material.RED_STAINED_GLASS_PANE));
        player.getInventory().addItem(CustomItems.AmogusJetBomb);
        player.getInventory().addItem(CustomItems.AmogusDroneMines);
        player.getInventory().addItem(CustomItems.AmogusBarrelRollLeft);
        player.getInventory().addItem(CustomItems.AmogusJetGuns);
        player.getInventory().addItem(CustomItems.AmogusBarrelRoll);
        player.getInventory().addItem(CustomItems.AmogusMissles);
        player.getInventory().addItem(CustomItems.AmogusNuke);
        player.getInventory().addItem(new ItemStack(Material.GREEN_STAINED_GLASS_PANE));
        return true;
    }
}
