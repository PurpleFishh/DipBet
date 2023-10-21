package me.purplefishh.dipcraft.superbet.event;

import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;

import java.util.Objects;

public class ProtectionEvent implements Listener {

    @EventHandler
    public void noCraft(CraftItemEvent e) {
        if (e.getView().getTopInventory().contains(ItemsCollection.getInstance().betting))
            e.setCancelled(true);
    }

    @EventHandler
    public void noInteractWithRestrictedItems(InventoryClickEvent event) {
        if (!ConfigCollection.getInstance().inventoriesName.contains(event.getView().getTitle()))
            return;
        if(ItemsCollection.getInstance().restrictedItems.contains(event.getCurrentItem()))
            event.setCancelled(true);
    }

    @EventHandler
    public void anvilProtection(InventoryClickEvent e) {
        if (!e.isCancelled())
            if (e.getWhoClicked() instanceof Player)
                if (e.getInventory() instanceof AnvilInventory) {
                    InventoryView view = e.getView();
                    int rawSlot = e.getRawSlot();
                    if (rawSlot == view.convertSlot(rawSlot))
                        if (Objects.equals(view.getItem(0), ItemsCollection.getInstance().betting) && rawSlot == 2)
                            e.setCancelled(true);
                }
    }

    @EventHandler
    public void itemDrop(PlayerDropItemEvent e) {
        if (e.getItemDrop().getItemStack().equals(ItemsCollection.getInstance().betting))
            e.getItemDrop().remove();
    }

    @EventHandler
    public void noDamageFireworks(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Firework fw)
            if (fw.hasMetadata("nodamage_fw_superbet"))
                e.setCancelled(true);
    }

}
