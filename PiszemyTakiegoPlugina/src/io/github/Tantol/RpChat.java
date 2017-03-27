package io.github.Tantol;

import java.util.ArrayList;

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
	private static ArrayList<Player> me = new ArrayList<Player>();
	private static ArrayList<Player> rec = new ArrayList<Player>();

	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player s = (Player) cs;
		String msg = "";
		for(int i = 0; i < strings.length; i++)
		{
		    msg+=strings[i]+" ";
		}
		if (cmnd.getName().equalsIgnoreCase("ooc") && strings.length<1) {
			// s.sendMessage("Debug: ooc");
			ic.remove(s);
			shout.remove(s);
			whisper.remove(s);
			me.remove(s);
			ooc.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ooc") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(4000, s, msg, "[OOC]");
		}
		if (cmnd.getName().equalsIgnoreCase("ic") && strings.length<1) {
			// s.sendMessage("Debug: ic");
			ooc.remove(s);
			shout.remove(s);
			whisper.remove(s);
			me.remove(s);
			ic.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ic") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(30, s, msg, "[IC]");
		}
		if (cmnd.getName().equalsIgnoreCase("s") && strings.length<1) {
			// s.sendMessage("Debug: shout");
			ic.remove(s);
			ooc.remove(s);
			whisper.remove(s);
			me.remove(s);
			shout.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("s") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(60, s, msg, "[SHOUT]");
		}
		if (cmnd.getName().equalsIgnoreCase("w") && strings.length<1) {
			// s.sendMessage("Debug: whisper");
			ic.remove(s);
			shout.remove(s);
			ooc.remove(s);
			me.remove(s);
			whisper.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("w") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(5, s, msg, "[WHISPER]");
		}
		if (cmnd.getName().equalsIgnoreCase("me") && strings.length<1) {
			// s.sendMessage("Debug: whisper");
			ic.remove(s);
			shout.remove(s);
			ooc.remove(s);
			whisper.remove(s);
			me.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("me") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			me(30, s, msg);
		}
		return true;
	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		rec = new ArrayList<Player>(event.getRecipients());
		String s = event.getMessage();
		if (!ooc.contains(p) && !ic.contains(p) && !shout.contains(p) && !whisper.contains(p) && !me.contains(p)) {
			ic.add(p);
		}
		if (ooc.contains(p)) {
			message(4000, p, s, "[OOC]");
		}
		if (ic.contains(p)) {
			message(30, p, s, "[IC]");
		}
		if (whisper.contains(p)) {
			message(5, p, s, "[WHISPER]");
		}
		if (shout.contains(p)) {
			message(60, p, s, "[SHOUT]");
		}
		if (me.contains(p)) {
			me(30, p, s);
		}
		event.setCancelled(true);
	}

	public void message(int distance, Player p, String s, String chatType) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			p.sendMessage("test " + rec.get(i).getLocation().distance(player_loc));
			if (rec.get(i).getLocation().distance(player_loc) < distance
					&& rec.get(i).getLocation().distance(player_loc) > 0) {
				rec.get(i).sendMessage("test " + rec.get(i).getLocation().distance(player_loc));

				rec.get(i).sendMessage(ChatColor.RED + chatType + ChatColor.WHITE + p.getName() + ": " + s);
				if (flaga2 == 0) {
					p.sendMessage(ChatColor.RED + chatType + ChatColor.WHITE + p.getName() + ": " + s);
					flaga2 = 1;
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

	public void me(int distance, Player p, String s) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			p.sendMessage("test " + rec.get(i).getLocation().distance(player_loc));
			if (rec.get(i).getLocation().distance(player_loc) < distance
					&& rec.get(i).getLocation().distance(player_loc) > 0) {
				rec.get(i).sendMessage("test " + rec.get(i).getLocation().distance(player_loc));

				rec.get(i).sendMessage(ChatColor.YELLOW + p.getName() + " " + s);
				if (flaga2 == 0) {
					p.sendMessage(ChatColor.YELLOW + p.getName() + " " + s);
					flaga2 = 1;
				}
				flaga = 1;
			}
			if (flaga == 0 && rec.get(i).getLocation().distance(player_loc) != 0) {
				sprawdz += 1;
			}
		}
		if (sprawdz == rec.size() - 1) {
			p.sendMessage("Nikt nie widzial co robisz");
			p.sendMessage("flaga " + sprawdz);
		}
		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}

}
