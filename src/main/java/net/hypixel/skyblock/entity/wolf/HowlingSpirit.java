package net.hypixel.skyblock.entity.wolf;

import net.hypixel.skyblock.entity.EntityDrop;
import net.hypixel.skyblock.entity.EntityDropType;
import net.hypixel.skyblock.item.SMaterial;

import java.util.Arrays;
import java.util.List;

public class HowlingSpirit extends BaseWolf {
    @Override
    public String getEntityName() {
        return "Howling Spirit";
    }

    @Override
    public double getEntityMaxHealth() {
        return 7000.0;
    }

    @Override
    public double getDamageDealt() {
        return 450.0;
    }

    @Override
    public List<EntityDrop> drops() {
        return Arrays.asList(new EntityDrop(SMaterial.SPRUCE_WOOD, EntityDropType.COMMON, 0.25), new EntityDrop(SMaterial.DARK_OAK_WOOD, EntityDropType.COMMON, 0.25), new EntityDrop(SMaterial.ACACIA_WOOD, EntityDropType.COMMON, 0.25));
    }

    @Override
    public double getXPDropped() {
        return 15.0;
    }

    @Override
    public boolean isAngry() {
        return true;
    }
}