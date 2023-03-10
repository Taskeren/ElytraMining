package cn.elytra.mod.mining;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {

	public static boolean enableFlatHammers = true;
	public static boolean enableCubeHammers = true;
	public static boolean enableFallingTorch = true;

	public static boolean canShovel = true;

	public static void synchronizeConfiguration(File configFile) {
		Configuration configuration = new Configuration(configFile);
		configuration.load();

		Property propertyEnableFlatHammers =
			configuration.get("general", "enableFlatHammers", true);
		enableFlatHammers = propertyEnableFlatHammers.getBoolean();
		Property propertyEnableCubeHammers =
			configuration.get("general", "enableCubeHammers", true);
		enableCubeHammers = propertyEnableCubeHammers.getBoolean();
		Property propertyEnableFallingTorch =
			configuration.get("general", "enableCubeHammers", true);
		enableFallingTorch = propertyEnableFallingTorch.getBoolean();

		Property propertyCanShovel =
			configuration.get("general", "canShovel", true);
		canShovel = propertyCanShovel.getBoolean();

		if(configuration.hasChanged()) {
			configuration.save();
		}
	}
}
