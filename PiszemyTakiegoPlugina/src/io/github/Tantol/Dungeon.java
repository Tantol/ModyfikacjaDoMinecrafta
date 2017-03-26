package io.github.Tantol;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Dungeon extends JavaPlugin {



@Override
public void onEnable() {
}
@Override
public void onDisable() {
}
public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
    Player p = (Player) sender;
    if(command.getName().equalsIgnoreCase("jebiecimatke")){
    	p.sendMessage("Funkcja Wprowadzona");
    	return true;
    }
    else return false;
}
}