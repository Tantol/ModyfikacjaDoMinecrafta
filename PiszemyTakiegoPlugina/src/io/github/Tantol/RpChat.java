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
	private static ArrayList<Player> rec = new ArrayList<Player>();

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player s = (Player) cs;
		if (cmnd.getName().equalsIgnoreCase("ooc")) {
			// s.sendMessage("Debug: ooc");
			if (ic.contains(s))
				ic.remove(s);
			if (shout.contains(s))
				shout.remove(s);
			if (whisper.contains(s))
				whisper.remove(s);
			ooc.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ic")) {
			// s.sendMessage("Debug: ic");
			if (ooc.contains(s))
				ooc.remove(s);
			if (shout.contains(s))
				shout.remove(s);
			if (whisper.contains(s))
				whisper.remove(s);
			ic.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("s")) {
			// s.sendMessage("Debug: shout");
			if (ic.contains(s))
				ic.remove(s);
			if (ooc.contains(s))
				ooc.remove(s);
			if (whisper.contains(s))
				whisper.remove(s);
			shout.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("w")) {
			// s.sendMessage("Debug: whisper");
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
		rec = new ArrayList<Player>(event.getRecipients());
		String s = event.getMessage();
		message(p, s);
		event.setCancelled(true);
	}

	public void message(Player p, String s) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			p.sendMessage("test " + rec.get(i).getLocation().distance(player_loc));
			if (rec.get(i).getLocation().distance(player_loc) < 30
					&& rec.get(i).getLocation().distance(player_loc) > 0) {
				rec.get(i).sendMessage("test " + rec.get(i).getLocation().distance(player_loc));
				if (!ooc.contains(p) && !ic.contains(p) && !shout.contains(p) && !whisper.contains(p)) {
					if (ooc.contains(s))
						ooc.remove(s);
					if (shout.contains(s))
						shout.remove(s);
					if (whisper.contains(s))
						whisper.remove(s);
					ic.add(p);
				}
				if (ooc.contains(p)) {
					rec.get(i).sendMessage(ChatColor.RED + "[OOC]" + ChatColor.WHITE + p.getName() + ": " + s);
					if (flaga2 == 0) {
						p.sendMessage(ChatColor.RED + "[OOC]" + ChatColor.WHITE + p.getName() + ": " + s);
						flaga2 = 1;
					}
				}
				if (ic.contains(p)) {
					rec.get(i).sendMessage(ChatColor.RED + "[IC]" + ChatColor.WHITE + p.getName() + ": " + s);
					if (flaga2 == 0) {
						p.sendMessage(ChatColor.RED + "[IC]" + ChatColor.WHITE + p.getName() + ": " + s);
						flaga2 = 1;
					}
				}
				if (whisper.contains(p)) {
					rec.get(i).sendMessage(ChatColor.RED + "[WHISPER]" + ChatColor.WHITE + p.getName() + ": " + s);
					if (flaga2 == 0) {
						p.sendMessage(ChatColor.RED + "[WHISPER]" + ChatColor.WHITE + p.getName() + ": " + s);
						flaga2 = 1;
					}
				}
				if (shout.contains(p)) {
					rec.get(i).sendMessage(ChatColor.RED + "[SHOUT]" + ChatColor.WHITE + p.getName() + ": " + s);
					if (flaga2 == 0) {
						p.sendMessage(ChatColor.RED + "[SHOUT]" + ChatColor.WHITE + p.getName() + ": " + s);
						flaga2 = 1;
					}

				}
				flaga = 1;
			}
			if (flaga == 0 && rec.get(i).getLocation().distance(player_loc) != 0) {
				sprawdz += 1;
			}
		}
		if (sprawdz == rec.size() - 1) {
			p.sendMessage("Nikt Cie nie slyszal");
			p.sendMessage("flaga " + sprawdz);
		}

		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}

}
