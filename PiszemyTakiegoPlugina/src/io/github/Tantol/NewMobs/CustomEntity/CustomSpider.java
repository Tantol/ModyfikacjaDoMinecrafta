package io.github.Tantol.NewMobs.CustomEntity;


import io.github.Tantol.NewMobs.CreateMob;
import net.minecraft.server.v1_11_R1.EntitySpider;
import net.minecraft.server.v1_11_R1.World;

public class CustomSpider extends EntitySpider{

	public CustomSpider(World world) {
		super(world);	
		CreateMob.addAtributess(this);
	}
	
}