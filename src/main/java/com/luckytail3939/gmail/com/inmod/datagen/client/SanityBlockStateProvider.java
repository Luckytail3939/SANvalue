package com.luckytail3939.gmail.com.inmod.datagen.client;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SanityBlockStateProvider extends BlockStateProvider {
    public SanityBlockStateProvider(PackOutput output,  ExistingFileHelper exFileHelper) {
        super(output, SanityMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        simpleBlockWithItem(SanityBlocks.MOONLIGHT_SILVER_BLOCK);
        simpleBlockWithItem(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK);
        simpleBlockWithItem(SanityBlocks.MOONLIGHT_SILVER_ORE);
        simpleBlockWithItem(SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE);

        logBlock((RotatedPillarBlock) SanityBlocks.MOONLIT_TREE_LOG.get());
        logBlock((RotatedPillarBlock) SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get());
        axisBlock((RotatedPillarBlock) SanityBlocks.MOONLIT_TREE_WOOD.get(),blockTexture(SanityBlocks.MOONLIT_TREE_LOG.get()),blockTexture(SanityBlocks.MOONLIT_TREE_LOG.get()));/*axisBlockメソッドは置く向きで見た目が変わるブロック用のモデルを生成する。*/
        axisBlock((RotatedPillarBlock) SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get(),blockTexture(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get()),blockTexture(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get()));
        item(SanityBlocks.MOONLIT_TREE_LOG);/*上ではモデルの見た目しか指定出来ないので、アイテムの見た目を設定している*/
        item(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG);
        item(SanityBlocks.MOONLIT_TREE_WOOD);
        item(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD);
        simpleLeaves(SanityBlocks.MOONLIT_TREE_LEAVES);

        simpleBlockWithItem(SanityBlocks.MOONLIT_TREE_PLANKS);
        slabBlock((SlabBlock) SanityBlocks.MOONLIT_TREE_SLAB.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));/*二つ重ねた時のテクスチャと、単体の時のテクスチャの二種類を割り当てる必要がある。*/
        stairsBlock((StairBlock) SanityBlocks.MOONLIT_TREE_STAIRS.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));
        fenceBlock((FenceBlock) SanityBlocks.MOONLIT_TREE_FENCE.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));
        fenceGateBlock((FenceGateBlock) SanityBlocks.MOONLIT_TREE_FENCE_GATE.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));
        doorBlockWithRenderType((DoorBlock) SanityBlocks.MOONLIT_TREE_DOOR.get(),modLoc("block/moonlit_tree_door_bottom"),modLoc("block/moonlit_tree_door_top"),"cutout");/*ドアの上下でテクスチャを指定する必要がある。三つ目ではブロックの描画の種類を指定している。cutoutは透過部分があるブロックに指定する。*/
        trapdoorBlockWithRenderType((TrapDoorBlock) SanityBlocks.MOONLIT_TREE_TRAPDOOR.get(),modLoc("block/moonlit_tree_trapdoor"),true,"cutout");/*trueでは向きによって見た目が変わるかの設定*/
        buttonBlock((ButtonBlock) SanityBlocks.MOONLIT_TREE_BUTTON.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));
        pressurePlateBlock((PressurePlateBlock) SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE.get(),blockTexture(SanityBlocks.MOONLIT_TREE_PLANKS.get()));

        flower(SanityBlocks.MOONLIGHT_FLOWER);
        sapling(SanityBlocks.MOONLIT_TREE_SAPLING);
    }
    private void simpleBlockWithItem(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    // ブロック用のアイテムモデルを作成
    private void item(RegistryObject<Block> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(
                SanityMod.MOD_ID + ":block/" +
                        ForgeRegistries.BLOCKS.getKey(block.get()).getPath()
        ));
    }

    // 呪われた葉ブロック→今回は使用していない。これは側面と上下で違うテクスチャを使用する時に使う。名前が"呪われた"になっているのは、参考の動画をもとにしているから。
    private void cursedLeaves(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().cubeTop(
                ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                TextureMapping.getBlockTexture(block.get(), "_side"),
                TextureMapping.getBlockTexture(block.get(), "_top")
        ));
    }

    // 普通の葉ブロック
    private void simpleLeaves(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), models().singleTexture(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                new ResourceLocation("minecraft:block/leaves"),
                "all", blockTexture(block.get())).renderType("cutout"));
    }

    //花
    private void flower(RegistryObject<Block> block) {
        simpleBlock(block.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                        blockTexture(block.get())).renderType("cutout"));
    }


    //苗木ブロック
    private void sapling(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),/*crossを設定することで、十字に表示される。*/
                        blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}
