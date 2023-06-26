package de.devsnx.littlegiants.listeners;

import de.devsnx.littlegiants.LittleGiants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.setJoinMessage("§7[§a+§7] §b" + event.getPlayer().getName());
        LittleGiants.getScoreboardManager().createNewScoreboard(event.getPlayer());
        LittleGiants.getScoreboardManager().updateAllScoreboards(false, true);
    }
}
