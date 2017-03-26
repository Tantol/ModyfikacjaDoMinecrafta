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
		//registerEvents(this, new ListenerClass()); template Listener
		//getCommand("hi").setExecutor(new Command()); template Command
		//getCommand("hello").setExecutor(new Both()); template Both
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