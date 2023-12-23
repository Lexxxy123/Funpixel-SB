package in.godspunky.skyblock.item.armor.gigachad;

import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;
import in.godspunky.skyblock.item.SpecificItemType;
import in.godspunky.skyblock.item.armor.LeatherArmorStatistics;

public class GigachadBoots implements MaterialFunction, LeatherArmorStatistics {
    @Override
    public double getBaseStrength() {
        return 100.0;
    }

    @Override
    public double getBaseCritChance() {
        return 0.1;
    }

    @Override
    public double getBaseCritDamage() {
        return 0.3;
    }

    @Override
    public double getBaseIntelligence() {
        return 5.0;
    }

    @Override
    public double getBaseMagicFind() {
        return 0.08;
    }

    @Override
    public double getBaseSpeed() {
        return 0.05;
    }

    @Override
    public double getBaseHealth() {
        return 150.0;
    }

    @Override
    public double getBaseDefense() {
        return 100.0;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public String getDisplayName() {
        return "Gigachad Boots";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.MYTHIC;
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