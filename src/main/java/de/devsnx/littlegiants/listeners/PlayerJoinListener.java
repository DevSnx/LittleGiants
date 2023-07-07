package de.devsnx.littlegiants.listeners;

import de.devsnx.littlegiants.LittleGiants;
import org.bukkit.Sound;
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

        event.getPlayer().setPlayerListHeaderFooter(" \n §5§lL§7ittle§b§lG§7iants §8- §a§lModpack Server §4❤ \n",
                " \n §5Discord§7: §bdiscord.gg/cGyUPHwhyQ §7- §dv1.0.3 \n");

        event.getPlayer().sendMessage("");
        event.getPlayer().sendMessage("§7Willkommen §5" + event.getPlayer().getName() + "§7! §7Alle deine Mods sind auf der §aaktuellen §7Version!");
        event.getPlayer().sendMessage("§7Du kannst das Scoreboard rechts mit §5/scoreboard §7<§aon§7/§coff§7> ein oder ausschalten :)");
        event.getPlayer().sendMessage("");
        event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F ,1.0F);
    }
}
