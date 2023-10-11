package me.purplefishh.dipcraft.superbet.board;

import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.utils.InventoryPopulate;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class Board {

    private Inventory gameInventory;

    public Board()
    {
        gameInventory = Bukkit.createInventory(null, 45, ConfigCollection.getInstance().main_inv_name);
        InventoryPopulate.mainInventory(gameInventory);
    }


}
