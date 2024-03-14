package net.hypixel.skyblock.gui;

import net.hypixel.skyblock.SkyBlock;
import net.hypixel.skyblock.features.skill.CombatSkill;
import net.hypixel.skyblock.features.skill.Skill;
import net.hypixel.skyblock.user.PlayerStatistics;
import net.hypixel.skyblock.user.PlayerUtils;
import net.hypixel.skyblock.user.User;
import net.hypixel.skyblock.util.SUtil;
import net.hypixel.skyblock.util.Sputnik;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BossMenu extends GUI {
    public static final Map<Player, Boolean> ableToJoin;
    public static final Map<Player, Boolean> cooldownCata;

    public BossMenu() {
        super("The Catacombs Demo", 36);
    }

    @Override
    public void onOpen(final GUIOpenEvent e) {
        this.fill(BLACK_STAINED_GLASS_PANE);
        final Player player = e.getPlayer();
        final User user = User.getUser(player.getUniqueId());
        final PlayerStatistics statistics = PlayerUtils.STATISTICS_CACHE.get(player.getUniqueId());
        this.set(GUIClickableItem.getCloseItem(31));
        final SkyBlock plugin = SkyBlock.getPlugin();
        this.set(new GUIClickableItem() {
            @Override
            public void run(final InventoryClickEvent e) {
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);
                GUIType.CATACOMBS_BOSS.getGUI().open((Player) e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 30;
            }

            @Override
            public ItemStack getItem() {
                String color = "&a";
                if (Sputnik.runningFloors() >= 2) {
                    color = "&e";
                } else if (Sputnik.runningFloors() > 4) {
                    color = "&c";
                }
                return SUtil.getStack(ChatColor.GREEN + "Current Server Status", Material.WATCH, (short) 0, 1, Sputnik.trans("&7Active Runs: " + color + SUtil.commaify(Sputnik.runningFloors()) + "&7/&a5"), Sputnik.trans("&7This Session total runs: &e" + SUtil.commaify(Sputnik.rf_())),  Sputnik.trans("&7Is rate limited: &cNo"), " ", ChatColor.YELLOW + "Click to refresh");
            }
        });
        this.set(new GUIClickableItem() {
            @Override
            public void run(final InventoryClickEvent e) {
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);
                GUIType.BOSS_COLLECTION.getGUI().open((Player) e.getWhoClicked());
            }

            @Override
            public int getSlot() {
                return 32;
            }

            @Override
            public ItemStack getItem() {
                return SUtil.getStack(ChatColor.GREEN + "Boss Collections", Material.BOOK_AND_QUILL, (short) 0, 1, Sputnik.trans("&7Kill demo bosses and"), Sputnik.trans("&7earn special rewards."), " ", ChatColor.YELLOW + "Click to view");
            }
        });
        this.set(new GUIClickableItem() {
            @Override
            public void run(final InventoryClickEvent e) {
                final Skill skill = CombatSkill.INSTANCE;
                final double xp = (skill != null) ? user.getSkillXP(skill) : 0.0;
                final int level = (skill != null) ? Skill.getLevel(xp, skill.hasSixtyLevels()) : 0;
                player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1.0f, 1.0f);
                e.getWhoClicked().closeInventory();
                if (level < 5 && !player.isOp()) {
                    player.sendMessage(ChatColor.RED + "You need at least Combat Level V to join a boss room!");
                    return;
                }
                if (!ableToJoin.containsKey(player)) {
                    ableToJoin.put(player, true);
                }
                if (!cooldownCata.containsKey(player)) {
                    cooldownCata.put(player, false);
                }
                if (!cooldownCata.get(player) && ableToJoin.get(player) && !player.getWorld().getName().contains("f6")) {
                    player.sendMessage(ChatColor.GREEN + "Requesting the server...");
                    Sputnik.startRoom(player);
                    ableToJoin.put(player, false);
                    cooldownCata.put(player, true);
                    SUtil.delay(() -> {
                        cooldownCata.put(player, false);
                    }, 1200L);
                } else if (!ableToJoin.get(player)) {
                    player.sendMessage(ChatColor.RED + "Cannot send request! Already requesting?");
                } else if (cooldownCata.get(player)) {
                    player.sendMessage(ChatColor.RED + "You are on cooldown! Please try again!");
                } else {
                    player.sendMessage(ChatColor.RED + "You're already playing!");
                }
            }

            @Override
            public int getSlot() {
                return 13;
            }

            @Override
            public ItemStack getItem() {
                ItemStack itemstack = null;
                String lore = "&cYou need Combat Level V to join.";
                boolean isEnough = false;
                final Skill skill = CombatSkill.INSTANCE;
                final double xp = (skill != null) ? user.getSkillXP(skill) : 0.0;
                final int level = (skill != null) ? Skill.getLevel(xp, skill.hasSixtyLevels()) : 0;
                if (level >= 5 || player.isOp()) {
                    isEnough = true;
                }
                if (isEnough) {
                    lore = "&eClick to send join request!";
                }
                if (cooldownCata.containsKey(player) && cooldownCata.get(player)) {
                    lore = "&cYou're currently on cooldown!";
                }
                itemstack = SUtil.getSkullURLStack(ChatColor.RED + "Demo Catacombs - Floor VI", "6a40675d333adcca75b35b6ba29b4fd7e5eaeb998395295385b494128715dab3", 1, Sputnik.trans("&7This floor is a &ademo floor&7,"), ChatColor.GRAY + "which mean there're no teammates, dungeon", ChatColor.GRAY + "just the F6 boss (You will solo it)", " ", Sputnik.trans("&7Boss: &cSadan"), Sputnik.trans("&8Necromancer Lord"), ChatColor.GRAY + "Necromancy was always strong in his", ChatColor.GRAY + "family. Says he once beat a", ChatColor.GRAY + "Wither in a duel. Likes to brag.", " ", Sputnik.trans("&7Requires: &bCombat Level V"), " ", Sputnik.trans(lore));
                return itemstack;
            }
        });
    }

    static {
        ableToJoin = new HashMap<>();
        cooldownCata = new HashMap<>();
    }
}