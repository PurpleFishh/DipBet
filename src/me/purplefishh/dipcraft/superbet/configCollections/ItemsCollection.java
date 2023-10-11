package me.purplefishh.dipcraft.superbet.configCollections;

import me.purplefishh.dipcraft.superbet.helpers.ConfigHelper;
import me.purplefishh.dipcraft.superbet.utils.ItemConstructor;
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

        betting = ItemConstructor.createItem(itemsSection.getConfigurationSection("betting"));
        exit = ItemConstructor.createItem(itemsSection.getConfigurationSection("exit"));
        red = ItemConstructor.createItem(itemsSection.getConfigurationSection("red"));
        green = ItemConstructor.createItem(itemsSection.getConfigurationSection("green"));
        black = ItemConstructor.createItem(itemsSection.getConfigurationSection("black"));
        red_button = ItemConstructor.createItem(itemsSection.getConfigurationSection("red_button"));
        green_button = ItemConstructor.createItem(itemsSection.getConfigurationSection("green_button"));
        black_button = ItemConstructor.createItem(itemsSection.getConfigurationSection("black_button"));
        status_no_bet = ItemConstructor.createItem(itemsSection.getConfigurationSection("status_no_bet"));
        status_timer = ItemConstructor.createItem(itemsSection.getConfigurationSection("status_timer"));
        status_running = ItemConstructor.createItem(itemsSection.getConfigurationSection("status_running"));
        last_colors = ItemConstructor.createItem(itemsSection.getConfigurationSection("last_colors"));
        main_bg = ItemConstructor.createItem(itemsSection.getConfigurationSection("main_bg"));
        line_bg = ItemConstructor.createItem(itemsSection.getConfigurationSection("line_bg"));
        put = ItemConstructor.createItem(itemsSection.getConfigurationSection("put"));
        cancel = ItemConstructor.createItem(itemsSection.getConfigurationSection("cancel"));
        increase = ItemConstructor.createItem(itemsSection.getConfigurationSection("increase"));
        decrease = ItemConstructor.createItem(itemsSection.getConfigurationSection("decrease"));
    }


}
