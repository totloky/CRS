package mod.totloky.crs.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class KeyInputHandler {
    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event)
    {
        if (KeyBinder.gui.isPressed())
        {
            Minecraft.getMinecraft().displayGuiScreen(new CharacterPassGui());
        }
    }
}
