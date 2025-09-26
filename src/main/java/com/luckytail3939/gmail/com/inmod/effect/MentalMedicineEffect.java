package com.luckytail3939.gmail.com.inmod.effect;

import com.luckytail3939.gmail.com.inmod.player.sanity.SanityCapabilityRegistry;
import com.luckytail3939.gmail.com.inmod.SanityMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.server.level.ServerPlayer;

public class MentalMedicineEffect extends MobEffect {

    public MentalMedicineEffect() {
        // Beneficial = バフ系のエフェクト
        super(MobEffectCategory.BENEFICIAL, 0x55FFFF); // 色は水色っぽい
    }

    // 効果が発動する間隔（1 = 毎tick, 20 = 1秒ごと）
    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0; // 1秒ごとに実行
    }

    // 実際の効果
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayer player) {
            player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                double value = sanity.getSanity();
                int recovery = 1 + amplifier; // レベルに応じて回復量を増やす

                double newValue = Math.min(100, value + recovery);
                sanity.setSanity(newValue);

                // ★ クライアント同期
                SanityMod.sync(player, newValue);
            });
        }
    }
}
