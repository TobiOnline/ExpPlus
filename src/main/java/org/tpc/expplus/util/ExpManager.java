package org.tpc.expplus.util;


import org.tpc.expplus.ExpPlusMod;

import net.minecraft.entity.player.EntityPlayer;

public class ExpManager {
	
//	private Map<String, Integer> globalExperience;
	
	public ExpManager() {
//		globalExperience = new HashMap<String, Integer>();
	}

	public void setPlayerExp(EntityPlayer player, int exp) {
		player.experienceTotal = exp;
		player.experienceLevel = getExperienceLevel(exp);
		player.experience = getExperienceBarValue(exp);
		
		ExpPlusMod.DEBUG("---------------------------------");
		ExpPlusMod.DEBUG("[Player]");
		ExpPlusMod.DEBUG("Experience: " + exp);
		ExpPlusMod.DEBUG("Level: " + player.experienceLevel);
		ExpPlusMod.DEBUG("Progress: " + player.experience);
		ExpPlusMod.DEBUG("---------------------------------");
	}
	
//	public void setTileEntityExp(TileExpPlusMod tileEntity, int exp) {
//		tileEntity.setExperience(exp);
//		
//		ExpPlusMod.DEBUG("---------------------------------");
//		ExpPlusMod.DEBUG("[TileEntity]");
//		ExpPlusMod.DEBUG("Experience: " + exp);
//		ExpPlusMod.DEBUG("---------------------------------");
//	}

//	public void setGlobalExp(String player, int exp) {
//		setGlobalExp(player, exp);
//		
//		ExpPlusMod.DEBUG("---------------------------------");
//		ExpPlusMod.DEBUG("[Global]");
//		ExpPlusMod.DEBUG("Experience: " + exp);
//		ExpPlusMod.DEBUG("Player: " + player);
//		ExpPlusMod.DEBUG("---------------------------------");
//		
//		globalExperience.put(player, exp);
//		saveGlobalExp(player);
//	}
//	
//	public int getGlobalExp(String player) {
//		if (!globalExperience.containsKey(player)) {
//			globalExperience.put(player, loadGlobalExp(player));
//		}
//		return globalExperience.get(player);
//	}
	
//	public File getDirectory() {
//		return Properties.DATA_DIRECTORY;
//	}
	
//	public int loadGlobalExp(String player) {
//		File saveFile = new File(getDirectory(), player + ".dat");
//		if (!saveFile.exists()) {
//			return -1;
//		}
//		
//		BufferedReader in = null;
//		try {
//			in = new BufferedReader(new FileReader(saveFile));
//			return Integer.parseInt(in.readLine());
//		} catch (IOException e) {
//		} catch (NumberFormatException e) {
//		} finally {
//			try {
//				in.close();
//			} catch (IOException e) {
//			}
//		}
//		return -1;
//	}
//
//	public void saveGlobalExp(String player) {
//		// Don't save on client
//		
//		if (!getDirectory().exists()) {
//			getDirectory().mkdirs();
//		}
//		
//		if (!globalExperience.containsKey(player)) {
//			return;
//		}
//		
//		File saveFile = new File(getDirectory(), player + ".dat");
//		if (saveFile.exists()) {
//			saveFile.delete();
//		}
//
//		if (globalExperience.get(player) <= 0) {
//			return;
//		}
//
//		BufferedWriter out = null;
//		try {
//			out = new BufferedWriter(new FileWriter(saveFile));
//			out.write(String.valueOf(globalExperience.get(player).intValue()));
//		} catch (IOException e) {
//		} finally {
//			try {
//				out.close();
//			} catch (IOException e) {
//			}
//		}
//	}

	public static int getExperienceLevel(int xp) {
		int level = 0;
		while (xp >= ExpManager.xpBarCap(level + 1)) {
			xp -= ExpManager.xpBarCap(++level);
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
		player.experienceTotal = ExpManager.getExperienceOf(player.experienceLevel, player.experience);
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
		while (xp >= ExpManager.xpBarCap(level)) {
			xp -= ExpManager.xpBarCap(level);
			level++;
		}

		return ((float) xp / (float) ExpManager.xpBarCap(level));
	}
}
