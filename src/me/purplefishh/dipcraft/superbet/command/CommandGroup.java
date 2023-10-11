package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import me.purplefishh.dipcraft.superbet.utils.Tuple;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * Group of commands<br>
 * Stored in a Trie where leafs are commands and the nodes are tags that make the complete command for execution
 */
public class CommandGroup implements IGeneralCommand {

    private final String label;
    private final HashSet<IGeneralCommand> childrenCommands;

    /**
     * @param label group label
     * @param command commands or groups registered with this label
     */
    public CommandGroup(String label, IGeneralCommand... command) {
        this.label = label;
        childrenCommands = new HashSet<>(Arrays.asList(command));
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        Tuple<ICommand, String, String[]> command = getCommand(args, getLabel());
        if (command == null) {
            sender.sendMessage(MessagesCollection.getInstance().wrong_command);
            return;
        }
        if (!sender.hasPermission(command.center())) {
            sender.sendMessage(MessagesCollection.getInstance().permission);
            return;
        }
        int paramsNeeded = (int) Arrays.stream(command.left().getUsage().split(" "))
                .filter(param -> param.startsWith("<") && !param.startsWith("<*"))
                .count();
        if (paramsNeeded != command.right().length) {
            sender.sendMessage(MessagesCollection.getInstance().not_enough_args);
            return;
        }
        command.left().execute(sender, command.right());
//        for (ICommand command : childrenCommands) {
//            if (command.getLabel().equalsIgnoreCase(args[0])) {
//                    command.execute(sender, Arrays.copyOfRange(args, 1, args.length));
//                    return;
//            }
//        }
    }

    /**
     * Find the command that the player tries to execute<br>
     * If the command dose not exists it will return null <br>
     * @param args the arguments that the sender gives
     * @param permission the base label of the permission
     * @return a tuple that contains on the left side the command,<br> in center the permission for the command,<br> and left the arguments that the command is executed with
     */
    public Tuple<ICommand, String, String[]> getCommand(String[] args, String permission) {
        if (args.length < 1)
            if (Objects.equals(permission, this.getLabel()))
                for (IGeneralCommand command : childrenCommands) {
                    if (command.getLabel().equalsIgnoreCase("")) {
                        return new Tuple<>((ICommand) command, permission + "." + command.getLabel(), args);
                    }
                }
            else
                return null;
        for (IGeneralCommand command : childrenCommands) {
            if (command.getLabel().equalsIgnoreCase(args[0])) {
                if (command instanceof CommandGroup cmd) {
                    return cmd.getCommand(Arrays.copyOfRange(args, 1, args.length), permission + "." + cmd.getLabel());
                } else if (command instanceof ICommand) {
                    return new Tuple<>((ICommand) command, permission + "." + command.getLabel(), Arrays.copyOfRange(args, 1, args.length));
                }
            }
        }
        return null;
    }


    /**
     * Add commands to the group
     * @param commands the commands to be added the group
     */
    public void add(IGeneralCommand... commands) {
        childrenCommands.addAll(Arrays.asList(commands));
    }

    /**
     * Remove a command from the group
     * @param command the command wanted to be removed
     */
    public void remove(IGeneralCommand command) {
        childrenCommands.remove(command);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
