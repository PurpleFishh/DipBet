package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.configCollections.DataStorageCollection;

import java.util.ArrayList;

public class CommandsCollection implements DataStorageCollection {
    private static CommandsCollection instance = null;

    private CommandsCollection() {
        commands = new ArrayList<>();
        loadData();
    }

    public static CommandsCollection getInstance() {
        if (instance == null)
            instance = new CommandsCollection();
        return instance;
    }

    ArrayList<IGeneralCommand> commands;

    @Override
    public void loadData() {
        commands.add(new ReloadCommand());
    }

    public IGeneralCommand getCommand(String label) {
        return commands.stream().filter(cmd -> cmd.getLabel().equalsIgnoreCase(label)).findFirst().orElse(null);
    }
}
