package io.github.Tantol.NewItems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CreateItem {
	private ItemStack newItem;
	private double minDmg,maxDmg,def,hp;
	private boolean antiFall;
	private String type;
	ArrayList<String> potionEff;
	private int potionEffGrade;
	float speed;
	public CreateItem(Material mat, String name, List<String> lore, double minDmg, double maxDmg,String type) {
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.type = type;
		newItem = makeItem(new ItemStack(mat), name, lore);
		//
	}
	public CreateItem(Material mat, String name, List<String> lore, double def, String type, double hp , float speed, boolean antiFall, ArrayList<String> potionEff, int potionEffGrade) {
		this.def = def;
		this.type = type;
		this.hp = hp;
		this.speed = speed;
		this.antiFall = antiFall;
		this.potionEff = potionEff;
		this.potionEffGrade = potionEffGrade;
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
	public double getHp(){
		return hp;
	}
	public float getSpeed(){
		return speed;
	}
	public boolean getFall(){
		return antiFall;
	}
	public ArrayList<String> getPotion(){
		return potionEff;
	}
	public int getPotionGrade(){
		return potionEffGrade;
	}
}
