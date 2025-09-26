package com.luckytail3939.gmail.com.inmod.datagen.client;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SanityItemModelProvider extends ItemModelProvider {
    public SanityItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SanityMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(SanityItems.RAW_MOONLIGHT_SILVER.get());
        basicItem(SanityItems.RAW_SUNLIGHT_STONE .get());
        basicItem(SanityItems.MOONLIGHT_SILVER_INGOT  .get());

        itemWithBlock(SanityBlocks.MOONLIT_TREE_SLAB);
        itemWithBlock(SanityBlocks.MOONLIT_TREE_STAIRS);
        itemWithBlock(SanityBlocks.MOONLIT_TREE_FENCE_GATE);
        itemWithBlock(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE);

        basicItem(SanityBlocks.MOONLIT_TREE_DOOR.get().asItem());
        trapdoor(SanityBlocks.MOONLIT_TREE_TRAPDOOR);
        fence(SanityBlocks.MOONLIT_TREE_FENCE,SanityBlocks.MOONLIT_TREE_PLANKS);
        button(SanityBlocks.MOONLIT_TREE_BUTTON,SanityBlocks.MOONLIT_TREE_PLANKS);

        sapling(SanityBlocks.MOONLIT_TREE_SAPLING);

        flower(SanityBlocks.MOONLIGHT_FLOWER);

        withExistingParent(SanityItems.MOON_SLIME_SPAWN_EGG.getId().getPath(),mcLoc("item/template_spawn_egg"));
    }

    public void itemWithBlock(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        SanityMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }
    public void trapdoor(RegistryObject<Block> block) {
        this.getBuilder(ForgeRegistries.BLOCKS.getKey(block.get()).getPath())
                .parent(new ModelFile.UncheckedModelFile(
                        SanityMod.MOD_ID + ":block/" +
                                ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }
    public void fence(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(SanityMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }
    public void button(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(SanityMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private void sapling(RegistryObject<Block> block) {
        this.withExistingParent(block.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SanityMod.MOD_ID,"block/" + block.getId().getPath()));
    }

    private void flower(RegistryObject<Block> block) {
        this.withExistingParent(block.getId().getPath(),
                        new ResourceLocation("item/generated"))
                .texture("layer0",
                        new ResourceLocation(SanityMod.MOD_ID, "block/" + block.getId().getPath()));
    }
}
