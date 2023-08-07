package com.bauxite.grapecraft.init;

import com.bauxite.grapecraft.item.GrapeItem;
import com.bauxite.grapecraft.item.GrapeSeedItem;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.bauxite.grapecraft.init.ModBlocks.HORIZONTAL_PLANTING_RACK_BLOCK;
import static com.bauxite.grapecraft.init.ModBlocks.VERTICAL_PLANTING_RACK_BLOCK;

public class ModItems {

    public static final BlockItem HORIZONTAL_PLANTING_RACK_BLOCK_ITEM = new BlockItem(HORIZONTAL_PLANTING_RACK_BLOCK,new Item.Settings());

    public static final BlockItem VERTICAL_PLANTING_RACK_BLOCK_ITEM = new BlockItem(VERTICAL_PLANTING_RACK_BLOCK,new Item.Settings());

    public static final GrapeSeedItem GRAPE_SEED_ITEM = new GrapeSeedItem(new Item.Settings());

    public static final GrapeItem GRAPE_ITEM = new GrapeItem(new Item.Settings().food(FoodComponents.APPLE));

    private static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(GRAPE_ITEM))
            .displayName(Text.translatable("itemGroup.grapecraft.test_group"))
            .entries(((displayContext, entries) -> {
                        entries.add(HORIZONTAL_PLANTING_RACK_BLOCK_ITEM);
                        entries.add(VERTICAL_PLANTING_RACK_BLOCK_ITEM);
                        entries.add(GRAPE_ITEM);
                        entries.add(GRAPE_SEED_ITEM);

                    }
                )
            )
            .build();

    public static void init(){

        Registry.register(Registries.ITEM,new Identifier("grapecraft","horizontal_planting_rack"),HORIZONTAL_PLANTING_RACK_BLOCK_ITEM);

        Registry.register(Registries.ITEM,new Identifier("grapecraft","vertical_planting_rack"),VERTICAL_PLANTING_RACK_BLOCK_ITEM);

        Registry.register(Registries.ITEM,new Identifier("grapecraft","grape_seeds"),GRAPE_SEED_ITEM);

        Registry.register(Registries.ITEM,new Identifier("grapecraft","grape"),GRAPE_ITEM);

        Registry.register(Registries.ITEM_GROUP, new Identifier("grapecraft", "testgroup"), ITEM_GROUP);


    }

}
