package me.purplefishh.dipcraft.superbet.command;

import org.bukkit.command.CommandSender;

public interface IGeneralCommand {
    void execute(CommandSender sender, String[] args);
    String getLabel();
}
