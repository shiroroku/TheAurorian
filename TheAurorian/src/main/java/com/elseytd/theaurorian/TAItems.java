package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Items.TAItem_AbsorptionOrb;
import com.elseytd.theaurorian.Items.TAItem_Amulet;
import com.elseytd.theaurorian.Items.TAItem_Armor_AurorianSteel;
import com.elseytd.theaurorian.Items.TAItem_Armor_Cerulean;
import com.elseytd.theaurorian.Items.TAItem_Armor_Knight;
import com.elseytd.theaurorian.Items.TAItem_Armor_SlimeBoots;
import com.elseytd.theaurorian.Items.TAItem_Armor_Spectral;
import com.elseytd.theaurorian.Items.TAItem_Armor_Spiked;
import com.elseytd.theaurorian.Items.TAItem_AurorianSteel_Axe;
import com.elseytd.theaurorian.Items.TAItem_AurorianSteel_Hoe;
import com.elseytd.theaurorian.Items.TAItem_AurorianSteel_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_AurorianSteel_Shovel;
import com.elseytd.theaurorian.Items.TAItem_AurorianSteel_Sword;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Axe;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Hoe;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Shovel;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Sickle;
import com.elseytd.theaurorian.Items.TAItem_AurorianStone_Sword;
import com.elseytd.theaurorian.Items.TAItem_Aurorianite_Axe;
import com.elseytd.theaurorian.Items.TAItem_Aurorianite_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Aurorianite_Sword;
import com.elseytd.theaurorian.Items.TAItem_Basic;
import com.elseytd.theaurorian.Items.TAItem_Bepsi;
import com.elseytd.theaurorian.Items.TAItem_Cerulean_Arrow;
import com.elseytd.theaurorian.Items.TAItem_Cerulean_Bucket;
import com.elseytd.theaurorian.Items.TAItem_Cerulean_Shield;
import com.elseytd.theaurorian.Items.TAItem_Crystal_Arrow;
import com.elseytd.theaurorian.Items.TAItem_CrystallineSprite;
import com.elseytd.theaurorian.Items.TAItem_Crystalline_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Crystalline_Shield;
import com.elseytd.theaurorian.Items.TAItem_Crystalline_Sword;
import com.elseytd.theaurorian.Items.TAItem_Debug;
import com.elseytd.theaurorian.Items.TAItem_DungeonKey;
import com.elseytd.theaurorian.Items.TAItem_DungeonLocator;
import com.elseytd.theaurorian.Items.TAItem_Food;
import com.elseytd.theaurorian.Items.TAItem_Food_Silkberry;
import com.elseytd.theaurorian.Items.TAItem_Food_Tea;
import com.elseytd.theaurorian.Items.TAItem_KeepersBow;
import com.elseytd.theaurorian.Items.TAItem_LivingDiviningRod;
import com.elseytd.theaurorian.Items.TAItem_Lockpicks;
import com.elseytd.theaurorian.Items.TAItem_MoonShield;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Axe;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Shield;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Moonstone_Sword;
import com.elseytd.theaurorian.Items.TAItem_QueensChipper;
import com.elseytd.theaurorian.Items.TAItem_Seeds;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Axe;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Bow;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Hoe;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Shovel;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Sickle;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Stick;
import com.elseytd.theaurorian.Items.TAItem_Silentwood_Sword;
import com.elseytd.theaurorian.Items.TAItem_StickySpiker;
import com.elseytd.theaurorian.Items.TAItem_StrangeMeat;
import com.elseytd.theaurorian.Items.TAItem_Umbra_Pickaxe;
import com.elseytd.theaurorian.Items.TAItem_Umbra_Shield;
import com.elseytd.theaurorian.Items.TAItem_Umbra_Sword;
import com.elseytd.theaurorian.Items.TAItem_Webbing;
import com.elseytd.theaurorian.Items.TAItem_WeepingWillowSap;

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

		public static ArmorMaterial CERULEAN_ARMOR = EnumHelper.addArmorMaterial("TA_CERULEAN_ARMOR", "theaurorian:cerulean", (int) (20 * TAConfig.Config_Cerulean_Multiplier_Durability), new int[] {
					(int) (3 * TAConfig.Config_Cerulean_Multiplier_Armor),
					(int) (6 * TAConfig.Config_Cerulean_Multiplier_Armor),
					(int) (5 * TAConfig.Config_Cerulean_Multiplier_Armor),
					(int) (3 * TAConfig.Config_Cerulean_Multiplier_Armor) }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial AURORIANSTEEL_ARMOR = EnumHelper.addArmorMaterial("TA_AURORIANSTEEL_ARMOR", "theaurorian:auroriansteelarmor", (int) (33 * TAConfig.Config_AurorianSteel_Multiplier_Durability), new int[] {
					(int) (4 * TAConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (7 * TAConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (8 * TAConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (4 * TAConfig.Config_AurorianSteel_Multiplier_Armor) }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);
		public static ArmorMaterial SPIKED_ARMOR = EnumHelper.addArmorMaterial("TA_SPIKED_ARMOR", "theaurorian:spiked", (int) (65 * TAConfig.Config_Special_Multiplier_Durability), new int[] {
					3,
					6,
					5,
					3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial KNIGHT_ARMOR = EnumHelper.addArmorMaterial("TA_KNIGHT_ARMOR", "theaurorian:knight", 30, new int[] { 2, 3, 2, 1 }, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		public static ArmorMaterial AURORIAN_SLIME = EnumHelper.addArmorMaterial("TA_AURORIAN_SLIME", "theaurorian:aurorianslime", (int) (120 * TAConfig.Config_Special_Multiplier_Durability), new int[] {
					1,
					2,
					3,
					1 }, 20, SoundEvents.ENTITY_SLIME_SQUISH, 0);
		public static ArmorMaterial SPECTRAL_ARMOR = EnumHelper.addArmorMaterial("TA_SPECTRAL_ARMOR", "theaurorian:spectral", (int) (20 * TAConfig.Config_Spectral_Multiplier_Durability), new int[] {
					(int) (4 * TAConfig.Config_Spectral_Multiplier_Armor),
					(int) (6 * TAConfig.Config_Spectral_Multiplier_Armor),
					(int) (6 * TAConfig.Config_Spectral_Multiplier_Armor),
					(int) (4 * TAConfig.Config_Spectral_Multiplier_Armor) }, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

		public static void initRepairMaterials() {
			SILENTWOOD.setRepairItem(new ItemStack(TABlocks.Registry.SILENTWOODPLANKS.getBlock()));
			AURORIANSTONE.setRepairItem(new ItemStack(TABlocks.Registry.AURORIANCOBBLESTONE.getBlock()));
			MOONSTONE.setRepairItem(new ItemStack(TAItems.Registry.INGOTMOONSTONE.getItem()));
			AURORIANITE.setRepairItem(new ItemStack(TAItems.Registry.INGOTAURORIANITE.getItem()));
			CRYSTALLINE.setRepairItem(new ItemStack(TAItems.Registry.INGOTCRYSTALLINE.getItem()));
			UMBRA.setRepairItem(new ItemStack(TAItems.Registry.INGOTUMBRA.getItem()));
			AURORIANSTEEL.setRepairItem(new ItemStack(TAItems.Registry.INGOTAURORIANSTEEL.getItem()));

			CERULEAN_ARMOR.setRepairItem(new ItemStack(TAItems.Registry.INGOTCERULEAN.getItem()));
			SPIKED_ARMOR.setRepairItem(new ItemStack(TAItems.Registry.INGOTUMBRA.getItem()));
			AURORIAN_SLIME.setRepairItem(new ItemStack(TAItems.Registry.AURORIANSLIMEBALL.getItem()));
			AURORIANSTEEL_ARMOR.setRepairItem(new ItemStack(TAItems.Registry.INGOTAURORIANSTEEL.getItem()));
			SPECTRAL_ARMOR.setRepairItem(new ItemStack(TAItems.Registry.SPECTRALSILK.getItem()));
		}
	}

	public enum Registry {
		ABSORPTIONORB(new TAItem_AbsorptionOrb()),
		AMULETDARK(new TAItem_Amulet(TAItem_Amulet.Amulets.DARKAMULET)),
		AMULETKEEPER(new TAItem_Amulet(TAItem_Amulet.Amulets.KEEPERAMULET)),
		AURORIANBACON(new TAItem_Food(TAItem_Food.Foods.AURORIANBACON)),
		AURORIANCOAL(new TAItem_Basic(TAItem_Basic.Items.AURORIANCOAL)),
		AURORIANCOALNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_AURORIANCOAL)),
		AURORIANITEAXE(new TAItem_Aurorianite_Axe()),
		AURORIANITEPICKAXE(new TAItem_Aurorianite_Pickaxe()),
		AURORIANITESWORD(new TAItem_Aurorianite_Sword()),
		AURORIANPORK(new TAItem_Food(TAItem_Food.Foods.AURORIANPORK)),
		AURORIANPORKCOOKED(new TAItem_Food(TAItem_Food.Foods.COOKEDAURORIANPORK)),
		AURORIANSLIMEBALL(new TAItem_Food(TAItem_Food.Foods.AURORIANSLIMEBALL)),
		AURORIANSLIMEBOOTS(new TAItem_Armor_SlimeBoots(EntityEquipmentSlot.FEET, "aurorianslimeboots")),
		AURORIANSTEELARMORBOOTS(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.FEET, "auroriansteelboots")),
		AURORIANSTEELARMORCHESTPLATE(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.CHEST, "auroriansteelchestplate")),
		AURORIANSTEELARMORHELMET(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.HEAD, "auroriansteelhelmet")),
		AURORIANSTEELARMORLEGGINGS(new TAItem_Armor_AurorianSteel(EntityEquipmentSlot.LEGS, "auroriansteelleggings")),
		AURORIANSTEELAXE(new TAItem_AurorianSteel_Axe()),
		AURORIANSTEELHOE(new TAItem_AurorianSteel_Hoe()),
		AURORIANSTEELNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_AURORIANSTEEL)),
		AURORIANSTEELPICKAXE(new TAItem_AurorianSteel_Pickaxe()),
		AURORIANSTEELSHOVEL(new TAItem_AurorianSteel_Shovel()),
		AURORIANSTEELSWORD(new TAItem_AurorianSteel_Sword()),
		AURORIANSTONEAXE(new TAItem_AurorianStone_Axe()),
		AURORIANSTONEHOE(new TAItem_AurorianStone_Hoe()),
		AURORIANSTONEPICKAXE(new TAItem_AurorianStone_Pickaxe()),
		AURORIANSTONESHOVEL(new TAItem_AurorianStone_Shovel()),
		AURORIANSTONESICKLE(new TAItem_AurorianStone_Sickle()),
		AURORIANSTONESWORD(new TAItem_AurorianStone_Sword()),
		BEPSI(new TAItem_Bepsi()),
		CERULEANARMORBOOTS(new TAItem_Armor_Cerulean(EntityEquipmentSlot.FEET, "ceruleanboots")),
		CERULEANARMORCHESTPLATE(new TAItem_Armor_Cerulean(EntityEquipmentSlot.CHEST, "ceruleanchestplate")),
		CERULEANARMORHELMET(new TAItem_Armor_Cerulean(EntityEquipmentSlot.HEAD, "ceruleanhelmet")),
		CERULEANARMORLEGGINGS(new TAItem_Armor_Cerulean(EntityEquipmentSlot.LEGS, "ceruleanleggings")),
		CERULEANARROW(new TAItem_Cerulean_Arrow()),
		CERULEANBUCKET(new TAItem_Cerulean_Bucket()),
		CERULEANNUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_CERULEAN)),
		CERULEANSHIELD(new TAItem_Cerulean_Shield()),
		CRYSTALARROW(new TAItem_Crystal_Arrow()),
		CRYSTALLINEPICKAXE(new TAItem_Crystalline_Pickaxe()),
		CRYSTALLINESHIELD(new TAItem_Crystalline_Shield()),
		CRYSTALLINESPRITE(new TAItem_CrystallineSprite()),
		CRYSTALLINESWORD(new TAItem_Crystalline_Sword()),
		CUP(new TAItem_Basic(TAItem_Basic.Items.CUP)),
		DEBUGGER(new TAItem_Debug()),
		INGOTAURORIANITE(new TAItem_Basic(TAItem_Basic.Items.INGOT_AURORIANITE)),
		INGOTAURORIANSTEEL(new TAItem_Basic(TAItem_Basic.Items.INGOT_AURORIANSTEEL)),
		INGOTCERULEAN(new TAItem_Basic(TAItem_Basic.Items.INGOT_CERULEAN)),
		INGOTCRYSTALLINE(new TAItem_Basic(TAItem_Basic.Items.INGOT_CRYSTALLINE)),
		INGOTMOONSTONE(new TAItem_Basic(TAItem_Basic.Items.INGOT_MOONSTONE)),
		INGOTUMBRA(new TAItem_Basic(TAItem_Basic.Items.INGOT_UMBRA)),
		KEEPERSBOW(new TAItem_KeepersBow()),
		KEYDARKSTONE(new TAItem_DungeonKey(TAItem_DungeonKey.Keys.DARKSTONE)),
		KEYMOONTEMPLE(new TAItem_DungeonKey(TAItem_DungeonKey.Keys.MOONTEMPLE)),
		KEYMOONTEMPLECELL(new TAItem_DungeonKey(TAItem_DungeonKey.Keys.MOONTEMPLECELL)),
		KEYRUNESTONE(new TAItem_DungeonKey(TAItem_DungeonKey.Keys.RUNESTONE)),
		KEYRUNESTONELOOT(new TAItem_DungeonKey(TAItem_DungeonKey.Keys.RUNESTONELOOT)),
		KNIGHTARMORBOOTS(new TAItem_Armor_Knight(EntityEquipmentSlot.FEET, "knightboots")),
		KNIGHTARMORCHESTPLATE(new TAItem_Armor_Knight(EntityEquipmentSlot.CHEST, "knightchestplate")),
		KNIGHTARMORHELMET(new TAItem_Armor_Knight(EntityEquipmentSlot.HEAD, "knighthelmet")),
		KNIGHTARMORLEGGINGS(new TAItem_Armor_Knight(EntityEquipmentSlot.LEGS, "knightleggings")),
		LAVENDER(new TAItem_Basic(TAItem_Basic.Items.LAVENDER)),
		LAVENDERBREAD(new TAItem_Food(TAItem_Food.Foods.LAVENDERBREAD)),
		LIVINGDIVININGROD(new TAItem_LivingDiviningRod()),
		LOCATOR(new TAItem_DungeonLocator()),
		LOCKPICKS(new TAItem_Lockpicks()),
		MOONSHIELD(new TAItem_MoonShield()),
		MOONSTONEAXE(new TAItem_Moonstone_Axe()),
		MOONSTONEHOE(new TAItem_Moonstone_Hoe()),
		MOONSTONENUGGET(new TAItem_Basic(TAItem_Basic.Items.NUGGET_MOONSTONE)),
		MOONSTONEPICKAXE(new TAItem_Moonstone_Pickaxe()),
		MOONSTONESHIELD(new TAItem_Moonstone_Shield()),
		MOONSTONESHOVEL(new TAItem_Moonstone_Shovel()),
		MOONSTONESICKLE(new TAItem_Moonstone_Sickle()),
		MOONSTONESWORD(new TAItem_Moonstone_Sword()),
		MOONTEMPLECELLKEYFRAGMENT(new TAItem_Basic(TAItem_Basic.Items.MOONTEMPLECELLKEYFRAGMENT)),
		PLANTFIBER(new TAItem_Basic(TAItem_Basic.Items.PLANTFIBER)),
		QUEENSCHIPPER(new TAItem_QueensChipper()),
		SCRAPAURORIANITE(new TAItem_Basic(TAItem_Basic.Items.SCRAP_AURORIANITE)),
		SCRAPCRYSTALLINE(new TAItem_Basic(TAItem_Basic.Items.SCRAP_CRYSTALLINE)),
		SCRAPUMBRA(new TAItem_Basic(TAItem_Basic.Items.SCRAP_UMBRA)),
		SEEDSLAVENDER(new TAItem_Seeds(TAItem_Seeds.ITEMNAME_LAVENDER)),
		SILENTWOODAXE(new TAItem_Silentwood_Axe()),
		SILENTWOODBOW(new TAItem_Silentwood_Bow()),
		SILENTWOODHOE(new TAItem_Silentwood_Hoe()),
		SILENTWOODPICKAXE(new TAItem_Silentwood_Pickaxe()),
		SILENTWOODSHOVEL(new TAItem_Silentwood_Shovel()),
		SILENTWOODSICKLE(new TAItem_Silentwood_Sickle()),
		SILENTWOODSTICK(new TAItem_Silentwood_Stick()),
		SILENTWOODSWORD(new TAItem_Silentwood_Sword()),
		SILKBERRY(new TAItem_Food_Silkberry()),
		SILKBERRYJAM(new TAItem_Food(TAItem_Food.Foods.SILKBERRYJAM)),
		SILKBERRYJAMSANDWICH(new TAItem_Food(TAItem_Food.Foods.SILKBERRYJAMSANDWICH)),
		SILKSHROOMSTEW(new TAItem_Food(TAItem_Food.Foods.SILKSHROOMSTEW)),
		SOULLESSFLESH(new TAItem_Food(TAItem_Food.Foods.SOULLESSFLESH)),
		SPECTRALSILK(new TAItem_Basic(TAItem_Basic.Items.SPECTRALSILK)),
		SPECTRALARMORBOOTS(new TAItem_Armor_Spectral(EntityEquipmentSlot.FEET, "spectralboots")),
		SPECTRALARMORCHESTPLATE(new TAItem_Armor_Spectral(EntityEquipmentSlot.CHEST, "spectralchestplate")),
		SPECTRALARMORHELMET(new TAItem_Armor_Spectral(EntityEquipmentSlot.HEAD, "spectralhelmet")),
		SPECTRALARMORLEGGINGS(new TAItem_Armor_Spectral(EntityEquipmentSlot.LEGS, "spectralleggings")),
		SPIKEDCHESTPLATE(new TAItem_Armor_Spiked(EntityEquipmentSlot.CHEST, "spikedchestplate")),
		STICKYSPIKER(new TAItem_StickySpiker()),
		STRANGEMEAT(new TAItem_StrangeMeat()),
		TEALAVENDER(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.LAVENDER)),
		TEAPETUNIA(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.PETUNIA)),
		TEASEEDY(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.SEEDY)),
		TEASILKBERRY(new TAItem_Food_Tea(TAItem_Food_Tea.Teas.SILKBERRY)),
		TROPHYKEEPER(new TAItem_Basic(TAItem_Basic.Items.TROPHY_KEEPER)),
		TROPHYMOONQUEEN(new TAItem_Basic(TAItem_Basic.Items.TROPHY_MOONQUEEN)),
		TROPHYSPIDER(new TAItem_Basic(TAItem_Basic.Items.TROPHY_SPIDER)),
		UMBRAPICKAXE(new TAItem_Umbra_Pickaxe()),
		UMBRASHIELD(new TAItem_Umbra_Shield()),
		UMBRASWORD(new TAItem_Umbra_Sword()),
		WEBBING(new TAItem_Webbing()),
		WEEPINGWILLOWSAP(new TAItem_WeepingWillowSap());

		private Item modItem;

		Registry(Item i) {
			this.modItem = i;
		}

		public void initModel() {
			ModelLoader.setCustomModelResourceLocation(this.modItem, 0, new ModelResourceLocation(this.modItem.getRegistryName(), "inventory"));
		}

		public boolean hasUniqueModel() {
			return this.modItem instanceof IUniqueModel;
		}

		public void register(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(this.modItem);
		}

		public Item getItem() {
			return this.modItem;
		}

	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Registry i : Registry.values()) {
			i.register(event);
		}
		TABlocks.registerItemblocks(event);
		TACompat.init();
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for (Registry i : Registry.values()) {
			if (i.hasUniqueModel()) {
				((IUniqueModel) i.modItem).initModel();
			} else {
				i.initModel();
			}
		}
	}

	public interface IUniqueModel {
		@SideOnly(Side.CLIENT)
		void initModel();
	}

}
