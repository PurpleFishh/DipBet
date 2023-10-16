package me.purplefishh.dipcraft.superbet.command;

import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class BetGiveItemCommand implements ICommand {
    @Override
    public void execute(CommandSender sender, String[] args) {
        Player sendTo = null;
        if (args.length > 0)
            if (Bukkit.getServer().getPlayerExact(args[0]) != null && Bukkit.getServer().getPlayerExact(args[0]).isOnline())
                sendTo = Bukkit.getServer().getPlayerExact(args[0]);
            else
                sender.sendMessage(MessagesCollection.getInstance().offline_player);
        else if (!(sender instanceof Player))
            sender.sendMessage(MessagesCollection.getInstance().players_only);
        else
            sendTo = (Player) sender;
        giveItem(Objects.requireNonNull(sendTo, "Tried to send item to a player but the player was null!"));
    }

    private void giveItem(Player player) {
        if (!player.getInventory().contains(ItemsCollection.getInstance().betting))
            player.getInventory().addItem(ItemsCollection.getInstance().betting);
    }

    @Override
    public String getDescription() {
        return "give the player the item that opens the roulette inventory";
    }

    @Override
    public String getUsage() {
        return "<*player_name>";
    }

    @Override
    public String getLabel() {
        return "";
    }
}
