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
@Mixin(targets = "appeng.client.gui.me.common.MEStorageScreen", remap = false)
public abstract class AppliedEnergisticsMEStorageScreenMixin {
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
        if (slot.getClass().getName().equals("appeng.client.gui.me.common.RepoSlot")) {
            ItemBordersBridge.renderContainerBorder(graphics, slot.getItem(), slot.x, slot.y);
        }
    }
}
