package NewItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ItemCommand implements CommandExecutor, Listener {

	ArrayList<CreateItem> items = new ArrayList<CreateItem>();

	public ItemCommand() {
		items.add(new CreateItem(Material.DIAMOND_SWORD, "Testowy Miecz", Arrays.asList("123", "321"), 1.0d, 30.0d,
				"WEAPON"));
		items.add(new CreateItem(Material.DIAMOND_CHESTPLATE, "Testowa Zbroja", Arrays.asList("123", "321"), 5.0d,
				"ARMOR"));
	}

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {

		Player pl = (Player) cs;
		int foo = Integer.parseInt(strings[0]);
		if (string.equalsIgnoreCase("newitem")) {
			if (strings.length == 0) {
				cs.sendMessage("Podaj argument");
			} else {
				cs.sendMessage("item crated");
				pl.getInventory().addItem(items.get(foo).getItem());
			}

		}
		return false;
	}

	@EventHandler
	public void onEntityDamage(EntityDamageByEntityEvent event) {
		Random r = new Random();
		ItemStack itemArmor;

		if (event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack itemMain = player.getInventory().getItemInMainHand();
			// 
			for (int i = 0; i < items.size(); i++) {

				if (items.get(i).getType() == "WEAPON") {
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
			Player defender = (Player) event.getEntity() ;
			itemArmor = defender.getInventory().getChestplate();
			for (int i = 0; i < items.size(); i++) {
			if (items.get(i).getType() == "ARMOR") {
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

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
