package com.elseytd.theaurorian.Compat.TinkersConstruct;

import com.elseytd.theaurorian.TAConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TATinkersTrait_AurorianEmpowered extends AbstractTrait {

	public TATinkersTrait_AurorianEmpowered() {
		super("aurorianempowered", TextFormatting.BLUE);
	}

	@Override
	public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
		if (event.getEntityPlayer().dimension == TAConfig.Config_AurorianDimID) {
			event.setNewSpeed(event.getNewSpeed() * 1.5F);
		}else {
			event.setNewSpeed(event.getNewSpeed());
		}
	}

}
