package in.godspunky.skyblock.item.farming;

import in.godspunky.skyblock.skill.FarmingSkill;
import in.godspunky.skyblock.skill.Skill;
import in.godspunky.skyblock.item.ExperienceRewardStatistics;
import in.godspunky.skyblock.item.GenericItemType;
import in.godspunky.skyblock.item.MaterialFunction;
import in.godspunky.skyblock.item.Rarity;

public class RedMushroom implements ExperienceRewardStatistics, MaterialFunction {
    @Override
    public double getRewardXP() {
        return 2.0;
    }

    @Override
    public Skill getRewardedSkill() {
        return FarmingSkill.INSTANCE;
    }

    @Override
    public String getDisplayName() {
        return "Red Mushroom";
    }

    @Override
    public Rarity getRarity() {
        return Rarity.COMMON;
    }

    @Override
    public GenericItemType getType() {
        return GenericItemType.ITEM;
    }
}