package io.github.Tantol.NewMobs.CustomEntity;

import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntityEnderman;
import net.minecraft.server.v1_11_R1.World;

public class CustomEnderman extends EntityEnderman{

	public CustomEnderman(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}