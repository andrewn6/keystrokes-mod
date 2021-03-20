package org.flop;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.settings.KeyBinding;
import org.flop.util.GraphicUtil;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;


public class KeyStrokesRenderer {

    private final KeyStrokesMod keyStrokesMod;

    public KeyStrokesRenderer(KeyStrokesMod keyStrokesMod) {
        this.keyStrokesMod = keyStrokesMod;
    }

    public void renderKeyStrokes() {
        Minecraft minecraft = Minecraft.getMinecraft();
        FontRenderer fontRenderer = minecraft.fontRenderer;

        int gap = 1;
        int buttonSize = 16;

        int spaceBarVertical = (int) (buttonSize * 0.5);
        int spaceBarHorizontal = gap * 2 + (buttonSize / 2) + gap;

        int mouseButtonVertical = (int) (buttonSize * 0.95);
        int mouseButtonHorizontal = buttonSize + (buttonSize / 2) + gap;

        GL11.glPushMatrix();
        GL11.glScaled(0.75, 0.75, 0.75); // scaled to 25%

        KeyBinding key = minecraft.gameSettings.keyBindForward;

        String keyName = getKeyBindingName(key);

        GraphicUtil.drawRectangle(gap * 2 + buttonSize, gap, gap * 2 + buttonSize * 2, buttonSize + gap, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());
        fontRenderer.drawString(keyName, gap * 3 + buttonSize + buttonSize / 2 - fontRenderer.getStringWidth(keyName) / 2, gap * 2 + buttonSize / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());


        key = minecraft.gameSettings.keyBindAttack;
        String text = this.keyStrokesMod.getAttackTimes().size() + " CPS"; // Text to draw on the button

        GraphicUtil.drawRectangle(gap, gap * 3 + buttonSize * 2, gap + mouseButtonHorizontal, gap * 3 + mouseButtonVertical * 3, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        GL11.glPushMatrix(); // Create a new matrix
        GL11.glScaled(0.755, 0.755, 0.755); // Scale down a little more so the text fits in the box
        fontRenderer.drawString(text, gap * 2 + buttonSize - fontRenderer.getStringWidth(text) / 2, gap * 3 + mouseButtonVertical * 3 + mouseButtonVertical / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());
        GL11.glPopMatrix(); // Delete matrix again

        key = minecraft.gameSettings.keyBindUseItem;
        text = this.keyStrokesMod.getUseItemTimes().size() + " CPS";

        GraphicUtil.drawRectangle(gap * 2 + mouseButtonHorizontal, gap * 3 + buttonSize * 2, gap * 2 + mouseButtonHorizontal * 2, gap * 3 + mouseButtonVertical * 3, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        GL11.glPushMatrix();
        GL11.glScaled(0.755, 0.755, 0.755);
        fontRenderer.drawString(text, gap * 3 + mouseButtonHorizontal * 2 - fontRenderer.getStringWidth(text) / 2, gap * 3 + mouseButtonVertical * 3 + mouseButtonVertical / 2 - fontRenderer.FONT_HEIGHT / 2, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());
        GL11.glPopMatrix();

        key = minecraft.gameSettings.keyBindJump;
        GraphicUtil.drawRectangle(gap, gap * 4 + mouseButtonVertical * 3, gap + spaceBarHorizontal, gap * 4 + buttonSize * 2 + mouseButtonVertical + spaceBarVertical, key.getIsKeyPressed() ? this.keyStrokesMod.getBackgroundColorActive() : this.keyStrokesMod.getBackgroundColor());

        int insideRectHorizontal = (int) (spaceBarHorizontal * 0.4); // Width of the little rectangle inside the background box

        int insideRectGapHor = (spaceBarHorizontal - insideRectHorizontal) / 2; // Gap to the left & right of the background box
        int insideRectGapVer = (spaceBarVertical - gap) / 2; // Gap to the top of the background box

        GraphicUtil.drawRectangle(gap + insideRectGapHor, gap * 5 + mouseButtonVertical * 3 + insideRectGapVer, gap + spaceBarHorizontal - insideRectGapHor, gap * 5 + mouseButtonVertical * 3 + insideRectGapVer + gap, key.getIsKeyPressed() ? this.keyStrokesMod.getTextColorActive() : this.keyStrokesMod.getTextColor());

        GL11.glPopMatrix(); // Remove the matrix we created
    }

    private String getKeyBindingName(KeyBinding keyBinding) {
        String name = Keyboard.getKeyName(keyBinding.getKeyCode());

        if (name.length() > 1) {
            if (name.startsWith("NUMPAD")) return name.charAt(6) + "";
            return name.charAt(0) + "";
        }

        return name;
    }

}
