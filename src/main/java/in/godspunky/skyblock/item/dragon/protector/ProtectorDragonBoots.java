package in.godspunky.skyblock.item.dragon.protector;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;
import in.godspunky.skyblock.item.SpecificItemType;
import in.godspunky.skyblock.item.armor.LeatherArmorStatistics;

public class ProtectorDragonBoots implements MaterialFunction, LeatherArmorStatistics {
    @Override
    public double getBaseHealth() {
        return 60.0;
    }

    @Override
    public double getBaseDefense() {
        return 115.0;
    }

    @Override
    public int getColor() {
        return 10065803;
    }

    @Override
    public String getDisplayName() {
        return "Protector Dragon Boots";
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
        return SpecificItemType.BOOTS;
    }

    @Override
    public String getLore() {
        return null;
    }
}