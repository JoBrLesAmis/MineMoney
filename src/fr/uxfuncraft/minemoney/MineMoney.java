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
import fr.uxfuncraft.minemoney.events.BlockPlacingListener;

public class MineMoney extends JavaPlugin {
	
	public Logger log;
	public Economy econ;
	public Commands cmd;
	public BlockBreakingListener bbl;
	public BlockPlacingListener bpl;
	public FileConfiguration config;
	public String[] blacklist;
	
	@Override
	public void onEnable() {
		log = Bukkit.getLogger();
		cmd = new Commands(this);
		bbl = new BlockBreakingListener(this);
		bpl = new BlockPlacingListener(this);
		
		getServer().getPluginManager().registerEvents(bbl, this);
		getServer().getPluginManager().registerEvents(bpl, this);
		
		if (!isEconomyEnabled()) {
			log.log(Level.SEVERE, "[MineMoney]Vault not found !");
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
			log.log(Level.SEVERE, "The config file cannot be created, disabling plugin...");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		try {
			updateConfig();
		} catch (IOException e) {
			e.printStackTrace();
			log.log(Level.SEVERE, "Cannot update the config file, disabling plugin...");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		int numberOfBlacklistedWorlds = 0;
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			if (config.contains("blacklist." + i)) {
				numberOfBlacklistedWorlds++;
			} else {
				break;
			}
		}
		
		blacklist = new String[numberOfBlacklistedWorlds];
		
		for (int i = 0; i < numberOfBlacklistedWorlds; i++) {
			blacklist[i] = config.getString("blacklist." + (i + 1));
		}
		
		for (int i = 0; i < numberOfBlacklistedWorlds; i++) {
			log.log(Level.INFO, i + ": " + blacklist[i]);
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
			
			config.set("glowstone.enabled", true);
			config.set("glowstone.moneygiven", 3.0D);
			
			config.set("mobspawner.enabled", true);
			config.set("mobspawner.moneygiven", 40.0D);
			
			config.set("stonebricks.enabled", true);
			config.set("stonebricks.moneygiven", 5.0D);
			
			config.set("netherbricks.enabled", true);
			config.set("netherbricks.moneygiven", 5.0D);
			
			config.set("netherrack.enabled", true);
			config.set("netherrack.moneygiven", 0.001D);
			
			config.set("enderstone.enabled", true);
			config.set("enderstone.moneygiven", 0.5D);
			
			config.set("#Worlds", "You can blacklist as many worlds as you want. DO NOT REMOVE THIS LINE !");
			config.set("blacklist.1", "world");
			config.set("blacklist.2", "world_nether");
			config.set("blacklist.3", "world_the_end");
			
			config.save(configFile);
		} else {
			this.config = YamlConfiguration.loadConfiguration(configFile);
		}
	}
	
	public void updateConfig() throws IOException {
		File configFile = new File("plugins/minemoney/config.yml");
		if (!configFile.exists()) {
			log.log(Level.SEVERE, "[MineMoney]The config file doesn't exist. Disable plugin...");
			getServer().getPluginManager().disablePlugin(this);
		} else {
			if (!config.contains("glowstone.enabled")) {
				config.set("glowstone.enabled", true);
				config.set("glowstone.moneygiven", 3.0D);
				
				config.set("mobspawner.enabled", true);
				config.set("mobspawner.moneygiven", 40.0D);
				
				config.set("stonebricks.enabled", true);
				config.set("stonebricks.moneygiven", 5.0D);
				
				config.set("netherbricks.enabled", true);
				config.set("netherbricks.moneygiven", 5.0D);
				
				config.set("netherrack.enabled", true);
				config.set("netherrack.moneygiven", 0.001D);
				
				config.set("enderstone.enabled", true);
				config.set("enderstone.moneygiven", 0.5D);
			}
			
			if (!config.contains("#Worlds")) {
				config.set("#Worlds", "You can blacklist as many worlds as you want");
				config.set("blacklist.1", "world");
				config.set("blacklist.2", "world_nether");
				config.set("blacklist.3", "world_the_end");
			}
			
			config.save(configFile);
		}
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		return cmd.onCommand(sender, command, label, args);
	}
}
