package charlie.nbtweak;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockEnhancedNote extends BlockContainer implements ITileEntityProvider {

	public BlockEnhancedNote() {
		super(Material.wood);
		this.setBlockTextureName("minecraft:noteblock");
		this.setCreativeTab(CreativeTabs.tabRedstone);
		this.setBlockName("enhancednoteblock");
		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new TileEntityEnhancedNote();
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z) {
		Minecraft.getMinecraft().displayGuiScreen(new GuiEnhancedSet((TileEntityEnhancedNote) world.getTileEntity(x, y, z), Minecraft.getMinecraft().currentScreen));
	}

	@Override
	public boolean onBlockEventReceived(World world, int x, int y, int z,
			int a, int r) {
		String str = "lkrb.piano.p" + a + "f";
		world.getClosestPlayer(x, y, z, -1).playSound(str, 1, 1);
		world.spawnParticle("note", (double) x + 0.5D, (double) y + 1.2D, (double) z + 0.5D, (double) (a - 20) / (108.0D - 21d), 0, 0);
		return true;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			Block block) {
		boolean flag = world.isBlockIndirectlyGettingPowered(x, y, z);
		TileEntityEnhancedNote entity = (TileEntityEnhancedNote) world.getTileEntity(x, y, z);
		if (entity != null && entity.previousRedstoneState != flag){
			if(flag)
				entity.triggerNote(world, x, y, z);
			entity.previousRedstoneState = flag;
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z,
			EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		if(!world.isRemote){
			if(player.getHeldItem() != null && player.getHeldItem().getItem() instanceof ItemNoteblockFork){
				Minecraft.getMinecraft().displayGuiScreen(new GuiEnhancedSet((TileEntityEnhancedNote) world.getTileEntity(x, y, z), Minecraft.getMinecraft().currentScreen));
			}else
				((TileEntityEnhancedNote) world.getTileEntity(x, y, z)).triggerNote(world, x, y, z);
		}
		return true;
	}

	@Override
	public void onBlockClicked(World world, int x, int y, int z,
			EntityPlayer player) {
		if(!world.isRemote){
			((TileEntityEnhancedNote) world.getTileEntity(x, y, z)).triggerNote(world, x, y, z);
		}
	}
	
}
