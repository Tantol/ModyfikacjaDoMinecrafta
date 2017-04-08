package io.github.Tantol.NewMobs;

import net.minecraft.server.v1_11_R1.EntitySkeleton;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.Material;
import net.minecraft.server.v1_11_R1.World;

public class CustomSkeleton extends EntitySkeleton{

	public CustomSkeleton(World world) {
		super(world);
		(this).setCustomName("SzymCOMAND");
		(this).setCustomNameVisible(true);
		this.getEquipment(EnumItemSlot.HEAD);
		
	}
	

}