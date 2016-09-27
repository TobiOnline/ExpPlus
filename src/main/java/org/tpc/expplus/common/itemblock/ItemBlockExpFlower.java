package org.tpc.expplus.common.itemblock;

import org.tpc.expplus.common.block.BlockExpFlower;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;

public class ItemBlockExpFlower extends ItemBlock {

	public ItemBlockExpFlower(BlockExpFlower block) {
		super(block);

		setCreativeTab(CreativeTabs.DECORATIONS);
		setRegistryName(block.getRegistryName());
		setUnlocalizedName(block.getUnlocalizedName());
	}
}