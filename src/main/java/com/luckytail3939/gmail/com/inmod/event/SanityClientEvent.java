package com.luckytail3939.gmail.com.inmod.event;


import com.luckytail3939.gmail.com.inmod.SanityMod;
import com.luckytail3939.gmail.com.inmod.config.SanityClientConfig;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SanityMod.MOD_ID,value = Dist.CLIENT)
public class SanityClientEvent {

    private static final Minecraft mc = Minecraft.getInstance();

    private static double currentSanity = 85.0;
    // アニメーション用（滑らかに追従）
    private static double displaySanity = 85.0;

    //SAN値バーテクスチャ
    private static final ResourceLocation BAR_BG = new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_bar_bg.png");
    private static final ResourceLocation BAR_FILL_BLUE = new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_bar_fill.png");
    private static final ResourceLocation BAR_FILL_YELLOW = new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_bar_yellow.png");
    private static final ResourceLocation BAR_FILL_RED = new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_bar_red.png");

    private static ResourceLocation getBarFillTexture(double sanity) {
        if (sanity >= 70) return BAR_FILL_BLUE;
        if (sanity >= 30) return BAR_FILL_YELLOW;
        return BAR_FILL_RED;
    }

    //SAN値アイコン
    private static final ResourceLocation ICON_CLOSED =
            new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_icon_1.png");
    private static final ResourceLocation ICON_HALF =
            new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_icon_2.png");
    private static final ResourceLocation ICON_OPEN =
            new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_icon_3.png");
    private static final ResourceLocation ICON_RED =
            new ResourceLocation(SanityMod.MOD_ID, "textures/gui/sanity_icon_4.png");

    //オーバーレイ
    private static final ResourceLocation TUNNEL_OVERLAY = new ResourceLocation(SanityMod.MOD_ID, "textures/gui/tunnel_overlay.png");

    //アイコン選択ロジック
    private static ResourceLocation getSanityIcon(double sanity) {
        if (sanity <= 25) {
            // SAN25以下 → 赤目（狂気）
            return ICON_RED;
        } else if (sanity <= 50) {
            // SAN50以下 → 半開き
            return ICON_OPEN;
        } else if (sanity >= 90) {
            // SAN90以上 → 目を閉じて休眠
            return ICON_CLOSED;
        } else {
            // 通常 → 開眼
            return ICON_HALF;
        }
    }

    // ネットワークから受け取った SAN値をここに反映させる
    public static void setSanity(double sanity) {
        currentSanity = sanity;
    }

    // ★ getter を追加
    public static double getCurrentSanity() {
        return currentSanity;
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Post event) {
        if (event.getOverlay() == VanillaGuiOverlay.HOTBAR.type()) {
            GuiGraphics gui = event.getGuiGraphics();
            Minecraft mc = Minecraft.getInstance();

            if (mc.player == null || mc.level == null) return;

            int width = mc.getWindow().getGuiScaledWidth();
            int height = mc.getWindow().getGuiScaledHeight();
            int offsetX = 0, offsetY = 0;

            // -----------------------------
            // 視界狭窄の処理（共通で最後に実行）
            // -----------------------------
            if (currentSanity < 60) {
                float scale = 6.0f;
                if (currentSanity <= 50) scale = 4.5f;
                if (currentSanity <= 40) scale = 4.0f;
                if (currentSanity <= 30) scale = 3.0f;
                if (currentSanity <= 20) scale = 2.0f;

                float alpha = getOverlayAlpha(currentSanity);
                applyTunnelVision(gui, width, height, scale, alpha);
            }
            // -----------------------------
            // HUD の描画
            // -----------------------------
            int barWidth = 100;
            int barHeight = 10;
            int x = SanityClientConfig.hudX.get() + offsetX;
            int y = SanityClientConfig.hudY.get() + offsetY;

            // アニメーションで滑らかに追従
            displaySanity += (currentSanity - displaySanity) * 0.1;
            int filled = (int)((displaySanity / 100.0) * barWidth);

            // SAN値に応じたゲージテクスチャ
            ResourceLocation barTexture = getBarFillTexture(currentSanity);

            // 背景
            gui.blit(BAR_BG, x, y, 0, 0, 100, 10, 100, 10);
            // ゲージ
            gui.blit(barTexture, x, y, 0, 0, filled, 10, 100, 10);

            // アイコン
            int iconSize = 16;
            int iconX = x - iconSize - 5;
            int iconY = y - (iconSize - barHeight) / 2;
            ResourceLocation currentIcon = getSanityIcon(currentSanity);
            gui.blit(currentIcon, iconX, iconY, 0, 0, iconSize, iconSize, iconSize, iconSize);
        }
    }

