package net.fayefer.salt_and_silver.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fayefer.salt_and_silver.SaltAndSilver;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ColorCode;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {

    public static final Block RAW_IMPURE_SILVER_BLOCK = registerBlock("raw_impure_silver_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block IMPURE_SILVER_BLOCK = registerBlock("impure_silver_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block IMPURE_SILVER_ORE = registerBlock("impure_silver_ore",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).strength(3.0F, 3.0F).requiresTool().sounds(BlockSoundGroup.STONE)));

    public static final Block DEEPSLATE_IMPURE_SILVER_ORE = registerBlock("deepslate_impure_silver_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 5), AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY).strength(4.5F, 3.0F).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block SILVER_BLOCK = registerBlock("silver_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_GRAY).strength(5.0F, 6.0F).requiresTool().sounds(BlockSoundGroup.METAL)));

    public static final Block SALT_BLOCK = registerBlock("salt_block",
            new ColoredFallingBlock(new ColorCode(15592941), AbstractBlock.Settings.create().mapColor(MapColor.WHITE).strength(0.6F).sounds(BlockSoundGroup.GRAVEL)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SaltAndSilver.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(SaltAndSilver.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        SaltAndSilver.LOGGER.info("Registering Mod Blocks for " + SaltAndSilver.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(RAW_IMPURE_SILVER_BLOCK);
            entries.add(IMPURE_SILVER_ORE);
            entries.add(DEEPSLATE_IMPURE_SILVER_ORE);
            entries.add(SALT_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(IMPURE_SILVER_BLOCK);
            entries.add(SILVER_BLOCK);
        });
    }
}
