package mod.totloky.crs.blocks;

import mod.totloky.crs.blocks.farms.FarmLogic;
import mod.totloky.crs.utils.GuiHelper;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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
        if (tileentity instanceof TileEntityFarmBlock) {
            if (playerIn.canUseCommandBlock()) {
                GuiHelper.farmAssistant((TileEntityFarmBlock) tileentity);
            } else {
                giveFarmLoot(playerIn, (TileEntityFarmBlock) tileentity);
            }
            return true;
        }
        return false;
    }

    private void giveFarmLoot(EntityPlayer playerIn, TileEntityFarmBlock tileEntityFarmBlock) {
        playerIn.inventory.addItemStackToInventory(FarmLogic.farmConsumer(tileEntityFarmBlock.getTypeStored()));
    }

    @Override
    public boolean hasTileEntity(IBlockState blockState) {
        return true;
    }

    public Class<TileEntityFarmBlock> getTileEntityClass() {
        return TileEntityFarmBlock.class;
    }
}
