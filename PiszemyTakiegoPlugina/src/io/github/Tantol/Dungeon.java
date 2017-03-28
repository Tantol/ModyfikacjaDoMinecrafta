package io.github.Tantol;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.Tantol.RpCHat.RpChat;



public class Dungeon extends JavaPlugin {
	FileConfiguration config = getConfig();
	private static Plugin plugin;

	@Override
	public void onEnable() {
		plugin = this;
		getCommand("jebiecimatke").setExecutor(new Test());
		getCommand("ooc").setExecutor(new RpChat());
		getCommand("ic").setExecutor(new RpChat());
		getCommand("s").setExecutor(new RpChat());
		getCommand("w").setExecutor(new RpChat());
		getCommand("me").setExecutor(new RpChat());
		getCommand("ng").setExecutor(new RpChat());
		getCommand("n").setExecutor(new RpChat());
		getCommand("rpchat").setExecutor(new RpChat());
		registerEvents(this, new RpChat());
		//registerEvents(this, new ListenerClass());
		//getCommand("hi").setExecutor(new Command()); template Command
		//getCommand("testchat").setExecutor(new Both());
		//registerEvents(this, new Both());
		//createConfig();
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
	private void createConfig() {
	    try {
	        if (!getDataFolder().exists()) {
	            getDataFolder().mkdirs();
	        }
	        File file = new File(getDataFolder(), "config.yml");
	        if (!file.exists()) {
	            getLogger().info("Config.yml not found, creating!");
	            saveDefaultConfig();
	        } else {
	            getLogger().info("Config.yml found, loading!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();

	    }

	}
	 
	public static Plugin getPlugin() {
		return plugin;
	}
}