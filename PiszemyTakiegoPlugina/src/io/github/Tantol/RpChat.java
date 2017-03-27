package io.github.Tantol;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class RpChat implements CommandExecutor, Listener {

	private static ArrayList<Player> ooc = new ArrayList<Player>();
	private static ArrayList<Player> ic = new ArrayList<Player>();
	private static ArrayList<Player> shout = new ArrayList<Player>();
	private static ArrayList<Player> whisper = new ArrayList<Player>();
	
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player s = (Player) cs;
		if (cmnd.getName().equalsIgnoreCase("ooc")) {
		//s.sendMessage("Debug: ooc");
			if (ic.contains(s))
				ic.remove(s);
			if (shout.contains(s))
				shout.remove(s);
			if (whisper.contains(s))
				whisper.remove(s);
			ooc.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ic")) {
			//s.sendMessage("Debug: ic");
				if (ooc.contains(s))
					ooc.remove(s);
				if (shout.contains(s))
					shout.remove(s);
				if (whisper.contains(s))
					whisper.remove(s);
				ic.add(s);
			}
		if (cmnd.getName().equalsIgnoreCase("s")) {
			//s.sendMessage("Debug: shout");
				if (ic.contains(s))
					ic.remove(s);
				if (ooc.contains(s))
					ooc.remove(s);
				if (whisper.contains(s))
					whisper.remove(s);
				shout.add(s);
			}
		if (cmnd.getName().equalsIgnoreCase("w")) {
			//s.sendMessage("Debug: whisper");
				if (ic.contains(s))
					ic.remove(s);
				if (shout.contains(s))
					shout.remove(s);
				if (ooc.contains(s))
					ooc.remove(s);
				whisper.add(s);
			}
		return true;

	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		Location player_loc = p.getLocation();
		
		if (ooc.contains(p)) {
			event.setFormat(ChatColor.RED + "[OOC] " + ChatColor.WHITE + "%s" + ": " + "%s");
			//p.sendMessage("Debug Player =  OOC " +  ooc.contains(p));
		}
		else if (ic.contains(p)) {
			event.setFormat(ChatColor.RED + "[IC] " + ChatColor.WHITE + "%s" + ": " + "%s");
			//p.sendMessage("Debug Player = IC " +  ic.contains(p));
		}
		else if (whisper.contains(p)) {
			event.setFormat(ChatColor.RED + "[WHISPER] " + ChatColor.WHITE + "%s" + ": " + "%s");
			//p.sendMessage("Debug Player = WHISPER " +  whisper.contains(p));
		}
		else if (shout.contains(p)) {
			event.setFormat(ChatColor.RED + "[SHOUT] " + ChatColor.WHITE + "%s" + ": " + "%s");
			//p.sendMessage("Debug Player = SHOUT" +  shout.contains(p));
		}
		else{
			//p.sendMessage("Debug Player = OOC " +  ooc.contains(p) + " IC " +  ic.contains(p)+ " WHISPER " +  whisper.contains(p)+  " SHOUT " + shout.contains(p));
		}
		
		/*for(Player other : event.getRecipients()){
			
			if(other.getLocation().distance(player_loc) < 30){
				other.sendMessage(event.getMessage());
			}
			else{
				p.sendMessage("Nikt cie nie slyszal");
				
			}
		}*/
				
	}
}
