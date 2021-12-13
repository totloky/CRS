package mod.totloky.crs.proxy;

import mod.totloky.crs.CommandCrs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandCrs());
    }
}
