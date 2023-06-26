package de.devsnx.littlegiants;

import de.devsnx.littlegiants.listeners.PlayerChatListener;
import de.devsnx.littlegiants.listeners.PlayerJoinListener;
import de.devsnx.littlegiants.listeners.PlayerQuitListener;
import de.devsnx.littlegiants.listeners.ServerPingListener;
import de.devsnx.littlegiants.manager.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class LittleGiants extends JavaPlugin {
    public static LittleGiants instance;
    public static ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        instance = this;
        scoreboardManager = new ScoreboardManager();

        PluginManager load = Bukkit.getPluginManager();
        load.registerEvents(new ServerPingListener(), this);
        load.registerEvents(new PlayerJoinListener(), this);
        load.registerEvents(new PlayerQuitListener(), this);
        load.registerEvents(new PlayerChatListener(), this);

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static LittleGiants getInstance() {
        return instance;
    }

    public static ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}