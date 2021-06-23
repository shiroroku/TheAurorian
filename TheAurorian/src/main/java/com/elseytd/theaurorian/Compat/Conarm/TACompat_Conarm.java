package com.elseytd.theaurorian.Compat.Conarm;

import c4.conarm.common.armor.traits.ArmorTraits;
import c4.conarm.lib.materials.ArmorMaterialType;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import c4.conarm.lib.traits.AbstractArmorTrait;
import com.elseytd.theaurorian.Compat.TinkersConstruct.TACompat_Tinkers;
import slimeknights.tconstruct.library.TinkerRegistry;

public class TACompat_Conarm {

	public static final AbstractArmorTrait traitmoonlit = new TAConarmTrait_Moonlit();

	public static void preinitArmorTraits() {
		TACompat_Tinkers.cerulean.addTrait(ArmorTraits.featherweight, ArmorMaterialType.CORE);
		TACompat_Tinkers.cerulean.addTrait(ArmorTraits.lightweight, ArmorMaterialType.PLATES);
		TACompat_Tinkers.cerulean.addTrait(ArmorTraits.lightweight, ArmorMaterialType.TRIM);

		TACompat_Tinkers.moonstone.addTrait(traitmoonlit, ArmorMaterialType.CORE);
		TACompat_Tinkers.moonstone.addTrait(traitmoonlit, ArmorMaterialType.PLATES);
		TACompat_Tinkers.moonstone.addTrait(traitmoonlit, ArmorMaterialType.TRIM);

		TACompat_Tinkers.auroriansteel.addTrait(ArmorTraits.dramatic, ArmorMaterialType.CORE);
		TACompat_Tinkers.auroriansteel.addTrait(ArmorTraits.prideful, ArmorMaterialType.PLATES);
		TACompat_Tinkers.auroriansteel.addTrait(ArmorTraits.duritae, ArmorMaterialType.TRIM);

		TACompat_Tinkers.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.CORE);
		TACompat_Tinkers.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.PLATES);
		TACompat_Tinkers.silentwood.addTrait(ArmorTraits.ecological, ArmorMaterialType.TRIM);

		TACompat_Tinkers.aurorianstone.addTrait(ArmorTraits.cheapskate, ArmorMaterialType.CORE);
		TACompat_Tinkers.aurorianstone.addTrait(ArmorTraits.cheap, ArmorMaterialType.PLATES);
		TACompat_Tinkers.aurorianstone.addTrait(ArmorTraits.cheap, ArmorMaterialType.TRIM);

	}

	public static void initArmorMaterials() {
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.cerulean, new CoreMaterialStats(14f, 15));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.cerulean, new PlatesMaterialStats(1f, 7f, 0));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.cerulean, new TrimMaterialStats(4f));

		TinkerRegistry.addMaterialStats(TACompat_Tinkers.moonstone, new CoreMaterialStats(15f, 7));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.moonstone, new PlatesMaterialStats(1.2f, -3.5f, 1));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.moonstone, new TrimMaterialStats(8f));

		TinkerRegistry.addMaterialStats(TACompat_Tinkers.auroriansteel, new CoreMaterialStats(20f, 22));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.auroriansteel, new PlatesMaterialStats(2f, -5f, 3));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.auroriansteel, new TrimMaterialStats(4f));

		TinkerRegistry.addMaterialStats(TACompat_Tinkers.silentwood, new CoreMaterialStats(2.5f, 3));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.silentwood, new PlatesMaterialStats(1f, 1f, 0));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.silentwood, new TrimMaterialStats(0.5f));

		TinkerRegistry.addMaterialStats(TACompat_Tinkers.aurorianstone, new CoreMaterialStats(8.7f, 5));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.aurorianstone, new PlatesMaterialStats(0.5f, -3.5f, 0));
		TinkerRegistry.addMaterialStats(TACompat_Tinkers.aurorianstone, new TrimMaterialStats(0.75f));
	}
}
