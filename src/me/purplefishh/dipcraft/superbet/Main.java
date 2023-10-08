package me.purplefishh.dipcraft.superbet;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.command.BetIntemGive;
import me.purplefishh.dipcraft.superbet.event.BetEvent;
import me.purplefishh.dipcraft.superbet.event.BetOpen;
import me.purplefishh.dipcraft.superbet.event.ProtectionEvent;
import me.purplefishh.dipcraft.superbet.resorce.ColorUtils;
import me.purplefishh.dipcraft.superbet.resorce.Repleace;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import me.purplefishh.dipcraft.superbet.utils.LastColorsInventory;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance = null;
    private Main() {
    }
    public static Main getInstance() {
        if (instance == null)
            instance = new Main();
        return instance;
    }

    private final String version = Bukkit.getVersion().split("\\(MC: ")[1].split("\\)")[0];
    @Getter
    private final boolean legacy = !(version.contains("1.20") || version.contains("1.19") || version.contains("1.18") ||
            version.contains("1.17") || version.contains("1.16") || version.contains("1.15") ||
            version.contains("1.14") || version.contains("1.13"));
    @Getter
    private Economy econ = null;

    @Override
    public void onEnable() {
        System.out.println("[SuperBet] Enable!");
        if (!setupEconomy()) {
            System.out.println("Plugin disabled because you don't have Vault on your server");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        commands();
        events();
        if (!Resorce.separate_roulette()) {
            inv = Bukkit.createInventory(null, 45, Repleace.repleace(Resorce.main_inv_name()));
            ColorUtils.colorinv(inv);
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    @Override
    public void onDisable() {
        System.out.println("[SuperBet] Disble!");
    }

    private void commands() {
        this.getCommand("bet").setExecutor(new BetIntemGive());
    }

    private void events() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new BetOpen(), this);
        pluginManager.registerEvents(new BetEvent(), this);
        pluginManager.registerEvents(new ProtectionEvent(), this);
        pluginManager.registerEvents(new LastColorsInventory(), this);
    }
}
