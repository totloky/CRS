package mod.totloky.crs.blocks;

import mod.totloky.crs.gui.GuiFarmBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Objects;

public class FarmBlock extends BlockContainer {

    public FarmBlock(Material blockMaterialIn, MapColor blockMapColorIn) {
        super(blockMaterialIn, blockMapColorIn);
        setRegistryName("farm_block");
        setUnlocalizedName("farm_block");
        setHardness(2);
        setCreativeTab(CreativeTabs.MISC);
    }


    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityFarmBlock();
    }

    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof TileEntityFarmBlock && playerIn.canUseCommandBlock()) {
            Minecraft.getMinecraft().displayGuiScreen(new GuiFarmBlock((TileEntityFarmBlock) tileentity));
        }
        else {
            giveFarmLoot(playerIn);
        }
        return true;
    }


    private void giveFarmLoot(EntityPlayer playerIn) {
        playerIn.inventory.addItemStackToInventory(new ItemStack(Objects.requireNonNull(Item.getByNameOrId("minecraft:diamond"))));
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {
        return true;
    }

    public Class<TileEntityFarmBlock> getTileEntityClass() {
        return TileEntityFarmBlock.class;
    }
}
