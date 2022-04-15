package mod.totloky.crs.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// this packet tells the server that the client wants to get stats
public class StatRequestPacket implements IMessage {

    public StatRequestPacket() {}

    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }
}
