package com.luckytail3939.gmail.com.inmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

//樹皮を剥いだ原木、木用のクラス
public class SanityLogBlock extends RotatedPillarBlock {
    public SanityLogBlock(Properties pProperties) {
        super(pProperties);
    }

    //FireBlockクラスにあるバニラの設定を参考に決める。
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }
}
