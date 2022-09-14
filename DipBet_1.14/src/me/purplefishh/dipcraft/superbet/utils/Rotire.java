package me.purplefishh.dipcraft.superbet.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.ColorUtils;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;

public class Rotire {

	private static long t;

	public static boolean start = false;
	public static HashMap<Player, Boolean> starts = new HashMap<>();

	public static List<Integer> last = new ArrayList<>();
	public static HashMap<Player, List<Integer>> last_separate = new HashMap<>();

	private static int rotiri() {
		Random rand = new Random();
		int ra = rand.nextInt(40);

		while (ra < 37)
			ra = rand.nextInt(40);
		return ra;
	}

	public static void InvRotire(final Inventory inv, final Player p) {
		if (Resorce.separate_roulette()) {
			starts.put(p, true);
			TimeUntilStart.starts.replace(p, false);

		} else {
			start = true;
			TimeUntilStart.start = false;
		}

		List<Integer> displayedcolors = new ArrayList<Integer>();
		int color = ColorUtils.colorgive();
		for (int i = 0; i <= 8; ++i) {
			if (ColorUtils.colorgive() == 3 && !displayedcolors.contains(3))
				displayedcolors.add(3);
			else
				displayedcolors.add(color);
			inv.setItem(9 + i, it(displayedcolors.get(i)));
			color = (color == 2) ? 1 : 2;
		}
		t = 7L;
		new BukkitRunnable() {
			int rot = rotiri();
			int k = 0, n = 0;

			@Override
			public void run() {
				if (n == rot) {
					Win.winer(displayedcolors.get(4), p);

					if (Resorce.separate_roulette()) {
						starts.replace(p, false);
						if (!last_separate.containsKey(p)) {
							List<Integer> last = new ArrayList<>();
							last_list_maker(last, displayedcolors.get(4));
							last_separate.put(p, last);
						} else {
							last_list_maker(last_separate.get(p), displayedcolors.get(4));
						}
					} else {
						start = false;
						last_list_maker(last, displayedcolors.get(4));
					}
					inv.setItem(36, Resorce.status_block());
					this.cancel();
				}
				if (k >= t) {
					displayedcolors.remove(0);
					if (ColorUtils.colorgive() == 3 && !displayedcolors.contains(3))
						displayedcolors.add(3);
					else
						displayedcolors.add(displayedcolors.get(7) == 2 ? 1 : 2);

					n++;
					for (int i = 0; i <= 8; ++i) {
						inv.setItem(9 + i, it(displayedcolors.get(i)));
					}
					if (n >= 35)
						t = k + 7L * (n - 33);
					else if (n >= 30)
						t = k + 7L * 2;
					else if (n >= 20)
						t = k + 7L;
				}
				k += 7;
			}
		}.runTaskTimer(Main.plugin(), 7L, 7L);

	}

	static ItemStack it(int r) {
		switch (r) {
		case 1:
			return Resorce.Negru();

		case 2:
			return Resorce.Rosu();

		case 3:
			return Resorce.Verde();
		}
		return null;
	}

	private static List<Integer> last_list_maker(List<Integer> list, int color) {
		if (list.size() == 9)
			list.remove(0);
		list.add(color);
		return list;
	}

}
