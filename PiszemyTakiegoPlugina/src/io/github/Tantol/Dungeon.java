package io.github.Tantol;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.Tantol.ArmorAPI.ArmorListener;
import io.github.Tantol.NewItems.ItemCommand;
import io.github.Tantol.NewMobs.MobCommand;
import io.github.Tantol.RpCHat.RpChat;

public class Dungeon extends JavaPlugin {
	private static Plugin plugin;
	public FileConfiguration config = getConfig();
	
	public static FileConfiguration customConfig = null;
	public File customConfigFile = null;
	
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
		getCommand("newmob").setExecutor(new MobCommand());
		registerEvents(this, new RpChat());
		registerEvents(this, new ItemCommand());
		registerEvents(this, new ArmorListener(getConfig().getStringList("blocked")));
		generateConfig();
		//customConfig.set("path.to.boolean", true);
		try {
			getCustomConfig();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		customConfig.set("path.to.string", "This is a custom yml file :D");
		saveCustomConfig();
		saveDefaultConfig();
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
	
	public void reloadCustomConfig() throws UnsupportedEncodingException {
	    if (customConfigFile == null) {
	    customConfigFile = new File(getDataFolder(), "mobConfig.yml");
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);

	    // Look for defaults in the jar
	    Reader defConfigStream = new InputStreamReader(this.getResource("mobConfig.yml"));
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig);
	    }
	}
	public FileConfiguration getCustomConfig() throws UnsupportedEncodingException {
	    if (customConfig == null) {
	        reloadCustomConfig();
	    }
	    return customConfig;
	}
	
	public void saveCustomConfig() {
	    if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        getCustomConfig().save(customConfigFile);
	    } catch (IOException ex) {
	        getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	    }
	}
	
	public void saveDefaultConfig() {
	    if (customConfigFile == null) {
	        customConfigFile = new File("mobConfig.yml");
	    }
	    if (!customConfigFile.exists()) {            
	         plugin.saveResource("mobConfig.yml", false);
	     }
	}
	
		 
		

}