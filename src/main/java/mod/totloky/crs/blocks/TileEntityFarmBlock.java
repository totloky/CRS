package mod.totloky.crs.blocks;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFarmBlock extends TileEntity {

    private String parentTextureStored = "";
    private String recourseTypeStored = "";

    public NBTTagCompound writeToNBT(String parentTextureStored, String recourseTypeStored) {
        this.recourseTypeStored = recourseTypeStored;
        this.parentTextureStored = parentTextureStored;
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("Texture", this.parentTextureStored);
        nbt.setString("Type", this.recourseTypeStored);
        System.out.println(nbt);
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.parentTextureStored = nbt.getString("Texture");
        this.recourseTypeStored = nbt.getString("Type");
    }

    public String getTextureStored() {
        return this.parentTextureStored;
    }

    public String getTypeStored() {
        return this.recourseTypeStored;
    }

    public TileEntityFarmBlock() { super(); }
}
