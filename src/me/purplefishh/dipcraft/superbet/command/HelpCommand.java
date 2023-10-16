package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;

public class HelpCommand implements ICommand{
    @Override
    public void execute(CommandSender sender, String[] args) {
        MessagesCollection.getInstance().helpLines.forEach(sender::sendMessage);
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