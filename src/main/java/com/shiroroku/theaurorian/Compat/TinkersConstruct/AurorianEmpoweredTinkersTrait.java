package com.shiroroku.theaurorian.Compat.TinkersConstruct;

import com.shiroroku.theaurorian.AurorianConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class AurorianEmpoweredTinkersTrait extends AbstractTrait {

	public AurorianEmpoweredTinkersTrait() {
		super("aurorianempowered", TextFormatting.BLUE);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer().dimension == AurorianConfig.Config_AurorianDimID) {
			event.setNewSpeed(event.getNewSpeed() * 1.5F);
		}else {
			event.setNewSpeed(event.getNewSpeed());
		}
	}

}
