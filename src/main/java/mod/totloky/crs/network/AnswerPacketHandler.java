package mod.totloky.crs.network;

import mod.totloky.crs.chat.utils.GuiHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// this packet sends stats to the client
public class AnswerPacketHandler implements IMessageHandler<StatAnswerPacket, IMessage> {

    @Override
    public IMessage onMessage(StatAnswerPacket message, MessageContext ctx) {

        int[] amount = message.getToSend();
        GuiHelper.packetAssistant(amount);

        return null;
    }
}