package com.shiroroku.theaurorian.Misc;

import com.shiroroku.theaurorian.AurorianMod;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class TPTACommand extends CommandBase {

	//Used for debugging only, thanks to: https://wiki.mcjty.eu/modding/index.php?title=Commands-1.12

	public TPTACommand() {
		aliases = Lists.newArrayList(AurorianMod.MODID, "TPTA", "tpta");
	}

	private final List<String> aliases;

	@Override
	@Nonnull
	public String getName() {
		return "tpta";
	}

	@Override
	@Nonnull
	public String getUsage(@Nonnull ICommandSender sender) {
		return "tpta <id>";
	}

	@Override
	@Nonnull
	public List<String> getAliases() {
		return aliases;
	}

	@Override
	public void execute(@Nonnull MinecraftServer server, @Nonnull ICommandSender sender, @Nonnull String[] args) throws CommandException {
		if (args.length < 1) {
			return;
		}
		String s = args[0];
		int dim;
		try {
			dim = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			sender.sendMessage(new TextComponentString(TextFormatting.RED + "Error parsing dimension!"));
			return;
		}

		if (sender instanceof EntityPlayer) {
			if (((EntityPlayer) sender).isCreative()) {
				TPTATeleport.teleportToDimension((EntityPlayer) sender, dim, 0, 100, 0);
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	@Nonnull
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		return Collections.emptyList();
	}

}
