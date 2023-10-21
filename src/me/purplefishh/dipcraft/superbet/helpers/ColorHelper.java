package me.purplefishh.dipcraft.superbet.helpers;

import me.purplefishh.dipcraft.superbet.resorce.BettingColors;

import java.util.Random;

public class ColorHelper {
    private static final Random randomizer = new Random();

    /**
     * Generate a color for the roulette and returns the color form an enum <br>
     * The color order in the enum are <br>
     * 0 = black    <br>
     * 1 = red      <br>
     * 2 = green    <br>
     *
     * @return the code of the generated color
     */
    public static BettingColors giveColor() {
        int randInt = randomizer.nextInt(100);
        if (randInt <= 45)
            return BettingColors.BLACK;
        if (randInt < 55) //  45 < randInt < 55 -> Green
            return BettingColors.GREEN;
        return BettingColors.RED;
    }
}
