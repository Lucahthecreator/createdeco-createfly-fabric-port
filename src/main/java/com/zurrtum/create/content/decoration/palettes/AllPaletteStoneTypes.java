package com.zurrtum.create.content.decoration.palettes;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.Supplier;

public enum AllPaletteStoneTypes {
    ASURINE(() -> Blocks.TUFF),
    OCHRUM(() -> Blocks.CALCITE),
    SCORCHIA(() -> Blocks.BLACKSTONE),
    LIMESTONE(() -> Blocks.SANDSTONE),
    CRIMSITE(() -> Blocks.DEEPSLATE),
    VERIDIUM(() -> Blocks.MOSSY_COBBLESTONE),
    SCORIA(() -> Blocks.BASALT);

    private final Supplier<Block> baseBlock;

    AllPaletteStoneTypes(Supplier<Block> baseBlock) {
        this.baseBlock = baseBlock;
    }

    public Supplier<Block> getBaseBlock() {
        return baseBlock;
    }
}
