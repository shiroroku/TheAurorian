package com.elseytd.theaurorian;

import com.elseytd.theaurorian.Entities.Boss.*;
import com.elseytd.theaurorian.Entities.Hostile.*;
import com.elseytd.theaurorian.Entities.Passive.*;
import com.elseytd.theaurorian.Entities.Projectiles.*;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TAEntities {
	public static void init() {
		int id = 0;

		quickRegEntity(DisturbedHollow_Entity.class, DisturbedHollow_Entity.EntityName, id++);
		quickRegEntity(Keeper_Entity.class, Keeper_Entity.EntityName, id++);
		quickRegEntity(UndeadKnight_Entity.class, UndeadKnight_Entity.EntityName, id++);
		quickRegEntity(MoonAcolyte_Entity.class, MoonAcolyte_Entity.EntityName, id++);
		quickRegEntity(MoonQueen_Entity.class, MoonQueen_Entity.EntityName, id++);
		quickRegEntity(AurorianRabbit_Entity.class, AurorianRabbit_Entity.EntityName, id++);
		quickRegEntity(AurorianSheep_Entity.class, AurorianSheep_Entity.EntityName, id++);
		quickRegEntity(AurorianPig_Entity.class, AurorianPig_Entity.EntityName, id++);
		quickRegEntity(AurorianSlime_Entity.class, AurorianSlime_Entity.EntityName, id++);
		quickRegNonlivingEntity(StickySpiker_Entity.class, StickySpiker_Entity.EntityName, id++);
		quickRegNonlivingEntity(CeruleanArrow_Entity.class, CeruleanArrow_Entity.EntityName, id++);
		quickRegEntity(CrystallineSprite_Entity.class, CrystallineSprite_Entity.EntityName, id++);
		quickRegNonlivingEntity(CrystallineBeam_Entity.class, CrystallineBeam_Entity.EntityName, id++);
		quickRegEntity(Spirit_Entity.class, Spirit_Entity.EntityName, id++);
		quickRegEntity(Spider_Entity.class, Spider_Entity.EntityName, id++);
		quickRegNonlivingEntity(Webbing_Entity.class, Webbing_Entity.EntityName, id++);
		quickRegEntity(Spiderling_Entity.class, Spiderling_Entity.EntityName, id++);
		quickRegNonlivingEntity(CrystalArrow_Entity.class, CrystalArrow_Entity.EntityName, id++);

	}

	private static void quickRegEntity(Class<? extends Entity> mob, String mobname, int id) {
		EntityRegistry.registerModEntity(new ResourceLocation(TAMod.MODID, mobname), mob, TAMod.MODID + "." + mobname, id, TAMod.INSTANCE, 64, 3, true, 0xade0f5, 0x3b4759);
	}

	private static void quickRegNonlivingEntity(Class<? extends Entity> mob, String mobname, int id) {
		EntityRegistry.registerModEntity(new ResourceLocation(TAMod.MODID, mobname), mob, TAMod.MODID + "." + mobname, id, TAMod.INSTANCE, 64, 3, true);
	}

	@SideOnly(Side.CLIENT)
	public static void initModels() {
		RenderingRegistry.registerEntityRenderingHandler(DisturbedHollow_Entity.class, DisturbedHollow_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(Keeper_Entity.class, Keeper_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(UndeadKnight_Entity.class, UndeadKnight_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MoonAcolyte_Entity.class, MoonAcolyte_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(MoonQueen_Entity.class, MoonQueen_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianRabbit_Entity.class, AurorianRabbit_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianSheep_Entity.class, AurorianSheep_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianPig_Entity.class, AurorianPig_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(AurorianSlime_Entity.class, AurorianSlime_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(StickySpiker_Entity.class, StickySpiker_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CeruleanArrow_Entity.class, CeruleanArrow_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystallineSprite_Entity.class, CrystallineSprite_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystallineBeam_Entity.class, CrystallineBeam_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(Spirit_Entity.class, Spirit_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(Spider_Entity.class, Spider_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(Webbing_Entity.class, Webbing_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(Spiderling_Entity.class, Spiderling_EntityRender.FACTORY);
		RenderingRegistry.registerEntityRenderingHandler(CrystalArrow_Entity.class, CrystalArrow_EntityRender.FACTORY);
	}
}
