package net.hypixel.skyblock.command;


import java.io.File;
import java.io.IOException;

import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.command.CommandParameters;
import net.hypixel.skyblock.command.CommandSource;
import net.hypixel.skyblock.command.SCommand;
import net.hypixel.skyblock.features.ranks.PlayerRank;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

@CommandParameters(description = "unban player", aliases = "unban", permission = PlayerRank.MOD)

public class Unban extends SCommand {
    @Override
    public void run(CommandSource sender, String[] args) {
            if (args.length >= 1) {
                Player target = Bukkit.getPlayerExact(args[0]);
                File playerfile = new File(((SkyBlock) SkyBlock.getPlugin(SkyBlock.class)).getDataFolder() + File.separator, "punishments.yml");
                YamlConfiguration loadConfiguration = YamlConfiguration.loadConfiguration(playerfile);
                String uuid = null;
                if (target != null) {
                    uuid = target.getPlayer().getUniqueId().toString();
                }
                if (uuid == null) {
                    for (String key : loadConfiguration.getKeys(false)) {
                        if (loadConfiguration.getString(String.valueOf(key) + ".name").equalsIgnoreCase(args[0])) {
                            uuid = key;
                        }
                    }
                }
                if (uuid == null) {
                    sender.send("§cPlayer does not exist.");

                } else if (loadConfiguration.contains(uuid)) {
                    if (loadConfiguration.getBoolean(String.valueOf(uuid) + ".ban.isbanned")) {
                        try {
                            loadConfiguration.set(String.valueOf(uuid) + ".ban.isbanned", false);
                            loadConfiguration.set(String.valueOf(uuid) + ".ban.reason", "");
                            loadConfiguration.set(String.valueOf(uuid) + ".ban.length", 0);
                            loadConfiguration.set(String.valueOf(uuid) + ".ban.id", "");
                            loadConfiguration.save(playerfile);
                            if (target != null) {
                                sender.send("§aUnbanned " + Bukkit.getPlayer(args[0]).getName());
                            } else {
                                sender.send("§aUnbanned " + args[0]);
                            }

                        } catch (IOException exception) {
                            exception.printStackTrace();

                        }
                    }
                    sender.send("§cPlayer is not banned!");

                }
            }
            sender.send("§cInvalid syntax. Correct: /unban <name>");

    }
}