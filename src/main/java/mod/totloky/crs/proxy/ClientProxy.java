package mod.totloky.crs.proxy;

import mod.totloky.crs.blocks.BlocksRegister;
import mod.totloky.crs.gui.KeyBinder;
import mod.totloky.crs.gui.KeyInputHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy{
    public static void clientPreInit(FMLPreInitializationEvent event)
    {
        KeyBinder.register();
        MinecraftForge.EVENT_BUS.register(new KeyInputHandler());
    }

    public static void init(FMLInitializationEvent event)
    {
        BlocksRegister.registerRender();
    }
}
