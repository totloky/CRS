package mod.totloky.crs.nbt;

import mod.totloky.crs.MySQLHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.sql.SQLException;
import java.util.Locale;

public class Stats implements IStat {

    private String strength;
    private String dexterity;
    private String knowledge;
    private String perception;
    private String endurance;
    private String magic;

    public Stats() {
        strength = "0";
        dexterity = "0";
        knowledge = "0";
        perception = "0";
        endurance = "0";
        magic = "0";
    }

    @Override
    public void set(String stat, String points) {
        switch(stat.toUpperCase(Locale.ROOT)) {
            case "STR":
                this.strength = points;
                break;

            case "DEX":
                this.dexterity = points;
                break;

            case "KNO":
                this.knowledge = points;
                break;

            case "PER":
                this.perception = points;
                break;

            case "END":
                this.endurance = points;
                break;

            case "MAG":
                this.magic = points;
                break;
        }
    }

    @Override
    public String get(String stat) {
        switch(stat) {
            case "STR":
                return this.strength;

            case "DEX":
                return this.dexterity;

            case "KNO":
                return this.knowledge;

            case "PER":
                return this.perception;

            case "END":
                return this.endurance;

            case "MAG":
                return this.magic;

            default:
                return "0";
        }
    }

    @SideOnly(Side.SERVER)
    @Override
    public String getCurrent(String stat) throws SQLException {
        return MySQLHandler.getStat(stat, "Odis");
    }

    @SideOnly(Side.SERVER)
    @Override
    public void setCurrent(String stat, String name) throws SQLException {
        String dbStat = MySQLHandler.getStat(stat, name);
        switch (stat) {
            case "STR":
                strength = dbStat;

            case "DEX":
                dexterity = dbStat;

            case "KNO":
                knowledge = dbStat;

            case "PER":
                perception = dbStat;

            case "END":
                endurance = dbStat;

            case "MAG":
                magic = dbStat;
        }
    }
}
