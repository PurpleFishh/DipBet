package me.purplefishh.dipcraft.superbet.event;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractWithItemEvent implements Listener {

    @EventHandler
    public void onInteractWithItem(PlayerInteractEvent event) {
        if (event.getItem() == null || !event.getItem().equals(ItemsCollection.getInstance().betting))
            return;

        Main.getInstance().getGame().openMainMenu(event.getPlayer());
        event.setCancelled(true);
    }
}
