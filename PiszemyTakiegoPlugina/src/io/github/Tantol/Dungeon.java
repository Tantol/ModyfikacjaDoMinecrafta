package io.github.Tantol;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.Tantol.ArmorAPI.ArmorListener;
import io.github.Tantol.NewItems.ItemCommand;
import io.github.Tantol.NewMobs.MobCommand;
import io.github.Tantol.RpCHat.RpChat;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.EntityZombie;
import io.github.Tantol.CustomMonsters.EntityRegistrer;
import io.github.Tantol.CustomMonsters.Xxx_class.MyVillager;
import io.github.Tantol.CustomMonsters.Xxx_class.MyZombie;

public class Dungeon extends JavaPlugin {
	private static Plugin plugin;
	public static ConfigManager chatRP;
	public static ConfigManager newItems;
	public static ConfigManager newMobs;
	public static ConfigManager newItemsFlag;
	/*public static boolean enabled = false;
	public static File saveDirectory;
	public static String nmsver;
	public static Logger log;*/
	
	/*@Override
	public void onLoad() {
		nmsver = Bukkit.getServer().getClass().getPackage().getName();
		nmsver = nmsver.substring(nmsver.lastIndexOf(".") + 1);
		saveDirectory = new File(this.getDataFolder(), "entityNBT");
		saveDirectory.mkdirs();
	}*/
	
	
	@Override
	public void onEnable() {
		plugin = this;
		chatRP = new ConfigManager(this,"config_chatRP.yml");
		newItems = new ConfigManager(this,"config_newItems.yml");
		newItemsFlag = new ConfigManager(this,"itemFlag.yml");
		newMobs = new ConfigManager(this,"config_newMobs.yml");
		chatRP.saveDefaultCustomConfig();
		newItems.saveDefaultCustomConfig();
		newItemsFlag.saveDefaultCustomConfig();
		newMobs.saveDefaultCustomConfig();
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
		
		//enabled = true;
		//log = getLogger();
		//EntityRegistrer.addAllCustomEntity();
		//registerEntities();
		
		//Jebac to .... ponizej
		//EntityRegistry.overrideEntity(CustomSkeleton2.class);
		//Ustawia na jajku i komendzie, (po reloadzie tylko jajko) 
		 //CustomEntityRegistry.registerCustomEntity(51, "skeleton", CustomSkeleton.class);
		 //CustomEntityRegistry.registerCustomEntity(51, "skeleton2", CustomSkeleton2.class);
		 //2---->Ustawia na jajku i komende
		   //CustomEntityRegistry.addCustomEntity(51, "skeleton", CustomSkeleton.class);
		   //CustomEntityRegistry.addCustomEntity(51, "skeleton", CustomSkeleton2.class);
		   //1--->Ustawia na jajku i komendzie
	}

	@Override
	public void onDisable() {
		plugin = this;
		//enabled = false;
		//unregisterEntities();

	}

	public static void registerEvents(org.bukkit.plugin.Plugin plugin, Listener... listeners) {
		for (Listener listener : listeners) {
			Bukkit.getServer().getPluginManager().registerEvents(listener, plugin);
		}
	}

	public static Plugin getPlugin() {
		return plugin;
	}
	
	private void registerEntities() {
			getServer().getPluginManager().registerEvents(new io.github.Tantol.CustomMonsters.v1_11_R1.Listeners(), this);
			io.github.Tantol.CustomMonsters.v1_11_R1.CustomRegistry.registerEntities();
		
	}
	
	private void unregisterEntities() {
		io.github.Tantol.CustomMonsters.v1_11_R1.CustomRegistry.unregisterEntities();
		
	}
	
}