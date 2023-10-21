package me.purplefishh.dipcraft.superbet.resorce;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public enum BettingColors {
    BLACK(ChatColor.BLACK + "Black", ItemsCollection.getInstance().black, ConfigCollection.getInstance().multiply_black),
    RED(ChatColor.RED + "Red", ItemsCollection.getInstance().red, ConfigCollection.getInstance().multiply_red),
    GREEN(ChatColor.GREEN + "Green", ItemsCollection.getInstance().green, ConfigCollection.getInstance().multiply_green);

    @Getter
    private final String name;
    @Getter
    private final ItemStack item;
    @Getter
    private final double multiply;

    BettingColors(String name, ItemStack item, double multiply) {
        this.name = name;
        this.item = item;
        this.multiply = multiply;
    }

}
