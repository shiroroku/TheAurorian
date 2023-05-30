package com.shiroroku.theaurorian.Compat.Conarm;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import c4.conarm.lib.traits.AbstractArmorTrait;
import com.shiroroku.theaurorian.Compat.TinkersConstruct.TinkersConstructCompat;
import slimeknights.tconstruct.library.TinkerRegistry;

public class ConstructsArmoryCompat {

	public static final AbstractArmorTrait traitmoonlit = new MoonlitArmorTrait();

	public static void preinitArmorTraits() {
		TinkersConstructCompat.cerulean.addTrait(ArmorTraits.featherweight, ArmorMaterialType.CORE);
		TinkersConstructCompat.cerulean.addTrait(ArmorTraits.lightweight, ArmorMaterialType.PLATES);
		TinkersConstructCompat.cerulean.addTrait(ArmorTraits.lightweight, ArmorMaterialType.TRIM);

		TinkersConstructCompat.moonstone.addTrait(traitmoonlit, ArmorMaterialType.CORE);
		TinkersConstructCompat.moonstone.addTrait(traitmoonlit, ArmorMaterialType.PLATES);
		TinkersConstructCompat.moonstone.addTrait(traitmoonlit, ArmorMaterialType.TRIM);

		TinkersConstructCompat.auroriansteel.addTrait(ArmorTraits.dramatic, ArmorMaterialType.CORE);
		TinkersConstructCompat.auroriansteel.addTrait(ArmorTraits.prideful, ArmorMaterialType.PLATES);
		TinkersConstructCompat.auroriansteel.addTrait(ArmorTraits.duritae, ArmorMaterialType.TRIM);

		TinkersConstructCompat.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.CORE);
		TinkersConstructCompat.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.PLATES);
		TinkersConstructCompat.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.TRIM);

		TinkersConstructCompat.aurorianstone.addTrait(ArmorTraits.cheapskate, ArmorMaterialType.CORE);
		TinkersConstructCompat.aurorianstone.addTrait(ArmorTraits.cheap, ArmorMaterialType.PLATES);
		TinkersConstructCompat.aurorianstone.addTrait(ArmorTraits.cheap, ArmorMaterialType.TRIM);

	}

	public static void initArmorMaterials() {
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.cerulean, new CoreMaterialStats(14f, 15));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.cerulean, new PlatesMaterialStats(1f, 7f, 0));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.cerulean, new TrimMaterialStats(4f));

		TinkerRegistry.addMaterialStats(TinkersConstructCompat.moonstone, new CoreMaterialStats(15f, 7));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.moonstone, new PlatesMaterialStats(1.2f, -3.5f, 1));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.moonstone, new TrimMaterialStats(8f));

		TinkerRegistry.addMaterialStats(TinkersConstructCompat.auroriansteel, new CoreMaterialStats(20f, 22));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.auroriansteel, new PlatesMaterialStats(2f, -5f, 3));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.auroriansteel, new TrimMaterialStats(4f));

		TinkerRegistry.addMaterialStats(TinkersConstructCompat.silentwood, new CoreMaterialStats(2.5f, 3));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.silentwood, new PlatesMaterialStats(1f, 1f, 0));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.silentwood, new TrimMaterialStats(0.5f));

		TinkerRegistry.addMaterialStats(TinkersConstructCompat.aurorianstone, new CoreMaterialStats(8.7f, 5));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.aurorianstone, new PlatesMaterialStats(0.5f, -3.5f, 0));
		TinkerRegistry.addMaterialStats(TinkersConstructCompat.aurorianstone, new TrimMaterialStats(0.75f));
	}
}
