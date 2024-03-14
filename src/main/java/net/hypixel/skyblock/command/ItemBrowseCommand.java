package net.hypixel.skyblock.command;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import net.hypixel.skyblock.gui.ItemBrowserGUI;

@CommandParameters(description = "Browse from a catalog of items.", aliases = "browseitem,browseitems,browsei,bi,ib", permission = "spt.item")
public class ItemBrowseCommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        final Player player = sender.getPlayer();
        String query = "";
        if (args.length >= 1) {
            query = StringUtils.join(args);
        }
        new ItemBrowserGUI(query).open(player);
    }
}