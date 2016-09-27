package org.tpc.expplus.common.tile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import static org.tpc.expplus.ExpPlusMod.DEBUG;

import java.util.UUID;

public class TileExpPlus extends TileEntity implements IInventory {

	protected ItemStack[]	inventory;
	protected UUID			owner;
	protected int			experience;
	protected Container		container;

	public TileExpPlus(int inventorySize) {
		inventory = new ItemStack[inventorySize];
		owner = null;
	}

	public float getDistanceToEntity(Entity entity) {
		return MathHelper.sqrt_float((float) pos.distanceSqToCenter(entity.posX, entity.posY, entity.posZ));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		
		String ownerString = tag.getString("Owner");
		if (!ownerString.isEmpty())
			owner = UUID.fromString(ownerString);
		
		experience = tag.getInteger("Experience");

		if (tag.hasKey("Inventory")) {
			inventory = new ItemStack[inventory.length];
			
			NBTTagList tagList = tag.getTagList("Inventory", 10);
			for (int i = 0; i < tagList.tagCount(); i++) {
				NBTTagCompound slot = (NBTTagCompound) tagList.get(i);
				
				byte slotNumber = slot.getByte("Slot");
				if ((slotNumber >= 0) && (slotNumber < inventory.length)) {
					inventory[slotNumber] = ItemStack.loadItemStackFromNBT(slot);
				}
			}
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setString("Owner", (owner == null) ? "" : owner.toString());
		tag.setInteger("Experience", experience);

		if (inventory != null) {
			NBTTagList itemList = new NBTTagList();
			for (int i = 0; i < inventory.length; i++) {
				ItemStack stack = inventory[i];
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

	@Override
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amount) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amount);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inventory[slot] = stack;
		if ((stack != null) && (stack.stackSize > getInventoryStackLimit())) {
			stack.stackSize = getInventoryStackLimit();
		}
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return (worldObj.getTileEntity(pos) == this) && (getDistanceToEntity(player) < 64.0f);
	}

	public void setOwner(UUID owner) {
		this.owner = owner;
	}

	public UUID getOwner() {
		return owner;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, int neighbourID) { }

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public void onAction(String action, String data, EntityPlayer player) {
		DEBUG(getClass().getSimpleName() + ".onAction(" + action + ", " + data + ", " + player.getName() + ")");
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemstack) {
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openInventory(EntityPlayer player) { }

	@Override
	public void closeInventory(EntityPlayer player) { }

	@Override
	public int getField(int id) {
		return Item.getIdFromItem(inventory[id].getItem());
	}

	@Override
	public void setField(int id, int value) {
		inventory[id] = new ItemStack(Item.getItemById(value));
	}

	@Override
	public int getFieldCount() {
		return inventory.length;
	}

	@Override
	public void clear() { }

}
