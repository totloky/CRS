package mod.totloky.crs.network;

import mod.totloky.crs.gui.NetworkHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// this packet sends stats to the client
public class AnswerPacketHandler implements IMessageHandler<StatAnswerPacket, IMessage> {

    @Override
    public IMessage onMessage(StatAnswerPacket message, MessageContext ctx) {

        int[] amount = message.getToSend();
        NetworkHelper.PacketAssistant(amount);

        return null;
    }
}