package io.github.Tantol.NewMobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import net.minecraft.server.v1_11_R1.EntitySkeleton;
import net.minecraft.server.v1_11_R1.WorldServer;

public class CreateMob {

	public CreateMob(String name, WorldServer world, Location loc) {
		EntitySkeleton skieleton = new EntitySkeleton(world);
		skieleton.setCustomName(ChatColor.RED + name);
		skieleton.setCustomNameVisible(true);
		skieleton.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		world.addEntity(skieleton);

	}

}