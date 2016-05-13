package charlie.nbtweak;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = NoteblockTweak.MODID, version = NoteblockTweak.VERSION)
public class NoteblockTweak
{
    public static final String MODID = "noteblocktweak";
    public static final String VERSION = "3.0";
    
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("noteblocktweak");
    
    public static BlockEnhancedNote BLOCKN;
    public static ItemNoteblockFork itemNbT;
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(new NoteblockPlaceHandler());
		cpw.mods.fml.common.FMLCommonHandler.instance().bus().register(new CreditNotifer());
		INSTANCE.registerMessage(BlockMessageHandler.class, BlockMessage.class, 0, Side.SERVER);
		INSTANCE.registerMessage(BlockEnhancedMessageHandler.class, BlockEnhancedMessage.class, 1, Side.SERVER);
		Mapping.init();
		RealPianoMapping.init();
		itemNbT = new ItemNoteblockFork();
		itemNbT.setUnlocalizedName("noteblockfork");
		itemNbT.setTextureName("noteblocktweak:noteblock_fork_hand");
		itemNbT.setMaxStackSize(1);
		itemNbT.setCreativeTab(CreativeTabs.tabTools);
		GameRegistry.registerItem(itemNbT, "NoteblockFork");
		BLOCKN = new BlockEnhancedNote();
		GameRegistry.registerBlock(BLOCKN, "EnhancedNoteBlock");
		GameRegistry.registerTileEntity(TileEntityEnhancedNote.class, "tileenhancednote");
    }
}
