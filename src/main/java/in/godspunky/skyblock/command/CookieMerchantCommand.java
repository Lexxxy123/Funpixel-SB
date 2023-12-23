package in.godspunky.skyblock.command;

import in.godspunky.skyblock.gui.GUIType;
import in.godspunky.skyblock.ranks.PlayerRank;
import in.godspunky.skyblock.user.PlayerUtils;
import in.godspunky.skyblock.util.Sputnik;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@CommandParameters(description = "Gets the NBT of your current item.", aliases = "fm", permission = PlayerRank.DEFAULT)
public class CookieMerchantCommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        final Player player = sender.getPlayer();
        if (PlayerUtils.getCookieDurationTicks(player) <= 0L) {
            this.send(Sputnik.trans("&cYou need the Cookie Buff active to use this feature!"));
            player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        GUIType.FARM_MERCHANT.getGUI().open(player);
    }
}
