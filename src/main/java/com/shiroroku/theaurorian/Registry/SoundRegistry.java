package com.shiroroku.theaurorian.Registry;

import com.shiroroku.theaurorian.AurorianMod;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class SoundRegistry {

	public static MusicTicker.MusicType MUSICTYPE;

	public static final SoundEvent MUSIC = getSoundEvent("music");
	public static final SoundEvent WEEPINGWILLOWBELL = getSoundEvent("weepingwillowbell");

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		event.getRegistry().register(MUSIC);
		event.getRegistry().register(WEEPINGWILLOWBELL);
	}

	@SideOnly(Side.CLIENT)
	public static void addMusicTypes() {
		MUSICTYPE = net.minecraftforge.client.EnumHelperClient.addMusicType("TAMUSIC", MUSIC, 1200, 3600);
	}

	private static SoundEvent getSoundEvent(String sound) {
		return new SoundEvent(new ResourceLocation(AurorianMod.MODID, sound)).setRegistryName(sound);
	}
}
