package org.tpc.expplus.common.generator;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.event.terraingen.TerrainGen;
import net.minecraftforge.fml.common.IWorldGenerator;

public class ExpPlusWorldGenerator implements IWorldGenerator {

	private final Block block;
	private final int triesPerChunk;
	private final int groupFactor;

	public ExpPlusWorldGenerator(Block block, int triesPerChunk, int groupFactor) {
		this.block = block;
		this.triesPerChunk = triesPerChunk;
		this.groupFactor = groupFactor;
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		for (int chunkTry = 0; chunkTry < triesPerChunk; ++chunkTry) {

			int groupX = chunkX * 16 + random.nextInt(16) + 8;
			int groupY = random.nextInt(128);
			int groupZ = chunkZ * 16 + random.nextInt(16) + 8;

			for (int blockTry = 0; blockTry < groupFactor; blockTry++) {

				int x = groupX + random.nextInt(16) - 8;
				int y = groupY + random.nextInt(8) - 4;
				int z = groupZ + random.nextInt(16) - 8;
				BlockPos position = new BlockPos(x, y, z);

				if (TerrainGen.decorate(world, random, position, FLOWERS) && world.isAirBlock(position)
						&& (!world.provider.getHasNoSky() || y < 127) && block.canPlaceBlockAt(world, position)) {
					world.setBlockState(position, block.getDefaultState());
				}
			}
		}
	}

}
