package net.hypixel.skyblock.entity.dimoon;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.item.SItem;
import net.hypixel.skyblock.item.SMaterial;
import net.hypixel.skyblock.user.User;
import net.hypixel.skyblock.api.block.BlockFallAPI;
import net.hypixel.skyblock.util.SUtil;
import net.hypixel.skyblock.util.Sputnik;

import java.util.List;
import java.util.UUID;

public class SummoningSequence {
    public UUID[] __qch__;
    double[] di;
    double[] em;
    double[] gl;
    double[] rb;
    public static double[] wl;
    private final SkyBlock plugin;
    private boolean acE;
    private boolean acD;
    private boolean acR;
    private boolean acG;
    private boolean bossSpawning;
    private boolean bossSpawned;
    private final World w;

    public SummoningSequence(World w) {
        this.__qch__ = new UUID[8];
        this.di = new double[]{234735.5, 191.0, 236483.5};
        this.em = new double[]{234666.5, 191.0, 236549.5};
        this.gl = new double[]{234600.5, 191.0, 236480.5};
        this.rb = new double[]{234672.5, 191.0, 236414.5};
        this.plugin = SkyBlock.getPlugin();
        this.acE = false;
        this.acD = false;
        this.acR = false;
        this.acG = false;
        this.bossSpawning = false;
        this.bossSpawned = false;
        this.w = w;
        new BukkitRunnable() {
            public void run() {
                if (SummoningSequence.this.bossSpawned) {
                    this.cancel();
                    return;
                }
                if (SummoningSequence.this.acE && SummoningSequence.this.acR && SummoningSequence.this.acD && SummoningSequence.this.acG && !SummoningSequence.this.bossSpawning) {
                    SummoningSequence.this.bossSpawning = true;
                    SummoningSequence.this.play();
                }
                if (SummoningSequence.this.acE) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.em[0], SummoningSequence.this.em[1] + 1.0, SummoningSequence.this.em[2]), 74.0f, 209.0f, 29.0f);
                }
                if (SummoningSequence.this.acR) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.rb[0], SummoningSequence.this.rb[1] + 1.0, SummoningSequence.this.rb[2]), 212.0f, 46.0f, 38.0f);
                }
                if (SummoningSequence.this.acG) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.gl[0], SummoningSequence.this.gl[1] + 1.0, SummoningSequence.this.gl[2]), 222.0f, 205.0f, 58.0f);
                }
                if (SummoningSequence.this.acD) {
                    SummoningSequence.this.gemParticle(new Location(w, SummoningSequence.this.di[0], SummoningSequence.this.di[1] + 1.0, SummoningSequence.this.di[2]), 58.0f, 222.0f, 216.0f);
                }
            }
        }.runTaskTimer(this.plugin, 0L, 1L);
        new BukkitRunnable() {
            public void run() {
                if (SummoningSequence.this.bossSpawned) {
                    this.cancel();
                    return;
                }
                if (SummoningSequence.this.acE) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.em[0], SummoningSequence.this.em[1] + 1.0, SummoningSequence.this.em[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 74.0f, 209.0f, 29.0f);
                }
                if (SummoningSequence.this.acR) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.rb[0], SummoningSequence.this.rb[1] + 1.0, SummoningSequence.this.rb[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 212.0f, 46.0f, 38.0f);
                }
                if (SummoningSequence.this.acG) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.gl[0], SummoningSequence.this.gl[1] + 1.0, SummoningSequence.this.gl[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 222.0f, 205.0f, 58.0f);
                }
                if (SummoningSequence.this.acD) {
                    SummoningSequence.this.activeBeam(new Location(w, SummoningSequence.this.di[0], SummoningSequence.this.di[1] + 1.0, SummoningSequence.this.di[2]), new Location(w, wl[0], wl[1] + 1.0, wl[2]), 58.0f, 222.0f, 216.0f);
                }
            }
        }.runTaskTimerAsynchronously(this.plugin, 0L, 6L);
    }

    void play() {
        SUtil.broadcastWorld(Sputnik.trans("&b✬&c✬&e✬&a✬ &cAll the crystals have been activated, Dimoon's &4cursed seal &cis slowly tearing down, Brace yourselves!"), this.w);
        new Location(this.w, wl[0], wl[1] + 1.0, wl[2]).getWorld().playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_SPAWN, 50.0f, 0.7f);
        SUtil.delay(() -> {
            new Location(this.w, wl[0], wl[1] + 1.0, wl[2]).getWorld().playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ENDERDRAGON_DEATH, 50.0f, -2.0f);
            SUtil.delay(() -> {
                this.playBossSpawnAnimation();
                this.w.strikeLightningEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]));
            }, 90L);
        }, 40L);
        new BukkitRunnable() {
            int i = 0;

            public void run() {
                ++this.i;
                if (0 < this.i) {
                    this.cancel();
                    return;
                }
                for (int i = 0; 40 > i; ++i) {
                    SummoningSequence.this.w.spigot().playEffect(new Location(SummoningSequence.this.w, wl[0], wl[1] + 1.0, wl[2]).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(0.0, 1.0), SUtil.random(-0.5, 0.5)), Effect.LARGE_SMOKE, 21, 0, 0.1f, 0.0f, 0.1f, 0.01f, 1, 30);
                    SummoningSequence.this.w.spigot().playEffect(new Location(SummoningSequence.this.w, wl[0], wl[1] + 1.0, wl[2]).clone().add(SUtil.random(-0.5, 0.5), SUtil.random(0.0, 1.0), SUtil.random(-0.5, 0.5)), Effect.POTION_SWIRL, 0, 1, 0.68235296f, 0.1882353f, 0.6901961f, 1.0f, 0, 640);
                }
            }
        }.runTaskTimerAsynchronously(this.plugin, 0L, 1L);
    }

    void playBossSpawnAnimation() {
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
        this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
        new BukkitRunnable() {
            public void run() {
                if (!SummoningSequence.this.bossSpawning) {
                    this.cancel();
                    return;
                }
                Location startPoint = new Location(SummoningSequence.this.w, wl[0] - 2.5, wl[1] + 1.0, wl[2] - 2.52);
                for (int i = 0; 30 > i; ++i) {
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-5, 5), SUtil.random(0, 5), SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.2901961f, 0.81960785f, 0.11372549f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-5, 5), SUtil.random(0, 5), SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.83137256f, 0.18039216f, 0.14901961f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-5, 5), SUtil.random(0, 5), SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.87058824f, 0.8039216f, 0.22745098f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-5, 5), SUtil.random(0, 5), SUtil.random(-5, 5)), Effect.COLOURED_DUST, 0, 1, 0.22745098f, 0.87058824f, 0.84705883f, 1.0f, 0, 640);
                    startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-5, 5), SUtil.random(0, 5), SUtil.random(-5, 5)), Effect.WITCH_MAGIC, 0, 1, 1.0f, 1.0f, 1.0f, 0.0f, 0, 64);
                }
            }
        }.runTaskTimer(this.plugin, 0L, 15L);
        SUtil.delay(() -> {
            Sputnik.pasteSchematicRep("egg1", true, 234666.0f, 155.0f, 236479.0f, this.w);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
        }, 10L);
        SUtil.delay(() -> {
            Sputnik.pasteSchematicRep("egg2", true, 234666.0f, 155.0f, 236479.0f, this.w);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
        }, 110L);
        SUtil.delay(() -> {
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
            this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
            List<Block> b = Sputnik.pasteSchematicRep("egg3", true, 234666.0f, 155.0f, 236479.0f, this.w);
            SUtil.delay(() -> this.plugin.sq.bossSpawned = true, 50L);
            SUtil.delay(() -> {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.EXPLODE, 10.0f, 0.0f);
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_DEATH, 10.0f, 2.0f);
                this.w.playEffect(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Effect.EXPLOSION_HUGE, 0);
                for (int i = 0; i < b.size(); ++i) {
                    int random = SUtil.random(0, 3);
                    double range = 0.0;
                    Location loc = new Location(this.w, wl[0], wl[1] + 1.0, wl[2]);
                    loc.setYaw(SUtil.random(0, 360));
                    if (1 == random) {
                        range = 0.8;
                    }
                    if (2 == random) {
                        range = 0.9;
                    }
                    if (3 == random) {
                        range = 1.1;
                    }
                    Vector vec = loc.getDirection().normalize().multiply(range);
                    vec.setY(0.9);
                    int j = i;
                    Location bloc = b.get(j).getLocation();
                    Material type = b.get(j).getType();
                    byte data = b.get(j).getData();
                    SUtil.delay(() -> BlockFallAPI.sendVelocityBlock(bloc, type, data, this.w, 60, vec), 1L);
                    b.get(i).setType(Material.AIR);
                }
                SUtil.delay(() -> {
                    this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_WOODBREAK, 10.0f, 0.5f);
                    this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.WITHER_SPAWN, 10.0f, 1.2f);
                    Dimoon.spawnDimoon();
                    this.plugin.sq.bossSpawned = true;
                    this.plugin.sq.bossSpawning = false;
                }, 1L);
            }, 100L);
        }, 210L);
    }

    void activeBeam(Location startPoint, Location endPoint, float r, float g, float b) {
        Location blockLocation = endPoint;
        Location crystalLocation = startPoint;
        Vector vector = blockLocation.clone().toVector().subtract(crystalLocation.clone().toVector());
        for (int count = 120, i = 1; i <= count; ++i) {
            startPoint.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply(i / (double) count)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
            startPoint.getWorld().spigot().playEffect(crystalLocation.clone().add(vector.clone().multiply(i / (double) count)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
        }
    }

    void gemParticle(Location startPoint, float r, float g, float b) {
        startPoint.add(0.0, -3.0, 0.0);
        for (int i = 0; 30 > i; ++i) {
            startPoint.getWorld().spigot().playEffect(startPoint.clone().add(SUtil.random(-2.5, 2.5), SUtil.random(-6, 3), SUtil.random(-2.5, 2.5)), Effect.COLOURED_DUST, 0, 1, r / 255.0f, g / 255.0f, b / 255.0f, 1.0f, 0, 640);
        }
    }

    void checkSlots() {
        if (this.acD != (null != this.__qch__[0] && null != this.__qch__[1])) {
            if (!this.acD) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&b✬ Sapphire Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&b✬ Sapphire Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acR != (null != this.__qch__[6] && null != this.__qch__[7])) {
            if (!this.acR) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&c✬ Ruby Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&c✬ Ruby Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acE != (null != this.__qch__[2] && null != this.__qch__[3])) {
            if (!this.acE) {
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
                SUtil.broadcastWorld(Sputnik.trans("&a✬ Jade Crystal &ehave been empowered and activated!"), this.w);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&a✬ Jade Crystal &ede-activated!"), this.w);
            }
        }
        if (this.acG != (null != this.__qch__[4] && null != this.__qch__[5])) {
            if (!this.acG) {
                SUtil.broadcastWorld(Sputnik.trans("&6✬ Topaz Crystal &ehave been empowered and activated!"), this.w);
                this.w.playSound(new Location(this.w, wl[0], wl[1] + 1.0, wl[2]), Sound.ZOMBIE_UNFECT, 10.0f, 1.2f);
            } else {
                SUtil.broadcastWorld(Sputnik.trans("&6✬ Topaz Crystal &ede-activated!"), this.w);
            }
        }
        this.acD = (null != this.__qch__[0] && null != this.__qch__[1]);
        this.acE = (null != this.__qch__[2] && null != this.__qch__[3]);
        this.acG = (null != this.__qch__[4] && null != this.__qch__[5]);
        this.acR = (null != this.__qch__[6] && null != this.__qch__[7]);
    }

    public void interactCatalyst(Player p, int slot, boolean rightClick) {
        if (this.haveCatal(p) && SkyBlock.getPlugin().altarCooldown) {
            p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
            p.sendMessage(Sputnik.trans("&cYou cannot use the altar right now! Let it cool down for a while!"));
            return;
        }
        if (this.bossSpawning || this.bossSpawned) {
            if (this.__qch__[slot] == p.getUniqueId()) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                p.sendMessage(Sputnik.trans("&cYou cannot recover your &aCatalyst&c once the boss spawned!"));
            }
            return;
        }
        if (!this.bossSpawning && !this.bossSpawned && -1 != slot) {
            this.checkSlots();
            if (this.haveCatal(p) && null == this.__qch__[slot]) {
                this.__qch__[slot] = p.getUniqueId();
                p.setItemInHand(null);
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
                p.sendMessage(Sputnik.trans("&4☬ &cYou placed a &aCatalyst&c!"));
                SUtil.broadcastWorld(Sputnik.trans("&4☬ &b" + p.getName() + " &cplaced a Catalyst! &7(&e" + this.catalystInTheAltar() + "&7/&a8&7)"), p.getWorld());
                this.checkSlots();
            } else if (this.haveCatal(p) && null != this.__qch__[slot]) {
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                p.sendMessage(Sputnik.trans("&cThis slot is already occupied!"));
            } else if (null != this.__qch__[slot]) {
                this.checkSlots();
                if (this.__qch__[slot] == p.getUniqueId()) {
                    this.__qch__[slot] = null;
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 2.0f);
                    p.sendMessage(Sputnik.trans("&4☬ &cYou recovered back your &aCatalyst&7!"));
                    Sputnik.smartGiveItem(SItem.of(SMaterial.HIDDEN_DIMOON_GEM).getStack(), p);
                    this.countDown(p);
                    this.checkSlots();
                } else {
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 0.0f);
                    p.sendMessage(Sputnik.trans("&cThis is not your &aCatalyst&c, duh."));
                }
                this.checkSlots();
            }
        }
    }

    public void pickupAllCatalysts(Player p) {
        for (int i = 0; i < this.__qch__.length; ++i) {
            if (this.__qch__[i] == p.getUniqueId()) {
                this.__qch__[i] = null;
                Sputnik.smartGiveItem(SItem.of(SMaterial.HIDDEN_DIMOON_GEM).getStack(), p);
            }
        }
        this.checkSlots();
    }

    public int catalystInTheAltar() {
        int j = 0;
        for (int i = 0; i < this.__qch__.length; ++i) {
            j += ((null != this.__qch__[i]) ? 1 : 0);
        }
        return j;
    }

    boolean haveCatal(Player p) {
        return null != this.getItemInHand(p) && SMaterial.HIDDEN_DIMOON_GEM == this.getItemInHand(p).getType();
    }

    SItem getItemInHand(Player p) {
        return SItem.find(p.getItemInHand());
    }

    void countDown(Player p) {
        User.getUser(p.getUniqueId()).setCooldownAltar(15);
        new BukkitRunnable() {
            public void run() {
                if (0 >= User.getUser(p.getUniqueId()).getCooldownAltar()) {
                    User.getUser(p.getUniqueId()).setCooldownAltar(0);
                    this.cancel();
                    return;
                }
                User.getUser(p.getUniqueId()).setCooldownAltar(User.getUser(p.getUniqueId()).getCooldownAltar() - 1);
            }
        }.runTaskTimerAsynchronously(this.plugin, 0L, 20L);
    }

    public void setAcE(boolean acE) {
        this.acE = acE;
    }

    public boolean isAcE() {
        return this.acE;
    }

    public void setAcD(boolean acD) {
        this.acD = acD;
    }

    public boolean isAcD() {
        return this.acD;
    }

    public void setAcR(boolean acR) {
        this.acR = acR;
    }

    public boolean isAcR() {
        return this.acR;
    }

    public void setAcG(boolean acG) {
        this.acG = acG;
    }

    public boolean isAcG() {
        return this.acG;
    }

    public void setBossSpawning(boolean bossSpawning) {
        this.bossSpawning = bossSpawning;
    }

    public boolean isBossSpawning() {
        return this.bossSpawning;
    }

    public void setBossSpawned(boolean bossSpawned) {
        this.bossSpawned = bossSpawned;
    }

    public boolean isBossSpawned() {
        return this.bossSpawned;
    }

    static {
        wl = new double[]{234668.5, 154.0, 236481.5};
    }
}