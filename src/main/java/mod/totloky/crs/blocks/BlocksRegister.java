package mod.totloky.crs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlocksRegister {
    public static Block FARM_BLOCK = new FarmBlock(Material.WOOD, MapColor.OBSIDIAN);

    public static void register() {
        setRegister(FARM_BLOCK);
    }

    @SideOnly(Side.CLIENT)
    public static void registerRender() {
        setRender(FARM_BLOCK);
    }

    private static void setRegister(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    private static void setRender(Block block) {

    }
}