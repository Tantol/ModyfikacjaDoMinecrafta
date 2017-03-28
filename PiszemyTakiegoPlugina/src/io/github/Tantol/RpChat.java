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
	private static ArrayList<ChatOb> chat = new ArrayList<ChatOb>();
	private static ArrayList<Player> rec = new ArrayList<Player>();
	private String msg_echo,narrator_echo,me_echo;
	static int flaga_c=0;
	


	public RpChat() {
		if(flaga_c==0){
			
			/*
			 * Tutaj Poni¿ej Edytowaæ
			 */
		//Chaty, nie zapomnij dodaæ w mainie i w plugy.yml
		
		chat.add(new ChatOb("ic", 30, ChatColor.GREEN + "[IC]", new ArrayList<Player>(), "msg","Czat ogolny in character. Zasieg:[30]"));
		chat.add(new ChatOb("ooc", 99999, ChatColor.RED + "[OOC]", new ArrayList<Player>(), "msg","Czat ogolny out of character. Zasieg:[Cala Mapa]"));
		chat.add(new ChatOb("w", 5, ChatColor.GRAY + "[WHISPER]", new ArrayList<Player>(), "msg","Czat szeptu. Zasieg:[5]"));
		chat.add(new ChatOb("s", 60, ChatColor.BLUE + "[SHOUT]", new ArrayList<Player>(), "msg","Czat krzyku. Zasieg:[60]"));
		chat.add(new ChatOb("me", 30, ChatColor.YELLOW + "", new ArrayList<Player>(), "me","Czat aktywnosci. Zasieg[30]"));
		chat.add(new ChatOb("n", 300, ChatColor.AQUA + "[NARRATOR]", new ArrayList<Player>(), "nar","Czat narratora. Zasieg[300], Wymagana permisja"));
		chat.add(new ChatOb("ng", 99999, ChatColor.AQUA + "[NARRATOR GLOBALNY]", new ArrayList<Player>(), "nar","Czat narratora. Zasieg[Cala Mapa], Wymagana permisja"));
		}
		
		//Wiadomosc, ktora otrzymamy gdy nikogo nie bedzie w zasiegu chatu
		msg_echo = " " + ChatColor.RED + "[!] " + ChatColor.WHITE + "Nikt Cie nie slyszal" + ChatColor.RED + " [!]";
		narrator_echo = " " + ChatColor.RED + "[!] " + ChatColor.WHITE + "Nikt Cie nie slyszal" + ChatColor.RED + " [!]";
		me_echo = ChatColor.RED + "[!] " + ChatColor.YELLOW + "Nikt nie widzial co robisz" + ChatColor.RED + " [!]";
		
		
		/*
		 * Tego co pod tym lepiej nie tykaæ jak nie wiesz co i jak ^^
		 */
		flaga_c=1;
	}

	
	public boolean onCommand(CommandSender cs, org.bukkit.command.Command cmnd, String string, String[] strings) {
		Player s = (Player) cs;
		String msg = "";
		for (int i = 0; i < strings.length; i++) {
			msg += strings[i] + " ";
		}

		int flaga = 0;
		for (int i = 0; i < chat.size(); i++) {
			if (cmnd.getName().equalsIgnoreCase(chat.get(i).getCmd()) && strings.length >= 1 && flaga == 0) {
				rec = new ArrayList<Player>(Dungeon.getPlugin().getServer().getOnlinePlayers());
				if (chat.get(i).getType() == "msg")
					message(chat.get(i).getRange(), s, msg, chat.get(i).getPref());
				if (chat.get(i).getType() == "me")
					me(chat.get(i).getRange(), s, msg, chat.get(i).getPref());
				if (chat.get(i).getType() == "nar")
					narrator(chat.get(i).getRange(), s, msg, chat.get(i).getPref());
				flaga = 1;
			}
			if (cmnd.getName().equalsIgnoreCase(chat.get(i).getCmd()) && strings.length < 1) {
				for (int j = 0; j < chat.size(); j++) {
					chat.get(j).getList().remove(s);
				}
				chat.get(i).getList().add(s);

			}
		}
		if(cmnd.getName().equalsIgnoreCase("rpchat")){
			String info = "";
			
			for (int i = 0; i < chat.size(); i++) {
				info+="/"+chat.get(i).getCmd()+"     "+chat.get(i).getDesc()+"\n";	
				
				}
			s.sendMessage(info);
		}
		flaga = 0;
		return true;
	}

	@EventHandler
	public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		rec = new ArrayList<Player>(event.getRecipients());
		String s = event.getMessage();
		int flaga = 0;
		for (int i = 0; i < chat.size(); i++) {
			if (!chat.get(i).getList().contains(p)) {
				flaga++;
			}
		}
		if (flaga == chat.size()) {
			chat.get(0).getList().add(p);
		}

		for (int i = 0; i < chat.size(); i++) {
			if (chat.get(i).getList().contains(p)) {
				if (chat.get(i).getType() == "msg")
					message(chat.get(i).getRange(), p, s, chat.get(i).getPref());
				if (chat.get(i).getType() == "me")
					me(chat.get(i).getRange(), p, s, chat.get(i).getPref());
				if (chat.get(i).getType() == "nar")
					narrator(chat.get(i).getRange(), p, s, chat.get(i).getPref());
			}
		}
		event.setCancelled(true);

	}

	public void message(int distance, Player p, String s, String chatType) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			if (rec.get(i).getLocation().distance(player_loc) < distance
					&& rec.get(i).getLocation().distance(player_loc) > 0) {
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
			p.sendMessage(chatType + msg_echo);
		}
		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}

	public void me(int distance, Player p, String s, String chatType) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			if (rec.get(i).getLocation().distance(player_loc) < distance
					&& rec.get(i).getLocation().distance(player_loc) > 0) {

				rec.get(i).sendMessage(chatType + p.getName() + " " + s);
				if (flaga2 == 0) {
					p.sendMessage(chatType + p.getName() + " " + s);
					flaga2 = 1;
				}
				flaga = 1;
			}
			if (flaga == 0 && rec.get(i).getLocation().distance(player_loc) != 0) {
				sprawdz += 1;
			}
		}
		if (sprawdz == rec.size() - 1) {
			p.sendMessage(me_echo);
		}
		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}

	public void narrator(int distance, Player p, String s, String chatType) {
		int flaga = 0;
		int flaga2 = 0;
		int sprawdz = 0;
		Location player_loc = p.getLocation();
		for (int i = 0; i < rec.size(); i++) {
			if (rec.get(i).getLocation().distance(player_loc) < distance
					&& rec.get(i).getLocation().distance(player_loc) > 0) {

				rec.get(i).sendMessage(chatType + ChatColor.WHITE + ": " + s);
				if (flaga2 == 0) {
					p.sendMessage(chatType + ChatColor.RED + "["+(rec.size()-1)+"]" + ChatColor.WHITE + ": " + s);
					flaga2 = 1;
				}
				flaga = 1;
			}
			if (flaga == 0 && rec.get(i).getLocation().distance(player_loc) != 0) {
				sprawdz += 1;
			}
		}
		if (sprawdz == rec.size() - 1) {
			p.sendMessage(
					chatType + narrator_echo);
		}
		flaga = 0;
		flaga2 = 0;
		sprawdz = 0;
		rec = new ArrayList<Player>();
	}
}
