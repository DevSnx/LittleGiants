package de.devsnx.littlegiants;

import org.bukkit.plugin.java.JavaPlugin;

public final class LittleGiants extends JavaPlugin {
    public static LittleGiants instance;
    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static LittleGiants getInstance() {
        return instance;
    }
}