package net.fayefer.salt_and_silver;

import net.fabricmc.api.ModInitializer;

import net.fayefer.salt_and_silver.block.ModBlocks;
import net.fayefer.salt_and_silver.fluid.ModFluids;
import net.fayefer.salt_and_silver.item.ModItemGroups;
import net.fayefer.salt_and_silver.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaltAndSilver implements ModInitializer {
	public static final String MOD_ID = "salt_and_silver";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();

		ModFluids.registerModFluids();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		LOGGER.info("Hello Fabric world!");
	}
}