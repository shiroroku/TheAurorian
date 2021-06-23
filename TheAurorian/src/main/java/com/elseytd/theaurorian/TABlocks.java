package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Blocks.*;
import com.elseytd.theaurorian.Items.TAItem_DungeonKey;
import com.elseytd.theaurorian.TAItems.IUniqueModel;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
public class TABlocks {

	public static class Fluids {
		public static TAFluid_Moonwater MOONWATER = new TAFluid_Moonwater();
		public static TAFluid_MoltenCerulean MOLTENCERULEAN = new TAFluid_MoltenCerulean();
		public static TAFluid_MoltenMoonstone MOLTENMOONSTONE = new TAFluid_MoltenMoonstone();
		public static TAFluid_MoltenAurorianSteel MOLTENAURORIANSTEEL = new TAFluid_MoltenAurorianSteel();

		public static void registerFluids() {
			FluidRegistry.registerFluid(Fluids.MOLTENCERULEAN);
			FluidRegistry.registerFluid(Fluids.MOLTENMOONSTONE);
			FluidRegistry.registerFluid(Fluids.MOONWATER);
			FluidRegistry.registerFluid(Fluids.MOLTENAURORIANSTEEL);

			FluidRegistry.addBucketForFluid(Fluids.MOLTENCERULEAN);
			FluidRegistry.addBucketForFluid(Fluids.MOLTENMOONSTONE);
			FluidRegistry.addBucketForFluid(Fluids.MOONWATER);
			FluidRegistry.addBucketForFluid(Fluids.MOLTENAURORIANSTEEL);
		}
	}

