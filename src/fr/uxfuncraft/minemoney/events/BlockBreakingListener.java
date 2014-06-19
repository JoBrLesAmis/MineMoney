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
				&& plugin.config.getBoolean("coal.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("coal.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineIron(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.IRON_ORE
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("iron.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("iron.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineGold(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.GOLD_ORE
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("gold.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("gold.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineLapis(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.LAPIS_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("lapis.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("lapis.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineRedstone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.REDSTONE_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("redstone.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("redstone.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineQuartz(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.QUARTZ_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("quartz.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("quartz.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineDiamond(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.DIAMOND_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("diamond.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("diamond.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineEmerald(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.EMERALD_ORE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("emerald.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("emerald.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineStone(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.STONE
				&& !event.getPlayer().getItemInHand().containsEnchantment(Enchantment.SILK_TOUCH) 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("stone.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("stone.moneygiven"));
		}
	}
	
	@EventHandler
	public void mineObsidian(BlockBreakEvent event) {
		Block item = event.getBlock();
		
		if (item.getType() == Material.OBSIDIAN 
				&& event.getPlayer().getGameMode() == GameMode.SURVIVAL
				&& plugin.config.getBoolean("obsidian.enabled") ) {
			plugin.econ.depositPlayer(event.getPlayer(), plugin.config.getDouble("obsidian.moneygiven"));
		}
	}
	
}
