package com.luckytail3939.gmail.com.inmod.block.custom;

//原木と木用のクラス

import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class SanityStrippableLogBlock extends SanityLogBlock{

    //インスタンス変数として樹皮を剥いだブロックを設定
    private final Supplier<Block> strippedLog;

    public SanityStrippableLogBlock(Properties pProperties, Supplier<Block> strippedLog) {
        super(pProperties);
        this.strippedLog = strippedLog;
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        //斧で右クリックした場合、樹皮を剥いだ原木に変更
        if (toolAction == ToolActions.AXE_STRIP){
            return strippedLog.get().defaultBlockState().setValue(AXIS,state.getValue(AXIS));/*そのまま置き換えると向きがリセットされるので、setValueで元の向きを引き継ぎ*/
        }
        return super.getToolModifiedState(state, context, toolAction, simulate);
    }
}
