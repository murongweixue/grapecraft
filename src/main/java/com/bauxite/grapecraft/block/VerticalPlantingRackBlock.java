package com.bauxite.grapecraft.block;

import com.bauxite.grapecraft.init.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class VerticalPlantingRackBlock extends Block implements Fertilizable {

    public static final BooleanProperty PLANTED = BooleanProperty.of("planted");

    public VerticalPlantingRackBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(PLANTED, false));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND || world.getBlockState(pos.down()).getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state, boolean isClient) {
        return world.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND && state == state.with(PLANTED,true);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos;
        while (world.getBlockState(blockPos.up()) == world.getBlockState(blockPos.up()).with(PLANTED, true)) {
            blockPos = blockPos.add(0, 1, 0);
        }
        if (world.getBlockState(blockPos.up()).getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK || world.getBlockState(blockPos.up()).getBlock() == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK) {
            world.setBlockState(blockPos.up(), world.getBlockState(blockPos.up()).with(PLANTED, true));
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PLANTED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid((double) 7 /16,0, (double) 7 /16, (double) 9 /16,1, (double) 9 /16);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        Block block = world.getBlockState(pos.up()).getBlock();
        if (state.get(PLANTED) /*&& random.nextFloat() <= 0.6*/) {
            if (block == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK) {
                world.setBlockState(pos.up(), world.getBlockState(pos.up()).with(PLANTED, true));
            } else if (block == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK) {
                world.setBlockState(pos.up(), world.getBlockState(pos.up()).with(PLANTED, true));
            }
        }
        super.randomTick(state, world, pos, random);
    }

}
