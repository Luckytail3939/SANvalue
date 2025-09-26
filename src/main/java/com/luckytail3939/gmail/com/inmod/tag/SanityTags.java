package com.luckytail3939.gmail.com.inmod.tag;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class SanityTags {
    public static class Blocks {

        //タグキー作成
        public static final TagKey<Block> MOONLIT_TREE_LOG = tag("moonlit_tree_log");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(SanityMod.MOD_ID, name));
        }
    }

    public static class Items {

        //タグキー作成
        public static final TagKey<Item> MOONLIT_TREE_LOG = tag("moonlit_tree_log");

        private static TagKey<Item> tag(String name){
            return ItemTags.create(new ResourceLocation(SanityMod.MOD_ID, name));
        }
    }
}
