package com.luckytail3939.gmail.com.inmod.player.sanity;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;

public class SanityCapabilityRegistry {
    public static final Capability<ISanity> SANITY = CapabilityManager.get(new CapabilityToken<>() {});
}
