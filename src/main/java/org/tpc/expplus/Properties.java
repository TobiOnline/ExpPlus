package org.tpc.expplus;

import java.io.File;

import net.minecraft.util.ResourceLocation;

public class Properties {

	public static final String MOD_ID = "expplus";
	public static final String MOD_NAME = "Exp Plus";
	public static final String VERSION = "0.1";

	public static final File DATA_DIRECTORY = new File("org/tpc/expplus/");

	public static boolean DEBUG_MODE = true;

	public static final String RESOURCE_DOMAIN = "expplus";

	public static final String PATH_TEXTURES = "textures/";
	public static final String PATH_BLOCKS = PATH_TEXTURES + "blocks/";
	public static final String PATH_GUI = PATH_TEXTURES + "gui/";
	public static final String PATH_ITEMS = PATH_TEXTURES + "items/";

	public static int EXPFLOWER_GENERATION = 16;
	public static int EXPFLOWER_GROUP = 8;
	public static int EXPFLOWER_MAXDROP = 3;

//	public static int EXPTRADER_ID = 2209;
//	public static int EXPGENERATOR_ID = 2210;
//	public static int EXPFLOWER_ID = 2211;

	public static String EXPFLOWER_NAME = "exp_flower";
//	public static String EXPTRADER_NAME = "ExpTrader";
//	public static String EXPGENERATOR_NAME = "ExpGenerator";

	// public static final int GUI_EXPTRADER = 0x01;
	// public static final int GUI_EXPGENERATOR = 0x02;
	
	public static final ResourceLocation BLOCK_EXPFLOWER = new ResourceLocation(Properties.RESOURCE_DOMAIN,
			Properties.EXPFLOWER_NAME);

	// public static final ResourceLocation TEXTURE_FONT = new
	// ResourceLocation("font/default.png");

	// public static final ResourceLocation TEXTURE_EXPTRADER = new
	// ResourceLocation("expplus",
	// PATH_BLOCKS + "ExpTrader.png");
	// public static final ResourceLocation TEXTURE_EXPGENERATOR = new
	// ResourceLocation("expplus",
	// PATH_BLOCKS + "ExpGenerator.png");
	//
	// public static final ResourceLocation TEXTURE_EXPTRADER_GUI = new
	// ResourceLocation("expplus",
	// PATH_GUI + "ExpTrader.png");
	// public static final ResourceLocation TEXTURE_EXPGENERATOR_GUI = new
	// ResourceLocation("expplus",
	// PATH_GUI + "ExpGenerator.png");

	// public static final ResourceLocation TEXTURE_EXPTRADER_ANIMATION = new
	// ResourceLocation("expplus",
	// PATH_BLOCKS + "ExpTraderAnimation.png");


}
