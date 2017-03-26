package io.github.Tantol;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RpChat implements CommandExecutor, Listener {

	private ArrayList<String> ooc = new ArrayList<String>();
	private ArrayList<String> ic = new ArrayList<String>();
	private ArrayList<String> shout = new ArrayList<String>();
	private ArrayList<String> whisper = new ArrayList<String>();
	
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player p = (Player) cs;
		String s = p.getName();
			if (cmnd.getName().equalsIgnoreCase("ooc")) {
			p.sendMessage("Debug: OOC: Plaer Name: "+ s);
			if (ic.contains(s))
				ic.remove(s);
			if (shout.contains(s))
				shout.remove(s);
			if (whisper.contains(s))
				whisper.remove(s);
			ooc.add(s);
		}
		return true;

	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		String s = p.getName();
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Dungeon.getPlugin(), new Runnable() {
			public void run() {
				if (ooc.contains(s)) {
					event.setFormat(ChatColor.RED + "[OOC] " + ChatColor.WHITE + "%s" + ": " + "%s");
					p.sendMessage("Debug Player = " +  ooc.contains(s)+ " Plaer Name: "+ s);
				}
				else{
					p.sendMessage("Debug Player = " +  ooc.contains(s)+ " Plaer Name: "+ s);
				}

		}
		}, 0, 40);

	}
}
