package me.purplefishh.dipcraft.superbet.command;

import org.bukkit.command.CommandSender;

public class BetRemoveItemCommand implements ICommand{
    @Override
    public void execute(CommandSender sender, String[] args) {

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