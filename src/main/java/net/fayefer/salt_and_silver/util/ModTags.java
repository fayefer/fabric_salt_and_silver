package net.fayefer.salt_and_silver.util;

import net.fayefer.salt_and_silver.SaltAndSilver;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Fluids {
        private static TagKey<Fluid> createTag(String name) {
            return TagKey.of(RegistryKeys.FLUID, Identifier.of(SaltAndSilver.MOD_ID, name));
        }
        public static final TagKey<Fluid> MILK_TAG = createTag("milk_tag");
        public static final TagKey<Fluid> ECTOPLASM_FLUID_TAG = createTag("ectoplasm_fluid_tag");
    }



    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(SaltAndSilver.MOD_ID, name));
        }
    }

    public static class Items {
        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(SaltAndSilver.MOD_ID, name));
        }
    }
}
