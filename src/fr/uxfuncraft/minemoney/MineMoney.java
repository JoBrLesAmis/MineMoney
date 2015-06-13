package fr.uxfuncraft.minemoney;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import fr.uxfuncraft.minemoney.commands.AddWorldBlacklistCommand;
import fr.uxfuncraft.minemoney.commands.DisableOreCommand;
import fr.uxfuncraft.minemoney.commands.EnableOreCommand;
import fr.uxfuncraft.minemoney.commands.GetBlacklistedWorldsCommand;
import fr.uxfuncraft.minemoney.commands.GetEnabledOresCommand;
import fr.uxfuncraft.minemoney.commands.GetMoneyGivenCommand;
import fr.uxfuncraft.minemoney.commands.MineMoneyCommand;
import fr.uxfuncraft.minemoney.commands.ModifyMoneyGivenCommand;
import fr.uxfuncraft.minemoney.commands.RemoveWorldBlacklistCommand;
import fr.uxfuncraft.minemoney.events.BlockListener;

public class MineMoney extends JavaPlugin {
	
	public static final Logger LOGGER = Logger.getLogger("MineMoney");
	public Economy econ;
	protected BlockListener bl;
	protected File configFile;
	public FileConfiguration config;
	protected File langFile;
	public FileConfiguration lang;
	public List<World> blacklist;
	public List<Material> blocks;
	public List<Double> prices;
	
