package net.fayefer.salt_and_silver.fluid;

import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MilkFluidBlock extends FluidBlock {
    public MilkFluidBlock(FlowableFluid fluid, Settings settings) {
        super(fluid, settings);
    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity livingEntity) {
            if (!livingEntity.getActiveStatusEffects().isEmpty()) {
                livingEntity.clearStatusEffects();
            }
        }
    }
}
