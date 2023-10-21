package me.purplefishh.dipcraft.superbet.helpers;

import com.cryptomorin.xseries.XItemStack;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import static me.purplefishh.dipcraft.superbet.helpers.TextHelper.color;

public class ItemConstructor {


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
        //ConfigurationSection temp = new YamlConfiguration().createSection("temp", itemInfo.getValues(true));
        itemInfo.set("material", itemInfo.getString("material").toUpperCase());
        if (itemInfo.isString("name"))
            itemInfo.set("name", color(itemInfo.getString("name")));
        if (itemInfo.isList("lore")) {
            itemInfo.set("lore", itemInfo.getList("lore").stream().map(line -> color((String) line)).toList());
        }
        ItemStack item = XItemStack.deserialize(itemInfo);
        if (itemInfo.isInt("amount"))
            item.setAmount(itemInfo.getInt("amount"));
        return item;
    }
}
