package com.elseytd.theaurorian;

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
import com.elseytd.theaurorian.Blocks.TABlock_Ore_AurorianCoal;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Cerulean;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Geode;
import com.elseytd.theaurorian.Blocks.TABlock_Ore_Moonstone;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Crops;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Lavender;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Silentwood_Sapling;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Silkberry;
import com.elseytd.theaurorian.Blocks.TABlock_Plant_Tallgrass;
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
import com.elseytd.theaurorian.Blocks.TABlock_Terrain_Moonsand;
import com.elseytd.theaurorian.Blocks.TAFluid_Moonwater;
import com.elseytd.theaurorian.Compat.TABlockFluid_MoltenCerulean;
import com.elseytd.theaurorian.Compat.TABlockFluid_MoltenMoonstone;
import com.elseytd.theaurorian.Compat.TAFluid_MoltenCerulean;
import com.elseytd.theaurorian.Compat.TAFluid_MoltenMoonstone;
import com.elseytd.theaurorian.Items.TAItem_Special_DungeonKey;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@Mod.EventBusSubscriber
public class TABlocks {

	// FLUID
	public static TAFluid_Moonwater FLUID_MOONWATER = new TAFluid_Moonwater();
	public static TAFluid_MoltenCerulean FLUID_MOLTENCERULEAN = new TAFluid_MoltenCerulean();
	public static TAFluid_MoltenMoonstone FLUID_MOLTENMOONSTONE = new TAFluid_MoltenMoonstone();

	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_Moonwater.FLUIDNAME)
	public static TABlockFluid_Moonwater moonwaterblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_MoltenCerulean.FLUIDNAME)
	public static TABlockFluid_MoltenCerulean moltenceruleanblock;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TAFluid_MoltenMoonstone.FLUIDNAME)
	public static TABlockFluid_MoltenMoonstone moltenmoonstoneblock;

	// SPECIAL
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

	// PLANTS
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Plant_Tallgrass.BLOCKNAME)
	public static TABlock_Plant_Tallgrass auroriantallgrass;
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
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_AurorianStone.BLOCKNAME)
	public static TABlock_Terrain_AurorianStone aurorianstone;
	@GameRegistry.ObjectHolder(TAMod.MODID + ":" + TABlock_Terrain_Moonsand.BLOCKNAME)
	public static TABlock_Terrain_Moonsand moonsand;

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

	@SideOnly(Side.CLIENT)
	public static void initModels() {

		moonwaterblock.initModel();
		moltenceruleanblock.initModel();
		moltenmoonstoneblock.initModel();

		// SPECIAL
		aurorianfurnace.initModel();
		aurorianfurnacechimney.initModel();
		aurorianfurnacelit.initModel();
		aurorianportal.initModel();
		aurorianportalframebricks.initModel();
		bossspawnerkeeper.initModel();
		bossspawnermoonqueen.initModel();
		silentwoodtorch.initModel();
		silentwoodworkbench.initModel();
		moonglass.initModel();
		aurorianglass.initModel();
		moonglasspane.initModel();
		aurorianglasspane.initModel();

		// PLANTS
		auroriantallgrass.initModel();
		silkberrycrop.initModel();
		lavendercrop.initModel();
		lavenderplant.initModel();
		silentwoodsapling.initModel();
		silkberryplant.initModel();

		// TERRAIN
		auroriancobblestone.initModel();
		aurorianstonebrick.initModel();
		auroriandirt.initModel();
		aurorianfarmtile.initModel();
		auroriangrass.initModel();
		aurorianstone.initModel();
		moonsand.initModel();

		// RUNESTONE
		runestone.initModel();
		runestonebars.initModel();
		runestonegate.initModel();
		runestonegatekeyhole.initModel();
		runestonelootgate.initModel();
		runestonelootgatekeyhole.initModel();
		runestonelamp.initModel();
		runestonesmooth.initModel();

		// MOONTEMPLE
		moongem.initModel();
		moontemplebars.initModel();
		moontemplebricks.initModel();
		moontemplebrickssmooth.initModel();
		moontemplecellgate.initModel();
		moontemplecellgatekeyhole.initModel();
		moontemplegate.initModel();
		moontemplegatekeyhole.initModel();
		moontemplelamp.initModel();

		// DARKSTONE
		darkstonebricks.initModel();
		darkstonefancy.initModel();
		darkstonelayers.initModel();
		darkstonegate.initModel();
		darkstonegatekeyhole.initModel();
		darkstonelamp.initModel();
		
		// SILENTWOOD
		silentwoodladder.initModel();
		silentwoodleaves.initModel();
		silentwoodlog.initModel();
		silentwoodplanks.initModel();

		// MINEABLES
		auroriancoalblock.initModel();
		auroriancoalore.initModel();
		ceruleanblock.initModel();
		ceruleanore.initModel();
		geodeore.initModel();
		moonstoneblock.initModel();
		moonstoneore.initModel();

		// STAIRS
		auroriancobblestonestairs.initModel();
		moontemplestairs.initModel();
		runestonestairs.initModel();
		silentwoodstairs.initModel();
		aurorianstonestairs.initModel();
		darkstonestairs.initModel();

	}

	public static void itemblockQuickReg(RegistryEvent.Register<Item> event, Block b) {
		event.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		// FLUID
		FluidRegistry.registerFluid(FLUID_MOONWATER);
		FluidRegistry.addBucketForFluid(FLUID_MOONWATER);
		event.getRegistry().register(new TABlockFluid_Moonwater());

		FluidRegistry.registerFluid(FLUID_MOLTENCERULEAN);
		FluidRegistry.addBucketForFluid(FLUID_MOLTENCERULEAN);
		event.getRegistry().register(new TABlockFluid_MoltenCerulean());

		FluidRegistry.registerFluid(FLUID_MOLTENMOONSTONE);
		FluidRegistry.addBucketForFluid(FLUID_MOLTENMOONSTONE);
		event.getRegistry().register(new TABlockFluid_MoltenMoonstone());

		// SPECIAL
		event.getRegistry().register(new TABlock_Furnace(false));
		event.getRegistry().register(new TABlock_Furnace(true));
		event.getRegistry().register(new TABlock_FurnaceChimney());
		event.getRegistry().register(new TABlock_Portal());
		event.getRegistry().register(new TABlock_PortalframeBricks());
		event.getRegistry().register(new TABlock_Silentwood_Torch());
		event.getRegistry().register(new TABlock_Silentwood_Workbench());
		event.getRegistry().register(new TABlock_Spawner_Boss(TABlock_Spawner_Boss.BLOCKNAME_KEEPER));
		event.getRegistry().register(new TABlock_Spawner_Boss(TABlock_Spawner_Boss.BLOCKNAME_MOONQUEEN));
		event.getRegistry().register(new TABlock_Glass(TABlock_Glass.BLOCKNAME_MOONGLASS));
		event.getRegistry().register(new TABlock_Glass(TABlock_Glass.BLOCKNAME_AURORIAN));
		event.getRegistry().register(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_MOONGLASS));
		event.getRegistry().register(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_AURORIAN));

		// TERRAIN
		event.getRegistry().register(new TABlock_Terrain_AurorianCobblestone());
		event.getRegistry().register(new TABlock_Aurorian_Stone_Brick());
		event.getRegistry().register(new TABlock_Terrain_AurorianDirt());
		event.getRegistry().register(new TABlock_Terrain_AurorianFarmTile());
		event.getRegistry().register(new TABlock_Terrain_AurorianGrass());
		event.getRegistry().register(new TABlock_Terrain_AurorianStone());
		event.getRegistry().register(new TABlock_Terrain_Moonsand());

		// PLANTS
		event.getRegistry().register(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_SILKBERRY));
		event.getRegistry().register(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_LAVENDER));
		event.getRegistry().register(new TABlock_Plant_Lavender());
		event.getRegistry().register(new TABlock_Plant_Silentwood_Sapling());
		event.getRegistry().register(new TABlock_Plant_Silkberry());
		event.getRegistry().register(new TABlock_Plant_Tallgrass());

		// RUNESTONE
		event.getRegistry().register(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE));
		event.getRegistry().register(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_RUNESTONE));
		event.getRegistry().register(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE));
		event.getRegistry().register(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TAItem_Special_DungeonKey.ITEMNAME_RUNESTONE, true));
		event.getRegistry().register(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT));
		event.getRegistry().register(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TAItem_Special_DungeonKey.ITEMNAME_RUNESTONELOOT));
		event.getRegistry().register(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_RUNESTONE));
		event.getRegistry().register(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_RUNESTONE));

		// MOONTEMPLE
		event.getRegistry().register(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL));
		event.getRegistry().register(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TAItem_Special_DungeonKey.ITEMNAME_MOONTEMPLECELL));
		event.getRegistry().register(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_MoonGem());

		// DARKSTONE
		event.getRegistry().register(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK));
		event.getRegistry().register(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_FANCY));
		event.getRegistry().register(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_LAYERS));
		event.getRegistry().register(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_DARK, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK));
		event.getRegistry().register(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK, TABlock_DungeonStoneGate.BLOCKNAME_DARK, TAItem_Special_DungeonKey.ITEMNAME_DARKSTONE));
		event.getRegistry().register(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_DARK));
		
		// SILENTWOOD
		event.getRegistry().register(new TABlock_Silentwood_Ladder());
		event.getRegistry().register(new TABlock_Silentwood_Leaves());
		event.getRegistry().register(new TABlock_Silentwood_Log());
		event.getRegistry().register(new TABlock_Silentwood_Planks());

		// MINEABLE
		event.getRegistry().register(new TABlock_Material(TABlock_Material.BLOCKNAME_CERULEAN));
		event.getRegistry().register(new TABlock_Material(TABlock_Material.BLOCKNAME_COAL));
		event.getRegistry().register(new TABlock_Material(TABlock_Material.BLOCKNAME_MOONSTONE));
		event.getRegistry().register(new TABlock_Ore_AurorianCoal());
		event.getRegistry().register(new TABlock_Ore_Cerulean());
		event.getRegistry().register(new TABlock_Ore_Geode());
		event.getRegistry().register(new TABlock_Ore_Moonstone());

		// STAIRS
		event.getRegistry().register(new TABlock_Stairs(new TABlock_Terrain_AurorianCobblestone(), TABlock_Stairs.BLOCKNAME_AURORIANCOBBLESTONE));
		event.getRegistry().register(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE), TABlock_Stairs.BLOCKNAME_MOONTEMPLE));
		event.getRegistry().register(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE), TABlock_Stairs.BLOCKNAME_RUNESTONE));
		event.getRegistry().register(new TABlock_Stairs(new TABlock_Silentwood_Planks(), TABlock_Stairs.BLOCKNAME_SILENTWOOD));
		event.getRegistry().register(new TABlock_Stairs(new TABlock_Aurorian_Stone_Brick(), TABlock_Stairs.BLOCKNAME_AURORIANSTONE));
		event.getRegistry().register(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK), TABlock_Stairs.BLOCKNAME_DARK));

		TATileEntities.registerTileEntities(event);
	}

	public static void registerItemblocks(RegistryEvent.Register<Item> event) {
		// FLUID
		itemblockQuickReg(event, TABlocks.moonwaterblock);
		itemblockQuickReg(event, TABlocks.moltenceruleanblock);
		itemblockQuickReg(event, TABlocks.moltenmoonstoneblock);

		// SPECIAL 
		itemblockQuickReg(event, TABlocks.aurorianfurnace);
		itemblockQuickReg(event, TABlocks.aurorianfurnacechimney);
		itemblockQuickReg(event, TABlocks.aurorianfurnacelit);
		itemblockQuickReg(event, TABlocks.aurorianportal);
		itemblockQuickReg(event, TABlocks.aurorianportalframebricks);
		itemblockQuickReg(event, TABlocks.bossspawnerkeeper);
		itemblockQuickReg(event, TABlocks.bossspawnermoonqueen);
		itemblockQuickReg(event, TABlocks.silentwoodtorch);
		itemblockQuickReg(event, TABlocks.silentwoodworkbench);
		itemblockQuickReg(event, TABlocks.moonglass);
		itemblockQuickReg(event, TABlocks.aurorianglass);
		itemblockQuickReg(event, TABlocks.moonglasspane);
		itemblockQuickReg(event, TABlocks.aurorianglasspane);

		// TERRAIN
		itemblockQuickReg(event, TABlocks.auroriancobblestone);
		itemblockQuickReg(event, TABlocks.aurorianstonebrick);
		itemblockQuickReg(event, TABlocks.auroriandirt);
		itemblockQuickReg(event, TABlocks.aurorianfarmtile);
		itemblockQuickReg(event, TABlocks.auroriangrass);
		itemblockQuickReg(event, TABlocks.aurorianstone);
		itemblockQuickReg(event, TABlocks.moonsand);

		// PLANTS
		itemblockQuickReg(event, TABlocks.auroriantallgrass);
		itemblockQuickReg(event, TABlocks.lavendercrop);
		itemblockQuickReg(event, TABlocks.silkberrycrop);
		itemblockQuickReg(event, TABlocks.lavenderplant);
		itemblockQuickReg(event, TABlocks.silentwoodsapling);
		itemblockQuickReg(event, TABlocks.silkberryplant);

		// RUNESTONE
		itemblockQuickReg(event, TABlocks.runestone);
		itemblockQuickReg(event, TABlocks.runestonebars);
		itemblockQuickReg(event, TABlocks.runestonegate);
		itemblockQuickReg(event, TABlocks.runestonegatekeyhole);
		itemblockQuickReg(event, TABlocks.runestonelootgate);
		itemblockQuickReg(event, TABlocks.runestonelootgatekeyhole);
		itemblockQuickReg(event, TABlocks.runestonelamp);
		itemblockQuickReg(event, TABlocks.runestonesmooth);

		// MOONTEMPLE
		itemblockQuickReg(event, TABlocks.moongem);
		itemblockQuickReg(event, TABlocks.moontemplebars);
		itemblockQuickReg(event, TABlocks.moontemplebricks);
		itemblockQuickReg(event, TABlocks.moontemplebrickssmooth);
		itemblockQuickReg(event, TABlocks.moontemplecellgate);
		itemblockQuickReg(event, TABlocks.moontemplecellgatekeyhole);
		itemblockQuickReg(event, TABlocks.moontemplegate);
		itemblockQuickReg(event, TABlocks.moontemplegatekeyhole);
		itemblockQuickReg(event, TABlocks.moontemplelamp);

		// DARKSTONE
		itemblockQuickReg(event, TABlocks.darkstonebricks);
		itemblockQuickReg(event, TABlocks.darkstonelayers);
		itemblockQuickReg(event, TABlocks.darkstonefancy);
		itemblockQuickReg(event, TABlocks.darkstonegate);
		itemblockQuickReg(event, TABlocks.darkstonegatekeyhole);
		itemblockQuickReg(event, TABlocks.darkstonelamp);
		
		// SILENTWOOD
		itemblockQuickReg(event, TABlocks.silentwoodladder);
		itemblockQuickReg(event, TABlocks.silentwoodleaves);
		itemblockQuickReg(event, TABlocks.silentwoodlog);
		itemblockQuickReg(event, TABlocks.silentwoodplanks);

		// MINEABLE
		itemblockQuickReg(event, TABlocks.auroriancoalblock);
		itemblockQuickReg(event, TABlocks.auroriancoalore);
		itemblockQuickReg(event, TABlocks.ceruleanblock);
		itemblockQuickReg(event, TABlocks.ceruleanore);
		itemblockQuickReg(event, TABlocks.geodeore);
		itemblockQuickReg(event, TABlocks.moonstoneblock);
		itemblockQuickReg(event, TABlocks.moonstoneore);

		// STAIRS
		itemblockQuickReg(event, TABlocks.auroriancobblestonestairs);
		itemblockQuickReg(event, TABlocks.moontemplestairs);
		itemblockQuickReg(event, TABlocks.runestonestairs);
		itemblockQuickReg(event, TABlocks.silentwoodstairs);
		itemblockQuickReg(event, TABlocks.aurorianstonestairs);
		itemblockQuickReg(event, TABlocks.darkstonestairs);
	}

	public static void registerOreDictionary() {
		OreDictionary.registerOre("treeLeaves", TABlocks.silentwoodplanks);
		OreDictionary.registerOre("stickWood", TAItems.silentwoodstick);
		OreDictionary.registerOre("logSilentwood", TABlocks.silentwoodlog);
		OreDictionary.registerOre("plankSilentwood", TABlocks.silentwoodplanks);

		OreDictionary.registerOre("oreAurorianCoal", TABlocks.auroriancoalore);
		OreDictionary.registerOre("oreAurorianGeode", TABlocks.geodeore);

		OreDictionary.registerOre("oreCerulean", TABlocks.ceruleanore);
		OreDictionary.registerOre("blockCerulean", TABlocks.ceruleanblock);
		OreDictionary.registerOre("ingotCerulean", TAItems.ceruleaningot);
		OreDictionary.registerOre("nuggetCerulean", TAItems.ceruleannugget);

		OreDictionary.registerOre("oreMoonstone", TABlocks.moonstoneore);
		OreDictionary.registerOre("blockMoonstone", TABlocks.moonstoneblock);
		OreDictionary.registerOre("ingotMoonstone", TAItems.moonstoneingot);
		OreDictionary.registerOre("nuggetMoonstone", TAItems.moonstonenugget);
	}

}
