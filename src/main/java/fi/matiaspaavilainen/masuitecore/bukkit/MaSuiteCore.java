package fi.matiaspaavilainen.masuitecore.bukkit;

import co.aikar.commands.PaperCommandManager;
import fi.matiaspaavilainen.masuitecore.bukkit.commands.MaSuiteCommand;
import fi.matiaspaavilainen.masuitecore.core.Updator;
import fi.matiaspaavilainen.masuitecore.core.configuration.BukkitConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MaSuiteCore extends JavaPlugin implements Listener {

    private BukkitConfiguration config = new BukkitConfiguration();

    public static BukkitCooldownManager cooldownManager = new BukkitCooldownManager();

    public static boolean bungee = true;
    public static List<String> onlinePlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        // Detect if new version on spigot
        config.create(this, null, "messages.yml");
        new Updator(getDescription().getVersion(), getDescription().getName(), "60037").checkUpdates();
        registerListeners();

        PaperCommandManager manager = new PaperCommandManager(this);
        manager.registerCommand(new MaSuiteCommand(this));
    }

    /**
     * Register listeners
     */
    private void registerListeners() {
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new CoreMessageListener(this));
    }

}
