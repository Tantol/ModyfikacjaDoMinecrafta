package io.github.Tantol;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ChatOb {
	private String cmd;
	private int range;
	private String pref;
	private String type;
	private ArrayList<Player> list;
	public ChatOb(String cmd, int range, String pref, ArrayList<Player> list,String type){
		this.cmd = cmd;
		this.range = range;
		this.pref = pref;
		this.list = list;
		this.type = type;
	}
	public String getCmd(){return cmd;}
	public String getPref(){return pref;}
	public int getRange(){return range;}
	public ArrayList<Player> getList(){return list;}
	public String getType(){return type;}

}
