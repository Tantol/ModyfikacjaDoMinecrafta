package io.github.Tantol.NewMobs.CustomEntity;

import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntityPigZombie;
import net.minecraft.server.v1_11_R1.World;

public class CustomPigZombie extends EntityPigZombie{

	public CustomPigZombie(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}