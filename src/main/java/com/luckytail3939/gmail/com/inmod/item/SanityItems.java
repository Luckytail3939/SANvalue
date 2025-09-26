package com.luckytail3939.gmail.com.inmod.item;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.awt.*;

public class SanityItems {
        //レジストリ作成
        public  static  final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SanityMod.MOD_ID);

        //レジストリにアイテムを追加
        public  static  final RegistryObject<Item> RAW_SUNLIGHT_STONE = ITEMS.register("raw_sunlight_stone", () -> new Item(new Item.Properties()));
        public  static  final RegistryObject<Item> RAW_MOONLIGHT_SILVER = ITEMS.register("raw_moonlight_silver", () -> new Item(new Item.Properties()));
        public  static  final RegistryObject<Item> MOONLIGHT_SILVER_INGOT = ITEMS.register("moonlight_silver_ingot", () -> new Item(new Item.Properties()));
        public  static  final RegistryObject<Item> MOON_SLIME_SPAWN_EGG = ITEMS.register("moon_slime_spawn_egg", () -> new ForgeSpawnEggItem(SanityEntities.MOON_SLIME, Color.GREEN.getRGB(),Color.lightGray.getRGB(),new Item.Properties()));
        public static final RegistryObject<Item> MOONLIGHT_FLOWER= ITEMS.register("moonlight_flower", () -> new BlockItem(SanityBlocks.MOONLIGHT_FLOWER.get(), new Item.Properties()));



        public  static  void register(IEventBus eventBus) {
                //  レジストリをイベントバスに登録←このメソッドをSanityModクラスから呼び出す。
                ITEMS.register(eventBus);
        }
}
