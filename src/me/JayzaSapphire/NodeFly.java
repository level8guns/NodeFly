package me.JayzaSapphire;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class NodeFly extends JavaPlugin {
    
    public void onEnable() {
    	getConfig().options().copyDefaults(true);
    	saveConfig();
    	reloadConfig();
    	
    	fixConfig();
    	
    	getCommand("fly").setExecutor(new NodeFlyCommand(this));
    	
        getServer().getPluginManager().registerEvents(new NodeFlyListener(this), this);
        
        for (Player user : getServer().getOnlinePlayers()) {
            check(user);
            
            if (getUser(user)) {
                user.setAllowFlight(true);
            }
        }
    }
    
    public boolean getUser(Player user) {
        String name = user.getName();
        
        check(user);
        
        return getConfig().getBoolean("users." + name);
    }
    
    public void check(Player user) {
        String name = user.getName();
        
        if (getConfig().getString("users." + name) == null) {
            getConfig().set("users." + name, false);
            saveConfig();
        }
    }
    
    public void setUser(Player user, GameMode mode, Boolean bool) {
        check(user);
        
        String name = user.getName();
        
        if (bool) {
            getConfig().set("users." + name, true);
            saveConfig();
            
            user.setAllowFlight(true);
        } else {
            getConfig().set("users." + name, false);
            saveConfig();
            
            if (mode != GameMode.CREATIVE) {
                user.setFlying(false);
                user.setAllowFlight(false);
            }
        }
    }
    
    private void fixConfig() {
    	if (getConfig().getString("keep-enabled") != null) {
    		getConfig().set("keep-enabled", null);
    		saveConfig();
    	}
    	
    	if (getConfig().getString("disabled-worlds") != null) {
    		getConfig().set("disabled-worlds", null);
    		saveConfig();
    	}
    }
    
    public void onDisable() {
        
    }
}