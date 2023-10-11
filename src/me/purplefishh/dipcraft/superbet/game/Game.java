package me.purplefishh.dipcraft.superbet.game;

import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Game {

    private ArrayList<Board> boards = new ArrayList<>();

    public Game() {
        if (!ConfigCollection.getInstance().separate_roulette)
            boards.add(new Board());
    }

    public void openBetting(Player player) {
        if (ConfigCollection.getInstance().separate_roulette) {

        } else {
            getFirstBoard().openInventory(player);
        }
    }

    public Board getFirstBoard()
    {
        return boards.get(0);
    }
}
