package com.luckytail3939.gmail.com.inmod.datagen.server;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import java.util.function.Consumer;

public class SanityAdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {
    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {

        // === ルート実績 ===
        Advancement root = Advancement.Builder.advancement()
                .display(
                        Items.BOOK, // アイコン
                        Component.translatable("advancement.inmod.sanity.root.title"),
                        Component.translatable("advancement.inmod.sanity.root.desc"),
                        new ResourceLocation(SanityMod.MOD_ID, "textures/gui/advancements/backgrounds/sanity.png"), // 独自背景
                        FrameType.TASK,
                        false, false, false // 最初は通知しない
                )
                .addCriterion("tick", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, new ResourceLocation(SanityMod.MOD_ID, "sanity/root"), fileHelper);

        // === SAN値が初めて減少したとき ===
        Advancement firstLoss = Advancement.Builder.advancement()
                .parent(root) // ルートの子要素にする
                .display(
                        Items.ENDER_EYE,
                        Component.translatable("advancement.inmod.sanity.first_loss.title"),
                        Component.translatable("advancement.inmod.sanity.first_loss.desc"),
                        null, // 親が背景を持っているので null
                        FrameType.TASK,
                        true, true, false
                )
                .addCriterion("lost_sanity", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, new ResourceLocation(SanityMod.MOD_ID, "sanity/first_loss"), fileHelper);

        // === SAN値が0になって死亡したとき ===
        Advancement insanityDeath = Advancement.Builder.advancement()
                .parent(firstLoss) // first_loss の子要素にする
                .display(
                        Items.WITHER_SKELETON_SKULL,
                        Component.translatable("advancement.inmod.sanity.insanity_death.title"),
                        Component.translatable("advancement.inmod.sanity.insanity_death.desc"),
                        null,
                        FrameType.CHALLENGE, // 紫枠
                        true, true, true
                )
                .addCriterion("insanity_death", new ImpossibleTrigger.TriggerInstance())
                .save(consumer, new ResourceLocation(SanityMod.MOD_ID, "sanity/insanity_death"), fileHelper);
    }
}
