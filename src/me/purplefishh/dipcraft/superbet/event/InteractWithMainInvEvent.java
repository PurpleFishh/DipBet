package me.purplefishh.dipcraft.superbet.event;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.resorce.BettingColors;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InteractWithMainInvEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ConfigCollection.getInstance().main_inv_name) || event.getCurrentItem() == null)
            return;
        ItemStack item = event.getCurrentItem();
        Player p = (Player) event.getWhoClicked();

        if (item.equals(ItemsCollection.getInstance().last_colors))
            Main.getInstance().getGame(p).getBoard(p).openLastColorInventory(p);

        // This math means the positions of putting a bet items(red, green, black)
        if (event.getSlot() == 4 * 9 + 3 - 1 || event.getSlot() == 4 * 9 + 5 - 1 || event.getSlot() == 4 * 9 + 7 - 1)
            Main.getInstance().getGame(p).openBettingMenu(p, code(event.getCurrentItem().getType()));

        event.setCancelled(true);
    }

    BettingColors code(Material data) {
        if (ItemsCollection.getInstance().red_button.getType() == data)
            return BettingColors.RED;
        if (ItemsCollection.getInstance().black_button.getType() == data)
            return BettingColors.BLACK;
        if (ItemsCollection.getInstance().green_button.getType() == data)
            return BettingColors.GREEN;
        return BettingColors.RED;
    }
}
