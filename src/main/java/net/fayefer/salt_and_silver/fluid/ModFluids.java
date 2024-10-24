package net.fayefer.salt_and_silver.fluid;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.EmptyItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.base.FullItemFluidStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fayefer.salt_and_silver.SaltAndSilver;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Items.BUCKET;
import static net.minecraft.item.Items.MILK_BUCKET;


public class ModFluids {
    public static final FlowableFluid MILK_FLUID_STILL = register("milk_fluid_still", new MilkFluid.Still());
    public static final FlowableFluid MILK_FLUID_FLOWING = register("milk_fluid_flowing", new MilkFluid.Flowing());

    public static final FlowableFluid ECTOPLASM_FLUID_STILL = register("ectoplasm_fluid_still", new EctoplasmFluid.Still());
    public static final FlowableFluid ECTOPLASM_FLUID_FLOWING = register("ectoplasm_fluid_flowing", new EctoplasmFluid.Flowing());

    private static FlowableFluid register(String name, FlowableFluid flowableFluid) {
        return Registry.register(Registries.FLUID, Identifier.of(SaltAndSilver.MOD_ID, name), flowableFluid);
    }
    public static void registerModFluids() {
        SaltAndSilver.LOGGER.info("Registering Mod Fluids for " + SaltAndSilver.MOD_ID);
    }
}