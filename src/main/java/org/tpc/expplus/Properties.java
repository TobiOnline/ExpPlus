package org.tpc.expplus;

import net.minecraft.util.ResourceLocation;

public class Properties {

	public static final String MOD_ID = "expplus";
	public static final String MOD_NAME = "Exp Plus";
	public static final String MOD_VERSION = "0.1";
	public static final String RESOURCE_DOMAIN = MOD_ID;

	public static final String RESOURCE_PREFIX_TEXTURES = "textures/";
	public static final String RESOURCE_PREFIX_GUI = RESOURCE_PREFIX_TEXTURES + "gui/";
	public static final String RESOURCE_PREFIX_BLOCK = RESOURCE_PREFIX_TEXTURES + "blocks/";
	public static final String RESOURCE_PREFIX_MODEL = "block/";

	public static boolean DEBUG_MODE = true;

	public static int DEFAULT_EXPFLOWER_GENERATION = 16;
	public static int DEFAULT_EXPFLOWER_GROUP = 8;
	public static int DEFAULT_EXPFLOWER_MAXDROP = 3;

	public static String NAME_EXPFLOWER = "exp_flower";
	public static String NAME_EXPTRADER = "exp_trader";

	public static final int GUI_ID_EXPTRADER = 0x01;

	public static final ResourceLocation RESOURCE_BLOCK_EXPFLOWER = new ResourceLocation(RESOURCE_DOMAIN,
			NAME_EXPFLOWER);
	public static final ResourceLocation RESOURCE_BLOCK_EXPTRADER = new ResourceLocation(RESOURCE_DOMAIN,
			NAME_EXPTRADER);

	public static final ResourceLocation RESOURCE_TEXURE_ANIMATIONS = new ResourceLocation(RESOURCE_DOMAIN,
			RESOURCE_PREFIX_BLOCK + "animations.png");

	public static final ResourceLocation RESOURCE_GUI_EXPTRADER = new ResourceLocation(RESOURCE_DOMAIN,
			RESOURCE_PREFIX_GUI + NAME_EXPTRADER + ".png");

	public static final String MESSAGE_IN_USE = "message.in_use";
}
