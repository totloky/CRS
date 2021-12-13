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
    public void execute(MinecraftServer server, ICommandSender sender,  String[] args) throws CommandException {
        EntityPlayer player = getCommandSenderAsPlayer(sender);
        String ErrorMassage = "Что-то пошло не так. Попробуйте /crs stats [player name]";

        /*  /Crs Stats logic   */
        if (args[0].contains("stats")) {
            if (args.length == 2) {
                try {
                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Характеристики персонажа: '" + args[1] + "' \n" +
                            TextFormatting.GOLD + "Сила: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("STR", args[1]) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Ловкость: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("DEX", args[1]) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Знания: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("KNO", args[1]) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Восприятие: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("PER", args[1]) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Выносливость: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("END", args[1]) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Магия: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("MAG", args[1]) + TextFormatting.GOLD +  "]" ));
                } catch (SQLException e) {
                    e.printStackTrace();
                    player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                }
            }
            else if (args.length == 1) {
                try {
                    player.sendMessage(new TextComponentString(TextFormatting.GOLD + "Характеристики персонажа:\n" +
                            TextFormatting.GOLD + "Сила: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("STR", player.getName()) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Ловкость: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("DEX", player.getName()) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Знания: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("KNO", player.getName()) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Восприятие: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("PER", player.getName()) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Выносливость: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("END", player.getName()) + TextFormatting.GOLD +  "]\n" +
                            TextFormatting.GOLD + "Магия: [" + TextFormatting.DARK_GREEN + DBWorker.getStat("MAG", player.getName()) + TextFormatting.GOLD +  "]" ));
                } catch (SQLException e) {
                    e.printStackTrace();
                    player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
                }
            }
        }
        else {
            player.sendMessage(new TextComponentString(TextFormatting.RED + ErrorMassage));
        }
    }
}
