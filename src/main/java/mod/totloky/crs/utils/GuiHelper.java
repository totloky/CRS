package mod.totloky.crs.utils;

import mod.totloky.crs.blocks.TileEntityFarmBlock;
import mod.totloky.crs.gui.GuiCharacterPass;
import mod.totloky.crs.gui.GuiFarmBlock;
import net.minecraft.client.Minecraft;

public class GuiHelper {

    // this method is needed to get the stat data for the gui. This is not a rudiment, it does not work without it!
    public static void packetAssistant(int[] statsArray) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiCharacterPass(statsArray));
    }

    public static void farmAssistant(TileEntityFarmBlock farmBlockIn) {
        Minecraft.getMinecraft().displayGuiScreen(new GuiFarmBlock((TileEntityFarmBlock) farmBlockIn));
    }
}
