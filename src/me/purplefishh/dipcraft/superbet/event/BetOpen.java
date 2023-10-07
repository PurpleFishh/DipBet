package me.purplefishh.dipcraft.superbet.event;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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

	// Event for opening main inventory for betting
	@EventHandler
	void OpenBettingInv(PlayerInteractEvent e) {
		if (e.getItem() != null && e.getItem().equals(Resorce.BetItem())) {
			if (Resorce.separate_roulette()) {
				if (invs.containsKey(e.getPlayer()))
					e.getPlayer().openInventory(invs.get(e.getPlayer()));
				else {
					Inventory inv = Bukkit.createInventory(null, 45, Repleace.repleace(Resorce.main_inv_name()));
					ColorUtils.colorinv(inv);
					e.getPlayer().openInventory(inv);
					invs.put(e.getPlayer(), inv);
				}
			} else
				e.getPlayer().openInventory(Main.inv);
			e.setCancelled(true);
		}
	}

	// The option to delete the bet item from inv after closing the GUI
	@EventHandler
	public void DeleteOnClose(InventoryCloseEvent e) {
		if (e.getView().getTitle().equals(Repleace.repleace(Resorce.main_inv_name()))) {
			if (Resorce.delete_after_bet())
				e.getPlayer().getInventory().remove(Resorce.BetItem());
		}
	}

	// Interaction in the betting inv
	@EventHandler
	void ClickinBettingInv(InventoryClickEvent e) {
		if (e.getView().getTitle().equals(Repleace.repleace(Resorce.main_inv_name())) && e.getCurrentItem() != null) {
			ItemStack item = e.getCurrentItem();
			Player p = (Player) e.getWhoClicked();
			if (item.equals(Resorce.back()))
				p.closeInventory();
			if (item.equals(Resorce.last_colors())) {
				p.openInventory(LastColorsInventory.last_colors_inventory(p));
			}

			// if ((item.getType().equals(Resorce.red_button(0).getType())
			// || item.getType().equals(Resorce.green_button(0).getType())
			// || item.getType().equals(Resorce.black_button(0).getType()))
			// This math means the positions of putting an bet items(red, green, black)
			if (e.getSlot() == 4 * 9 + 3 - 1 || e.getSlot() == 4 * 9 + 5 - 1 || e.getSlot() == 4 * 9 + 7 - 1) {
				if (Resorce.pariubani.containsKey(p) && Resorce.pariubani.get(p) != 0
						&& Resorce.pariu.get(p) != code(item.getType()))
					p.sendMessage(Resorce.already_betted());
				else {
					if (Resorce.sound_effects())
						p.playSound(p, Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 100, 1);
					if (Resorce.separate_roulette()) {
						if (Rotire.starts.containsKey(p) && Rotire.starts.get(p) == true)
							p.sendMessage(Resorce.started_game());
						else {
							Resorce.pariu.put(p, code(item.getType()));
							p.openInventory(BaniInv.invBani());
						}
					} else {
						if (Rotire.start == true)
							p.sendMessage(Resorce.started_game());
						else {
							Resorce.pariu.put(p, code(item.getType()));
							p.openInventory(BaniInv.invBani());
						}
					}
				}

			}
			e.setCancelled(true);
		}
	}

	int code(Material data) {
		if (Resorce.black_button(0).getType() == data)
			return 1;
		if (Resorce.red_button(0).getType() == data)
			return 2;
		if (Resorce.green_button(0).getType() == data)
			return 3;
		return -1;
	}
}
