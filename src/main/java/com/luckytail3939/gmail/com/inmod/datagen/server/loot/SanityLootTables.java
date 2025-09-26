package com.luckytail3939.gmail.com.inmod.datagen.server.loot;

import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;


//全てのルートテーブルの総括
public class SanityLootTables {
    public  static LootTableProvider create(PackOutput output) {
        return  new LootTableProvider(output, Set.of(), List.of(
                new  LootTableProvider.SubProviderEntry(
                        SanityBlockLootTables::new, LootContextParamSets.BLOCK),

                new  LootTableProvider.SubProviderEntry(
                         CustomEntityLootProvider::new, LootContextParamSets.ENTITY)
        ));
    }
}
