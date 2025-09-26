package com.luckytail3939.gmail.com.inmod;

import com.luckytail3939.gmail.com.inmod.block.SanityBlocks;
import com.luckytail3939.gmail.com.inmod.command.SanityCommand;
import com.luckytail3939.gmail.com.inmod.config.SanityClientConfig;
import com.luckytail3939.gmail.com.inmod.config.SanityCommonConfig;
import com.luckytail3939.gmail.com.inmod.effect.ModEffects;
import com.luckytail3939.gmail.com.inmod.potion.ModPotions;
import com.luckytail3939.gmail.com.inmod.entity.SanityEntities;
import com.luckytail3939.gmail.com.inmod.item.SanityItems;
import com.luckytail3939.gmail.com.inmod.item.SanityTabs;
import com.luckytail3939.gmail.com.inmod.loot.SanityLootModifier;
import com.luckytail3939.gmail.com.inmod.network.SanitySyncPacket;
import com.luckytail3939.gmail.com.inmod.wordgen.biome.SanityOverworldRegion;
import com.luckytail3939.gmail.com.inmod.wordgen.biome.SanitySurfaceRuleData;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(SanityMod.MOD_ID)
public class SanityMod {
    public static final String MOD_ID = "sanitymod";

    public static final Logger LOGGER = LogUtils.getLogger();

    private static final  String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID,"main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public SanityMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SanityCommonConfig.SPEC);

        modEventBus.addListener(this::commonSetup);

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, SanityClientConfig.SPEC);

        // パケット登録
        int id = 0;
        CHANNEL.registerMessage(id++, SanitySyncPacket.class,
                SanitySyncPacket::toBytes,
                SanitySyncPacket::new,
                SanitySyncPacket::handle);

        //アイテムレジストリをイベントバスに登録
        SanityItems.register(modEventBus);

        //クリエイティブタブレジストリをイベントバスに登録
        SanityTabs.register(modEventBus);

        //ブロックレジストリをイベントバスに登録
        SanityBlocks.register(modEventBus);

        //GlobalLootModifierレジストリをイベントバスに登録
        SanityLootModifier.register(modEventBus);

        //エンティティレジストリをイベントバスに登録
        SanityEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);

        ModEffects.EFFECTS.register(modEventBus);
        ModPotions.POTIONS.register(modEventBus);
    }

    // SanityMod.java

    public static void sync(ServerPlayer player, double sanity) {
        CHANNEL.send(
                PacketDistributor.PLAYER.with(() -> player),
                new SanitySyncPacket(sanity)
        );
    }


    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(()->{
            Regions.register(new SanityOverworldRegion(
                    new ResourceLocation(SanityMod.MOD_ID,"overworld"),5));
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD,
                    MOD_ID, SanitySurfaceRuleData.makeRules());
        });
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(SanityItems.RAW_SUNLIGHT_STONE);
            event.accept(SanityItems.RAW_MOONLIGHT_SILVER );
            event.accept(SanityItems.MOONLIGHT_SILVER_INGOT );
        }
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        SanityCommand.register(event.getServer().getCommands().getDispatcher());
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}

//ポーションの材料を変更、月光銀は他のクラフトに使用。独自バイオームに生成される植物からポーション素材を作成可能に変更。←完了
//GeckoLibを使用した独自アニメーション持ちのゴーレムを追加。これのドロップアイテムとして太陽石を追加。
//太陽石を用いたアイテムにより、SAN値減少量低下効果を得ることが出来るようにする。（ランタンなど）
//SAN値減少量が増加する代わりに攻撃力が上がる狂化エフェクトの作成
//SAN値を減少させる発狂エフェクトの作成。
//猫の近くにいるとSAN値回復。
