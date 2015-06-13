package fr.uxfuncraft.minemoney.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.Command;

import fr.uxfuncraft.minemoney.MineMoney;

public class GetMoneyGivenCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public GetMoneyGivenCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			if (sender.hasPermission("minemoney.getmoneygiven")) {
				args[0] = args[0].toUpperCase();
				Material block = Material.getMaterial(args[0]);
				if ((plugin.blocks != null) && plugin.blocks.contains(block)) {
					int index = plugin.blocks.indexOf(block);
					List<Double> prices = plugin.config.getDoubleList("list.prices");
					
					sender.sendMessage("[§bMineMoney§r]" + plugin.lang.getString("information.getMoneyGiven").replace("{ORE_NAME}", block.name())
							.replace("{MONEY_GIVEN}", String.valueOf(prices.get(index))));
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.oreNotEnabled"));
			} else {
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
			}
		} else
			sender.sendMessage("§4" + plugin.lang.getString("error.badGetMoneyUtilisation"));
		
		return true;
	}

}
