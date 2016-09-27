package org.tpc.expplus;

import java.util.Random;

import org.tpc.expplus.common.block.BlockExpFlower;
import org.tpc.expplus.common.itemblock.ItemBlockExpFlower;
import org.tpc.expplus.debug.DebugWindow;
import org.tpc.expplus.debug.NBTTagDebug;
import org.tpc.expplus.proxy.CommonProxy;

import net.minecraft.nbt.NBTBase;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Properties.MOD_ID, useMetadata = true)
public class ExpPlusMod {
    
	public static final Random RANDOM = new Random(System.currentTimeMillis());
	
	public static BlockExpFlower BLOCK_EXPFLOWER;
	public static ItemBlockExpFlower ITEMBLOCK_EXPFLOWER;
	
	@SidedProxy(clientSide = "org.tpc.expplus.proxy.ClientProxy", serverSide = "org.tpc.expplus.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance
	public static ExpPlusMod instance;
	
	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		Property debug = config.get(Properties.MOD_NAME, "Debug", Properties.DEBUG_MODE);
		debug.setComment("Turn debugging on.");
		Properties.DEBUG_MODE = debug.getBoolean(Properties.DEBUG_MODE);
		
		Property expFlowerGeneration = config.get("Generation", "Exp Flower Generation", Properties.EXPFLOWER_GENERATION);
		expFlowerGeneration.setComment("The greater the value the more exp-flowers will be generated.");
		Properties.EXPFLOWER_GENERATION = expFlowerGeneration.getInt();
		
		Property expFlowerGroup = config.get("Generation", "Exp Flower Group", Properties.EXPFLOWER_GROUP);
		expFlowerGroup.setComment("The greater the value the bigger are the groups of exp-flowers.");
		Properties.EXPFLOWER_GROUP = expFlowerGroup.getInt();
		
		config.save();
	}
	
    @EventHandler
    public void onInit(FMLInitializationEvent event) {
    	proxy.register();
    }
    
    // --------------------------------------------------------------
    // Debugging
    // --------------------------------------------------------------
    
    private static DebugWindow debugWindow;
	
	public static void DEBUG(Object value) {
		if(!Properties.DEBUG_MODE) {
			return;
		}
		
		if(debugWindow == null) {
			debugWindow = new DebugWindow();
		}
		
		if(value instanceof NBTBase) {
			value = NBTTagDebug.debug((NBTBase) value);
		}
		
		debugWindow.debug(value);
	}
}
