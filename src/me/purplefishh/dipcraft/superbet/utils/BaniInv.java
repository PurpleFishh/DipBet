package me.purplefishh.dipcraft.superbet.utils;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.resorce.Repleace;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class BaniInv {

	/*public static Inventory invBani() {
		Inventory inv = Bukkit.createInventory(null, 45, Repleace.repleace(Resorce.bet_inv_name()));
		ItemStack barieraalba = Resorce.line_bg();
		// Fundal
		for (int i = 0; i < 45; ++i)
			inv.setItem(i, barieraalba);
		for (int i = 1; i <= 7; ++i) {
			inv.setItem(9 + i, add(Resorce.bet_amount(7 - i + 1)));
		}
		for (int i = 1; i <= 7; ++i) {
			inv.setItem(18 + i, remove(Resorce.bet_amount(7 - i + 1)));
		}
		
		inv.setItem(38, Resorce.put_item());
		inv.setItem(42, Resorce.cancel_item());
		return inv;
	}

	private static ItemStack add(int b) {
		ItemStack item = Resorce.increase_item();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Resorce.increase_amount_color()) + "+" + punct(b));
		item.setItemMeta(meta);
		return item;
	}

	private static ItemStack remove(int b) {
		ItemStack item = Resorce.decrease_item();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Resorce.decrease_amount_color()) + "-" + punct(b));
		item.setItemMeta(meta);
		return item;
	}

	public static String punct(int b) {
		NumberFormat format = NumberFormat.getInstance();
        format.setGroupingUsed(true);
		return format.format(b);
		
	}*/
}
