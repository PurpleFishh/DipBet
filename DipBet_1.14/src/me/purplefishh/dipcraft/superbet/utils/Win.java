package me.purplefishh.dipcraft.superbet.utils;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.purplefishh.dipcraft.superbet.event.BetEvent;
import me.purplefishh.dipcraft.superbet.main.Main;
import me.purplefishh.dipcraft.superbet.resorce.Resorce;
import net.milkbowl.vault.economy.Economy;

public class Win {

	public static void winer(int win, Player separatwinner) {
		if (Resorce.separate_roulette()) {
			if (Resorce.pariu.get(separatwinner) == win) {
				giveprice(separatwinner, win);
				if (Resorce.sound_effects())
					separatwinner.playSound(separatwinner, Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
			} else {
				separatwinner.sendMessage(Resorce.lose());
				if (Resorce.sound_effects())
					separatwinner.playSound(separatwinner, Sound.BLOCK_ANVIL_PLACE, 100, 1);
			}
			delPariu(separatwinner);
		} else {
			for (Player winners : Resorce.pariu.keySet()) {
				if (Resorce.pariu.get(winners) == win) {
					giveprice(winners, win);
					if (Resorce.sound_effects())
						winners.playSound(winners, Sound.ENTITY_PLAYER_LEVELUP, 100, 1);
				} else {
					winners.sendMessage(Resorce.lose());
					if (Resorce.sound_effects())
						separatwinner.playSound(separatwinner, Sound.BLOCK_ANVIL_PLACE, 100, 1);
				}
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
