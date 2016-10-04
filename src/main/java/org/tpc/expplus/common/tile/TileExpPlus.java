package org.tpc.expplus.common.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import java.util.UUID;

public abstract class TileExpPlus extends TileEntity {

	protected UUID owner;
	protected String ownerName;
	protected IInventory inventory;

	public TileExpPlus(IInventory inventory) {
		this.owner = null;
		this.ownerName = null;
		this.inventory = inventory;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		owner = (ownerName = tag.getString("Owner")).isEmpty() ? null : UUID.fromString(ownerName);
		if ((ownerName = tag.getString("OwnerName")).isEmpty()) {
			ownerName = "None";
		}

		if (tag.hasKey("Inventory")) {
			NBTTagList tagList = tag.getTagList("Inventory", 10);

			for (int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound slot = (NBTTagCompound) tagList.get(i);

				byte slotNumber = slot.getByte("Slot");
				if ((slotNumber >= 0) && (slotNumber < inventory.getFieldCount())) {
					inventory.setInventorySlotContents(slotNumber, ItemStack.loadItemStackFromNBT(slot));
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("Owner", (owner == null) ? "" : owner.toString());
		tag.setString("OwnerName", (ownerName == null) ? "" : ownerName);

		if (inventory != null) {
			NBTTagList itemList = new NBTTagList();

			for (int i = 0; i < inventory.getFieldCount(); i++) {
				ItemStack stack = inventory.getStackInSlot(i);
				if (stack != null) {
					NBTTagCompound slot = new NBTTagCompound();
					slot.setByte("Slot", (byte) i);
					stack.writeToNBT(slot);
					itemList.appendTag(slot);
				}
			}

			tag.setTag("Inventory", itemList);
		}

		return tag;
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public UUID getOwner() {
		return owner;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public boolean isOwner(EntityPlayer player) {
		return ((owner == null) || owner.equals(player.getUniqueID())); 
	}
	
	public IInventory getInventory() {
		return inventory;
	}
}
