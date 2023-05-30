package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SilkberryFood extends ItemSeedFood {

	public static final String ITEMNAME = "silkberry";

	public SilkberryFood() {
		super(2, 0.1F, BlockRegistry.Registry.PLANTSILKBERRYCROP.getBlock(), BlockRegistry.Registry.AURORIANFARMTILE.getBlock());
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
		this.setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 20), 0.5F);
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		ItemStack itemstack = player.getHeldItem(hand);
		net.minecraft.block.state.IBlockState state = worldIn.getBlockState(pos);
		if (facing == EnumFacing.UP && player.canPlayerEdit(pos.offset(facing), facing, itemstack) && state.getBlock() == BlockRegistry.Registry.AURORIANFARMTILE.getBlock() || state.getBlock() == Blocks.FARMLAND && worldIn.isAirBlock(pos.up())) {
			worldIn.setBlockState(pos.up(), BlockRegistry.Registry.PLANTSILKBERRYCROP.getBlock().getDefaultState(), 11);
			itemstack.shrink(1);
			return EnumActionResult.SUCCESS;
		} else {
			return EnumActionResult.FAIL;
		}
	}

}
