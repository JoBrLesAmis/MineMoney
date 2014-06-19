package fr.uxfuncraft.minemoney;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.uxfuncraft.minemoney.commands.Commands;
import fr.uxfuncraft.minemoney.events.BlockBreakingListener;

public class MineMoney extends JavaPlugin {
	
	public Logger log;
	public Economy econ;
	public Commands cmd;
	public BlockBreakingListener bbl;
	public FileConfiguration config;
	
	@Override
	public void onEnable() {
		log = Bukkit.getLogger();
		cmd = new Commands(this);
		bbl = new BlockBreakingListener(this);
		
		getServer().getPluginManager().registerEvents(bbl, this);
		
		if (!isEconomyEnabled()) {
			log.log(Level.SEVERE, "[§bMine§3Money§r]Vault not found !");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		File configFolder = new File("plugins/minemoney");
		if (!configFolder.exists()) {
			configFolder.mkdir();
		}
		
		try {
			createConfig();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEconomyEnabled() {
		if (getServer().getPluginManager().getPlugin("Vault") == null) {
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) {
			return false;
		}
		
		econ = rsp.getProvider();
		return econ != null;
	}
	
	public void createConfig() throws IOException {
		File configFile = new File("plugins/minemoney/config.yml");
		if (!configFile.exists()) {
			configFile.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			config.set("Help", "You can choose if you want enable an ore or not. By default, all ores are enable.\nYou can also choose how many money will give while the ore is breaking.");

			config.set("coal.enabled", true);
			config.set("coal.moneygiven", 1.0D);

			config.set("iron.enabled", true);
			config.set("iron.moneygiven", 3.0D);

			config.set("gold.enabled", true);
			config.set("gold.moneygiven", 10.0D);

			config.set("lapis.enabled", true);
			config.set("lapis.moneygiven", 7.0D);

			config.set("redstone.enabled", true);
			config.set("redstone.moneygiven", 5.0D);

			config.set("quartz.enabled", true);
			config.set("quartz.moneygiven", 1.0D);

			config.set("diamond.enabled", true);
			config.set("diamond.moneygiven", 15.0D);
			
			config.set("emerald.enabled", true);
			config.set("emerald.moneygiven", 20.0D);
			
			config.set("stone.enabled", true);
			config.set("stone.moneygiven", 0.001D);
			
			config.set("obsidian.enabled", true);
			config.set("obsidian.moneygiven", 25.0D);
			
			config.save(configFile);
		} else {
			this.config = YamlConfiguration.loadConfiguration(configFile);
		}
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return cmd.onCommand(sender, command, label, args);
	}
}
