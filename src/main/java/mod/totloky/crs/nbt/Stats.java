package mod.totloky.crs.nbt;

import mod.totloky.crs.MySQLHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;

public class Stats implements IStat {

    private HashMap<String, String> vals = new HashMap<String, String>();

    public Stats() {
        vals.put("STR", "0");
        vals.put("DEX", "0");
        vals.put("KNO", "0");
        vals.put("PER", "0");
        vals.put("END", "0");
        vals.put("MAG", "0");
    }


    @Override
    public void set(String stat, String points) {
        vals.put(stat.toUpperCase(Locale.ROOT), points);
    }

    @Override
    public String get(String stat) {
        String res = vals.get(stat);
        return res != null ? res : "0";
    }

    @SideOnly(Side.SERVER)
    @Override
    public String getCurrent(String stat, String playerName) throws SQLException {
        return MySQLHandler.getStat(stat, playerName);
    }

    @SideOnly(Side.SERVER)
    @Override
    public void setCurrent(String stat, String name) throws SQLException {
        vals.put(stat, MySQLHandler.getStat(stat, name));
    }
}
