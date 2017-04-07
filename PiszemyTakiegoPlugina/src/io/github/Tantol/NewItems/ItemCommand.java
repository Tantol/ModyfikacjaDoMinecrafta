package io.github.Tantol.NewItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

import io.github.Tantol.Dungeon;
import io.github.Tantol.ArmorAPI.ArmorEquipEvent;

public class ItemCommand implements CommandExecutor, Listener {
	public static ArrayList<ArrayList<String>> effects = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> potionEffect = new ArrayList<String>();

	static ArrayList<CreateItem> items = new ArrayList<CreateItem>();
	List<String> armor = Arrays.asList("ARMOR", "HELMET", "LEGS", "BOOTS");

	static int flaga_b = 0;
	int taskId;

	public ItemCommand() {
		if (flaga_b == 0) {
			effects.add((ArrayList<String>) Dungeon.newItemsFlag.getCustomConfig().getList("ARMOR.players"));
			effects.add((ArrayList<String>) Dungeon.newItemsFlag.getCustomConfig().getList("HELMET.players"));
			effects.add((ArrayList<String>) Dungeon.newItemsFlag.getCustomConfig().getList("LEGS.players"));
			effects.add((ArrayList<String>) Dungeon.newItemsFlag.getCustomConfig().getList("BOOTS.players"));
			//
			Dungeon.newItemsFlag.getCustomConfig().set(armor.get(0) + ".players", effects.get(0));
			Dungeon.newItemsFlag.getCustomConfig().set(armor.get(1) + ".players", effects.get(1));
			Dungeon.newItemsFlag.getCustomConfig().set(armor.get(2) + ".players", effects.get(2));
			Dungeon.newItemsFlag.getCustomConfig().set(armor.get(3) + ".players", effects.get(3));
			Dungeon.newItemsFlag.getCustomConfig().set("Potion.potionEffects", potionEffect);

			for (int i = 0; i < Dungeon.newItems.getCustomConfig().getList("item_list").size(); i++) {
				String path = Dungeon.newItems.getCustomConfig().getList("item_list").get(i).toString();
				Material material = Material
						.getMaterial(Dungeon.newItems.getCustomConfig().getString(path + ".material"));
				String name = Dungeon.newItems.getCustomConfig().getString(path + ".name");
				String type = Dungeon.newItems.getCustomConfig().getString(path + ".type");
				ArrayList<String> list = (ArrayList<String>) Dungeon.newItems.getCustomConfig().getList(path + ".desc");
				double minDmg = Dungeon.newItems.getCustomConfig().getDouble(path + ".minDmg");
				double maxDmg = Dungeon.newItems.getCustomConfig().getDouble(path + ".maxDmg");
				double def = Dungeon.newItems.getCustomConfig().getDouble(path + ".addDef");
				double hp = Dungeon.newItems.getCustomConfig().getDouble(path + ".addHp");
				float movSpeed = Dungeon.newItems.getCustomConfig().getInt(path + ".movSpeed");
				boolean antiFall = Dungeon.newItems.getCustomConfig().getBoolean(path + ".prevFalling");
				ArrayList<String> potionEff = (ArrayList<String>) Dungeon.newItems.getCustomConfig()
						.getList(path + ".potionEff");
				int potionEffGrade = Dungeon.newItems.getCustomConfig().getInt(path + ".potionEffGrade");

				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("WEAPON")) {
					items.add(new CreateItem(material, name, list, minDmg, maxDmg, type));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("ARMOR")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed, antiFall, potionEff,
							potionEffGrade));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("HELMET")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed, antiFall, potionEff,
							potionEffGrade));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("LEGS")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed, antiFall, potionEff,
							potionEffGrade));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("BOOTS")) {
					System.out.println(movSpeed);
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed, antiFall, potionEff,
							potionEffGrade));
				}
				flaga_b = 1;
			}
			/*
			 * items.add(new CreateItem(Material.DIAMOND_SWORD, "Testowy Miecz",
			 * Arrays.asList("123", "321"), 1.0d, 30.0d, "WEAPON"));
			 * items.add(new CreateItem(Material.DIAMOND_CHESTPLATE,
			 * "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d, "ARMOR",
			 * 5.0d,0.0f)); items.add(new CreateItem(Material.DIAMOND_HELMET,
			 * "Testowa Helm", Arrays.asList("123", "321"), 5.0d, "HELMET",
			 * 5.0d,0.0f)); items.add(new CreateItem(Material.DIAMOND_LEGGINGS,
			 * "Testowe Spodnie", Arrays.asList("123", "321"), 5.0d, "LEGS",
			 * 5.0d,0.0f)); items.add(new CreateItem(Material.DIAMOND_BOOTS,
			 * "Testowe Buty", Arrays.asList("123", "321"), 5.0d, "BOOTS",
			 * 5.0d,0.30f));
			 */
		}
	}

	//
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmnd, String string, String[] args) {

		Player player = (Player) sender;
		if (cmnd.getName().equalsIgnoreCase("newitem")) {
			if (args.length == 0) {
				sender.sendMessage("Podaj argument");
			} else {
				int index = Integer.parseInt(args[0]);
				sender.sendMessage("item crated");
				player.getInventory().addItem(items.get(index).getItem());
			}

		}
		return false;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		Random r = new Random();

		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack itemMain = player.getInventory().getItemInMainHand();
			//
			for (int i = 0; i < items.size(); i++) {

				if (items.get(i).getType().equals("WEAPON")) {
					double dmg = items.get(i).getMinDmg()
							+ (items.get(i).getMaxDmg() - items.get(i).getMinDmg()) * r.nextDouble();
					if (itemMain.equals(items.get(i).getItem())) {
						event.setDamage(dmg);
						player.sendMessage("" + event.getEntityType().name() + " - " + round(dmg, 1) + " HP");
						break;
					} else {
						player.sendMessage(
								"" + event.getEntityType().name() + " - " + round(event.getDamage(), 1) + " HP");
						break;
					}

				}
			}

		}
		/*
		 * if (event.getEntity() instanceof Player) { Player defender = (Player)
		 * event.getEntity(); ItemStack itemArmor =
		 * defender.getInventory().getChestplate(); for (int i = 0; i <
		 * items.size(); i++) { if (items.get(i).getType().equals("ARMOR")) { if
		 * (itemArmor.equals(items.get(i).getItem())) { double dmgValue =
		 * event.getDamage(); System.out.println("[Dungeon] DEBUG: Old Dmg: " +
		 * event.getDamage()); event.setDamage((dmgValue -
		 * items.get(i).getDef()));
		 * System.out.println("[Dungeon] DEBUG: New Dmg: " + (dmgValue -
		 * items.get(i).getDef())); } } } }
		 */

	}

	@EventHandler
	public void onWearArmor(ArmorEquipEvent event) {
		if (event.getOldArmorPiece() != null && event.getOldArmorPiece().getType() != Material.AIR) {
			for (int i = 0; i < items.size(); i++)
				for (int j = 0; j < armor.size(); j++)
					if (items.get(i).getType().equals(armor.get(j))) {
						if (items.get(i).getItem().getItemMeta().equals(event.getOldArmorPiece().getItemMeta())) {
							if (Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j) + ".players")
									.contains(event.getPlayer().getName())) {
								event.getPlayer().sendMessage("Echo Off");
								event.getPlayer().setMaxHealth(event.getPlayer().getMaxHealth() - items.get(i).getHp());
								event.getPlayer().setHealth(event.getPlayer().getHealth() - items.get(i).getHp());
								event.getPlayer()
										.setWalkSpeed(event.getPlayer().getWalkSpeed() - items.get(i).getSpeed() / 100);
								fallStopIt(event.getPlayer(), j);

								potionStopIt(event.getPlayer(), j);
								effects.get(j).remove(event.getPlayer().getName());
								Dungeon.newItemsFlag.getCustomConfig().set(armor.get(j) + ".players", effects.get(j));
								Dungeon.newItemsFlag.saveDefaultCustomConfig();
								Dungeon.newItemsFlag.saveCustomConfig();

							}
						}
					}
		}
		if (event.getNewArmorPiece() != null && event.getNewArmorPiece().getType() != Material.AIR) {
			for (int i = 0; i < items.size(); i++)
				for (int j = 0; j < armor.size(); j++)
					if (items.get(i).getType().equals(armor.get(j)))
						if (items.get(i).getItem().getItemMeta().equals(event.getNewArmorPiece().getItemMeta())) {
							if (!Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j) + ".players")
									.contains(event.getPlayer().getName())) {
								event.getPlayer().sendMessage("Echo On");
								System.out.println(event.getPlayer().getFallDistance());
								if (items.get(i).getFall() == true)
									antiFall(event.getPlayer(), j);
								event.getPlayer().setFallDistance(0);
								event.getPlayer().setMaxHealth(event.getPlayer().getMaxHealth() + items.get(i).getHp());
								event.getPlayer().setHealth(event.getPlayer().getHealth() + items.get(i).getHp());
								event.getPlayer()
										.setWalkSpeed(event.getPlayer().getWalkSpeed() + items.get(i).getSpeed() / 100);
								for (int g = 0; g < items.get(i).getPotion().size(); g++) {
									System.out.println(items.get(i).getPotion().get(g));
									PotionEffectType eff = null;

									try {
										eff = PotionEffectType.getByName(items.get(i).getPotion().get(g));
									} catch (Exception ex) {
										ex.printStackTrace();
										break;
									}
									PotionEffect effect = new PotionEffect(eff, 40, items.get(i).getPotionGrade());

									if (!potionEffect.contains(effect.toString())) {
										potionEffect.add(effect.toString());
									}
									potionEffect(event.getPlayer(), effect, j);

								}

								effects.get(j).add(event.getPlayer().getName());
								Dungeon.newItemsFlag.getCustomConfig().set(armor.get(j) + ".players", effects.get(j));
								Dungeon.newItemsFlag.saveDefaultCustomConfig();
								Dungeon.newItemsFlag.saveCustomConfig();

							}
						}

		}

	}

	public void antiFall(Player player, int j) {

		BukkitScheduler scheduler = Dungeon.getPlugin().getServer().getScheduler();
		taskId = scheduler.scheduleSyncRepeatingTask(Dungeon.getPlugin(), new Runnable() {
			@Override
			public void run() {

				if (Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j) + ".players")
						.contains(player.getName())) {
					player.setFallDistance(0);
					System.out.println("Fall Work For " + player.getName());

				} else {
					System.out.println("Im in else");

				}

			}

		}

				, 0L, 1L);
		Dungeon.newItemsFlag.getCustomConfig().set("FALL." + player.getName() + ".nick", player.getDisplayName());
		Dungeon.newItemsFlag.getCustomConfig().set("FALL." + player.getName() + ".id", taskId);
		Dungeon.newItemsFlag.getCustomConfig().set("FALL." + player.getName() + "." + armor.get(j) + ".fallingOn",
				true);
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	public void potionEffect(Player player, PotionEffect effect, int j) {

		BukkitScheduler scheduler = Dungeon.getPlugin().getServer().getScheduler();

		taskId = scheduler.scheduleSyncRepeatingTask(Dungeon.getPlugin(), new Runnable() {
			@Override

			public void run() {

				// if
				// (Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)
				// + ".players")
				// .contains(player.getName())) {
				player.addPotionEffect(effect);
				System.out.println("Potion " + effect + " Works For " + player.getName());
				// }
			}

		}

				, 0L, 5L);
		Dungeon.newItemsFlag.getCustomConfig()
				.set("Potion." + player.getName() + "." + armor.get(j) + "." + effect + ".id", taskId);
		Dungeon.newItemsFlag.getCustomConfig()
				.set("Potion." + player.getName() + "." + armor.get(j) + "." + effect + ".potionOn", true);
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent event) {
		for (int i = 0; i < 4; i++) {
			if (Dungeon.newItemsFlag.getCustomConfig()
					.getBoolean("FALL." + event.getPlayer().getName() + "." + armor.get(i) + ".fallingOn") == true) {
				antiFall(event.getPlayer(), i);
			}

		}

		for (int i = 0; i < 4; i++)
			for (int ef = 0; ef < potionEffect.size(); ef++)
				for (int it = 0; it < items.size(); it++){
					System.out.println("i= "+i+" ef = "+ef+" it= "+it);	
					System.out.println(Dungeon.newItemsFlag.getCustomConfig().getString(("Potion." + event.getPlayer() + "."
							+ armor.get(i) + "." + potionEffect.get(ef) + ".potionOn").toString()));
					System.out.println(Dungeon.newItemsFlag.getCustomConfig().getBoolean("Potion." + event.getPlayer() + "."
							+ armor.get(i) + "." + potionEffect.get(ef) + ".potionOn"));
					if (Dungeon.newItemsFlag.getCustomConfig().getBoolean("Potion." + event.getPlayer() + "."
							+ armor.get(i) + "." + potionEffect.get(ef) + ".potionOn")== true) {

						PotionEffectType eff = null;

						try {
							eff = PotionEffectType.getByName(items.get(it).getPotion().get(ef));
						} catch (Exception ex) {
							ex.printStackTrace();
							break;
						}						
						PotionEffect effect = new PotionEffect(eff, 40, 1);
						System.out.println("it= "+it+" ef = "+ef+" eff= "+eff+" effect= "+effect);	
						//potionEffect(event.getPlayer(), effect, i);

					}
				}

	}

	

	@EventHandler
	public void onPlayerQuitJEvent(PlayerQuitEvent event) {
		for (int i = 0; i < 4; i++) {
			fallStopItExit(event.getPlayer(), i);
			potionStopItExit(event.getPlayer(), i);
		}
	}

	public void fallStopIt(Player player, int j) {
		Dungeon.getPlugin().getServer().getScheduler()
				.cancelTask(Dungeon.newItemsFlag.getCustomConfig().getInt("FALL." + player.getName() + ".id"));
		Dungeon.newItemsFlag.getCustomConfig().set("FALL." + player.getName() + "." + armor.get(j) + ".fallingOn",
				false);
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	public void fallStopItExit(Player player, int j) {
		Dungeon.getPlugin().getServer().getScheduler()
				.cancelTask(Dungeon.newItemsFlag.getCustomConfig().getInt("FALL." + player.getName() + ".id"));
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	public void potionStopIt(Player player, int j) {
		for (int i = 0; i < potionEffect.size(); i++) {
			if (Dungeon.newItemsFlag.getCustomConfig().getBoolean("Potion." + player.getName() + "." + armor.get(j)
					+ "." + potionEffect.get(i).toString() + ".potionOn") == true) {
				Dungeon.getPlugin().getServer().getScheduler()
						.cancelTask(Dungeon.newItemsFlag.getCustomConfig().getInt("Potion." + player.getName() + "."
								+ armor.get(j) + "." + potionEffect.get(i).toString() + ".id", taskId));
				Dungeon.newItemsFlag.getCustomConfig().set("Potion." + player.getName() + "." + armor.get(j) + "."
						+ potionEffect.get(i).toString() + ".potionOn", false);
			}
		}
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	public void potionStopItExit(Player player, int j) {
		for (int i = 0; i < potionEffect.size(); i++) {
			Dungeon.getPlugin().getServer().getScheduler().cancelTask(Dungeon.newItemsFlag.getCustomConfig().getInt(
					"Potion." + player.getName() + "." + armor.get(j) + "." + potionEffect.get(i).toString() + ".id",
					taskId));
		}
		Dungeon.newItemsFlag.saveDefaultCustomConfig();
		Dungeon.newItemsFlag.saveCustomConfig();
	}

	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			for (int i = 0; i < 4; i++) {
				effects.get(i).remove(player.getName());
				Dungeon.newItemsFlag.getCustomConfig().set(armor.get(i) + ".players", effects.get(i));

				Dungeon.newItemsFlag.saveDefaultCustomConfig();
				Dungeon.newItemsFlag.saveCustomConfig();
				player.setMaxHealth(20);
				player.setWalkSpeed(0.19999999f);
				fallStopIt(player, i);
				potionStopIt(player, i);
			}

		}

	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
