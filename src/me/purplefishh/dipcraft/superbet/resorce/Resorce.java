package me.purplefishh.dipcraft.superbet.resorce;

public class Resorce {

/*	// Lista Pariu
	public static HashMap<Player, Integer> pariubani = new HashMap<>();
	public static HashMap<Player, Integer> pariu = new HashMap<>();

	// Config
	*//*private static FileConfiguration config() {
		return Main.config();
	}

	private static FileConfiguration msg() {
		return Main.msg();
	}*//*


	// Help

	

	// Options


	
	// Items



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
	}*/


}
