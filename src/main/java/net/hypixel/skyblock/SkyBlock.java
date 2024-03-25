package net.hypixel.skyblock;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.google.common.io.Files;
import de.slikey.effectlib.EffectManager;
import dev.demeng.sentinel.wrapper.SentinelClient;
import dev.demeng.sentinel.wrapper.exception.*;
import net.hypixel.skyblock.api.placeholder.SkyblockPlaceholder;
import net.hypixel.skyblock.api.worldmanager.SkyBlockWorldManager;
import net.hypixel.skyblock.features.auction.AuctionBid;
import net.hypixel.skyblock.features.auction.AuctionEscrow;
import net.hypixel.skyblock.features.auction.AuctionItem;
import net.hypixel.skyblock.features.calendar.SkyBlockCalendar;
import net.hypixel.skyblock.command.*;
import net.hypixel.skyblock.config.Config;
import net.hypixel.skyblock.features.enchantment.EnchantmentType;
import net.hypixel.skyblock.entity.EntityPopulator;
import net.hypixel.skyblock.entity.EntitySpawner;
import net.hypixel.skyblock.entity.SEntityType;
import net.hypixel.skyblock.entity.StaticDragonManager;
import net.hypixel.skyblock.entity.nms.VoidgloomSeraph;
import net.hypixel.skyblock.features.quest.QuestLineHandler;
import net.hypixel.skyblock.features.ranks.SetRankCommand;
import net.hypixel.skyblock.item.*;
import net.hypixel.skyblock.item.armor.VoidlingsWardenHelmet;
import net.hypixel.skyblock.item.pet.Pet;
import net.hypixel.skyblock.listener.PacketListener;
import net.hypixel.skyblock.listener.PlayerChatListener;
import net.hypixel.skyblock.listener.ServerPingListener;
import net.hypixel.skyblock.listener.WorldListener;
import net.hypixel.skyblock.features.merchant.MerchantItemHandler;
import net.hypixel.skyblock.nms.packetevents.*;
import net.hypixel.skyblock.npc.impl.SkyblockNPC;
import net.hypixel.skyblock.features.region.Region;
import net.hypixel.skyblock.features.region.RegionType;

