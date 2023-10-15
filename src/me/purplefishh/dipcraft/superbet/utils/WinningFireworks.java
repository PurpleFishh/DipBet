package me.purplefishh.dipcraft.superbet.utils;

import me.purplefishh.dipcraft.superbet.Main;
import me.purplefishh.dipcraft.superbet.configCollections.ConfigCollection;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.metadata.FixedMetadataValue;

public class WinningFireworks {
    public static void firework(Player p) {
        if (!ConfigCollection.getInstance().fireworks)
            return;
        Location loc = p.getLocation();

        Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fw.setMetadata("nodamage_fw_superbet", new FixedMetadataValue(Main.getInstance(), true));

        fwm.setPower(0);
        fwm.addEffect(FireworkEffect.builder().withColor(Color.WHITE).flicker(true).with(FireworkEffect.Type.BALL_LARGE).build());
        fw.setFireworkMeta(fwm);

        fwm.addEffect(FireworkEffect.builder().withColor(Color.AQUA).flicker(true).with(FireworkEffect.Type.BURST).build());
        fw.setFireworkMeta(fwm);

    }
}
