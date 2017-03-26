package io.github.Tantol.Template;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

class Command implements CommandExecutor {

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		if (string.equalsIgnoreCase("hi")) {
			cs.sendMessage("hi");
		}
		return false;
	}

}
