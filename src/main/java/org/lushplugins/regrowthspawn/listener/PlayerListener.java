package org.lushplugins.regrowthspawn.listener;

import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.lushplugins.regrowthspawn.RegrowthSpawn;
import org.lushplugins.regrowthspawn.config.ConfigManager;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerSpawnLocation(AsyncPlayerSpawnLocationEvent event) {
        ConfigManager configManager = RegrowthSpawn.getInstance().getConfigManager();
        if (!configManager.isEnabled()) {
            return;
        }

        if (configManager.isSpawnOnJoin()) {
            event.setSpawnLocation(configManager.getLocation());
            return;
        }

        if (configManager.isSpawnOnFirstJoin() && event.isNewPlayer()) {
            event.setSpawnLocation(configManager.getLocation());
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        ConfigManager configManager = RegrowthSpawn.getInstance().getConfigManager();
        if (!configManager.isEnabled()) {
            return;
        }

        if (configManager.isSpawnOnRespawn()) {
            event.setRespawnLocation(configManager.getLocation());
            return;
        }

        if (event.getPlayer().getRespawnLocation(true) == null) {
            event.setRespawnLocation(configManager.getLocation());
        }
    }
}
