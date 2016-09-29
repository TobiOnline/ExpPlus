package org.tpc.expplus.common.itemblock;

import org.tpc.expplus.common.block.BlockExpTrader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemBlockExpTrader extends ItemBlock {

	public ItemBlockExpTrader(BlockExpTrader block) {
		super(block);

		setCreativeTab(CreativeTabs.REDSTONE);
		setRegistryName(block.getRegistryName());
		setUnlocalizedName(block.getUnlocalizedName());
	}
}