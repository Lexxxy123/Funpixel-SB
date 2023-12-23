package in.godspunky.skyblock.command;

import in.godspunky.skyblock.SkySimEngine;
import in.godspunky.skyblock.ranks.PlayerRank;

@CommandParameters(description = "", aliases = "ssi", permission = PlayerRank.ADMIN)
public class ServerInfoCommand extends SCommand {
    @Override
    public void run(final CommandSource sender, final String[] args) {
        this.send("&aYou're playing on &cSkySim Network &bBeta 2");
        try {
            this.send("&7Server Version: &a" + SkySimEngine.getPlugin().getServerVersion().readableString());
        } catch (final Exception e) {
            e.printStackTrace();
        }
        this.send("&7Developed by: &cGiaKhanhVN &e(Major) &7and &cKP56&7.");
        this.send("&7Server API information: &aMC1_8_R3 &7by &eMojang");
        this.send("&7Development API information: &aToiletPaperAPI &7by &ePaper &7and &eGiaKhanhVN");
        this.send("&7Packet Injector information: &ebuild-1.0.5.2 &7by &eGiaKhanhVN");
        this.send("&7SSAI informtation: &ealpha-r.3.4 &7by &eGiaKhanhVN");
    }
}
