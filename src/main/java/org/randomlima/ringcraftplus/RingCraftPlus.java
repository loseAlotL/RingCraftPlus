package org.randomlima.ringcraftplus;

import org.bukkit.plugin.java.JavaPlugin;
import org.randomlima.ringcraftplus.Commands.*;
import org.randomlima.ringcraftplus.CustomItems.CustomItems;
import org.randomlima.ringcraftplus.Listeners.GaladrielPhial.GaladrielPhialRightClick;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffDamage;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffLeftClick;
import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffRightClick;
//import org.randomlima.ringcraftplus.Listeners.GandalfStaff.GandalfStaffShift;
import org.randomlima.ringcraftplus.Listeners.GondorHorn.GondorHornRightClick;
import org.randomlima.ringcraftplus.Listeners.Palantir.PalantirRightClick;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffLeftClick;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffRightClick;
import org.randomlima.ringcraftplus.Listeners.SarumanStaff.SarumanStaffShift;

public final class RingCraftPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        CustomItems.init();
        this.getCommand("palantir").setExecutor(new Palantir());
        this.getCommand("gondorhorn").setExecutor(new GondorHorn());
        this.getCommand("gandalfstaff").setExecutor(new GandalfStaff());
        this.getCommand("sarumanstaff").setExecutor(new SarumanStaff());
        this.getCommand("galadrielphial").setExecutor(new GaladrielPhial());
        getServer().getPluginManager().registerEvents(new GondorHornRightClick(), this);
        getServer().getPluginManager().registerEvents(new PalantirRightClick(this), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffRightClick(this), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new GandalfStaffDamage(), this);
        getServer().getPluginManager().registerEvents(new SarumanStaffRightClick(this), this);
        getServer().getPluginManager().registerEvents(new SarumanStaffLeftClick(this), this);
        getServer().getPluginManager().registerEvents(new SarumanStaffShift(this), this);
        getServer().getPluginManager().registerEvents(new GaladrielPhialRightClick(this), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
