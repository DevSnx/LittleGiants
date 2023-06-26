package de.devsnx.littlegiants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class ServerPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent event){
        event.setMotd("§b§lL§7ittle§b§lG§7iants §8- §a§lModpack Server!");
        event.setMaxPlayers(128);
    }
}
