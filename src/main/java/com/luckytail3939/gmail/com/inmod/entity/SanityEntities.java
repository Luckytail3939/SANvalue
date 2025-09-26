package com.luckytail3939.gmail.com.inmod.entity;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.entity.custom.MoonSlime;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SanityEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, SanityMod.MOD_ID);

    public static final RegistryObject<EntityType<MoonSlime>> MOON_SLIME = ENTITY_TYPES.register("moon_slime",()->EntityType.Builder.of(MoonSlime::new, MobCategory.MONSTER).build(SanityMod.MOD_ID + ":moon_slime"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
