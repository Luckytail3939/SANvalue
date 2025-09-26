package com.luckytail3939.gmail.com.inmod.block;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.custom.SanityLeavesBlock;
import com.luckytail3939.gmail.com.inmod.block.custom.SanityLogBlock;
import com.luckytail3939.gmail.com.inmod.block.custom.SanityStrippableLogBlock;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import com.luckytail3939.gmail.com.inmod.wordgen.tree.MoonlitTreeGrower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class SanityBlocks {
    //レジストリを作成
    public  static  final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SanityMod.MOD_ID);

    //レジストリにブロックを登録

    //本来はnew Blockの中にブロックの設定を記入するが、ダイヤモンドの設定をコピー。
    //月光銀ブロック
    public  static  final RegistryObject<Block> MOONLIGHT_SILVER_BLOCK = registerBlockItem("moonlight_silver_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)));
    //月光銀の原石ブロック
    public  static  final RegistryObject<Block> RAW_MOONLIGHT_SILVER_BLOCK = registerBlockItem("raw_moonlight_silver_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.METAL)));
    //月光石の鉱石
    //DropExperienceBlockは破壊時に経験値をドロップするブロック
    public  static  final RegistryObject<Block> MOONLIGHT_SILVER_ORE = registerBlockItem("moonlight_silver_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE), UniformInt.of(3,7)));

    //深層岩月光石の鉱石
    public  static  final RegistryObject<Block> DEEPSLATE_MOONLIGHT_SILVER_ORE = registerBlockItem("deepslate_moonlight_silver_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DIAMOND_ORE)));

    //樹皮を剥いだ月光樹の原木
    public  static  final RegistryObject<Block> STRIPPED_MOONLIT_TREE_LOG = registerBlockItem("stripped_moonlit_tree_log",
            () -> new SanityLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).sound(SoundType.BONE_BLOCK)));

    //樹皮を剥いだ月光樹
    public  static  final RegistryObject<Block> STRIPPED_MOONLIT_TREE_WOOD = registerBlockItem("stripped_moonlit_tree_wood",
            () -> new SanityLogBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).sound(SoundType.BONE_BLOCK)));

    //月光樹の原木
    public  static  final RegistryObject<Block> MOONLIT_TREE_LOG = registerBlockItem("moonlit_tree_log",
            () -> new SanityStrippableLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.BONE_BLOCK),
                    STRIPPED_MOONLIT_TREE_LOG));

    //月光樹
    public  static  final RegistryObject<Block> MOONLIT_TREE_WOOD = registerBlockItem("moonlit_tree_wood",
            () -> new SanityStrippableLogBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).sound(SoundType.BONE_BLOCK),
                    STRIPPED_MOONLIT_TREE_WOOD));

    //月光樹の葉ブロック
    public  static  final RegistryObject<Block> MOONLIT_TREE_LEAVES = registerBlockItem("moonlit_tree_leaves",
            () -> new SanityLeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //板材
    public  static  final RegistryObject<Block> MOONLIT_TREE_PLANKS = registerBlockItem("moonlit_tree_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK)));

    //ハーフブロック
    public  static  final RegistryObject<Block> MOONLIT_TREE_SLAB= registerBlockItem("moonlit_tree_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK)));

    //階段
    public  static  final RegistryObject<Block> MOONLIT_TREE_STAIRS= registerBlockItem("moonlit_tree_stairs",
            () -> new StairBlock(() -> SanityBlocks.MOONLIT_TREE_PLANKS.get().defaultBlockState(),BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK)));

    //フェンス
    public  static  final RegistryObject<Block> MOONLIT_TREE_FENCE= registerBlockItem("moonlit_tree_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK)));

    //フェンスゲート
    public  static  final RegistryObject<Block> MOONLIT_TREE_FENCE_GATE= registerBlockItem("moonlit_tree_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK), SoundEvents.BONE_BLOCK_PLACE,SoundEvents.BONE_BLOCK_PLACE));

    //ドア
    public  static  final RegistryObject<Block> MOONLIT_TREE_DOOR= registerBlockItem("moonlit_tree_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK), BlockSetType.OAK));/*BlockSetTypeで開閉時の音や、素手で開閉出来るかが決まる。*/

    //トラップドア
    public  static  final RegistryObject<Block> MOONLIT_TREE_TRAPDOOR= registerBlockItem("moonlit_tree_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().sound(SoundType.BONE_BLOCK),BlockSetType.OAK));/*noOcclusionこれを付けると、テクスチャの透過部分がおかしくならない。*/

    //ボタン
    public  static  final RegistryObject<Block> MOONLIT_TREE_BUTTON= registerBlockItem("moonlit_tree_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK),BlockSetType.OAK,100,true));/*数値：ボタンの戻るまでの値、矢で反応するかどうか。*/

    //感圧板
    public  static  final RegistryObject<Block> MOONLIT_TREE_PRESSURE_PLATE= registerBlockItem("moonlit_tree_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).sound(SoundType.BONE_BLOCK),BlockSetType.OAK));/*SensitivityをMOBSにするとモブだけに反応して、EVERYTHINGにすると、アイテムにも反応するようになる。*/

    //苗木
    public  static  final RegistryObject<Block> MOONLIT_TREE_SAPLING = registerBlockItem("moonlit_tree_sapling",
            () -> new SaplingBlock(new MoonlitTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    // 花（ポーション素材）
    public static final RegistryObject<Block> MOONLIGHT_FLOWER =
            BLOCKS.register("moonlight_flower",
                    () -> new FlowerBlock(() -> MobEffects.LUCK, 0,//←効果無し
                            BlockBehaviour.Properties.copy(Blocks.DANDELION)));

    //<T extends Block>は、今後高度なブロックを追加した時に使いまわせるようにするため。 「ブロッククラスの子クラスならなんでも受け付けます」という意味。
    public  static <T extends Block> RegistryObject<T> registerBlockItem(String name, Supplier<T> supplier) {
        //レジストリにブロックを追加
        RegistryObject<T> block = BLOCKS.register(name, supplier);
        //ブロックアイテムをアイテムレジストリに追加
        SanityItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    //レジストリをイベントバスに登録←このメソッドをSanityModクラスから呼び出す。
    public  static  void  register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
