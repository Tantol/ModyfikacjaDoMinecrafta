package io.github.Tantol.CustomMonsters.Xxx_class;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.EntityHuman;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.EnumHand;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.SoundEffect;
import net.minecraft.server.v1_11_R1.World;





public class MyZombie extends EntityZombie {

	
	public MyZombie(World world) {
		super(world);
		ItemStack cp = new ItemStack(Material.GOLD_CHESTPLATE);
		net.minecraft.server.v1_11_R1.ItemStack nms = CraftItemStack.asNMSCopy(cp);
		this.setEquipment(EnumItemSlot.MAINHAND,nms);
		this.setCustomName("Janusz");
		this.setCustomNameVisible(true);
		this.setSlot(EnumItemSlot.CHEST,nms);
		this.setSprinting(true);
		
	}

	public boolean a(EntityHuman entity, EnumHand enumhand, @Nullable ItemStack itemstack) {
		return false;
	}
	
	@Override
	protected SoundEffect G() {
		return null;
	}
	
	public void collide(Entity entity) {}
	
	public static MyZombie spawn(Location location) {
		try {
			if (!location.getChunk().isLoaded()) location.getChunk().load();
			MyZombie zombie = new MyZombie(((CraftWorld) location.getWorld()).getHandle());
			zombie .setPosition(location.getX(), location.getY(), location.getZ());
			(((CraftWorld) location.getWorld()).getHandle()).addEntity(zombie , SpawnReason.CUSTOM);
			return zombie ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}