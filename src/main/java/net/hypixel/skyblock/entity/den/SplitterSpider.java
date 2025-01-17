package net.hypixel.skyblock.entity.den;

import org.bukkit.entity.Entity;
import net.hypixel.skyblock.entity.SEntity;
import net.hypixel.skyblock.entity.SEntityType;

public class SplitterSpider extends BaseSpider {
    @Override
    public String getEntityName() {
        return "Splitter Spider";
    }

    @Override
    public double getEntityMaxHealth() {
        return 180.0;
    }

    @Override
    public double getDamageDealt() {
        return 30.0;
    }
    
    @Override
    public int mobLevel() {
        return 3;
    }

    @Override
    public double getXPDropped() {
        return 9.7;
    }

    @Override
    public void onDeath(final SEntity sEntity, final Entity killed, final Entity damager) {
        super.onDeath(sEntity, killed, damager);
        for (int i = 0; i < 2; ++i) {
            new SEntity(sEntity.getEntity(), SEntityType.SILVERFISH);
        }
    }
}
