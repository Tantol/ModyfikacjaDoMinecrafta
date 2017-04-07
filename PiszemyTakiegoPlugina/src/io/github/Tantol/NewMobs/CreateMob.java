package io.github.Tantol.NewMobs;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_11_R1.EntityBat;
import net.minecraft.server.v1_11_R1.EntityBlaze;
import net.minecraft.server.v1_11_R1.EntityCaveSpider;
import net.minecraft.server.v1_11_R1.EntityChicken;
import net.minecraft.server.v1_11_R1.EntityCow;
import net.minecraft.server.v1_11_R1.EntityCreature;
import net.minecraft.server.v1_11_R1.EntityCreeper;
import net.minecraft.server.v1_11_R1.EntityEnderDragon;
import net.minecraft.server.v1_11_R1.EntityEnderman;
import net.minecraft.server.v1_11_R1.EntityEndermite;
import net.minecraft.server.v1_11_R1.EntityEvoker;
import net.minecraft.server.v1_11_R1.EntityGhast;
import net.minecraft.server.v1_11_R1.EntityGuardian;
import net.minecraft.server.v1_11_R1.EntityGuardianElder;
import net.minecraft.server.v1_11_R1.EntityHorse;
import net.minecraft.server.v1_11_R1.EntityHorseDonkey;
import net.minecraft.server.v1_11_R1.EntityHorseMule;
import net.minecraft.server.v1_11_R1.EntityHorseSkeleton;
import net.minecraft.server.v1_11_R1.EntityHorseZombie;
import net.minecraft.server.v1_11_R1.EntityIronGolem;
import net.minecraft.server.v1_11_R1.EntityLiving;
import net.minecraft.server.v1_11_R1.EntityLlama;
import net.minecraft.server.v1_11_R1.EntityMagmaCube;
import net.minecraft.server.v1_11_R1.EntityMushroomCow;
import net.minecraft.server.v1_11_R1.EntityOcelot;
import net.minecraft.server.v1_11_R1.EntityPig;
import net.minecraft.server.v1_11_R1.EntityPigZombie;
import net.minecraft.server.v1_11_R1.EntityPolarBear;
import net.minecraft.server.v1_11_R1.EntityRabbit;
import net.minecraft.server.v1_11_R1.EntitySheep;
import net.minecraft.server.v1_11_R1.EntityShulker;
import net.minecraft.server.v1_11_R1.EntitySilverfish;
import net.minecraft.server.v1_11_R1.EntitySkeleton;
import net.minecraft.server.v1_11_R1.EntitySkeletonStray;
import net.minecraft.server.v1_11_R1.EntitySkeletonWither;
import net.minecraft.server.v1_11_R1.EntitySlime;
import net.minecraft.server.v1_11_R1.EntitySnowman;
import net.minecraft.server.v1_11_R1.EntitySpider;
import net.minecraft.server.v1_11_R1.EntitySquid;
import net.minecraft.server.v1_11_R1.EntityVex;
import net.minecraft.server.v1_11_R1.EntityVillager;
import net.minecraft.server.v1_11_R1.EntityVindicator;
import net.minecraft.server.v1_11_R1.EntityWitch;
import net.minecraft.server.v1_11_R1.EntityWither;
import net.minecraft.server.v1_11_R1.EntityWolf;
import net.minecraft.server.v1_11_R1.EntityZombie;
import net.minecraft.server.v1_11_R1.EntityZombieHusk;
import net.minecraft.server.v1_11_R1.EntityZombieVillager;
import net.minecraft.server.v1_11_R1.EnumItemSlot;
import net.minecraft.server.v1_11_R1.WorldServer;


public class CreateMob extends EntityCreature {
	String name, type;
	EntityLiving mob = null;
	WorldServer world;
	Location loc;

