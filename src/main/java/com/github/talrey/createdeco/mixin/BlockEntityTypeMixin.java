package com.github.talrey.createdeco.mixin;

import com.github.talrey.createdeco.blocks.ShippingContainerBlock;
import com.github.talrey.createdeco.blocks.DyedPlacardBlock;
import com.zurrtum.create.AllBlockEntityTypes;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {
  @Inject(method = "isValid", at = @At("HEAD"), cancellable = true)
  private void createdeco$acceptShippingContainerStates(BlockState state, CallbackInfoReturnable<Boolean> cir) {
    if ((Object) this == AllBlockEntityTypes.ITEM_VAULT && ShippingContainerBlock.isVault(state))
      cir.setReturnValue(true);
    if ((Object) this == AllBlockEntityTypes.PLACARD && state.getBlock() instanceof DyedPlacardBlock)
      cir.setReturnValue(true);
  }
}
