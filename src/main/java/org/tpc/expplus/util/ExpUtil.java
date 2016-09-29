package org.tpc.expplus.util;

import net.minecraft.entity.player.EntityPlayer;

import static org.tpc.expplus.ExpPlus.DEBUG;

public class ExpUtil {

	private ExpUtil() { }

	public static void setPlayerExp(EntityPlayer player, int exp) {
		player.experienceTotal = exp;
		player.experienceLevel = getExperienceLevel(exp);
		player.experience = getExperienceBarValue(exp);
		
		DEBUG("---------------------------------");
		DEBUG("[Player]");
		DEBUG("Experience: " + exp);
		DEBUG("Level: " + player.experienceLevel);
		DEBUG("Progress: " + player.experience);
		DEBUG("---------------------------------");
	}

//	public void setTileEntityExp(TileExpPlusMod tileEntity, int exp) {
//		tileEntity.setExperience(exp);
//		
//		DEBUG("---------------------------------");
//		DEBUG("[TileEntity]");
//		DEBUG("Experience: " + exp);
//		DEBUG("---------------------------------");
//	}

//	public void setGlobalExp(String player, int exp) {
//		setGlobalExp(player, exp);
//		
//		DEBUG("---------------------------------");
//		DEBUG("[Global]");
//		DEBUG("Experience: " + exp);
//		DEBUG("Player: " + player);
//		DEBUG("---------------------------------");
//		
//		globalExperience.put(player, exp);
//	}
//	
//	public int getGlobalExp(String player) {
//		if (!globalExperience.containsKey(player)) {
//			globalExperience.put(player, loadGlobalExp(player));
//		}
//		return globalExperience.get(player);
//	}

	public static int getExperienceLevel(int xp) {
		int level = 0;
		while (xp >= ExpUtil.xpBarCap(level + 1)) {
			xp -= ExpUtil.xpBarCap(++level);
		}
		return level;
	}

	public static int getExperienceNeededForLevel(int level) {
		int xp = 0;
		for (; level > 0; level--) {
			xp += xpBarCap(level);
		}
		return xp;
	}
	
	public static void recalculateExp(EntityPlayer player) {
		player.experienceTotal = ExpUtil.getExperienceOf(player.experienceLevel, player.experience);
	}

	public static int getExperienceOf(int level, float expBar) {
		return getExperienceNeededForLevel(level) + (int) (expBar * (float) xpBarCap(level));
	}

	public static int xpBarCap(int level) {
		return level >= 30 ? 62 + ((level - 30) * 7) : (level >= 15 ? 17 + ((level - 15) * 3) : 17);
	}

	public static int getXPSplit(int xp) {
		return xp >= 2477 ? 2477 : (xp >= 1237 ? 1237 : (xp >= 617 ? 617 : (xp >= 307 ? 307 : (xp >= 149 ? 149
				: (xp >= 73 ? 73 : (xp >= 37 ? 37 : (xp >= 17 ? 17 : (xp >= 7 ? 7 : (xp >= 3 ? 3 : 1)))))))));
	}

	public static float getExperienceBarValue(int experience) {
		if (experience == 0) {
			return 0.0F;
		}
		int level = 1;
		int xp = experience;
		while (xp >= ExpUtil.xpBarCap(level)) {
			xp -= ExpUtil.xpBarCap(level);
			level++;
		}

		return ((float) xp / (float) ExpUtil.xpBarCap(level));
	}
}
