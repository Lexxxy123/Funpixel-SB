package in.godspunky.skyblock.command;

import in.godspunky.skyblock.ranks.PlayerRank;
import in.godspunky.skyblock.user.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

@CommandParameters(description = "bruhbu", aliases = "datarec", permission = PlayerRank.ADMIN)
public class InvRecovery extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        final Player player = sender.getPlayer();
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        final User user = sender.getUser();
        if (!player.isOp()) {
            this.send(ChatColor.RED + "No permission to execute this command!");
            return;
        }
        if (args.length == 0) {
            this.send(ChatColor.RED + "Invaild Syntax! You need to provide a player name");
            return;
        }
        final Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
            final User user2 = User.getUser(target.getUniqueId());
            try {
               // user2.loadPlayerData();
                user.send("&aSuccess!");
                user2.send("&eData Recovered, now disconnect and join back.");
            } catch (final IllegalArgumentException e) {
                e.printStackTrace();
                user.send("&cError! Check Console!");
            }
            return;
        }
        this.send(ChatColor.RED + "Invaild Syntax! You need to provide a vaild player name.");
    }
}