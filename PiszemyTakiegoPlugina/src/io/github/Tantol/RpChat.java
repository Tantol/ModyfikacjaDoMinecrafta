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
	private static ArrayList<Player> narrator = new ArrayList<Player>();
	private static ArrayList<Player> gnarrator = new ArrayList<Player>();
	int ooc_range = 4000;
	int ic_range = 30;
	int shout_range = 60;
	int whisper_range = 5;
	int me_range = 30;
	int narrator_range = 300;
	int gnarrator_range = 4000;

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
			narrator.remove(s);
			gnarrator.remove(s);
			ooc.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ooc") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(ooc_range, s, msg, ChatColor.RED + "[OOC]");
		}
		if (cmnd.getName().equalsIgnoreCase("ic") && strings.length<1) {
			// s.sendMessage("Debug: ic");
			ooc.remove(s);
			shout.remove(s);
			whisper.remove(s);
			me.remove(s);
			narrator.remove(s);
			gnarrator.remove(s);
			ic.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ic") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(ic_range, s, msg,ChatColor.GREEN + "[IC]");
		}
		if (cmnd.getName().equalsIgnoreCase("s") && strings.length<1) {
			// s.sendMessage("Debug: shout");
			ic.remove(s);
			ooc.remove(s);
			whisper.remove(s);
			me.remove(s);
			narrator.remove(s);
			gnarrator.remove(s);
			shout.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("s") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(shout_range, s, msg,ChatColor.BLUE + "[SHOUT]");
		}
		if (cmnd.getName().equalsIgnoreCase("w") && strings.length<1) {
			// s.sendMessage("Debug: whisper");
			ic.remove(s);
			shout.remove(s);
			ooc.remove(s);
			me.remove(s);
			narrator.remove(s);
			gnarrator.remove(s);
			whisper.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("w") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(whisper_range, s, msg,ChatColor.GRAY +  "[WHISPER]");
		}
		if (cmnd.getName().equalsIgnoreCase("n") && strings.length<1) {
			// s.sendMessage("Debug: whisper");
			ic.remove(s);
			shout.remove(s);
			ooc.remove(s);
			me.remove(s);
			whisper.remove(s);
			gnarrator.remove(s);
			narrator.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("n") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(narrator_range, s, msg,ChatColor.AQUA +  "[NARRATOR]");
		}
		if (cmnd.getName().equalsIgnoreCase("ng") && strings.length<1) {
			// s.sendMessage("Debug: whisper");
			ic.remove(s);
			shout.remove(s);
			ooc.remove(s);
			me.remove(s);
			narrator.remove(s);
			whisper.remove(s);
			gnarrator.add(s);
		}
		if (cmnd.getName().equalsIgnoreCase("ng") && strings.length>=1) {
			rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
			message(gnarrator_range, s, msg,ChatColor.AQUA +  "[NARRATOR GLOBALNY]");
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
			me(me_range, s, msg);
		}
		return true;
	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		rec = new ArrayList<Player>(event.getRecipients());
		String s = event.getMessage();
		if (!ooc.contains(p) && !ic.contains(p) && !shout.contains(p) && !whisper.contains(p) && !me.contains(p) && !narrator.contains(p)  && !gnarrator.contains(p)) {
			ic.add(p);
		}
		if (ooc.contains(p)) {
			message(ooc_range, p, s,ChatColor.RED + "[OOC]");
		}
		if (ic.contains(p)) {
			message(ic_range, p, s,ChatColor.GREEN + "[IC]");
		}
		if (whisper.contains(p)) {
			message(whisper_range, p, s,ChatColor.GRAY + "[WHISPER]");
		}
		if (shout.contains(p)) {
			message(shout_range, p, s,ChatColor.BLUE + "[SHOUT]");
		}
		if (me.contains(p)) {
			me(me_range, p, s);
		}
		if (narrator.contains(p)) {
			message(narrator_range, p, s, ChatColor.AQUA +  "[NARRATOR]");
		}
		if (gnarrator.contains(p)) {
			message(gnarrator_range, p, s, ChatColor.AQUA +  "[NARRATOR GLOBALNY]");
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

				rec.get(i).sendMessage(chatType + ChatColor.WHITE + p.getName() + ": " + s);
				if (flaga2 == 0) {
					p.sendMessage(chatType + ChatColor.WHITE + p.getName() + ": " + s);
					flaga2 = 1;
				}
				flaga = 1;
			}
			if (flaga == 0 && rec.get(i).getLocation().distance(player_loc) != 0) {
				sprawdz += 1;
			}
		}
		if (sprawdz == rec.size() - 1) {
			p.sendMessage(chatType + ": " + ChatColor.RED + "[!] "+ChatColor.WHITE+"Nikt Cie nie slyszal"+ChatColor.RED + " [!]");
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
			p.sendMessage(ChatColor.RED + "[!] "+ChatColor.YELLOW+"Nikt nie widzial co robisz"+ChatColor.RED + " [!]");
			p.sendMessage("flaga " + sprawdz);
		}
		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}

}
