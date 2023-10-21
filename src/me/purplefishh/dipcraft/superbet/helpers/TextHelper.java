package me.purplefishh.dipcraft.superbet.helpers;

import me.purplefishh.dipcraft.superbet.utils.ReplaceTags;
import org.bukkit.ChatColor;

import java.text.NumberFormat;

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

    /**
     * Place dots in a number(10000 -> '10.000')
     * @param number number to be worked on
     * @return the resulted string
     */
    public static String numberDot(double number) {
        NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
        return format.format(number);

    }

    /**
     * Give a message with Minutes and Seconds tags that will be replaced and give the time in seconds <br>
     * It will convert the seconds in seconds and minutes and replace the placeholders in the String
     * @param message string with placeholders for minutes and seconds that will be replaced
     * @param time time in seconds
     * @return the resulted String
     */
    public static String replaceConvertTime(String message, int time)
    {
        int min = 0, sec = 0;
        if (time >= 60)
            min = time / 60;
        if (time % 60 != 0)
            sec = time - min * 60;
        return  message.replaceAll(ReplaceTags.SECONDS.getTag(), String.valueOf(sec))
                .replaceAll(ReplaceTags.MINUTES.getTag(), String.valueOf(min));
    }
}
