package com.bauxite.grapecraft.item;

import com.bauxite.grapecraft.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.bauxite.grapecraft.block.VerticalPlantingRackBlock.PLANTED;

public class GrapeSeedItem extends Item {
    public GrapeSeedItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        World world = context.getWorld();
        if (!context.getWorld().isClient && world.getBlockState(pos.down()).getBlock() == Blocks.FARMLAND && world.getBlockState(pos).getBlock() == ModBlocks.VERTICAL_PLANTING_RACK_BLOCK){
            context.getWorld().setBlockState(pos,world.getBlockState(pos).with(PLANTED,true));
        }
        return super.useOnBlock(context);
    }
}
