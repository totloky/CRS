package mod.totloky.crs.gui;

import mod.totloky.crs.blocks.TileEntityFarmBlock;
import net.minecraft.client.gui.GuiScreen;

public class GuiFarmBlock extends GuiScreen {

    private final TileEntityFarmBlock farmBlock;

    //private static ResourceLocation PASS_GUI_TEXTURE =  new ResourceLocation("crs","textures/gui/pass.png");

    public GuiFarmBlock(TileEntityFarmBlock farmBlockIn) {
        this.farmBlock = farmBlockIn;
    }

    @Override
    public void initGui() {
        super.initGui();
    }


    @Override
    public void drawScreen(int w, int h, float p_73863_3_) {
        this.drawDefaultBackground();
    }
}
