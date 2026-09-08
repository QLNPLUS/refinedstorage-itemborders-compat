package org.example.itembordersstoragecompat.mixin;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.example.itembordersstoragecompat.ItemBordersBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(targets = "com.refinedmods.refinedstorage.screen.BaseScreen", remap = false)
public abstract class RefinedStorageBaseScreenMixin {
    @Inject(
        method = "renderItem(Lnet/minecraft/client/gui/GuiGraphics;IILnet/minecraft/world/item/ItemStack;ZLjava/lang/String;I)V",
        at = @At("TAIL"),
        remap = false
    )
    private void itemBordersStorageCompat$renderBorder(
        GuiGraphics graphics,
        int x,
        int y,
        ItemStack stack,
        boolean overlay,
        String text,
        int textColor,
        CallbackInfo callbackInfo
    ) {
        ItemBordersBridge.renderContainerBorder(graphics, stack, x, y);
    }
}
