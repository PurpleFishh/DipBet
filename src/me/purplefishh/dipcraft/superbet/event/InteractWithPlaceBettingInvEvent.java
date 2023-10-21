package me.purplefishh.dipcraft.superbet.event;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InteractWithPlaceBettingInvEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(ConfigCollection.getInstance().bet_inv_name) || event.getCurrentItem() == null)
            return;
        ItemStack item = event.getCurrentItem();
        Player p = (Player) event.getWhoClicked();

        if(event.getCurrentItem().equals(ItemsCollection.getInstance().put))
        {
            Main.getInstance().getGame(p).placeBet(p);
            event.setCancelled(true);
            return;
        }

        double amount = 0;
        if (event.getSlot() >= 10 && event.getSlot() <= 16)
            amount = ConfigCollection.getInstance().bettingAmounts[-1 * (event.getSlot() - 16)];
        else if (event.getSlot() >= 19 && event.getSlot() <= 25)
            amount = -ConfigCollection.getInstance().bettingAmounts[-1 * (event.getSlot() - 25)];
        else
            return;

        Main.getInstance().getGame(p).addToBetMoney(p, amount);
        event.setCancelled(true);
    }
}
