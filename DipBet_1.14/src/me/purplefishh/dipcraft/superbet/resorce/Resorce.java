package me.purplefishh.dipcraft.superbet.resorce;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.utils.BaniInv;

public class Resorce {

	// Lista Pariu
	public static HashMap<Player, Integer> pariubani = new HashMap<>();
	public static HashMap<Player, Integer> pariu = new HashMap<>();

	// Config
	private static FileConfiguration config() {
		return Main.config();
	}

	private static FileConfiguration msg() {
		return Main.msg();
	}

	// Messages
	public static String color(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}

	public static String wmput(String s) {
		return water_mark() + " " + s;
	}

	public static String water_mark() {
		return color(msg().getString("water_mark"));
	}

	public static String permission() {
		return wmput(color(msg().getString("permission")));
	}

	public static String offline_player() {
		return wmput(color(msg().getString("offline_player")));
	}

	public static String send_item() {
		return wmput(color(msg().getString("send_item")));
	}

	public static String remove_item() {
		return wmput(color(msg().getString("remove_item")));
	}

	public static String get_item() {
		return wmput(color(msg().getString("get_item")));
	}

	public static String already_betted() {
		return wmput(color(msg().getString("already_betted")));
	}
	
	public static String started_game() {
		return wmput(color(msg().getString("started_game")));
	}

	public static String no_money_bet() {
		return wmput(color(msg().getString("no_money_bet")));
	}

	public static String money_select(String string) {
		return wmput(color(msg().getString("money_select")).replaceAll("%sum%", string + ""));
	}

	public static String make_less_zero() {
		return wmput(color(msg().getString("make_less_zero")));
	}

	public static String win(String string) {
		return wmput(color(msg().getString("win")).replaceAll("%sum%", string + ""));
	}

	public static String lose() {
		return wmput(color(msg().getString("lose")));
	}

	public static String start_in_time(int min, int sec) {
		return wmput(color(msg().getString("start_in_time")).replaceAll("%minutes%", min + "").replaceAll("%seconds%", sec + ""));
	}

	public static String start() {
		return wmput(color(msg().getString("start")));
	}

	public static String get_another_item() {
		return wmput(color(msg().getString("get_another_item")));
	}

	// Help
	public static String helpheader() {
		return color(msg().getString("helpheader"));
	}

	public static String helpbet() {
		return color(msg().getString("helpbet"));
	}

	public static String helpbetinventory() {
		return color(msg().getString("helpbetinventory"));
	}

	public static String helpbetgive() {
		return color(msg().getString("helpbetgive"));
	}

	public static String helpbetremove() {
		return color(msg().getString("helpbetremove"));
	}

	public static String helpbetreload() {
		return color(msg().getString("helpbetreload"));
	}

	public static String helpcomm() {
		return color(msg().getString("helpcomm"));
	}
	

	// Options

	public static int time() {
		return config().getInt("time");
	}

	public static boolean separate_roulette() {
		return config().getBoolean("separate_roulette");
	}

	public static boolean delete_after_bet() {
		return config().getBoolean("delete_after_bet");
	}
	public static boolean sound_effects() {
		return config().getBoolean("sound_effects");
	}
	public static boolean fireworks() {
		return config().getBoolean("fireworks");
	}
	
	public static int bet_amount(int i) {
		return config().getInt("bet" + i);
	}
	public static int multiply_black() {
		return config().getInt("multiply_black");
	}
	public static int multiply_red() {
		return config().getInt("multiply_red");
	}
	public static int multiply_green() {
		return config().getInt("multiply_green");
	}
	
	// Items

	public static String main_inv_name() {
		return color(config().getString("main_inv_name"));
	}

	public static String bet_inv_name() {
		return color(config().getString("bet_inv_name"));
	}

