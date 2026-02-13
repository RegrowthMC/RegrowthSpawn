package org.lushplugins.regrowthspawn;

import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.lushplugins.regrowthspawn.command.RegrowthSpawnCommand;
import org.lushplugins.regrowthspawn.config.ConfigManager;
import org.lushplugins.regrowthspawn.listener.PlayerListener;
import revxrsal.commands.bukkit.BukkitLamp;

public final class RegrowthSpawn extends SpigotPlugin {
    private static RegrowthSpawn plugin;

    private ConfigManager configManager;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager();
        this.configManager.reload();

        registerListener(new PlayerListener());

        BukkitLamp.builder(this)
            .build()
            .register(new RegrowthSpawnCommand());
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public static RegrowthSpawn getInstance() {
        return plugin;
    }
}
