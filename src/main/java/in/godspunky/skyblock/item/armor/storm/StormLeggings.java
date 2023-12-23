package in.godspunky.skyblock.item.armor.storm;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;
import in.godspunky.skyblock.item.SpecificItemType;
import in.godspunky.skyblock.item.armor.LeatherArmorStatistics;

public class StormLeggings implements MaterialFunction, LeatherArmorStatistics {
    @Override
    public double getBaseIntelligence() {
        return 250.0;
    }

    @Override
    public double getBaseHealth() {
        return 230.0;
    }

    @Override
    public double getBaseDefense() {
        return 105.0;
    }

    @Override
    public int getColor() {
        return 1550532;
    }

    @Override
    public String getDisplayName() {
        return "Storm's Leggings";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.LEGENDARY;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ARMOR;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.LEGGINGS;
    }

    @Override
    public String getLore() {
        return null;
    }
}