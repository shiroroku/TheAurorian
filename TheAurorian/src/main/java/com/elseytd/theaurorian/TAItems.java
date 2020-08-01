package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Items.TAItem_Armor_AurorianSteel;
import com.elseytd.theaurorian.Items.TAItem_Armor_Cerulean;
import com.elseytd.theaurorian.Items.TAItem_Armor_Knight;
import com.elseytd.theaurorian.Items.TAItem_Armor_SlimeBoots;
import com.elseytd.theaurorian.Items.TAItem_Armor_Spiked;
import com.elseytd.theaurorian.Items.TAItem_Basic;
import com.elseytd.theaurorian.Items.TAItem_Crafting_SilentwoodStick;
import com.elseytd.theaurorian.Items.TAItem_CrystallineSprite;
import com.elseytd.theaurorian.Items.TAItem_Debug;
import com.elseytd.theaurorian.Items.TAItem_Food;
import com.elseytd.theaurorian.Items.TAItem_Food_Silkberry;
import com.elseytd.theaurorian.Items.TAItem_Food_Tea;
import com.elseytd.theaurorian.Items.TAItem_Seeds;
import com.elseytd.theaurorian.Items.TAItem_Special_AbsorptionOrb;
import com.elseytd.theaurorian.Items.TAItem_Special_Amulet;
import com.elseytd.theaurorian.Items.TAItem_Special_Bepsi;
import com.elseytd.theaurorian.Items.TAItem_Special_Crystal;
import com.elseytd.theaurorian.Items.TAItem_Special_DungeonKey;
import com.elseytd.theaurorian.Items.TAItem_Special_KeepersBow;
import com.elseytd.theaurorian.Items.TAItem_Special_LivingDiviningRod;
import com.elseytd.theaurorian.Items.TAItem_Special_Locator;
import com.elseytd.theaurorian.Items.TAItem_Special_Lockpicks;
import com.elseytd.theaurorian.Items.TAItem_Special_MoonShield;
import com.elseytd.theaurorian.Items.TAItem_Special_StickySpiker;
import com.elseytd.theaurorian.Items.TAItem_Special_StrangeMeat;
import com.elseytd.theaurorian.Items.TAItem_Special_Webbing;
import com.elseytd.theaurorian.Items.TAItem_Special_WeepingWillowSap;
import com.elseytd.theaurorian.Items.TAItem_Tool_AurorianSteel_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_AurorianSteel_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Tool_AurorianSteel_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_AurorianSteel_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Tool_AurorianSteel_Sword;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorian_Stone_Sword;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorianite_Axe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorianite_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Aurorianite_Sword;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Arrow;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Bucket;
import com.elseytd.theaurorian.Items.TAItem_Tool_Cerulean_Shield;
import com.elseytd.theaurorian.Items.TAItem_Tool_Crystalline_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Crystalline_Shield;
import com.elseytd.theaurorian.Items.TAItem_Tool_Crystalline_Sword;
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
import com.elseytd.theaurorian.Items.TAItem_Tool_Umbra_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Tool_Umbra_Shield;
import com.elseytd.theaurorian.Items.TAItem_Tool_Umbra_Sword;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class TAItems {

	public static class Materials {
		public static Item.ToolMaterial SILENTWOOD = EnumHelper.addToolMaterial("TA_SILENTWOOD", TAConfig.Config_Silentwood_HarvestLevel, (int) (59 * TAConfig.Config_Silentwood_Multiplier_Durability), 3.0F * TAConfig.Config_Silentwood_Multiplier_Speed, 0, 20);
		public static Item.ToolMaterial AURORIANSTONE = EnumHelper.addToolMaterial("TA_AURORIANSTONE", TAConfig.Config_AurorianStone_HarvestLevel, (int) (131 * TAConfig.Config_AurorianStone_Multiplier_Durability), 4.5F * TAConfig.Config_AurorianStone_Multiplier_Speed, 1.5F * TAConfig.Config_AurorianStone_Multiplier_Damage, 14);
		public static Item.ToolMaterial MOONSTONE = EnumHelper.addToolMaterial("TA_MOONSTONE", TAConfig.Config_Moonstone_HarvestLevel, (int) (250 * TAConfig.Config_Moonstone_Multiplier_Durability), 7.0F * TAConfig.Config_Moonstone_Multiplier_Speed, 2.5F * TAConfig.Config_Moonstone_Multiplier_Damage, 14);
		public static Item.ToolMaterial AURORIANITE = EnumHelper.addToolMaterial("TA_AURORIANITE", TAConfig.Config_Special_HarvestLevel, (int) (1000 * TAConfig.Config_Special_Multiplier_Durability), 8.0F * TAConfig.Config_Special_Multiplier_Speed, 3.0F * TAConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial UMBRA = EnumHelper.addToolMaterial("TA_UMBRA", TAConfig.Config_Special_HarvestLevel, (int) (1000 * TAConfig.Config_Special_Multiplier_Durability), 8.0F * TAConfig.Config_Special_Multiplier_Speed, 3.0F * TAConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial CRYSTALLINE = EnumHelper.addToolMaterial("TA_CRYSTALLINE", TAConfig.Config_Special_HarvestLevel, (int) (1000 * TAConfig.Config_Special_Multiplier_Durability), 8.0F * TAConfig.Config_Special_Multiplier_Speed, 3.0F * TAConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial AURORIANSTEEL = EnumHelper.addToolMaterial("TA_AURORIANSTEEL", TAConfig.Config_AurorianSteel_HarvestLevel, (int) (1500 * TAConfig.Config_AurorianSteel_Multiplier_Durability), 8.5F * TAConfig.Config_AurorianSteel_Multiplier_Speed, 3.5F * TAConfig.Config_AurorianSteel_Multiplier_Damage, 10);

		public static ArmorMaterial CERULEAN_ARMOR = EnumHelper.addArmorMaterial("TA_CERULEAN_ARMOR", "theaurorian:cerulean", (int) (20 * TAConfig.Config_Cerulean_Multiplier_Durability), new int[] { (int) (3 * TAConfig.Config_Cerulean_Multiplier_Armor), (int) (6 * TAConfig.Config_Cerulean_Multiplier_Armor), (int) (5 * TAConfig.Config_Cerulean_Multiplier_Armor), (int) (3 * TAConfig.Config_Cerulean_Multiplier_Armor) }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial AURORIANSTEEL_ARMOR = EnumHelper.addArmorMaterial("TA_AURORIANSTEEL_ARMOR", "theaurorian:auroriansteelarmor", (int) (33 * TAConfig.Config_AurorianSteel_Multiplier_Durability), new int[] { (int) (4 * TAConfig.Config_AurorianSteel_Multiplier_Armor), (int) (7 * TAConfig.Config_AurorianSteel_Multiplier_Armor), (int) (8 * TAConfig.Config_AurorianSteel_Multiplier_Armor), (int) (4 * TAConfig.Config_AurorianSteel_Multiplier_Armor) }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);
		public static ArmorMaterial SPIKED_ARMOR = EnumHelper.addArmorMaterial("TA_SPIKED_ARMOR", "theaurorian:spiked", (int) (65 * TAConfig.Config_Special_Multiplier_Durability), new int[] { 3, 6, 5, 3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial KNIGHT_ARMOR = EnumHelper.addArmorMaterial("TA_KNIGHT_ARMOR", "theaurorian:knight", 30, new int[] { 2, 3, 2, 1 }, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		public static ArmorMaterial AURORIAN_SLIME = EnumHelper.addArmorMaterial("TA_AURORIAN_SLIME", "theaurorian:aurorianslime", (int) (120 * TAConfig.Config_Special_Multiplier_Durability), new int[] { 1, 2, 3, 1 }, 20, SoundEvents.ENTITY_SLIME_SQUISH, 0);

		public static void initRepairMaterials() {
			SILENTWOOD.setRepairItem(new ItemStack(TABlocks.silentwoodplanks));
			AURORIANSTONE.setRepairItem(new ItemStack(TABlocks.auroriancobblestone));
			MOONSTONE.setRepairItem(new ItemStack(TAItems.moonstoneingot));
			AURORIANITE.setRepairItem(new ItemStack(TAItems.aurorianiteingot));
			CRYSTALLINE.setRepairItem(new ItemStack(TAItems.crystallineingot));
			UMBRA.setRepairItem(new ItemStack(TAItems.umbraingot));
			AURORIANSTEEL.setRepairItem(new ItemStack(TAItems.auroriansteel));

			CERULEAN_ARMOR.setRepairItem(new ItemStack(TAItems.ceruleaningot));
			SPIKED_ARMOR.setRepairItem(new ItemStack(TAItems.umbraingot));
			AURORIAN_SLIME.setRepairItem(new ItemStack(TAItems.aurorianslimeball));
			AURORIANSTEEL_ARMOR.setRepairItem(new ItemStack(TAItems.auroriansteel));
		}
	}

	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Locator.ITEMNAME)
	public static TAItem_Special_Locator locator;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Umbra_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Umbra_Pickaxe umbrapickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Umbra_Sword.ITEMNAME)
	public static TAItem_Tool_Umbra_Sword umbrasword;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Umbra_Shield.ITEMNAME)
	public static TAItem_Tool_Umbra_Shield umbrashield;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_MoonShield.ITEMNAME)
	public static TAItem_Special_MoonShield moonshield;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "spikedchestplate")
	public static TAItem_Armor_Spiked spikedchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "aurorianslimeboots")
	public static TAItem_Armor_SlimeBoots aurorianslimeboots;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Seeds.ITEMNAME_LAVENDER)
	public static TAItem_Seeds lavenderseeds;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_AbsorptionOrb.ITEMNAME)
	public static TAItem_Special_AbsorptionOrb absorptionorb;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorianite_Axe.ITEMNAME)
	public static TAItem_Tool_Aurorianite_Axe aurorianiteaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorianite_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Aurorianite_Pickaxe aurorianitepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Aurorianite_Sword.ITEMNAME)
	public static TAItem_Tool_Aurorianite_Sword aurorianitesword;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Crystalline_Pickaxe.ITEMNAME)
	public static TAItem_Tool_Crystalline_Pickaxe crystallinepickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Crystalline_Shield.ITEMNAME)
	public static TAItem_Tool_Crystalline_Shield crystallineshield;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Crystalline_Sword.ITEMNAME)
	public static TAItem_Tool_Crystalline_Sword crystallinesword;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_CrystallineSprite.ITEMNAME)
	public static TAItem_CrystallineSprite crystallinesprite;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Amulet.ITEMNAME_DARKAMULET)
	public static TAItem_Special_Amulet darkamulet;
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
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Amulet.ITEMNAME_KEEPERAMULET)
	public static TAItem_Special_Amulet keeperamulet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_LivingDiviningRod.ITEMNAME)
	public static TAItem_Special_LivingDiviningRod livingdiviningrod;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Lockpicks.ITEMNAME)
	public static TAItem_Special_Lockpicks lockpicks;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_StickySpiker.ITEMNAME)
	public static TAItem_Special_StickySpiker stickyspiker;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Arrow.ITEMNAME)
	public static TAItem_Tool_Cerulean_Arrow ceruleanarrow;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Bucket.ITEMNAME)
	public static TAItem_Tool_Cerulean_Bucket ceruleanbucket;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_Cerulean_Shield.ITEMNAME)
	public static TAItem_Tool_Cerulean_Shield ceruleanshield;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Debug.ITEMNAME)
	public static TAItem_Debug debugger;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_AURORIANBACON)
	public static TAItem_Food aurorianbacon;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_AURORIANPORK)
	public static TAItem_Food aurorianpork;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_COOKEDAURORIANPORK)
	public static TAItem_Food aurorianporkcooked;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Silkberry.ITEMNAME)
	public static TAItem_Food_Silkberry silkberry;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_SILKBERRYJAM)
	public static TAItem_Food silkberryjam;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_SILKBERRYJAMSANDWICH)
	public static TAItem_Food silkberryjamsandwich;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_LAVENDERBREAD)
	public static TAItem_Food lavenderbread;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_LAVENDER)
	public static TAItem_Food_Tea tealavender;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_PETUNIA)
	public static TAItem_Food_Tea teapetunia;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_SEEDY)
	public static TAItem_Food_Tea teaseedy;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food_Tea.ITEMNAME_SILKBERRY)
	public static TAItem_Food_Tea teasilkberry;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Bepsi.ITEMNAME)
	public static TAItem_Special_Bepsi bepsi;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_StrangeMeat.ITEMNAME)
	public static TAItem_Special_StrangeMeat strangemeat;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_AURORIANSLIMEBALL)
	public static TAItem_Food aurorianslimeball;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_AURORIANCOAL)
	public static TAItem_Basic auroriancoal;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Crystal.ITEMNAME)
	public static TAItem_Special_Crystal crystal;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_CUP)
	public static TAItem_Basic cup;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_LAVENDER)
	public static TAItem_Basic lavender;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_MOONTEMPLECELLKEYFRAGMENT)
	public static TAItem_Basic moontemplecellkeyfragment;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_NUGGET_AURORIANCOAL)
	public static TAItem_Basic auroriancoalnugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_NUGGET_CERULEAN)
	public static TAItem_Basic ceruleannugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_NUGGET_MOONSTONE)
	public static TAItem_Basic moonstonenugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_NUGGET_AURORIANSTEEL)
	public static TAItem_Basic auroriansteelnugget;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_PLANTFIBER)
	public static TAItem_Basic plantfiber;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Crafting_SilentwoodStick.ITEMNAME)
	public static TAItem_Crafting_SilentwoodStick silentwoodstick;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_CERULEAN)
	public static TAItem_Basic ceruleaningot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_MOONSTONE)
	public static TAItem_Basic moonstoneingot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_SCRAP_AURORIANITE)
	public static TAItem_Basic scrapaurorianite;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_SCRAP_CRYSTALLINE)
	public static TAItem_Basic scrapcrystalline;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_SCRAP_UMBRA)
	public static TAItem_Basic scrapumbra;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_AURORIANITE)
	public static TAItem_Basic aurorianiteingot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_CRYSTALLINE)
	public static TAItem_Basic crystallineingot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_UMBRA)
	public static TAItem_Basic umbraingot;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Basic.ITEMNAME_INGOT_AURORIANSTEEL)
	public static TAItem_Basic auroriansteel;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "auroriansteelhelmet")
	public static TAItem_Armor_AurorianSteel auroriansteelhelmet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "auroriansteelchestplate")
	public static TAItem_Armor_AurorianSteel auroriansteelchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "auroriansteelleggings")
	public static TAItem_Armor_AurorianSteel auroriansteelleggings;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "auroriansteelboots")
	public static TAItem_Armor_AurorianSteel auroriansteelboots;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanhelmet")
	public static TAItem_Armor_Cerulean ceruleanhelmet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanchestplate")
	public static TAItem_Armor_Cerulean ceruleanchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanleggings")
	public static TAItem_Armor_Cerulean ceruleanleggings;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "ceruleanboots")
	public static TAItem_Armor_Cerulean ceruleanboots;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knighthelmet")
	public static TAItem_Armor_Knight knighthelmet;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightchestplate")
	public static TAItem_Armor_Knight knightchestplate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightleggings")
	public static TAItem_Armor_Knight knightleggings;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + "knightboots")
	public static TAItem_Armor_Knight knightboots;
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
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_AurorianSteel_Sword.ITEMNAME)
	public static TAItem_Tool_AurorianSteel_Sword auroriansteelsword;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_AurorianSteel_Axe.ITEMNAME)
	public static TAItem_Tool_AurorianSteel_Axe auroriansteelaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_AurorianSteel_Pickaxe.ITEMNAME)
	public static TAItem_Tool_AurorianSteel_Pickaxe auroriansteelpickaxe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_AurorianSteel_Hoe.ITEMNAME)
	public static TAItem_Tool_AurorianSteel_Hoe auroriansteelhoe;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Tool_AurorianSteel_Shovel.ITEMNAME)
	public static TAItem_Tool_AurorianSteel_Shovel auroriansteelshovel;
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
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Food.ITEMNAME_SILKSHROOMSTEW)
	public static TAItem_Food silkshroomstew;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_WeepingWillowSap.ITEMNAME)
	public static TAItem_Special_WeepingWillowSap weepingwillowsap;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_Webbing.ITEMNAME)
	public static TAItem_Special_Webbing webbing;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAItem_Special_KeepersBow.ITEMNAME)
	public static TAItem_Special_KeepersBow keepersbow;

	private enum TAItemRegistry {
		ABSORPTIONORB(new TAItem_Special_AbsorptionOrb()),
		AMULETDARK(new TAItem_Special_Amulet(TAItem_Special_Amulet.Amulets.DARKAMULET)),
		AMULETKEEPER(new TAItem_Special_Amulet(TAItem_Special_Amulet.Amulets.KEEPERAMULET)),
		AURORIANBACON(new TAItem_Food(TAItem_Food.Foods.AURORIANBACON)),
		AURORIANCOAL(new TAItem_Basic(TAItem_Basic.Items.AURORIANCOAL)),
		AURORIANCOALNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_AURORIANCOAL)),
		AURORIANITEAXE(new TAItem_Tool_Aurorianite_Axe()),
		AURORIANITEPICKAXE(new TAItem_Tool_Aurorianite_Pickaxe()),
		AURORIANITESWORD(new TAItem_Tool_Aurorianite_Sword()),
		AURORIANPORK(new TAItem_Food(TAItem_Food.Foods.AURORIANPORK)),
		AURORIANPORKCOOKED(new TAItem_Food(TAItem_Food.Foods.COOKEDAURORIANPORK)),
		AURORIANSLIMEBALL(new TAItem_Food(TAItem_Food.Foods.AURORIANSLIMEBALL)),
		AURORIANSLIMEBOOTS(new TAItem_Armor_SlimeBoots(EntityEquipmentSlot.FEET, "aurorianslimeboots")),
		AURORIANSTEELARMORBOOTS(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.FEET, "auroriansteelboots")),
		AURORIANSTEELARMORCHESTPLATE(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.CHEST, "auroriansteelchestplate")),
		AURORIANSTEELARMORHELMET(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.HEAD, "auroriansteelhelmet")),
		AURORIANSTEELARMORLEGGINGS(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.LEGS, "auroriansteelleggings")),
		AURORIANSTEELAXE(new TAItem_Tool_AurorianSteel_Axe()),
		AURORIANSTEELHOE(new TAItem_Tool_AurorianSteel_Hoe()),
		AURORIANSTEELNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_AURORIANSTEEL)),
		AURORIANSTEELPICKAXE(new TAItem_Tool_AurorianSteel_Pickaxe()),
		AURORIANSTEELSHOVEL(new TAItem_Tool_AurorianSteel_Shovel()),
		AURORIANSTEELSWORD(new TAItem_Tool_AurorianSteel_Sword()),
		AURORIANSTONEAXE(new TAItem_Tool_Aurorian_Stone_Axe()),
		AURORIANSTONEHOE(new TAItem_Tool_Aurorian_Stone_Hoe()),
		AURORIANSTONEPICKAXE(new TAItem_Tool_Aurorian_Stone_Pickaxe()),
		AURORIANSTONESHOVEL(new TAItem_Tool_Aurorian_Stone_Shovel()),
		AURORIANSTONESICKLE(new TAItem_Tool_Aurorian_Stone_Sickle()),
		AURORIANSTONESWORD(new TAItem_Tool_Aurorian_Stone_Sword()),
		BEPSI(new TAItem_Special_Bepsi()),
		CERULEANARMORBOOTS(new TAItem_Armor_Cerulean(EntityEquipmentSlot.FEET, "ceruleanboots")),
		CERULEANARMORCHESTPLATE(new TAItem_Armor_Cerulean(EntityEquipmentSlot.CHEST, "ceruleanchestplate")),
		CERULEANARMORHELMET(new TAItem_Armor_Cerulean(EntityEquipmentSlot.HEAD, "ceruleanhelmet")),
		CERULEANARMORLEGGINGS(new TAItem_Armor_Cerulean(EntityEquipmentSlot.LEGS, "ceruleanleggings")),
		CERULEANARROW(new TAItem_Tool_Cerulean_Arrow()),
		CERULEANBUCKET(new TAItem_Tool_Cerulean_Bucket()),
		CERULEANNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_CERULEAN)),
		CERULEANSHIELD(new TAItem_Tool_Cerulean_Shield()),
		CRYSTAL(new TAItem_Special_Crystal()),
		CRYSTALLINEPICKAXE(new TAItem_Tool_Crystalline_Pickaxe()),
		CRYSTALLINESHIELD(new TAItem_Tool_Crystalline_Shield()),
		CRYSTALLINESPRITE(new TAItem_CrystallineSprite()),
		CRYSTALLINESWORD(new TAItem_Tool_Crystalline_Sword()),
		CUP(new TAItem_Basic(TAItem_Basic.Items.CUP)),
		DEBUGGER(new TAItem_Debug()),
		INGOTAURORIANITE(new TAItem_Basic(TAItem_Basic.Items.INGOT_AURORIANITE)),
		INGOTAURORIANSTEEL(new TAItem_Basic(TAItem_Basic.Items.INGOT_AURORIANSTEEL)),
		INGOTCERULEAN(new TAItem_Basic(TAItem_Basic.Items.INGOT_CERULEAN)),
		INGOTCRYSTALLINE(new TAItem_Basic(TAItem_Basic.Items.INGOT_CRYSTALLINE)),
		INGOTMOONSTONE(new TAItem_Basic(TAItem_Basic.Items.INGOT_MOONSTONE)),
		INGOTUMBRA(new TAItem_Basic(TAItem_Basic.Items.INGOT_UMBRA)),
		KEEPERSBOW(new TAItem_Special_KeepersBow()),
		KEYDARKSTONE(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.Keys.DARKSTONE)),
		KEYMOONTEMPLE(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.Keys.MOONTEMPLE)),
		KEYMOONTEMPLECELL(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.Keys.MOONTEMPLECELL)),
		KEYRUNESTONE(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.Keys.RUNESTONE)),
		KEYRUNESTONELOOT(new TAItem_Special_DungeonKey(TAItem_Special_DungeonKey.Keys.RUNESTONELOOT)),
		KNIGHTARMORBOOTS(new TAItem_Armor_Knight(EntityEquipmentSlot.FEET, "knightboots")),
		KNIGHTARMORCHESTPLATE(new TAItem_Armor_Knight(EntityEquipmentSlot.CHEST, "knightchestplate")),
		KNIGHTARMORHELMET(new TAItem_Armor_Knight(EntityEquipmentSlot.HEAD, "knighthelmet")),
		KNIGHTARMORLEGGINGS(new TAItem_Armor_Knight(EntityEquipmentSlot.LEGS, "knightleggings")),
		LAVENDER(new TAItem_Basic(TAItem_Basic.Items.LAVENDER)),
		LAVENDERBREAD(new TAItem_Food(TAItem_Food.Foods.LAVENDERBREAD)),
		LIVINGDIVININGROD(new TAItem_Special_LivingDiviningRod()),
		LOCATOR(new TAItem_Special_Locator()),
		LOCKPICKS(new TAItem_Special_Lockpicks()),
		MOONSHIELD(new TAItem_Special_MoonShield()),
		MOONSTONEAXE(new TAItem_Tool_Moonstone_Axe()),
		MOONSTONEHOE(new TAItem_Tool_Moonstone_Hoe()),
		MOONSTONENUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_MOONSTONE)),
		MOONSTONEPICKAXE(new TAItem_Tool_Moonstone_Pickaxe()),
		MOONSTONESHOVEL(new TAItem_Tool_Moonstone_Shovel()),
		MOONSTONESICKLE(new TAItem_Tool_Moonstone_Sickle()),
		MOONSTONESWORD(new TAItem_Tool_Moonstone_Sword()),
		MOONTEMPLECELLKEYFRAGMENT(new TAItem_Basic(TAItem_Basic.Items.MOONTEMPLECELLKEYFRAGMENT)),
		PLANTFIBER(new TAItem_Basic(TAItem_Basic.Items.PLANTFIBER)),
		SCRAPAURORIANITE(new TAItem_Basic(TAItem_Basic.Items.SCRAP_AURORIANITE)),
		SCRAPCRYSTALLINE(new TAItem_Basic(TAItem_Basic.Items.SCRAP_CRYSTALLINE)),
		SCRAPUMBRA(new TAItem_Basic(TAItem_Basic.Items.SCRAP_UMBRA)),
		SEEDSLAVENDER(new TAItem_Seeds(TAItem_Seeds.ITEMNAME_LAVENDER)),
		SILENTWOODAXE(new TAItem_Tool_Silentwood_Axe()),
		SILENTWOODBOW(new TAItem_Tool_Silentwood_Bow()),
		SILENTWOODHOE(new TAItem_Tool_Silentwood_Hoe()),
		SILENTWOODPICKAXE(new TAItem_Tool_Silentwood_Pickaxe()),
		SILENTWOODSHOVEL(new TAItem_Tool_Silentwood_Shovel()),
		SILENTWOODSICKLE(new TAItem_Tool_Silentwood_Sickle()),
		SILENTWOODSTICK(new TAItem_Crafting_SilentwoodStick()),
		SILENTWOODSWORD(new TAItem_Tool_Silentwood_Sword()),
		SILKBERRY(new TAItem_Food_Silkberry()),
		SILKBERRYJAM(new TAItem_Food(TAItem_Food.Foods.SILKBERRYJAM)),
		SILKBERRYJAMSANDWICH(new TAItem_Food(TAItem_Food.Foods.SILKBERRYJAMSANDWICH)),
		SILKSHROOMSTEW(new TAItem_Food(TAItem_Food.Foods.SILKSHROOMSTEW)),
		SPIKEDCHESTPLATE(new TAItem_Armor_Spiked(EntityEquipmentSlot.CHEST, "spikedchestplate")),
		STICKYSPIKER(new TAItem_Special_StickySpiker()),
		STRANGEMEAT(new TAItem_Special_StrangeMeat()),
		TEALAVENDER(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.LAVENDER)),
		TEAPETUNIA(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.PETUNIA)),
		TEASEEDY(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.SEEDY)),
		TEASILKBERRY(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.SILKBERRY)),
		UMBRAPICKAXE(new TAItem_Tool_Umbra_Pickaxe()),
		UMBRASHIELD(new TAItem_Tool_Umbra_Shield()),
		UMBRASWORD(new TAItem_Tool_Umbra_Sword()),
		WEBBING(new TAItem_Special_Webbing()),
		WEEPINGWILLOWSAP(new TAItem_Special_WeepingWillowSap());

		private Item modItem;

		TAItemRegistry(Item i) {
			this.modItem = i;
		}

		public void InitModel() {
			ModelLoader.setCustomModelResourceLocation(modItem, 0, new ModelResourceLocation(modItem.getRegistryName(), "inventory"));
		}

		public void Register(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(this.modItem);
		}

		public boolean hasSpecialModel() {
			if (this.modItem instanceof ISpecialModel) {
				return true;
			} else {
				return false;
			}
		}
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (TAItemRegistry i : TAItemRegistry.values()) {
			i.Register(event);
		}
		TABlocks.registerItemblocks(event);
		TACompat.init();
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for (TAItemRegistry i : TAItemRegistry.values()) {
			if (!i.hasSpecialModel()) {
				i.InitModel();
			} else {
				ISpecialModel model = (ISpecialModel) i.modItem;
				model.initModel();
			}
		}
	}

	public interface ISpecialModel {
		@SideOnly(Side.CLIENT)
		public void initModel();
	}

}
