package cn.elytra.mod.mining;

import cn.elytra.mod.mining.block.FallingTorch;
import cn.elytra.mod.mining.item.CubeMiningHammer;
import cn.elytra.mod.mining.item.FlatMiningHammer;
import cpw.mods.fml.common.event.*;

public class CommonProxy {

	// preInit "Run before anything else. Read your config, create blocks, items,
	// etc, and register them with the GameRegistry."
	public void preInit(FMLPreInitializationEvent event) {
		Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

		if(Config.enableFlatHammers) {
			FlatMiningHammer.preInit();
		}
		if(Config.enableCubeHammers) {
			CubeMiningHammer.preInit();
		}
		if(Config.enableFallingTorch) {
			FallingTorch.preInit();
		}
	}

	// load "Do your mod setup. Build whatever data structures you care about. Register recipes."
	public void init(FMLInitializationEvent event) {
		if(Config.enableFlatHammers) {
			FlatMiningHammer.init();
		}
		if(Config.enableCubeHammers) {
			CubeMiningHammer.init();
		}
		if(Config.enableFallingTorch) {
			FallingTorch.init();
		}
	}

	// postInit "Handle interaction with other mods, complete your setup based on this."
	public void postInit(FMLPostInitializationEvent event) {
	}

	public void serverAboutToStart(FMLServerAboutToStartEvent event) {
	}

	// register server commands in this event handler
	public void serverStarting(FMLServerStartingEvent event) {
	}

	public void serverStarted(FMLServerStartedEvent event) {
	}

	public void serverStopping(FMLServerStoppingEvent event) {
	}

	public void serverStopped(FMLServerStoppedEvent event) {
	}
}
