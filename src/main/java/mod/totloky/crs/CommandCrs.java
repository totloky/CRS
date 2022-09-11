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
import java.util.Arrays;
import java.util.Collections;
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
        String errorMassage = "Something went wrong. Please report the bug to the technical administrator.";


        // (/crs) usage information
        if (args.length == 0) {
            player.sendMessage(new TextComponentString(TextFormatting.RED + "try /crs [command]\n/crs db" +
                    "\n/crs register\n/crs stat\n/crs stats"));
        }


        // (/crs db) logic
        // (/crs db connect) for open new db connection
        if (args[0].equals("db")) {
            switch (args[1]) {
                case "connect":
                    try {
                        MySQLHandler.dbOpenConnection();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    }
                    break;
                case "disconnect":
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "Coming soon"));
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    break;
            }
            return;
        }

        // (/crs register [nick] [password] | [stats])
        if (args[0].equals("register")) {
            switch (args.length) {
                case 3:
                    try {
                        MySQLHandler.regUser(args[1], args[2]);
                        player.sendMessage(new TextComponentString(TextFormatting.GRAY + "Player " + args[1] + " successfully registered"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    }
                    break;
                case 9:
                    String[] stats = new String[6];
                    for (int i=0; i<6; i++) {
                        stats[i]=args[i+3];
                    }
                    System.out.println(Arrays.toString(stats));
                    try {
                        MySQLHandler.regUser(args[1], args[2], stats);
                        player.sendMessage(new TextComponentString(TextFormatting.GRAY + "Player " + args[1] + " successfully registered"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    }
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.RED + "/crs register [nick] [password] [STR] [DEX] [KNO] [PER] [END] [MAG]"));
            }
        }


        // (/crs stat [nick] [stat] [value])
        if (args[0].equals("stat") & args[1].equals("set")) {
            try {
                try {
                    MySQLHandler.setStat(args[2], args[3], args[4]);
                } catch (SQLException e) {
                    e.printStackTrace();
                    player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
            }
        }


        //  (/crs stats) logic
        //  (/crs stats [nick])
        if (args[0].equals("stats")) {
            switch (args.length) {
                case 2:
                    try {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "'" + args[1] + "' stats:" + "\n" +
                                TextFormatting.GOLD + "STR: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("STR", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "DEX: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("DEX", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "KNO: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("KNO", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "PER: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("PER", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "END: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("END", args[1]) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "MAG: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("MAG", args[1]) + TextFormatting.GOLD + "]"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    }
                    break;
                // (/crs stats)
                case 1:
                    try {
                        player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Your stats:\n" +
                                TextFormatting.GOLD + "STR: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("STR", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "DEX: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("DEX", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "KNO: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("KNO", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "PER: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("PER", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "END: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("END", player.getName()) + TextFormatting.GOLD + "]\n" +
                                TextFormatting.GOLD + "MAG: [" + TextFormatting.DARK_GREEN + MySQLHandler.getStat("MAG", player.getName()) + TextFormatting.GOLD + "]"));
                    } catch (SQLException e) {
                        e.printStackTrace();
                        player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    }
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.RED + errorMassage));
                    break;
            }
        }
    }

    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {

        return sender instanceof EntityPlayer ? server.getPlayerList().canSendCommands(((EntityPlayer) sender).getGameProfile()) : false;
    }



    // get a list of options for when the user presses the TAB key !!!! NULLPOINTER, FIX !!!! TODO
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {

        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "db", "stat", "stats", "register");
        } else {
            if (args[0].contains("db")) {
                return getListOfStringsMatchingLastWord(args, "connect", "disconnect");
            }
            if (args[0].contains("stats")) {
                return args.length == 2 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
            }
            if (args[0].contains("stat")) {
                return getListOfStringsMatchingLastWord(args, "set");
            }
        }
        return null;
    }
}
