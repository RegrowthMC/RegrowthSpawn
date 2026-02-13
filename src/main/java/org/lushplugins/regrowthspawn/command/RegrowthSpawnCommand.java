package org.lushplugins.regrowthspawn.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.lushplugins.regrowthspawn.RegrowthSpawn;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Subcommand;
import revxrsal.commands.annotation.Switch;
import revxrsal.commands.bukkit.actor.BukkitCommandActor;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@Command({"regrowthspawn", "spawn"})
@SuppressWarnings("unused")
public class RegrowthSpawnCommand {

    @Command("spawn")
    public void spawn(BukkitCommandActor actor) {
        if (!RegrowthSpawn.getInstance().getConfigManager().isEnabled()) {
            actor.sender().sendMessage(Component.text()
                .content("No spawn location has been set")
                .color(TextColor.fromHexString("#ff6969"))
                .build());
            return;
        }

        actor.requirePlayer().teleportAsync(RegrowthSpawn.getInstance().getConfigManager().getLocation());

        actor.sender().sendActionBar(Component.text()
            .content("Teleported to Spawn")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Subcommand("set")
    @CommandPermission("regrowthspawn.set")
    public void setSpawn(BukkitCommandActor actor, @Switch boolean precise) {
        RegrowthSpawn.getInstance().getConfigManager().setLocation(actor.requirePlayer().getLocation(), precise);

        actor.sender().sendMessage(Component.text()
            .content("Spawn location has been set!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }

    @Subcommand("reload")
    @CommandPermission("regrowthspawn.reload")
    public void reload(CommandSender sender) {
        RegrowthSpawn.getInstance().getConfigManager().reload();

        sender.sendMessage(Component.text()
            .content("RegrowthSpawn reloaded!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
