package org.tpc.expplus.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAmount extends Slot {
	
	public int amount = 1;

	public SlotAmount(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer entityPlayer) {
		return false;
	}

	public void onAmountChanged() {
		if(getStack() != null) {
			getStack().stackSize = amount;
			onSlotChanged();
		} else {
			putStack(null);
		}
	}
	
}
