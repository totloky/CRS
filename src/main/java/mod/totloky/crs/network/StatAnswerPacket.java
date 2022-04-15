package mod.totloky.crs.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

// !!! FIX THIS REALISATION !!! TODO
public class StatAnswerPacket implements IMessage {

    public StatAnswerPacket(){
    }

    private int strToSend;
    private int dexToSend;
    private int knoToSend;
    private int perToSend;
    private int endToSend;
    private int magToSend;

    public StatAnswerPacket(int[] stats) {

        this.strToSend = stats[0];
        this.dexToSend = stats[1];
        this.knoToSend = stats[2];
        this.perToSend = stats[3];
        this.endToSend = stats[4];
        this.magToSend = stats[5];
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(strToSend);
        buf.writeInt(dexToSend);
        buf.writeInt(knoToSend);
        buf.writeInt(perToSend);
        buf.writeInt(endToSend);
        buf.writeInt(magToSend);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        strToSend = buf.readInt();
        dexToSend = buf.readInt();
        knoToSend = buf.readInt();
        perToSend = buf.readInt();
        endToSend = buf.readInt();
        magToSend = buf.readInt();
    }

    public int[] getToSend() {
        return new int[]{strToSend, dexToSend, knoToSend, perToSend, endToSend, magToSend};
    }
}
