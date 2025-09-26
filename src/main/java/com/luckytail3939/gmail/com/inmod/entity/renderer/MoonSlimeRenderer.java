package com.luckytail3939.gmail.com.inmod.entity.renderer;

import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.entity.custom.MoonSlime;
import com.luckytail3939.gmail.com.inmod.entity.model.MoonSlimeModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

public class MoonSlimeRenderer extends MobRenderer<MoonSlime, MoonSlimeModel<MoonSlime>> {

    private static final ResourceLocation MOON_SLIME_LOCATION = new ResourceLocation(SanityMod.MOD_ID,"textures/entity/moon_slime.png");
    public MoonSlimeRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MoonSlimeModel<>(pContext.bakeLayer(MoonSlimeModel.LAYER_LOCATION)), 0.25f);//数値:影のサイズ
    }

    public void render(MoonSlime pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.shadowRadius = 0.25F * (float)pEntity.getSize();
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }

    protected void scale(MoonSlime pLivingEntity, PoseStack pPoseStack, float pPartialTickTime) {
        float $$3 = 0.999F;
        pPoseStack.scale(0.999F, 0.999F, 0.999F);
        pPoseStack.translate(0.0F, 0.001F, 0.0F);
        float $$4 = (float)pLivingEntity.getSize();
        float $$5 = Mth.lerp(pPartialTickTime, pLivingEntity.oSquish, pLivingEntity.squish) / ($$4 * 0.5F + 1.0F);
        float $$6 = 1.0F / ($$5 + 1.0F);
        pPoseStack.scale($$6 * $$4, 1.0F / $$6 * $$4, $$6 * $$4);
    }

    @Override
    protected @Nullable RenderType getRenderType(MoonSlime pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        ResourceLocation resourceLocation = this.getTextureLocation(pLivingEntity);
        return RenderType.entityTranslucent(resourceLocation,true);/*Translucent = 半透明*/
    }

    @Override
    public ResourceLocation getTextureLocation(MoonSlime moonSlime) {
        return MOON_SLIME_LOCATION;
    }
}
