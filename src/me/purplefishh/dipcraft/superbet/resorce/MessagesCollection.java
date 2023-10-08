package me.purplefishh.dipcraft.superbet.resorce;

import me.purplefishh.dipcraft.superbet.ConfigHelper;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class MessagesCollection {

    /**
     * The config file for messages saved in class for easier access
     */
    private final FileConfiguration msgConfig = ConfigHelper.getInstance().getConfMessages();

    private static MessagesCollection instance = null;

    private MessagesCollection() {
        loadConfig();
    }

    public static MessagesCollection getInstance() {
        if (instance == null)
            instance = new MessagesCollection();
        return instance;
    }

    /**
     * Will convert the color code from config to Minecraft colors
     * @param s the message
     * @return the message with converted color
     */
    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    /**
     * Message from config
     */
    public String water_mark,
            permission,
            offline_player,
            send_item,
            remove_item,
            get_item,
            already_betted,
            started_game,
            no_money_bet,
            make_less_zero,
            lose,
            start,
            get_another_item;

    /**
     * Strings for the help menu
     */
    public String
            helpHeader,
            helpBet,
            helpBetInventory,
            helpBetGive,
            helpBetRemove,
            helpBetReload,
            helpCommand;

    /**
     * Message form config with keys that need to be replaced
     */
    private String
            money_selectRaw,
            winRaw,
            start_in_timeRaw;

    /**
     * Load the messages form the config in every variable.
     * It can be used also for reloading the config
     */
    public void loadConfig() {
        water_mark = color(msgConfig.getString("water_mark"));

        permission = insertWaterMark(color(msgConfig.getString("permission")));
        offline_player = insertWaterMark(color(msgConfig.getString("offline_player")));
        send_item = insertWaterMark(color(msgConfig.getString("send_item")));
        remove_item = insertWaterMark(color(msgConfig.getString("remove_item")));
        get_item = insertWaterMark(color(msgConfig.getString("get_item")));
        already_betted = insertWaterMark(color(msgConfig.getString("already_betted")));
        started_game = insertWaterMark(color(msgConfig.getString("started_game")));
        no_money_bet = insertWaterMark(color(msgConfig.getString("no_money_bet")));
        make_less_zero = insertWaterMark(color(msgConfig.getString("make_less_zero")));
        lose = insertWaterMark(color(msgConfig.getString("lose")));
        start = insertWaterMark(color(msgConfig.getString("start")));
        get_another_item = insertWaterMark(color(msgConfig.getString("get_another_item")));

        helpHeader = color(msgConfig.getString("helpheader"));
        helpBet = color(msgConfig.getString("helpbet"));
        helpBetInventory = color(msgConfig.getString("helpbetinventory"));
        helpBetGive = color(msgConfig.getString("helpbetgive"));
        helpBetRemove = color(msgConfig.getString("helpbetremove"));
        helpBetReload = color(msgConfig.getString("helpbetreload"));
        helpCommand = color(msgConfig.getString("helpcomm"));

        money_selectRaw = insertWaterMark(color(msgConfig.getString("money_select")));
        winRaw = insertWaterMark(color(msgConfig.getString("win")));
        start_in_timeRaw = insertWaterMark(color(msgConfig.getString("start_in_time")));
    }

    /**
     * Insert the plugin Water-Mark in front of a String
     */
    private String insertWaterMark(String s) {
        return water_mark + s;
    }

    /**
     * The message for money selecting
     * @param string the amount of money selected that will replace %sum% key
     * @return the message after replacement
     */
    public String money_select(String string) {
        return money_selectRaw.replaceAll("%sum%", string);
    }
    /**
     * The message for winning a game
     * @param string the amount of money won that will replace %sum% key
     * @return the message after replacement
     */
    public String win(String string) {
        return winRaw.replaceAll("%sum%", string);
    }
    /**
     * The message of waiting for the game to start
     * @param min minutes until the start, that will replace %minutes% key
     * @param sec seconds until the start, that will replace %seconds% key
     * @return the message after replacement
     */
    public String start_in_time(int min, int sec) {
        return start_in_timeRaw.replaceAll("%minutes%", String.valueOf(min)).replaceAll("%seconds%", String.valueOf(sec));
    }
}
