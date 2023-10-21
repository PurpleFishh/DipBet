package me.purplefishh.dipcraft.superbet.configCollections;

import me.purplefishh.dipcraft.superbet.helpers.ConfigHelper;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;

import static me.purplefishh.dipcraft.superbet.helpers.TextHelper.color;

public class ConfigCollection implements DataStorageCollection {

    /**
     * The config file for messages saved in class for easier access
     */
    private FileConfiguration config;

    private static ConfigCollection instance = null;

    private ConfigCollection() {
        loadData();
    }

    public static ConfigCollection getInstance() {
        if (instance == null)
            instance = new ConfigCollection();
        return instance;
    }

    /**
     * Parameters
     */
    public int time,
            multiply_black,
            multiply_red,
            multiply_green;
    /**
     * The betting amounts available <br>
     * An array of 7 values
     */
    public int[] bettingAmounts = new int[7];

    /**
     * Conditions
     */
    public boolean separate_roulette,
            delete_after_bet,
            sound_effects,
            fireworks;

    /**
     * Inventory names
     */
    public String main_inv_name,
            bet_inv_name,
            increase_amount_color,
            decrease_amount_color,
            last_colors_inv_name;

    /**
     * A collection with the name of all inventories
     */
    public ArrayList<String> inventoriesName = new ArrayList<>();

    /**
     * Load the messages form the config in every variable.
     * It can be used also for reloading the config
     */
    public void loadData() {
        config = ConfigHelper.getInstance().getConfig();

        separate_roulette = config.getBoolean("separate_roulette");
        delete_after_bet = config.getBoolean("delete_after_bet");
        sound_effects = config.getBoolean("sound_effects");
        fireworks = config.getBoolean("fireworks");

        time = config.getInt("time");
        multiply_black = config.getInt("multiply_black");
        multiply_red = config.getInt("multiply_red");
        multiply_green = config.getInt("multiply_green");

        main_inv_name = color(config.getString("main_inv_name"));
        bet_inv_name = color(config.getString("bet_inv_name"));
        last_colors_inv_name = color(config.getString("last_colors_inv_name"));
        increase_amount_color = config.getString("increase_amount_color");
        decrease_amount_color = config.getString("decrease_amount_color");

        for (int i = 0; i < 7; ++i)
            bettingAmounts[i] = getBettingAmountByIndex(i + 1);

        inventoriesName.add(main_inv_name);
        inventoriesName.add(bet_inv_name);
        inventoriesName.add(last_colors_inv_name);
    }

    /**
     * Get the betting amount available
     *
     * @param i betting amount intex
     * @return the wanted amount
     */
    private int getBettingAmountByIndex(int i) {
        return config.getInt("bet" + i);
    }
}
