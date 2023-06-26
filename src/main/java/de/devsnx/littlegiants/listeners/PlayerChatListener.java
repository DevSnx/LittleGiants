package de.devsnx.littlegiants.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event){

        String message = event.getMessage();
        if(event.getPlayer().isOp()){
            event.setFormat("§c§lCrew §8| §7" + event.getPlayer().getName() + "§8: §7" + message);
        }else{
            event.setFormat("§b§lCrew §8| §7" + event.getPlayer().getName() + "§8: §7" + message);
        }
    }
}
