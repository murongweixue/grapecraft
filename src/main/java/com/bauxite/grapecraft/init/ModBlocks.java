package com.bauxite.grapecraft.init;

import com.bauxite.grapecraft.block.GrapeBlock;
import com.bauxite.grapecraft.block.HorizontalPlantingRackBlock;
import com.bauxite.grapecraft.block.VerticalPlantingRackBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final VerticalPlantingRackBlock VERTICAL_PLANTING_RACK_BLOCK = new VerticalPlantingRackBlock(FabricBlockSettings.create().breakInstantly().ticksRandomly().strength(0.2F).sounds(BlockSoundGroup.WOOD).noCollision());

    public static final HorizontalPlantingRackBlock HORIZONTAL_PLANTING_RACK_BLOCK = new HorizontalPlantingRackBlock(FabricBlockSettings.create().breakInstantly().ticksRandomly().strength(0.2F).sounds(BlockSoundGroup.WOOD).noCollision());

    public static final GrapeBlock GRAPE_BLOCK = new GrapeBlock(FabricBlockSettings.create().noCollision().sounds(BlockSoundGroup.GRASS));

    public static void init(){

        Registry.register(Registries.BLOCK,new Identifier("grapecraft","vertical_planting_rack_block"),VERTICAL_PLANTING_RACK_BLOCK);

        Registry.register(Registries.BLOCK,new Identifier("grapecraft","horizontal_planting_rack_block"),HORIZONTAL_PLANTING_RACK_BLOCK);

        Registry.register(Registries.BLOCK,new Identifier("grapecraft","grape"),GRAPE_BLOCK);

    }
}
