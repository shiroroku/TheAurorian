package com.shiroroku.theaurorian.Misc;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;

public class AurorianFluidBucketWrapper extends FluidHandlerItemStackSimple {
	public AurorianFluidBucketWrapper(final ItemStack container, final int capacity) {
		super(container, capacity);
	}

	@Override
	public boolean canFillFluidType(final FluidStack fluid) {
		return fluid.getFluid() == FluidRegistry.WATER || fluid.getFluid() == FluidRegistry.LAVA || fluid.getFluid().getName().equals("milk") || FluidRegistry.getBucketFluids().contains(fluid.getFluid());
	}

}
