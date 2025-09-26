package com.luckytail3939.gmail.com.inmod.event;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.entity.custom.MoonSlime;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)//特性の追加
public class SanityEventModBusEvent {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event){/*EntityAttributeCreationEventイベントが走った時にムーンスライムの特性を読み込ませる*/
        event.put(SanityEntities.MOON_SLIME.get(), MoonSlime.createAttributes().build());
    }
}