	public enum Registry {
		AURORIANCOBBLESTONE(new TABlock_Aurorian_Cobblestone()),
		AURORIANCOBBLESTONESTAIRS(new TABlock_Stairs(new TABlock_Aurorian_Cobblestone(), TABlock_Stairs.BLOCKNAME_AURORIANCOBBLESTONE)),
		AURORIANDIRT(new TABlock_Aurorian_Dirt()),
		AURORIANFARMTILE(new TABlock_Aurorian_FarmTile()),
		AURORIANFURNACECHIMNEY(new TABlock_FurnaceChimney()),
		AURORIANFURNACEOFF(new TABlock_Furnace(false)),
		AURORIANFURNACEON(new TABlock_Furnace(true)),
		AURORIANGRASS(new TABlock_Aurorian_Grass(TABlock_Aurorian_Grass.BLOCKNAME)),
		AURORIANGRASSLIGHT(new TABlock_Aurorian_Grass(TABlock_Aurorian_Grass.BLOCKNAME_LIGHT)),
		AURORIANPORTAL(new TABlock_Portal()),
		AURORIANPORTALFRAME(new TABlock_PortalframeBricks()),
		AURORIANSTONE(new TABlock_Aurorian_Stone()),
		AURORIANSTONEBRICK(new TABlock_Aurorian_StoneBrick()),
		AURORIANSTONEBRICKSSTAIRS(new TABlock_Stairs(new TABlock_Aurorian_StoneBrick(), TABlock_Stairs.BLOCKNAME_AURORIANSTONE)),
		BOSSSPAWNERKEEPER(new TABlock_BossSpawner(TABlock_BossSpawner.Bosses.KEEPER)),
		BOSSSPAWNERMOONQUEEN(new TABlock_BossSpawner(TABlock_BossSpawner.Bosses.MOONQUEEN)),
		BOSSSPAWNERSPIDER(new TABlock_BossSpawner(TABlock_BossSpawner.Bosses.SPIDER)),
		CRYSTAL(new TABlock_Crystal()),
		DUNGEONSTONEDARK(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKFANCY(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_FANCY)),
		DUNGEONSTONEDARKGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_DARK, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKGATEKEYHOLE(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_DARK, TABlock_DungeonStoneGate.BLOCKNAME_DARK, TAItem_DungeonKey.Keys.DARKSTONE)),
		DUNGEONSTONEDARKLAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKLAYERS(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK_LAYERS)),
		DUNGEONSTONEDARKSTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_DARK), TABlock_Stairs.BLOCKNAME_DARK)),
		DUNGEONSTONEMOONTEMPLE(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEBARS(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATECELL(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLE(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLE, TAItem_DungeonKey.Keys.MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLECELL(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL, TABlock_DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, TAItem_DungeonKey.Keys.MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLELAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESMOOTH(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_MOONTEMPLE), TABlock_Stairs.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONERUNESTONE(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEBARS(new TABlock_DungeonStoneBars(TABlock_DungeonStoneBars.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATE(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATEKEYHOLE(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONE, TAItem_DungeonKey.Keys.RUNESTONE, true)),
		DUNGEONSTONERUNESTONEGATEKEYHOLELOOT(new TABlock_DungeonStoneGateKeyhole(TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TAItem_DungeonKey.Keys.RUNESTONELOOT)),
		DUNGEONSTONERUNESTONEGATELOOT(new TABlock_DungeonStoneGate(TABlock_DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, TABlock_DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT)),
		DUNGEONSTONERUNESTONELAMP(new TABlock_DungeonStoneLamp(TABlock_DungeonStoneLamp.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESMOOTH(new TABlock_DungeonStoneSmooth(TABlock_DungeonStoneSmooth.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESTAIRS(new TABlock_Stairs(new TABlock_DungeonStone(TABlock_DungeonStone.BLOCKNAME_RUNESTONE), TABlock_Stairs.BLOCKNAME_RUNESTONE)),
		FLUIDMOLTENCERULEAN(new TABlockFluid_MoltenCerulean()),
		FLUIDMOLTENMOONSTONE(new TABlockFluid_MoltenMoonstone()),
		FLUIDMOLTENAURORIANSTEEL(new TABlockFluid_MoltenAurorianSteel()),
		FLUIDMOONWATER(new TABlockFluid_Moonwater()),
		GLASSAURORIAN(new TABlock_Glass(TABlock_Glass.BLOCKNAME_AURORIAN)),
		GLASSMOON(new TABlock_Glass(TABlock_Glass.BLOCKNAME_MOONGLASS)),
		GLASSPANEAURORIAN(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_AURORIAN)),
		GLASSPANEMOON(new TABlock_GlassPane(TABlock_GlassPane.BLOCKNAME_MOONGLASS)),
		MATERIALAURORIANSTEEL(new TABlock_Material(TABlock_Material.BLOCKNAME_AURORIANSTEEL)),
		MATERIALCERULEAN(new TABlock_Material(TABlock_Material.BLOCKNAME_CERULEAN)),
		MATERIALCOAL(new TABlock_Material(TABlock_Material.BLOCKNAME_COAL)),
		MATERIALMOONSTONE(new TABlock_Material(TABlock_Material.BLOCKNAME_MOONSTONE)),
		MOONGEM(new TABlock_MoonGem()),
		MOONLIGHTFORGE(new TABlock_MoonLightForge()),
		MOONSAND(new TABlock_Moonsand()),
		MOONTORCH(new TABlock_MoonTorch()),
		MUSHROOM(new TABlock_Mushroom()),
		MUSHROOMCRYSTAL(new TABlock_MushroomCrystal()),
		MUSHROOMSMALL(new TABlock_Plant_Mushroom()),
		MUSHROOMSTEM(new TABlock_MushroomStem()),
		MYSTICALBARRIER(new TABlock_MysticalBarrier()),
		OREAURORIANCOAL(new TABlock_Ore_AurorianCoal()),
		ORECERULEAN(new TABlock_Ore_Cerulean()),
		OREGEODE(new TABlock_Ore_Geode()),
		OREMOONSTONE(new TABlock_Ore_Moonstone()),
		PERIDOTITE(new TABlock_Peridotite(TABlock_Peridotite.BLOCKNAME)),
		PERIDOTITESMOOTH(new TABlock_Peridotite(TABlock_Peridotite.BLOCKNAME_SMOOTH)),
		PERIDOTITESMOOTHSTAIRS(new TABlock_Stairs(new TABlock_Peridotite(TABlock_Peridotite.BLOCKNAME_SMOOTH), TABlock_Stairs.BLOCKNAME_PERIDOTITESMOOTH)),
		PLANTLAVENDER(new TABlock_Plant_Lavender()),
		PLANTLAVENDERCROP(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_LAVENDER)),
		PLANTPETUNIA(new TABlock_Plant_Petunia()),
		PLANTSILENTWOODSAPLING(new TABlock_Plant_Silentwood_Sapling()),
		PLANTSILKBERRY(new TABlock_Plant_Silkberry()),
		PLANTSILKBERRYCROP(new TABlock_Plant_Crops(TABlock_Plant_Crops.BLOCKNAME_SILKBERRY)),
		PLANTTALLGRASS(new TABlock_Plant_Tallgrass()),
		PLANTTALLGRASSLIGHT(new TABlock_Plant_Tallgrass_Light()),
		SCRAPPER(new TABlock_Scrapper()),
		SILENTWOODCHEST(new TABlock_Silentwood_Chest()),
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

		Registry(Block i) {
			this.modBlock = i;
		}

		public void initModel() {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this.modBlock), 0, new ModelResourceLocation(this.modBlock.getRegistryName(), "inventory"));
		}

		public boolean hasUniqueModel() {
			return this.modBlock instanceof IUniqueModel ? true : false;
		}

		public boolean hasUniqueItemBlock() {
			return this.modBlock instanceof IUniqueItemBlock ? true : false;
		}

		public void register(RegistryEvent.Register<Block> event) {
			event.getRegistry().register(this.modBlock);
		}

		public void registerItemBlock(RegistryEvent.Register<Item> event) {
			event.getRegistry().register(new ItemBlock(this.modBlock).setRegistryName(this.modBlock.getRegistryName()));
		}

		public Block getBlock() {
			return this.modBlock;
		}

	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		for (Registry b : Registry.values()) {
			b.register(event);
		}
		TATileEntities.registerTileEntities(event);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		for (Registry b : Registry.values()) {
			if (b.hasUniqueModel()) {
				((IUniqueModel) b.modBlock).initModel();
			} else {
				b.initModel();
			}
		}
	}

	public static void registerItemblocks(RegistryEvent.Register<Item> event) {
		for (Registry b : Registry.values()) {
			if (b.hasUniqueItemBlock()) {
				((IUniqueItemBlock) b.modBlock).registerItemBlock(event);
			} else {
				b.registerItemBlock(event);
			}
		}
	}

	public interface IUniqueItemBlock {
		void registerItemBlock(RegistryEvent.Register<Item> event);
	}

}
