package me.JayzaSapphire;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NodeFlyCommand implements CommandExecutor {
    
    private NodeFly plugin;
    public NodeFlyCommand(NodeFly plugin) {
        this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] a) {
        if (sender instanceof Player) {
            if (a.length == 0) {
                if (sender.hasPermission("nfly.use") || sender.hasPermission("nfly.*")) {
                    Player user = (Player) sender;
                    
                    if (plugin.getUser(user)) {
                        plugin.setUser(user, user.getGameMode(), false);
                        sender.sendMessage(ChatColor.DARK_AQUA + "You have disabled flying.");
                        return true;
                    } else {
                        plugin.setUser(user, user.getGameMode(), true);
                        sender.sendMessage(ChatColor.DARK_AQUA + "You have enabled flying.");
                        return true;
                    }
                } else {
                	sender.sendMessage(ChatColor.RED + "Long permission errors are overrated.");
                }
            }
            
            if (a.length == 1) {
                Player target = this.plugin.getServer().getPlayer(a[0]);
                
                if (target != null) {
                	if (target.getName().equalsIgnoreCase(sender.getName())) {
                    	if (sender.hasPermission("nfly.use") || sender.hasPermission("nfly.*")) {
                        	Player user = (Player) sender;
                        	
                        	if (plugin.getUser(user)) {
                        		plugin.setUser(user, user.getGameMode(), false);
                        		sender.sendMessage(ChatColor.DARK_AQUA + "You have disabled flying.");
                        		return true;
                    		} else {
                    		plugin.setUser(user, user.getGameMode(), true);
                        		sender.sendMessage(ChatColor.DARK_AQUA + "You have enabled flying.");
                        		return true;
                    		}
                    	} else {
                    		sender.sendMessage(ChatColor.RED + "Long permission errors are overrated.");
                    	}
                	} else {
                    	if (sender.hasPermission("nfly.other") || sender.hasPermission("nfly.*")) {
                    		if (plugin.getUser(target)) {
                    			plugin.setUser(target, target.getGameMode(), false);
                    			sender.sendMessage(ChatColor.DARK_AQUA + "You have disabled flying for " + target.getName() + ".");
                    			target.sendMessage(ChatColor.DARK_AQUA + sender.getName() + " disabled you to fly.");
                    			return true;
                    		} else {
                    			plugin.setUser(target, target.getGameMode(), true);
                    			sender.sendMessage(ChatColor.DARK_AQUA + "You have enabled flying for " + target.getName() + ".");
                    			target.sendMessage(ChatColor.DARK_AQUA + sender.getName() + " enabled you to fly.");
                    			return true;
                    		}
                    	} else {
                    		sender.sendMessage(ChatColor.RED + "Long permission errors are overrated.");
                    	}
                	}
                } else {
                	if (sender.hasPermission("nfly.use") || sender.hasPermission("nfly.other") || sender.hasPermission("nfly.*")) {
                        sender.sendMessage(ChatColor.RED + "Could not find " + ChatColor.GRAY + "Mr." + ChatColor.RED + a[0]);
                    } else {
                    	sender.sendMessage(ChatColor.RED + "Long permission errors are overrated.");
                    }
                }
            }
            
            if (a.length > 1) {
                if (sender.hasPermission("nfly.use") || sender.hasPermission("nfly.other") || sender.hasPermission("nfly.*")) {
                    sender.sendMessage(ChatColor.RED + "/fly | /fly <user>");
                } else {
                	sender.sendMessage(ChatColor.RED + "Long permission errors are overrated.");
                }
            }
        } else {
            if (a.length >= 0) {
                sender.sendMessage("Console cannot fly, unless you plan to throw it.");
            }
        }
		return false;
    }
}