package me.purplefishh.dipcraft.superbet.game;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import me.purplefishh.dipcraft.superbet.gameTimers.GameStarting;
import me.purplefishh.dipcraft.superbet.handel.ExitItemPress;
import me.purplefishh.dipcraft.superbet.helpers.TextHelper;
import me.purplefishh.dipcraft.superbet.resorce.BettingColors;
import me.purplefishh.dipcraft.superbet.utils.PlacedBet;
import me.purplefishh.dipcraft.superbet.utils.WinningFireworks;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameSingle implements IGame {

    private final Board board;
    private final HashMap<Player, PlacedBet> bets;
    private GameStatus status;

    public GameSingle() {
        board = new Board(this);
        bets = new HashMap<>();
        status = GameStatus.STANDBY;
    }

    public void openMainMenu(Player player) {
        board.openInventory(player);
    }

    public void openBettingMenu(Player player, BettingColors color) {
        if (status != GameStatus.PLAYING)
            if (!bets.containsKey(player)) {
                bets.put(player, new PlacedBet(player, color));
                board.openBettingInventory(player);
            } else
                player.sendMessage(MessagesCollection.getInstance().already_betted);
        else
            player.sendMessage(MessagesCollection.getInstance().started_game);
    }

    public Board getBoard(Player p) {
        return board;
    }

    public void placeBet(Player player) {
        if (bets.get(player).getAmount() > 0) {
            bets.get(player).setPlaced(true);
            Main.getInstance().getEcon().withdrawPlayer(player, bets.get(player).getAmount());
            ExitItemPress.exitFromInventory(player, ConfigCollection.getInstance().bet_inv_name);
            player.sendMessage(MessagesCollection.getInstance().betPlaced(bets.get(player).getAmount(), bets.get(player).getColor()));
            if (status == GameStatus.STANDBY)
                nextGameState();
        } else
            player.sendMessage(MessagesCollection.getInstance().no_money_bet);
    }

    public void removePlacedBet(Player player) {
        bets.remove(player);
    }

    public void addToBetMoney(Player player, double amount) {
        if (bets.get(player).getAmount() == 0 && amount < 0)
            player.sendMessage(MessagesCollection.getInstance().make_less_zero);
        else {
            if (bets.get(player).getAmount() + amount > Main.getInstance().getEcon().getBalance(player))
                bets.get(player).setAmount(Main.getInstance().getEcon().getBalance(player));
            else
                bets.get(player).addToAmount(amount);
            player.sendMessage(MessagesCollection.getInstance().moneySelect(bets.get(player).getAmount()));
        }
    }

    public void nextGameState() {
        if (status == GameStatus.STANDBY)
            start();
        else if (status == GameStatus.STARTING)
            startPlaying();
        else if (status == GameStatus.PLAYING)
            endGame();
    }

    private void start() {
        status = GameStatus.STARTING;
        GameStarting startingTimer = new GameStarting(this, null);
        startingTimer.runTaskTimer(Main.getInstance(), 20L, 20L);
    }

    public void startPlaying() {
        status = GameStatus.PLAYING;
        board.updateStatusItem(0);
        board.spinRoulette();
    }

    public void endGame() {
        status = GameStatus.STANDBY;
        board.updateStatusItem(0);

        BettingColors winningColor = board.getWinningColor();
        for (Player winner : bets.keySet()) {
            if (bets.get(winner).getColor() == winningColor) {
                double winningSum = bets.get(winner).getAmount() * winningColor.getMultiply();
                winner.sendMessage(MessagesCollection.getInstance().win(TextHelper.numberDot(winningSum)));
                Main.getInstance().getEcon().depositPlayer(winner, winningSum);

                WinningFireworks.firework(winner);
                playSound(Sound.ENTITY_PLAYER_LEVELUP, 1);
            } else {
                winner.sendMessage(MessagesCollection.getInstance().lose);
                playSound(Sound.BLOCK_ANVIL_PLACE, 1);
            }
        }
        bets.clear();
        board.reset();
    }

    public void broadcastMessage(String msg) {
        bets.keySet().forEach(player -> player.sendMessage(msg));
    }

    public void playSound(Sound sound, float pitch) {
        if (ConfigCollection.getInstance().sound_effects)
            bets.keySet().forEach(player -> player.playSound(player, sound, 100, pitch));
    }

    public GameStatus getStatus() {
        return status;
    }

    public PlacedBet getPlayerBet(Player player) {
        return bets.get(player);
    }

    public void delete() {
        Bukkit.getOnlinePlayers().stream()
                .filter(player -> ConfigCollection.getInstance().inventoriesName.contains(player.getOpenInventory().getTitle()))
                .forEach(HumanEntity::closeInventory);
        bets.clear();
    }
}
