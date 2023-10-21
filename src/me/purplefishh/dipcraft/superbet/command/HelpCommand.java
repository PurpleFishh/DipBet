package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.command.system.ICommand;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;

public class HelpCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        sender.sendMessage(MessagesCollection.getInstance().helpHeader);
        if (sender.hasPermission("bet.itemgive.himself"))
            sender.sendMessage(MessagesCollection.getInstance().helpBet);
        if (sender.hasPermission("bet.inventory"))
            sender.sendMessage(MessagesCollection.getInstance().helpBetInventory);
        if (sender.hasPermission("bet.itemgive.other"))
            sender.sendMessage(MessagesCollection.getInstance().helpBetGive);
        if (sender.hasPermission("bet.remove"))
            sender.sendMessage(MessagesCollection.getInstance().helpBetRemove);
        if (sender.hasPermission("bet.reload"))
            sender.sendMessage(MessagesCollection.getInstance().helpBetReload);
        if (sender.hasPermission("bet.help"))
            sender.sendMessage(MessagesCollection.getInstance().helpCommand);
    }

    @Override
    public String getDescription() {
        return "the help command of the plugin";
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getLabel() {
        return "help";
    }
}