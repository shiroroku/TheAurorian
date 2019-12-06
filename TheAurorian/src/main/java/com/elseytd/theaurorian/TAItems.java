package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Items.TAItem;
import com.elseytd.theaurorian.Items.TAItem_Armor_Cerulean;
import com.elseytd.theaurorian.Items.TAItem_Armor_Knight;
import com.elseytd.theaurorian.Items.TAItem_Armor_Spiked;
import com.elseytd.theaurorian.Items.TAItem_Crafting_AurorianCoal;
import com.elseytd.theaurorian.Items.TAItem_Crafting_Crystal;
import com.elseytd.theaurorian.Items.TAItem_Crafting_Cup;
import com.elseytd.theaurorian.Items.TAItem_Crafting_Lavender;
import com.elseytd.theaurorian.Items.TAItem_Crafting_MoonTempleCellKeyFragment;
import com.elseytd.theaurorian.Items.TAItem_Crafting_Nugget;
import com.elseytd.theaurorian.Items.TAItem_Crafting_PlantFiber;
import com.elseytd.theaurorian.Items.TAItem_Crafting_SilentwoodStick;
import com.elseytd.theaurorian.Items.TAItem_Food_AurorianBacon;
import com.elseytd.theaurorian.Items.TAItem_Food_AurorianPork;
import com.elseytd.theaurorian.Items.TAItem_Food_CookedAurorianPork;
import com.elseytd.theaurorian.Items.TAItem_Food_Silkberry;
import com.elseytd.theaurorian.Items.TAItem_Food_SilkberryRasin;
import com.elseytd.theaurorian.Items.TAItem_Food_Tea;
import com.elseytd.theaurorian.Items.TAItem_Ingot_Cerulean;
import com.elseytd.theaurorian.Items.TAItem_Ingot_Moonstone;
import com.elseytd.theaurorian.Items.TAItem_Seeds;
import com.elseytd.theaurorian.Items.TAItem_Special_AbsorptionOrb;
import com.elseytd.theaurorian.Items.TAItem_Special_Aurorianite_Axe;
import com.elseytd.theaurorian.Items.TAItem_Special_Aurorianite_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Special_Aurorianite_Sword;
import com.elseytd.theaurorian.Items.TAItem_Special_Bepsi;
import com.elseytd.theaurorian.Items.TAItem_Special_Crystalline_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Special_Crystalline_Shield;
import com.elseytd.theaurorian.Items.TAItem_Special_DarkAmulet;
import com.elseytd.theaurorian.Items.TAItem_Special_DungeonKey;
import com.elseytd.theaurorian.Items.TAItem_Special_KeeperAmulet;
import com.elseytd.theaurorian.Items.TAItem_Special_LivingDiviningRod;
import com.elseytd.theaurorian.Items.TAItem_Special_Lockpicks;
import com.elseytd.theaurorian.Items.TAItem_Special_MoonTempleBreaker;
import com.elseytd.theaurorian.Items.TAItem_Special_RunestoneBreaker;
import com.elseytd.theaurorian.Items.TAItem_Special_StickySpiker;
import com.elseytd.theaurorian.Items.TAItem_Special_StrangeMeat;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Sword;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Arrow;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Bucket;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Shield;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Tool_Moonstone_Sword;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Bow;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Tool_Silentwood_Sword;

