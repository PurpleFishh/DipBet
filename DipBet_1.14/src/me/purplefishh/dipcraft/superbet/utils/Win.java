package me.purplefishh.dipcraft.superbet.utils;

import org.bukkit.entity.Player;

import me.purplefishh.dipcraft.superbet.event.BetEvent;
import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import net.milkbowl.vault.economy.Economy;

public class Win {

	@SuppressWarnings({ "deprecation" })
	public static void winer(int win, Player pp) {
		if (Resorce.separate_roulette()) {
			if (Resorce.pariu.get(pp) == win) {

				Economy eco = Main.getEconomy();
				if (win == 3) {
					pp.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(pp) * Resorce.multiply_green())));
					eco.depositPlayer(pp.getName(), Resorce.pariubani.get(pp) * Resorce.multiply_green());
				} else if (win == 1) {
					pp.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(pp) * Resorce.multiply_black())));
					eco.depositPlayer(pp.getName(), Resorce.pariubani.get(pp) * Resorce.multiply_black());
				}if(win == 2) {
					pp.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(pp) * Resorce.multiply_red())));
					eco.depositPlayer(pp.getName(), Resorce.pariubani.get(pp) * Resorce.multiply_red());
				}

			} else
				pp.sendMessage(Resorce.lose());
			delPariu(pp);
		} else {
			for (Player p : Resorce.pariu.keySet()) {
				if (Resorce.pariu.get(p) == win) {

					Economy eco = Main.getEconomy();
					if (win == 3) {
						p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_green())));
						eco.depositPlayer(p.getName(), Resorce.pariubani.get(p) * Resorce.multiply_green());
					} if(win == 1){
						p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_black())));
						eco.depositPlayer(p.getName(), Resorce.pariubani.get(p) * Resorce.multiply_black());
					}if(win == 2) {
						p.sendMessage(Resorce.win(BaniInv.punct(Resorce.pariubani.get(p) * Resorce.multiply_red())));
						eco.depositPlayer(p.getName(), Resorce.pariubani.get(p) * Resorce.multiply_red());
					}

				} else
					p.sendMessage(Resorce.lose());
			}
			delPariu(pp);
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
