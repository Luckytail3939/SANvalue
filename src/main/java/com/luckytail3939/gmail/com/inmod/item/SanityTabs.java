package com.luckytail3939.gmail.com.inmod.item;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.potion.ModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class SanityTabs {
    //レジストリを作成
    public  static  final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SanityMod.MOD_ID);

    //レジストリにタブを登録←displayItemsの{}内で、アイテムを追加可能。
    public static  final RegistryObject<CreativeModeTab> SANITY_TAB  = TABS.register("sanity_tab",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("creativtabs.sanity_tab"))
                    .icon(SanityItems.RAW_SUNLIGHT_STONE.get()::getDefaultInstance)
                    .displayItems(((pParameters, pOutput) -> {

                        pOutput.accept(SanityItems.RAW_SUNLIGHT_STONE.get());
                        pOutput.accept(SanityItems.RAW_MOONLIGHT_SILVER .get());
                        pOutput.accept(SanityItems.MOONLIGHT_SILVER_INGOT .get());
                        pOutput.accept(SanityItems.MOON_SLIME_SPAWN_EGG.get());

                        pOutput.accept(SanityBlocks.MOONLIGHT_SILVER_BLOCK.get());
                        pOutput.accept(SanityBlocks.RAW_MOONLIGHT_SILVER_BLOCK.get());
                        pOutput.accept(SanityBlocks.MOONLIGHT_SILVER_ORE.get());
                        pOutput.accept(SanityBlocks.DEEPSLATE_MOONLIGHT_SILVER_ORE.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_LOG.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_WOOD.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_LEAVES.get());
                        pOutput.accept(SanityBlocks.STRIPPED_MOONLIT_TREE_LOG.get());
                        pOutput.accept(SanityBlocks.STRIPPED_MOONLIT_TREE_WOOD.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_PLANKS.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_SLAB.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_STAIRS.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_FENCE.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_FENCE_GATE.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_DOOR.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_TRAPDOOR.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_BUTTON.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_PRESSURE_PLATE.get());
                        pOutput.accept(SanityBlocks.MOONLIT_TREE_SAPLING.get());
                        pOutput.accept(new ItemStack(SanityBlocks.MOONLIGHT_FLOWER.get(), 1));

                        pOutput.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.MENTAL_MEDICINE_POTION.get()));//ポーション
                    }))
                    .build());

    //レジストリをイベントバスに登録←このメソッドをSanityModクラスから呼び出す。
    public  static  void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
