package fr.uxfuncraft.minemoney.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.uxfuncraft.minemoney.MineMoney;

public class RemoveWorldBlacklistCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public RemoveWorldBlacklistCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			if (sender.hasPermission("minemoney.removeworldblacklisted")) {
				World world = plugin.getServer().getWorld(args[0]);
				if (world != null) {
					if ((plugin.blacklist != null) && plugin.blacklist.contains(world)) {
						int index = plugin.blacklist.indexOf(world);
						List<String> worldName = plugin.config.getStringList("list.blacklist");
						
						plugin.blacklist.remove(index);
						worldName.remove(index);
						plugin.config.set("list.blacklist", worldName);
						try {
							plugin.config.save(plugin.getConfigFile());
							sender.sendMessage("§2" + plugin.lang.getString("success.removeWorld").replace("{WORLD_NAME}", world.getName()));
						} catch (IOException e) {
							e.printStackTrace();
							sender.sendMessage("§4" + plugin.lang.getString("error.whileRemovingWorldBlacklist").replace("{WORLD_NAME}", world.getName()));
						}
						
					} else
						sender.sendMessage("§4" + plugin.lang.getString("error.worldNotInBlacklist"));
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.worldDoesntExist"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		} else 
			sender.sendMessage("§4" + plugin.lang.getString("error.badRemoveWorldBlacklistUtilisation"));
		
		return true;
	}

}
