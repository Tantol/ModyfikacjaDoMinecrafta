package io.github.Tantol.NewMobs.CustomEntity;

import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntitySkeleton;
import net.minecraft.server.v1_11_R1.World;

public class CustomSkeleton extends EntitySkeleton{

	public CustomSkeleton(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
}