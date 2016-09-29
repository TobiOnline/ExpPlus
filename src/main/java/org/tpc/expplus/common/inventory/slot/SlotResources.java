package org.tpc.expplus.common.inventory.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotResources extends Slot {
	
	private SlotAmount amountSlot;
	
	public SlotResources(IInventory inventory, int id, int x, int y) {
		super(inventory, id, x, y);
	}
	
	@Override
	public boolean isItemValid(ItemStack stack) {
		return true;
	}
	
	public void setAmountSlot(SlotAmount amountSlot) {
		this.amountSlot = amountSlot;
	}
	
	public int getAmount() {
		return getHasStack() ? getStack().stackSize : 0;
	}
	
	public void setAmount(int amount) {
		if(getHasStack()) {
			getStack().stackSize = amount;
			onSlotChanged();
		}
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player) {
		return true;
	}

	public void updateAmount() {
		if(getStack() == null) {
			amountSlot.putStack(null);
		} else {
			amountSlot.putStack(getStack().copy());
			amountSlot.onAmountChanged();
		}
	}

}
