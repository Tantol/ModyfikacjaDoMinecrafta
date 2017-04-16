package io.github.Tantol.NewMobs.CustomEntity;

import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.World;

public class CustomZombie extends EntityZombie{

	public CustomZombie(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}