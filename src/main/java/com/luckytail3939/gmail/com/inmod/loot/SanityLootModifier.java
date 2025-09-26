package com.luckytail3939.gmail.com.inmod.loot;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SanityLootModifier {
    //シリアライザーとは、プログラミングでデータを変換する仕組みのことを指すことが多い。今回はjavaとjsonの変換、つまりCodecのこと。
    // レジストリを作成
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, SanityMod.MOD_ID);

    // レジストリにCodecを追加
    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item",AddItemModifier.CODEC);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> REPLACE_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("replace_item",ReplaceItemModifier.CODEC);

    //レジストリをイベントバスに登録
    //いまいち理解していません。未来の俺がなんとかしてくれるでしょう！
    public static void register(IEventBus eventBus) {
        LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
