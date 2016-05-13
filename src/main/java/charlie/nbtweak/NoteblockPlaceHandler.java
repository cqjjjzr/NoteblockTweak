package charlie.nbtweak;

import net.minecraft.block.BlockNote;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemNameTag;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class NoteblockPlaceHandler {
	@SubscribeEvent
	public void blockPlaced(BlockEvent.PlaceEvent event){
		if(event.block instanceof BlockNote){
			TileEntityNote entity = (TileEntityNote) Minecraft.getMinecraft().theWorld.getTileEntity(event.x, event.y, event.z);
			Minecraft.getMinecraft().displayGuiScreen(new GuiSet(entity, Minecraft.getMinecraft().currentScreen));
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public void rightClick(PlayerInteractEvent event){
		if(event.entityPlayer.getHeldItem() != null && event.entityPlayer.getHeldItem().getItem() instanceof ItemNoteblockFork){
			if(Minecraft.getMinecraft().theWorld.getBlock(event.x, event.y, event.z) instanceof BlockNote){
				if(!event.entityPlayer.isSneaking()){
					event.setCanceled(true);
					TileEntityNote entity = (TileEntityNote) Minecraft.getMinecraft().theWorld.getTileEntity(event.x, event.y, event.z);
					Minecraft.getMinecraft().displayGuiScreen(new GuiSet(entity, Minecraft.getMinecraft().currentScreen));
				}
			}
		}
	}
}
