package de.devsnx.littlegiants.listeners;

import de.devsnx.littlegiants.LittleGiants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        event.setQuitMessage("§7[§c-§7] §b" + event.getPlayer().getName());
        LittleGiants.getScoreboardManager().removePlayerScoreboard(event.getPlayer());
        LittleGiants.getScoreboardManager().updateAllScoreboards(false, true);
    }
}
