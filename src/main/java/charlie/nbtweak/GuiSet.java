package charlie.nbtweak;

import java.util.Arrays;
import java.util.LinkedList;

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

public class GuiSet extends GuiScreen {
	private TileEntityNote note;
	private GuiTextField tfInput;
	private GuiScreen parentScreen;
	private LinkedList<GuiButton> btnOct;
	private LinkedList<GuiButton> btnLct;
	private LinkedList<GuiButton> btnUct;
	//private GuiButton btnExit;
	private String g = "", n = "", ub = "";
	private boolean textM = true;
	private ResourceLocation texture = new ResourceLocation("noteblocktweak", "textures/gui/gui.png");

	public GuiSet(TileEntityNote note, GuiScreen p){
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
	protected void mouseClicked(int par1, int par2, int par3) {
	    tfInput.mouseClicked(par1, par2, par3);
	    super.mouseClicked(par1, par2, par3);
	}
	 
	@Override
	public void onGuiClosed() {
		String str = text();
		if(str == null) return;
		if(!avaliable(str)) return;
		int g = Mapping.gMap.get(str);
		int n = Mapping.nMap.get(str);
		BlockMessage bm = new BlockMessage();
		bm.x = note.xCoord;
		bm.y = note.yCoord;
		bm.z = note.zCoord;
		bm.dimension = Minecraft.getMinecraft().thePlayer.dimension;
		bm.id = g;
		bm.note = n;
		NoteblockTweak.INSTANCE.sendToServer(bm);
	}
	@Override
	public void initGui(){
		tfInput = new GuiTextField(fontRendererObj, (int)(width*0.5)-150, (int)(height*0.85), 300, 20);
		tfInput.setMaxStringLength(10);
		tfInput.setFocused(true);
		tfInput.setCanLoseFocus(false);
		btnOct = new LinkedList<GuiButton>();
		btnLct = new LinkedList<GuiButton>();
		btnUct = new LinkedList<GuiButton>();
		//btnExit = new GuiButton(0, (int)(width*0.75), (int)(height*0.85), 60, 20, "OK");
		String[] octs = {"C", "D", "E", "F", "G", "A", "B"};
		String[] lcts = {"1", "2", "3", "4", "5", "6", "7"};
		String[] ucts = {"\u266f", "\u266d", "\u266e"};
		for(int i = 0;i < 7;i++){
			GuiButton gb = new GuiButton(0, (int)(width*0.1), (int)(height*0.15) + 20 * i, 20, 20, octs[i]);
			btnOct.add(i, gb);
			buttonList.add(gb);
		}
		for(int i = 0;i < 7;i++){
			GuiButton gb = new GuiButton(0, (int)(width*0.15), (int)(height*0.15) + 20 * i, 20, 20, lcts[i]);
			btnLct.add(i, gb);
			buttonList.add(gb);
		}
		for(int i = 0;i < 3;i++){
			GuiButton gb = new GuiButton(0, (int)(width*0.2), (int)(height*0.15) + 20 * i, 20, 20, ucts[i]);
			btnUct.add(i, gb);
			buttonList.add(gb);
		}
	}
	
	@Override
	public void drawScreen(int p1, int p2, float p3){
		try{
			drawDefaultBackground();
		}catch(Exception e){}
		int wX, wY;
		wX = width / 2 - (247 / 2);
		wY = height / 2 - (165 / 2);
		mc.renderEngine.bindTexture(texture);
		drawTexturedModalRect(wX, wY, 0, 0, 256, 256); //绘制一个256x256的纹理
		for(int i = 0;i < 7;i++){
			GuiButton gb = btnOct.get(i);
			gb.xPosition = wX + 10 + i * 28;
			gb.yPosition = wY + 98;
		}
		for(int i = 0;i < 7;i++){
			GuiButton gb = btnLct.get(i);
			gb.xPosition = wX + 10 + i * 28;
			gb.yPosition = wY + 122;
		}
		for(int i = 0;i < 3;i++){
			GuiButton gb = btnUct.get(i);
			gb.xPosition = wX + 214;
			gb.yPosition = wY + 98 + i * 20;
		}
		tfInput.xPosition = wX + 110;
		tfInput.yPosition = wY + 21;
		tfInput.height = 20;
		tfInput.width = 115;
		/*drawRect((int)(width*0.1), (int)(height*0.1), (int)(width*0.9), (int)(height*0.7), 0x80FFFFFF);*/
		drawCenteredString(fontRendererObj, I18n.format("gui.notice"), width/2, wY + 73, 0xFFFF00);
		String str = text();
		if(str != null)
			if(avaliable(str))
				drawCenteredString(fontRendererObj, I18n.format("gui.valid"), wX + 166, wY + 51, 0x00FF00);
			else
				drawCenteredString(fontRendererObj, I18n.format("gui.invalid"), wX + 166, wY + 51, 0xFF0000);
		
		if(tfInput != null){
			tfInput.drawTextBox();
			if(!textM)
				tfInput.setText(g + ub + n);
		}
		super.drawScreen(p1, p2, p3);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return false;
	}
	
	@Override
	protected void actionPerformed(GuiButton button) {
		if(button == null) return;
	    if(btnOct.contains(button)){
	    	g = button.displayString;
	    	textM = false;
	    }
	    if(btnLct.contains(button)){
	    	n = button.displayString;
	    	textM = false;
	    }
	    if(btnUct.contains(button)){
	    	if(button.displayString == "\u266f") ub = "#";
	    	if(button.displayString == "\u266d") ub = "b";
	    	if(button.displayString == "\u266e") ub = "";
	    	textM = false;
	    }
	    tfInput.setText(g + ub + n);
	}
	
	private boolean avaliable(String str){
		if(str.length() != 2 && str.length() != 3) return false;
		return Mapping.gMap.containsKey(str) && Mapping.nMap.containsKey(str);
	}
	
	private String text(){
		if(tfInput == null) return null;
		String str = tfInput.getText();
		if(str == null) return null;
		str = str.trim();
		if(str.length() > 1 && Character.isLetter(str.charAt(0))){
			char fCh = str.charAt(0);
			fCh = Character.toUpperCase(fCh);
			str = String.valueOf(fCh) + str.substring(1);
		}
		return str;
	}
}
