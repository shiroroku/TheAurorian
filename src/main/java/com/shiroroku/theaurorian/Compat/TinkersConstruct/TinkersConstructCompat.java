package com.shiroroku.theaurorian.Compat.TinkersConstruct;

import com.shiroroku.theaurorian.Registry.BlockRegistry;
import com.shiroroku.theaurorian.Registry.ItemRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.awt.*;

import static slimeknights.tconstruct.library.utils.HarvestLevels.COBALT;
import static slimeknights.tconstruct.library.utils.HarvestLevels.IRON;

public class TinkersConstructCompat {

	public static final AbstractTrait traitmoonlit = new MoonlitTinkersTrait();
	public static final AbstractTrait traitaurorianempowered = new AurorianEmpoweredTinkersTrait();

	public static Material cerulean;
	public static Material moonstone;
	public static Material silentwood;
	public static Material aurorianstone;
	public static Material auroriansteel;

	public static void preinitMetalsAndMaterials() {
		cerulean = new Material("cerulean", new Color(50, 157, 255).getRGB());
		cerulean.addCommonItems("Cerulean");
		cerulean.setRepresentativeItem("ingotCerulean");
		cerulean.setFluid(BlockRegistry.Fluids.MOLTENCERULEAN);
		cerulean.setCraftable(false).setCastable(true);
		cerulean.addTrait(traitaurorianempowered);

		moonstone = new Material("moonstone", new Color(240, 242, 232).getRGB());
		moonstone.addCommonItems("Moonstone");
		moonstone.setRepresentativeItem("ingotMoonstone");
		moonstone.setFluid(BlockRegistry.Fluids.MOLTENMOONSTONE);
		moonstone.setCraftable(false).setCastable(true);
		moonstone.addTrait(traitmoonlit);
		moonstone.addTrait(traitaurorianempowered);

		auroriansteel = new Material("auroriansteel", new Color(96, 50, 255).getRGB());
		auroriansteel.addCommonItems("AurorianSteel");
		auroriansteel.setRepresentativeItem(ItemRegistry.Registry.INGOTAURORIANSTEEL.getItem());
		auroriansteel.setFluid(BlockRegistry.Fluids.MOLTENAURORIANSTEEL);
		auroriansteel.setCraftable(false).setCastable(true);
		auroriansteel.addTrait(TinkerTraits.established);
		auroriansteel.addTrait(traitaurorianempowered);

		silentwood = new Material("silentwood", new Color(182, 207, 233).getRGB());
		silentwood.addItem("plankSilentwood", 1, Material.VALUE_Ingot);
		silentwood.addItem("logSilentwood", 1, Material.VALUE_Ingot * 4);
		silentwood.addItem("stickSilentwood", 1, Material.VALUE_Ingot / 2);
		silentwood.setRepresentativeItem("plankSilentwood");
		silentwood.setCraftable(true).setCastable(false);
		silentwood.addTrait(TinkerTraits.ecological);
		silentwood.addTrait(traitaurorianempowered);

		aurorianstone = new Material("aurorianstone", new Color(92, 115, 143).getRGB());
		aurorianstone.addItem("cobblestoneAurorian", 1, Material.VALUE_Ingot);
		aurorianstone.addItem("stoneAurorian", 1, Material.VALUE_Ingot);
		aurorianstone.setRepresentativeItem("cobblestoneAurorian");
		aurorianstone.setCraftable(true).setCastable(false);
		aurorianstone.addTrait(TinkerTraits.cheap);
		aurorianstone.addTrait(traitaurorianempowered);

	}

	public static void initMaterials() {
		TinkerRegistry.addMaterialStats(cerulean, new HeadMaterialStats(270, 7.00f, 3.00f, HarvestLevels.DIAMOND), new HandleMaterialStats(1f, 50), new ExtraMaterialStats(75), new BowMaterialStats(1.2f, 0.8f, 2f));
		TinkerRegistry.integrate(cerulean, BlockRegistry.Fluids.MOLTENCERULEAN, "Cerulean").toolforge().preInit();

		TinkerRegistry.addMaterialStats(moonstone, new HeadMaterialStats(320, 5.50f, 3.5f, HarvestLevels.IRON), new HandleMaterialStats(0.75f, 50), new ExtraMaterialStats(120), new BowMaterialStats(0.5f, 1.5f, 7f));
		TinkerRegistry.integrate(moonstone, BlockRegistry.Fluids.MOLTENMOONSTONE, "Moonstone").toolforge().preInit();

		TinkerRegistry.addMaterialStats(auroriansteel, new HeadMaterialStats(950, 8.20f, 7.5f, COBALT), new HandleMaterialStats(1.5f, -75), new ExtraMaterialStats(250), new BowMaterialStats(0.4f, 2f, 9f));
		TinkerRegistry.integrate(auroriansteel, BlockRegistry.Fluids.MOLTENAURORIANSTEEL, "AurorianSteel").toolforge().preInit();

		TinkerRegistry.addMaterialStats(silentwood, new HeadMaterialStats(35, 1.75f, 2.00f, HarvestLevels.STONE), new HandleMaterialStats(1.25f, 30), new ExtraMaterialStats(15), new BowMaterialStats(1f, 1.25f, 0));
		TinkerRegistry.integrate(silentwood).toolforge().preInit();

		TinkerRegistry.addMaterialStats(aurorianstone, new HeadMaterialStats(120, 4.25f, 3.00f, IRON), new HandleMaterialStats(0.30f, -30), new ExtraMaterialStats(40), new BowMaterialStats(0.2f, 0.4f, 1f));
		TinkerRegistry.integrate(aurorianstone).toolforge().preInit();
	}

	@SideOnly(Side.CLIENT)
	public static void preInitSetMaterialRender() {
		silentwood.setRenderInfo(new MaterialRenderInfo.Metal(silentwood.materialTextColor, 0.05f, 0.15f, 0f));
		aurorianstone.setRenderInfo(new MaterialRenderInfo.Metal(aurorianstone.materialTextColor, 0.05f, 0.15f, 0f));
	}

}
