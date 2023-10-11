package me.purplefishh.dipcraft.superbet.game;

import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.utils.InventoryPopulate;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Board {

    private Inventory gameInv;

    public Board()
    {
        gameInv = Bukkit.createInventory(null, 45, ConfigCollection.getInstance().main_inv_name);
        InventoryPopulate.mainInventory(gameInv);
    }


    public void openInventory(Player player) {
        player.openInventory(gameInv);
    }
}
