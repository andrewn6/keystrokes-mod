package org.flop;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.flop.listener.KeyStrokesListener;

import java.util.ArrayList;
import java.util.List;

@Mod("flop")
public class Keystrokes {

    private final int backgroundColor = 0x99000000;

    private final int backgroundColorActive = 0x99FFFFFF;

    private final int textColor = 0xFFFFFFFF;
    private final int textColorActive = 0xFF000000;

    private final List<Long> attackTimes = new ArrayList<>();
    private final List<Long> useItemTimes = new ArrayList<>();

    private KeyStrokesRenderer renderer;

    @EventHandler
    public void init(FMLInitializationEvent event) {
        this.renderer = new KeyStrokesRenderer(this);

        MinecraftForge.EVENT_BUS.register(new KeyStrokesListener(this));
        FMLCommonHandler.instance().bus().register(new KeyStrokesListener(this));
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public int getBackgroundColorActive() {
        return backgroundColorActive;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getTextColorActive() {
        return textColorActive;
    }

    public List<Long> getAttackTimes() {
        return attackTimes;
    }

    public List<Long> getUseItemTimes() {
        return useItemTimes;
    }

    public KeyStrokesRenderer getRenderer() {
        return renderer;
    }
}