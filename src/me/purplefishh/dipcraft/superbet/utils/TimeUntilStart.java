package me.purplefishh.dipcraft.superbet.utils;

import java.util.HashMap;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.purplefishh.dipcraft.superbet.event.BetOpen;
import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;

public class TimeUntilStart {

	public static boolean start = false;
	public static HashMap<Player, Boolean> starts = new HashMap<>();

	public static void start(final Player p, final Inventory inv) {
		if (Resorce.separate_roulette())
			starts.put(p, true);
		else
			start = true;
		
		new BukkitRunnable() {
			int t = Resorce.time();
			
			@Override
			public void run() {
				inv.setItem(36, Resorce.status_timer(t - 1));
				if (t == 0) {

					if (Resorce.separate_roulette()) {
						Rotire.InvRotire(BetOpen.invs.get(p), p);
						p.sendMessage(Resorce.start());
					} else {
						Rotire.InvRotire(Main.inv, p);
						msgsend(Resorce.start());
					}
					inv.setItem(36, Resorce.status_running());
					this.cancel();
				}
				if(t % 30 == 0 && t >= 30)
				 {
					if (Resorce.separate_roulette())
						p.sendMessage(timecalulate(t));
					else
						msgsend(timecalulate(t));

				}
				if (t % 10 == 0 && t > 0 && t < 30) {
					if (Resorce.separate_roulette())
						p.sendMessage(timecalulate(t));
					else
						msgsend(timecalulate(t));

				}
				if (t < 10 && t > 0) {
					if (Resorce.separate_roulette())
						p.sendMessage(timecalulate(t));
					else
						msgsend(timecalulate(t));
					if(Resorce.sound_effects())
						Rotire.playsound(p, Sound.BLOCK_NOTE_BLOCK_PLING, 1);
				}
				t--;
			}
		}.runTaskTimer(Main.plugin(), 20L, 20L);
	}

	public static void msgsend(String s) {
		for (Player p : Resorce.pariu.keySet()) {
			p.sendMessage(s);
		}
	}
	static String timecalulate(int time)
	{
		int min = 0, sec = 0;
		if (time >= 60)
			min = time / 60;
		if (time % 60 != 0)
			sec = time - min * 60;
		return Resorce.start_in_time(min, sec);
	}
}
