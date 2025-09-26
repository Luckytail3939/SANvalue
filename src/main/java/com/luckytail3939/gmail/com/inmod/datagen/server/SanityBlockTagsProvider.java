package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.tag.SanityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SanityBlockTagsProvider extends BlockTagsProvider {
    public SanityBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,@Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SanityMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //追加鉱石の適正ツール指定
        //ツルハシ
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(SanityBlocks.MOONLIGHT_SILVER_BLOCK.get(),
                        SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK.get(),
                        SanityBlocks.MOONLIGHT_SILVER_ORE.get(),
                        SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get()
                );

        //各鉱石の必要ピッケルレベル指定（ネザライトなど一部のレベルはタグが違う）
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(SanityBlocks.MOONLIGHT_SILVER_BLOCK.get()
                        );

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK.get(),
                        SanityBlocks.MOONLIGHT_SILVER_ORE.get(),
                        SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get()
                        );

        //木材関連
        this.tag(SanityTags.Blocks.MOONLIT_TREE_LOG)
                .add(SanityBlocks.MOONLIT_TREE_LOG.get())
                .add(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get())
                .add(SanityBlocks.MOONLIT_TREE_WOOD.get())
                .add(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get());
        //原木を使うレシピ（焚き火など）や、地面かどうかの判定処理に使われます。
        this.tag(BlockTags.LOGS)
                .add(SanityBlocks.MOONLIT_TREE_LOG.get())
                .add(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get());
        //木炭が作れるようになる。
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(SanityBlocks.MOONLIT_TREE_LOG.get())
                .add(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get())
                .add(SanityBlocks.MOONLIT_TREE_WOOD.get())
                .add(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get());
        //地面かどうかや、ハサミで回収可能になる。
        this.tag(BlockTags.LEAVES)
                .add(SanityBlocks.MOONLIT_TREE_LEAVES.get());

        //フェンスなどは設定しないとくっつかなくなる。
        this.tag(BlockTags.PLANKS).add(SanityBlocks.MOONLIT_TREE_PLANKS.get());
        this.tag(BlockTags.SLABS).add(SanityBlocks.MOONLIT_TREE_SLAB.get());
        this.tag(BlockTags.STAIRS).add(SanityBlocks.MOONLIT_TREE_STAIRS.get());
        this.tag(BlockTags.FENCES).add(SanityBlocks.MOONLIT_TREE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(SanityBlocks.MOONLIT_TREE_FENCE_GATE.get());
        this.tag(BlockTags.DOORS).add(SanityBlocks.MOONLIT_TREE_DOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(SanityBlocks.MOONLIT_TREE_TRAPDOOR.get());
        this.tag(BlockTags.BUTTONS).add(SanityBlocks.MOONLIT_TREE_BUTTON.get());
        this.tag(BlockTags.PRESSURE_PLATES).add(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE.get());

        this.tag(BlockTags.FLOWERS).add(SanityBlocks.MOONLIGHT_FLOWER.get());
        this.tag(BlockTags.SMALL_FLOWERS).add(SanityBlocks.MOONLIGHT_FLOWER.get());

    }
}
