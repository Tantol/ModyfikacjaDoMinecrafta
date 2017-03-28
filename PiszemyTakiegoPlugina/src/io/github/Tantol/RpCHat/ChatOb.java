package io.github.Tantol.RpCHat;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class ChatOb {
	private int range;
	private String pref,type,cmd,desc;
	private ArrayList<Player> list;
	public ChatOb(String cmd, int range, String pref, ArrayList<Player> list,String type,String desc){
		this.cmd = cmd;
		this.range = range;
		this.pref = pref;
		this.list = list;
		this.type = type;
		this.desc = desc;
	}
	public String getCmd(){return cmd;}
	public String getPref(){return pref;}
	public int getRange(){return range;}
	public ArrayList<Player> getList(){return list;}
	public String getType(){return type;}
	public String getDesc(){return desc;}
}
