package org.tpc.expplus.common.itemblock;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemBlockExpFlower extends ItemBlock {

	public ItemBlockExpFlower(Block block) {
		super(block);

		setCreativeTab(CreativeTabs.DECORATIONS);
		setRegistryName(block.getRegistryName());
		setUnlocalizedName(block.getUnlocalizedName());
	}
}