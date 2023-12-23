package in.godspunky.skyblock.item.storage;

import in.godspunky.skyblock.item.Rarity;
import in.godspunky.skyblock.item.SkullStatistics;

public class GreaterBackpack extends Storage implements SkullStatistics {
    @Override
    public int getSlots() {
        return 36;
    }

    @Override
    public String getDisplayName() {
        return "Greater Backpack";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.EPIC;
    }

    @Override
    public String getURL() {
        return "62f3b3a05481cde77240005c0ddcee1c069e5504a62ce0977879f55a39396146";
    }
}