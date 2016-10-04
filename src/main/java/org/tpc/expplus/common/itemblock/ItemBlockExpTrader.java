package org.tpc.expplus.common.itemblock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemBlockExpTrader extends ItemBlock {

	public ItemBlockExpTrader(Block block) {
		super(block);

		setCreativeTab(CreativeTabs.REDSTONE);
		setRegistryName(block.getRegistryName());
		setUnlocalizedName(block.getUnlocalizedName());
	}
}