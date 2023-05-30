package com.shiroroku.theaurorian.Blocks;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.TileEntities.SilentwoodChestTileEntity;
import com.shiroroku.theaurorian.TileEntities.SilentwoodChestTileEntityRenderer;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
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

public class SilentwoodChest extends BlockChest implements ItemRegistry.IUniqueModel {

	public static final String BLOCKNAME = "silentwoodchest";

	public static BlockChest.Type SILENTWOODCHEST = EnumHelper.addEnum(BlockChest.Type.class, "SILENTWOODCHEST", new Class[0]);

	public SilentwoodChest() {
		super(SILENTWOODCHEST);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setHardness(2.0F);
		this.setRegistryName(BLOCKNAME);
		this.setSoundType(SoundType.WOOD);
		this.setUnlocalizedName(AurorianMod.MODID + "." + BLOCKNAME);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new SilentwoodChestTileEntity();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		ClientRegistry.bindTileEntitySpecialRenderer(SilentwoodChestTileEntity.class, new SilentwoodChestTileEntityRenderer());
		Item.getItemFromBlock(BlockRegistry.Registry.SILENTWOODCHEST.getBlock()).setTileEntityItemStackRenderer(new TileEntityItemStackRenderer() {
			@Override
			public void renderByItem(ItemStack itemStackIn, float partialTicks) {
				TileEntityRendererDispatcher.instance.render(new SilentwoodChestTileEntity(SILENTWOODCHEST), 0.0D, 0.0D, 0.0D, 0.0F, partialTicks);
			}
		});
	}

}
