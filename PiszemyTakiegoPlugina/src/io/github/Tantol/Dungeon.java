package io.github.Tantol;

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
	FileConfiguration config = getConfig();
	private static Plugin plugin;

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
	 
	public static Plugin getPlugin() {
		return plugin;
	}
	
	public void generateConfig(){
		config.options().copyDefaults(true);
		saveConfig();
	}
	
	
}