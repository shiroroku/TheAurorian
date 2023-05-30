package com.shiroroku.theaurorian.Items;

import com.shiroroku.theaurorian.Misc.AurorianFluidBucketWrapper;
import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class CeruleanBucket extends UniversalBucket {

	public static final String ITEMNAME = "ceruleanbucket";
	private final ItemStack empty = new ItemStack(this);

	public CeruleanBucket() {
		super(Fluid.BUCKET_VOLUME, ItemStack.EMPTY, true);
		this.setMaxStackSize(1);
		this.setCreativeTab(AurorianMod.CREATIVE_TAB);
		this.setRegistryName(ITEMNAME);
		this.setUnlocalizedName(AurorianMod.MODID + "." + ITEMNAME);
	}

	@Override
	public void getSubItems(@Nullable final CreativeTabs tab, final NonNullList<ItemStack> subItems) {
		if (!this.isInCreativeTab(tab))
			return;

		subItems.add(empty);
		for (final Fluid fluid : FluidRegistry.getRegisteredFluids().values()) {
			final FluidStack fs = new FluidStack(fluid, getCapacity());
			final ItemStack stack = new ItemStack(this);
			final IFluidHandlerItem fluidHandler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);
			if (fluidHandler != null && fluidHandler.fill(fs, true) == fs.amount) {
				final ItemStack filled = fluidHandler.getContainer();
				subItems.add(filled);
			}
		}
	}

	@Override
	public String getItemStackDisplayName(final ItemStack stack) {
		final FluidStack fluid = getFluid(stack);
		final String unlocname = this.getUnlocalizedNameInefficiently(stack);
		if (fluid == null) {
			return I18n.translateToLocal(unlocname + ".name").trim();
		}
		final String fluidUnlocalisedName = unlocname + ".filled." + fluid.getFluid().getName() + ".name";
		if (I18n.canTranslate(fluidUnlocalisedName)) {
			return I18n.translateToLocal(fluidUnlocalisedName);
		}
		return I18n.translateToLocalFormatted(unlocname + ".filled.name", fluid.getLocalizedName());
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(final World world, final EntityPlayer player, final EnumHand hand) {
		final ItemStack heldItem = player.getHeldItem(hand);
		if (getFluid(heldItem) != null)
			return super.onItemRightClick(world, player, hand);

		final RayTraceResult target = this.rayTrace(world, player, true);

		if (target == null || target.typeOfHit != RayTraceResult.Type.BLOCK) {
			return new ActionResult<>(EnumActionResult.PASS, heldItem);
		}
		final ItemStack singleBucket = heldItem.copy();
		singleBucket.setCount(1);
		final FluidActionResult filledResult = FluidUtil.tryPickUpFluid(singleBucket, player, world, target.getBlockPos(), target.sideHit);
		if (filledResult.isSuccess()) {
			final ItemStack filledBucket = filledResult.result;

			if (player.capabilities.isCreativeMode)
				return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);

			heldItem.shrink(1);
			if (heldItem.isEmpty())
				return new ActionResult<>(EnumActionResult.SUCCESS, filledBucket);

			ItemHandlerHelper.giveItemToPlayer(player, filledBucket);

			return new ActionResult<>(EnumActionResult.SUCCESS, heldItem);
		}

		return new ActionResult<>(EnumActionResult.PASS, heldItem);
	}

	@Override
	public ItemStack getEmpty() {
		return empty;
	}

	@Nullable
	@Override
	public FluidStack getFluid(final ItemStack container) {
		return FluidUtil.getFluidContained(container);
	}

	@Override
	public ICapabilityProvider initCapabilities(final ItemStack stack, final NBTTagCompound nbt) {
		return new AurorianFluidBucketWrapper(stack, Fluid.BUCKET_VOLUME);
	}
}
