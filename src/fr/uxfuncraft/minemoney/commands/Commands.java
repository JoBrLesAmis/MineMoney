package fr.uxfuncraft.minemoney.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;

import fr.uxfuncraft.minemoney.MineMoney;

public class Commands {
	
	public MineMoney plugin;
	
	public Commands(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	public MineMoneyCommand mmc;
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		mmc = new MineMoneyCommand(plugin);
		
		if (mmc.onCommand(sender, command, label, args)) {
			return true;
		} return true;
	}

}
