package io.github.Tantol.NewItems;

import org.bukkit.entity.Player;

public class ItemPlayer {
	int[] armorCheck = { 0, 0, 0, 0, 0 };
	Player player;

	public ItemPlayer(Player player) {
		this.player = player;
	}

	public int getArmorCheck(int i) {
		return armorCheck[i];
	}

	public void setArmorCheck(int i, int j) {
		armorCheck[i] = j;
	}
}
