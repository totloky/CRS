package mod.totloky.crs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class FarmBlock extends Block {
    public FarmBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        setRegistryName("farm_block");
        setUnlocalizedName("farm_block");
        setHardness(2);
    }
}
