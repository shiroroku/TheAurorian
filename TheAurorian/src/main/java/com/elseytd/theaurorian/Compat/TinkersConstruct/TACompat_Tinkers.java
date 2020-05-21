package com.elseytd.theaurorian.Compat.TinkersConstruct;

import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.library.utils.HarvestLevels.IRON;

import java.awt.Color;

import com.elseytd.theaurorian.TABlocks;
import com.elseytd.theaurorian.TAItems;
import com.elseytd.theaurorian.Blocks.TAFluid_MoltenAurorianSteel;
import com.elseytd.theaurorian.Blocks.TAFluid_MoltenCerulean;
import com.elseytd.theaurorian.Blocks.TAFluid_MoltenMoonstone;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.BowMaterialStats;
import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerTraits;

public class TACompat_Tinkers {

	public static final AbstractTrait traitmoonlit = new TATinkersTrait_Moonlit();
	public static final AbstractTrait traitaurorianempowered = new TATinkersTrait_AurorianEmpowered();

	public static Material cerulean;
	public static Material moonstone;
	public static Material silentwood;
	public static Material aurorianstone;
	public static Material auroriansteel;

	public static void initMaterials() {
		cerulean.addCommonItems("Cerulean");
		cerulean.setRepresentativeItem(new ItemStack(TAItems.ceruleaningot));
		cerulean.addItem(TAItems.ceruleannugget, 1, Material.VALUE_Nugget);
		cerulean.addItem(TAItems.ceruleaningot, 1, Material.VALUE_Ingot);
		cerulean.addItem(TABlocks.ceruleanblock, Material.VALUE_Block);
		cerulean.addTrait(traitaurorianempowered);

		moonstone.addCommonItems("Moonstone");
		moonstone.setRepresentativeItem(new ItemStack(TAItems.moonstoneingot));
		moonstone.addItem(TAItems.moonstonenugget, 1, Material.VALUE_Nugget);
		moonstone.addItem(TAItems.moonstoneingot, 1, Material.VALUE_Ingot);
		moonstone.addItem(TABlocks.moonstoneblock, Material.VALUE_Block);
		moonstone.addTrait(traitmoonlit);
		moonstone.addTrait(traitaurorianempowered);

		silentwood.addCommonItems("Silentwood");
		silentwood.setCraftable(true);
		silentwood.addItem(TABlocks.silentwoodplanks, Material.VALUE_Ingot);
		silentwood.addItem(TABlocks.silentwoodlog, Material.VALUE_Ingot * 4);
		silentwood.addItem(TAItems.silentwoodstick, 1, Material.VALUE_Ingot / 2);
		silentwood.setRepresentativeItem(new ItemStack(TABlocks.silentwoodplanks));
		silentwood.addTrait(TinkerTraits.ecological);
		silentwood.addTrait(traitaurorianempowered);

		aurorianstone.addCommonItems("Aurorian Stone");
		aurorianstone.setCraftable(true);
		aurorianstone.addItem(TABlocks.auroriancobblestone, Material.VALUE_Ingot);
		aurorianstone.addItem(TABlocks.aurorianstone, Material.VALUE_Ingot);
		aurorianstone.setRepresentativeItem(new ItemStack(TABlocks.auroriancobblestone));
		aurorianstone.addTrait(TinkerTraits.cheap);
		aurorianstone.addTrait(traitaurorianempowered);

		auroriansteel.addCommonItems("Aurorian Steel");
		auroriansteel.setRepresentativeItem(new ItemStack(TAItems.auroriansteel));
		auroriansteel.addItem(TAItems.auroriansteelnugget, 1, Material.VALUE_Nugget);
		auroriansteel.addItem(TAItems.auroriansteel, 1, Material.VALUE_Ingot);
		auroriansteel.addTrait(TinkerTraits.established);
		auroriansteel.addTrait(traitaurorianempowered);
	}

	public static void preinitMetalsAndMaterials() {
		addSmelteryMetals("Cerulean", TAFluid_MoltenCerulean.FLUIDNAME);
		addSmelteryMetals("Moonstone", TAFluid_MoltenMoonstone.FLUIDNAME);
		addSmelteryMetals("AurorianSteel", TAFluid_MoltenAurorianSteel.FLUIDNAME);

		cerulean = new Material("cerulean", new Color(50, 157, 255).getRGB());
		TinkerRegistry.addMaterialStats(cerulean, new HeadMaterialStats(270, 7.00f, 3.00f, HarvestLevels.DIAMOND), new HandleMaterialStats(1f, 50), new ExtraMaterialStats(75), new BowMaterialStats(0.2f, 0.4f, -1f));
		TinkerRegistry.integrate(cerulean, TABlocks.Fluids.MOLTENCERULEAN, "Cerulean").toolforge().preInit();

		moonstone = new Material("moonstone", new Color(240, 242, 232).getRGB());
		TinkerRegistry.addMaterialStats(moonstone, new HeadMaterialStats(320, 5.50f, 3.5f, HarvestLevels.IRON), new HandleMaterialStats(0.75f, 50), new ExtraMaterialStats(120), new BowMaterialStats(0.2f, 0.4f, -1f));
		TinkerRegistry.integrate(moonstone, TABlocks.Fluids.MOLTENMOONSTONE, "Moonstone").toolforge().preInit();

		silentwood = new Material("silentwood", new Color(182, 207, 233).getRGB());
		TinkerRegistry.addMaterialStats(silentwood, new HeadMaterialStats(35, 1.75f, 2.00f, HarvestLevels.STONE), new HandleMaterialStats(1.25f, 30), new ExtraMaterialStats(15), new BowMaterialStats(1f, 1.25f, 0));
		TinkerRegistry.integrate(silentwood, "Silentwood").toolforge().preInit();

		aurorianstone = new Material("aurorianstone", new Color(92, 115, 143).getRGB());
		TinkerRegistry.addMaterialStats(aurorianstone, new HeadMaterialStats(120, 4.25f, 3.00f, IRON), new HandleMaterialStats(0.30f, -30), new ExtraMaterialStats(40), new BowMaterialStats(0.2f, 0.4f, -1f));
		TinkerRegistry.integrate(aurorianstone, "Aurorian Stone").toolforge().preInit();

		auroriansteel = new Material("auroriansteel", new Color(96, 50, 255).getRGB());
		TinkerRegistry.addMaterialStats(auroriansteel, new HeadMaterialStats(950, 8.20f, 7.5f, COBALT), new HandleMaterialStats(1.5f, -75), new ExtraMaterialStats(250), new BowMaterialStats(0.2f, 0.4f, -1f));
		TinkerRegistry.integrate(auroriansteel, TABlocks.Fluids.MOLTENAURORIANSTEEL, "AurorianSteel").toolforge().preInit();
		
	}

	private static void addSmelteryMetals(String oredictname, String fluidName) {
		NBTTagCompound tagmetal = new NBTTagCompound();
		tagmetal.setString("fluid", fluidName);
		tagmetal.setString("ore", oredictname);
		tagmetal.setBoolean("toolforge", true);
		FMLInterModComms.sendMessage("tconstruct", "integrateSmeltery", tagmetal);
	}
}
