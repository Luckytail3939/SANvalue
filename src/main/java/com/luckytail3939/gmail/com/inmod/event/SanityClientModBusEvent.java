package com.luckytail3939.gmail.com.inmod.event;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.entity.model.MoonSlimeModel;
import com.luckytail3939.gmail.com.inmod.entity.renderer.MoonSlimeRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT)
public class SanityClientModBusEvent {

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event){/*RegisterLayerDefinitionsイベントが走った時にムーンスライムのレイヤーを読み込ませる*/
        event.registerLayerDefinition(MoonSlimeModel.LAYER_LOCATION,MoonSlimeModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRender(EntityRenderersEvent.RegisterRenderers event){/*RegisterRenderersイベントが走った時にムーンスライムのレンダラーを読み込ませる*/
        event.registerEntityRenderer(SanityEntities.MOON_SLIME.get(), MoonSlimeRenderer::new);
    }
}
