package mod.totloky.crs.proxy;

import mod.totloky.crs.CommandCrs;
import mod.totloky.crs.ConfigManager;
import mod.totloky.crs.MySQLHandler;
import mod.totloky.crs.blocks.BlocksRegister;
import mod.totloky.crs.nbt.InitCapabilities;
import mod.totloky.crs.network.PacketManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import java.sql.SQLException;

public class CommonProxy {




    @Mod.EventHandler
    public void commonPreInit(FMLPreInitializationEvent event) {
        InitCapabilities.registerCapabilities();
        PacketManager.registerPacketList();
        BlocksRegister.register();
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) throws SQLException {

        // creating mod config
        ConfigManager.configCreate();
        // init db connection at server starting
        MySQLHandler.dbOpenConnection();
        // crs command registration
        event.registerServerCommand(new CommandCrs());
    }
}