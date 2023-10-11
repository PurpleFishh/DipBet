package me.purplefishh.dipcraft.superbet.helpers;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigHelper {
    private static ConfigHelper instance = null;
    private ConfigHelper(){
        mainConfigConstructor();
        messagesConfigConstructor();
    }
    public static ConfigHelper getInstance(){
        if(instance == null)
            instance = new ConfigHelper();
        return instance;
    }

    /**
     * The file for legacy config
     */
    private File fileLegacy;
    /**
     * The main config
     */
    @Getter
    private FileConfiguration config;
    /**
     * Message config file
     */
    private File fileMessages;
    /**
     * The message config
     */
    @Getter
    private FileConfiguration confMessages;

    /**
     * Construct the main config
     * Choosing the version of config, normal or legacy config, depending on the Minecraft Server Version
     * The legacy config has different setting that will make it work for older versions
     */
    private void mainConfigConstructor() {
        if (!Main.getInstance().isLegacy()) {
            {
                Main.getInstance().saveDefaultConfig();
                config = Main.getInstance().getConfig();
            }
        } else {
            fileLegacy = new File(Main.getInstance().getDataFolder(), "config_legacy.yml");
            if (!fileLegacy.exists())
                Main.getInstance().saveResource("config_legacy.yml", false);
            config = YamlConfiguration.loadConfiguration(fileLegacy);
        }
    }

    /**
     * Creating the custom config for messages
     */
    private void messagesConfigConstructor() {
        fileMessages = new File(Main.getInstance().getDataFolder(), "messages.yml");
        if (!fileMessages.exists())
            Main.getInstance().saveResource("messages.yml", false);
        confMessages = YamlConfiguration.loadConfiguration(fileMessages);
    }

    /**
     * Reloading the configs
     */
    public void reloadConfig() {
        if (!Main.getInstance().isLegacy()) {
            File file = new File(Main.getInstance().getDataFolder().getAbsolutePath() + "/config.yml");
            config = YamlConfiguration.loadConfiguration(file);
        } else {
            try {
                config.load(fileLegacy);
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
        try {
            confMessages.load(fileMessages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}