package io.github.Tantol.NewItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
	
import io.github.Tantol.Dungeon;
import io.github.Tantol.ArmorAPI.ArmorEquipEvent;
import io.github.Tantol.ArmorAPI.ArmorEquipEvent.EquipMethod;
import io.github.Tantol.ArmorAPI.ArmorType;

public class ItemCommand implements CommandExecutor, Listener {
	public static ArrayList<ArrayList<String>> effects = new ArrayList<ArrayList<String>>();

	static ArrayList<CreateItem> items = new ArrayList<CreateItem>();
	List<String> armor = Arrays.asList("ARMOR", "HELMET", "LEGS", "BOOTS");
	static int flaga_b = 0;

	public ItemCommand() {
		if (flaga_b == 0) {
			effects.add(new ArrayList<String>());
			effects.add(new ArrayList<String>());
			effects.add(new ArrayList<String>());
			effects.add(new ArrayList<String>());

			for (int i = 0; i < Dungeon.newItems.getCustomConfig().getList("item_list").size(); i++) {
				String path = Dungeon.newItems.getCustomConfig().getList("item_list").get(i).toString();
				Material material = Material
						.getMaterial(Dungeon.newItems.getCustomConfig().getString(path + ".material"));
				String name = Dungeon.newItems.getCustomConfig().getString(path + ".name");
				String type = Dungeon.newItems.getCustomConfig().getString(path + ".type");
				ArrayList<String> list = (ArrayList<String>) Dungeon.newItems.getCustomConfig().getList(path + ".desc");
				double minDmg = Dungeon.newItems.getCustomConfig().getInt(path + ".minDmg");
				double maxDmg = Dungeon.newItems.getCustomConfig().getInt(path + ".maxDmg");
				double def = Dungeon.newItems.getCustomConfig().getInt(path + ".addDef");
				double hp = Dungeon.newItems.getCustomConfig().getInt(path + ".addHp");
				float movSpeed = Dungeon.newItems.getCustomConfig().getInt(path + ".movSpeed");

				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("WEAPON")) {
					items.add(new CreateItem(material, name, list, minDmg, maxDmg, type));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("ARMOR")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("HELMET")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("LEGS")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed));
				}
				if (Dungeon.newItems.getCustomConfig().getString(path + ".type").equals("BOOTS")) {
					items.add(new CreateItem(material, name, list, def, type, hp, movSpeed));
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
							if (effects.get(j).contains(event.getPlayer().getName())) {
							//if (Dungeon.newItemsFlag.getCustomConfig().getList(j+".players").contains(event.getPlayer().getName())) {
								event.getPlayer().sendMessage("Echo Off");
								// event.getPlayer().sendMessage(event.getOldArmorPiece().toString()
								// + " Off");
								event.getPlayer().setMaxHealth(event.getPlayer().getMaxHealth() - items.get(i).getHp());
								event.getPlayer().setHealth(event.getPlayer().getHealth() - items.get(i).getHp());
								event.getPlayer()
										.setWalkSpeed(event.getPlayer().getWalkSpeed() - items.get(i).getSpeed());
								//effects.get(j).remove(event.getPlayer().getName());
								//((ArrayList<String>)Dungeon.newItemsFlag.getCustomConfig().getList(j+".players")).remove(event.getPlayer().getName());
							}
						}
					}
		}
		if (event.getNewArmorPiece() != null && event.getNewArmorPiece().getType() != Material.AIR) {

			for (int i = 0; i < items.size(); i++)
				for (int j = 0; j < armor.size(); j++)
					if (items.get(i).getType().equals(armor.get(j)))
						if (items.get(i).getItem().getItemMeta().equals(event.getNewArmorPiece().getItemMeta())) {
						//	if (!Dungeon.newItemsFlag.getCustomConfig().getList(j+".players").contains(event.getPlayer().getName())) {
							if (!effects.get(j).contains(event.getPlayer().getName())) {
								event.getPlayer().sendMessage("Echo On");
								System.out.println((String) Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players").get(0));
								System.out.println(Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players").size());
								for(int x = 0; x< Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players").size(); x++)
								{
									System.out.println(Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players").get(x).toString());
								}
								// event.getPlayer().sendMessage(event.getNewArmorPiece().toString()
								// + " On");
								event.getPlayer().setMaxHealth(event.getPlayer().getMaxHealth() + items.get(i).getHp());
								event.getPlayer().setHealth(event.getPlayer().getHealth() + items.get(i).getHp());
								event.getPlayer()
										.setWalkSpeed(event.getPlayer().getWalkSpeed() + items.get(i).getSpeed());
								//effects.get(j).add(event.getPlayer().getName());
								((ArrayList<String>)Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players")).add(event.getPlayer().getName());
								System.out.println(Dungeon.newItemsFlag.getCustomConfig().getList(armor.get(j)+".players").size());
							}
						}

		}

	}

	@EventHandler
	public void playerDeathEvent(PlayerDeathEvent event) {

		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			for(int i = 0 ; i<4;i++){		
				//effects.get(i).remove(player.getName());
			//	((ArrayList<String>)Dungeon.newItemsFlag.getCustomConfig().getList(i+".players")).remove(player.getName());
				player.setMaxHealth(20);	
			}
		}

	}
	/*
	 * for (int i = 0; i < 4; i++) { if (!effects.contains(player))
	 * effects.get(i).add(player);
	 * 
	 * } for (ItemStack i : player.getInventory().getArmorContents()) { if (i !=
	 * null && !i.getType().equals(Material.AIR)) {
	 * Bukkit.getServer().getPluginManager() .callEvent(new
	 * ArmorEquipEvent(player, EquipMethod.DEATH, ArmorType.matchType(i), i,
	 * null)); } }
	 */

	@EventHandler
	/*
	 * public void onPlayerJoin(PlayerJoinEvent e){ Player player =
	 * e.getPlayer(); for(int i = 0 ; i<4; i++){ if(!items.contains(player))
	 * effects.get(i).add(player); } }
	 */

	// e.getPlayer().sendMessage(ChatColor.DARK_BLUE+"Welcome to our server!");
	// }
	/*
	 * @EventHandler public void onInventoryMove(InventoryClickEvent event) {
	 * Player player = (Player) event.getWhoClicked(); ItemStack armorParts[] =
	 * new ItemStack[4];
	 * 
	 * armorParts[0] = player.getInventory().getChestplate(); armorParts[1] =
	 * player.getInventory().getHelmet(); armorParts[2] =
	 * player.getInventory().getLeggings(); armorParts[3] =
	 * player.getInventory().getBoots(); ItemStack current =
	 * event.getCurrentItem(); ItemStack onCurrsor = event.getCursor();
	 * ArmorType armorType = ArmorType .matchType(event.getCurrentItem() != null
	 * && event.getCurrentItem().getType() != Material.AIR ?
	 * event.getCurrentItem() : event.getCursor()); for (int i = 0; i <
	 * items.size(); i++) for (int j = 0; j < armor.size(); j++) { if
	 * (items.get(i).getType().equals(armor.get(j))) { for (int g = 0; g <
	 * armorParts.length; g++) if (armorParts[g] != null &&
	 * armorParts[g].getType() != Material.AIR) { if
	 * (items.get(i).getItem().equals(current)) { //
	 * if(armorParts[g].equals(obj)) player.sendMessage("Event On Slot."); //
	 * player.setMaxHealth(player.getMaxHealth() - // 10); } } if
	 * (items.get(i).getType().equals(armor.get(j))) { if (armorType != null &&
	 * event.getRawSlot() == armorType.getSlot()) { if
	 * (items.get(i).getItem().equals(onCurrsor)) {
	 * 
	 * player.sendMessage("Event On Currsor."); //
	 * player.setMaxHealth(player.getMaxHealth() + // 10);
	 * 
	 * } } } }
	 * 
	 * }
	 * 
	 * }
	 */

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
