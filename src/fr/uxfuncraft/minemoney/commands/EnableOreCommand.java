package fr.uxfuncraft.minemoney.commands;

import java.io.IOException;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import fr.uxfuncraft.minemoney.MineMoney;

public class EnableOreCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public EnableOreCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 2) {
			if (sender.hasPermission("minemoney.enableore")) {
				args[0] = args[0].toUpperCase();
				Material block = Material.getMaterial(args[0]);
				if (block != null) {
					if ((plugin.blocks != null) && !plugin.blocks.contains(block)) {
						if (block == Material.REDSTONE_ORE) {
							if (!plugin.blocks.contains(Material.GLOWING_REDSTONE_ORE)) {
								List<String> blockName = plugin.config.getStringList("list.materials");
								List<Double> prices = plugin.config.getDoubleList("list.prices");
								
								plugin.blocks.add(block);
								plugin.blocks.add(Material.GLOWING_REDSTONE_ORE);
								plugin.prices.add(Double.valueOf(args[1]));
								plugin.prices.add(Double.valueOf(args[1]));
								blockName.add(args[0]);
								blockName.add("GLOWING_REDSTONE_ORE");
								prices.add(Double.valueOf(args[1]));
								prices.add(Double.valueOf(args[1]));
								
								plugin.config.set("list.materials", blockName);
								plugin.config.set("list.prices", prices);
								try {
									plugin.config.save(plugin.getConfigFile());
									sender.sendMessage("§2" + plugin.lang.getString("success.enable").replace("{ORE_NAME}", block.name()));
								} catch (IOException e) {
									sender.sendMessage("§4" + plugin.lang.getString("error.whileEnable").replace("{ORE_NAME}", block.name()));
									e.printStackTrace();
								}
							} else
								sender.sendMessage("§4" + plugin.lang.getString("error.glowingRedstone"));
						} else if (block == Material.GLOWING_REDSTONE_ORE) {
							if (!plugin.blocks.contains(Material.REDSTONE_ORE)) {
								List<String> blockName = plugin.config.getStringList("list.materials");
								List<Double> prices = plugin.config.getDoubleList("list.prices");
								
								plugin.blocks.add(block);
								plugin.blocks.add(Material.REDSTONE_ORE);
								plugin.prices.add(Double.valueOf(args[1]));
								plugin.prices.add(Double.valueOf(args[1]));
								blockName.add(args[0]);
								blockName.add("REDSTONE_ORE");
								prices.add(Double.valueOf(args[1]));
								prices.add(Double.valueOf(args[1]));
								
								plugin.config.set("list.materials", blockName);
								plugin.config.set("list.prices", prices);
								try {
									plugin.config.save(plugin.getConfigFile());
									sender.sendMessage("§2" + plugin.lang.getString("success.enable").replace("{ORE_NAME}", block.name()));
								} catch (IOException e) {
									sender.sendMessage("§4" + plugin.lang.getString("error.whileEnable").replace("{ORE_NAME}", block.name()));
									e.printStackTrace();
								}
							} else 
								sender.sendMessage("§4" + plugin.lang.getString("error.redstoneOre"));
						} else {
							List<String> blockName = plugin.config.getStringList("list.materials");
							List<Double> prices = plugin.config.getDoubleList("list.prices");
							
							plugin.blocks.add(block);
							plugin.prices.add(Double.valueOf(args[1]));
							blockName.add(args[0]);
							prices.add(Double.valueOf(args[1]));
							
							plugin.config.set("list.materials", blockName);
							plugin.config.set("list.prices", prices);
							try {
								plugin.config.save(plugin.getConfigFile());
								sender.sendMessage("§2" + plugin.lang.getString("success.enable").replace("{ORE_NAME}", block.name()));
							} catch (IOException e) {
								sender.sendMessage("§4" + plugin.lang.getString("error.whileEnable").replace("{ORE_NAME}", block.name()));
								e.printStackTrace();
							}
						}
					} else
						sender.sendMessage("§4" + plugin.lang.getString("error.oreAlreadyEnabled"));
				} else
					sender.sendMessage("§4" + plugin.lang.getString("error.oreDoesntExist"));
			} else
				sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
		} else
			sender.sendMessage("§4" + plugin.lang.getString("error.badEnableUtilisation"));
		return true;
	}
}
