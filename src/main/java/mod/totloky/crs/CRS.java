package mod.totloky.crs;

import mod.totloky.crs.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = CRS.MODID, name = CRS.NAME, version = CRS.VERSION, serverSideOnly = true, acceptableRemoteVersions = "*")
public class CRS {
    public static final String MODID = "crs";
    public static final String NAME = "CRS";
    public static final String VERSION = "1.0";

    @SidedProxy(serverSide = "mod.totloky.crs.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        this.proxy.serverStarting(event);
    }
}
