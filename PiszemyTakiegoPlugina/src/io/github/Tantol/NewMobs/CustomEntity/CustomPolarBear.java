package io.github.Tantol.NewMobs.CustomEntity;

import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntityPolarBear;
import net.minecraft.server.v1_11_R1.World;

public class CustomPolarBear extends EntityPolarBear{

	public CustomPolarBear(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}