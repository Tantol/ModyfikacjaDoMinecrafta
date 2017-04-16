package io.github.Tantol.NewMobs.CustomEntity;


import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntityCaveSpider;
import net.minecraft.server.v1_11_R1.World;

public class CustomCaveSpider extends EntityCaveSpider{

	public CustomCaveSpider(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}