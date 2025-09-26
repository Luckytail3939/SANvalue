package com.luckytail3939.gmail.com.inmod.command;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.network.SanitySyncPacket;
import com.luckytail3939.gmail.com.inmod.player.sanity.SanityCapabilityRegistry;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;

public class SanityCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("sanity")
                .then(Commands.literal("add")
                        .then(Commands.argument("amount", IntegerArgumentType.integer())
                                .executes(context ->{
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    int amount = IntegerArgumentType.getInteger(context,"amount");
                                    player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity ->{
                                        sanity.addSanity(amount);
                                        SanityMod.LOGGER.info("Added {} SAN to player {} (now: {})",
                                                amount,player.getName().getString(),sanity.getSanity());
                                        // ★ サーバーで更新した値をクライアントに同期
                                        SanityMod.CHANNEL.send(
                                                net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> player),
                                                new SanitySyncPacket(sanity.getSanity())
                                        );
                                    });
                                    return 1;
                                })
                        )
                )
                .then(Commands.literal("reduce")
                        .then(Commands.argument("amount",IntegerArgumentType.integer())
                                .executes(context ->{
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    int amount = IntegerArgumentType.getInteger(context,"amount");
                                    player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity -> {
                                        sanity.reduceSanity(amount);
                                        SanityMod.LOGGER.info("Reduced {} SAN from player {} (now: {})",
                                                amount,player.getName().getString(),sanity.getSanity());
                                        // ★ サーバーで更新した値をクライアントに同期
                                        SanityMod.CHANNEL.send(
                                                net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> player),
                                                new SanitySyncPacket(sanity.getSanity())
                                        );
                                    });
                                    return 1;
                                })
                        )
                )
                .then(Commands.literal("set")
                        .then(Commands.argument("value",IntegerArgumentType.integer(0,100))
                                .executes(context -> {
                                    ServerPlayer player = context.getSource().getPlayerOrException();
                                    int value = IntegerArgumentType.getInteger(context,"value");
                                    player.getCapability(SanityCapabilityRegistry.SANITY).ifPresent(sanity ->{
                                        sanity.setSanity(value);
                                        SanityMod.LOGGER.info("Set SAN of player {} to {}",player.getName().getString(),sanity.getSanity());
                                        // ★ サーバーで更新した値をクライアントに同期
                                        SanityMod.CHANNEL.send(
                                                net.minecraftforge.network.PacketDistributor.PLAYER.with(() -> player),
                                                new SanitySyncPacket(sanity.getSanity())
                                        );
                                    });
                                    return 1;
                                })
                        )
                )
        );
    }
}
