package me.purplefishh.dipcraft.superbet.game;

import javafx.util.Pair;
import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.ItemsCollection;
import me.purplefishh.dipcraft.superbet.helpers.ColorHelper;
import me.purplefishh.dipcraft.superbet.resorce.BettingColors;
import me.purplefishh.dipcraft.superbet.utils.InventoryPopulate;
import me.purplefishh.dipcraft.superbet.utils.ReplaceTags;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static me.purplefishh.dipcraft.superbet.helpers.ItemHelper.replacePlaceHolders;

public class Board {

    private final Inventory gameInv, lastColorInv, placeBetInv;
    private final ArrayList<BettingColors> rouletteColors;
    private final Stack<BettingColors> lastWinningColors;
    private final Random random;

    public Board() {
        gameInv = Bukkit.createInventory(null, 45, ConfigCollection.getInstance().main_inv_name);
        lastColorInv = Bukkit.createInventory(null, 45, ConfigCollection.getInstance().last_colors_inv_name);
        placeBetInv = Bukkit.createInventory(null, 45, ConfigCollection.getInstance().bet_inv_name);
        InventoryPopulate.mainInventory(gameInv);
        InventoryPopulate.lastColorInventory(lastColorInv);
        InventoryPopulate.placeBetInventory(placeBetInv);

        rouletteColors = new ArrayList<>(9);
        lastWinningColors = new Stack<>();
        random = new Random();
    }


    public void openInventory(Player player) {
        player.openInventory(gameInv);
    }

    public void openLastColorInventory(Player player) {
        player.openInventory(lastColorInv);
    }

    public void openBettingInventory(Player player) {
        player.openInventory(placeBetInv);
    }

    public void updateStatusItem(int time) {
        if (Main.getInstance().getGame().getStatus() == GameStatus.STARTING)
            gameInv.setItem(36, replacePlaceHolders(ItemsCollection.getInstance().status_timer, new Pair<>(ReplaceTags.TIME.getTag(), time)));
        if (Main.getInstance().getGame().getStatus() == GameStatus.PLAYING)
            gameInv.setItem(36, ItemsCollection.getInstance().status_running);
        if (Main.getInstance().getGame().getStatus() == GameStatus.STANDBY)
            gameInv.setItem(36, ItemsCollection.getInstance().status_no_bet);
    }

    public void reset() {
        InventoryPopulate.cleanBettingButtons(gameInv);
    }

    public void spinRoulette() {
        BettingColors color = ColorHelper.giveColor();
        while (color == BettingColors.GREEN)
            color = ColorHelper.giveColor();
        for (int i = 0; i <= 8; ++i) {
            if (ColorHelper.giveColor() == BettingColors.GREEN && !rouletteColors.contains(BettingColors.GREEN))
                rouletteColors.add(i, BettingColors.GREEN);
            else
                rouletteColors.add(i, color);
            gameInv.setItem(9 + i, rouletteColors.get(i).getItem());
            if (color == BettingColors.BLACK)
                color = BettingColors.RED;
            else if (color == BettingColors.RED)
                color = BettingColors.BLACK;
        }
        Main.getInstance().getGame().playSound(Sound.BLOCK_CHEST_OPEN, 1);


        new BukkitRunnable() {
            long time = 7L;
            final int totalRotations = random.nextInt(3) + 37;
            int speedDown = 0;
            int rotations = 0;

            @Override
            public void run() {
                if (rotations == totalRotations) {
                    Main.getInstance().getGame().nextGameState();
                    addWinningColor();
                    updateStatusItem(0);
                    this.cancel();
                }
                if (speedDown >= time) {
                    for (int i = 0; i < 8; ++i)
                        rouletteColors.set(i, rouletteColors.get(i + 1));

                    BettingColors color = rouletteColors.get(7);
                    int x = 6;
                    while (color == BettingColors.GREEN) {
                        color = rouletteColors.get(x);
                        x--;
                        if (x == 0) {
                            color = ColorHelper.giveColor();
                            break;
                        }
                    }
                    if (color == BettingColors.BLACK)
                        color = BettingColors.RED;
                    else if (color == BettingColors.RED)
                        color = BettingColors.BLACK;
                    if (ColorHelper.giveColor() == BettingColors.GREEN && !rouletteColors.contains(BettingColors.GREEN))
                        rouletteColors.set(8, BettingColors.GREEN);
                    else
                        rouletteColors.set(8, color);

                    rotations++;
                    for (int i = 0; i <= 8; ++i)
                        gameInv.setItem(9 + i, rouletteColors.get(i).getItem());

                    Main.getInstance().getGame().playSound(Sound.BLOCK_NOTE_BLOCK_SNARE, 1);

                    if (rotations >= 35)
                        time = speedDown + 7L * (rotations - 33);
                    else if (rotations >= 30)
                        time = speedDown + 7L * 2;
                    else if (rotations >= 20)
                        time = speedDown + 7L;
                }
                speedDown += 7;
                Main.getInstance().getGame().playSound(Sound.BLOCK_NOTE_BLOCK_BASS, 1);
            }

        }.runTaskTimer(Main.getInstance(), 7L, 7L);
    }

    public BettingColors getWinningColor() {
        return rouletteColors.get(4);
    }

    private void addWinningColor() {
        if (lastWinningColors.size() == 9)
            lastWinningColors.pop();
        lastWinningColors.push(getWinningColor());
        InventoryPopulate.setLastWinningColors(lastColorInv, lastWinningColors);
    }
}
