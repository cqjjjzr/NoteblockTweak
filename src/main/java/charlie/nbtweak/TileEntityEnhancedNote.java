package charlie.nbtweak;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityEnhancedNote extends TileEntity {
	protected int note;
	protected boolean previousRedstoneState;
	@Override
	public void writeToNBT(NBTTagCompound nbt){
		super.writeToNBT(nbt);
		nbt.setInteger("note", note);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt){
		super.readFromNBT(nbt);
		note = nbt.getInteger("note");
	}
	
	public void triggerNote(World world, int x, int y, int z){
		world.addBlockEvent(x, y, z, NoteblockTweak.BLOCKN, note, 0);
	}
}
