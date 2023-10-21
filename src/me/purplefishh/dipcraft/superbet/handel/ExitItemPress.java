package me.purplefishh.dipcraft.superbet.handel;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Objects;

public class ExitItemPress implements Listener {
    @EventHandler
    public static void handelInteraction(InventoryClickEvent event) {
        if (ConfigCollection.getInstance().inventoriesName.contains(event.getView().getTitle())) {
            if (!Objects.equals(event.getCurrentItem(), ItemsCollection.getInstance().exit))
                return;
            //if (event.getView().getTitle().equals(ConfigCollection.getInstance().bet_inv_name))
            //    Main.getInstance().getGame().removePlacedBet((Player) event.getWhoClicked());
            exitFromInventory((Player) event.getWhoClicked(), event.getView().getTitle());
        }
    }

    public static void exitFromInventory(Player player, String invName) {
        if (invName.equals(ConfigCollection.getInstance().main_inv_name)) {
            player.closeInventory();
            return;
        }
        Main.getInstance().getGame(player).openMainMenu(player);
    }

}
