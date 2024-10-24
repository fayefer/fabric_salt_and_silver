package net.fayefer.salt_and_silver.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fayefer.salt_and_silver.SaltAndSilver;
import net.fayefer.salt_and_silver.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup SALT_AND_SILVER_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(SaltAndSilver.MOD_ID, "salt_and_silver"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.IMPURE_SILVER_INGOT))
                    .displayName(Text.translatable("itemgroup.salt_and_silver.salt_and_silver"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_IMPURE_SILVER);
                        entries.add(ModItems.IMPURE_SILVER_INGOT);
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);

                        entries.add(ModItems.ECTOPLASM);

                        entries.add(ModItems.ECTOPLASM_FLUID);

                        entries.add(ModBlocks.RAW_IMPURE_SILVER_BLOCK);
                        entries.add(ModBlocks.IMPURE_SILVER_BLOCK);
                        entries.add(ModBlocks.IMPURE_SILVER_ORE);
                        entries.add(ModBlocks.DEEPSLATE_IMPURE_SILVER_ORE);
                        entries.add(ModBlocks.SILVER_BLOCK);
                        entries.add(ModBlocks.SALT_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        SaltAndSilver.LOGGER.info("Registering Item Groups for " + SaltAndSilver.MOD_ID);
    }
}
