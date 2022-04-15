package mod.totloky.crs.network;

import mod.totloky.crs.nbt.IStat;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static mod.totloky.crs.nbt.StatsStorage.STATS_CAPABILITY;

public class RequestPacketHandler implements IMessageHandler<StatRequestPacket, IMessage> {

    @Override
    public IMessage onMessage(StatRequestPacket message, MessageContext ctx) {



        EntityPlayerMP player = ctx.getServerHandler().player;
        IStat stats = player.getCapability(STATS_CAPABILITY, null);
        if (stats.get("STR").equals("Not in db")) {
            return new StatAnswerPacket(new int[] {0, 0, 0, 0, 0, 0});
        } else {
            return new StatAnswerPacket(new int[]{
                    Integer.parseInt(stats.get("STR")),
                    Integer.parseInt(stats.get("DEX")),
                    Integer.parseInt(stats.get("KNO")),
                    Integer.parseInt(stats.get("PER")),
                    Integer.parseInt(stats.get("END")),
                    Integer.parseInt(stats.get("MAG"))});
        }
    }
}
