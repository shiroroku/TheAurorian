package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.ItemRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Util.AurorianSteelHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class AurorianSteelShovel extends ItemSpade {

	public static final String ITEMNAME = "auroriansteelshovel";

	public AurorianSteelShovel() {
		super(ItemRegistry.Materials.AURORIANSTEEL);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState state, BlockPos pos, EntityLivingBase entityLiving) {
		if (!worldIn.isRemote && state.getBlockHardness(worldIn, pos) != 0.0D) {
			stack.damageItem(1, entityLiving);
			AurorianSteelHelper.handleAurorianSteelDurability(stack, worldIn, entityLiving);
		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		AurorianSteelHelper.getAurorianSteelInfo(stack, worldIn, tooltip, flagIn);
	}
}
