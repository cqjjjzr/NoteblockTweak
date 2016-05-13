package charlie.nbtweak;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class BlockEnhancedMessage implements IMessage{
	public int x, y, z, note, dimension;
	
	public BlockEnhancedMessage(){}

	@Override
	public void fromBytes(ByteBuf buf) {
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		note = buf.readInt();
		dimension = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(note);
		buf.writeInt(dimension);
	}
}
