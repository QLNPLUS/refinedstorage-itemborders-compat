package org.example.itembordersstoragecompat;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ItemBordersBridge {
    private static final Method RENDER_METHOD = findRenderMethod();

    private ItemBordersBridge() {
    }

    public static void renderContainerBorder(GuiGraphics graphics, ItemStack stack, int x, int y) {
        if (stack.isEmpty()) {
            return;
        }

        try {
            RENDER_METHOD.invoke(null, graphics.pose(), stack, x, y);
        } catch (IllegalAccessException exception) {
            throw new IllegalStateException("Cannot access Item Borders' container border renderer", exception);
        } catch (InvocationTargetException exception) {
            Throwable cause = exception.getCause();
            if (cause instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }
            throw new IllegalStateException("Item Borders failed to render a storage item border", cause);
        }
    }

    private static Method findRenderMethod() {
        try {
            Class<?> itemBorders = Class.forName("com.anthonyhilyard.itemborders.ItemBorders");
            Method method = itemBorders.getDeclaredMethod("render", PoseStack.class, ItemStack.class, int.class, int.class);
            method.setAccessible(true);
            return method;
        } catch (ReflectiveOperationException exception) {
            throw new IllegalStateException("Unsupported Item Borders version: container border renderer was not found", exception);
        }
    }
}
