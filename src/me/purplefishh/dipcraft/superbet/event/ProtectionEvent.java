package me.purplefishh.dipcraft.superbet.event;

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

import me.purplefishh.dipcraft.superbet.resorce.Resorce;

public class ProtectionEvent implements Listener {

	@EventHandler
	public void noCraft(CraftItemEvent e) {
		if (e.getView().getTopInventory().contains(Resorce.BetItem())) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (!e.isCancelled())
			if (e.getWhoClicked() instanceof Player)
				if (e.getInventory() instanceof AnvilInventory) {
					InventoryView view = e.getView();
					int rawSlot = e.getRawSlot();
					if (rawSlot == view.convertSlot(rawSlot)) {
						if (view.getItem(0).equals(Resorce.BetItem()) && rawSlot == 2)
							e.setCancelled(true);
					}
				}

	}

	@EventHandler
	public void itemDrop(PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().equals(Resorce.BetItem()))
			e.getItemDrop().remove();
	}
	
	@EventHandler
	public void nodamagefireworks(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Firework) {
		    Firework fw = (Firework) e.getDamager();
		    if (fw.hasMetadata("nodamage_fw_superbet")) {
		        e.setCancelled(true);
		    }
		}
	}

}
