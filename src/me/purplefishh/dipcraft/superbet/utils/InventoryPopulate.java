package me.purplefishh.dipcraft.superbet.utils;

import javafx.util.Pair;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.helpers.ItemHelper;
import org.bukkit.inventory.Inventory;

public class InventoryPopulate {

    public static void mainInventory(Inventory inv) {
        /// Background
        for (int i = 0; i < 9; ++i)
            inv.setItem(i, ItemsCollection.getInstance().main_bg);
        for (int i = 18; i < 45; ++i)
            inv.setItem(i, ItemsCollection.getInstance().main_bg);

        for (int i = 9; i < 18; ++i) {
            inv.setItem(i, ItemsCollection.getInstance().line_bg);

        }

        /// Guide lines
        inv.setItem(0 * 9 + 5 - 1, ItemsCollection.getInstance().line_bg);
        inv.setItem(2 * 9 + 5 - 1, ItemsCollection.getInstance().line_bg);

        // Betting buttons
        inv.setItem(4 * 9 + 3 - 1, ItemHelper.replacePlaceHolders(ItemsCollection.getInstance().red_button, new Pair<>(ReplaceTags.BET_AMOUNT.getTag(), 0)));
        inv.setItem(4 * 9 + 5 - 1, ItemHelper.replacePlaceHolders(ItemsCollection.getInstance().green_button, new Pair<>(ReplaceTags.BET_AMOUNT.getTag(), 0)));
        inv.setItem(4 * 9 + 7 - 1, ItemHelper.replacePlaceHolders(ItemsCollection.getInstance().black_button, new Pair<>(ReplaceTags.BET_AMOUNT.getTag(), 0)));

        // Last Colors Button
        inv.setItem(3 * 9 + 9 - 1, ItemsCollection.getInstance().last_colors);

        // Exit Buton
        inv.setItem(44, ItemsCollection.getInstance().exit);
        inv.setItem(36, ItemsCollection.getInstance().status_no_bet);
    }
}
