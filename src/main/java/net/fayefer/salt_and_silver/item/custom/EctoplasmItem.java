package net.fayefer.salt_and_silver.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class EctoplasmItem extends Item {
    private static final Map<Block, Block> ECTOPLASM_MAP =
            Map.of(
                    Blocks.STONE, Blocks.NETHERRACK,
                    Blocks.STONE_BRICKS, Blocks.NETHER_BRICKS,
                    Blocks.COBBLESTONE, Blocks.BLACKSTONE,
                    Blocks.DIRT, Blocks.SOUL_SOIL,
                    Blocks.SAND, Blocks.SOUL_SAND
            );

    public EctoplasmItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);

        if(!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80));
            world.playSound((PlayerEntity) null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.PLAYERS,
                    0.5F, 0.4F);
            serverWorld.spawnParticles(ParticleTypes.SOUL, user.getX() + 0.5, user.getY() + 0.5, user.getZ() + 0.5,
                    16, 0.1, 0.1, 0.1, 0.1);
            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, user.getX() + 0.5, user.getY() + 1.5, user.getZ() + 0.5,
                    16, 0.1, 0.1, 0.1, 0.1);
            itemStack.decrement(1);
        }
        return TypedActionResult.success(itemStack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = entity.getWorld();
        LivingEntity mob = entity;

        if(!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            if(mob.getType() == EntityType.SLIME) {
                MagmaCubeEntity magmaCube = EntityType.MAGMA_CUBE.create(world);
                magmaCube.setSize(((SlimeEntity) mob).getSize(), false);
                magmaCube.setPosition(mob.getPos());
                serverWorld.spawnEntityAndPassengers(magmaCube);
                mob.discard();
                mob = magmaCube;
            }
            else if(mob.getType() == EntityType.SKELETON) {
                WitherSkeletonEntity witherSkeleton = EntityType.WITHER_SKELETON.create(world);
                witherSkeleton.setPosition(mob.getPos());
                serverWorld.spawnEntityAndPassengers(witherSkeleton);
                mob.discard();
                mob = witherSkeleton;
            }
            else if(mob.getType() == EntityType.BREEZE) {
                BlazeEntity blaze = EntityType.BLAZE.create(world);
                blaze.setPosition(mob.getPos());
                serverWorld.spawnEntityAndPassengers(blaze);
                mob.discard();
                mob = blaze;
            }
            else if(mob.getType() == EntityType.PHANTOM) {
                GhastEntity ghast = EntityType.GHAST.create(world);
                ghast.setPosition(mob.getPos());
                serverWorld.spawnEntityAndPassengers(ghast);
                mob.discard();
                mob = ghast;
            }
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80));
            mob.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80));
            mob.playSound(SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, 0.5F, 0.4F);
            serverWorld.spawnParticles(ParticleTypes.SOUL, mob.getX() + 0.5, mob.getY() + 0.5, mob.getZ() + 0.5,
                    16, 0.1, 0.1, 0.1, 0.1);
            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, mob.getX() + 0.5, mob.getY() + 1.5, mob.getZ() + 0.5,
                    16, 0.1, 0.1, 0.1, 0.1);
            stack.decrement(1);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos clickedPos = context.getBlockPos();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        Block aboveBlock = world.getBlockState(context.getBlockPos().up()).getBlock();
        PlayerEntity player = context.getPlayer();
        ItemStack itemStack = context.getStack();

        if(!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;
            if (player != null) {
                if (!player.isSneaking()) {
                    if (clickedBlock != Blocks.BEDROCK && clickedBlock != Blocks.REINFORCED_DEEPSLATE) {
                        if (aboveBlock == Blocks.AIR || aboveBlock == Blocks.WATER || aboveBlock == Blocks.LAVA) {
                            if (ECTOPLASM_MAP.containsKey(clickedBlock)) {
                                world.setBlockState(context.getBlockPos(), ECTOPLASM_MAP.get(clickedBlock).getDefaultState());
                            }
                            world.setBlockState(clickedPos.up(), world.getBlockState(clickedPos));
                            world.setBlockState(clickedPos, aboveBlock.getDefaultState());
                            serverWorld.spawnParticles(ParticleTypes.SOUL, clickedPos.getX() + 0.5, clickedPos.getY() + 0.5, clickedPos.getZ() + 0.5,
                                    16, 0.1, 0.1, 0.1, 0.1);
                            serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, clickedPos.getX() + 0.5, clickedPos.getY() + 1.5, clickedPos.getZ() + 0.5,
                                    16, 0.1, 0.1, 0.1, 0.1);
                            world.playSound((PlayerEntity) null, clickedPos,
                                    SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.NEUTRAL, 0.5F, 0.4F);
                            itemStack.decrement(1);
                        }
                    }
                }
                else {
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80));
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 80));
                    serverWorld.spawnParticles(ParticleTypes.SOUL, player.getX() + 0.5, player.getY() + 0.5, player.getZ() + 0.5,
                            16, 0.1, 0.1, 0.1, 0.1);
                    serverWorld.spawnParticles(ParticleTypes.SOUL_FIRE_FLAME, player.getX() + 0.5, player.getY() + 1.5, player.getZ() + 0.5,
                            16, 0.1, 0.1, 0.1, 0.1);
                    world.playSound((PlayerEntity) null, clickedPos, SoundEvents.ENTITY_ALLAY_AMBIENT_WITHOUT_ITEM, SoundCategory.PLAYERS,
                            0.5F, 0.4F);
                    itemStack.decrement(1);
                }
            }
        }
                return ActionResult.SUCCESS;
    }
}
