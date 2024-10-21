package net.fayefer.salt_and_silver.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class EctoplasmItem extends Item {
    public EctoplasmItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        if(!world.isClient()) {
            Block aboveBlock = world.getBlockState(context.getBlockPos().up()).getBlock();
            if(aboveBlock == Blocks.AIR && clickedBlock != Blocks.BEDROCK && clickedBlock != Blocks.REINFORCED_DEEPSLATE) {
                world.setBlockState(context.getBlockPos().up(), world.getBlockState(context.getBlockPos()));
                world.setBlockState(context.getBlockPos(), Blocks.AIR.getDefaultState());

                ServerWorld serverWorld = (ServerWorld) context.getWorld();
                serverWorld.spawnParticles(ParticleTypes.SOUL, context.getBlockPos().getX(), context.getBlockPos().getY(), context.getBlockPos().getZ(),
                        16, 0,0,0,0.1);
                world.playSound((PlayerEntity)null, context.getBlockPos(),
                        SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.NEUTRAL, 0.5F, 0.4F);

                context.getStack().decrement(1);
            }
        }
                return ActionResult.SUCCESS;
    }
}
