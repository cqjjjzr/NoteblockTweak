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

public class BlockMessageHandler implements IMessageHandler<BlockMessage, IMessage> {
	@Override
	public IMessage onMessage(BlockMessage message, MessageContext ctx) {
		/*int d = message.dimension == 0 ? 0 : (message.dimension == -1 ? 1 : 2);
		switch(message.id){
		case 0:
		MinecraftServer.getServer().worldServers[d].setBlock(message.x, message.y - 1, message.z, Blocks.planks);break;
		case 1:
		MinecraftServer.getServer().worldServers[d].setBlock(message.x, message.y - 1, message.z, Blocks.dirt);break;
		case 2:
		MinecraftServer.getServer().worldServers[d].setBlock(message.x, message.y - 1, message.z, Blocks.stone);break;
		}*/
		int d = message.dimension == 0 ? 0 : (message.dimension == -1 ? 1 : 2);
		switch(message.id){
		case 0:
			ctx.getServerHandler().playerEntity.worldObj.setBlock(message.x, message.y - 1, message.z, Blocks.planks);break;
		case 1:
			ctx.getServerHandler().playerEntity.worldObj.setBlock(message.x, message.y - 1, message.z, Blocks.dirt);break;
		case 2:
			ctx.getServerHandler().playerEntity.worldObj.setBlock(message.x, message.y - 1, message.z, Blocks.stone);break;
		}
		TileEntityNote entity = (TileEntityNote) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
		entity.note = (byte) message.note;
		entity.triggerNote(ctx.getServerHandler().playerEntity.worldObj, message.x, message.y, message.z);
		return null;
	}
}
