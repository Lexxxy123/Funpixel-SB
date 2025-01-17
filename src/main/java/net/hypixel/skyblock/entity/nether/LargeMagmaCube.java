package net.hypixel.skyblock.entity.nether;

import net.hypixel.skyblock.SkyBlock;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftMagmaCube;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.scheduler.BukkitRunnable;
import net.hypixel.skyblock.entity.EntityFunction;
import net.hypixel.skyblock.entity.SEntity;
import net.hypixel.skyblock.entity.SlimeStatistics;

public class LargeMagmaCube implements SlimeStatistics, EntityFunction {
    @Override
    public String getEntityName() {
        return "Magma Cube";
    }

    @Override
    public double getEntityMaxHealth() {
        return 300.0;
    }

    @Override
    public double getDamageDealt() {
        return 150.0;
    }
    
    @Override
    public int mobLevel() {
        return 9;
    }

    @Override
    public double getXPDropped() {
        return 4.0;
    }

    @Override
    public void onTarget(final SEntity sEntity, final EntityTargetLivingEntityEvent e) {
        final LivingEntity entity = (LivingEntity) e.getEntity();
        final Entity found = e.getTarget();
        new BukkitRunnable() {
            public void run() {
                if (entity.isDead()) {
                    this.cancel();
                    return;
                }
                final Entity target = ((CraftMagmaCube) entity).getHandle().getGoalTarget().getBukkitEntity();
                if (!found.equals(target)) {
                    this.cancel();
                    return;
                }
                for (int i = 0; i < 3; ++i) {
                    new BukkitRunnable() {
                        public void run() {
                            if (entity.isDead()) {
                                this.cancel();
                                return;
                            }
                            final Fireball fireball = (Fireball) entity.getWorld().spawn(entity.getEyeLocation().add(entity.getEyeLocation().getDirection().multiply(3.0)), (Class) Fireball.class);
                            fireball.setMetadata("magma", new FixedMetadataValue(SkyBlock.getPlugin(), sEntity));
                            fireball.setDirection(target.getLocation().getDirection().multiply(-1.0).normalize());
                        }
                    }.runTaskLater(SkyBlock.getPlugin(), (i + 1) * 10);
                }
            }
        }.runTaskTimer(SkyBlock.getPlugin(), 60L, 100L);
    }

    @Override
    public int getSize() {
        return 6;
    }

    @Override
    public boolean split() {
        return false;
    }
}
