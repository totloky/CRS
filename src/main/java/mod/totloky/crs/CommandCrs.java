package mod.totloky.crs;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.sql.SQLException;
import java.util.Locale;

public class CommandCrs extends CommandBase {

    public static final String NAME = "crs", USAGE = "/crs";


    public void processCommand(ICommandSender commandSender, String[] args) {
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return this.USAGE.toLowerCase(Locale.ROOT);
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        String ErrorMassage = "Something went wrong. Check db connection or make sure that the Gradle configuration is correct.";
        if (args.length == 0) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "try /crs [command]"));
            return;
        }
        /*  (/Crs Stats) logic  */
        /*  (/Crs Stats [Nick]) */
        if (args[0].contains("stats")) {
            switch (args.length) {
                case 2:
                    try {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "'" + args[1] + "' stats:" + "\n" +
                                TextFormatting.GOLD + "STR: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("STR", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "DEX: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("DEX", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "KNO: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("KNO", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "PER: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("PER", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "END: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("END", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "MAG: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("MAG", args[1]) + TextFormatting.GOLD + "]"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    }
                    break;
                /*  (/Crs Stats */
                case 1:
                    try {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Your stats:\n" +
                                TextFormatting.GOLD + "STR: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("STR", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "DEX: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("DEX", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "KNO: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("KNO", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "PER: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("PER", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "END: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("END", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "MAG: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("MAG", player.getName()) + TextFormatting.GOLD + "]"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    }
                    break;


                default:
                    player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    break;

            }
        } else {
            player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
        }
    }
}
