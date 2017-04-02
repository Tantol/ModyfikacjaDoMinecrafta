package io.github.Tantol.NewMobs;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import io.github.Tantol.Dungeon;
import net.minecraft.server.v1_11_R1.CommandExecute;
import net.minecraft.server.v1_11_R1.WorldServer;

public class MobCommand extends CommandExecute implements CommandExecutor, Listener {
	private String ranga = "Player";
	private ArrayList<CreateMob> mobs = new ArrayList<CreateMob>();

	public MobCommand() {}

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmnd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			Location loc = player.getLocation();
			WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
			
			if (cmnd.getName().equalsIgnoreCase("newmob")) {
				mobs.add(new CreateMob("Szymek", world, loc));
				sender.sendMessage(Dungeon.customConfig.getString("ic.color"));
				
				Dungeon.customConfig.set("path.to.boolean", true);
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only " + ranga + " can use this command");
		}
		return false;

	}
}