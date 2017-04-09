package io.github.Tantol.NewMobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_11_R1.Entity;

public class ListWithAllCustomMobs {
	private final static Map<Integer,Class<? extends Entity>> onEggAndSummonWithOldMobClass= new HashMap<>();
	private final static List<Integer> onEggAndSummon = new ArrayList<Integer> ();
	private final static List<String> worldAddEntity = new ArrayList<String>();
	
	public ListWithAllCustomMobs(){}
	public void add(CreateMob mob, boolean onComand, boolean onEggSummon){
		if(onComand){
			
		}
		else if(onEggSummon){
			
		}
	}
}
