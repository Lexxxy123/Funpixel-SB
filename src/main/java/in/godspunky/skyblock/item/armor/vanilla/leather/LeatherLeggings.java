package in.godspunky.skyblock.item.armor.vanilla.leather;

import in.godspunky.skyblock.item.*;
import in.godspunky.skyblock.item.*;

public class LeatherLeggings implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Leather Leggings";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
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
    public double getBaseDefense() {
        return 10.0;
    }
}