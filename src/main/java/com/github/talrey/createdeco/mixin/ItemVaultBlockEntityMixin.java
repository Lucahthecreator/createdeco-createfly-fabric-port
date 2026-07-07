package com.github.talrey.createdeco.mixin;

import com.github.talrey.createdeco.blocks.ShippingContainerBlock;
import com.zurrtum.create.content.logistics.vault.ItemVaultBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
public class ItemVaultBlockEntityMixin {
  @Inject(method = "isValidBlockState", at = @At("HEAD"), cancellable = true)
  private void createdeco$acceptShippingContainerStates(BlockState state, CallbackInfoReturnable<Boolean> cir) {
    if ((Object)this instanceof ItemVaultBlockEntity && ShippingContainerBlock.isVault(state))
      cir.setReturnValue(true);
  }
}
