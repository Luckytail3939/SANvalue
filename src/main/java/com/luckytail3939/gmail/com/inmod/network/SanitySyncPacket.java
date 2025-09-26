package com.luckytail3939.gmail.com.inmod.network;

import com.luckytail3939.gmail.com.inmod.event.SanityClientEvent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SanitySyncPacket {
    private final double sanity;

    public SanitySyncPacket(double sanity) {
        this.sanity = sanity;
    }

    public SanitySyncPacket(FriendlyByteBuf buf) {
        this.sanity = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeDouble(sanity);
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            // ★ クライアントでのみ実行
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () ->
                    SanityClientEvent.setSanity(sanity)
            );
        });
        ctx.get().setPacketHandled(true);
    }
}