	@Override
	public void onEnable() {
		LOGGER.setParent(Logger.getGlobal());
		
		// Enabling economy
		if (!isEconomyEnabled()) {
			LOGGER.severe("[MineMoney]Vault or economy plugin not found !");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		// Creating config
		File configFolder = new File("plugins/minemoney");
		if (!configFolder.exists()) {
			configFolder.mkdir();
		}
		
		try {
			createConfig();
			updateConfig();
			createLang();
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.severe("[MineMoney]Problem with the config file, disabling plugin...");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		// Loading lists
		
		if (blocks == null) {
			blocks = new LinkedList<Material>();
			List<String> blockName = config.getStringList("list.materials");
			if (blockName.size() > 0) {
				for (int i = 0; i < blockName.size(); i++) {
					blocks.add(Material.getMaterial(blockName.get(i)));
				}
			}
		}
		
		if (prices == null) {
			prices = new LinkedList<Double>();
			if (config.getDoubleList("list.prices").size() > 0) 
				prices = config.getDoubleList("list.prices");
		}
		
		if (blacklist == null) {
			blacklist = new LinkedList<World>();
			List<String> worldName = config.getStringList("list.blacklist");
			if (worldName.size() > 0) {
				for (int i = 0; i < worldName.size(); i++) {
					blacklist.add(getServer().getWorld(worldName.get(i)));
				}
			}
		}
		
		// Enabling commands
		MineMoneyCommand mmc = new MineMoneyCommand(this);
		getCommand("minemoney").setExecutor(mmc);
		
		EnableOreCommand eoc = new EnableOreCommand(this);
		getCommand("enableore").setExecutor(eoc);
		
		DisableOreCommand doc = new DisableOreCommand(this);
		getCommand("disableore").setExecutor(doc);
		
		ModifyMoneyGivenCommand mmgc = new ModifyMoneyGivenCommand(this);
		getCommand("modifymoneygiven").setExecutor(mmgc);
		
		GetMoneyGivenCommand gmgc = new GetMoneyGivenCommand(this);
		getCommand("getmoneygiven").setExecutor(gmgc);
		
		AddWorldBlacklistCommand awbc = new AddWorldBlacklistCommand(this);
		getCommand("addworldblacklist").setExecutor(awbc);
		
		RemoveWorldBlacklistCommand rwbc = new RemoveWorldBlacklistCommand(this);
		getCommand("removeworldblacklist").setExecutor(rwbc);
		
		GetBlacklistedWorldsCommand gbwc = new GetBlacklistedWorldsCommand(this);
		getCommand("getblacklistedworlds").setExecutor(gbwc);
		
		GetEnabledOresCommand geoc = new GetEnabledOresCommand(this);
		getCommand("getenabledores").setExecutor(geoc);
		
		// Registering Events
		bl = new BlockListener(this);
		getServer().getPluginManager().registerEvents(bl, this);
		
		// Check if config is good
		if (blocks != null && prices != null) {
			if (blocks.size() != prices.size()) {
				LOGGER.severe("[MineMoney]Configuration contains errors. Disabling plugin...");
				getServer().getPluginManager().disablePlugin(this);
			}
		} else {
			if (blocks != null || prices != null) {
				LOGGER.severe("[MineMoney] Configuration contains errors. Disabling plugin...");
				getServer().getPluginManager().disablePlugin(this);
			}
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
		configFile = new File("plugins/minemoney/config.yml");
		if (!configFile.exists()) {
			configFile.createNewFile();
			config = YamlConfiguration.loadConfiguration(configFile);
			config.set("version", this.getDescription().getVersion());
			config.set("lang", "en_UK");
			config.set("list.materials", new LinkedList<String>());
			config.set("list.prices", new LinkedList<String>());
			config.set("list.blacklist", new LinkedList<String>());
			config.save(configFile);
		} else
			config = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public void updateConfig() throws IOException {
		if (!config.contains("version")) {
			configFile.delete();
			createConfig();
		}
	}

	public void createLang() throws IOException {
		langFile = new File("plugins/minemoney/" + config.getString("lang") + ".yml");
		if (!langFile.exists()) {
			langFile.createNewFile();
			lang = YamlConfiguration.loadConfiguration(langFile);
			lang.set("error.badDisableUtilisation", "Bad use ! Type /disableore <ore name>.");
			lang.set("error.badEnableUtilisation", "Bad use ! Type /enableore <ore name> <moneyRewarded>.");
			lang.set("error.badModifyMoneyUtilisation", "Bad use ! Type /modifymoneygiven <ore> <new money reward>.");
			lang.set("error.badGetMoneyUtilisation", "Bad use ! Type /getmoneygiven <ore>.");
			lang.set("error.badAddWorldBlacklistUtilisation", "Bad use ! Type /addworldblacklist <world>.");
			lang.set("error.badRemoveWorldBlacklistUtilisation", "Bad use ! Type /removeworldblacklist <world>.");
			lang.set("error.badGetBlacklistedWorldsUtilisation", "Bad use ! Type /getblacklistedworlds <page>.");
			lang.set("error.badGetEnabledOresUtilisation", "Bad use ! Type /getenabledores <page>");
			lang.set("error.oreNotEnabled", "The ore you specified isn't enabled !");
			lang.set("error.worldNotInBlacklist", "The world you specified isn't in the blacklist !");
			lang.set("error.oreAlreadyEnabled", "The ore you specified is already enabled !");
			lang.set("error.worldAlreadyBlacklisted", "The world you specified is already blacklisted !");
			lang.set("error.oreDoesntExist", "The ore you specified doesn't exist !");
			lang.set("error.worldDoesntExist", "The world you specified doesn't exist !");
			lang.set("error.whileDisable", "An error occurred while trying to disable ore {ORE_NAME} !");
			lang.set("error.whileRemovingWorldBlacklist", "An error occurred while trying to remove world {WORLD_NAME} from the blacklist !");
			lang.set("error.whileEnable", "An error occurred while trying to enable ore {ORE_NAME} !");
			lang.set("error.whileAddingWorldBlacklist", "An error occurred while trying to add world {WORLD_NAME} in the blacklist !");
			lang.set("error.whileModifyMoney", "An error occurred while trying to modify the money given by the ore {ORE_NAME} !");
			lang.set("error.noPermission", "You don't have permission to do this !");
			lang.set("error.noBlacklistedWorlds", "You don't have blacklisted world !");
			lang.set("error.noEnabledOres", "You don't have enabled ore !");
			lang.set("error.glowingRedstone", "GLOWING_REDSTONE_ORE already enabled ! Please remove it from the config before !");
			lang.set("error.redstoneOre", "REDSTONE_ORE already enabled ! Please remove it from the config before !");
			lang.set("success.reload", "Configuration reloaded !");
			lang.set("success.disable", "Successfully disabled ore {ORE_NAME} !");
			lang.set("success.removeWorld", "Successfully removed world {WORLD_NAME} from the blacklist !");
			lang.set("success.enable", "Successfully enabled ore {ORE_NAME} !");
			lang.set("success.addWorld", "Successfully added world {WORLD_NAME} to the blacklist !");
			lang.set("success.modifyMoney", "Successfully modified money given by ore {ORE_NAME} !");
			lang.set("information.getMoneyGiven", "The money given by {ORE_NAME} is ${MONEY_GIVEN}.");
			lang.set("information.getWorldBlacklistedTitle", "Blacklisted worlds : (page {ACTUAL_PAGE}/{MAX_PAGE})");
			lang.set("information.getWorldBlacklistedList", "{WORLD_NUMBER}. {WORLD_NAME}");
			lang.set("information.getBlocksListTitle", "Enabled ores : (page {ACTUAL_PAGE}/{MAX_PAGE})");
			lang.set("information.getBlocksList", "{ORE_NUMBER}. {ORE_NAME} -- {MONEY_REWARDED}");
			lang.save(langFile);
		} else
			lang = YamlConfiguration.loadConfiguration(langFile);
	}

	public File getConfigFile() {
		return this.configFile;
	}
}
