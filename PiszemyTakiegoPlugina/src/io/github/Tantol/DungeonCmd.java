package io.github.Tantol;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DungeonCmd implements CommandExecutor {

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		if(strings.length<1){
		if (string.equalsIgnoreCase("dungeon")) {
			cs.sendMessage(ChatColor.BLUE + "Welcome to Dungeon ");

		}
		}
		if(strings.length>=1){
		if (string.equalsIgnoreCase("dungeon") && strings[0].equalsIgnoreCase("reload")) {
			cs.sendMessage(ChatColor.RED + "dungeon reloaded");
			Player player = (Player) cs;
			 Plugin plugin = player.getServer().getPluginManager().getPlugin("Dungeon");
			 player.getServer().getPluginManager().disablePlugin(plugin);
			 player.getServer().getPluginManager().enablePlugin(plugin);
		//	 Dungeon.getPlugin().getServer().getPluginManager().disablePlugin(Dungeon.getPlugin());
		//	 Dungeon.getPlugin().getServer().getPluginManager().enablePlugin(Dungeon.getPlugin());
		}
		}
		return false;
	}

}
