package fr.uxfuncraft.minemoney.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import fr.uxfuncraft.minemoney.MineMoney;

public class MineMoneyCommand {
	
	MineMoney plugin;
	
	public MineMoneyCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("minemoney") 
				|| label.equalsIgnoreCase("mm") 
				|| label.equalsIgnoreCase("mmoney")) {
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("reload")) {
					if (sender.hasPermission("minemoney.reload")) {
						plugin.getServer().getPluginManager().disablePlugin(plugin);
						plugin.getServer().getPluginManager().enablePlugin(plugin);
						sender.sendMessage("§2Configuration reloaded !");
					} else {
						sender.sendMessage("§4You must have \"minemoney.reload\" permission to use this command.");
					}
				}
			}
			return true;
		}
		return false;
	}
}
