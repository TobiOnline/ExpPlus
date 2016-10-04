package org.tpc.expplus.common.tile;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.Properties;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;

public class TileExpGenerator extends TileExpPlus {

	public enum State {
		Active, Inactive, Activating, Deactivating
	}
	
	private static final int INVENTORY_SIZE = 5;
	
	// Volatile data for animation
	public double time;
	public float animation;
	public float rotation;
	
	private State state;
	
	private long energy;
	private int experience;

	public TileExpGenerator() {
		super(new InventoryBasic(Properties.CONTAINER_EXPGENERATOR, false, INVENTORY_SIZE));
		
		experience = 0;
		state = State.Inactive;
		
		rotation = (float) (ExpPlus.RANDOM.nextFloat() * 360.0F);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);

		tag.setInteger("Experience", experience);
		tag.setByte("State", (byte) state.ordinal());
		tag.setLong("Energy", energy);
		
		return tag;
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		experience = tag.getInteger("Experience");
		state = State.values()[tag.getByte("State")];
		energy = tag.getLong("Energy");
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int ownerExperience) {
		this.experience = ownerExperience;
	}
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
}
