package mod.totloky.crs.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class PacketManager {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("CRS|DEFAULT_CHANNEL");

    public static void registerPacketList() {
        int i=0;
        INSTANCE.registerMessage(AnswerPacketHandler.class, StatAnswerPacket.class, i, Side.CLIENT);
        i++;
        INSTANCE.registerMessage(RequestPacketHandler.class, StatRequestPacket.class, i, Side.SERVER);
    }
}
