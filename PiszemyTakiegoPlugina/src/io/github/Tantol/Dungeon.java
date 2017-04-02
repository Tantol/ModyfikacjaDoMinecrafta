package io.github.Tantol;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.Tantol.ArmorAPI.ArmorListener;
import io.github.Tantol.NewItems.ItemCommand;
import io.github.Tantol.RpCHat.RpChat;

public class Dungeon extends JavaPlugin {
	private static Plugin plugin;
	FileConfiguration config = getConfig();
	public static File customYml = new File("mobConfig.yml");
	public static FileConfiguration customConfig = YamlConfiguration.loadConfiguration(customYml);
	//private static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("dungeon").setExecutor(new DungeonCmd());
		getCommand("ooc").setExecutor(new RpChat());
		getCommand("ic").setExecutor(new RpChat());
		getCommand("s").setExecutor(new RpChat());
		getCommand("w").setExecutor(new RpChat());
		getCommand("me").setExecutor(new RpChat());
		getCommand("ng").setExecutor(new RpChat());
		getCommand("n").setExecutor(new RpChat());
		getCommand("rpchat").setExecutor(new RpChat());
		getCommand("newitem").setExecutor(new ItemCommand());
		registerEvents(this, new RpChat());
		registerEvents(this, new ItemCommand());
		registerEvents(this, new ArmorListener(getConfig().getStringList("blocked")));
		generateConfig();
		customConfig.set("path.to.boolean", true);
		saveCustomYml(customConfig, customYml);
		customConfig.set("path.to.boolean", true);
		// registerEvents(this, new ListenerClass());
		// getCommand("hi").setExecutor(new Command()); template Command
		// getCommand("testchat").setExecutor(new Both());
		// registerEvents(this, new Both());
		// createConfig();
	}

	@Override
	public void onDisable() {
		plugin = this;
	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public void generateConfig() {
		config.options().copyDefaults(true);
		saveConfig();
	}

	public void saveCustomYml(FileConfiguration ymlConfig, File ymlFile) {
		try {
			ymlConfig.options().copyDefaults(true);
			saveConfig();
			ymlConfig.save(ymlFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}