package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.tag.SanityTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SanityItemTagsProvider extends ItemTagsProvider {
    public SanityItemTagsProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, SanityMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        //ブロックタグの内容をアイテムタグにコピー
        this.copy(SanityTags.Blocks.MOONLIT_TREE_LOG,SanityTags.Items.MOONLIT_TREE_LOG);

        this.copy(BlockTags.LOGS, ItemTags.LOGS);

        this.copy(BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN);

        this.copy(BlockTags.LEAVES, ItemTags.LEAVES);

        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);/*板材タグを付けると、作業台などが作成可能になる*/

        this.copy(BlockTags.FLOWERS, ItemTags.FLOWERS);
        this.copy(BlockTags.SMALL_FLOWERS, ItemTags.SMALL_FLOWERS);


    }
}
