package mod.totloky.crs.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import java.sql.SQLException;

import static mod.totloky.crs.DBWorker.getStat;

public class CharacterPassGui extends GuiScreen {

    public final int guiWidth = 130;
    public final int guiHeight = 170;
    private static ResourceLocation PASS_GUI_TEXTURE =  new ResourceLocation("crs","textures/gui/pass.png");

    public CharacterPassGui() {
    }

    @Override
    public void initGui() {
        super.initGui();
    }


    @Override
    public void drawScreen(int w, int h, float p_73863_3_) {
        mc.getTextureManager().bindTexture(PASS_GUI_TEXTURE);
        drawTexturedModalRect((width-guiWidth)/2, (height-guiHeight)/2-20, 0, 0, guiWidth, guiHeight);
        fontRenderer.drawString("AHAHAHAH", (width-guiWidth)/2+guiWidth-50, 170, 990000);
    }
}
