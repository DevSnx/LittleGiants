package de.devsnx.littlegiants.utils;

import org.bukkit.ChatColor;

/**
 * @author DevSnx
 * @since 26.06.2023
 */
public class Utils {

    public static String formatTime(int secs) {
        int remainder = (secs/20) % 86400;

        int hours = remainder / 3600;
        int minutes = (remainder / 60) - (hours * 60);
        int seconds = (remainder % 3600) - (minutes * 60);

         if (hours > 0) {
            return ChatColor.translateAlternateColorCodes('&', "&d" + hours + " &7Stunden &b" + minutes + " &7Minuten");
        } else if (minutes > 0) {
            return ChatColor.translateAlternateColorCodes('&', "&b" + minutes + " &7Minuten");
        } else {
            return String.valueOf("§b" + seconds + " §fs");
        }
    }
}