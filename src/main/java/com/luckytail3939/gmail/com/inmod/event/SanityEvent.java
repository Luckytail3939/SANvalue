package com.luckytail3939.gmail.com.inmod.event;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.config.SanityCommonConfig;
import com.luckytail3939.gmail.com.inmod.network.SanitySyncPacket;
import com.luckytail3939.gmail.com.inmod.player.sanity.SanityCapabilityRegistry;
import com.luckytail3939.gmail.com.inmod.player.sanity.SanityProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID)
public class SanityEvent {
    public static final ResourceLocation SANITY_CAP = new ResourceLocation(SanityMod.MOD_ID,"sanity");

    // Capability をプレイヤーにアタッチ
    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event){
        // Player に SanityProvider をくっつける
        if (event.getObject() instanceof Player player) {
            // ⚠ プレイヤー名はまだ初期化されていないことがあるので取得しない
            SanityMod.LOGGER.info("Attaching Sanity capability to player entity (UUID: {})", player.getUUID());
            event.addCapability(SANITY_CAP, new SanityProvider());
        }
    }

    //SAN値のNBTデータを保存/復元する
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        Player oldPlayer = event.getOriginal();
        Player newPlayer = event.getEntity();

        if (event.isWasDeath()) {
            // 死亡時のみリセット
            newPlayer.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(newSanity -> {
                newSanity.setSanity(85); // 初期値85
                SanityMod.LOGGER.info("Player {} died. SAN reset to {}", newPlayer.getName().getString(), newSanity.getSanity());
            });
        } else {
            // ディメンション移動やログイン時はコピー
            oldPlayer.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(oldSanity -> {
                newPlayer.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(newSanity -> {
                    newSanity.setSanity(oldSanity.getSanity());
                    SanityMod.LOGGER.info("Copied SAN value {} -> {}", oldSanity.getSanity(), newSanity.getSanity());
                });
            });
        }
    }

    // ディメンション移動完了後に同期
    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                SanityMod.LOGGER.info("Player {} changed dimension. SAN is {}", serverPlayer.getName().getString(), sanity.getSanity());

                // ★ クライアントに同期
                SanityMod.CHANNEL.send(
                        net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> serverPlayer),
                        new SanitySyncPacket(sanity.getSanity())
                );
            });
        }
    }

    //死亡後のリスポーン時に 必ず85へリセット
    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                sanity.setSanity(85); // 初期値にリセット
                SanityMod.LOGGER.info("Player {} respawned. SAN reset to {}", player.getName().getString(), sanity.getSanity());

                // ★ クライアントに即時同期
                SanityMod.CHANNEL.send(
                        net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> player),
                        new SanitySyncPacket(sanity.getSanity())
                );
            });
        }
    }

    //SAN が 0 のときだけ死亡イベント (LivingDeathEvent) が発火
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (!(event.player instanceof ServerPlayer player)) return;
        if (event.player.level().isClientSide) return; // ★ サーバー側のみ
        if (event.phase != TickEvent.Phase.END) return;

        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            double value = sanity.getSanity();


            // --- デバフ付与（サーバー側制御） ---
            if (value >= 60) {
                // 正常：何もなし
            } else if (value >= 50) {
                // 軽度：低確率で移動低下・採掘低下
                if (event.player.level().random.nextFloat() < 0.001f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    event.player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                }
            } else if (value >= 40) {
                // 中度：やや高確率で移動低下・採掘低下
                if (event.player.level().random.nextFloat() < 0.005f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    event.player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                }
            } else if (value >= 30) {
                // 重度：確率で空腹付与・移動低下・採掘低下
                if (event.player.level().random.nextFloat() < 0.003f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                }
                if (event.player.level().random.nextFloat() < 0.005f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    event.player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                }
            } else if (value >= 20) {
                // 狂気：確率で吐き気・空腹付与・移動低下・採掘低下
                if (event.player.level().random.nextFloat() < 0.003f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                }
                if (event.player.level().random.nextFloat() < 0.005f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                }
                if (event.player.level().random.nextFloat() < 0.006f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    event.player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                }
            } else if (value >= 1) {
                // 狂気：盲目（常時）、まれに吐き気・空腹付与・移動低下・採掘低下
                event.player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 0, false, false));
                if (event.player.level().random.nextFloat() < 0.008f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                }
                if (event.player.level().random.nextFloat() < 0.006f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                }
                if (event.player.level().random.nextFloat() < 0.008f) {
                    event.player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 0));
                    event.player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 100, 0));
                }
            } else if (value == 0) {
                // SAN値ゼロ：即死
                event.player.hurt(event.player.damageSources().magic(), Float.MAX_VALUE);
            }
        });

        // --- 1秒ごとの SAN減少チェック ---
        tickCounter++;
        if (tickCounter < 20) return; // 20tick = 1秒
        tickCounter = 0;

        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            double value = sanity.getSanity();
            int lightLevel = player.level().getMaxLocalRawBrightness(player.blockPosition());

            boolean isCave = (
                    !player.level().canSeeSky(player.blockPosition())
                            && player.blockPosition().getY() < SanityCommonConfig.CAVE_Y_LEVEL.get()
                            && lightLevel <= 7
            );
            boolean isDarkness = (lightLevel <= 7);

            if (isCave) {
                value -= SanityCommonConfig.CAVE_DECREASE.get();
            } else if (isDarkness) {
                value -= SanityCommonConfig.DARKNESS_DECREASE.get();
            }
            if (!player.level().getEntitiesOfClass(Monster.class, player.getBoundingBox().inflate(8)).isEmpty()) {
                value -= SanityCommonConfig.MOB_NEARBY_DECREASE.get();
            }
            if (player.getStats().getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)) > 72000) { // 3日
                value -= SanityCommonConfig.SLEEPLESS_DECREASE.get();
            }
            if (player.getHealth() / player.getMaxHealth() <= 0.25f) {
                value -= SanityCommonConfig.LOW_HEALTH_DECREASE.get();
            }

            value = Math.max(0, Math.min(100, value));
            sanity.setSanity(value);
            sync(player, value);
        });

        SanityMod.LOGGER.info("Config values: cave={} darkness={} mob={} sleepless={} lowHealth={}",
                SanityCommonConfig.CAVE_DECREASE.get(),
                SanityCommonConfig.DARKNESS_DECREASE.get(),
                SanityCommonConfig.MOB_NEARBY_DECREASE.get(),
                SanityCommonConfig.SLEEPLESS_DECREASE.get(),
                SanityCommonConfig.LOW_HEALTH_DECREASE.get());

    }



    //バニラの死亡メッセージを消して差し替え
    @SubscribeEvent
    public static void onPlayerDeath(net.minecraftforge.event.entity.living.LivingDeathEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            if (sanity.getSanity() <= 0) {
                if (player.server != null) {
                    player.server.getPlayerList().broadcastSystemMessage(
                            net.minecraft.network.chat.Component.literal(
                                    player.getName().getString() + " は正気を失って死んだ"
                            ),
                            false
                    );
                }
                // ★ バニラの死亡メッセージを抑制
                event.setCanceled(true);
            }
        });
    }

    // ログイン時にサーバー → クライアントへ初期同期
    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            serverPlayer.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                SanityMod.LOGGER.info("Player {} logged in. SAN is {}", serverPlayer.getName().getString(), sanity.getSanity());

                // ★ クライアントに同期
                SanityMod.CHANNEL.send(
                        net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> serverPlayer),
                        new SanitySyncPacket(sanity.getSanity())
                );
            });
        }
    }

    // 1秒ごとに処理するためのタイマー
    private static int tickCounter = 0;

    // 🌿 回復イベント ----------------------

    // ベッドで睡眠
    @SubscribeEvent
    public static void onSleep(PlayerSleepInBedEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            double value = Math.min(100, sanity.getSanity() + SanityCommonConfig.SLEEP_RECOVERY.get());
            sanity.setSanity(value);
            sync(player, value);
        });
    }

    // 食事
    @SubscribeEvent
    public static void onEat(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if (!event.getItem().isEdible()) return; // ← 食べ物判定

        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            double recovery = SanityCommonConfig.FOOD_RECOVERY.get();
            double value = Math.min(100, sanity.getSanity() + recovery);
            sanity.setSanity(value);
            sync(player, value);
        });
    }


    // 経験値取得
    @SubscribeEvent
    public static void onXpPickup(PlayerXpEvent.PickupXp event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();
        player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
            double value = Math.min(100, sanity.getSanity() + SanityCommonConfig.XP_RECOVERY.get());
            sanity.setSanity(value);
            sync(player, value);
        });
    }

    // プレイヤーごとの焚き火クールタイム管理
    private static final Map<UUID, Long> campfireCooldown = new HashMap<>();

    // プレイヤーがしゃがんでいて、近くに焚き火があるとき SAN 回復
    @SubscribeEvent
    public static void onPlayerNearCampfire(TickEvent.PlayerTickEvent event) {
        if (!(event.player instanceof ServerPlayer player)) return;
        if (event.phase != TickEvent.Phase.END) return;

        if (player.isCrouching()) {
            boolean nearCampfire = player.level().getBlockStates(player.getBoundingBox().inflate(3))
                    .anyMatch(state -> state.is(Blocks.CAMPFIRE));

            if (nearCampfire) {
                long now = player.level().getGameTime();
                long last = campfireCooldown.getOrDefault(player.getUUID(), 0L);

                if (now - last >= 200) { // 200tick = 10秒
                    campfireCooldown.put(player.getUUID(), now);

                    player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                        double value = Math.min(100, sanity.getSanity() + SanityCommonConfig.CAMPFIRE_RECOVERY.get());
                        sanity.setSanity(value);
                        sync(player, value);
                    });
                }
            }
        }
    }

    // 共通：同期メソッド
    private static void sync(ServerPlayer player,double value) {
        SanityMod.CHANNEL.send(
                PacketDistributor.PLAYER.with(() -> player),
                new SanitySyncPacket(value)
        );
    }
}

//onAttachCapabilities
//ゲーム内でプレイヤーには 常に SanityCapability がアタッチされる。
//player.getCapability(SanityCapabilityRegistry.SANITY) を使えば SAN 値を取得・変更できる。

//onPlayerLogin
//クライアントへ初期同期