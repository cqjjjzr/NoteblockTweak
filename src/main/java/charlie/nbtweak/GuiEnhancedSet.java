package charlie.nbtweak;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.java.games.input.Keyboard;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockWood;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.ResourceLocation;

public class GuiEnhancedSet extends GuiScreen {
	private TileEntityEnhancedNote note;
	private GuiTextField tfInput;
	private GuiScreen parentScreen;
	private ResourceLocation texture = new ResourceLocation("noteblocktweak", "textures/gui/Piano.png");

	public GuiEnhancedSet(TileEntityEnhancedNote note, GuiScreen p){
		this.note = note;
		this.parentScreen = p;
	}
	
	@Override
	protected void keyTyped(char par1, int par2) {
		if(par2 == org.lwjgl.input.Keyboard.KEY_RETURN){
			Minecraft.getMinecraft().displayGuiScreen(parentScreen);
			return;
		}
	    if(tfInput.textboxKeyTyped(par1, par2))
	        return;
	    super.keyTyped(par1, par2);
	}
	 
	@Override
	protected void mouseClicked(int x, int y, int k) {
		int wX = width / 2 - (400 / 2);
		int wY = height / 2 - (44 / 2);
		if(x > wX && x < wX + 400 && y > wY && y < wY + 44){
			if(y > wY + 24){
				int key = (int) ((x - wX) / 7.6);
				List<String> lst = Arrays.asList(
						"A2", "B2", 
						"C1", "D1", "E1", "F1", "G1", "A1", "B1", 
						"C", "D", "E", "F", "G", "A", "B", 
						"c", "d", "e", "f", "g", "a", "b", 
						"c1", "d1", "e1", "f1", "g1", "a1", "b1",
						"c2", "d2", "e2", "f2", "g2", "a2", "b2",
						"c3", "d3", "e3", "f3", "g3", "a3", "b3",
						"c4", "d4", "e4", "f4", "g4", "a4", "b4",
						"c5");
				key = key < 52 ? key : 51;
				String str = lst.get(key);
				tfInput.setText(str);
			}
		}
	    tfInput.mouseClicked(x, y, k);
	    super.mouseClicked(x, y, k);
	}
	 
	@Override
	public void onGuiClosed() {
		String str = text();
		if(str == null) return;
		if(!avaliable(str)) return;
		int n = RealPianoMapping.get(str);
		BlockEnhancedMessage bm = new BlockEnhancedMessage();
		bm.x = note.xCoord;
		bm.y = note.yCoord;
		bm.z = note.zCoord;
		bm.dimension = Minecraft.getMinecraft().thePlayer.dimension;
		bm.note = n;
		NoteblockTweak.INSTANCE.sendToServer(bm);
	}
	@Override
	public void initGui(){
		tfInput = new GuiTextField(fontRendererObj, (int)(width*0.5)-150, (int)(height*0.85), 300, 20);
		tfInput.setMaxStringLength(10);
		tfInput.setFocused(true);
		tfInput.setCanLoseFocus(false);
	}
	
	@Override
	public void drawScreen(int p1, int p2, float p3){
		drawDefaultBackground();
		int wX, wY;
		wX = width / 2 - (400 / 2);
		wY = height / 2 - (44 / 2);
		mc.renderEngine.bindTexture(texture);
		this.func_152125_a(wX, wY, 0, 0 , 729, 85, 400, 44, 729, 85);
		tfInput.xPosition = wX;
		tfInput.yPosition = wY + 84;
		tfInput.height = 20;
		tfInput.width = 400;
		/*drawRect((int)(width*0.1), (int)(height*0.1), (int)(width*0.9), (int)(height*0.7), 0x80FFFFFF);*/
		//drawCenteredString(fontRendererObj, "Input or select note, press Enter to confirm", width/2, wY + 73, 0xFFFF00);
		String str = text();
		if(str != null)
			if(avaliable(str))
				drawCenteredString(fontRendererObj, I18n.format("gui.valid"), width / 2, wY + 114, 0x00FF00);
			else
				drawCenteredString(fontRendererObj, I18n.format("gui.invalid"), width / 2, wY + 114, 0xFF0000);
		
		if(tfInput != null){
			tfInput.drawTextBox();
		}
		super.drawScreen(p1, p2, p3);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	private boolean avaliable(String str){
		return RealPianoMapping.get(str) != -1;
	}
	
	private String text(){
		if(tfInput == null) return null;
		String str = tfInput.getText();
		if(str == null) return null;
		str = str.trim();
		return str;
	}
}
