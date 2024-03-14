package net.hypixel.skyblock.item.pickaxe.vanilla;

import net.hypixel.skyblock.item.*;
import net.hypixel.skyblock.item.*;

public class WoodenPickaxe implements ToolStatistics, MaterialFunction {
    @Override
    public String getDisplayName() {
        return "Wooden Pickaxe";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public int getBaseDamage() {
        return 20;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.TOOL;
    }

    @Override
    public SpecificItemType getSpecificType() {
        return SpecificItemType.PICKAXE;
    }
}