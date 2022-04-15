package mod.totloky.crs.gui;

import net.minecraft.client.Minecraft;

public class NetworkHelper {

    // this method is needed to get the stat data for the gui. This is not a rudiment, it does not work without it!
    public static void PacketAssistant(int[] statsArray) {
        Minecraft.getMinecraft().displayGuiScreen(new CharacterPassGui(statsArray));
    }
}
