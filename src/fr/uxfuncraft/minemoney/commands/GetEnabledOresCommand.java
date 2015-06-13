package fr.uxfuncraft.minemoney.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.uxfuncraft.minemoney.MineMoney;

public class GetEnabledOresCommand implements CommandExecutor {

	MineMoney plugin;
	
	public GetEnabledOresCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			if (sender.hasPermission("minemoney.getEnabledOres")) {
				List<String> oreName = plugin.config.getStringList("list.materials");
				List<Double> oreReward = plugin.config.getDoubleList("list.prices");
				
				if (oreName.size() > 0) {
					int pageMax = (int)Math.ceil(((double)oreName.size()) /10);
					
					sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getBlocksListTitle").replace("{ACTUAL_PAGE}", "1")
							.replace("{MAX_PAGE}", String.valueOf(pageMax)));
					
					for (int i = 0; i < 10; i++) {
						if (i < oreName.size()) {
							sender.sendMessage(plugin.lang.getString("information.getBlocksList").replace("{ORE_NUMBER}", String.valueOf(i + 1))
									.replace("{ORE_NAME}", oreName.get(i)).replace("{MONEY_REWARDED}", String.valueOf(oreReward.get(i))));
						} else
							break;
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.noEnabledOres"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		}
		else if (args.length == 1) {
			if (sender.hasPermission("minemoney.getEnabledOres")) {
				List<String> oreName = plugin.config.getStringList("list.materials");
				List<Double> oreReward = plugin.config.getDoubleList("list.prices");
				if (oreName.size() > 0) {
					if (oreName.size() <= 10) {
						sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getBlocksListTitle").replace("{ACTUAL_PAGE}", "1")
								.replace("{MAX_PAGE}", "1"));
						for (int i = 0; i < oreName.size(); i++) {
							sender.sendMessage(plugin.lang.getString("information.getBlocksList").replace("{ORE_NUMBER}", String.valueOf(i + 1))
									.replace("{ORE_NAME}", oreName.get(i)).replace("{MONEY_REWARDED}", String.valueOf(oreReward.get(i))));
						}
					} else {
						int pageMax = (int)Math.ceil(((double)oreName.size()) /10);
						sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getBlocksListTitle").replace("{ACTUAL_PAGE}", args[0])
								.replace("{MAX_PAGE}", String.valueOf(pageMax)));
						
						for (int i = ((Integer.valueOf(args[0]) - 1) * 10); i < (Integer.valueOf(args[0]) * 10); i++) {
							if (i < oreName.size())
							sender.sendMessage(plugin.lang.getString("information.getBlocksList").replace("{ORE_NUMBER}", String.valueOf(i + 1))
									.replace("{ORE_NAME}", oreName.get(i)).replace("{MONEY_REWARDED}", String.valueOf(oreReward.get(i))));
							else
								break;
						}
						
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.noEnabledOres"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		} else 
			sender.sendMessage("§4" + plugin.lang.getString("error.badGetEnabledOresUtilisation"));
		
		return true;
	}

}
