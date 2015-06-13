package fr.uxfuncraft.minemoney.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import fr.uxfuncraft.minemoney.MineMoney;

public class MineMoneyCommand implements CommandExecutor {
	
	MineMoney plugin;
	
	public MineMoneyCommand(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length >= 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (sender.hasPermission("minemoney.reload")) {
					plugin.getServer().getPluginManager().disablePlugin(plugin);
					plugin.getServer().getPluginManager().enablePlugin(plugin);
					sender.sendMessage("§2" + plugin.lang.getString("success.reload"));
				} else {
					sender.sendMessage("§4" + plugin.lang.getString("error.noPermission"));
				}
			}
		}
		return true;
	}
}
