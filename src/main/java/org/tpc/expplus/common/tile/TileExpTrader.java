package org.tpc.expplus.common.tile;

import java.util.UUID;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;

public class TileExpTrader extends TileExpPlus {

	private static final int INVENTORY_SIZE = 5;

	// Volatile data for animation
	public float animation;
	public float rotation;

	private Object useLock;
	private UUID inUseBy;

	// Direction
	// To left: false
	// To right: true
	private boolean direction;
	
	private int ownerExperience;
	private int tradeExperience;

	public TileExpTrader() {
		super(new InventoryBasic(Properties.CONTAINER_EXPTRADER, false, INVENTORY_SIZE));
		
		useLock = new Object();
		
		rotation = (float) (ExpPlus.RANDOM.nextFloat() * 360.0F);
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
