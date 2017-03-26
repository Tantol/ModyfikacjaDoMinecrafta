package io.github.Tantol.Template;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
 
class Both implements CommandExecutor, Listener{
 
 
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (string.equalsIgnoreCase("testchat")) {
            cs.sendMessage("hello");
        }
        return false;
    }
   
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Hello");
    }
 
}