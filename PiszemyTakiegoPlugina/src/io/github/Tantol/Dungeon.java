package io.github.Tantol;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public class Dungeon extends JavaPlugin {

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
}