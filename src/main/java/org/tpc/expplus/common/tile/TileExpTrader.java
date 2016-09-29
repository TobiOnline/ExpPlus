package org.tpc.expplus.common.tile;

import java.util.UUID;

import org.tpc.expplus.ExpPlus;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;

public class TileExpTrader extends TileExpPlus {

	private static final int INVENTORY_SIZE = 5;
	
	public float animation;
	public float rotation;

	private Object useLock;
	private UUID inUseBy;

	// Direction
	// To left: false
	// To right: true
	public boolean direction;
	
	public int ownerExperience;
	public int tradeExperience;

	public TileExpTrader() {
		super(new InventoryBasic("container.exp_trader", false, INVENTORY_SIZE));
		useLock = new Object();
		
		direction = false;
		ownerExperience = 0;
		
		animation = 0.5F + ((float) ExpPlus.RANDOM.nextInt(2));
		rotation = (float) (ExpPlus.RANDOM.nextFloat() * (Math.PI * 2.0F));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setBoolean("Direction", direction);
		tag.setInteger("OwnerExperience", ownerExperience);
		
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		direction = tag.getBoolean("Direction");
		ownerExperience = tag.getInteger("OwnerExperience");
	}
	
	public boolean getDirection() {
		return direction;
	}

	public int getOwnerExperience() {
		return ownerExperience;
	}
	
	public void setOwnerExperience(int ownerExperience) {
		this.ownerExperience = ownerExperience;
	}
	
	public int getTradeExperience() {
		return tradeExperience;
	}
	
	public void setTradeExperience(int tradeExperience) {
		this.tradeExperience = tradeExperience;
	}
	
	public Object getLockObject() {
		return useLock;
	}

	public UUID getInUseBy() {
		return inUseBy;
	}
	
	public void setInUseBy(UUID inUseBy) {
		this.inUseBy = inUseBy;
	}
}
