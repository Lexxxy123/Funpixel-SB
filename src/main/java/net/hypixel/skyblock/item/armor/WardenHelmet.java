package net.hypixel.skyblock.item.armor;

import net.hypixel.skyblock.item.*;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import net.hypixel.skyblock.item.*;

public class WardenHelmet implements MaterialFunction, SkullStatistics, ToolStatistics, Ability {
    @Override
    public double getBaseHealth() {
        return 300.0;
    }

    @Override
    public double getBaseDefense() {
        return 100.0;
    }

    @Override
    public String getURL() {
        return "e5eb0bd85aaddf0d29ed082eac03fcade43d0ee803b0e8162add28a6379fb54e";
    }

    @Override
    public String getDisplayName() {
        return "Warden Helmet";
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
        return SpecificItemType.HELMET;
    }

    @Override
    public String getLore() {
        return null;
    }

    @Override
    public String getAbilityName() {
        return "Brute Force";
    }

    @Override
    public String getAbilityDescription() {
        return ChatColor.GRAY + "Halves your speed but grants " + ChatColor.RED + "+20% " + ChatColor.GRAY + "weapon damage for every " + ChatColor.GREEN + "25 " + ChatColor.GRAY + "speed.";
    }

    @Override
    public int getAbilityCooldownTicks() {
        return 0;
    }

    @Override
    public AbilityActivation getAbilityActivation() {
        return AbilityActivation.NO_ACTIVATION;
    }

    @Override
    public void onAbilityUse(final Player player, final SItem sItem) {
    }

    @Override
    public int getManaCost() {
        return 0;
    }
}