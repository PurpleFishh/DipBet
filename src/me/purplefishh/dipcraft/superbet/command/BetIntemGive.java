package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Give the betting item to a player
 */
public class BetIntemGive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Commands for players and console
		Main.getInstance().getCommandGroup().execute(sender, args);
		return true;
        /*if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("superbet.command.reload")) {
					Main.reloadConf();
					if (!Resorce.separate_roulette()) {
						Main.inv = Bukkit.createInventory(null, 45, Repleace.repleace(Resorce.main_inv_name()));
						ColorUtils.colorinv(Main.inv);
					}
					sender.sendMessage(Resorce.water_mark() + ChatColor.GREEN + " Reload complete!");
				} else
					sender.sendMessage(Resorce.permission());
				return true;
			}
			if (args[0].equalsIgnoreCase("help")) {
				if (sender.hasPermission("superbet.command.help")) {
					help(sender);
				} else
					sender.sendMessage(Resorce.permission());
				return true;
			}
			
			if (args[0].equalsIgnoreCase("inventory")) {
				if (sender.hasPermission("superbet.command.inventory")) {
					Player p;
					if (args.length == 1)
					{
						if (!(sender instanceof Player)) {
							sender.sendMessage("Only players can execute this command!");
							return true;
						}
						p = (Player) sender;
					}
					else if (exPlayer(args[1]))
						p = Bukkit.getPlayer(args[1]);
					else {
						sender.sendMessage(Resorce.offline_player());
						return true;
					}

					if (Resorce.separate_roulette()) {
						if (BetOpen.invs.containsKey(p))
							p.openInventory(BetOpen.invs.get(p));
						else {
							Inventory inv = Bukkit.createInventory(null, 45,
									Repleace.repleace(Resorce.main_inv_name()));
							ColorUtils.colorinv(inv);
							p.openInventory(inv);
							BetOpen.invs.put(p, inv);
						}
					} else
						p.openInventory(Main.inv);
				} else
					sender.sendMessage(Resorce.permission());
				return true;
			}
			
			if (sender.hasPermission("superbet.command.itemgive.other")) {
				if (exPlayer(args[0])) {
					Player p = Bukkit.getPlayer(args[0]);
					sender.sendMessage(Resorce.send_item());
					itemininv(p);
				} else {
					sender.sendMessage(Resorce.offline_player());
				}
				return true;
			} else {
				sender.sendMessage(Resorce.permission());
				return true;
			}	
		}

		// Commands only for players
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can execute this command!");
			return true;
		}
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("remove")) {
				if (sender.hasPermission("superbet.command.remove")) {
					Player p = (Player) sender;
					p.getInventory().remove(Resorce.BetItem());
					p.sendMessage(Resorce.remove_item());
				} else
					sender.sendMessage(Resorce.permission());
				return true;
			}
		}
		if (sender.hasPermission("superbet.command.itemgive"))
			itemininv((Player) sender);
		else
			sender.sendMessage(Resorce.permission());
		return true;
	}

	private boolean exPlayer(String name) {
		for (Player key : Bukkit.getOnlinePlayers())
			if (key.getName().equalsIgnoreCase(name))
				return true;
		return false;
	}

	private void itemininv(Player p) {
		Inventory inv = p.getInventory();
		if (inv.contains(Resorce.BetItem())) {
			p.sendMessage(Resorce.get_another_item());
			return;
		}
		p.getInventory().addItem(Resorce.BetItem());
		p.sendMessage(Resorce.get_item());
	}

	private void help(CommandSender p) {
		p.sendMessage(Resorce.helpheader());
		if (p.hasPermission("superbet.command.itemgive"))
			p.sendMessage(Resorce.helpbet());
		if (p.hasPermission("superbet.command.itemgive.inventory"))
			p.sendMessage(Resorce.helpbetinventory());
		if (p.hasPermission("superbet.command.itemgive.other"))
			p.sendMessage(Resorce.helpbetgive());
		if (p.hasPermission("superbet.command.remove"))
			p.sendMessage(Resorce.helpbetremove());
		if (p.hasPermission("superbet.command.reload"))
			p.sendMessage(Resorce.helpbetreload());
		if (p.hasPermission("superbet.command.help"))
			p.sendMessage(Resorce.helpcomm());
	}*/
	}
}
