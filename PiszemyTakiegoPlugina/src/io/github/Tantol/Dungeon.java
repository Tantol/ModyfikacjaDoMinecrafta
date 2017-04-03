package io.github.Tantol;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
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
	private static ArrayList<ConfigManager> tmp = new ArrayList<ConfigManager>();
	public ConfigManager chatRP;
	ConfigManager newItems;
	ConfigManager newMobs;
	@Override
	public void onEnable() {
		plugin = this;
		confSave("config_chatRP.yml");
		confSave("config_newItems.yml");
		confSave("config_newMobs.yml");
		//chatRP = new ConfigManager(this,"config_chatRP.yml");
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
	
	public void confSave(String name){
		tmp.add(new ConfigManager(this,name));
		tmp.get(tmp.size()-1).saveDefaultCustomConfig();
	}
	public FileConfiguration getCustomConfig(int index) {
        return tmp.get(index).getCustomConfig();
    }
	/*public FileConfiguration getCustomConfig(ConfigManager index) {
        return index.getCustomConfig();
	}*/
}