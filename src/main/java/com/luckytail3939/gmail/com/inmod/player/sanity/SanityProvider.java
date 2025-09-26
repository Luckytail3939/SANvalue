package com.luckytail3939.gmail.com.inmod.player.sanity;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SanityProvider implements ICapabilityProvider, ICapabilitySerializable<CompoundTag> {
    private final SanityCapability backend = new SanityCapability();
    private final LazyOptional<ISanity> optional = LazyOptional.of(()->backend);

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        return capability == SanityCapabilityRegistry.SANITY ? optional.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putDouble("Sanity",backend.getSanity());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        backend.setSanity(nbt.getDouble("Sanity"));
    }
}
