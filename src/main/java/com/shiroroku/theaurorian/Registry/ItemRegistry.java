package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianCompatibility;
import com.shiroroku.theaurorian.AurorianConfig;
import com.shiroroku.theaurorian.Items.*;
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
public class ItemRegistry {

	public static class Materials {
		public static Item.ToolMaterial SILENTWOOD = EnumHelper.addToolMaterial("TA_SILENTWOOD", AurorianConfig.Config_Silentwood_HarvestLevel, (int) (59 * AurorianConfig.Config_Silentwood_Multiplier_Durability), 3.0F * AurorianConfig.Config_Silentwood_Multiplier_Speed, 0, 20);
		public static Item.ToolMaterial AURORIANSTONE = EnumHelper.addToolMaterial("TA_AURORIANSTONE", AurorianConfig.Config_AurorianStone_HarvestLevel, (int) (131 * AurorianConfig.Config_AurorianStone_Multiplier_Durability), 4.5F * AurorianConfig.Config_AurorianStone_Multiplier_Speed, 1.5F * AurorianConfig.Config_AurorianStone_Multiplier_Damage, 14);
		public static Item.ToolMaterial MOONSTONE = EnumHelper.addToolMaterial("TA_MOONSTONE", AurorianConfig.Config_Moonstone_HarvestLevel, (int) (250 * AurorianConfig.Config_Moonstone_Multiplier_Durability), 7.0F * AurorianConfig.Config_Moonstone_Multiplier_Speed, 2.5F * AurorianConfig.Config_Moonstone_Multiplier_Damage, 14);
		public static Item.ToolMaterial AURORIANITE = EnumHelper.addToolMaterial("TA_AURORIANITE", AurorianConfig.Config_Special_HarvestLevel, (int) (1000 * AurorianConfig.Config_Special_Multiplier_Durability), 8.0F * AurorianConfig.Config_Special_Multiplier_Speed, 3.0F * AurorianConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial UMBRA = EnumHelper.addToolMaterial("TA_UMBRA", AurorianConfig.Config_Special_HarvestLevel, (int) (1000 * AurorianConfig.Config_Special_Multiplier_Durability), 8.0F * AurorianConfig.Config_Special_Multiplier_Speed, 3.0F * AurorianConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial CRYSTALLINE = EnumHelper.addToolMaterial("TA_CRYSTALLINE", AurorianConfig.Config_Special_HarvestLevel, (int) (1000 * AurorianConfig.Config_Special_Multiplier_Durability), 8.0F * AurorianConfig.Config_Special_Multiplier_Speed, 3.0F * AurorianConfig.Config_Special_Multiplier_Damage, 20);
		public static Item.ToolMaterial AURORIANSTEEL = EnumHelper.addToolMaterial("TA_AURORIANSTEEL", AurorianConfig.Config_AurorianSteel_HarvestLevel, (int) (1500 * AurorianConfig.Config_AurorianSteel_Multiplier_Durability), 8.5F * AurorianConfig.Config_AurorianSteel_Multiplier_Speed, 3.5F * AurorianConfig.Config_AurorianSteel_Multiplier_Damage, 10);

		public static ArmorMaterial CERULEAN_ARMOR = EnumHelper.addArmorMaterial("TA_CERULEAN_ARMOR", "theaurorian:cerulean", (int) (20 * AurorianConfig.Config_Cerulean_Multiplier_Durability), new int[] {
					(int) (3 * AurorianConfig.Config_Cerulean_Multiplier_Armor),
					(int) (6 * AurorianConfig.Config_Cerulean_Multiplier_Armor),
					(int) (5 * AurorianConfig.Config_Cerulean_Multiplier_Armor),
					(int) (3 * AurorianConfig.Config_Cerulean_Multiplier_Armor) }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial AURORIANSTEEL_ARMOR = EnumHelper.addArmorMaterial("TA_AURORIANSTEEL_ARMOR", "theaurorian:auroriansteelarmor", (int) (33 * AurorianConfig.Config_AurorianSteel_Multiplier_Durability), new int[] {
					(int) (4 * AurorianConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (7 * AurorianConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (8 * AurorianConfig.Config_AurorianSteel_Multiplier_Armor),
					(int) (4 * AurorianConfig.Config_AurorianSteel_Multiplier_Armor) }, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 2);
		public static ArmorMaterial SPIKED_ARMOR = EnumHelper.addArmorMaterial("TA_SPIKED_ARMOR", "theaurorian:spiked", (int) (65 * AurorianConfig.Config_Special_Multiplier_Durability), new int[] {
					3,
					6,
					5,
					3 }, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1);
		public static ArmorMaterial KNIGHT_ARMOR = EnumHelper.addArmorMaterial("TA_KNIGHT_ARMOR", "theaurorian:knight", 30, new int[] { 2, 3, 2, 1 }, 5, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0);
		public static ArmorMaterial AURORIAN_SLIME = EnumHelper.addArmorMaterial("TA_AURORIAN_SLIME", "theaurorian:aurorianslime", (int) (120 * AurorianConfig.Config_Special_Multiplier_Durability), new int[] {
					1,
					2,
					3,
					1 }, 20, SoundEvents.ENTITY_SLIME_SQUISH, 0);
		public static ArmorMaterial SPECTRAL_ARMOR = EnumHelper.addArmorMaterial("TA_SPECTRAL_ARMOR", "theaurorian:spectral", (int) (20 * AurorianConfig.Config_Spectral_Multiplier_Durability), new int[] {
					(int) (4 * AurorianConfig.Config_Spectral_Multiplier_Armor),
					(int) (6 * AurorianConfig.Config_Spectral_Multiplier_Armor),
					(int) (6 * AurorianConfig.Config_Spectral_Multiplier_Armor),
					(int) (4 * AurorianConfig.Config_Spectral_Multiplier_Armor) }, 25, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0);

		public static void initRepairMaterials() {
			SILENTWOOD.setRepairItem(new ItemStack(BlockRegistry.Registry.SILENTWOODPLANKS.getBlock()));
			AURORIANSTONE.setRepairItem(new ItemStack(BlockRegistry.Registry.AURORIANCOBBLESTONE.getBlock()));
			MOONSTONE.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTMOONSTONE.getItem()));
			AURORIANITE.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTAURORIANITE.getItem()));
			CRYSTALLINE.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTCRYSTALLINE.getItem()));
			UMBRA.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTUMBRA.getItem()));
			AURORIANSTEEL.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTAURORIANSTEEL.getItem()));

			CERULEAN_ARMOR.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTCERULEAN.getItem()));
			SPIKED_ARMOR.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTUMBRA.getItem()));
			AURORIAN_SLIME.setRepairItem(new ItemStack(ItemRegistry.Registry.AURORIANSLIMEBALL.getItem()));
			AURORIANSTEEL_ARMOR.setRepairItem(new ItemStack(ItemRegistry.Registry.INGOTAURORIANSTEEL.getItem()));
			SPECTRAL_ARMOR.setRepairItem(new ItemStack(ItemRegistry.Registry.SPECTRALSILK.getItem()));
		}
	}

	public enum Registry {
		ABSORPTIONORB(new AbsorptionOrbItem()),
		AMULETDARK(new AmuletItem(AmuletItem.Amulets.DARKAMULET)),
		AMULETKEEPER(new AmuletItem(AmuletItem.Amulets.KEEPERAMULET)),
		AURORIANBACON(new FoodItem(FoodItem.Foods.AURORIANBACON)),
		AURORIANCOAL(new BasicItem(BasicItem.Items.AURORIANCOAL)),
		AURORIANCOALNUGGET(new BasicItem(BasicItem.Items.NUGGET_AURORIANCOAL)),
		AURORIANITEAXE(new AurorianiteAxe()),
		AURORIANITEPICKAXE(new AurorianitePickaxe()),
		AURORIANITESWORD(new AurorianiteSword()),
		AURORIANPORK(new FoodItem(FoodItem.Foods.AURORIANPORK)),
		AURORIANPORKCOOKED(new FoodItem(FoodItem.Foods.COOKEDAURORIANPORK)),
		AURORIANSLIMEBALL(new FoodItem(FoodItem.Foods.AURORIANSLIMEBALL)),
		AURORIANSLIMEBOOTS(new SlimeBootsItemArmor(EntityEquipmentSlot.FEET, "aurorianslimeboots")),
		AURORIANSTEELARMORBOOTS(new AurorianSteelItemArmor(EntityEquipmentSlot.FEET, "auroriansteelboots")),
		AURORIANSTEELARMORCHESTPLATE(new AurorianSteelItemArmor(EntityEquipmentSlot.CHEST, "auroriansteelchestplate")),
		AURORIANSTEELARMORHELMET(new AurorianSteelItemArmor(EntityEquipmentSlot.HEAD, "auroriansteelhelmet")),
		AURORIANSTEELARMORLEGGINGS(new AurorianSteelItemArmor(EntityEquipmentSlot.LEGS, "auroriansteelleggings")),
		AURORIANSTEELAXE(new AurorianSteelAxe()),
		AURORIANSTEELHOE(new AurorianSteelHoe()),
		AURORIANSTEELNUGGET(new BasicItem(BasicItem.Items.NUGGET_AURORIANSTEEL)),
		AURORIANSTEELPICKAXE(new AurorianSteelPickaxe()),
		AURORIANSTEELSHOVEL(new AurorianSteelShovel()),
		AURORIANSTEELSWORD(new AurorianSteelSword()),
		AURORIANSTONEAXE(new AurorianStoneAxe()),
		AURORIANSTONEHOE(new AurorianStoneHoe()),
		AURORIANSTONEPICKAXE(new AurorianStonePickaxe()),
		AURORIANSTONESHOVEL(new AurorianStoneShovel()),
		AURORIANSTONESICKLE(new AurorianStoneSickleItem()),
		AURORIANSTONESWORD(new AurorianStoneSword()),
		BEPSI(new BepsiItem()),
		CERULEANARMORBOOTS(new CeruleanItemArmor(EntityEquipmentSlot.FEET, "ceruleanboots")),
		CERULEANARMORCHESTPLATE(new CeruleanItemArmor(EntityEquipmentSlot.CHEST, "ceruleanchestplate")),
		CERULEANARMORHELMET(new CeruleanItemArmor(EntityEquipmentSlot.HEAD, "ceruleanhelmet")),
		CERULEANARMORLEGGINGS(new CeruleanItemArmor(EntityEquipmentSlot.LEGS, "ceruleanleggings")),
		CERULEANARROW(new CeruleanArrow()),
		CERULEANBUCKET(new CeruleanBucket()),
		CERULEANNUGGET(new BasicItem(BasicItem.Items.NUGGET_CERULEAN)),
		CERULEANSHIELD(new CeruleanShieldItem()),
		CRYSTALARROW(new CrystalArrow()),
		CRYSTALLINEPICKAXE(new CrystallinePickaxe()),
		CRYSTALLINESHIELD(new CrystallineShieldItem()),
		CRYSTALLINESPRITE(new CrystallineSpriteItem()),
		CRYSTALLINESWORD(new CrystallineSword()),
		CUP(new BasicItem(BasicItem.Items.CUP)),
		DEBUGGER(new DebugItem()),
		INGOTAURORIANITE(new BasicItem(BasicItem.Items.INGOT_AURORIANITE)),
		INGOTAURORIANSTEEL(new BasicItem(BasicItem.Items.INGOT_AURORIANSTEEL)),
		INGOTCERULEAN(new BasicItem(BasicItem.Items.INGOT_CERULEAN)),
		INGOTCRYSTALLINE(new BasicItem(BasicItem.Items.INGOT_CRYSTALLINE)),
		INGOTMOONSTONE(new BasicItem(BasicItem.Items.INGOT_MOONSTONE)),
		INGOTUMBRA(new BasicItem(BasicItem.Items.INGOT_UMBRA)),
		KEEPERSBOW(new KeepersBow()),
		KEYDARKSTONE(new DungeonKeyItem(DungeonKeyItem.Keys.DARKSTONE)),
		KEYMOONTEMPLE(new DungeonKeyItem(DungeonKeyItem.Keys.MOONTEMPLE)),
		KEYMOONTEMPLECELL(new DungeonKeyItem(DungeonKeyItem.Keys.MOONTEMPLECELL)),
		KEYRUNESTONE(new DungeonKeyItem(DungeonKeyItem.Keys.RUNESTONE)),
		KEYRUNESTONELOOT(new DungeonKeyItem(DungeonKeyItem.Keys.RUNESTONELOOT)),
		KNIGHTARMORBOOTS(new KnightItemArmor(EntityEquipmentSlot.FEET, "knightboots")),
		KNIGHTARMORCHESTPLATE(new KnightItemArmor(EntityEquipmentSlot.CHEST, "knightchestplate")),
		KNIGHTARMORHELMET(new KnightItemArmor(EntityEquipmentSlot.HEAD, "knighthelmet")),
		KNIGHTARMORLEGGINGS(new KnightItemArmor(EntityEquipmentSlot.LEGS, "knightleggings")),
		LAVENDER(new BasicItem(BasicItem.Items.LAVENDER)),
		LAVENDERBREAD(new FoodItem(FoodItem.Foods.LAVENDERBREAD)),
		LIVINGDIVININGROD(new LivingDiviningRodItem()),
		LOCATOR(new DungeonLocatorItem()),
		LOCKPICKS(new LockpicksItem()),
		MOONSHIELD(new MoonShieldItem()),
		MOONSTONEAXE(new MoonstoneAxe()),
		MOONSTONEHOE(new MoonstoneHoe()),
		MOONSTONENUGGET(new BasicItem(BasicItem.Items.NUGGET_MOONSTONE)),
		MOONSTONEPICKAXE(new MoonstonePickaxe()),
		MOONSTONESHIELD(new MoonstoneShieldItem()),
		MOONSTONESHOVEL(new MoonstoneShovel()),
		MOONSTONESICKLE(new MoonstoneSickleItem()),
		MOONSTONESWORD(new MoonstoneSword()),
		MOONTEMPLECELLKEYFRAGMENT(new BasicItem(BasicItem.Items.MOONTEMPLECELLKEYFRAGMENT)),
		PLANTFIBER(new BasicItem(BasicItem.Items.PLANTFIBER)),
		QUEENSCHIPPER(new QueensChipper()),
		SCRAPAURORIANITE(new BasicItem(BasicItem.Items.SCRAP_AURORIANITE)),
		SCRAPCRYSTALLINE(new BasicItem(BasicItem.Items.SCRAP_CRYSTALLINE)),
		SCRAPUMBRA(new BasicItem(BasicItem.Items.SCRAP_UMBRA)),
		SEEDSLAVENDER(new SeedsItem(SeedsItem.ITEMNAME_LAVENDER)),
		SILENTWOODAXE(new SilentwoodAxe()),
		SILENTWOODBOW(new SilentwoodBow()),
		SILENTWOODHOE(new SilentwoodHoe()),
		SILENTWOODPICKAXE(new SilentwoodPickaxe()),
		SILENTWOODSHOVEL(new SilentwoodShovel()),
		SILENTWOODSICKLE(new SilentwoodSickle()),
		SILENTWOODSTICK(new SilentwoodStick()),
		SILENTWOODSWORD(new SilentwoodSword()),
		SILKBERRY(new SilkberryFood()),
		SILKBERRYJAM(new FoodItem(FoodItem.Foods.SILKBERRYJAM)),
		SILKBERRYJAMSANDWICH(new FoodItem(FoodItem.Foods.SILKBERRYJAMSANDWICH)),
		SILKSHROOMSTEW(new FoodItem(FoodItem.Foods.SILKSHROOMSTEW)),
		SOULLESSFLESH(new FoodItem(FoodItem.Foods.SOULLESSFLESH)),
		SPECTRALSILK(new BasicItem(BasicItem.Items.SPECTRALSILK)),
		SPECTRALARMORBOOTS(new SpectralItemArmor(EntityEquipmentSlot.FEET, "spectralboots")),
		SPECTRALARMORCHESTPLATE(new SpectralItemArmor(EntityEquipmentSlot.CHEST, "spectralchestplate")),
		SPECTRALARMORHELMET(new SpectralItemArmor(EntityEquipmentSlot.HEAD, "spectralhelmet")),
		SPECTRALARMORLEGGINGS(new SpectralItemArmor(EntityEquipmentSlot.LEGS, "spectralleggings")),
		SPIKEDCHESTPLATE(new SpikedItemArmor(EntityEquipmentSlot.CHEST, "spikedchestplate")),
		STICKYSPIKER(new StickySpikerItem()),
		STRANGEMEAT(new StrangeMeatFood()),
		TEALAVENDER(new TeaFood(TeaFood.Teas.LAVENDER)),
		TEAPETUNIA(new TeaFood(TeaFood.Teas.PETUNIA)),
		TEASEEDY(new TeaFood(TeaFood.Teas.SEEDY)),
		TEASILKBERRY(new TeaFood(TeaFood.Teas.SILKBERRY)),
		TROPHYKEEPER(new BasicItem(BasicItem.Items.TROPHY_KEEPER)),
		TROPHYMOONQUEEN(new BasicItem(BasicItem.Items.TROPHY_MOONQUEEN)),
		TROPHYSPIDER(new BasicItem(BasicItem.Items.TROPHY_SPIDER)),
		UMBRAPICKAXE(new UmbraPickaxe()),
		UMBRASHIELD(new UmbraShield()),
		UMBRASWORD(new UmbraSword()),
		WEBBING(new WebbingItem()),
		WEEPINGWILLOWSAP(new WeepingWillowSap());

		private final Item modItem;

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
		BlockRegistry.registerItemblocks(event);
		AurorianCompatibility.init();
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
