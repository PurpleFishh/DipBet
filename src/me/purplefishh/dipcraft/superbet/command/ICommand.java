package me.purplefishh.dipcraft.superbet.command;

/**
 * Interface used for commands
 */
public interface ICommand extends IGeneralCommand{
    /**
     * @return command description
     */
    String getDescription();

    /**
     * Parameters that the command uses <br>
     * Example: '<amount> <*player_name>' -> amount is the first argument that is mandatory and player name is the second argument that is optional
     * @return command arguments
     */
    String getUsage();
}
