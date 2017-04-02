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
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.Tantol.ArmorAPI.ArmorType;

public class ItemCommand implements CommandExecutor, Listener {

	ArrayList<CreateItem> items = new ArrayList<CreateItem>();
	List<String> armor = Arrays.asList("ARMOR", "HELMET", "LEGS", "BOOTS");

	public ItemCommand() {
		items.add(new CreateItem(Material.DIAMOND_SWORD, "Testowy Miecz", Arrays.asList("123", "321"), 1.0d, 30.0d,
				"WEAPON"));
		items.add(new CreateItem(Material.DIAMOND_CHESTPLATE, "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d,
				"ARMOR", 5.0d));
		items.add(new CreateItem(Material.DIAMOND_HELMET, "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d, "HELMET",
				5.0d));
		items.add(new CreateItem(Material.DIAMOND_LEGGINGS, "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d, "LEGS",
				5.0d));
		items.add(new CreateItem(Material.DIAMOND_BOOTS, "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d, "BOOTS",
				5.0d));
	}

	public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmnd, String string, String[] args) {

		Player player = (Player) sender;
		if (cmnd.getName().equalsIgnoreCase("newitem")) {
			if (args.length == 0) {
				sender.sendMessage("Podaj argument");
			} else {
				int index = Integer.parseInt(args[0]);
				sender.sendMessage("item crated");
				player.getInventory().addItem(items.get(index).getItem());
				player.setMaxHealth(20);
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
		if (event.getEntity() instanceof Player) {
			Player defender = (Player) event.getEntity();
			ItemStack itemArmor = defender.getInventory().getChestplate();
			for (int i = 0; i < items.size(); i++) {
				if (items.get(i).getType().equals("ARMOR")) {
					if (itemArmor.equals(items.get(i).getItem())) {
						double dmgValue = event.getDamage();
						System.out.println("[Dungeon] DEBUG: Old Dmg: " + event.getDamage());
						event.setDamage((dmgValue - items.get(i).getDef()));
						System.out.println("[Dungeon] DEBUG: New Dmg: " + (dmgValue - items.get(i).getDef()));
					}
				}
			}
		}

	}

	/*
	 * @EventHandler public void onWearArmor(ArmorEquipEvent event) { if
	 * (event.getNewArmorPiece() != null && event.getNewArmorPiece().getType()
	 * != Material.AIR) { event.getPlayer().sendMessage("Echo On"); for (int i =
	 * 0; i < items.size(); i++) for(int j = 0; j < armor.size() ; j++) if
	 * (items.get(i).getType().equals(armor.get(j))) if
	 * (items.get(i).getItem().equals(event.getNewArmorPiece())){
	 * event.getPlayer().sendMessage(event.getNewArmorPiece().toString() +
	 * " On");
	 * event.getPlayer().setHealth(event.getPlayer().getHealth()+items.get(i).
	 * getHp());
	 * 
	 * }
	 * 
	 * } if (event.getOldArmorPiece() != null &&
	 * event.getOldArmorPiece().getType() != Material.AIR) {
	 * event.getPlayer().sendMessage("Echo Off"); for (int i = 0; i <
	 * items.size(); i++) for(int j = 0; j < armor.size() ; j++) if
	 * (items.get(i).getType().equals(armor.get(j))) if
	 * (items.get(i).getItem().equals(event.getOldArmorPiece())){
	 * event.getPlayer().sendMessage(event.getOldArmorPiece().toString() +
	 * " Off");
	 * event.getPlayer().setHealth(event.getPlayer().getHealth()-items.get(i).
	 * getHp()); } } }
	 */

	@EventHandler
	public void onInventoryMove(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack armorParts[] = new ItemStack[4];

		armorParts[0] = player.getInventory().getChestplate();
		armorParts[1] = player.getInventory().getHelmet();
		armorParts[2] = player.getInventory().getLeggings();
		armorParts[3] = player.getInventory().getBoots();
		ItemStack current = event.getCurrentItem();
		ItemStack onCurrsor = event.getCursor();
		ArmorType armorType = ArmorType
				.matchType(event.getCurrentItem() != null && event.getCurrentItem().getType() != Material.AIR
						? event.getCurrentItem() : event.getCursor());
		for (int i = 0; i < items.size(); i++)
			for (int j = 0; j < armor.size(); j++) {
				if (items.get(i).getType().equals(armor.get(j))) {
					for (int g = 0; g < armorParts.length; g++)
						if (armorParts[g] != null && armorParts[g].getType() != Material.AIR) {
							if (items.get(i).getItem().equals(onCurrsor)) {

								player.sendMessage("Event On Slot.");
								// player.setMaxHealth(player.getMaxHealth() -
								// 10);
							}
						}
					if (items.get(i).getType().equals(armor.get(j))) {
						if (armorType != null && event.getRawSlot() == armorType.getSlot()) {
							if (items.get(i).getItem().equals(onCurrsor)) {

								player.sendMessage("Event On Currsor.");
								// player.setMaxHealth(player.getMaxHealth() +
								// 10);

							}
						}
					}
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
