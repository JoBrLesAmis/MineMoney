package fr.uxfuncraft.minemoney.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.uxfuncraft.minemoney.MineMoney;

public class GetBlacklistedWorldsCommand implements CommandExecutor {

	MineMoney plugin;
	
	public GetBlacklistedWorldsCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			if (sender.hasPermission("minemoney.getBlacklistedWorlds")) {
				List<String> worldName = plugin.config.getStringList("list.blacklist");
				
				if (worldName.size() > 0) {
					int pageMax = (int)Math.ceil(((double)worldName.size()) /10);
					
					sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getWorldBlacklistedTitle").replace("{ACTUAL_PAGE}", "1")
							.replace("{MAX_PAGE}", String.valueOf(pageMax)));
					
					for (int i = 0; i < 10; i++) {
						if (i < worldName.size()) {
							sender.sendMessage(plugin.lang.getString("information.getWorldBlacklistedList").replace("{WORLD_NUMBER}", String.valueOf(i + 1))
									.replace("{WORLD_NAME}", worldName.get(i)));
						} else
							break;
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.noBlacklistedWorlds"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		}
		else if (args.length == 1) {
			if (sender.hasPermission("minemoney.getBlacklistedWorlds")) {
				List<String> worldName = plugin.config.getStringList("list.blacklist");
				if (worldName.size() > 0) {
					if (worldName.size() <= 10) {
						sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getWorldBlacklistedTitle").replace("{ACTUAL_PAGE}", "1")
								.replace("{MAX_PAGE}", "1"));
						for (int i = 0; i < worldName.size(); i++) {
							sender.sendMessage(plugin.lang.getString("information.getWorldBlacklistedList").replace("{WORLD_NUMBER}", String.valueOf(i + 1))
									.replace("{WORLD_NAME}", worldName.get(i)));
						}
					} else {
						int pageMax = (int)Math.ceil(((double)worldName.size()) /10);
						sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getWorldBlacklistedTitle").replace("{ACTUAL_PAGE}", args[0])
								.replace("{MAX_PAGE}", String.valueOf(pageMax)));
						
						for (int i = ((Integer.valueOf(args[0]) - 1) * 10); i < (Integer.valueOf(args[0]) * 10); i++) {
							if (i < worldName.size())
							sender.sendMessage(plugin.lang.getString("information.getWorldBlacklistedList").replace("{WORLD_NUMBER}", String.valueOf(i + 1))
									.replace("{WORLD_NAME}", worldName.get(i)));
							else
								break;
						}
						
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.noBlacklistedWorlds"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		} else 
			sender.sendMessage("§4" + plugin.lang.getString("error.badGetBlacklistedWorldsUtilisation"));
		
		return true;
	}
	
}
