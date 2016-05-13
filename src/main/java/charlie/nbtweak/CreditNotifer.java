package charlie.nbtweak;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraft.util.ChatComponentText;

public class CreditNotifer {
	@SubscribeEvent
	public void world(PlayerTickEvent event){
		event.player.addChatComponentMessage(new ChatComponentText("NoteblockTweak " + NoteblockTweak.VERSION + " by CharlieJiang"));
		event.player.addChatComponentMessage(new ChatComponentText("RealPiano ResourcePack by lkrb"));
		cpw.mods.fml.common.FMLCommonHandler.instance().bus().unregister(this);
	}
}
