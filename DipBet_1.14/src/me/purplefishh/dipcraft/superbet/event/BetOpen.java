package me.purplefishh.dipcraft.superbet.event;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.ColorUtils;
import me.purplefishh.dipcraft.superbet.resorce.Repleace;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import me.purplefishh.dipcraft.superbet.utils.BaniInv;
import me.purplefishh.dipcraft.superbet.utils.LastColorsInventory;
import me.purplefishh.dipcraft.superbet.utils.Rotire;

public class BetOpen implements Listener {

	public static HashMap<Player, Inventory> invs = new HashMap<Player, Inventory>();

	@EventHandler
	void OpenInvBET(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().getType() != Material.AIR)
			if (e.getItem().equals(Resorce.BetItem())) {
				if (!e.getPlayer().getOpenInventory().getTitle().equals(Repleace.repleace(Resorce.bet_inv_name()))
						&& !e.getPlayer().getOpenInventory().getTitle()
								.equals(Repleace.repleace(Resorce.main_inv_name()))
						&& !e.getPlayer().getOpenInventory().getTitle()
								.equals(Repleace.repleace(Resorce.last_colors_inv_name()))) {

					if (Resorce.separate_roulette()) {
						if (invs.containsKey(e.getPlayer()))
							e.getPlayer().openInventory(invs.get(e.getPlayer()));
						else {
							Inventory inv = Bukkit.createInventory(null, 45,
									Repleace.repleace(Resorce.main_inv_name()));
							ColorUtils.colorinv(inv);
							e.getPlayer().openInventory(inv);
							invs.put(e.getPlayer(), inv);
						}
					} else {
						e.getPlayer().openInventory(Main.inv);
					}
				}
				e.setCancelled(true);
			}

	}

	@EventHandler
	public void DeleteOnClose(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals(Repleace.repleace(Resorce.main_inv_name()))) {
			// if (Resorce.separate_roulette())
			// invs.remove(e.getPlayer());
			if (Resorce.delete_after_bet())
				e.getPlayer().getInventory().remove(Resorce.BetItem());
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	void Rot(InventoryClickEvent e) {
		if (e.getView().getTitle().equals(Repleace.repleace(Resorce.main_inv_name()))) {
			if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR) {
				if (e.getCurrentItem().equals(Resorce.main_bg()) || e.getCurrentItem().equals(Resorce.line_bg()))
					e.setCancelled(true);
				if (e.getCurrentItem().equals(Resorce.back()))
					e.getWhoClicked().closeInventory();
				if (e.getCurrentItem().equals(Resorce.last_colors())) {
					e.getWhoClicked()
							.openInventory(LastColorsInventory.last_colors_inventory((Player) e.getWhoClicked()));
				}
				ItemStack item = e.getCurrentItem();
				if ((item.getType().equals(Resorce.red_button(0).getType())
						|| item.getType().equals(Resorce.green_button(0).getType())
						|| item.getType().equals(Resorce.black_button(0).getType()))
						&& (e.getSlot() == 4 * 9 + 3 - 1 || e.getSlot() == 4 * 9 + 5 - 1
								|| e.getSlot() == 4 * 9 + 7 - 1)) {
					if (Resorce.separate_roulette()) {
						if (Rotire.starts.containsKey(e.getWhoClicked())
								&& Rotire.starts.get(e.getWhoClicked()) == true)
							e.getWhoClicked().sendMessage(Resorce.started_game());
						else {
							Resorce.pariu.put((Player) e.getWhoClicked(), code(e.getCurrentItem().getData().getData()));
							e.getWhoClicked().openInventory(BaniInv.invBani());
						}
					} else {
						if (Rotire.start == true) {
							e.getWhoClicked().sendMessage(Resorce.started_game());
						} else {
							Resorce.pariu.put((Player) e.getWhoClicked(), code(e.getCurrentItem().getData().getData()));
							e.getWhoClicked().openInventory(BaniInv.invBani());
						}
					}
				}
				e.setCancelled(true);
			}
		}
	}

	int code(int data) {
		switch (data) {
		case 15:
			return 1;
		case 14:
			return 2;
		case 13:
			return 3;
		}
		return -1;
	}
}
