package mod.totloky.crs.gui;

import io.netty.buffer.Unpooled;
import mod.totloky.crs.blocks.TileEntityFarmBlock;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.CPacketCustomPayload;
import org.lwjgl.input.Keyboard;

import java.io.IOException;

public class GuiFarmBlock extends GuiScreen {

    private GuiTextField textureRequestTextField;
    private GuiTextField lootTypeTextField;
    private final TileEntityFarmBlock farmBlock;

    private GuiButton doneBtn;
    private GuiButton cancelBtn;


    public GuiFarmBlock(TileEntityFarmBlock farmBlockIn) {
        this.farmBlock = farmBlockIn;
    }

    public void updateScreen()
    {
        this.textureRequestTextField.updateCursorCounter();
        this.lootTypeTextField.updateCursorCounter();
    }

    @Override
    public void initGui() {

        super.initGui();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.doneBtn = this.addButton(new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.done")));
        this.cancelBtn = this.addButton(new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, I18n.format("gui.cancel")));

        this.textureRequestTextField = new GuiTextField(2, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
        this.textureRequestTextField.setMaxStringLength(32500);
        this.textureRequestTextField.setFocused(true);

        this.lootTypeTextField = new GuiTextField(3, this.fontRenderer, this.width / 2 - 150, 90, 300, 20);
        this.lootTypeTextField.setMaxStringLength(32500);
        this.lootTypeTextField.setFocused(true);

        this.textureRequestTextField.setText(farmBlock.getTextureStored());
        this.lootTypeTextField.setText(farmBlock.getTypeStored());
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
    {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.textureRequestTextField.mouseClicked(mouseX, mouseY, mouseButton);
        this.lootTypeTextField.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Farm parameters", this.width / 2, 20, 16777215);
        this.drawString(this.fontRenderer, "Inbox for block texture", this.width / 2 - 150, 40, 10526880);
        this.textureRequestTextField.drawTextBox();

        this.drawString(this.fontRenderer, "Inbox for loot type", this.width / 2 - 150, 80, 10526880);
        this.lootTypeTextField.drawTextBox();

        this.doneBtn.drawButton(mc, mouseX, mouseY, partialTicks);
        this.cancelBtn.drawButton(mc, mouseX, mouseY, partialTicks);
    }

    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            if (button.id == 1)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else if (button.id == 0)
            {
                PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
                packetbuffer.writeString(this.textureRequestTextField.getText());
                packetbuffer.writeString(this.lootTypeTextField.getText());
                this.mc.getConnection().sendPacket(new CPacketCustomPayload("CRS|FARMS_CHANNEL", packetbuffer));
                farmBlock.writeToNBT(textureRequestTextField.getText(), lootTypeTextField.getText());
                this.mc.displayGuiScreen((GuiScreen)null);
            }
        }
    }

    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        this.textureRequestTextField.textboxKeyTyped(typedChar, keyCode);
        this.lootTypeTextField.textboxKeyTyped(typedChar, keyCode);

        if (keyCode != 28 && keyCode != 156)
        {
            if (keyCode == 1)
            {
                this.actionPerformed(this.cancelBtn);
            }
        }
        else
        {
            this.actionPerformed(this.doneBtn);
        }
    }
}
