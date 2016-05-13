package charlie.nbtweak;

import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStone;
import net.minecraft.block.BlockWood;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class BlockEnhancedMessageHandler implements IMessageHandler<BlockEnhancedMessage, IMessage> {
	@Override
	public IMessage onMessage(BlockEnhancedMessage message, MessageContext ctx) {
		//int d = message.dimension == 0 ? 0 : (message.dimension == -1 ? 1 : 2);
		TileEntityEnhancedNote entity = (TileEntityEnhancedNote) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		entity.note = message.note;
		entity.triggerNote(ctx.getServerHandler().playerEntity.worldObj, message.x, message.y, message.z);
		return null;
	}
}
