package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetInventoryOpenCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player)
            Main.getInstance().getGame((Player) sender).openMainMenu((Player) sender);
        else
            sender.sendMessage(MessagesCollection.getInstance().players_only);
    }

    @Override
    public String getDescription() {
        return "open the roulette inventory";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getLabel() {
        return "inventory";
    }
}