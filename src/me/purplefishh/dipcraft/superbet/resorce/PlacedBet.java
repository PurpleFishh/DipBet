package me.purplefishh.dipcraft.superbet.resorce;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class PlacedBet {
    @Getter
    private Player player;
    @Getter
    private BettingColors color;
    @Getter
    @Setter
    private double amount;
    @Getter
    @Setter
    private boolean placed;

    public PlacedBet(Player player, BettingColors color) {
        amount = 0;
        placed = false;
        this.color = color;
        this.player = player;
    }

    public void addToAmount(double amount) {
        if (this.amount + amount < 0)
            this.amount = 0;
        else
            this.amount += amount;
    }
}
