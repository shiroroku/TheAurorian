package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianMod;
import com.shiroroku.theaurorian.Entities.Boss.*;
import com.shiroroku.theaurorian.Entities.Hostile.*;
import com.shiroroku.theaurorian.Entities.Passive.*;
import com.shiroroku.theaurorian.Entities.Projectiles.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRegistry {
	public static void init() {
		int id = 0;

		quickRegEntity(DisturbedHollowEntity.class, DisturbedHollowEntity.EntityName, id++);
		quickRegEntity(KeeperEntity.class, KeeperEntity.EntityName, id++);
		quickRegEntity(UndeadKnightEntity.class, UndeadKnightEntity.EntityName, id++);
		quickRegEntity(MoonAcolyteEntity.class, MoonAcolyteEntity.EntityName, id++);
		quickRegEntity(MoonQueenEntity.class, MoonQueenEntity.EntityName, id++);
		quickRegEntity(AurorianRabbitEntity.class, AurorianRabbitEntity.EntityName, id++);
		quickRegEntity(AurorianSheepEntity.class, AurorianSheepEntity.EntityName, id++);
		quickRegEntity(AurorianPigEntity.class, AurorianPigEntity.EntityName, id++);
		quickRegEntity(AurorianSlimeEntity.class, AurorianSlimeEntity.EntityName, id++);
		quickRegNonlivingEntity(StickySpikerEntity.class, StickySpikerEntity.EntityName, id++);
		quickRegNonlivingEntity(CeruleanArrowEntity.class, CeruleanArrowEntity.EntityName, id++);
		quickRegEntity(CrystallineSpriteEntity.class, CrystallineSpriteEntity.EntityName, id++);
		quickRegNonlivingEntity(CrystallineBeamEntity.class, CrystallineBeamEntity.EntityName, id++);
		quickRegEntity(SpiritEntity.class, SpiritEntity.EntityName, id++);
		quickRegEntity(SpiderEntity.class, SpiderEntity.EntityName, id++);
		quickRegNonlivingEntity(WebbingEntity.class, WebbingEntity.EntityName, id++);
		quickRegEntity(SpiderlingEntity.class, SpiderlingEntity.EntityName, id++);
		quickRegNonlivingEntity(CrystalArrowEntity.class, CrystalArrowEntity.EntityName, id++);

	}

	private static void quickRegEntity(Class<? extends Entity> mob, String mobname, int id) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(AurorianMod.MODID, mobname), mob, AurorianMod.MODID + "." + mobname, id, AurorianMod.INSTANCE, 64, 3, true, 0xade0f5, 0x3b4759);
	}

	private static void quickRegNonlivingEntity(Class<? extends Entity> mob, String mobname, int id) {
		net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(new ResourceLocation(AurorianMod.MODID, mobname), mob, AurorianMod.MODID + "." + mobname, id, AurorianMod.INSTANCE, 64, 3, true);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		RenderingRegistry.registerEntityRenderingHandler(DisturbedHollowEntity.class, DisturbedHollowEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(KeeperEntity.class, KeeperEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(UndeadKnightEntity.class, UndeadKnightEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MoonAcolyteEntity.class, MoonAcolyteEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MoonQueenEntity.class, MoonQueenEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianRabbitEntity.class, AurorianRabbitEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianSheepEntity.class, AurorianSheepEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianPigEntity.class, AurorianPigEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianSlimeEntity.class, AurorianSlimeEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(StickySpikerEntity.class, StickySpikerEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CeruleanArrowEntity.class, CeruleanArrowEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystallineSpriteEntity.class, CrystallineSpriteEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystallineBeamEntity.class, CrystallineBeamEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(SpiritEntity.class, SpiritEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(SpiderEntity.class, SpiderEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(WebbingEntity.class, WebbingEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(SpiderlingEntity.class, SpiderlingEntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystalArrowEntity.class, CrystalArrowEntityRender.FACTORY);
	}
}
