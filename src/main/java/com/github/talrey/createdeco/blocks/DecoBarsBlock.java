package com.github.talrey.createdeco.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class DecoBarsBlock extends IronBarsBlock {
  public DecoBarsBlock(Properties properties) {
    super(properties);
  }

  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    LevelReader level = context.getLevel();
    BlockPos pos = context.getClickedPos();
    return defaultBlockState()
      .setValue(NORTH, connectsTo(level.getBlockState(pos.north())))
      .setValue(EAST, connectsTo(level.getBlockState(pos.east())))
      .setValue(SOUTH, connectsTo(level.getBlockState(pos.south())))
      .setValue(WEST, connectsTo(level.getBlockState(pos.west())))
      .setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
  }

  @Override
  protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess,
                                   BlockPos pos, Direction direction, BlockPos neighborPos,
                                   BlockState neighborState, RandomSource random) {
    if (state.getValue(WATERLOGGED))
      scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

    if (direction.getAxis().isHorizontal())
      return state.setValue(PROPERTY_BY_DIRECTION.get(direction), connectsTo(neighborState));

    return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
  }

  private boolean connectsTo(BlockState other) {
    return other.getBlock() == this;
  }
}
