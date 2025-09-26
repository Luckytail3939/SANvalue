package com.luckytail3939.gmail.com.inmod.effect;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, SanityMod.MOD_ID);

    // 精神薬エフェクトを登録
    public static final RegistryObject<MobEffect> MENTAL_MEDICINE =
            EFFECTS.register("mental_medicine", MentalMedicineEffect::new);
}
