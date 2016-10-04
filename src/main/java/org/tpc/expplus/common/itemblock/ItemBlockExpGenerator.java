package org.tpc.expplus.common.itemblock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemBlockExpGenerator extends ItemBlock {

	public ItemBlockExpGenerator(Block block) {
		super(block);

		setCreativeTab(CreativeTabs.REDSTONE);
		setRegistryName(block.getRegistryName());
		setUnlocalizedName(block.getUnlocalizedName());
	}
}