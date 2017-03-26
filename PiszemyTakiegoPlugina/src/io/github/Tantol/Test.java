package io.github.Tantol;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player s = (Player) cs;
		if (cmnd.getName().equalsIgnoreCase("jebiecimatke")) {
			s.sendMessage("Dzia³a!!!");
		}
		return false;
	}

}