package mod.totloky.crs;

import mod.totloky.crs.proxy.ClientProxy;
import mod.totloky.crs.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.sql.SQLException;

@Mod(modid = CRS.MODID, name = CRS.NAME, version = CRS.VERSION, acceptableRemoteVersions = "*")
public class CRS {
    public static final String MODID = "crs";
    public static final String NAME = "CRS";
    public static final String VERSION = "1.1";

    @SidedProxy(serverSide = "mod.totloky.crs.proxy.CommonProxy", clientSide = "mod.totloky.crs.proxy.ClientProxy")
    public static CommonProxy commonProxy;
    public static ClientProxy clientProxy;

    @EventHandler
    public void commonPreInit(FMLPreInitializationEvent event) {
        commonProxy.commonPreInit(event);
    }

    @EventHandler
    @SideOnly(Side.SERVER)
    public void serverStarting(FMLServerStartingEvent event) throws SQLException {
        commonProxy.serverStarting(event);
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void clientStarting(FMLPreInitializationEvent event) {
        clientProxy.clientPreInit(event);
    }
}
