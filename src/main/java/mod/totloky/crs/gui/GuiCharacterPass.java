package mod.totloky.crs.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GuiCharacterPass extends GuiScreen {

    private int[] statsArray;

    public final int guiWidth = 130;
    public final int guiHeight = 170;
    private static ResourceLocation PASS_GUI_TEXTURE =  new ResourceLocation("crs","textures/gui/pass.png");

    public GuiCharacterPass() {
    }

    public GuiCharacterPass(int[] statsArray) {
        this.statsArray = statsArray;
    }

    @Override
    public void initGui() {
        super.initGui();
    }


    @Override
    public void drawScreen(int w, int h, float p_73863_3_) {
        this.drawDefaultBackground();
        mc.getTextureManager().bindTexture(PASS_GUI_TEXTURE);
        drawTexturedModalRect((width-guiWidth)/2, (height-guiHeight)/2-20, 0, 0, guiWidth, guiHeight);
        fontRenderer.drawString("str: " + statsArray[0], (width-guiWidth)/2+guiWidth-100, 100, 990000);
        fontRenderer.drawString("dex: " + statsArray[1], (width-guiWidth)/2+guiWidth-100, 120, 990000);
        fontRenderer.drawString("kno: " + statsArray[2], (width-guiWidth)/2+guiWidth-100, 140, 990000);
        fontRenderer.drawString("per: " + statsArray[3], (width-guiWidth)/2+guiWidth-100, 160, 990000);
        fontRenderer.drawString("end: " + statsArray[4], (width-guiWidth)/2+guiWidth-100, 180, 990000);
        fontRenderer.drawString("mag: " + statsArray[5], (width-guiWidth)/2+guiWidth-100, 80, 990000);
    }
}
