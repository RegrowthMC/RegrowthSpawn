package org.lushplugins.regrowthspawn.config;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.lushplugins.regrowthspawn.RegrowthSpawn;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConfigManager {
    private boolean enabled;
    private boolean spawnOnFirstJoin;
    private boolean spawnOnJoin;
    private boolean spawnOnRespawn;
    private Location location;

    public ConfigManager() {
        RegrowthSpawn.getInstance().saveDefaultConfig();
    }

    public void reload() {
        RegrowthSpawn.getInstance().reloadConfig();
        FileConfiguration config = RegrowthSpawn.getInstance().getConfig();

        this.enabled = config.getBoolean("enabled");
        this.spawnOnFirstJoin = config.getBoolean("spawn-on-first-join");
        this.spawnOnJoin = config.getBoolean("spawn-on-join");
        this.spawnOnRespawn = config.getBoolean("spawn-on-respawn");

        this.location = new Location(
            Bukkit.getWorld(config.getString("location.world", "world")),
            config.getDouble("location.x"),
            config.getDouble("location.y"),
            config.getDouble("location.z"),
            (float) config.getDouble("location.yaw", 0),
            (float) config.getDouble("location.pitch", 0)
        );
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isSpawnOnFirstJoin() {
        return spawnOnFirstJoin;
    }

    public boolean isSpawnOnJoin() {
        return spawnOnJoin;
    }

    public boolean isSpawnOnRespawn() {
        return spawnOnRespawn;
    }

    public Location getLocation() {
        return location;
    }

    // TODO: Check whether rounding coords would be a good idea (can add a command flag to not round)
    public void setLocation(Location location, boolean precise) {
        FileConfiguration config = RegrowthSpawn.getInstance().getConfig();


        config.set("location.world", location.getWorld() != null ? location.getWorld().getName() : "world");
        config.set("location.x", precise ? location.getX() : round(location.getX()));
        config.set("location.y", precise ? location.getY() : round(location.getY()));
        config.set("location.z", precise ? location.getZ() : round(location.getZ()));
        config.set("location.yaw", precise ? location.getYaw() : round(location.getYaw()));
        config.set("location.pitch", precise ? location.getPitch() : round(location.getPitch()));

        config.set("enabled", true);
        RegrowthSpawn.getInstance().saveConfig();

        this.enabled = true;
        this.location = location;
    }

    private double round(double num) {
        return new BigDecimal(num)
            .setScale(4, RoundingMode.HALF_UP)
            .doubleValue();
    }
}
