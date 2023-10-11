package me.purplefishh.dipcraft.superbet.utils;

public class Win {

	/*public static void winer(int win, Player separatwinner) {
		if (Resorce.separate_roulette()) {
			if (Resorce.pariu.get(separatwinner) == win) {
				giveprice(separatwinner, win);
				if (Resorce.fireworks())
					firework(separatwinner);
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
					if (Resorce.fireworks())
						firework(winners);
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

	public static void firework(Player p) {
		Location loc = p.getLocation();

		Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
		FireworkMeta fwm = fw.getFireworkMeta();
		fw.setMetadata("nodamage_fw_superbet", new FixedMetadataValue(Main.plugin(), true));

		fwm.setPower(0);
		fwm.addEffect(FireworkEffect.builder().withColor(Color.WHITE).flicker(true).with(Type.BALL_LARGE).build());
		fw.setFireworkMeta(fwm);

		FireworkMeta fwm2 = fwm;
		fwm2.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).with(Type.BURST).build());
		fw.setFireworkMeta(fwm2);

	}*/

}
