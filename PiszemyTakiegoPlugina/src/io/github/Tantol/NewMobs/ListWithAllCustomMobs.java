package io.github.Tantol.NewMobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.server.v1_11_R1.Entity;
import net.minecraft.server.v1_11_R1.EntityTypes;
import net.minecraft.server.v1_11_R1.MinecraftKey;
import net.minecraft.server.v1_11_R1.WorldServer;

public class ListWithAllCustomMobs {
	class mobInfo {
		private String name;
		private String type;
		private WorldServer world;
		public mobInfo(String name, String type, WorldServer world){
			this.name=name;
			this.type=type;
			this.world=world;
		}
		public void setName(String name){
			this.name=name;
		}
		public void setType(String type){
			this.type=type;
		}
		public void setWorld(WorldServer world){
			this.world=world;
		}
		public String getName(){return name;}
		public String getType(){return type;}
		public WorldServer getWorld(){return world;}

	}
	private final List<CreateMob> onEggAndSummonList = new ArrayList<CreateMob>();
	private final List<mobInfo> onCommandList = new ArrayList<mobInfo>();


	public ListWithAllCustomMobs() {
		this.onEggAndSummonList.clear();
		this.onCommandList.clear();
	}

	public void add(CreateMob mob, boolean onCommand, boolean onEggAndSummon) {
		mobInfo info = new mobInfo(mob.getName(),mob.getType(),mob.getWorld());
		if (onCommand) {
			boolean wasAddedQuestionMark = false;
			for (int i = 0; i < onCommandList.size(); i++) {

				if (onCommandList.get(i).equals(info)) {
					System.out.println("Entity (" + mob.getType() + ") with that name (" + mob.getName()
							+ ") is alrady exist (set to use on command) on this server ("+mob.getWorld()+").");
					wasAddedQuestionMark = true;
				}
			}
			if (!wasAddedQuestionMark) {
				mob.worldAddEntity();
				onCommandList.add(info);
				System.out.println(
						"Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was successful added (set to use on command) on server ("+mob.getWorld()+").");
			}

		}
		if (onEggAndSummon) {
			boolean wasAddedQuestionMark = false;
			boolean wasOverwritten=false;
			for(int i=0; i<onEggAndSummonList.size(); i++){
				if (onEggAndSummonList.get(i).equals(mob)) {
					System.out.println("Entity (" + mob.getType() + ") with that name (" + mob.getName()
					+ ")and this attributes is alrady exist (set to Summon on egg) on this server ("+mob.getWorld()+").");
			wasAddedQuestionMark = true;
				}
				if((!wasAddedQuestionMark)&&(mob.getId()==onEggAndSummonList.get(i).getId())){
					/*if(mob.getOldMobClass()==onEggAndSummonList.get(i).getOldMobClass()){
						onEggAndSummonList.remove(i);
					}*/
					mob.addCustomEntity();
					wasOverwritten=true;
					System.out.println(
							"Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was successful overwritten (set to Summon on egg) on server ("+mob.getWorld()+").");
				}
			}
			if((!wasAddedQuestionMark)&&(!wasOverwritten)){
				mob.addCustomEntity();
				onEggAndSummonList.add(mob);
				System.out.println(
						"Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was successful added (set to Summon on egg) on server ("+mob.getWorld()+").");
			}

		}
	}
	public void remove(CreateMob mob, boolean onCommand, boolean onEggAndSummon){
		mobInfo info = new mobInfo(mob.getName(),mob.getType(),mob.getWorld());
		if(onCommand){
			boolean wasRemovedQuestionMark=false;
			for(int i=0; i<onCommandList.size(); i++){
				if(info.equals(onCommandList.get(i))){
					mob.removeWorldAddEntity();
					onCommandList.remove(i);
					wasRemovedQuestionMark=true;
					System.out.println("Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was successful removed from server ("+mob.getWorld()+").");
				}
			}
			if(!wasRemovedQuestionMark){
				System.out.println("Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was not removed from server ("+mob.getWorld()+"), because that mob is not existing on it.");
			}
		}
		if(onEggAndSummon){
			for(int i=0; i<onEggAndSummonList.size(); i++){
				if(mob.equals(onEggAndSummonList.get(i))){
					mob.removeCustomEntity();
					onEggAndSummonList.remove(i);
					System.out.println("Entity (" + mob.getType() + ") with name (" + mob.getName() + ") was removed from set to Summon on egg (besic class was added on his place)"+mob.getWorld());
				}
			}
			
		}
		
	}
}
