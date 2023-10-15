package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;

/**
 * Reload the plugin configs
 */
public class ReloadCommand implements ICommand {

    @Override
    public void execute(CommandSender sender, String[] args) {
        Main.getInstance().reload();
        sender.sendMessage(MessagesCollection.getInstance().reload_success);
    }

    @Override
    public String getLabel() {
        return "reload";
    }

    @Override
    public String getDescription() {
        return "reload plugin config";
    }

    @Override
    public String getUsage() {
        return "";
    }
}
