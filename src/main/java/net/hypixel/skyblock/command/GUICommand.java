package net.hypixel.skyblock.command;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import net.hypixel.skyblock.gui.GUI;
import net.hypixel.skyblock.gui.GUIType;

@CommandParameters(description = "Opens a GUI.", permission = "spt.gui.edit")
public class GUICommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        if (args.length != 1) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        final Player player = sender.getPlayer();
        final GUI gui = GUIType.valueOf(args[0].toUpperCase()).getGUI();
        gui.open(player);
    }
}