	@SuppressWarnings("deprecation")
	public static ItemStack item_create(String id, String name, List<String> lore) {

		if (name != null)
			name = color(name);
		List<String> lorelist = new ArrayList<>();
		if (lore != null)
			for (String key : lore)
				lorelist.add(color(key));
		ItemStack item = null;
		if (Main.legacy() == false)
			item = new ItemStack(Material.getMaterial(id), 1);
		else {
			String ids = id;
			if (id.split(":").length >= 2)
				ids = id.split(":")[0];
			int data = 0;
			if (id.split(":").length >= 2)
				data = Integer.parseInt(id.split(":")[1]);
			if (ids == null)
				System.out.println("!!!");

			item = new ItemStack(Material.getMaterial(ids), 1, (short) 0, (byte) data);
		}
		ItemMeta meta = item.getItemMeta();
		if (name != null)
			meta.setDisplayName(name);
		if (lore != null)
			meta.setLore(lorelist);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack item_name_change(ItemStack item, String name, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack BetItem() {
		String name = config().getString("betting-item-name");
		List<String> lorelist = (List<String>) config().getList("betting-item-name");
		String id = config().getString("betting-item-id");

		return item_create(id, name, lorelist);
	}

	public static ItemStack main_bg() {
		String id = config().getString("main_bg_id");
		String name = " ";
		return item_create(id, name, null);
	}

	public static String increase_amount_color() {
		return config().getString("increase_amount_color");
	}

	public static String decrease_amount_color() {
		return config().getString("decrease_amount_color");
	}

	public static ItemStack line_bg() {
		String id = config().getString("line_bg_id");
		String name = " ";
		return item_create(id, name, null);
	}

	public static ItemStack Rosu() {
		String id = config().getString("red_id");
		String name = config().getString("red_name");
		return item_create(id, name, null);
	}

	public static ItemStack Negru() {
		String id = config().getString("black_id");
		String name = config().getString("black_name");
		return item_create(id, name, null);
	}

	public static ItemStack Verde() {
		String id = config().getString("green_id");
		String name = config().getString("green_name");
		return item_create(id, name, null);
	}

	public static ItemStack back() {
		String id = config().getString("exit_id");
		String exit_name = config().getString("exit_name");
		return item_create(id, exit_name, null);
	}

	public static ItemStack red_button(int suma) {
		String id = config().getString("red_id");
		@SuppressWarnings("unchecked")
		List<String> lorelist = (List<String>) config().getList("red_button_lore");
		List<String> lorelist2 = new ArrayList<>();
		String name = config().getString("red_button_name");
		for (String key : lorelist)
			lorelist2.add(key.replaceAll("%bet_amount%", BaniInv.punct(suma)));
		name = name.replaceAll("%bet_amount%", BaniInv.punct(suma));
		return item_create(id, name, lorelist2);
	}

	public static ItemStack green_button(int suma) {
		String id = config().getString("green_id");
		@SuppressWarnings("unchecked")
		List<String> lorelist = (List<String>) config().getList("green_button_lore");
		List<String> lorelist2 = new ArrayList<>();
		String name = config().getString("green_button_name");
		for (String key : lorelist)
			lorelist2.add(key.replaceAll("%bet_amount%", BaniInv.punct(suma)));
		name = name.replaceAll("%bet_amount%", BaniInv.punct(suma));
		return item_create(id, name, lorelist2);
	}

	public static ItemStack black_button(int suma) {
		String id = config().getString("black_id");
		@SuppressWarnings("unchecked")
		List<String> lorelist = (List<String>) config().getList("black_button_lore");
		List<String> lorelist2 = new ArrayList<>();
		String name = config().getString("black_button_name");
		for (String key : lorelist)
			lorelist2.add(key.replaceAll("%bet_amount%", BaniInv.punct(suma)));
		name = name.replaceAll("%bet_amount%", BaniInv.punct(suma));
		return item_create(id, name, lorelist2);
	}

	public static ItemStack increase_item() {
		String id = config().getString("increase_id");
		return item_create(id, null, null);
	}

	public static ItemStack decrease_item() {
		String id = config().getString("decrease_id");
		return item_create(id, null, null);
	}

	public static ItemStack put_item() {
		String id = config().getString("put_id");
		String name = config().getString("put_name");
		return item_create(id, name, null);
	}

	public static ItemStack cancel_item() {
		String id = config().getString("cancel_id");
		String name = config().getString("cance_name");
		return item_create(id, name, null);
	}

	public static ItemStack status_block() {
		String id = config().getString("status_no_bet_id");
		String name = config().getString("status_no_bet");
		return item_create(id, name, null);
	}

	public static ItemStack status_timer(int time) {
		String id = config().getString("status_timer_id");
		String name = config().getString("status_timer");
		name = name.replaceAll("%time%", time + "");
		return item_create(id, name, null);
	}

	public static ItemStack status_running() {
		String id = config().getString("status_running_id");
		String name = config().getString("status_running");
		return item_create(id, name, null);
	}
	public static ItemStack last_colors()
	{
		String id = config().getString("last_colors_id");
		@SuppressWarnings("unchecked")
		List<String> lorelist = (List<String>) config().getList("last_colors_lore");
		String name = config().getString("last_colors_name");
		return item_create(id, name, lorelist);
	}
	public static String last_colors_inv_name() {
		return color(config().getString("last_colors_name"));
	}

}
