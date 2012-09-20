package me.JayzaSapphire;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class NodeFlyListener implements Listener {
    
    private NodeFly plugin;
    public NodeFlyListener(NodeFly plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player user = event.getPlayer();
        
        plugin.check(user);
        
        if (plugin.getUser(user)) {
        	plugin.setUser(user, user.getGameMode(), true);
        }
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
    	final Player user = event.getPlayer();
    	
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
        	public void run() {
        		if (plugin.getUser(user)) {
        			plugin.setUser(user, user.getGameMode(), true);
        		}
        	}
        }, 1);
    }
    
    @EventHandler
    public void onGameMode(PlayerGameModeChangeEvent event) {
    	final Player user = event.getPlayer();
    	
    	plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    		public void run() {
    			if (plugin.getUser(user)) {
    				plugin.setUser(user, user.getGameMode(), true);
    			}
    		}
    	}, 1);
    }
    
    @EventHandler
    public void onChangeWorld(PlayerChangedWorldEvent event) {
        Player user = event.getPlayer();
        GameMode mode = user.getGameMode();
        
        if (this.plugin.getUser(user)) {
            this.plugin.setUser(user, mode, false);
        }
    }
}