import net.hypixel.skyblock.features.slayer.SlayerQuest;
import net.hypixel.skyblock.database.DatabaseManager;
import net.hypixel.skyblock.user.AuctionSettings;
import net.hypixel.skyblock.user.User;
import net.hypixel.skyblock.util.*;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.reflections.Reflections;
import net.hypixel.skyblock.gui.GUIListener;
import net.hypixel.skyblock.nms.nmsutil.apihelper.APIManager;
import net.hypixel.skyblock.nms.nmsutil.packetlistener.PacketHelper;
import net.hypixel.skyblock.nms.nmsutil.packetlistener.handler.PacketHandler;
import net.hypixel.skyblock.nms.nmsutil.packetlistener.handler.ReceivedPacket;
import net.hypixel.skyblock.nms.nmsutil.packetlistener.handler.SentPacket;
import net.hypixel.skyblock.nms.nmsutil.packetlistener.metrics.Metrics;
import net.hypixel.skyblock.nms.pingrep.PingAPI;
import net.hypixel.skyblock.npc.impl.SkyblockNPCManager;
import net.hypixel.skyblock.database.SQLDatabase;
import net.hypixel.skyblock.database.SQLRegionData;
import net.hypixel.skyblock.database.SQLWorldData;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class SkyBlock extends JavaPlugin implements PluginMessageListener {
    @Getter
    private static ProtocolManager protocolManager;
    @Getter
    private static SkyBlock plugin;
    private final PacketHelper packetInj;
    private boolean authenticated;
    public static final boolean dimoonEnabled = false;

    public static final String[] DEVELOPERS = {"Hamza" , "EpicPortal" , "Dumbo"};


    public boolean altarCooldown;

    public static EffectManager effectManager;
    @Getter
    private static SkyBlock instance;
    public Config config;
    public Config heads;
    public Config blocks;
    public Config spawners;

    @Getter
    private QuestLineHandler questLineHandler;
    @Setter
    @Getter
    private int onlinePlayerAcrossServers;
    public CommandMap commandMap;
    public SQLDatabase sql;
    public SQLRegionData regionData;
    public SQLWorldData worldData;
    public CommandLoader cl;
    public Repeater repeater;
    @Getter
    private BungeeChannel bc;
    @Setter
    @Getter
    private String serverName;

    public List<String> bannedUUID;



    public SkyBlock() {
        this.packetInj = new PacketHelper();
        this.altarCooldown = false;
        this.serverName = "dev";
        this.bannedUUID = Collections.singletonList("");
    }

    public void onLoad() {
        SLog.info("Loading Bukkit-serializable classes...");
        this.loadSerializableClasses();
    }

    public void onEnable() {
        plugin = this;
        sendMessage("&aEnabling Skyblock Core. Made by " + getDevelopersName());
        long start = System.currentTimeMillis();

        sendMessage("&aLoading SkyBlock worlds...");
        SkyBlockWorldManager.loadWorlds();
        sendMessage("&aLoading YAML data from disk...");
        this.config = new Config("config.yml");
        this.heads = new Config("heads.yml");
        this.blocks = new Config("blocks.yml");
        this.spawners = new Config("spawners.yml");
        authenticate();
        if (authenticated) {
            sendMessage("&aLoading Command map...");
            try {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                this.commandMap = (CommandMap) f.get(Bukkit.getServer());
            } catch (final IllegalAccessException | NoSuchFieldException e) {
                SLog.severe("Couldn't load command map: ");
                e.printStackTrace();
            }
            sendMessage("&aLoading SQL database...");
            this.sql = new SQLDatabase();
            DatabaseManager.connectToDatabase("mongodb://admin:gs%40skyblockmongo@170.205.54.29:2004/?directConnection=true&serverSelectionTimeoutMS=2000&appName=mongosh+1.6.2", "Godspunky");
            this.regionData = new SQLRegionData();
            this.worldData = new SQLWorldData();
            this.cl = new CommandLoader();



            sendMessage("&aBegin Protocol injection... (SkyBlockProtocol v0.6.2)");
            APIManager.registerAPI(this.packetInj, this);
            if (!this.packetInj.injected) {
                this.getLogger().warning("[FATAL ERROR] Protocol Injection failed. Disabling the plugin for safety...");
                Bukkit.getPluginManager().disablePlugin(this);
                return;
            }
            sendMessage("&aInjecting...");
            PingAPI.register();
            new Metrics(this);
            APIManager.initAPI(PacketHelper.class);
            sendMessage("&aStarting server loop...");
            this.repeater = new Repeater();
            VoidlingsWardenHelmet.startCounting();
            sendMessage("&aLoading commands...");
            this.loadCommands();
            sendMessage("&aLoading listeners...");
            this.loadListeners();
            sendMessage("&aInjecting Packet/Ping Listener into the core...");
            this.registerPacketListener();
            this.registerPingListener();
            sendMessage("&aStarting entity spawners...");
            EntitySpawner.startSpawnerTask();
            sendMessage("&aEstablishing player regions...");
            Region.cacheRegions();
            sendMessage("&aLoading NPCS...");
            registerNPCS();
            sendMessage("&aLoading auction items from disk...");
            effectManager = new EffectManager(this);
            AuctionItem.loadAuctionsFromDisk();
            sendMessage("&aLoading Quest!");
            initializeQuests();
            sendMessage("&aLoading merchants prices...");
            MerchantItemHandler.init();
            sendMessage("&aSynchronizing world time with calendar time and removing world entities...");
            SkyBlockCalendar.synchronize();
            sendMessage("&aLoading items...");
            SMaterial.loadItems();
            sendMessage("&aConverting CraftRecipes into custom recipes...");
            Recipe.loadRecipes();
            sendMessage("&aHooking SkyBlockEngine to PlaceholderAPI and registering...");
            if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
                new SkyblockPlaceholder().register();
                sendMessage("&aHooked to PAPI successfully!");
            } else {
                sendMessage("&aERROR! PlaceholderAPI plugin does not exist, disabing placeholder request!");
            }
            protocolManager = ProtocolLibrary.getProtocolManager();
            WorldListener.init();

            sendMessage("&aSuccessfully enabled " + this.getDescription().getFullName());
            sendMessage("&a===================================");
            sendMessage("&aSkyBlock ENGINE - MADE BY " + getDevelopersName());
            sendMessage("&aPLUGIN ENABLED! HOOKED INTO SkyBlock!");
            sendMessage("&a ");
            sendMessage("&aThis plugin provide most of SkyBlock functions!");
            sendMessage("&aOriginally was made by super");
            sendMessage("&aContinued by GodSpunky (C) 2024");
            sendMessage("&aAny illegal usage will be suppressed! DO NOT LEAK IT!");
            sendMessage("&a===================================");
           // startPopulators();
            this.getCommand("setrank").setExecutor(new SetRankCommand());

            long end = System.currentTimeMillis();
            sendMessage("&aSuccessfully enabled Hub Core in " + CC.getTimeDifferenceAndColor(start, end) + "&a.");
        } else {
            throw new NullPointerException("license is not valid or empty. Please insert license in config.yml");
        }

    }

    private void initializeQuests() {
        sendMessage("&aInitializing quests...");
        long start = System.currentTimeMillis();

        this.questLineHandler = new QuestLineHandler();

        sendMessage("&aSuccessfully registered " + ChatColor.GREEN + this.questLineHandler.getQuests().size() + ChatColor.WHITE + " quests [" + SUtil.getTimeDifferenceAndColor(start, System.currentTimeMillis()) + ChatColor.WHITE + "]");
    }





    public void onDisable() {
        sendMessage("&aSaving Player data...");

        for (User user : User.getCachedUsers()){
            if (user == null) continue;
            if (user.getUuid() == null) continue;
            if (!user.toBukkitPlayer().isOnline()) continue;
            user.save().thenRun(user::kick);
        }


        sendMessage("&aKilling all non-human entities...");
        for (final World world : Bukkit.getWorlds()) {
            for (final Entity entity : world.getEntities()) {
                if (entity instanceof HumanEntity) {
                    continue;
                }
                entity.remove();
            }
        }
        if (this.repeater != null && EntitySpawner.class != null && EntitySpawner.class != null && StaticDragonManager.class != null && SkyBlockCalendar.class != null) {
            sendMessage("&aStopping server loop...");
            this.repeater.stop();
            sendMessage("&aUnloading ores from Dwarven Mines...");
            WorldListener.unloadBlocks();
            sendMessage("&aEjecting protocol channel...");
            APIManager.disableAPI(PacketHelper.class);
            sendMessage("&aCleaning HashSets...");
            for (final Map.Entry<Entity, Block> entry : VoidgloomSeraph.CACHED_BLOCK.entrySet()) {
                final Entity stand = entry.getKey();
                if (stand != null && VoidgloomSeraph.CACHED_BLOCK.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_ID.containsKey(stand) && VoidgloomSeraph.CACHED_BLOCK_DATA.containsKey(stand)) {
                    VoidgloomSeraph.CACHED_BLOCK.get(stand).getLocation().getBlock().setTypeIdAndData(VoidgloomSeraph.CACHED_BLOCK_ID.get(stand), VoidgloomSeraph.CACHED_BLOCK_DATA.get(stand), true);
                }
            }
            //this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
            //this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
            sendMessage("&aStopping entity spawners...");
            EntitySpawner.stopSpawnerTask();
            sendMessage("&aEnding Dragons fight... (If one is currently active)");
            StaticDragonManager.endFight();
            sendMessage("&aSaving calendar time...");
            SkyBlockCalendar.saveElapsed();
            sendMessage("&aSaving auction data...");
            for (final AuctionItem item : AuctionItem.getAuctions()) {
                item.save();
            }
            plugin = null;
        }
        sendMessage("&aDisabled " + this.getDescription().getFullName());
        sendMessage("&a===================================");
        sendMessage("&aSkyBlock ENGINE - MADE BY " + getDevelopersName());
        sendMessage("&aPLUGIN DISABLED!");
        sendMessage("&a===================================");
    }

    private void registerNPCS()
    {
        Reflections reflections = new Reflections("net.hypixel.skyblock.npc");
        for (Class<? extends SkyblockNPC> npcClazz : reflections.getSubTypesOf(SkyblockNPC.class)){
            try {
                npcClazz.getDeclaredConstructor().newInstance();
            }catch (Exception ex){
                ex.printStackTrace();

            }
        }
        sendMessage("&aSuccessfully loaded &e" + SkyblockNPCManager.getNPCS().size() + "&a NPCs");
    }




    private void loadCommands() {
        Reflections reflections = new Reflections("net.hypixel.skyblock.command");
        sendMessage("&eRegistering commands...");
        int count = 0;

        for (Class<? extends SCommand> command : reflections.getSubTypesOf(SCommand.class)) {
            try {
                cl.register(command.getDeclaredConstructor().newInstance());
                count++;
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                     InvocationTargetException exception) {
                SLog.severe("An exception occured when loading " + command.getSimpleName());
                SLog.severe(exception.getMessage());
            }
        }
        sendMessage("&eRegistered " + count + " commands");
    }

    private void loadListeners() {
        new net.hypixel.skyblock.listener.BlockListener();
        new net.hypixel.skyblock.listener.PlayerListener();
        new ServerPingListener();
        new ItemListener();
        new GUIListener();
        new PacketListener();
        new WorldListener();
        new PlayerChatListener();
    }

    private void startPopulators() {
        new EntityPopulator(20, 30, 200L, SEntityType.ENCHANTED_DIAMOND_SKELETON, RegionType.OBSIDIAN_SANCTUARY).start();
        new EntityPopulator(20, 30, 200L, SEntityType.ENCHANTED_DIAMOND_ZOMBIE, RegionType.OBSIDIAN_SANCTUARY).start();
        new EntityPopulator(20, 30, 200L, SEntityType.DIAMOND_ZOMBIE, RegionType.DIAMOND_RESERVE).start();
        new EntityPopulator(20, 30, 200L, SEntityType.DIAMOND_SKELETON, RegionType.DIAMOND_RESERVE).start();
        new EntityPopulator(20, 30, 200L, SEntityType.SMALL_SLIME, RegionType.SLIMEHILL).start();
        new EntityPopulator(20, 30, 200L, SEntityType.MEDIUM_SLIME, RegionType.SLIMEHILL).start();
        new EntityPopulator(20, 30, 400L, SEntityType.LARGE_SLIME, RegionType.SLIMEHILL).start();
        new EntityPopulator(20, 30, 400L, SEntityType.PIGMAN, RegionType.PIGMENS_DEN).start();
        new EntityPopulator(20, 30, 400L, SEntityType.LAPIS_ZOMBIE, RegionType.LAPIS_QUARRY).start();
        new EntityPopulator(20, 30, 400L, SEntityType.SNEAKY_CREEPER, RegionType.GUNPOWDER_MINES).start();
        new EntityPopulator(20, 30, 300L, SEntityType.WEAK_ENDERMAN, RegionType.THE_END).start();
        new EntityPopulator(20, 30, 300L, SEntityType.ENDERMAN, RegionType.THE_END).start();
        new EntityPopulator(20, 30, 300L, SEntityType.STRONG_ENDERMAN, RegionType.THE_END).start();
        new EntityPopulator(20, 30, 200L, SEntityType.ZEALOT, RegionType.DRAGONS_NEST).start();
        new EntityPopulator(1, 5, 1200L, SEntityType.ENDER_CHEST_ZEALOT, RegionType.DRAGONS_NEST).start();
        new EntityPopulator(20, 30, 200L, SEntityType.WATCHER, RegionType.DRAGONS_NEST).start();
        new EntityPopulator(20, 30, 200L, SEntityType.OBSIDIAN_DEFENDER, RegionType.DRAGONS_NEST).start();
        new EntityPopulator(20, 30, 300L, SEntityType.SPLITTER_SPIDER, RegionType.SPIDERS_DEN_HIVE).start();
        new EntityPopulator(20, 30, 300L, SEntityType.WEAVER_SPIDER, RegionType.SPIDERS_DEN_HIVE).start();
        new EntityPopulator(20, 30, 300L, SEntityType.VORACIOUS_SPIDER, RegionType.SPIDERS_DEN_HIVE).start();
        new EntityPopulator(20, 30, 300L, SEntityType.SPIDER_JOCKEY, RegionType.SPIDERS_DEN_HIVE).start();
        new EntityPopulator(20, 30, 300L, SEntityType.DASHER_SPIDER, RegionType.SPIDERS_DEN_HIVE).start();
        new EntityPopulator(20, 30, 300L, SEntityType.HIGH_LEVEL_SKELETON, RegionType.HIGH_LEVEL, world -> world.getTime() >= 13188L && world.getTime() <= 22812L).start();

        new EntityPopulator(20, 30, 200L, SEntityType.ZOMBIE, RegionType.GRAVEYARD).start();
        new EntityPopulator(20, 30, 200L, SEntityType.ZOMBIE_VILLAGER, RegionType.GRAVEYARD).start();
        new EntityPopulator(20, 30, 200L, SEntityType.WOLF, RegionType.RUINS).start();
        new EntityPopulator(20, 30, 200L, SEntityType.OLD_WOLF, RegionType.RUINS).start();
        new EntityPopulator(20, 30, 200L, SEntityType.CRYPT_GHOUL, RegionType.COAL_MINE_CAVES).start();
        new EntityPopulator(10, 30, 200L, SEntityType.GOLDEN_GHOUL, RegionType.COAL_MINE_CAVES).start();
        new EntityPopulator(10, 30, 200L, SEntityType.SOUL_OF_THE_ALPHA, RegionType.HOWLING_CAVE).start();
        new EntityPopulator(20, 30, 200L, SEntityType.HOWLING_SPIRIT, RegionType.HOWLING_CAVE).start();
        new EntityPopulator(10, 30, 200L, SEntityType.PACK_SPIRIT, RegionType.HOWLING_CAVE).start();
    }

    private void loadSerializableClasses() {
        ConfigurationSerialization.registerClass(SlayerQuest.class, "SlayerQuest");
        ConfigurationSerialization.registerClass(Pet.PetItem.class, "PetItem");
        ConfigurationSerialization.registerClass(SItem.class, "SItem");
        ConfigurationSerialization.registerClass(AuctionSettings.class, "AuctionSettings");
        ConfigurationSerialization.registerClass(AuctionEscrow.class, "AuctionEscrow");
        ConfigurationSerialization.registerClass(SerialNBTTagCompound.class, "SerialNBTTagCompound");
        ConfigurationSerialization.registerClass(AuctionBid.class, "AuctionBid");
    }

    private void registerPacketListener() {
        PacketHelper.addPacketHandler(new PacketHandler() {
            @Override
            public void onReceive(final ReceivedPacket packet) {
                final PacketReceiveServerSideEvent ev = new PacketReceiveServerSideEvent(packet);
                Bukkit.getPluginManager().callEvent(ev);
            }

            @Override
            public void onSend(final SentPacket packet) {
                final PacketSentServerSideEvent ev = new PacketSentServerSideEvent(packet);
                Bukkit.getPluginManager().callEvent(ev);
            }
        });
    }

    private void registerPingListener() {
        PingAPI.registerListener(event -> {
            final SkySimServerPingEvent e = new SkySimServerPingEvent(event);
            Bukkit.getPluginManager().callEvent(e);
        });
    }

    public static Player findPlayerByIPAddress(final String ip) {
        for (final Player p : Bukkit.getOnlinePlayers()) {
            if (p.getAddress().toString().contains(ip)) {
                return p;
            }
        }
        return null;
    }


    public String getDevelopersName(){
        StringBuilder builder = new StringBuilder();
        for (String name : DEVELOPERS){
            builder.append(name).append(" , ");
        }
        return builder.toString().substring(0 , builder.length() - 2);
    }


    public void onPluginMessageReceived(final String channel, final Player player, final byte[] message) {
        final PluginMessageReceived e = new PluginMessageReceived(new WrappedPluginMessage(channel, player, message));
        Bukkit.getPluginManager().callEvent(e);
    }

    private void authenticate() {
        final String licenseKey = config.getString("licenseKey");

        SentinelClient client = new SentinelClient(
                "http://authentication.gsdevelopments.in:2222/api/v1",
                "rhvs43epk4onhk9atpqrvivli5",
                "GYCZMI3M4rNYap1Q0JuSmM3b2MR0vaE+U2Kf7KWH1rg=");

        this.authenticated = false;

        try {
            client.getLicenseController().auth(
                    licenseKey, "SkyblockCore", null, null, SentinelClient.getCurrentHwid(), SentinelClient.getCurrentIp());
            this.authenticated = true;
        } catch (InvalidLicenseException e) {
            System.out.println("Invalid license key.");
        } catch (ExpiredLicenseException e) {
            System.out.println("Expired.");
        } catch (BlacklistedLicenseException e) {
            System.out.println("Blacklisted.");
        } catch (ConnectionMismatchException e) {
            System.out.println("Provided connection does not match.");
        } catch (ExcessiveServersException e) {
            System.out.println("Too many servers. (Max: " + e.getMaxServers() + ")");
        } catch (ExcessiveIpsException e) {
            System.out.println("Too many IPs. (Max: " + e.getMaxIps() + ")");
        } catch (InvalidProductException e) {
            System.out.println("License is for different product.");
        } catch (InvalidPlatformException e) {
            System.out.println("Provided connection platform is invalid.");
        } catch (IOException e) {
            System.out.println("An unexpected error occurred.");
        }

        if (authenticated) {
            System.out.println("Successfully authenticated.");
        }
    }

    public String getPrefix(){
        return ChatColor.translateAlternateColorCodes('&', "&7[&aGodspunky&bSkyblock&dCore&7] &f");
    }
    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(getPrefix() + CC.translate(message));
    }
}
