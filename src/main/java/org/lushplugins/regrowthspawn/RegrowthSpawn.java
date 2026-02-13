package org.lushplugins.regrowthspawn;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthSpawn extends JavaPlugin {
    private static RegrowthSpawn plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthSpawn getInstance() {
        return plugin;
    }
}
