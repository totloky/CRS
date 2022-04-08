package mod.totloky.crs.nbt;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.sql.SQLException;

public interface IStat {

    void set(String stat, String points);

    String get(String stat);

    @SideOnly(Side.SERVER)
    String getCurrent(String stat) throws SQLException;

    @SideOnly(Side.SERVER)
    void setCurrent(String stat, String name) throws SQLException;
}
