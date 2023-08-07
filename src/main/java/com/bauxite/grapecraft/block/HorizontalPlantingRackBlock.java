package com.bauxite.grapecraft.block;

import com.bauxite.grapecraft.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class HorizontalPlantingRackBlock extends Block {

    public static final BooleanProperty PLANTED = BooleanProperty.of("planted");

    public static final BooleanProperty CONNECTED = BooleanProperty.of("connected");

    public HorizontalPlantingRackBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(PLANTED, false));
        setDefaultState(getDefaultState().with(CONNECTED, false));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {

        if (world.getBlockState(pos.down()).getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK ||
                world.getBlockState(pos.north()).getBlock() == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK ||
                world.getBlockState(pos.south()).getBlock() == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK ||
                world.getBlockState(pos.east()).getBlock() == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK ||
                world.getBlockState(pos.west()).getBlock() == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK
        ){
            return true;
        }
        return false;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(PLANTED,CONNECTED);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if(!world.isClient && world.getBlockState(pos.down()).getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK){
            world.setBlockState(pos,state.with(CONNECTED,true).with(PLANTED,false));
        }
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(PLANTED) /*&& random.nextFloat() <= 0.6*/){
            if ((!state.get(CONNECTED)) && world.getBlockState(pos.down()).isAir()){
                world.setBlockState(pos.down(),ModBlocks.GRAPE_BLOCK.getDefaultState());
            }
            for (int i = 0; i < 64; ++i) {
                BlockPos newPos = pos.add(random.nextBetween(-1, 2), 0, random.nextBetween(-1, 2));
                if (world.getBlockState(newPos) == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK.getDefaultState().with(PLANTED, false) || world.getBlockState(newPos) == ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK.getDefaultState().with(PLANTED, false).with(CONNECTED,true)) {
                    world.setBlockState(newPos, world.getBlockState(newPos).with(PLANTED,true));
                    break;
                }
            }
        }
        super.randomTick(state, world, pos, random);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.DOWN && neighborState.getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK){
            return state.with(CONNECTED,true);
        }
        else if (direction == Direction.DOWN && neighborState.getBlock() == Blocks.AIR) {
            return state.with(CONNECTED,false);
        }
        return state;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape1 = VoxelShapes.cuboid((double) 7 /16,0,0, (double) 9 /16, (double) 2 /16,1);
        VoxelShape shape2 = VoxelShapes.cuboid(0,0, (double) 7 /16, 1, (double) 2 /16, (double) 9 /16);
        return VoxelShapes.union(shape1,shape2);
    }
}
