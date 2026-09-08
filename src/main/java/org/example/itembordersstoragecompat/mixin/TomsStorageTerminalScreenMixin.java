package org.example.itembordersstoragecompat.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.example.itembordersstoragecompat.ItemBordersBridge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = "com.tom.storagemod.gui.AbstractStorageTerminalScreen", remap = false)
public abstract class TomsStorageTerminalScreenMixin {
    @Redirect(
        method = "drawSlot",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/gui/GuiGraphics;renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V",
            remap = true
        ),
        remap = false
    )
    private void itemBordersStorageCompat$renderBorder(
        GuiGraphics graphics,
        Font font,
        ItemStack stack,
        int x,
        int y
    ) {
        graphics.renderItemDecorations(font, stack, x, y);
        ItemBordersBridge.renderContainerBorder(graphics, stack, x, y);
    }
}
