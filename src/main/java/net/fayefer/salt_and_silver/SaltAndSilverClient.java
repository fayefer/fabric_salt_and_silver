package net.fayefer.salt_and_silver;

import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fayefer.salt_and_silver.fluid.ModFluids;

public class SaltAndSilverClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.MILK_FLUID_STILL, new SimpleFluidRenderHandler(
                SimpleFluidRenderHandler.WATER_STILL, SimpleFluidRenderHandler.WATER_FLOWING, SimpleFluidRenderHandler.WATER_OVERLAY, 0xFFFFFF)
        );
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.MILK_FLUID_FLOWING, new SimpleFluidRenderHandler(
               SimpleFluidRenderHandler.WATER_STILL, SimpleFluidRenderHandler.WATER_FLOWING, SimpleFluidRenderHandler.WATER_OVERLAY, 0xFFFFFF)
        );

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.ECTOPLASM_FLUID_STILL, new SimpleFluidRenderHandler(
                SimpleFluidRenderHandler.WATER_STILL, SimpleFluidRenderHandler.WATER_FLOWING, SimpleFluidRenderHandler.WATER_OVERLAY, 0x6cbbe0)
        );
        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.ECTOPLASM_FLUID_FLOWING, new SimpleFluidRenderHandler(
                SimpleFluidRenderHandler.WATER_STILL, SimpleFluidRenderHandler.WATER_FLOWING, SimpleFluidRenderHandler.WATER_OVERLAY, 0x6cbbe0)
        );
    }
}
