package NewItems;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {
	private ItemStack newItem;
	private double minDmg;
	private double maxDmg;
	private double def;
	private String type;
	public CreateItem(Material mat, String name, List<String> lore, double minDmg, double maxDmg,String type) {
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.type = type;
		newItem = makeItem(new ItemStack(mat), name, lore);
		
	}
	public CreateItem(Material mat, String name, List<String> lore, double def, String type) {
		this.def = def;
		this.type = type;
		newItem = makeItem(new ItemStack(mat), name, lore);

	}
	public ItemStack makeItem(ItemStack mat, String name, List<String> lore) {
		ItemMeta item = mat.getItemMeta();
		item.setDisplayName(name);
		item.setLore(lore);
		item.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		mat.setItemMeta(item);
		return mat;
	}

	public ItemStack getItem() {
		return newItem;
	}

	public double getMinDmg() {
		return minDmg;
	}

	public double getMaxDmg() {
		return maxDmg;
	}
	public double getDef() {
		return def;
	}
	public String getType(){
		return type;
	}
}
