package mod.totloky.crs.gui;

import mod.totloky.crs.network.PacketManager;
import mod.totloky.crs.network.StatRequestPacket;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;



public class KeyInputHandler {
    

    @SubscribeEvent
    public void onKeyInput (InputEvent.KeyInputEvent event)
    {

        if (KeyBinder.guiKey.isPressed())
        {
            StatRequestPacket message = new StatRequestPacket();
            PacketManager.INSTANCE.sendToServer(message);
        }
    }
}
