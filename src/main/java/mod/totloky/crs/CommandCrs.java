package mod.totloky.crs;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.List;

public class CommandCrs extends CommandBase {

    public static final String NAME = "crs".toLowerCase(), USAGE = "/crs".toLowerCase();


    public void processCommand(ICommandSender commandSender, String[] args) {
    }

    @Override
    public String getName() {
        return this.NAME;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return this.USAGE;
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        String ErrorMassage = "Something went wrong. Check db connection and make sure that the Gradle configuration is correct.";

        /* (/crs) usage information */
        if (args.length == 0) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "try /crs [command]"));
        }

        /* (/crs db) logic */
        /* (/crs db connect) for open new db connection */
        if (args[0].contains("db")) {
            switch (args[1]) {
                case "connect":
                    try {
                        DBWorker.dbOpenConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    }
                    break;

                case "disconnect":
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "Coming soon"));
                    break;

                default:
                    player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    break;
            }
            return;
        }

        /*  (/crs stats) logic  */
        /*  (/crs stats [nick]) */
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
                                TextFormatting.GOLD + "МАГ: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("MAG", args[1]) + TextFormatting.GOLD + "]"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                    }
                    break;

                /*  (/crs stats) */
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
        }
    }


    /* get a list of options for when the user presses the TAB key */
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1)
        {
            return getListOfStringsMatchingLastWord(args, new String[]{"db", "stats"});
        }
        else
        {
            return null;
        }
    }
}
