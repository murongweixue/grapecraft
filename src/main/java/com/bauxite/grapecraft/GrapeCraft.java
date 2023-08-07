package com.bauxite.grapecraft;

import com.bauxite.grapecraft.init.ModBlocks;
import com.bauxite.grapecraft.init.ModItems;
import net.fabricmc.api.ModInitializer;

public class GrapeCraft implements ModInitializer {
    @Override
    public void onInitialize() {
        ModBlocks.init();
        ModItems.init();
    }
}
