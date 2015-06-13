package fr.uxfuncraft.minemoney.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.uxfuncraft.minemoney.MineMoney;

public class ModifyMoneyGivenCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public ModifyMoneyGivenCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2) {
			if (sender.hasPermission("minemoney.modifymoneygiven")) {
				args[0] = args[0].toUpperCase();
				Material block = Material.getMaterial(args[0]);
				if ((plugin.blocks != null) && plugin.blocks.contains(block)) {
					int index = plugin.blocks.indexOf(block);
					List<Double> prices = plugin.config.getDoubleList("list.prices");
					
					prices.set(index, Double.valueOf(args[1]));
					plugin.prices.set(index, Double.valueOf(args[1]));
					
					plugin.config.set("list.prices", prices);
					try {
						plugin.config.save(plugin.getConfigFile());
						sender.sendMessage("§2" + plugin.lang.getString("success.modifyMoney").replace("{ORE_NAME}", block.name()));
					} catch (IOException e) {
						sender.sendMessage("§4" + plugin.lang.getString("error.whileModifyMoney").replace("{ORE_NAME}", block.name()));
						e.printStackTrace();
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.oreNotEnabled"));
			} else {
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
			}
		} else
			sender.sendMessage("§4" + plugin.lang.getString("error.badModifyMoneyUtilisation"));
		
		return true;
	}

}
