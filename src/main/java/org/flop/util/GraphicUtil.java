package org.flop.util;

import net.minecraft.client.gui.Gui;

public class GraphicUtil {

    public static void drawRectangle(int point1x, int point1y, int point2y, int color) {
        Gui.drawRect(point1x, point1y, point2x, point2y, color);
    }

}