    // アニメーション用の現在値
    private static float displayScale = 6.0f;
    private static float displayAlpha = 0.0f;

    // ★ 視界狭窄効果（HUD演出）
    private static void applyTunnelVision(GuiGraphics gui, int width, int height, float targetScale, float targetAlpha) {

        // スムーズに追従
        displayScale += (targetScale - displayScale) * 0.05f;
        displayAlpha += (targetAlpha - displayAlpha) * 0.05f;

        // scale = 1.0f なら通常サイズ、0.5f なら縮小（視界狭）
        int overlaySize = (int)(width * displayScale);
        int x = (width - overlaySize) / 2;
        int y = (height - overlaySize) / 2;

        // ★ ブレンドを有効化
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // ★ 色を掛け合わせてアルファ制御
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, displayAlpha);

        gui.blit(TUNNEL_OVERLAY, x, y, 0, 0, overlaySize, overlaySize, overlaySize, overlaySize);

        // ★ 状態を戻す
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
    }

    //視界狭窄効果オーバーレイの濃さ
    private static float getOverlayAlpha(double sanity) {
        if (sanity >= 60) return 0.0f;   // 健常 → 表示なし
        if (sanity >= 50) return 0.4f;   // 初期
        if (sanity >= 40) return 0.5f;   // 軽度
        if (sanity >= 30) return 0.7f;   // 中度
        if (sanity >= 20) return 0.9f;   // 重度
        return 1.0f;                     // 狂気 → 真っ黒
    }


    @SubscribeEvent
    public static void onCameraAngles(ViewportEvent.ComputeCameraAngles event) {
        if (mc.player == null) return;

        double sanity = SanityClientEvent.getCurrentSanity(); // HUDと共有しているSAN値

        if (sanity <50 && sanity >= 30) {
            // 軽度：視点が小刻みに揺れる
            double time = System.currentTimeMillis() / 100.0; // 揺れの基準時間
            float yawOffset = (float)Math.sin(time) * 1.2f;   // 左右に ±1.2度
            float pitchOffset = (float)Math.cos(time * 1.2) * 1.1f; // 上下に ±1.1度

            event.setYaw(event.getYaw() + yawOffset);
            event.setPitch(event.getPitch() + pitchOffset);
        }else if (sanity < 30 && sanity >= 10) {
            // 重度：視点が小刻みに揺れる
            double time = System.currentTimeMillis() / 50.0; // 揺れの基準時間
            float yawOffset = (float)Math.sin(time) * 1.5f;   // 左右に ±1.5度
            float pitchOffset = (float)Math.cos(time * 1.2) * 1.2f; // 上下に ±1.2度

            event.setYaw(event.getYaw() + yawOffset);
            event.setPitch(event.getPitch() + pitchOffset);
        }else if (sanity < 10 && sanity >= 1) {
            // 重度：視点が小刻みに揺れる
            double time = System.currentTimeMillis() / 10.0; // 揺れの基準時間
            float yawOffset = (float)Math.sin(time) * 5.5f;   // 左右に ±1.5度
            float pitchOffset = (float)Math.cos(time * 1.2) * 3.0f; // 上下に ±3度

            event.setYaw(event.getYaw() + yawOffset);
            event.setPitch(event.getPitch() + pitchOffset);
        }
    }
}

//SAN値減少によるデバフ付与をクライアント側からサーバー側へ変更。効果終了後、アイコンが残るバグ修正のため。


