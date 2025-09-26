package com.luckytail3939.gmail.com.inmod.datagen.server.loot;

import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.SlimePredicate;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.stream.Stream;

public class CustomEntityLootProvider extends EntityLootSubProvider {
    protected CustomEntityLootProvider() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        this.add(SanityEntities.MOON_SLIME.get(), LootTable.lootTable().withPool(
                // ムーンライムにアイテムドロップを追加
                LootPool.lootPool().setRolls(ConstantValue.exactly(1.0f))
                        //アメジストをドロップ
                        .add(LootItem.lootTableItem(Items.AMETHYST_SHARD)
                            // １～３個ドロップ
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0f,3.0f)))
                            // ドロップ増加エンチャント付きの場合、レベルごとにドロップが１増える
                            .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0f,1.0f)))
                        )
                        // ムーンライムの大きさが最小の場合のみ、アイテムをドロップする
                        .when(LootItemEntityPropertyCondition.hasProperties(
                                LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.entity().subPredicate(SlimePredicate.sized(MinMaxBounds.Ints.exactly(1)))
                        ))
        ));
    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return SanityEntities.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
    }
}
