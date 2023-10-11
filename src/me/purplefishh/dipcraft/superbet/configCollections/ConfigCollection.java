package me.purplefishh.dipcraft.superbet.configCollections;

import me.purplefishh.dipcraft.superbet.helpers.ConfigHelper;
import org.bukkit.configuration.file.FileConfiguration;

import static me.purplefishh.dipcraft.superbet.helpers.TextHelper.color;

public class ConfigCollection implements DataStorageCollection {

    /**
     * The config file for messages saved in class for easier access
     */
    private final FileConfiguration config = ConfigHelper.getInstance().getConfig();

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
     * Load the messages form the config in every variable.
     * It can be used also for reloading the config
     */
    public void loadData() {
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
        last_colors_inv_name = color(config.getString("last_colors_name"));
        increase_amount_color = config.getString("increase_amount_color");
        decrease_amount_color = config.getString("decrease_amount_color");

        for (int i = 1; i <= 7; ++i)
            bettingAmounts[i] = getBettingAmountByIndex(i);
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
