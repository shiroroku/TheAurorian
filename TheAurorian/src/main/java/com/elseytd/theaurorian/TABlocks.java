package com.elseytd.theaurorian;

import com.elseytd.theaurorian.TAItems.ISpecialModel;
import com.elseytd.theaurorian.Blocks.TABlockFluid_MoltenCerulean;
import com.elseytd.theaurorian.Blocks.TABlockFluid_MoltenMoonstone;
import com.elseytd.theaurorian.Blocks.TABlockFluid_Moonwater;
import com.elseytd.theaurorian.Blocks.TABlock_Aurorian_Stone_Brick;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStone;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStoneBars;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStoneGate;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStoneGateKeyhole;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStoneLamp;
import com.elseytd.theaurorian.Blocks.TABlock_DungeonStoneSmooth;
import com.elseytd.theaurorian.Blocks.TABlock_Furnace;
import com.elseytd.theaurorian.Blocks.TABlock_FurnaceChimney;
import com.elseytd.theaurorian.Blocks.TABlock_Glass;
import com.elseytd.theaurorian.Blocks.TABlock_GlassPane;
import com.elseytd.theaurorian.Blocks.TABlock_Material;
import com.elseytd.theaurorian.Blocks.TABlock_MoonGem;
import com.elseytd.theaurorian.Blocks.TABlock_Mushroom;
import com.elseytd.theaurorian.Blocks.TABlock_MushroomCrystal;
import com.elseytd.theaurorian.Blocks.TABlock_MushroomStem;
import com.elseytd.theaurorian.Blocks.TABlock_MysticalBarrier;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_AurorianCoal;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Cerulean;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Geode;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Moonstone;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Crops;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Lavender;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Mushroom;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Petunia;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Silentwood_Sapling;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Silkberry;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Tallgrass;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Tallgrass_Light;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_WeepingWillow_Sapling;
import com.elseytd.theaurorian.Blocks.TABlock_Portal;
import com.elseytd.theaurorian.Blocks.TABlock_PortalframeBricks;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Ladder;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Leaves;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Log;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Planks;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Torch;
import com.elseytd.theaurorian.Blocks.TABlock_Silentwood_Workbench;
import com.elseytd.theaurorian.Blocks.TABlock_Spawner_Boss;
import com.elseytd.theaurorian.Blocks.TABlock_Stairs;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_AurorianCobblestone;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_AurorianDirt;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_AurorianFarmTile;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_AurorianGrass;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_AurorianStone;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_Peridotite;
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_Moonsand;
import com.elseytd.theaurorian.Blocks.TABlock_UmbraStone;
import com.elseytd.theaurorian.Blocks.TABlock_Urn;
import com.elseytd.theaurorian.Blocks.TABlock_WeepingWillow_Leaves;
import com.elseytd.theaurorian.Blocks.TABlock_WeepingWillow_Log;
import com.elseytd.theaurorian.Blocks.TABlock_WeepingWillow_Planks;
import com.elseytd.theaurorian.Blocks.TAFluid_MoltenCerulean;
import com.elseytd.theaurorian.Blocks.TAFluid_MoltenMoonstone;
import com.elseytd.theaurorian.Blocks.TAFluid_Moonwater;
import com.elseytd.theaurorian.Items.TAItem_Special_DungeonKey;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class TABlocks {

	public static class Fluids {
		public static TAFluid_Moonwater MOONWATER = new TAFluid_Moonwater();
		public static TAFluid_MoltenCerulean MOLTENCERULEAN = new TAFluid_MoltenCerulean();
		public static TAFluid_MoltenMoonstone MOLTENMOONSTONE = new TAFluid_MoltenMoonstone();

		public static void registerFluids() {
			FluidRegistry.registerFluid(Fluids.MOLTENCERULEAN);
			FluidRegistry.registerFluid(Fluids.MOLTENMOONSTONE);
			FluidRegistry.registerFluid(Fluids.MOONWATER);

			FluidRegistry.addBucketForFluid(Fluids.MOLTENCERULEAN);
			FluidRegistry.addBucketForFluid(Fluids.MOLTENMOONSTONE);
			FluidRegistry.addBucketForFluid(Fluids.MOONWATER);
		}
	}

	// FLUID
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_Moonwater.FLUIDNAME)
	public static TABlockFluid_Moonwater moonwaterblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_MoltenCerulean.FLUIDNAME)
	public static TABlockFluid_MoltenCerulean moltenceruleanblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_MoltenMoonstone.FLUIDNAME)
	public static TABlockFluid_MoltenMoonstone moltenmoonstoneblock;

	// SPECIAL
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Urn.BLOCKNAME)
	public static TABlock_Urn urn;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Furnace.BLOCKNAME)
	public static TABlock_Furnace aurorianfurnace;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_FurnaceChimney.BLOCKNAME)
	public static TABlock_FurnaceChimney aurorianfurnacechimney;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Furnace.BLOCKNAME_LIT)
	public static TABlock_Furnace aurorianfurnacelit;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Portal.BLOCKNAME)
	public static TABlock_Portal aurorianportal;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_PortalframeBricks.BLOCKNAME)
	public static TABlock_PortalframeBricks aurorianportalframebricks;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Spawner_Boss.BLOCKNAME_KEEPER)
	public static TABlock_Spawner_Boss bossspawnerkeeper;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Spawner_Boss.BLOCKNAME_MOONQUEEN)
	public static TABlock_Spawner_Boss bossspawnermoonqueen;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Torch.BLOCKNAME)
	public static TABlock_Silentwood_Torch silentwoodtorch;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Workbench.BLOCKNAME)
	public static TABlock_Silentwood_Workbench silentwoodworkbench;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Glass.BLOCKNAME_MOONGLASS)
	public static TABlock_Glass moonglass;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Glass.BLOCKNAME_AURORIAN)
	public static TABlock_Glass aurorianglass;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_GlassPane.BLOCKNAME_MOONGLASS)
	public static TABlock_GlassPane moonglasspane;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_GlassPane.BLOCKNAME_AURORIAN)
	public static TABlock_GlassPane aurorianglasspane;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_MysticalBarrier.BLOCKNAME)
	public static TABlock_MysticalBarrier mysticalbarrier;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_MushroomCrystal.BLOCKNAME)
	public static TABlock_MushroomCrystal mushroomcrystal;

	// PLANTS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Tallgrass.BLOCKNAME)
	public static TABlock_Plant_Tallgrass auroriantallgrass;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Tallgrass_Light.BLOCKNAME)
	public static TABlock_Plant_Tallgrass_Light auroriantallgrasslight;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Crops.BLOCKNAME_SILKBERRY)
	public static TABlock_Plant_Crops silkberrycrop;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Crops.BLOCKNAME_LAVENDER)
	public static TABlock_Plant_Crops lavendercrop;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Lavender.BLOCKNAME)
	public static TABlock_Plant_Lavender lavenderplant;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Silentwood_Sapling.BLOCKNAME)
	public static TABlock_Plant_Silentwood_Sapling silentwoodsapling;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Silkberry.BLOCKNAME)
	public static TABlock_Plant_Silkberry silkberryplant;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Petunia.BLOCKNAME)
	public static TABlock_Plant_Petunia petuniaplant;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Mushroom.BLOCKNAME)
	public static TABlock_Mushroom mushroom;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_MushroomStem.BLOCKNAME)
	public static TABlock_MushroomStem mushroomstem;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Mushroom.BLOCKNAME)
	public static TABlock_Plant_Mushroom mushroomsmall;

	// TERRAIN
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianCobblestone.BLOCKNAME)
	public static TABlock_Terrain_AurorianCobblestone auroriancobblestone;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Aurorian_Stone_Brick.BLOCKNAME)
	public static TABlock_Aurorian_Stone_Brick aurorianstonebrick;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianDirt.BLOCKNAME)
	public static TABlock_Terrain_AurorianDirt auroriandirt;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianFarmTile.BLOCKNAME)
	public static TABlock_Terrain_AurorianFarmTile aurorianfarmtile;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianGrass.BLOCKNAME)
	public static TABlock_Terrain_AurorianGrass auroriangrass;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianGrass.BLOCKNAME_LIGHT)
	public static TABlock_Terrain_AurorianGrass auroriangrasslight;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianStone.BLOCKNAME)
	public static TABlock_Terrain_AurorianStone aurorianstone;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_Peridotite.BLOCKNAME)
	public static TABlock_Terrain_Peridotite aurorianperidotite;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_Peridotite.BLOCKNAME_SMOOTH)
	public static TABlock_Terrain_Peridotite aurorianperidotitesmooth;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_Moonsand.BLOCKNAME)
	public static TABlock_Terrain_Moonsand moonsand;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_UmbraStone.BLOCKNAME_UMBRASTONE)
	public static TABlock_UmbraStone umbrastone;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_UmbraStone.BLOCKNAME_UMBRASTONECRACKED)
	public static TABlock_UmbraStone umbrastonecracked;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_UmbraStone.BLOCKNAME_UMBRASTONEROOFTILES)
	public static TABlock_UmbraStone umbrastonerooftiles;

	// RUNESTONE
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStone.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStone runestone;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneBars.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStoneBars runestonebars;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStoneGate runestonegate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStoneGateKeyhole runestonegatekeyhole;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT)
	public static TABlock_DungeonStoneGate runestonelootgate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT)
	public static TABlock_DungeonStoneGateKeyhole runestonelootgatekeyhole;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneLamp.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStoneLamp runestonelamp;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneSmooth.BLOCKNAME_RUNESTONE)
	public static TABlock_DungeonStoneSmooth runestonesmooth;

	// MOONTEMPLE
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_MoonGem.BLOCKNAME)
	public static TABlock_MoonGem moongem;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneBars.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStoneBars moontemplebars;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStone moontemplebricks;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneSmooth.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStoneSmooth moontemplebrickssmooth;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL)
	public static TABlock_DungeonStoneGate moontemplecellgate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL)
	public static TABlock_DungeonStoneGateKeyhole moontemplecellgatekeyhole;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStoneGate moontemplegate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStoneGateKeyhole moontemplegatekeyhole;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneLamp.BLOCKNAME_MOONTEMPLE)
	public static TABlock_DungeonStoneLamp moontemplelamp;

	// DARKSTONE
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStone.BLOCKNAME_DARK)
	public static TABlock_DungeonStone darkstonebricks;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStone.BLOCKNAME_DARK_FANCY)
	public static TABlock_DungeonStone darkstonefancy;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStone.BLOCKNAME_DARK_LAYERS)
	public static TABlock_DungeonStone darkstonelayers;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK)
	public static TABlock_DungeonStoneGateKeyhole darkstonegatekeyhole;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneGate.BLOCKNAME_DARK)
	public static TABlock_DungeonStoneGate darkstonegate;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_DungeonStoneLamp.BLOCKNAME_DARK)
	public static TABlock_DungeonStoneLamp darkstonelamp;

	// SILENTWOOD
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Ladder.BLOCKNAME)
	public static TABlock_Silentwood_Ladder silentwoodladder;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Leaves.BLOCKNAME)
	public static TABlock_Silentwood_Leaves silentwoodleaves;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Log.BLOCKNAME)
	public static TABlock_Silentwood_Log silentwoodlog;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Silentwood_Planks.BLOCKNAME)
	public static TABlock_Silentwood_Planks silentwoodplanks;

	// WEEPING WILLOW
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_WeepingWillow_Leaves.BLOCKNAME)
	public static TABlock_WeepingWillow_Leaves weepingwillowleaves;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_WeepingWillow_Log.BLOCKNAME)
	public static TABlock_WeepingWillow_Log weepingwillowlog;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_WeepingWillow_Sapling.BLOCKNAME)
	public static TABlock_Plant_WeepingWillow_Sapling weepingwillowsapling;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_WeepingWillow_Planks.BLOCKNAME)
	public static TABlock_WeepingWillow_Planks weepingwillowplanks;

	// MINEABLES
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Material.BLOCKNAME_COAL)
	public static TABlock_Material auroriancoalblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Ore_AurorianCoal.BLOCKNAME)
	public static TABlock_Ore_AurorianCoal auroriancoalore;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Material.BLOCKNAME_CERULEAN)
	public static TABlock_Material ceruleanblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Ore_Cerulean.BLOCKNAME)
	public static TABlock_Ore_Cerulean ceruleanore;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Ore_Geode.BLOCKNAME)
	public static TABlock_Ore_Geode geodeore;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Material.BLOCKNAME_MOONSTONE)
	public static TABlock_Material moonstoneblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Ore_Moonstone.BLOCKNAME)
	public static TABlock_Ore_Moonstone moonstoneore;

	// STAIRS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_AURORIANCOBBLESTONE)
	public static TABlock_Stairs auroriancobblestonestairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_MOONTEMPLE)
	public static TABlock_Stairs moontemplestairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_RUNESTONE)
	public static TABlock_Stairs runestonestairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_SILENTWOOD)
	public static TABlock_Stairs silentwoodstairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_AURORIANSTONE)
	public static TABlock_Stairs aurorianstonestairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_DARK)
	public static TABlock_Stairs darkstonestairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_UMBRASTONEROOFTILES)
	public static TABlock_Stairs umbrastoneroofstairs;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Stairs.BLOCKNAME_WEEPINGWILLOWPLANKS)
	public static TABlock_Stairs weepingwillowplanksstairs;

	private enum TABlockRegistry {
		AURORIANCOBBLESTONE(new TABlock_Terrain_AurorianCobblestone()),
		AURORIANCOBBLESTONESTAIRS(new TABlock_Stairs(new TABlock_Terrain_AurorianCobblestone(), TABlock_Stairs.BLOCKNAME_AURORIANCOBBLESTONE)),
		AURORIANDIRT(new TABlock_Terrain_AurorianDirt()),
		AURORIANFARMTILE(new TABlock_Terrain_AurorianFarmTile()),
		AURORIANFURNACECHIMNEY(new TABlock_FurnaceChimney()),
		AURORIANFURNACEOFF(new TABlock_Furnace(false)),
		AURORIANFURNACEON(new TABlock_Furnace(true)),
		AURORIANGRASS(new TABlock_Terrain_AurorianGrass(TABlock_Terrain_AurorianGrass.BLOCKNAME_LIGHT)),
		AURORIANGRASSLIGHT(new TABlock_Terrain_AurorianGrass(TABlock_Terrain_AurorianGrass.BLOCKNAME)),
		AURORIANPORTAL(new TABlock_Portal()),
		AURORIANPORTALFRAME(new TABlock_PortalframeBricks()),
		AURORIANSTONE(new TABlock_Terrain_AurorianStone()),
		AURORIANSTONEBRICK(new TABlock_Aurorian_Stone_Brick()),
		AURORIANSTONEBRICKSSTAIRS(new TABlock_Stairs(new TABlock_Aurorian_Stone_Brick(), TABlock_Stairs.BLOCKNAME_AURORIANSTONE)),
		BOSSSPAWNERKEEPER(new TABlock_Spawner_Boss(TABlock_Spawner_Boss.Bosses.KEEPER)),
		BOSSSPAWNERMOONQUEEN(new TABlock_Spawner_Boss(TABlock_Spawner_Boss.Bosses.MOONQUEEN)),
		DUNGEONSTONEDARK(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKFANCY(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_FANCY)),
		DUNGEONSTONEDARKGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_DARK, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKGATEKEYHOLE(
			new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK, TABlock_DungeonStoneGate.BLOCKNAME_DARK, TAItem_Special_DungeonKey.Keys.DARKSTONE)),
		DUNGEONSTONEDARKLAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKLAYERS(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_LAYERS)),
		DUNGEONSTONEDARKSTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK), TABlock_Stairs.BLOCKNAME_DARK)),
		DUNGEONSTONEMOONTEMPLE(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEBARS(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATECELL(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLE(
			new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TAItem_Special_DungeonKey.Keys.MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLECELL(
			new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TAItem_Special_DungeonKey.Keys.MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLELAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESMOOTH(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE), TABlock_Stairs.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONERUNESTONE(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEBARS(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATEKEYHOLE(
			new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TAItem_Special_DungeonKey.Keys.RUNESTONE, true)),
		DUNGEONSTONERUNESTONEGATEKEYHOLELOOT(
			new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TAItem_Special_DungeonKey.Keys.RUNESTONELOOT)),
		DUNGEONSTONERUNESTONEGATELOOT(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT)),
		DUNGEONSTONERUNESTONELAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESMOOTH(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE), TABlock_Stairs.BLOCKNAME_RUNESTONE)),
		FLUIDMOLTENCERULEAN(new TABlockFluid_MoltenCerulean()),
		FLUIDMOLTENMOONSTONE(new TABlockFluid_MoltenMoonstone()),
		FLUIDMOONWATER(new TABlockFluid_Moonwater()),
		GLASSAURORIAN(new TABlock_Glass(TABlock_Glass.BLOCKNAME_AURORIAN)),
		GLASSMOON(new TABlock_Glass(TABlock_Glass.BLOCKNAME_MOONGLASS)),
		GLASSPANEAURORIAN(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_AURORIAN)),
		GLASSPANEMOON(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_MOONGLASS)),
		MATERIALCERULEAN(new TABlock_Material(TABlock_Material.BLOCKNAME_CERULEAN)),
		MATERIALCOAL(new TABlock_Material(TABlock_Material.BLOCKNAME_COAL)),
		MATERIALMOONSTONE(new TABlock_Material(TABlock_Material.BLOCKNAME_MOONSTONE)),
		MOONGEM(new TABlock_MoonGem()),
		MOONSAND(new TABlock_Terrain_Moonsand()),
		MUSHROOM(new TABlock_Mushroom()),
		MUSHROOMCRYSTAL(new TABlock_MushroomCrystal()),
		MUSHROOMSMALL(new TABlock_Plant_Mushroom()),
		MUSHROOMSTEM(new TABlock_MushroomStem()),
		MYSTICALBARRIER(new TABlock_MysticalBarrier()),
		OREAURORIANCOAL(new TABlock_Ore_AurorianCoal()),
		ORECERULEAN(new TABlock_Ore_Cerulean()),
		OREGEODE(new TABlock_Ore_Geode()),
		OREMOONSTONE(new TABlock_Ore_Moonstone()),
		PERIDOTITE(new TABlock_Terrain_Peridotite(TABlock_Terrain_Peridotite.BLOCKNAME)),
		PERIDOTITESMOOTH(new TABlock_Terrain_Peridotite(TABlock_Terrain_Peridotite.BLOCKNAME_SMOOTH)),
		PERIDOTITESMOOTHSTAIRS(new TABlock_Stairs(new TABlock_Terrain_Peridotite(TABlock_Terrain_Peridotite.BLOCKNAME_SMOOTH), TABlock_Stairs.BLOCKNAME_PERIDOTITESMOOTH)),
		PLANTLAVENDER(new TABlock_Plant_Lavender()),
		PLANTLAVENDERCROP(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_LAVENDER)),
		PLANTPETUNIA(new TABlock_Plant_Petunia()),
		PLANTSILENTWOODSAPLING(new TABlock_Plant_Silentwood_Sapling()),
		PLANTSILKBERRY(new TABlock_Plant_Silkberry()),
		PLANTSILKBERRYCROP(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_SILKBERRY)),
		PLANTTALLGRASS(new TABlock_Plant_Tallgrass()),
		PLANTTALLGRASSLIGHT(new TABlock_Plant_Tallgrass_Light()),
		SILENTWOODLADDER(new TABlock_Silentwood_Ladder()),
		SILENTWOODLEAVES(new TABlock_Silentwood_Leaves()),
		SILENTWOODLOG(new TABlock_Silentwood_Log()),
		SILENTWOODPLANKS(new TABlock_Silentwood_Planks()),
		SILENTWOODPLANKSSTAIRS(new TABlock_Stairs(new TABlock_Silentwood_Planks(), TABlock_Stairs.BLOCKNAME_SILENTWOOD)),
		SILENTWOODTORCH(new TABlock_Silentwood_Torch()),
		SILENTWOODWORKBENCH(new TABlock_Silentwood_Workbench()),
		UMBRASTONE(new TABlock_UmbraStone(TABlock_UmbraStone.BLOCKNAME_UMBRASTONE)),
		UMBRASTONECRACKED(new TABlock_UmbraStone(TABlock_UmbraStone.BLOCKNAME_UMBRASTONECRACKED)),
		UMBRASTONEROOFTILES(new TABlock_UmbraStone(TABlock_UmbraStone.BLOCKNAME_UMBRASTONEROOFTILES)),
		UMBRASTONEROOFTILESSTAIRS(new TABlock_Stairs(new TABlock_UmbraStone(TABlock_UmbraStone.BLOCKNAME_UMBRASTONEROOFTILES), TABlock_Stairs.BLOCKNAME_UMBRASTONEROOFTILES)),
		URN(new TABlock_Urn()),
		WEEPINGWILLOWLEAVES(new TABlock_WeepingWillow_Leaves()),
		WEEPINGWILLOWLOG(new TABlock_WeepingWillow_Log()),
		WEEPINGWILLOWPLANKS(new TABlock_WeepingWillow_Planks()),
		WEEPINGWILLOWPLANKSSTAIRS(new TABlock_Stairs(new TABlock_UmbraStone(TABlock_WeepingWillow_Planks.BLOCKNAME), TABlock_Stairs.BLOCKNAME_WEEPINGWILLOWPLANKS)),
		WEEPINGWILLOWSAPLING(new TABlock_Plant_WeepingWillow_Sapling());

		private Block modBlock;

		TABlockRegistry(Block i) {
			this.modBlock = i;
		}

		public void registerItemBlock(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(new ItemBlock(this.modBlock).setRegistryName(this.modBlock.getRegistryName()));
		}

		public boolean hasSpecialModel() {
			if (this.modBlock instanceof ISpecialModel) {
				return true;
			} else {
				return false;
			}
		}

		public void InitModel() {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this.modBlock), 0, new ModelResourceLocation(this.modBlock.getRegistryName(), "inventory"));
		}

		public void Register(RegistryEvent.Register<Block> event) {
			event.getRegistry().register(this.modBlock);
		}
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		Fluids.registerFluids();

		for (TABlockRegistry b : TABlockRegistry.values()) {
			b.Register(event);
		}

		TATileEntities.registerTileEntities(event);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for (TABlockRegistry b : TABlockRegistry.values()) {
			if (!b.hasSpecialModel()) {
				b.InitModel();
			} else {
				ISpecialModel model = (ISpecialModel) b.modBlock;
				model.initModel();
			}
		}
	}

	public static void registerItemblocks(RegistryEvent.Register<Item> event) {
		for (TABlockRegistry b : TABlockRegistry.values()) {
			b.registerItemBlock(event);
		}
	}

}
