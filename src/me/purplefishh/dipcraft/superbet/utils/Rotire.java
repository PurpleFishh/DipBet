package me.purplefishh.dipcraft.superbet.utils;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.resorce.ColorUtils;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Rotire {

	static long t;
	static List<Integer> v = new ArrayList<>();
	public static boolean start = false;
	public static HashMap<Player, Boolean> starts = new HashMap<>();

	public static List<Integer> last = new ArrayList<>();
	public static HashMap<Player, List<Integer>> last_separate = new HashMap<>();

	static int rotiri() {
		Random rand = new Random();
		int ra = rand.nextInt(40);

		while (ra < 37)
			ra = rand.nextInt(40);
		return ra;
	}

	public static void InvRotire(final Inventory inv, final Player p) {
		if (Resorce.separate_roulette())
			starts.put(p, true);
		else
			start = true;
		if (Resorce.separate_roulette())
			TimeUntilStart.starts.replace(p, false);
		else
			TimeUntilStart.start = false;
		int color = ColorUtils.colorgive();
		while (color == 3)
			color = ColorUtils.colorgive();
		for (int i = 0; i <= 8; ++i) {
			if (ColorUtils.colorgive() == 3 && !v.contains(3))
				v.add(i, 3);
			else
				v.add(i, color);
			inv.setItem(9 + i, it(v.get(i)));
			if (color == 2)
				color = 1;
			else if (color == 1)
				color = 2;
		}
		if (Resorce.sound_effects())
			playsound(p, Sound.BLOCK_CHEST_OPEN, 1);

		t = 7L;
		new BukkitRunnable() {
			int rot = rotiri();
			int k = 0, n = 0;

			@Override
			public void run() {
				if (n == rot) {
					Win.winer(v.get(4), p);

					if (Resorce.separate_roulette()) {
						starts.replace(p, false);
						List<Integer> last = new ArrayList<>();
						if (!last_separate.containsKey(p)) {
							last_list_maker(last, v.get(4));
							last_separate.put(p, last);
						} else {
							last = last_separate.get(p);
							last_list_maker(last, v.get(4));
						}
					} else {
						start = false;
						last_list_maker(last, v.get(4));
					}
					inv.setItem(36, Resorce.status_block());
					this.cancel();
				}
				if (k >= t) {
					for (int i = 0; i < 8; ++i)
						v.set(i, v.get(i + 1));

					int color = v.get(7);
					int x = 6;
					while (color == 3) {
						color = v.get(x);
						x--;
						if (x == 0) {
							color = ColorUtils.colorgive();
							return;
						}
					}
					if (color == 2)
						color = 1;
					else if (color == 1)
						color = 2;
					if (ColorUtils.colorgive() == 3 && !v.contains(3))
						v.set(8, 3);
					else
						v.set(8, color);

					n++;
					for (int i = 0; i <= 8; ++i) {
						inv.setItem(9 + i, it(v.get(i)));
					}

					// p.playSound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 100, 1);
					// playsound(p, Sound.UI_BUTTON_CLICK, 10);
					if (Resorce.sound_effects())
						playsound(p, Sound.BLOCK_NOTE_BLOCK_SNARE, 1);

					if (n >= 35)
						t = k + 7L * (n - 33);
					else if (n >= 30)
						t = k + 7L * 2;
					else if (n >= 20)
						t = k + 7L;
				}
				k += 7;
				// playsound(p, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 3);
				if (Resorce.sound_effects())
					playsound(p, Sound.BLOCK_NOTE_BLOCK_BASS, 1);
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

	public static void playsound(Player p, Sound sound, float pitch) {
		if (Resorce.separate_roulette()) {
			p.playSound(p, sound, 100, pitch);
		} else {
			for (Player pp : Resorce.pariu.keySet())
				pp.playSound(pp, sound, 100, pitch);
		}
	}
}