import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAItems {

	public static class Materials {
		public static Item.ToolMaterial SILENTWOOD = EnumHelper.addToolMaterial("TA_SILENTWOOD", 0, 59, 3.0F, 0.0F, 20);
		public static Item.ToolMaterial AURORIANSTONE = EnumHelper.addToolMaterial("TA_AURORIANSTONE", 1, 131, 4.5F, 1.5F, 14);
		public static Item.ToolMaterial MOONSTONE = EnumHelper.addToolMaterial("TA_MOONSTONE", 2, 250, 7.0F, 2.5F, 14);
		public static Item.ToolMaterial AURORIANITE = EnumHelper.addToolMaterial("TA_AURORIANITE", 3, 1000, 8.0F, 3.0F, 20);
		public static Item.ToolMaterial CRYSTALLINE = EnumHelper.addToolMaterial("TA_CRYSTALLINE", 2, 600, 7.5F, 2.5F, 30);
		
		public static ArmorMaterial CERULEAN_ARMOR = EnumHelper.addArmorMaterial("TA_CERULEAN_ARMOR", "theaurorian:cerulean", 20, new int[] { 3, 6, 5, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial SPIKED_ARMOR = EnumHelper.addArmorMaterial("TA_SPIKED_ARMOR", "theaurorian:spiked", 65, new int[] { 3, 6, 5, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial KNIGHT_ARMOR = EnumHelper.addArmorMaterial("TA_KNIGHT_ARMOR", "theaurorian:knight", 30, new int[] { 2, 3, 2, 1 }, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
	}

	
	// SPECIAL
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "spikedchestplate")
	public static TAItem_Armor_Spiked spikedchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Seeds.ITEMNAME_LAVENDER)
	public static TAItem_Seeds lavenderseeds;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_AbsorptionOrb.ITEMNAME)
	public static TAItem_Special_AbsorptionOrb absorptionorb;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Aurorianite_Axe.ITEMNAME)
	public static TAItem_Special_Aurorianite_Axe aurorianiteaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Aurorianite_Pickaxe.ITEMNAME)
	public static TAItem_Special_Aurorianite_Pickaxe aurorianitepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Aurorianite_Sword.ITEMNAME)
	public static TAItem_Special_Aurorianite_Sword aurorianitesword;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Crystalline_Pickaxe.ITEMNAME)
	public static TAItem_Special_Crystalline_Pickaxe crystallinepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Crystalline_Shield.ITEMNAME)
	public static TAItem_Special_Crystalline_Shield crystallineshield;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DarkAmulet.ITEMNAME)
	public static TAItem_Special_DarkAmulet darkamulet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DungeonKey.ITEMNAME_DARKSTONE)
	public static TAItem_Special_DungeonKey darkstonekey;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLECELL)
	public static TAItem_Special_DungeonKey moontemplecellkey;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLE)
	public static TAItem_Special_DungeonKey moontemplekey;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DungeonKey.ITEMNAME_RUNESTONE)
	public static TAItem_Special_DungeonKey runestonekey;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_DungeonKey.ITEMNAME_RUNESTONELOOT)
	public static TAItem_Special_DungeonKey runestonelootkey;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_KeeperAmulet.ITEMNAME)
	public static TAItem_Special_KeeperAmulet keeperamulet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_LivingDiviningRod.ITEMNAME)
	public static TAItem_Special_LivingDiviningRod livingdiviningrod;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Lockpicks.ITEMNAME)
	public static TAItem_Special_Lockpicks lockpicks;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_MoonTempleBreaker.ITEMNAME)
	public static TAItem_Special_MoonTempleBreaker moontemplebreaker;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_RunestoneBreaker.ITEMNAME)
	public static TAItem_Special_RunestoneBreaker runestonebreaker;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_StickySpiker.ITEMNAME)
	public static TAItem_Special_StickySpiker stickyspiker;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Arrow.ITEMNAME)
	public static TAItem_Tool_Cerulean_Arrow ceruleanarrow;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Bucket.ITEMNAME)
	public static TAItem_Tool_Cerulean_Bucket ceruleanbucket;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Shield.ITEMNAME)
	public static TAItem_Tool_Cerulean_Shield ceruleanshield;

	// FOODS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_AurorianBacon.ITEMNAME)
	public static TAItem_Food_AurorianBacon aurorianbacon;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_AurorianPork.ITEMNAME)
	public static TAItem_Food_AurorianPork aurorianpork;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_CookedAurorianPork.ITEMNAME)
	public static TAItem_Food_CookedAurorianPork aurorianporkcooked;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Silkberry.ITEMNAME)
	public static TAItem_Food_Silkberry silkberry;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_SilkberryRasin.ITEMNAME)
	public static TAItem_Food_SilkberryRasin silkberryrasin;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_LAVENDER)
	public static TAItem_Food_Tea tealavender;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_SEEDY)
	public static TAItem_Food_Tea teaseedy;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_SILKBERRY)
	public static TAItem_Food_Tea teasilkberry;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Bepsi.ITEMNAME)
	public static TAItem_Special_Bepsi bepsi;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_StrangeMeat.ITEMNAME)
	public static TAItem_Special_StrangeMeat strangemeat;

	// CRAFTING
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem.ITEMNAME_AURORIANSLIMEBALL)
	public static TAItem aurorianslimeball;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_AurorianCoal.ITEMNAME)
	public static TAItem_Crafting_AurorianCoal auroriancoal;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Crystal.ITEMNAME)
	public static TAItem_Crafting_Crystal crystal;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Cup.ITEMNAME)
	public static TAItem_Crafting_Cup cup;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Lavender.ITEMNAME)
	public static TAItem_Crafting_Lavender lavender;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_MoonTempleCellKeyFragment.ITEMNAME)
	public static TAItem_Crafting_MoonTempleCellKeyFragment moontemplecellkeyfragment;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Nugget.ITEMNAME_COAL)
	public static TAItem_Crafting_Nugget auroriancoalnugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Nugget.ITEMNAME_CERULEAN)
	public static TAItem_Crafting_Nugget ceruleannugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_Nugget.ITEMNAME_MOONSTONE)
	public static TAItem_Crafting_Nugget moonstonenugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_PlantFiber.ITEMNAME)
	public static TAItem_Crafting_PlantFiber plantfiber;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_SilentwoodStick.ITEMNAME)
	public static TAItem_Crafting_SilentwoodStick silentwoodstick;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Ingot_Cerulean.ITEMNAME)
	public static TAItem_Ingot_Cerulean ceruleaningot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Ingot_Moonstone.ITEMNAME)
	public static TAItem_Ingot_Moonstone moonstoneingot;

	// CERULEAN ARMOR
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanhelmet")
	public static TAItem_Armor_Cerulean ceruleanhelmet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanchestplate")
	public static TAItem_Armor_Cerulean ceruleanchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanleggings")
	public static TAItem_Armor_Cerulean ceruleanleggings;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanboots")
	public static TAItem_Armor_Cerulean ceruleanboots;

	// KNIGHT ARMOR
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knighthelmet")
	public static TAItem_Armor_Knight knighthelmet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightchestplate")
	public static TAItem_Armor_Knight knightchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightleggings")
	public static TAItem_Armor_Knight knightleggings;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightboots")
	public static TAItem_Armor_Knight knightboots;

	// MOONSTONE TOOLS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Axe.ITEMNAME)
	public static TAItem_Tool_Moonstone_Axe moonstoneaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Hoe.ITEMNAME)
	public static TAItem_Tool_Moonstone_Hoe moonstonehoe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Moonstone_Pickaxe moonstonepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Shovel.ITEMNAME)
	public static TAItem_Tool_Moonstone_Shovel moonstoneshovel;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Sickle.ITEMNAME)
	public static TAItem_Tool_Moonstone_Sickle moonstonesickle;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Moonstone_Sword.ITEMNAME)
	public static TAItem_Tool_Moonstone_Sword moonstonesword;

	// AURORIAN STONE TOOLS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Axe.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Axe aurorianstoneaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Hoe.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Hoe aurorianstonehoe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Pickaxe aurorianstonepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Shovel.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Shovel aurorianstoneshovel;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Sickle.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Sickle aurorianstonesickle;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorian_Stone_Sword.ITEMNAME)
	public static TAItem_Tool_Aurorian_Stone_Sword aurorianstonesword;

	// SILENTWOOD TOOLS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Axe.ITEMNAME)
	public static TAItem_Tool_Silentwood_Axe silentwoodaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Bow.ITEMNAME)
	public static TAItem_Tool_Silentwood_Bow silentwoodbow;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Hoe.ITEMNAME)
	public static TAItem_Tool_Silentwood_Hoe silentwoodhoe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Silentwood_Pickaxe silentwoodpickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Shovel.ITEMNAME)
	public static TAItem_Tool_Silentwood_Shovel silentwoodshovel;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Sickle.ITEMNAME)
	public static TAItem_Tool_Silentwood_Sickle silentwoodsickle;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Silentwood_Sword.ITEMNAME)
	public static TAItem_Tool_Silentwood_Sword silentwoodsword;

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		// SPECIAL
		absorptionorb.initModel();
		aurorianiteaxe.initModel();
		aurorianitepickaxe.initModel();
		aurorianitesword.initModel();
		ceruleanarrow.initModel();
		ceruleanbucket.initModel();
		ceruleanshield.initModel();
		crystallinepickaxe.initModel();
		crystallineshield.initModel();
		darkamulet.initModel();
		darkstonekey.initModel();
		keeperamulet.initModel();
		lavenderseeds.initModel();
		livingdiviningrod.initModel();
		lockpicks.initModel();
		moontemplebreaker.initModel();
		moontemplecellkey.initModel();
		moontemplekey.initModel();
		runestonebreaker.initModel();
		runestonekey.initModel();
		runestonelootkey.initModel();
		spikedchestplate.initModel();
		stickyspiker.initModel();

		// FOOD
		aurorianbacon.initModel();
		aurorianpork.initModel();
		aurorianporkcooked.initModel();
		bepsi.initModel();
		silkberry.initModel();
		silkberryrasin.initModel();
		strangemeat.initModel();
		tealavender.initModel();
		teaseedy.initModel();
		teasilkberry.initModel();

		// CRAFTING
		auroriancoal.initModel();
		auroriancoalnugget.initModel();
		aurorianslimeball.initModel();
		ceruleaningot.initModel();
		ceruleannugget.initModel();
		crystal.initModel();
		cup.initModel();
		lavender.initModel();
		moonstoneingot.initModel();
		moonstonenugget.initModel();
		moontemplecellkeyfragment.initModel();
		plantfiber.initModel();
		silentwoodstick.initModel();

		// CERULEAN ARMOR
		ceruleanhelmet.initModel();
		ceruleanchestplate.initModel();
		ceruleanleggings.initModel();
		ceruleanboots.initModel();

		// KNIGHT ARMOR
		knighthelmet.initModel();
		knightchestplate.initModel();
		knightleggings.initModel();
		knightboots.initModel();

		// MOONSTONE TOOLS
		moonstoneaxe.initModel();
		moonstonehoe.initModel();
		moonstonepickaxe.initModel();
		moonstoneshovel.initModel();
		moonstonesickle.initModel();
		moonstonesword.initModel();

		// AURORIAN STONE TOOLS
		aurorianstoneaxe.initModel();
		aurorianstonehoe.initModel();
		aurorianstonepickaxe.initModel();
		aurorianstoneshovel.initModel();
		aurorianstonesickle.initModel();
		aurorianstonesword.initModel();

		// SILENTWOOD TOOLS
		silentwoodaxe.initModel();
		silentwoodbow.initModel();
		silentwoodhoe.initModel();
		silentwoodpickaxe.initModel();
		silentwoodshovel.initModel();
		silentwoodsickle.initModel();
		silentwoodsword.initModel();

	}

	public static void registerItems(RegistryEvent.Register<Item> event) {
		// SPECIAL
		event.getRegistry().register(new TAItem_Armor_Spiked(EntityEquipmentSlot.CHEST, "spikedchestplate"));
		event.getRegistry().register(new TAItem_Seeds(TAItem_Seeds.ITEMNAME_LAVENDER));
		event.getRegistry().register(new TAItem_Special_AbsorptionOrb());
		event.getRegistry().register(new TAItem_Special_Aurorianite_Axe());
		event.getRegistry().register(new TAItem_Special_Aurorianite_Pickaxe());
		event.getRegistry().register(new TAItem_Special_Aurorianite_Sword());
		event.getRegistry().register(new TAItem_Special_Crystalline_Pickaxe());
		event.getRegistry().register(new TAItem_Special_Crystalline_Shield());
		event.getRegistry().register(new TAItem_Special_DarkAmulet());
		event.getRegistry().register(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.ITEMNAME_DARKSTONE));
		event.getRegistry().register(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLE));
		event.getRegistry().register(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLECELL));
		event.getRegistry().register(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.ITEMNAME_RUNESTONE));
		event.getRegistry().register(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.ITEMNAME_RUNESTONELOOT));
		event.getRegistry().register(new TAItem_Special_KeeperAmulet());
		event.getRegistry().register(new TAItem_Special_LivingDiviningRod());
		event.getRegistry().register(new TAItem_Special_Lockpicks());
		event.getRegistry().register(new TAItem_Special_MoonTempleBreaker());
		event.getRegistry().register(new TAItem_Special_RunestoneBreaker());
		event.getRegistry().register(new TAItem_Special_StickySpiker());
		event.getRegistry().register(new TAItem_Special_StrangeMeat());
		event.getRegistry().register(new TAItem_Tool_Cerulean_Arrow());
		event.getRegistry().register(new TAItem_Tool_Cerulean_Bucket());
		event.getRegistry().register(new TAItem_Tool_Cerulean_Shield());

		// FOODS
		event.getRegistry().register(new TAItem_Food_AurorianBacon());
		event.getRegistry().register(new TAItem_Food_AurorianPork());
		event.getRegistry().register(new TAItem_Food_CookedAurorianPork());
		event.getRegistry().register(new TAItem_Food_Silkberry());
		event.getRegistry().register(new TAItem_Food_SilkberryRasin());
		event.getRegistry().register(new TAItem_Food_Tea(TAItem_Food_Tea.ITEMNAME_LAVENDER));
		event.getRegistry().register(new TAItem_Food_Tea(TAItem_Food_Tea.ITEMNAME_SEEDY));
		event.getRegistry().register(new TAItem_Food_Tea(TAItem_Food_Tea.ITEMNAME_SILKBERRY));
		event.getRegistry().register(new TAItem_Special_Bepsi());

		// CRAFTING
		event.getRegistry().register(new TAItem(TAItem.ITEMNAME_AURORIANSLIMEBALL));
		event.getRegistry().register(new TAItem_Crafting_AurorianCoal());
		event.getRegistry().register(new TAItem_Crafting_Crystal());
		event.getRegistry().register(new TAItem_Crafting_Cup());
		event.getRegistry().register(new TAItem_Crafting_Lavender());
		event.getRegistry().register(new TAItem_Crafting_MoonTempleCellKeyFragment());
		event.getRegistry().register(new TAItem_Crafting_Nugget(TAItem_Crafting_Nugget.ITEMNAME_CERULEAN));
		event.getRegistry().register(new TAItem_Crafting_Nugget(TAItem_Crafting_Nugget.ITEMNAME_COAL));
		event.getRegistry().register(new TAItem_Crafting_Nugget(TAItem_Crafting_Nugget.ITEMNAME_MOONSTONE));
		event.getRegistry().register(new TAItem_Crafting_PlantFiber());
		event.getRegistry().register(new TAItem_Crafting_SilentwoodStick());
		event.getRegistry().register(new TAItem_Ingot_Cerulean());
		event.getRegistry().register(new TAItem_Ingot_Moonstone());

		// CERULEAN ARMOR
		event.getRegistry().register(new TAItem_Armor_Cerulean(EntityEquipmentSlot.HEAD, "ceruleanhelmet"));
		event.getRegistry().register(new TAItem_Armor_Cerulean(EntityEquipmentSlot.CHEST, "ceruleanchestplate"));
		event.getRegistry().register(new TAItem_Armor_Cerulean(EntityEquipmentSlot.LEGS, "ceruleanleggings"));
		event.getRegistry().register(new TAItem_Armor_Cerulean(EntityEquipmentSlot.FEET, "ceruleanboots"));

		// KNIGHT ARMOR
		event.getRegistry().register(new TAItem_Armor_Knight(EntityEquipmentSlot.HEAD, "knighthelmet"));
		event.getRegistry().register(new TAItem_Armor_Knight(EntityEquipmentSlot.CHEST, "knightchestplate"));
		event.getRegistry().register(new TAItem_Armor_Knight(EntityEquipmentSlot.LEGS, "knightleggings"));
		event.getRegistry().register(new TAItem_Armor_Knight(EntityEquipmentSlot.FEET, "knightboots"));

		// MOONSTONE STONE TOOLS
		event.getRegistry().register(new TAItem_Tool_Moonstone_Axe());
		event.getRegistry().register(new TAItem_Tool_Moonstone_Hoe());
		event.getRegistry().register(new TAItem_Tool_Moonstone_Pickaxe());
		event.getRegistry().register(new TAItem_Tool_Moonstone_Shovel());
		event.getRegistry().register(new TAItem_Tool_Moonstone_Sickle());
		event.getRegistry().register(new TAItem_Tool_Moonstone_Sword());

		// AURORIAN STONE TOOLS
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Axe());
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Hoe());
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Pickaxe());
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Shovel());
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Sickle());
		event.getRegistry().register(new TAItem_Tool_Aurorian_Stone_Sword());

		// SILENTWOOD TOOLS
		event.getRegistry().register(new TAItem_Tool_Silentwood_Axe());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Bow());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Hoe());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Pickaxe());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Shovel());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Sickle());
		event.getRegistry().register(new TAItem_Tool_Silentwood_Sword());
	}
}