	public CreateMob(Player player, String type, String name, WorldServer world, Location loc) {
		super(world);
		this.type = type;
		this.name = name;
		this.world = world;
		this.loc = loc;

		///////////////////////////////////////////
		// Neutral mobs ///////////////////////////
		///////////////////////////////////////////
		if (type.equals("Cave Spider")) {
			mob = new EntityCaveSpider(world);
			addAtributes();
		} else if (type.equals("Enderman")) {
			mob = new EntityEnderman(world);
			addAtributes();
		} else if (type.equals("Polar Bear")) {
			mob = new EntityPolarBear(world);
			addAtributes();
		} else if (type.equals("Spider")) {
			mob = new EntitySpider(world);
			addAtributes();
		} else if (type.equals("Zombie Pigman")) {
			mob = new EntityPigZombie(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Hostile mobs ///////////////////////////
		// Need Chicken Jockey;
		// Need Skeleton Horseman;
		// Need Spider Jockey;
		///////////////////////////////////////////
		else if (type.equals("Blaze")) {
			mob = new EntityBlaze(world);
			addAtributes();
		} else if (type.equals("Creeper")) {
			mob = new EntityCreeper(world);
			addAtributes();
		} else if (type.equals("Elder Guardian")) {
			mob = new EntityGuardianElder(world);
			addAtributes();
		} else if (type.equals("Endermite")) {
			mob = new EntityEndermite(world);
			addAtributes();
		} else if (type.equals("Evoker")) {
			mob = new EntityEvoker(world);
			addAtributes();
		} else if (type.equals("Ghast")) {
			mob = new EntityGhast(world);
			addAtributes();
		} else if (type.equals("Guardian")) {
			mob = new EntityGuardian(world);
			addAtributes();
		} else if (type.equals("Husk")) {
			mob = new EntityZombieHusk(world);
			addAtributes();
		} else if (type.equals("Magma Cube")) {
			mob = new EntityMagmaCube(world);
			addAtributes();
		} else if (type.equals("Shulker")) {
			mob = new EntityShulker(world);
			addAtributes();
		} else if (type.equals("Silverfish")) {
			mob = new EntitySilverfish(world);
			addAtributes();
		} else if (type.equals("Skeleton")) {
			mob = new EntitySkeleton(world);
			addAtributes();
		} else if (type.equals("Slime")) {
			mob = new EntitySlime(world);
			addAtributes();
		} else if (type.equals("Stray")) {
			mob = new EntitySkeletonStray(world);
			addAtributes();
		} else if (type.equals("Vex")) {
			mob = new EntityVex(world);
			addAtributes();
		} else if (type.equals("Vindicator")) {
			mob = new EntityVindicator(world);
			addAtributes();
		} else if (type.equals("Witch")) {
			mob = new EntityWitch(world);
			addAtributes();
		} else if (type.equals("Wither Skeleton")) {
			mob = new EntitySkeletonWither(world);
			addAtributes();
		} else if (type.equals("Zombie")) {
			mob = new EntityZombie(world);
			addAtributes();
		} else if (type.equals("Zombie Villager")) {
			mob = new EntityZombieVillager(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Tameable mobs ///////////////////////////
		///////////////////////////////////////////
		else if (type.equals("Donkey")) {
			mob = new EntityHorseDonkey(world);
			addAtributes();
		} else if (type.equals("Horse")) {
			mob = new EntityHorse(world);
			addAtributes();
		} else if (type.equals("Llama")) {
			mob = new EntityLlama(world);
			addAtributes();
		} else if (type.equals("Mule")) {
			mob = new EntityHorseMule(world);
			addAtributes();
		} else if (type.equals("Ocelot")) {
			mob = new EntityOcelot(world);
			addAtributes();
		} else if (type.equals("Wolf")) {
			mob = new EntityWolf(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Utility mobs ///////////////////////////
		///////////////////////////////////////////
		else if (type.equals("Iron Golem")) {
			mob = new EntityIronGolem(world);
			addAtributes();
		} else if (type.equals("Snow Golem")) {
			mob = new EntitySnowman(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Boss mobs ///////////////////////////
		///////////////////////////////////////////
		else if (type.equals("Ender Dragon")) {
			mob = new EntityEnderDragon(world);
			addAtributes();
		} else if (type.equals("Wither")) {
			mob = new EntityWither(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Unused mobs ///////////////////////////
		// Need Giant;
		///////////////////////////////////////////
		else if (type.equals("Killer Bunny")) {
			mob = new EntityRabbit(world);
			((EntityRabbit) mob).setRabbitType(99);
			addAtributes();
		} else if (type.equals("Zombie Horse")) {
			mob = new EntityHorseZombie(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Passive mobs ///////////////////////////
		///////////////////////////////////////////
		else if (type.equals("Bat")) {
			mob = new EntityBat(world);
			addAtributes();
		} else if (type.equals("Chicken")) {
			mob = new EntityChicken(world);
			addAtributes();
		} else if (type.equals("Cow")) {
			mob = new EntityCow(world);
			addAtributes();
		} else if (type.equals("Mooshroom")) {
			mob = new EntityMushroomCow(world);
			addAtributes();
		} else if (type.equals("Pig")) {
			mob = new EntityPig(world);
			addAtributes();
		} else if (type.equals("Rabbit")) {
			mob = new EntityRabbit(world);
			addAtributes();
		} else if (type.equals("Sheep")) {
			mob = new EntitySheep(world);
			addAtributes();
		} else if (type.equals("Skeleton Horse")) {
			mob = new EntityHorseSkeleton(world);
			addAtributes();
		} else if (type.equals("Squid")) {
			mob = new EntitySquid(world);
			addAtributes();
		} else if (type.equals("Villager")) {
			mob = new EntityVillager(world);
			addAtributes();
		}
		///////////////////////////////////////////
		// Other mobs ///////////////////////////
		///////////////////////////////////////////

		mob.setCustomName(ChatColor.RED + name);
		mob.setCustomNameVisible(true);
		mob.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
		world.addEntity(mob);
		
		/*EntityManager manager = RemoteEntities.createManager(this);
        RemoteEntity entity = manager.createEntity(RemoteEntityType.Villager, location, false);
        entity.setStationary(true);
        entity.setPushable(false);
        entity.getBukkitEntity().setCustomName("§d§lTutorial");
        entity.getBukkitEntity().setCustomNameVisible(true);*/

	}

	public void addAtributes() {
	}

}