package me.purplefishh.dipcraft.superbet.gameTimers;

import lombok.Getter;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import me.purplefishh.dipcraft.superbet.configCollections.MessagesCollection;
import me.purplefishh.dipcraft.superbet.game.GameStatus;
import me.purplefishh.dipcraft.superbet.game.IGame;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import static me.purplefishh.dipcraft.superbet.helpers.TextHelper.replaceConvertTime;

public class GameStarting extends BukkitRunnable {

    private final IGame game;
    private final Player player;
    @Getter
    private int time = ConfigCollection.getInstance().time;

    public GameStarting(IGame game, Player player) {
        this.game = game;
        this.player = player;
    }

    @Override
    public void run() {
        game.getBoard(player).updateStatusItem(time);
        if (time == 0) {
            //Rotire.InvRotire(Main.inv, p);
            game.nextGameState();
            game.broadcastMessage(MessagesCollection.getInstance().start);
            this.cancel();
        }
        int min = 0, sec = 0;
        if (time >= 60)
            min = time / 60;
        if (time % 60 != 0)
            sec = time - min * 60;

        if ((time % 30 == 0 && time >= 30) ||
                (time % 10 == 0 && time > 0 && time < 30))
            game.broadcastMessage(MessagesCollection.getInstance().start_in_time(min, sec));

        if (time < 10 && time > 0) {
            game.broadcastMessage(MessagesCollection.getInstance().start_in_time(min, sec));
            game.playSound(Sound.BLOCK_NOTE_BLOCK_PLING, 1);
        }
        time--;
    }
}
