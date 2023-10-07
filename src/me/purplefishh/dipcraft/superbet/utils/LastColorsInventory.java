package me.purplefishh.dipcraft.superbet.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import me.purplefishh.dipcraft.superbet.event.BetOpen;
import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.Repleace;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;

public class LastColorsInventory implements Listener {

	public static Inventory last_colors_inventory(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, Resorce.last_colors_inv_name());
		for (int i = 0; i < 45; ++i)
			inv.setItem(i, Resorce.main_bg());
		for (int i = 9; i < 18; ++i)
			inv.setItem(i, Resorce.line_bg());
		if (Resorce.separate_roulette()) {
			for (int i = 0; i < 9; ++i)
				if (i <= Rotire.last_separate.get(p).size() - 1)
					inv.setItem(i + 1 * 9, Rotire.it(Rotire.last_separate.get(p).get(i)));
		}
		if (!Rotire.last.isEmpty())
			for (int i = 0; i < 9; ++i)
				if (i <= Rotire.last.size() - 1)
					inv.setItem(i + 1 * 9, Rotire.it(Rotire.last.get(i)));
		inv.setItem(44, Resorce.cancel_item());

		return inv;
	}

	@EventHandler
	public void inv_events(InventoryClickEvent e) {
		if (e.getView().getTitle().equals(Repleace.repleace(Resorce.last_colors_inv_name()))
				&& e.getCurrentItem() != null) {
			if (e.getCurrentItem().equals(Resorce.cancel_item())) {
				if (Resorce.separate_roulette())
					e.getWhoClicked().openInventory(BetOpen.invs.get(e.getWhoClicked()));
				else
					e.getWhoClicked().openInventory(Main.inv);
			}
			e.setCancelled(true);
		}
	}

}
