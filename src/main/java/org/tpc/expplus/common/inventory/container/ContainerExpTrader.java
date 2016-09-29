package org.tpc.expplus.common.inventory.container;
import org.tpc.expplus.common.tile.TileExpTrader;

import net.minecraft.entity.player.EntityPlayer;
//
//import org.tpc.expplus.common.inventory.slot.SlotAmount;
//import org.tpc.expplus.common.inventory.slot.SlotResources;
//import org.tpc.expplus.common.tile.TileExpTrader;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.MathHelper;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//
public class ContainerExpTrader extends Container {
//	
//	private final int AMOUNT_SHIFT_CLICK = 64;
//
	private TileExpTrader trader;
	private EntityPlayer player;
//	private SlotAmount slotAmount;
//	private SlotResources slotResources;
//
	public ContainerExpTrader(EntityPlayer player, TileExpTrader expTrader) {
		this.player = player;
		this.trader = expTrader;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return MathHelper.sqrt_double(player.getDistanceSq(trader.getPos())) < 64.0D; 
	}
	
	public EntityPlayer getPlayer() {
		return player;
	}
	
	public TileExpTrader getTrader() {
		return trader;
	}
}
//		expTrader.setContainer(this);
//
//		InventoryPlayer inventory = player.inventory;
//		for (int y = 0; y < 4; y++) {
//			for (int x = 0; x < 9; x++) {
//				addSlotToContainer(new Slot(inventory, x + (y * 9), 15 + (x * 18), 85 + (y * 18) + (y == 0 ? 76 : 0)));
//			}
//		}
//		
//		slotAmount = new SlotAmount(expTrader, 0, 119, 30);
//		slotResources = new SlotResources(expTrader, 1, 156, 30);
//		
////		slotAmount.setResourceSlot(slotResources);
////		slotResources.setAmountSlot(slotAmount);
//		
//		if(slotAmount.getHasStack()) {
//			slotAmount.amount = slotAmount.getStack().stackSize;
//			slotAmount.onAmountChanged();
//		}
//		
//		addSlotToContainer(slotAmount);
//		addSlotToContainer(slotResources);
//	}
//
//	@Override
//	public boolean canInteractWith(EntityPlayer player) {
//		return expTrader.isUseableByPlayer(player);
//	}
//
//	@Override
//	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
//		if(!player.getUniqueID().equals(expTrader.getOwner())) {
//			return null;
//		}
//		
//		System.out.println("Transfer stack in slot " + slot);
//		
//		ItemStack stack = null;
//		Slot slotObject = (Slot) inventorySlots.get(slot);
//
//		// null checks and checks if the item can be stacked (maxStackSize > 1)
//		if ((slotObject != null) && slotObject.getHasStack()) {
//			ItemStack stackInSlot = slotObject.getStack();
//			stack = stackInSlot.copy();
//			
//			if(slot < 36) {
//				// places it into the tileEntity is possible since its in the player inventory
//				ItemStack resourceStack = slotResources.getStack();
//				if(resourceStack == null) {
//					slotResources.putStack(stackInSlot.copy());
//					slotResources.updateAmount();
//					stackInSlot.stackSize = 0;
//				} else {
//					if(stackInSlot.isItemEqual(resourceStack)) {
//						if(resourceStack.stackSize == slotResources.getSlotStackLimit()) {
//							return null;
//						}
//						if(resourceStack.stackSize + stackInSlot.stackSize > slotResources.getSlotStackLimit()) {
//							stackInSlot.stackSize -= (slotResources.getSlotStackLimit() - resourceStack.stackSize);
//							resourceStack.stackSize = slotResources.getSlotStackLimit();
//							
//							slotResources.onSlotChanged();
//						} else {
//							resourceStack.stackSize += stackInSlot.stackSize;
//							stackInSlot.stackSize = 0;
//							
//							slotResources.onSlotChanged();
//						}
//					}
//				}
//			} else {
//				// places it into the player inventory since its in the tileEntity
////				addToInventory(player, stackInSlot, false);
//			}
//
//			if (stackInSlot.stackSize == 0) {
//				slotObject.putStack(null);
//			} else {
//				slotObject.onSlotChanged();
//			}
//
//			if (stackInSlot.stackSize == stack.stackSize) {
//				return null;
//			}
//			
//			slotObject.onPickupFromSlot(player, stackInSlot);
//		}
//		return stack;
//	}
//	
////	@Override
////	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
////		DEBUG("Slot clicked (slot: " + slotId + ", type: " + dragType + ", click: " + clickTypeIn + ", player " + player.getUniqueID() + ")");
////		
////		InventoryPlayer inventory = player.inventory;
////		switch(slotId) {
////			case 36:
////				if(!player.getUniqueID().equals(expTrader.getOwner())) {
////					trade();
//////					PacketDispatcher.sendPacketToServer(new PacketAction("Trade", expTrader).createPacket());
////					return null;
////				}
////				onAmountClick(inventory, dragType, clickTypeIn);
////				return null;
////			case 37:
////				if(!player.getUniqueID().equals(expTrader.getOwner())) {
////					return null;
////				}
////				if((dragType == 0 && clickTypeIn == 1) || (dragType == 0 && clickTypeIn == 6)) {
////					return transferStackInSlot(player, slotId);
////				}
////				if(inventory.getItemStack() == null) {
////					return pickItemStack(inventory, dragType, clickTypeIn);
////				}
////				return putItemStack(inventory, dragType, clickTypeIn);
////			default:
////				return super.slotClick(slotId, dragType, clickTypeIn, player);
////		}
////	}
//
////	private ItemStack putItemStack(InventoryPlayer inventory, int type, ClickType clickTypeIn) {
////		/*
////		 * REMEMBER: The player is holding a stack.
////		*/
////		
////		ItemStack slotStack = slotResources.getStack();
////		ItemStack inventoryStack = inventory.getItemStack();
////		
////		// Do nothing on dragging (maybe dragging air?)
////		if(clickTypeIn == ClickType.PICKUP) {
////			return null;
////		}
////		
////		switch(type) {
////			case 0:
////				
////				// Left click
////				
////				if(slotStack != null && slotStack.isItemEqual(inventoryStack)) {
////					
////					// Add as much as there is space
////					
////					if(slotStack.stackSize + inventoryStack.stackSize > slotResources.getSlotStackLimit()) {
////						inventoryStack.stackSize -= (slotResources.getSlotStackLimit() - slotStack.stackSize);
////						inventory.markDirty();
////						slotStack.stackSize = slotResources.getSlotStackLimit();
////						slotResources.onSlotChanged();
////						return inventoryStack;
////					} else {
////						inventory.setItemStack(null);
////						slotStack.stackSize += inventoryStack.stackSize;
////						slotResources.onSlotChanged();
////					}
////				} else if(slotStack != null) {
////					
////					// If the stack in the slot is smaller than the maximal stack size then switch the stacks
////					
////					if(slotStack.stackSize <= slotStack.getMaxStackSize()) {
////						inventory.setItemStack(slotStack);
////						inventory.markDirty();
////						slotResources.putStack(inventoryStack);
////						slotResources.updateAmount();
////						return slotStack;
////					}
////				} else {
////					
////					// Just put the stack in the slot
////					
////					slotResources.putStack(inventory.getItemStack());
////					slotResources.updateAmount();
////					inventory.setItemStack(null);
////					inventory.markDirty();
////				}
////				return null;
////			case 1:
////				
////				// Right click - Just put one more in the slot.
////				if(clickTypeIn == ClickType.QUICK_MOVE) {
////					return putItemStack(inventory, 0, 0);
////				}
////				
////				if(slotStack != null && slotStack.isItemEqual(inventoryStack)) {
////					
////					// Add one (if there is space)
////					
////					if(slotStack.stackSize < slotResources.getSlotStackLimit()) {
////						slotStack.stackSize++;
////						slotResources.onSlotChanged();
////						if((--inventoryStack.stackSize) <= 0) {
////							inventory.setItemStack(null);
////						}
////						inventory.onInventoryChanged();
////						return inventoryStack;
////					}
////				} else if(slotStack == null){
////					
////					// Set as one, since there is nothing in the slot.
////					
////					ItemStack stack = inventoryStack.copy();
////					stack.stackSize = 1;
////					slotResources.putStack(stack);
////					slotResources.updateAmount();
////					
////					if((--inventory.getItemStack().stackSize) <= 0) {
////						inventory.setItemStack(null);
////					}
////					inventory.onInventoryChanged();
////					return inventoryStack;
////				}
////				return null;
////			default:
////				return putItemStack(inventory, 0, 0);
////		}
////	}
////
////	private ItemStack pickItemStack(InventoryPlayer inventory, int type, ClickType clickTypeIn) {
////		/*
////		 * REMEMBER: The player is not dragging a stack.
////		*/
////		
////		ItemStack slotStack = slotResources.getStack();
////		ItemStack inventoryStack;
////		
////		// Do nothing on dragging
////		if(clickTypeIn == 5) {
////			return null;
////		}
////		
////		switch(type) {
////			case 0:
////				
////				// Left click
////				
////				if(slotStack != null) {
////					if(slotStack.stackSize > slotStack.getMaxStackSize()) {
////						
////						// Take just the maximal stacksize
////						
////						slotStack.stackSize -= slotStack.getMaxStackSize();
////						slotResources.onSlotChanged();
////						inventoryStack = slotStack.copy();
////						inventoryStack.stackSize = inventoryStack.getMaxStackSize();
////						inventory.setItemStack(inventoryStack);
////					} else {
////						
////						// Take the whole stack
////						
////						slotResources.putStack(null);
////						slotResources.updateAmount();
////						inventoryStack = slotStack;
////						inventory.setItemStack(inventoryStack);
////					}
////					inventory.onInventoryChanged();
////					return inventoryStack;
////				}
////				return null;
////			case 1:
////				
////				// Right click - Take the half of the stack or the maximal stack-size (The one that is smaller). But at least 1.
////				
////				int amount = Math.max(Math.min(slotStack.stackSize, slotStack.getMaxStackSize()) / 2, 1);
////				slotStack.stackSize -= amount;
////				if(slotStack.stackSize == 0) {
////					slotResources.putStack(null);
////					slotResources.updateAmount();
////				} else {
////					slotResources.onSlotChanged();
////				}
////				inventoryStack = slotStack.copy();
////				inventoryStack.stackSize = amount;
////				inventory.setItemStack(inventoryStack);
////				inventory.onInventoryChanged();
////				return inventoryStack;
////			default:
////				return pickItemStack(inventory, 0, 0);
////		}
////	}
////
////	private void onAmountClick(InventoryPlayer inventory, int type, ClickType clickTypeIn) {
////		ItemStack inventoryStack = inventory.getItemStack();
////		ItemStack slotStack = slotAmount.getStack();
////		
////		switch(type) {
////			case 0:
////				if((clickTypeIn == 1 || inventoryStack != null) && slotAmount.amount == 1) {
////					
////					/* Fixes the problem with minValue + amount == amount + 1
////					 * So that it sets the amount to the amount we want to
////					 */
////					
////					slotAmount.amount = 0;
////				}
////				if(clickTypeIn == 1) {
////					if(slotAmount.amount + AMOUNT_SHIFT_CLICK < slotAmount.getSlotStackLimit()) {
////						slotAmount.amount += AMOUNT_SHIFT_CLICK;
////					} else {
////						slotAmount.amount = slotAmount.getSlotStackLimit();
////					}
////				} else if(inventoryStack != null) {
////					if(slotAmount.amount + inventoryStack.stackSize < slotAmount.getSlotStackLimit()) {
////						slotAmount.amount += inventoryStack.stackSize;
////					} else {
////						slotAmount.amount = slotAmount.getSlotStackLimit();
////					}
////				} else {
////					if(slotAmount.amount < slotAmount.getSlotStackLimit()) {
////						slotAmount.amount++;
////					}
////				}
////				break;
////			case 1:
////				if(clickTypeIn == 1) {
////					if(slotAmount.amount - AMOUNT_SHIFT_CLICK > 1) {
////						slotAmount.amount -= AMOUNT_SHIFT_CLICK;
////					} else {
////						slotAmount.amount = 1;
////					}
////				} else if(inventoryStack != null) {
////					if(slotAmount.amount - inventoryStack.stackSize > 1) {
////						slotAmount.amount -= inventoryStack.stackSize;
////					} else {
////						slotAmount.amount = 1;
////					}
////				} else {
////					if(slotAmount.amount > 1) {
////						slotAmount.amount--;
////					}
////				}
////				break;
////		}
////		
////		slotAmount.onAmountChanged();
////	}
////
////	public TileExpTrader getTileEntity() {
////		return expTrader;
////	}
////	
////	public SlotAmount getSlotAmount() {
////		return slotAmount;
////	}
////	
////	public SlotResources getSlotResources() {
////		return slotResources;
////	}
////
////	public boolean addToInventory(EntityPlayer player, ItemStack stack, boolean tryFirst) {
////		ItemStack[] deltaInventory = new ItemStack[player.inventory.mainInventory.length];
////		
////		for(int slot = 0; slot < deltaInventory.length; slot++) {
////			ItemStack invStack = player.inventory.mainInventory[slot];
////			
////			if(invStack != null) {
////				if(!invStack.isItemEqual(stack)) {
////					continue;
////				}
////				if(invStack.stackSize >= invStack.getMaxStackSize()) {
////					continue;
////				}
////				if(invStack.stackSize + stack.stackSize > invStack.getMaxStackSize()) {
////					stack.stackSize -= (invStack.getMaxStackSize() - invStack.stackSize);
////
////					deltaInventory[slot] = invStack.copy();
////					deltaInventory[slot].stackSize = invStack.getMaxStackSize();
////				} else {
////					deltaInventory[slot] = invStack.copy();
////					deltaInventory[slot].stackSize += stack.stackSize;
////					
////					stack.stackSize = 0;
////					break;
////				}
////			} else {
////				if(stack.stackSize > stack.getMaxStackSize()) {
////					deltaInventory[slot] = stack.copy();
////					deltaInventory[slot].stackSize = deltaInventory[slot].getMaxStackSize();
////					stack.stackSize -= deltaInventory[slot].stackSize;
////				} else {
////					deltaInventory[slot] = stack.copy();
////					
////					stack.stackSize = 0;
////					break;
////				}
////			}
////		}
////		
////		if(!tryFirst || (tryFirst && stack.stackSize <= 0)) {
////			for(int slot = 0; slot < deltaInventory.length; slot++) {
////				if(deltaInventory[slot] != null) {
////					System.out.println("Setting slot " + slot + " to " + deltaInventory[slot].stackSize);
////					putStackInSlot(slot, deltaInventory[slot]);
////				}
////			}
////		}
////		
////		return stack.stackSize <= 0;
////	}
////	
////	public boolean removeFromInventory(EntityPlayer player, ItemStack stack, boolean tryFirst) {
////		ItemStack[] deltaInventory = new ItemStack[player.inventory.mainInventory.length];
////		
////		for(int slot = 0; slot < deltaInventory.length; slot++) {
////			ItemStack invStack = player.inventory.mainInventory[slot];
////			
////			if(invStack != null) {
////				if(!invStack.isItemEqual(stack)) {
////					continue;
////				}
////				
////				if(invStack.stackSize < stack.stackSize) {
////					deltaInventory[slot] = invStack.copy();
////					deltaInventory[slot].stackSize = 0;
////					stack.stackSize -= invStack.stackSize;
////				} else {
////					deltaInventory[slot] = invStack.copy();
////					deltaInventory[slot].stackSize -= stack.stackSize;
////					stack.stackSize = 0;
////					break;
////				}
////			}
////		}
////		
////		if(!tryFirst || (tryFirst && stack.stackSize <= 0)) {
////			for(int slot = 0; slot < deltaInventory.length; slot++) {
////				if(deltaInventory[slot] != null) {
////					System.out.println("Setting slot " + slot);
////					if(deltaInventory[slot].stackSize > 0) {
////						putStackInSlot(slot, deltaInventory[slot]);
////					} else {
////						putStackInSlot(slot, null);
////					}
////				}
////			}
////		}
////		
////		return stack.stackSize <= 0;
////	}
////
////	public void trade() {
////		ExpPlus.DEBUG("Trading " + (!expTrader.worldObj.isRemote ? "(Server)" : "(Client)"));
////		
////		if(expTrader.getExperience() == 0) {
////			return;
////		}
////		
////		if(!expTrader.direction) {
////			// experience against item
////			
////			if(player.experienceTotal < expTrader.getExperience()) {
////				ExpPlus.DEBUG(player.username + " has not enough exp to trade.");
////				return;
////			}
////			
////			if(!slotAmount.getHasStack() || !slotResources.getHasStack() || slotAmount.getStack().stackSize > slotResources.getStack().stackSize) {
////				ExpPlus.DEBUG(expTrader.getOwner() + " has to refill his ExpTrader. Not enough resources.");
////				return;
////			}
////			
////			ItemStack stack = slotAmount.getStack().copy();
////			stack.stackSize = (stack.stackSize > 0) ? stack.stackSize : 1;
////			
////			if(!addToInventory(player, stack.copy(), true)) {
////				ExpPlus.DEBUG(player.username + " has not enough space to trade.");
////				return;
////			}
////			
////			slotResources.getStack().stackSize -= stack.stackSize;
////			if(slotResources.getStack().stackSize == 0) {
////				slotResources.putStack(null);
////			} else {
////				slotResources.onSlotChanged();
////			}
////
////			expTrader.setOwnerExperience(expTrader.getOwnerExperience() + expTrader.getExperience());
////			ExpPlus.getExpManager().setPlayerExp(player, player.experienceTotal - expTrader.getExperience());
////		} else {
////			// item against experience
////			
////			if(expTrader.getOwnerExperience() < expTrader.getExperience()) {
////				ExpPlus.DEBUG(expTrader.getOwner() + " has to refill his ExpTrader. Not enough exp.");
////				return;
////			}
////			
////			if(!slotAmount.getHasStack() || !slotResources.getHasStack() || (slotAmount.getStack().stackSize + slotResources.getStack().stackSize > slotResources.getSlotStackLimit())) {
////				ExpPlus.DEBUG(expTrader.getOwner() + " has to empty his ExpTrader. Not enough space.");
////				return;
////			}
////			
////			ItemStack stack = slotAmount.getStack().copy();
////			stack.stackSize = (stack.stackSize > 0) ? stack.stackSize : 1;
////			
////			if(!removeFromInventory(player, stack.copy(), true)) {
////				ExpPlus.DEBUG(player.username + " has not enough resources to trade.");
////				return;
////			}
////
////			slotResources.getStack().stackSize += stack.stackSize;
////			slotResources.onSlotChanged();
////			
////			expTrader.setOwnerExperience(expTrader.getOwnerExperience() - expTrader.getExperience());
////			ExpPlus.getExpManager().setPlayerExp(player, player.experienceTotal + expTrader.getExperience());
////		}
////	}
//	
//	public EntityPlayer getPlayer() {
//		return player;
//	}
//}
