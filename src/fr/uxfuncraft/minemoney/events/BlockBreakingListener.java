package fr.uxfuncraft.minemoney.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.uxfuncraft.minemoney.MineMoney;

public class BlockBreakingListener implements Listener {
	
	public MineMoney plugin;
	
	public BlockBreakingListener(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void mineCoal(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.COAL_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("coal.enabled")
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("coal.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineIron(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.IRON_ORE
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("iron.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("iron.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineGold(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.GOLD_ORE
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("gold.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("gold.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineLapis(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.LAPIS_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("lapis.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("lapis.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineRedstone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.GLOWING_REDSTONE_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("redstone.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("redstone.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineQuartz(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.QUARTZ_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("quartz.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("quartz.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineDiamond(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.DIAMOND_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("diamond.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("diamond.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineEmerald(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.EMERALD_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("emerald.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("emerald.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineStone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.STONE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("stone.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("stone.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineObsidian(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.OBSIDIAN 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("obsidian.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("obsidian.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineGlowstone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.GLOWSTONE 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("glowstone.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("glowstone.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineMobSpawner(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.MOB_SPAWNER
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("mobspawner.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("mobspawner.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineStoneBricks(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.SMOOTH_BRICK
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("stonebricks.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("stonebricks.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineNetherBricks(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.NETHER_BRICK
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("netherbricks.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("netherbricks.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineNetherrack(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.NETHERRACK 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("netherrack.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("netherrack.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineEnderStone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.ENDER_STONE
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("enderstone.enabled") 
				&& !isInBlacklistedWorld(event)) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("enderstone.moneygiven"));
		}
	}
	
	private boolean isInBlacklistedWorld(BlockBreakEvent event) {
		boolean isInBlacklistedWorld = false;
		
		for (int i = 0; i < plugin.blacklist.length; i++) {
			if (event.getPlayer().getWorld().getName().equalsIgnoreCase(plugin.blacklist[i])) {
				isInBlacklistedWorld = true;
				i = plugin.blacklist.length;
			}
		}
		
		return isInBlacklistedWorld;
	}
	
}
