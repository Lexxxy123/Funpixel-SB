package in.godspunky.skyblock.command;

import in.godspunky.skyblock.ranks.PlayerRank;
import in.godspunky.skyblock.util.Sputnik;
import org.bukkit.ChatColor;

@CommandParameters(description = "The main command for SkySimEngine.", aliases = "ssei", permission = PlayerRank.ADMIN)
public class SkySimEngineCommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        this.send(ChatColor.RED + "Sky" + ChatColor.GREEN + "Sim" + ChatColor.GOLD + " Engine v" + SkySimEngineCommand.plugin.getDescription().getVersion());
        this.send(ChatColor.GREEN + "Created by" + ChatColor.GOLD + " GiaKhanhVN " + ChatColor.GREEN + "for SkySim Usage and the \ncompatibility with highly modified version of Spigot!");
        this.send(Sputnik.trans("&aSome of the data structures and logic have been taken from &6super&a's (superischroma) project, thank you super, very cool!"));
    }
}