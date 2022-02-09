package mod.totloky.crs.gui;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class KeyBinder {

    public static KeyBinding gui;

    public static void register()
    {
        gui = new KeyBinding("key.gui", Keyboard.KEY_P, "key.categories.mhq");
        ClientRegistry.registerKeyBinding(gui);

        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }
}
