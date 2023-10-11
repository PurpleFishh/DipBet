package me.purplefishh.dipcraft.superbet.utils;

import com.cryptomorin.xseries.XItemStack;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class ItemsConstructor {


    /**
     * Constructs an ItemStack by deserializing the config section of the item
     *
     * @param itemInfo the config section with the item info
     * @return the constructed ItemStack
     */
    public static ItemStack createItem(ConfigurationSection itemInfo) {
        if (itemInfo == null)
            return null;
        if (!itemInfo.isString("material"))
            return null;
        itemInfo.set("material", itemInfo.getString("material").toUpperCase());

        ItemStack item = XItemStack.deserialize(itemInfo);
        if (itemInfo.isInt("amount"))
            item.setAmount(itemInfo.getInt("amount"));
        return item;
    }
}
