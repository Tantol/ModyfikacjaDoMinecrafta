package io.github.Tantol.NewMobs;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.MagmaCube;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;

import io.github.Tantol.ConfigManager;
import io.github.Tantol.Dungeon;
import io.github.Tantol.CustomMonsters.Xxx_class.MyVillager;
import net.minecraft.server.v1_11_R1.CommandExecute;
import net.minecraft.server.v1_11_R1.EntityChicken;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EntityRabbit;
import net.minecraft.server.v1_11_R1.EntitySkeleton;
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
			WorldServer world2 = ((CraftWorld) loc.getWorld()).getHandle();
			if (cmnd.getName().equalsIgnoreCase("newmob")) {
				/*mobs.add(new CreateMob(player,"Skeleton","Szymek", world, loc));
				sender.sendMessage(Dungeon.newMobs.getCustomConfig().getString("przyklad.np1.mp3"));
				sender.sendMessage("wooow");
				EntityType s = EntityType.RABBIT;
				player.getWorld().spawnEntity(loc, s);*/
				//CustomSkeleton skele = new CustomSkeleton(world);
				//CustomSkeleton skele2 = new CustomSkeleton(world);
				//skele.setPosition(loc.getX(), loc.getY(), loc.getZ());
				//skele.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
				//world.addEntity(skele);
				//world.addEntity(skele2);
				MyVillager.spawn(loc);
				return true;
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Only " + ranga + " can use this command");
		}
		return false;

	}
}