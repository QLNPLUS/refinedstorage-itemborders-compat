package org.example.itembordersstoragecompat.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.inventory.Slot;
import org.example.itembordersstoragecompat.ItemBordersBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "appeng.client.gui.AEBaseScreen", remap = false)
public abstract class AppliedEnergisticsBaseScreenMixin {
    @Inject(
        method = "renderSlot(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/inventory/Slot;)V",
        at = @At("TAIL"),
        remap = false
    )
    private void itemBordersStorageCompat$renderBorder(
        GuiGraphics graphics,
        Slot slot,
        CallbackInfo callbackInfo
    ) {
        ItemBordersBridge.renderContainerBorder(graphics, slot.getItem(), slot.x, slot.y);
    }
}
