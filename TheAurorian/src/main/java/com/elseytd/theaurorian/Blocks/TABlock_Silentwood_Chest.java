package com.elseytd.theaurorian.Blocks;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems.IUniqueModel;
import com.elseytd.theaurorian.TAMod;
import com.elseytd.theaurorian.TileEntities.SilentwoodChest_TileEntity;
import com.elseytd.theaurorian.TileEntities.SilentwoodChest_TileEntitySpecialRenderer;
import net.minecraft.block.BlockChest;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TABlock_Silentwood_Chest extends BlockChest implements IUniqueModel {

	public static final String BLOCKNAME = "silentwoodchest";

	public static BlockChest.Type SILENTWOODCHEST = EnumHelper.addEnum(BlockChest.Type.class, "SILENTWOODCHEST", new Class[0], new Object[0]);

	public TABlock_Silentwood_Chest() {
		super(SILENTWOODCHEST);
		this.setCreativeTab(TAMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(TAMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SilentwoodChest_TileEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(SilentwoodChest_TileEntity.class, new SilentwoodChest_TileEntitySpecialRenderer());
		Item.getItemFromBlock(TABlocks.Registry.SILENTWOODCHEST.getBlock()).setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
			@Override
			public void renderByItem(ItemStack itemStackIn, float partialTicks) {
				TileEntityRendererDispatcher.instance.render(new SilentwoodChest_TileEntity(SILENTWOODCHEST), 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
			}
		});
	}

}
