package in.godspunky.skyblock.command;

import in.godspunky.skyblock.ranks.PlayerRank;
import in.godspunky.skyblock.user.User;
import in.godspunky.skyblock.util.SUtil;
import org.bukkit.command.ConsoleCommandSender;
import in.godspunky.skyblock.collection.ItemCollection;

@CommandParameters(description = "Modify your collections.", permission = PlayerRank.ADMIN)
public class CollectionsCommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        if (args.length != 3) {
            throw new CommandArgumentException();
        }
        if (sender instanceof ConsoleCommandSender) {
            throw new CommandFailException("Console senders cannot use this command!");
        }
        final ItemCollection collection = ItemCollection.getByIdentifier(args[0]);
        if (collection == null) {
            throw new CommandFailException("Could not find the specified collection!");
        }
        final User user = sender.getUser();
        final int amount = Integer.parseInt(args[2]);
        final String lowerCase = args[1].toLowerCase();
        switch (lowerCase) {
            case "add":
                user.addToCollection(collection, amount);
                this.send("You have added " + SUtil.commaify(amount) + " to your " + collection.getName() + " collection.");
                return;
            case "subtract":
            case "sub":
                user.setCollection(collection, user.getCollection(collection) - amount);
                this.send("You have subtracted " + SUtil.commaify(amount) + " from your " + collection.getName() + " collection.");
                return;
            case "set":
                user.setCollection(collection, amount);
                this.send("You have set your " + collection.getName() + " collection to " + SUtil.commaify(amount) + ".");
                return;
            default:
                throw new CommandArgumentException();
        }
    }
}