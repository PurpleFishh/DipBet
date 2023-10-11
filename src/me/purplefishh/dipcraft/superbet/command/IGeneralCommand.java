package me.purplefishh.dipcraft.superbet.command;

import org.bukkit.command.CommandSender;

/**
 * The general command interface used for commands and commands group
 */
public interface IGeneralCommand {
    /**
     * This function will be called when the command is executed
     * @param sender the sender of the command
     * @param args command arguments sent
     */
    void execute(CommandSender sender, String[] args);

    /**
     * @return command label
     */
    String getLabel();
}
