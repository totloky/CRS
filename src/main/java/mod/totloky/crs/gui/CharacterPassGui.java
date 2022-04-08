package mod.totloky.crs.gui;

import mod.totloky.crs.nbt.IStat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import static mod.totloky.crs.nbt.StatsStorage.STATS_CAPABILITY;

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
        EntityPlayer player = mc.player;
        IStat stats = player.getCapability(STATS_CAPABILITY, null);
        String message = stats.get("strength");

        mc.getTextureManager().bindTexture(PASS_GUI_TEXTURE);
        drawTexturedModalRect((width-guiWidth)/2, (height-guiHeight)/2-20, 0, 0, guiWidth, guiHeight);
        fontRenderer.drawString("magic: " + message, (width-guiWidth)/2+guiWidth-50, 170, 990000);
    }
}
