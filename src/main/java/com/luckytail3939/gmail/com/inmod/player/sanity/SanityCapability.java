package com.luckytail3939.gmail.com.inmod.player.sanity;

public class SanityCapability implements ISanity {
    private double sanity = 85; // 初期値。後で調整可能

    @Override
    public double getSanity() {
        return sanity;
    }

    @Override
    public void setSanity(double value) {
        this.sanity = Math.max(0, Math.min(value, 100)); // 0～100に制限
    }

    @Override
    public void addSanity(double amount) {
        setSanity(this.sanity + amount);
    }

    @Override
    public void reduceSanity(double amount) {
        setSanity(this.sanity - amount);
    }
}
