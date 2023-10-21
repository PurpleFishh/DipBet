package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.command.system.ICommand;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetRemoveItemCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof Player)
            ((Player) sender).getInventory().remove(ItemsCollection.getInstance().betting);
        else
            sender.sendMessage(MessagesCollection.getInstance().players_only);
    }

    @Override
    public String getDescription() {
        return "remove the bet item from your inventory";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getLabel() {
        return "remove";
    }
}