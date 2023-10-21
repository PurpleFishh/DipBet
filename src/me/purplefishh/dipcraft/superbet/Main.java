package me.purplefishh.dipcraft.superbet;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.command.*;
import me.purplefishh.dipcraft.superbet.command.system.CommandGroup;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import me.purplefishh.dipcraft.superbet.event.InteractWithItemEvent;
import me.purplefishh.dipcraft.superbet.event.InteractWithMainInvEvent;
import me.purplefishh.dipcraft.superbet.event.InteractWithPlaceBettingInvEvent;
import me.purplefishh.dipcraft.superbet.event.ProtectionEvent;
import me.purplefishh.dipcraft.superbet.game.GameSingle;
import me.purplefishh.dipcraft.superbet.game.IGame;
import me.purplefishh.dipcraft.superbet.handel.ExitItemPress;
import me.purplefishh.dipcraft.superbet.handel.InventoryClose;
import me.purplefishh.dipcraft.superbet.helpers.ConfigHelper;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance = null;

    private final String version = Bukkit.getVersion().split("\\(MC: ")[1].split("\\)")[0];
    @Getter
    private final boolean legacy = !(version.contains("1.20") || version.contains("1.19") || version.contains("1.18") ||
            version.contains("1.17") || version.contains("1.16") || version.contains("1.15") ||
            version.contains("1.14") || version.contains("1.13"));
    @Getter
    private Economy econ = null;
    private IGame game;
    private HashMap<Player, IGame> separatedGames;
    @Getter
    private CommandGroup commandGroup;

    @Override
    public void onEnable() {
        System.out.println("[SuperBet] Enable!");
        if (!setupEconomy()) {
            System.out.println("Plugin disabled because you don't have Vault on your server");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        instance = this;
        commands();
        events();
        if (ConfigCollection.getInstance().separate_roulette) {
            separatedGames = new HashMap<>();
        } else
            game = new GameSingle();
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
        commandGroup = new CommandGroup("bet",
                new BetGiveItemCommand(),
                new ReloadCommand(),
                new BetInventoryOpenCommand(),
                new BetRemoveItemCommand(),
                new HelpCommand());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        commandGroup.execute(sender, args);
        return true;
    }

    private void events() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ProtectionEvent(), this);
        pluginManager.registerEvents(new InteractWithItemEvent(), this);
        pluginManager.registerEvents(new InteractWithMainInvEvent(), this);
        pluginManager.registerEvents(new InteractWithPlaceBettingInvEvent(), this);
        pluginManager.registerEvents(new ExitItemPress(), this);
        pluginManager.registerEvents(new InventoryClose(), this);
    }

    public void reload() {
        boolean separatedRouletteBefore = ConfigCollection.getInstance().separate_roulette;
        ConfigHelper.getInstance().reloadConfig();
        //TODO: Fa o lista din configuri folosind DataStorageCollection si da la toate loadData prin for
        ConfigCollection.getInstance().loadData();
        MessagesCollection.getInstance().loadData();
        ItemsCollection.getInstance().loadData();

        if (separatedRouletteBefore) {
            separatedGames.values().forEach(IGame::delete);
            separatedGames.clear();
        } else
            game.delete();
        if (!ConfigCollection.getInstance().separate_roulette)
            game = new GameSingle();
    }

    public IGame getGame(Player player) {
        if (ConfigCollection.getInstance().separate_roulette) {
            if (!separatedGames.containsKey(player))
                separatedGames.put(player, new GameSingle());
            return separatedGames.get(player);
        }
        return game;
    }
}
