package com.luckytail3939.gmail.com.inmod.datagen.server.loot;

import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class SanityBlockLootTables extends BlockLootSubProvider {
    protected SanityBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(SanityBlocks.MOONLIGHT_SILVER_BLOCK .get());
        this.dropSelf(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK .get());
        //鉱石ブロックのドロップ設定
        this.add(SanityBlocks.MOONLIGHT_SILVER_ORE.get(),block -> this.createOreDrop(block, SanityItems.RAW_MOONLIGHT_SILVER.get()));
        this.add(SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get(),block -> this.createOreDrop(block, SanityItems.RAW_MOONLIGHT_SILVER.get()));
        //木関連ブロック
        this.dropSelf(SanityBlocks.MOONLIT_TREE_LOG.get());
        this.dropSelf(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_WOOD.get());
        this.dropSelf(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get());
        this.add(SanityBlocks.MOONLIT_TREE_LEAVES.get(),block -> createLeavesDrops(block,SanityBlocks.MOONLIT_TREE_SAPLING.get(),NORMAL_LEAVES_SAPLING_CHANCES));/*現在苗木が無いため、仮で月光銀ブロックをドロップアイテムに設定→2025_08_27苗木に修正済み*/

        this.dropSelf(SanityBlocks.MOONLIT_TREE_PLANKS.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_STAIRS.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_FENCE.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_FENCE_GATE.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_TRAPDOOR.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_BUTTON.get());
        this.dropSelf(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE.get());
        this.add(SanityBlocks.MOONLIT_TREE_SLAB.get(),createSlabItemTable(SanityBlocks.MOONLIT_TREE_SLAB.get()));/*ハーフブロックは二つ重なっていると、壊したときに一つしかアイテムがドロップしないので、createSlabItemTableで指定してやると正常になる。*/
        this.add(SanityBlocks.MOONLIT_TREE_DOOR.get(),createDoorTable(SanityBlocks.MOONLIT_TREE_DOOR.get()));/*ドアはハーフブロックとは逆で、二つドロップしてしまうのでcreateDoorTableを使用して解決*/

        this.dropSelf(SanityBlocks.MOONLIT_TREE_SAPLING.get());

        // --- 月光花（そのまま自分をドロップ） ---
        this.dropSelf(SanityBlocks.MOONLIGHT_FLOWER.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return SanityBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
