package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.Blocks.*;
import com.shiroroku.theaurorian.Items.DungeonKeyItem;
import com.shiroroku.theaurorian.Registry.ItemRegistry.IUniqueModel;
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
public class BlockRegistry {

	public static class Fluids {
		public static MoonwaterFluid MOONWATER = new MoonwaterFluid();
		public static MoltenCeruleanFluid MOLTENCERULEAN = new MoltenCeruleanFluid();
		public static MoltenMoonstoneFluid MOLTENMOONSTONE = new MoltenMoonstoneFluid();
		public static MoltenAurorianSteelFluid MOLTENAURORIANSTEEL = new MoltenAurorianSteelFluid();

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
		AURORIANCOBBLESTONE(new AurorianCobblestone()),
		AURORIANCOBBLESTONESTAIRS(new StairsBlock(new AurorianCobblestone(), StairsBlock.BLOCKNAME_AURORIANCOBBLESTONE)),
		AURORIANDIRT(new AurorianDirt()),
		AURORIANFARMTILE(new AurorianFarmTile()),
		AURORIANFURNACECHIMNEY(new AurorianFurnaceChimney()),
		AURORIANFURNACEOFF(new AurorianFurnace(false)),
		AURORIANFURNACEON(new AurorianFurnace(true)),
		AURORIANGRASS(new AurorianGrass(AurorianGrass.BLOCKNAME)),
		AURORIANGRASSLIGHT(new AurorianGrass(AurorianGrass.BLOCKNAME_LIGHT)),
		AURORIANPORTAL(new AurorianPortal()),
		AURORIANPORTALFRAME(new AurorianPortalframeBricks()),
		AURORIANSTONE(new AurorianStone()),
		AURORIANSTONEBRICK(new AurorianStoneBrick()),
		AURORIANSTONEBRICKSSTAIRS(new StairsBlock(new AurorianStoneBrick(), StairsBlock.BLOCKNAME_AURORIANSTONE)),
		BOSSSPAWNERKEEPER(new BossSpawner(BossSpawner.Bosses.KEEPER)),
		BOSSSPAWNERMOONQUEEN(new BossSpawner(BossSpawner.Bosses.MOONQUEEN)),
		BOSSSPAWNERSPIDER(new BossSpawner(BossSpawner.Bosses.SPIDER)),
		CRYSTAL(new CrystalBlock()),
		DUNGEONSTONEDARK(new DungeonStone(DungeonStone.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKFANCY(new DungeonStone(DungeonStone.BLOCKNAME_DARK_FANCY)),
		DUNGEONSTONEDARKGATE(new DungeonStoneGate(DungeonStoneGate.BLOCKNAME_DARK, DungeonStoneGateKeyhole.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKGATEKEYHOLE(new DungeonStoneGateKeyhole(DungeonStoneGateKeyhole.BLOCKNAME_DARK, DungeonStoneGate.BLOCKNAME_DARK, DungeonKeyItem.Keys.DARKSTONE)),
		DUNGEONSTONEDARKLAMP(new DungeonStoneLamp(DungeonStoneLamp.BLOCKNAME_DARK)),
		DUNGEONSTONEDARKLAYERS(new DungeonStone(DungeonStone.BLOCKNAME_DARK_LAYERS)),
		DUNGEONSTONEDARKSTAIRS(new StairsBlock(new DungeonStone(DungeonStone.BLOCKNAME_DARK), StairsBlock.BLOCKNAME_DARK)),
		DUNGEONSTONEMOONTEMPLE(new DungeonStone(DungeonStone.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEBARS(new DungeonStoneBars(DungeonStoneBars.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATE(new DungeonStoneGate(DungeonStoneGate.BLOCKNAME_MOONTEMPLE, DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATECELL(new DungeonStoneGate(DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLE(new DungeonStoneGateKeyhole(DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLE, DungeonStoneGate.BLOCKNAME_MOONTEMPLE, DungeonKeyItem.Keys.MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLEGATEKEYHOLECELL(new DungeonStoneGateKeyhole(DungeonStoneGateKeyhole.BLOCKNAME_MOONTEMPLECELL, DungeonStoneGate.BLOCKNAME_MOONTEMPLECELL, DungeonKeyItem.Keys.MOONTEMPLECELL)),
		DUNGEONSTONEMOONTEMPLELAMP(new DungeonStoneLamp(DungeonStoneLamp.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESMOOTH(new DungeonStoneSmooth(DungeonStoneSmooth.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONEMOONTEMPLESTAIRS(new StairsBlock(new DungeonStone(DungeonStone.BLOCKNAME_MOONTEMPLE), StairsBlock.BLOCKNAME_MOONTEMPLE)),
		DUNGEONSTONERUNESTONE(new DungeonStone(DungeonStone.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEBARS(new DungeonStoneBars(DungeonStoneBars.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATE(new DungeonStoneGate(DungeonStoneGate.BLOCKNAME_RUNESTONE, DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONEGATEKEYHOLE(new DungeonStoneGateKeyhole(DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONE, DungeonStoneGate.BLOCKNAME_RUNESTONE, DungeonKeyItem.Keys.RUNESTONE, true)),
		DUNGEONSTONERUNESTONEGATEKEYHOLELOOT(new DungeonStoneGateKeyhole(DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT, DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, DungeonKeyItem.Keys.RUNESTONELOOT)),
		DUNGEONSTONERUNESTONEGATELOOT(new DungeonStoneGate(DungeonStoneGate.BLOCKNAME_RUNESTONELOOT, DungeonStoneGateKeyhole.BLOCKNAME_RUNESTONELOOT)),
		DUNGEONSTONERUNESTONELAMP(new DungeonStoneLamp(DungeonStoneLamp.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESMOOTH(new DungeonStoneSmooth(DungeonStoneSmooth.BLOCKNAME_RUNESTONE)),
		DUNGEONSTONERUNESTONESTAIRS(new StairsBlock(new DungeonStone(DungeonStone.BLOCKNAME_RUNESTONE), StairsBlock.BLOCKNAME_RUNESTONE)),
		FLUIDMOLTENCERULEAN(new MoltenCeruleanFluidBlock()),
		FLUIDMOLTENMOONSTONE(new MoltenMoonstoneFluidBlock()),
		FLUIDMOLTENAURORIANSTEEL(new MoltenAurorianSteelFluidBlock()),
		FLUIDMOONWATER(new MoonwaterFluidBlock()),
		GLASSAURORIAN(new AurorianGlass(AurorianGlass.BLOCKNAME_AURORIAN)),
		GLASSMOON(new AurorianGlass(AurorianGlass.BLOCKNAME_MOONGLASS)),
		GLASSPANEAURORIAN(new AurorianGlassPane(AurorianGlassPane.BLOCKNAME_AURORIAN)),
		GLASSPANEMOON(new AurorianGlassPane(AurorianGlassPane.BLOCKNAME_MOONGLASS)),
		MATERIALAURORIANSTEEL(new MaterialBlock(MaterialBlock.BLOCKNAME_AURORIANSTEEL)),
		MATERIALCERULEAN(new MaterialBlock(MaterialBlock.BLOCKNAME_CERULEAN)),
		MATERIALCOAL(new MaterialBlock(MaterialBlock.BLOCKNAME_COAL)),
		MATERIALMOONSTONE(new MaterialBlock(MaterialBlock.BLOCKNAME_MOONSTONE)),
		MOONGEM(new MoonGem()),
		MOONLIGHTFORGE(new MoonLightForge()),
		MOONSAND(new Moonsand()),
		MOONTORCH(new MoonTorch()),
		MUSHROOM(new IndigoMushroom()),
		MUSHROOMCRYSTAL(new IndigoMushroomCrystal()),
		MUSHROOMSMALL(new IndigoMushroomSmall()),
		MUSHROOMSTEM(new IndigoMushroomStem()),
		MYSTICALBARRIER(new MysticalBarrier()),
		OREAURORIANCOAL(new AurorianCoalOre()),
		ORECERULEAN(new CeruleanOre()),
		OREGEODE(new GeodeOre()),
		OREMOONSTONE(new MoonstoneOre()),
		PERIDOTITE(new Peridotite(Peridotite.BLOCKNAME)),
		PERIDOTITESMOOTH(new Peridotite(Peridotite.BLOCKNAME_SMOOTH)),
		PERIDOTITESMOOTHSTAIRS(new StairsBlock(new Peridotite(Peridotite.BLOCKNAME_SMOOTH), StairsBlock.BLOCKNAME_PERIDOTITESMOOTH)),
		PLANTLAVENDER(new LavenderBlock()),
		PLANTLAVENDERCROP(new CropsBlock(CropsBlock.BLOCKNAME_LAVENDER)),
		PLANTPETUNIA(new PetuniaBlock()),
		PLANTSILENTWOODSAPLING(new SilentwoodSapling()),
		PLANTSILKBERRY(new SilkberryBlock()),
		PLANTSILKBERRYCROP(new CropsBlock(CropsBlock.BLOCKNAME_SILKBERRY)),
		PLANTTALLGRASS(new AurorianTallgrass()),
		PLANTTALLGRASSLIGHT(new AurorianTallgrassLight()),
		SCRAPPER(new ScrapperBlock()),
		SILENTWOODCHEST(new SilentwoodChest()),
		SILENTWOODLADDER(new SilentwoodLadder()),
		SILENTWOODLEAVES(new SilentwoodLeaves()),
		SILENTWOODLOG(new SilentwoodLog()),
		SILENTWOODPLANKS(new SilentwoodPlanks()),
		SILENTWOODPLANKSSTAIRS(new StairsBlock(new SilentwoodPlanks(), StairsBlock.BLOCKNAME_SILENTWOOD)),
		SILENTWOODTORCH(new SilentwoodTorch()),
		SILENTWOODWORKBENCH(new SilentwoodWorkbench()),
		UMBRASTONE(new UmbraStone(UmbraStone.BLOCKNAME_UMBRASTONE)),
		UMBRASTONECRACKED(new UmbraStone(UmbraStone.BLOCKNAME_UMBRASTONECRACKED)),
		UMBRASTONEROOFTILES(new UmbraStone(UmbraStone.BLOCKNAME_UMBRASTONEROOFTILES)),
		UMBRASTONEROOFTILESSTAIRS(new StairsBlock(new UmbraStone(UmbraStone.BLOCKNAME_UMBRASTONEROOFTILES), StairsBlock.BLOCKNAME_UMBRASTONEROOFTILES)),
		URN(new UrnBlock()),
		WEEPINGWILLOWLEAVES(new WeepingWillowLeaves()),
		WEEPINGWILLOWLOG(new WeepingWillowLog()),
		WEEPINGWILLOWPLANKS(new WeepingWillowPlanks()),
		WEEPINGWILLOWPLANKSSTAIRS(new StairsBlock(new UmbraStone(WeepingWillowPlanks.BLOCKNAME), StairsBlock.BLOCKNAME_WEEPINGWILLOWPLANKS)),
		WEEPINGWILLOWSAPLING(new WeepingWillowSapling());

		private final Block modBlock;

		Registry(Block i) {
			this.modBlock = i;
		}

		public void initModel() {
			ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this.modBlock), 0, new ModelResourceLocation(this.modBlock.getRegistryName(), "inventory"));
		}

		public boolean hasUniqueModel() {
			return this.modBlock instanceof IUniqueModel;
		}

		public boolean hasUniqueItemBlock() {
			return this.modBlock instanceof IUniqueItemBlock;
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
		TileEntityRegistry.registerTileEntities(event);
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
