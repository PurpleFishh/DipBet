package me.purplefishh.dipcraft.superbet.game;

import me.purplefishh.dipcraft.superbet.resorce.BettingColors;
import me.purplefishh.dipcraft.superbet.utils.PlacedBet;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public interface IGame {
    void openMainMenu(Player player);

    void openBettingMenu(Player player, BettingColors color);

    Board getBoard(Player p);

    void placeBet(Player player);

    void addToBetMoney(Player player, double amount);

    void removePlacedBet(Player player);

    void broadcastMessage(String msg);

    void nextGameState();

    void playSound(Sound sound, float pitch);

    GameStatus getStatus();

    PlacedBet getPlayerBet(Player player);
}
