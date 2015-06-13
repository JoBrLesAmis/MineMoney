package fr.uxfuncraft.minemoney.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import fr.uxfuncraft.minemoney.MineMoney;

public class DisableOreCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public DisableOreCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 1) {
			if (sender.hasPermission("minemoney.disableore")) {
				args[0] = args[0].toUpperCase();
				Material block = Material.getMaterial(args[0]);
				if ((plugin.blocks != null) && plugin.blocks.contains(block)) {
					if (block == Material.REDSTONE_ORE && plugin.blocks.contains(Material.GLOWING_REDSTONE_ORE)) {
						int index = plugin.blocks.indexOf(block);
							
						List<String> blockName = plugin.config.getStringList("list.materials");
						List<Double> prices = plugin.config.getDoubleList("list.prices");
						
						plugin.blocks.remove(index);
						plugin.prices.remove(index);
						blockName.remove(index);
						prices.remove(index);

						int index2 = plugin.blocks.indexOf(Material.GLOWING_REDSTONE_ORE);
						plugin.blocks.remove(index2);
						plugin.prices.remove(index2);
						blockName.remove(index2);
						prices.remove(index2);
						
						plugin.config.set("list.materials", blockName);
						plugin.config.set("list.prices", prices);
						try {
							plugin.config.save(plugin.getConfigFile());
							sender.sendMessage("§2" + plugin.lang.getString("success.disable").replace("{ORE_NAME}", block.name()));
						} catch (IOException e) {
							sender.sendMessage("§4" + plugin.lang.getString("error.whileDisable").replace("{ORE_NAME}", block.name()));
							e.printStackTrace();
						}
					} else if (block == Material.GLOWING_REDSTONE_ORE && plugin.blocks.contains(Material.REDSTONE_ORE)) {
						int index = plugin.blocks.indexOf(block);
						
						List<String> blockName = plugin.config.getStringList("list.materials");
						List<Double> prices = plugin.config.getDoubleList("list.prices");
						
						plugin.blocks.remove(index);
						plugin.prices.remove(index);
						blockName.remove(index);
						prices.remove(index);
						
						int index2 = plugin.blocks.indexOf(Material.REDSTONE_ORE);
						plugin.blocks.remove(index2);
						plugin.prices.remove(index2);
						blockName.remove(index2);
						prices.remove(index2);
						
						plugin.config.set("list.materials", blockName);
						plugin.config.set("list.prices", prices);
						try {
							plugin.config.save(plugin.getConfigFile());
							sender.sendMessage("§2" + plugin.lang.getString("success.disable").replace("{ORE_NAME}", block.name()));
						} catch (IOException e) {
							sender.sendMessage("§4" + plugin.lang.getString("error.whileDisable").replace("{ORE_NAME}", block.name()));
							e.printStackTrace();
						}
					} else {
						int index = plugin.blocks.indexOf(block);
						
						List<String> blockName = plugin.config.getStringList("list.materials");
						List<Double> prices = plugin.config.getDoubleList("list.prices");
						
						plugin.blocks.remove(index);
						plugin.prices.remove(index);
						blockName.remove(index);
						prices.remove(index);
						
						plugin.config.set("list.materials", blockName);
						plugin.config.set("list.prices", prices);
						try {
							plugin.config.save(plugin.getConfigFile());
							sender.sendMessage("§2" + plugin.lang.getString("success.disable").replace("{ORE_NAME}", block.name()));
						} catch (IOException e) {
							sender.sendMessage("§4" + plugin.lang.getString("error.whileDisable").replace("{ORE_NAME}", block.name()));
							e.printStackTrace();
						}
					}
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.oreNotEnabled"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		} else
			sender.sendMessage("§4" + plugin.lang.getString("error.badDisableUtilisation"));
		
		return true;
	}
}
