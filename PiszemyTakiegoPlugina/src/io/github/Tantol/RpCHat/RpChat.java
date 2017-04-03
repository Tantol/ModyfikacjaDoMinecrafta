package io.github.Tantol.RpCHat;

    import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import io.github.Tantol.Dungeon;

public class RpChat implements CommandExecutor, Listener {
	private static ArrayList<ChatOb> chat = new ArrayList<ChatOb>();
	private static ArrayList<Player> rec = new ArrayList<Player>();
	private String msg_echo,narrator_echo,me_echo;
	static int flaga_c=0;
	Dungeon main;


	public RpChat() {
		if(flaga_c==0){
		chat.add(new ChatOb("ic", Dungeon.chatRP.getCustomConfig().getInt("ic.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("ic.color")) + Dungeon.chatRP.getCustomConfig().getString("ic.pref"), new ArrayList<Player>(), "msg",Dungeon.chatRP.getCustomConfig().getString("ic.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("ic.range")+"]"));
		chat.add(new ChatOb("ooc", Dungeon.chatRP.getCustomConfig().getInt("ooc.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("ooc.color")) + Dungeon.chatRP.getCustomConfig().getString("ooc.pref"), new ArrayList<Player>(), "msg",Dungeon.chatRP.getCustomConfig().getString("ooc.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("ooc.range")+"]"));
		chat.add(new ChatOb("w", Dungeon.chatRP.getCustomConfig().getInt("w.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("w.color")) + Dungeon.chatRP.getCustomConfig().getString("w.pref"), new ArrayList<Player>(), "msg",Dungeon.chatRP.getCustomConfig().getString("w.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("w.range")+"]"));
		chat.add(new ChatOb("s", Dungeon.chatRP.getCustomConfig().getInt("s.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("s.color")) + Dungeon.chatRP.getCustomConfig().getString("s.pref"), new ArrayList<Player>(), "msg",Dungeon.chatRP.getCustomConfig().getString("s.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("s.range")+"]"));
		chat.add(new ChatOb("me", Dungeon.chatRP.getCustomConfig().getInt("me.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("me.color")) + Dungeon.chatRP.getCustomConfig().getString("me.pref"), new ArrayList<Player>(), "me",Dungeon.chatRP.getCustomConfig().getString("me.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("me.range")+"]"));
		chat.add(new ChatOb("n", Dungeon.chatRP.getCustomConfig().getInt("n.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("n.color")) + Dungeon.chatRP.getCustomConfig().getString("n.pref"), new ArrayList<Player>(), "nar",Dungeon.chatRP.getCustomConfig().getString("n.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("n.range")+"]"));
		chat.add(new ChatOb("ng", Dungeon.chatRP.getCustomConfig().getInt("ng.range"), ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("ng.color")) + Dungeon.chatRP.getCustomConfig().getString("ng.pref"), new ArrayList<Player>(), "nar",Dungeon.chatRP.getCustomConfig().getString("ng.desc")+ " ["+Dungeon.chatRP.getCustomConfig().getInt("ng.range")+"]"));
		}
		
		msg_echo = ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.markcolor")) + "[!] " + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.textcolor")) + Dungeon.chatRP.getCustomConfig().getString("echo.msg.text") + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.markcolor")) + " [!]";
		narrator_echo =ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.markcolor")) + "[!] " + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.textcolor")) + Dungeon.chatRP.getCustomConfig().getString("echo.narrator.text") + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.markcolor")) + " [!]";
		me_echo = ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.markcolor")) + "[!] " + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.textcolor")) + Dungeon.chatRP.getCustomConfig().getString("echo.me.text") + ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.markcolor")) + " [!]";
		
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
					if(Dungeon.chatRP.getCustomConfig().getBoolean("echo.msg.showcounter") == true)
					p.sendMessage(ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.counterbrackletcolor")) + "["+ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.counternumbercolor"))+(rec.size()-1)+ ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.msg.counterbrackletcolor")) +"]"+chatType + ChatColor.WHITE + p.getName() + ": " + s);
					else
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
					if(Dungeon.chatRP.getCustomConfig().getBoolean("echo.me.showcounter") == true)
						p.sendMessage(ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.counterbrackletcolor")) + "["+ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.counternumbercolor"))+(rec.size()-1)+ ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.me.counterbrackletcolor")) +"]"+chatType + p.getName() + " " + s);	
					else
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
					if(Dungeon.chatRP.getCustomConfig().getBoolean("echo.narrator.showcounter") == true)
					p.sendMessage(ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.counterbrackletcolor")) + "["+ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.counternumbercolor"))+(rec.size()-1)+ ChatColor.translateAlternateColorCodes ('&',Dungeon.chatRP.getCustomConfig().getString("echo.narrator.counterbrackletcolor")) +"]" + chatType + ChatColor.WHITE + ": " + s);
					else
						p.sendMessage(chatType + ": " + ChatColor.WHITE + s);	
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
