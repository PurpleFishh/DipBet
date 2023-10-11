package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import me.purplefishh.dipcraft.superbet.utils.Tuple;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashSet;

public class CommandGroup implements IGeneralCommand {

    private final String label;
    private final HashSet<IGeneralCommand> childrenCommands;

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
        if (!sender.hasPermission(command.getCenter())) {
            sender.sendMessage(MessagesCollection.getInstance().permission);
            return;
        }
        int paramsNeeded = (int) Arrays.stream(command.getLeft().getUsage().split(" "))
                .filter(param -> param.startsWith("<") && !param.startsWith("<*"))
                .count();
        if(paramsNeeded != command.getRight().length) {
            sender.sendMessage(MessagesCollection.getInstance().not_enough_args);
            return;
        }
        command.getLeft().execute(sender, command.getRight());
//        for (ICommand command : childrenCommands) {
//            if (command.getLabel().equalsIgnoreCase(args[0])) {
//                    command.execute(sender, Arrays.copyOfRange(args, 1, args.length));
//                    return;
//            }
//        }
    }

    public Tuple<ICommand, String, String[]> getCommand(String[] args, String permission) {
        if (args.length < 1)
            return null;
        for (IGeneralCommand command : childrenCommands) {
            if (command.getLabel().equalsIgnoreCase(args[0])) {
                if (command instanceof CommandGroup) {
                    CommandGroup cmd = (CommandGroup) command;
                    return cmd.getCommand(Arrays.copyOfRange(args, 1, args.length), permission + "." + cmd.getLabel());
                } else if (command instanceof ICommand) {
                    return new Tuple<>((ICommand) command, permission + "." + command.getLabel(), Arrays.copyOfRange(args, 1, args.length));
                }
            }
        }
        return null;
    }


    public void add(IGeneralCommand... commands) {
        childrenCommands.addAll(Arrays.asList(commands));
    }

    public void remove(IGeneralCommand command) {
        childrenCommands.remove(command);
    }

    @Override
    public String getLabel() {
        return label;
    }
}
