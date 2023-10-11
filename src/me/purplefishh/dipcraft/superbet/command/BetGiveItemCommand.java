package me.purplefishh.dipcraft.superbet.command;

import com.comphenix.protocol.PacketType;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BetGiveItemCommand implements ICommand{
    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player))
        {
            sender.sendMessage(MessagesCollection.getInstance().players_only);
            return;
        }
        ((Player)sender).getInventory().addItem(ItemsCollection.getInstance().betting);
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
