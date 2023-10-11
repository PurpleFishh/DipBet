package me.purplefishh.dipcraft.superbet.helpers;

import org.bukkit.ChatColor;

public class TextHelper {

    /**
     * Will convert the color code from config to Minecraft colors
     *
     * @param s the message
     * @return the message with converted color
     */
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
