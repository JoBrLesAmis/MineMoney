package fr.uxfuncraft.minemoney.events;

import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.uxfuncraft.minemoney.MineMoney;

public class BlockListener implements Listener {
	
	protected MineMoney plugin;
	
	public BlockListener(MineMoney plugin) {
		this.plugin = plugin;
	}
	
	// Earn money when block is broken
	@EventHandler
	public void mineBlock(BlockBreakEvent e) {
		if (!isMaterialsEmpty()) {
			Block block = e.getBlock();
			
			if (plugin.blocks.contains(block.getType()) && !isInBlacklistedWorld(e.getPlayer()) && e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				double reward = plugin.prices.get(plugin.blocks.indexOf(block.getType()));
				plugin.econ.depositPlayer((OfflinePlayer)e.getPlayer(), reward);
			}
		}
	}
	
	// Lose money when block is placed
	@EventHandler
	public void placeBlock(BlockPlaceEvent e) {
		if (!isMaterialsEmpty()) {
			Block block = e.getBlock();
			
			if (plugin.blocks.contains(block.getType()) && !isInBlacklistedWorld(e.getPlayer()) && e.getPlayer().getGameMode() == GameMode.SURVIVAL) {
				double reward = plugin.prices.get(plugin.blocks.indexOf(block.getType()));
				plugin.econ.withdrawPlayer((OfflinePlayer)e.getPlayer(), reward);
			}
		}
	}
	
	private boolean isInBlacklistedWorld(Player player) {
		if (plugin.blacklist.size() != 0) {
			if (plugin.blacklist.contains(player.getWorld()))
				return true;
		}
		
		return false;
	}
	
	protected boolean isMaterialsEmpty() {
		return plugin.blocks == null;
	}
	
}
