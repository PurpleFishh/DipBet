package me.purplefishh.dipcraft.superbet.configCollections;

import me.purplefishh.dipcraft.superbet.helpers.ConfigHelper;
import me.purplefishh.dipcraft.superbet.utils.ItemsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ItemsCollection implements DataStorageCollection {

    private static ItemsCollection instance = null;

    private ItemsCollection() {
        loadData();
    }

    public static ItemsCollection getInstance() {
        if (instance == null)
            instance = new ItemsCollection();
        return instance;
    }

    /**
     * Loaded items
     */
    public ItemStack betting,
            exit,
            red,
            green,
            black,
            red_button,
            green_button,
            black_button,
            status_no_bet,
            status_timer,
            status_running,
            last_colors,
            main_bg,
            line_bg,
            put,
            cancel,
            increase,
            decrease;


    /**
     * Loads the items from config
     */
    public void loadData() {
        ConfigurationSection itemsSection = ConfigHelper.getInstance().getConfig().getConfigurationSection("items");
        Objects.requireNonNull(itemsSection, "The config section 'items' dose not exist!");

        betting = ItemsConstructor.createItem(itemsSection.getConfigurationSection("betting"));
        exit = ItemsConstructor.createItem(itemsSection.getConfigurationSection("exit"));
        red = ItemsConstructor.createItem(itemsSection.getConfigurationSection("red"));
        green = ItemsConstructor.createItem(itemsSection.getConfigurationSection("green"));
        black = ItemsConstructor.createItem(itemsSection.getConfigurationSection("black"));
        red_button = ItemsConstructor.createItem(itemsSection.getConfigurationSection("red_button"));
        green_button = ItemsConstructor.createItem(itemsSection.getConfigurationSection("green_button"));
        black_button = ItemsConstructor.createItem(itemsSection.getConfigurationSection("black_button"));
        status_no_bet = ItemsConstructor.createItem(itemsSection.getConfigurationSection("status_no_bet"));
        status_timer = ItemsConstructor.createItem(itemsSection.getConfigurationSection("status_timer"));
        status_running = ItemsConstructor.createItem(itemsSection.getConfigurationSection("status_running"));
        last_colors = ItemsConstructor.createItem(itemsSection.getConfigurationSection("last_colors"));
        main_bg = ItemsConstructor.createItem(itemsSection.getConfigurationSection("main_bg"));
        line_bg = ItemsConstructor.createItem(itemsSection.getConfigurationSection("line_bg"));
        put = ItemsConstructor.createItem(itemsSection.getConfigurationSection("put"));
        cancel = ItemsConstructor.createItem(itemsSection.getConfigurationSection("cancel"));
        increase = ItemsConstructor.createItem(itemsSection.getConfigurationSection("increase"));
        decrease = ItemsConstructor.createItem(itemsSection.getConfigurationSection("decrease"));
    }


}
