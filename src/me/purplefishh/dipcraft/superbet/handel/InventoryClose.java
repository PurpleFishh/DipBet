package me.purplefishh.dipcraft.superbet.handel;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryClose implements Listener {

    @EventHandler
    public void onBettingInventoryClose(InventoryCloseEvent event) {
        if (!event.getView().getTitle().equals(ConfigCollection.getInstance().bet_inv_name))
            return;
        if (!Main.getInstance().getGame((Player) event.getPlayer()).getPlayerBet((Player) event.getPlayer()).isPlaced())
            Main.getInstance().getGame((Player) event.getPlayer()).removePlacedBet((Player) event.getPlayer());
    }
}
