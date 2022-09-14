package me.purplefishh.dipcraft.superbet.utils;

import org.bukkit.entity.Player;

import me.purplefishh.dipcraft.superbet.event.BetEvent;
import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import net.milkbowl.vault.economy.Economy;

public class Win {

	public static void winer(int win, Player separatwinner) {
		if (Resorce.separate_roulette()) {
			if (Resorce.pariu.get(separatwinner) == win)
				giveprice(separatwinner, win);
			else
				separatwinner.sendMessage(Resorce.lose());
			delPariu(separatwinner);
		} else {
			for (Player winners : Resorce.pariu.keySet()) {
				if (Resorce.pariu.get(winners) == win) {
					giveprice(winners, win);
				} else
					winners.sendMessage(Resorce.lose());
				delPariu(winners);
			}
		}
	}

	private static void giveprice(Player p, int win) {
		Economy eco = Main.getEconomy();
		switch (win) {
			case 3:
				p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_green())));
				eco.depositPlayer(p, Resorce.pariubani.get(p) * Resorce.multiply_green());
				break;
			case 1:
				p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_black())));
				eco.depositPlayer(p, Resorce.pariubani.get(p) * Resorce.multiply_black());
				break;
			case 2:
				p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_red())));
				eco.depositPlayer(p, Resorce.pariubani.get(p) * Resorce.multiply_red());
				break;
		}
	}

	private static void delPariu(Player p) {
		Resorce.pariu.clear();
		Resorce.pariubani.clear();
		BetEvent.changebuton(1, 0, p);
		BetEvent.changebuton(2, 0, p);
		BetEvent.changebuton(3, 0, p);
	}

}
