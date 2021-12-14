package mod.totloky.crs.proxy;

import mod.totloky.crs.CommandCrs;
import mod.totloky.crs.DBWorker;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.sql.SQLException;

public class CommonProxy {

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) throws SQLException {
        DBWorker.dbOpenConnection();
        event.registerServerCommand(new CommandCrs());
    }
}
