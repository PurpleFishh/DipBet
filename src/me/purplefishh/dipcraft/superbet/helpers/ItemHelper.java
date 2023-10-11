package me.purplefishh.dipcraft.superbet.helpers;

import javafx.util.Pair;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemHelper {
    /**
     * Replace the placeholders from an item name and lore with the wanted data
     *
     * @param item           item that will be worked with
     * @param keyReplacement variable arguments of pairs, the key is the placeholder and the value is the value that will replace with
     * @return item after updating it
     */
    @SafeVarargs
    public static ItemStack replacePlaceHolders(ItemStack item, Pair<String, Object>... keyReplacement) {
        if (!item.hasItemMeta())
            return item;
        ItemMeta meta = item.getItemMeta();
        if (meta.hasDisplayName()) {
            String name = meta.getDisplayName();
            for (Pair<String, Object> pair : keyReplacement)
                name = name.replaceAll(pair.getKey(), pair.getValue().toString());
            meta.setDisplayName(name);
        }
        if (meta.hasLore()) {
            List<String> lore = meta.getLore();
            for (Pair<String, Object> pair : keyReplacement) {
                lore = lore.stream().map(line -> line.replaceAll(pair.getKey(), pair.getValue().toString())).toList();
            }
            meta.setLore(lore);
        }
        item.setItemMeta(meta);
        return item;
    }

    /**
     * Add a suffix to the name of an item
     *
     * @param item           item that will be worked with
     * @param additionToName the suffix
     * @return item after updating it
     */
    public static ItemStack linkToItemName(ItemStack item, String additionToName) {
        if (!item.hasItemMeta())
            return item;
        ItemMeta meta = item.getItemMeta();
        if (meta.hasDisplayName()) {
            meta.setDisplayName(meta.getDisplayName() + additionToName);
        }
        item.setItemMeta(meta);
        return item;
    }
}
