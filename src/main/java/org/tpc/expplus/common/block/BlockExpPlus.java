package org.tpc.expplus.common.block;

import org.tpc.expplus.ExpPlus;
import org.tpc.expplus.common.tile.TileExpPlus;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockExpPlus extends BlockContainer {

	public BlockExpPlus() {
		super(Material.ROCK);

		setCreativeTab(CreativeTabs.REDSTONE);
		setSoundType(SoundType.STONE);
		setHardness(10.0F);
	}
	
	@Override
	public float getExplosionResistance(Entity exploder) {
		return 2000.0F;
	}

	@Override
	public boolean canEntityDestroy(IBlockState state, IBlockAccess world, BlockPos pos, Entity entity) {
		if (!(entity instanceof EntityPlayer))
			return false;

		EntityPlayer player = ((EntityPlayer) entity);
		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof TileExpPlus))
			return false;

		return ((TileExpPlus) tile).isOwner(player);
	}

	@Override
	public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player,
			boolean willHarvest) {
		if (world.isRemote)
			return true;

		TileEntity tile = world.getTileEntity(pos);
		if (!(tile instanceof TileExpPlus))
			return super.removedByPlayer(state, world, pos, player, willHarvest);

		TileExpPlus tileExpPlus = (TileExpPlus) tile;
		if (tileExpPlus.isOwner(player)) {
			super.removedByPlayer(state, world, pos, player, willHarvest);
			return true;
		}

		return false;
	}

	@Override
	protected boolean canSilkHarvest() {
		return false;
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer,
			ItemStack stack) {
		if (!(placer instanceof EntityPlayer))
			return;

		TileEntity tile = worldIn.getTileEntity(pos);
		if ((tile == null) || !(tile instanceof TileExpPlus))
			return;

		((TileExpPlus) tile).setOwner(((EntityPlayer) placer).getUniqueID());
		tile.markDirty();
	}

	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean isBlockNormalCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
		return false;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		// Drop through if the player is sneaking
		return !playerIn.isSneaking();
	}

	public void dropExp(int experience, World world, BlockPos pos) {
		while (experience > 0) {
			int exp = EntityXPOrb.getXPSplit(experience);
			experience -= exp;
			world.spawnEntityInWorld(new EntityXPOrb(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.75D,
					(double) pos.getZ() + 0.5D, exp));
		}
	}

	protected void dropItem(ItemStack item, World world, int x, int y, int z) {
		if (world.isRemote)
			return;

		if ((item != null) && (item.stackSize > 0)) {
			EntityItem entityItem = new EntityItem(world, x + (ExpPlus.RANDOM.nextFloat() * 0.8F) + 0.1F,
					y + (ExpPlus.RANDOM.nextFloat() * 0.8F) + 0.1F,
					z + (ExpPlus.RANDOM.nextFloat() * 0.8F) + 0.1F,
					new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));

			if (item.hasTagCompound()) {
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}

			float factor = 0.05F;
			entityItem.motionX = ExpPlus.RANDOM.nextGaussian() * factor;
			entityItem.motionY = (ExpPlus.RANDOM.nextGaussian() * factor) + 0.2F;
			entityItem.motionZ = ExpPlus.RANDOM.nextGaussian() * factor;
			world.spawnEntityInWorld(entityItem);
			item.stackSize = 0;
		}
	}
}
