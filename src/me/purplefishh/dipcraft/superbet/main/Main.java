package me.purplefishh.dipcraft.superbet.main;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.purplefishh.dipcraft.superbet.command.BetIntemGive;
import me.purplefishh.dipcraft.superbet.event.BetEvent;
import me.purplefishh.dipcraft.superbet.event.BetOpen;
import me.purplefishh.dipcraft.superbet.event.ProtectionEvent;
import me.purplefishh.dipcraft.superbet.resorce.ColorUtils;
import me.purplefishh.dipcraft.superbet.resorce.Repleace;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import me.purplefishh.dipcraft.superbet.utils.LastColorsInventory;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin {
	
	public static Plugin pl = null;
	public static Inventory inv;
	public static String version = Bukkit.getVersion().split("\\(MC: ")[1].split("\\)")[0];

	static File filelegacy;
	static FileConfiguration config;
	static File filemessages;
	static FileConfiguration confmessages;

	private static Economy econ = null;

	@Override
	public void onEnable() {
		System.out.println("[SuperBet] Enable!");
		if (!setupEconomy()) {
			System.out.println("Plugin disabled beacause you don't have Vault on your server");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}

		pl = this;

		configs();
		messagesConfig();
		commands();
		events();
		if (Resorce.separate_roulette() == false) {
			inv = Bukkit.createInventory(null, 45, Repleace.repleace(Resorce.main_inv_name()));
			ColorUtils.colorinv(inv);

		}
	}

	public static boolean legacy() {
		if (version.contains("1.19"))
			return false;
		else if (version.contains("1.18"))
			return false;
		else if (version.contains("1.17"))
			return false;
		else if (version.contains("1.16"))
			return false;
		else if (version.contains("1.15"))
			return false;
		else if (version.contains("1.14"))
			return false;
		else if (version.contains("1.13"))
			return false;

		return true;
	}

	private boolean setupEconomy() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}

	@Override
	public void onDisable() {
		System.out.println("[SuperBet] Disble!");
	}

	public static Plugin plugin() {
		return pl;
	}

	public static FileConfiguration config() {
			return config;
	}

	public static FileConfiguration msg() {
		return confmessages;
	}

	private void configs() {
		if (!legacy()) {
			{
				saveDefaultConfig();
				config = getConfig();
			}
		} else {
			filelegacy = new File(getDataFolder(), "config_legacy.yml");
			if (!filelegacy.exists())
				saveResource("config_legacy.yml", false);
			config = YamlConfiguration.loadConfiguration(filelegacy);
		}
	}

	public static void reloadConf() {
		if (legacy() == false) {
			File file = new File(pl.getDataFolder().getAbsolutePath() + "/config.yml");
			config = YamlConfiguration.loadConfiguration(file);
		} else {
			try {
				config.load(filelegacy);
			} catch (IOException | InvalidConfigurationException e) {
				e.printStackTrace();
			}
		}
		try {
			confmessages.load(filemessages);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

	public void messagesConfig() {
		filemessages = new File(getDataFolder(), "messages.yml");
		if (!filemessages.exists())
			saveResource("messages.yml", false);
		confmessages = YamlConfiguration.loadConfiguration(filemessages);
	}

	private void commands() {
		this.getCommand("bet").setExecutor(new BetIntemGive());
	}

	private void events() {
		PluginManager plmanager = Bukkit.getPluginManager();
		plmanager.registerEvents(new BetOpen(), this);
		plmanager.registerEvents(new BetEvent(), this);
		plmanager.registerEvents(new ProtectionEvent(), this);
		plmanager.registerEvents(new LastColorsInventory(), this);
	}

	public static Economy getEconomy() {
		return econ;
	}

}
